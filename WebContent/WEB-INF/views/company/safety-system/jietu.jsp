  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="/WEB-INF/views/taglibs.jsp" %>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>

    <!DOCTYPE HTML>
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>JavaScript 图片截取效果</title>
    <%@ include file="/WEB-INF/inc/print.inc"%>
    </head>
    <body>
    <script type="text/javascript">
    var isIE = (document.all) ? true : false;

    var isIE6 = isIE && ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);

    var $ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
    };

    var Class = {
    create: function() {
    return function() { this.initialize.apply(this, arguments); }
    }
    }

    var Extend = function(destination, source) {
    for (var property in source) {
    destination[property] = source[property];
    }
    }

    var Bind = function(object, fun) {
    return function() {
    return fun.apply(object, arguments);
    }
    }

    var BindAsEventListener = function(object, fun) {
    var args = Array.prototype.slice.call(arguments).slice(2);
    return function(event) {
    return fun.apply(object, [event || window.event].concat(args));
    }
    }

    var CurrentStyle = function(element){
    return element.currentStyle || document.defaultView.getComputedStyle(element, null);
    }

    function addEventHandler(oTarget, sEventType, fnHandler) {
    if (oTarget.addEventListener) {
    oTarget.addEventListener(sEventType, fnHandler, false);
    } else if (oTarget.attachEvent) {
    oTarget.attachEvent("on" + sEventType, fnHandler);
    } else {
    oTarget["on" + sEventType] = fnHandler;
    }
    };

    function removeEventHandler(oTarget, sEventType, fnHandler) {
    if (oTarget.removeEventListener) {
    oTarget.removeEventListener(sEventType, fnHandler, false);
    } else if (oTarget.detachEvent) {
    oTarget.detachEvent("on" + sEventType, fnHandler);
    } else {
    oTarget["on" + sEventType] = null;
    }
    };

    //图片切割
    var ImgCropper = Class.create();
    ImgCropper.prototype = {
    //容器对象,控制层,图片地址
    initialize: function(container, handle, url, options) {
    this._Container = $(container);//容器对象
    this._layHandle = $(handle);//控制层
    this.Url = url;//图片地址

    this._layBase = this._Container.appendChild(document.createElement("img"));//底层
    this._layCropper = this._Container.appendChild(document.createElement("img"));//切割层
    this._layCropper.onload = Bind(this, this.SetPos);
    //用来设置大小
    this._tempImg = document.createElement("img");
    this._tempImg.onload = Bind(this, this.SetSize);

    this.SetOptions(options);

    this.Opacity = Math.round(this.options.Opacity);
    this.Color = this.options.Color;
    this.Scale = !!this.options.Scale;
    this.Ratio = Math.max(this.options.Ratio, 0);
    this.Width = Math.round(this.options.Width);
    this.Height = Math.round(this.options.Height);

    //设置预览对象
    var oPreview = $(this.options.Preview);//预览对象
    if(oPreview){
    oPreview.style.position = "relative";
    oPreview.style.overflow = "hidden";
    this.viewWidth = Math.round(this.options.viewWidth);
    this.viewHeight = Math.round(this.options.viewHeight);
    //预览图片对象
    this._view = oPreview.appendChild(document.createElement("img"));
    this._view.style.position = "absolute";
    this._view.onload = Bind(this, this.SetPreview);
    }
    //设置拖放
    this._drag = new Drag(this._layHandle, { Limit: true, onMove: Bind(this, this.SetPos), Transparent: true });
    //设置缩放
    this.Resize = !!this.options.Resize;
    if(this.Resize){
    var op = this.options, _resize = new Resize(this._layHandle, { Max: true, onResize: Bind(this, this.SetPos) });
    //设置缩放触发对象
    op.RightDown && (_resize.Set(op.RightDown, "right-down"));
    op.LeftDown && (_resize.Set(op.LeftDown, "left-down"));
    op.RightUp && (_resize.Set(op.RightUp, "right-up"));
    op.LeftUp && (_resize.Set(op.LeftUp, "left-up"));
    op.Right && (_resize.Set(op.Right, "right"));
    op.Left && (_resize.Set(op.Left, "left"));
    op.Down && (_resize.Set(op.Down, "down"));
    op.Up && (_resize.Set(op.Up, "up"));
    //最小范围限制
    this.Min = !!this.options.Min;
    this.minWidth = Math.round(this.options.minWidth);
    this.minHeight = Math.round(this.options.minHeight);
    //设置缩放对象
    this._resize = _resize;
    }
    //设置样式
    this._Container.style.position = "relative";
    this._Container.style.overflow = "hidden";
    this._layHandle.style.zIndex = 200;
    this._layCropper.style.zIndex = 100;
    this._layBase.style.position = this._layCropper.style.position = "absolute";
    this._layBase.style.top = this._layBase.style.left = this._layCropper.style.top = this._layCropper.style.left =
    0;//对齐
    //初始化设置
    this.Init();
    },
    //设置默认属性
    SetOptions: function(options) {
    this.options = {//默认值
    Opacity: 50,//透明度(0到100)
    Color: "",//背景色
    Width: 0,//图片高度
    Height: 0,//图片高度
    //缩放触发对象
    Resize: false,//是否设置缩放
    Right: "",//右边缩放对象
    Left: "",//左边缩放对象
    Up: "",//上边缩放对象
    Down: "",//下边缩放对象
    RightDown: "",//右下缩放对象
    LeftDown: "",//左下缩放对象
    RightUp: "",//右上缩放对象
    LeftUp: "",//左上缩放对象
    Min: false,//是否最小宽高限制(为true时下面min参数有用)
    minWidth: 50,//最小宽度
    minHeight: 50,//最小高度
    Scale: false,//是否按比例缩放
    Ratio: 0,//缩放比例(宽/高)
    //预览对象设置
    Preview: "",//预览对象
    viewWidth: 0,//预览宽度
    viewHeight: 0//预览高度
    };
    Extend(this.options, options || {});
    },
    //初始化对象
    Init: function() {
    //设置背景色
    this.Color && (this._Container.style.backgroundColor = this.Color);
    //设置图片
    this._tempImg.src = this._layBase.src = this._layCropper.src = this.Url;
    //设置透明
    if(isIE){
    this._layBase.style.filter = "alpha(opacity:" + this.Opacity + ")";
    } else {
    this._layBase.style.opacity = this.Opacity / 100;
    }
    //设置预览对象
    this._view && (this._view.src = this.Url);
    //设置缩放
    if(this.Resize){
    with(this._resize){
    Scale = this.Scale; Ratio = this.Ratio; Min = this.Min; minWidth = this.minWidth; minHeight = this.minHeight;
    }
    }
    },
    //设置切割样式
    SetPos: function() {
    //ie6渲染bug
    if(isIE6){ with(this._layHandle.style){ zoom = .9; zoom = 1; }; };
    //获取位置参数
    var p = this.GetPos();
    //按拖放对象的参数进行切割
    this._layCropper.style.clip = "rect(" + p.Top + "px " + (p.Left + p.Width) + "px " + (p.Top + p.Height) + "px "
    + p.Left + "px)";
    //设置预览
    this.SetPreview();
    },
    //设置预览效果
    SetPreview: function() {
    if(this._view){
    //预览显示的宽和高
    var p = this.GetPos(), s = this.GetSize(p.Width, p.Height, this.viewWidth, this.viewHeight), scale = s.Height /
    p.Height;
    //按比例设置参数
    var pHeight = this._layBase.height * scale, pWidth = this._layBase.width * scale, pTop = p.Top * scale, pLeft =
    p.Left * scale;
    //设置预览对象
    with(this._view.style){
    //设置样式
    width = pWidth + "px"; height = pHeight + "px"; top = - pTop + "px "; left = - pLeft + "px";
    //切割预览图
    clip = "rect(" + pTop + "px " + (pLeft + s.Width) + "px " + (pTop + s.Height) + "px " + pLeft + "px)";
    }
    }
    },
    //设置图片大小
    SetSize: function() {
    var s = this.GetSize(this._tempImg.width, this._tempImg.height, this.Width, this.Height);
    //设置底图和切割图
    this._layBase.style.width = this._layCropper.style.width = s.Width + "px";
    this._layBase.style.height = this._layCropper.style.height = s.Height + "px";
    //设置拖放范围
    this._drag.mxRight = s.Width; this._drag.mxBottom = s.Height;
    //设置缩放范围
    if(this.Resize){ this._resize.mxRight = s.Width; this._resize.mxBottom = s.Height; }
    },
    //获取当前样式
    GetPos: function() {
    with(this._layHandle){
    return { Top: offsetTop, Left: offsetLeft, Width: offsetWidth, Height: offsetHeight }
    }
    },
    //获取尺寸
    GetSize: function(nowWidth, nowHeight, fixWidth, fixHeight) {
    var iWidth = nowWidth, iHeight = nowHeight, scale = iWidth / iHeight;
    //按比例设置
    if(fixHeight){ iWidth = (iHeight = fixHeight) * scale; }
    if(fixWidth && (!fixHeight || iWidth > fixWidth)){ iHeight = (iWidth = fixWidth) / scale; }
    //返回尺寸对象
    return { Width: iWidth, Height: iHeight }
    }
    }
    </script>
    <%--drag--%>
    <script type="text/javascript">
    //�Ϸų���
    var Drag = Class.create();
    Drag.prototype = {
    //�ϷŶ���
    initialize: function(drag, options) {
    this.Drag = $(drag);//�ϷŶ���
    this._x = this._y = 0;//��¼�������ϷŶ����λ��
    this._marginLeft = this._marginTop = 0;//��¼margin
    //�¼�����(���ڰ��Ƴ��¼�)
    this._fM = BindAsEventListener(this, this.Move);
    this._fS = Bind(this, this.Stop);

    this.SetOptions(options);

    this.Limit = !!this.options.Limit;
    this.mxLeft = parseInt(this.options.mxLeft);
    this.mxRight = parseInt(this.options.mxRight);
    this.mxTop = parseInt(this.options.mxTop);
    this.mxBottom = parseInt(this.options.mxBottom);

    this.LockX = !!this.options.LockX;
    this.LockY = !!this.options.LockY;
    this.Lock = !!this.options.Lock;

    this.onStart = this.options.onStart;
    this.onMove = this.options.onMove;
    this.onStop = this.options.onStop;

    this._Handle = $(this.options.Handle) || this.Drag;
    this._mxContainer = $(this.options.mxContainer) || null;

    this.Drag.style.position = "absolute";
    //͸��
    if(isIE && !!this.options.Transparent){
    //����ϷŶ���
    with(this._Handle.appendChild(document.createElement("div")).style){
    width = height = "100%"; backgroundColor = "#fff"; filter = "alpha(opacity:0)"; fontSize = 0;
    }
    }
    //������Χ
    this.Repair();
    addEventHandler(this._Handle, "mousedown", BindAsEventListener(this, this.Start));
    },
    //����Ĭ������
    SetOptions: function(options) {
    this.options = {//Ĭ��ֵ
    Handle: "",//���ô������󣨲�������ʹ���ϷŶ���
    Limit: false,//�Ƿ����÷�Χ����(Ϊtrueʱ�����������,�����Ǹ���)
    mxLeft: 0,//�������
    mxRight: 9999,//�ұ�����
    mxTop: 0,//�ϱ�����
    mxBottom: 9999,//�±�����
    mxContainer: "",//ָ��������������
    LockX: false,//�Ƿ�����ˮƽ�����Ϸ�
    LockY: false,//�Ƿ�������ֱ�����Ϸ�
    Lock: false,//�Ƿ�����
    Transparent: false,//�Ƿ�͸��
    onStart: function(){},//��ʼ�ƶ�ʱִ��
    onMove: function(){},//�ƶ�ʱִ��
    onStop: function(){}//�����ƶ�ʱִ��
    };
    Extend(this.options, options || {});
    },
    //׼���϶�
    Start: function(oEvent) {
    if(this.Lock){ return; }
    this.Repair();
    //��¼�������ϷŶ����λ��
    this._x = oEvent.clientX - this.Drag.offsetLeft;
    this._y = oEvent.clientY - this.Drag.offsetTop;
    //��¼margin
    this._marginLeft = parseInt(CurrentStyle(this.Drag).marginLeft) || 0;
    this._marginTop = parseInt(CurrentStyle(this.Drag).marginTop) || 0;
    //mousemoveʱ�ƶ� mouseupʱֹͣ
    addEventHandler(document, "mousemove", this._fM);
    addEventHandler(document, "mouseup", this._fS);
    if(isIE){
    //���㶪ʧ
    addEventHandler(this._Handle, "losecapture", this._fS);
    //������겶��
    this._Handle.setCapture();
    }else{
    //���㶪ʧ
    addEventHandler(window, "blur", this._fS);
    //��ֹĬ�϶���
    oEvent.preventDefault();
    };
    //���ӳ���
    this.onStart();
    },
    //������Χ
    Repair: function() {
    if(this.Limit){
    //��������Χ����
    this.mxRight = Math.max(this.mxRight, this.mxLeft + this.Drag.offsetWidth);
    this.mxBottom = Math.max(this.mxBottom, this.mxTop + this.Drag.offsetHeight);
    //�����������������positionΪrelative��absolute����Ի���Զ�λ�����ڻ�ȡoffset֮ǰ����
    !this._mxContainer || CurrentStyle(this._mxContainer).position == "relative" ||
    CurrentStyle(this._mxContainer).position == "absolute" || (this._mxContainer.style.position = "relative");
    }
    },
    //�϶�
    Move: function(oEvent) {
    //�ж��Ƿ�����
    if(this.Lock){ this.Stop(); return; };
    //���ѡ��
    window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
    //�����ƶ�����
    var iLeft = oEvent.clientX - this._x, iTop = oEvent.clientY - this._y;
    //���÷�Χ����
    if(this.Limit){
    //���÷�Χ����
    var mxLeft = this.mxLeft, mxRight = this.mxRight, mxTop = this.mxTop, mxBottom = this.mxBottom;
    //�����������������������Χ����
    if(!!this._mxContainer){
    mxLeft = Math.max(mxLeft, 0);
    mxTop = Math.max(mxTop, 0);
    mxRight = Math.min(mxRight, this._mxContainer.clientWidth);
    mxBottom = Math.min(mxBottom, this._mxContainer.clientHeight);
    };
    //�����ƶ�����
    iLeft = Math.max(Math.min(iLeft, mxRight - this.Drag.offsetWidth), mxLeft);
    iTop = Math.max(Math.min(iTop, mxBottom - this.Drag.offsetHeight), mxTop);
    }
    //����λ�ã�������margin
    if(!this.LockX){ this.Drag.style.left = iLeft - this._marginLeft + "px"; }
    if(!this.LockY){ this.Drag.style.top = iTop - this._marginTop + "px"; }
    //���ӳ���
    this.onMove();
    },
    //ֹͣ�϶�
    Stop: function() {
    //�Ƴ��¼�
    removeEventHandler(document, "mousemove", this._fM);
    removeEventHandler(document, "mouseup", this._fS);
    if(isIE){
    removeEventHandler(this._Handle, "losecapture", this._fS);
    this._Handle.releaseCapture();
    }else{
    removeEventHandler(window, "blur", this._fS);
    };
    //���ӳ���
    this.onStop();
    }
    };
    </script>
    <%--resize--%>
    <script type="text/javascript">
    //���ų���
    var Resize = Class.create();
    Resize.prototype = {
    //���Ŷ���
    initialize: function(obj, options) {
    this._obj = $(obj);//���Ŷ���

    this._styleWidth = this._styleHeight = this._styleLeft = this._styleTop = 0;//��ʽ����
    this._sideRight = this._sideDown = this._sideLeft = this._sideUp = 0;//�������
    this._fixLeft = this._fixTop = 0;//��λ����
    this._scaleLeft = this._scaleTop = 0;//��λ����

    this._mxSet = function(){};//��Χ���ó���
    this._mxRightWidth = this._mxDownHeight = this._mxUpHeight = this._mxLeftWidth = 0;//��Χ����
    this._mxScaleWidth = this._mxScaleHeight = 0;//������Χ����

    this._fun = function(){};//����ִ�г���

    //��ȡ�߿���
    var _style = CurrentStyle(this._obj);
    this._borderX = (parseInt(_style.borderLeftWidth) || 0) + (parseInt(_style.borderRightWidth) || 0);
    this._borderY = (parseInt(_style.borderTopWidth) || 0) + (parseInt(_style.borderBottomWidth) || 0);
    //�¼�����(���ڰ��Ƴ��¼�)
    this._fR = BindAsEventListener(this, this.Resize);
    this._fS = Bind(this, this.Stop);

    this.SetOptions(options);
    //��Χ����
    this.Max = !!this.options.Max;
    this._mxContainer = $(this.options.mxContainer) || null;
    this.mxLeft = Math.round(this.options.mxLeft);
    this.mxRight = Math.round(this.options.mxRight);
    this.mxTop = Math.round(this.options.mxTop);
    this.mxBottom = Math.round(this.options.mxBottom);
    //�������
    this.Min = !!this.options.Min;
    this.minWidth = Math.round(this.options.minWidth);
    this.minHeight = Math.round(this.options.minHeight);
    //����������
    this.Scale = !!this.options.Scale;
    this.Ratio = Math.max(this.options.Ratio, 0);

    this.onResize = this.options.onResize;

    this._obj.style.position = "absolute";
    !this._mxContainer || CurrentStyle(this._mxContainer).position == "relative" || (this._mxContainer.style.position =
    "relative");
    },
    //����Ĭ������
    SetOptions: function(options) {
    this.options = {//Ĭ��ֵ
    Max: false,//�Ƿ����÷�Χ����(Ϊtrueʱ����mx��������)
    mxContainer:"",//ָ��������������
    mxLeft: 0,//�������
    mxRight: 9999,//�ұ�����
    mxTop: 0,//�ϱ�����
    mxBottom: 9999,//�±�����
    Min: false,//�Ƿ���С�������(Ϊtrueʱ����min��������)
    minWidth: 50,//��С���
    minHeight: 50,//��С�߶�
    Scale: false,//�Ƿ񰴱�������
    Ratio: 0,//���ű���(��/��)
    onResize: function(){}//����ʱִ��
    };
    Extend(this.options, options || {});
    },
    //���ô�������
    Set: function(resize, side) {
    var resize = $(resize), fun;
    if(!resize) return;
    //���ݷ�������
    switch (side.toLowerCase()) {
    case "up" :
    fun = this.Up;
    break;
    case "down" :
    fun = this.Down;
    break;
    case "left" :
    fun = this.Left;
    break;
    case "right" :
    fun = this.Right;
    break;
    case "left-up" :
    fun = this.LeftUp;
    break;
    case "right-up" :
    fun = this.RightUp;
    break;
    case "left-down" :
    fun = this.LeftDown;
    break;
    case "right-down" :
    default :
    fun = this.RightDown;
    };
    //���ô�������
    addEventHandler(resize, "mousedown", BindAsEventListener(this, this.Start, fun));
    },
    //׼������
    Start: function(e, fun, touch) {
    //��ֹð��(���Ϸ����ʱ����)
    e.stopPropagation ? e.stopPropagation() : (e.cancelBubble = true);
    //����ִ�г���
    this._fun = fun;
    //��ʽ����ֵ
    this._styleWidth = this._obj.clientWidth;
    this._styleHeight = this._obj.clientHeight;
    this._styleLeft = this._obj.offsetLeft;
    this._styleTop = this._obj.offsetTop;
    //�����߶�λ����
    this._sideLeft = e.clientX - this._styleWidth;
    this._sideRight = e.clientX + this._styleWidth;
    this._sideUp = e.clientY - this._styleHeight;
    this._sideDown = e.clientY + this._styleHeight;
    //top��left��λ����
    this._fixLeft = this._styleLeft + this._styleWidth;
    this._fixTop = this._styleTop + this._styleHeight;
    //���ű���
    if(this.Scale){
    //���ñ���
    this.Ratio = Math.max(this.Ratio, 0) || this._styleWidth / this._styleHeight;
    //left��top�Ķ�λ����
    this._scaleLeft = this._styleLeft + this._styleWidth / 2;
    this._scaleTop = this._styleTop + this._styleHeight / 2;
    };
    //��Χ����
    if(this.Max){
    //���÷�Χ����
    var mxLeft = this.mxLeft, mxRight = this.mxRight, mxTop = this.mxTop, mxBottom = this.mxBottom;
    //�����������������������Χ����
    if(!!this._mxContainer){
    mxLeft = Math.max(mxLeft, 0);
    mxTop = Math.max(mxTop, 0);
    mxRight = Math.min(mxRight, this._mxContainer.clientWidth);
    mxBottom = Math.min(mxBottom, this._mxContainer.clientHeight);
    };
    //������Сֵ������
    mxRight = Math.max(mxRight, mxLeft + (this.Min ? this.minWidth : 0) + this._borderX);
    mxBottom = Math.max(mxBottom, mxTop + (this.Min ? this.minHeight : 0) + this._borderY);
    //����ת��ʱҪ������������д��function��ʽ
    this._mxSet = function(){
    this._mxRightWidth = mxRight - this._styleLeft - this._borderX;
    this._mxDownHeight = mxBottom - this._styleTop - this._borderY;
    this._mxUpHeight = Math.max(this._fixTop - mxTop, this.Min ? this.minHeight : 0);
    this._mxLeftWidth = Math.max(this._fixLeft - mxLeft, this.Min ? this.minWidth : 0);
    };
    this._mxSet();
    //�����ű����µķ�Χ����
    if(this.Scale){
    this._mxScaleWidth = Math.min(this._scaleLeft - mxLeft, mxRight - this._scaleLeft - this._borderX) * 2;
    this._mxScaleHeight = Math.min(this._scaleTop - mxTop, mxBottom - this._scaleTop - this._borderY) * 2;
    };
    };
    //mousemoveʱ���� mouseupʱֹͣ
    addEventHandler(document, "mousemove", this._fR);
    addEventHandler(document, "mouseup", this._fS);
    if(isIE){
    addEventHandler(this._obj, "losecapture", this._fS);
    this._obj.setCapture();
    }else{
    addEventHandler(window, "blur", this._fS);
    e.preventDefault();
    };
    },
    //����
    Resize: function(e) {
    //���ѡ��
    window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
    //ִ�����ų���
    this._fun(e);
    //������ʽ������������ڵ���0����ie����
    with(this._obj.style){
    width = this._styleWidth + "px"; height = this._styleHeight + "px";
    top = this._styleTop + "px"; left = this._styleLeft + "px";
    }
    //���ӳ���
    this.onResize();
    },
    //���ų���
    //��
    Up: function(e) {
    this.RepairY(this._sideDown - e.clientY, this._mxUpHeight);
    this.RepairTop();
    this.TurnDown(this.Down);
    },
    //��
    Down: function(e) {
    this.RepairY(e.clientY - this._sideUp, this._mxDownHeight);
    this.TurnUp(this.Up);
    },
    //��
    Right: function(e) {
    this.RepairX(e.clientX - this._sideLeft, this._mxRightWidth);
    this.TurnLeft(this.Left);
    },
    //��
    Left: function(e) {
    this.RepairX(this._sideRight - e.clientX, this._mxLeftWidth);
    this.RepairLeft();
    this.TurnRight(this.Right);
    },
    //����
    RightDown: function(e) {
    this.RepairAngle(
    e.clientX - this._sideLeft, this._mxRightWidth,
    e.clientY - this._sideUp, this._mxDownHeight
    );
    this.TurnLeft(this.LeftDown) || this.Scale || this.TurnUp(this.RightUp);
    },
    //����
    RightUp: function(e) {
    this.RepairAngle(
    e.clientX - this._sideLeft, this._mxRightWidth,
    this._sideDown - e.clientY, this._mxUpHeight
    );
    this.RepairTop();
    this.TurnLeft(this.LeftUp) || this.Scale || this.TurnDown(this.RightDown);
    },
    //����
    LeftDown: function(e) {
    this.RepairAngle(
    this._sideRight - e.clientX, this._mxLeftWidth,
    e.clientY - this._sideUp, this._mxDownHeight
    );
    this.RepairLeft();
    this.TurnRight(this.RightDown) || this.Scale || this.TurnUp(this.LeftUp);
    },
    //����
    LeftUp: function(e) {
    this.RepairAngle(
    this._sideRight - e.clientX, this._mxLeftWidth,
    this._sideDown - e.clientY, this._mxUpHeight
    );
    this.RepairTop(); this.RepairLeft();
    this.TurnRight(this.RightUp) || this.Scale || this.TurnDown(this.LeftDown);
    },
    //��������
    //ˮƽ����
    RepairX: function(iWidth, mxWidth) {
    iWidth = this.RepairWidth(iWidth, mxWidth);
    if(this.Scale){
    var iHeight = this.RepairScaleHeight(iWidth);
    if(this.Max && iHeight > this._mxScaleHeight){
    iHeight = this._mxScaleHeight;
    iWidth = this.RepairScaleWidth(iHeight);
    }else if(this.Min && iHeight < this.minHeight){
    var tWidth = this.RepairScaleWidth(this.minHeight);
    if(tWidth < mxWidth){ iHeight = this.minHeight; iWidth = tWidth; }
    }
    this._styleHeight = iHeight;
    this._styleTop = this._scaleTop - iHeight / 2;
    }
    this._styleWidth = iWidth;
    },
    //��ֱ����
    RepairY: function(iHeight, mxHeight) {
    iHeight = this.RepairHeight(iHeight, mxHeight);
    if(this.Scale){
    var iWidth = this.RepairScaleWidth(iHeight);
    if(this.Max && iWidth > this._mxScaleWidth){
    iWidth = this._mxScaleWidth;
    iHeight = this.RepairScaleHeight(iWidth);
    }else if(this.Min && iWidth < this.minWidth){
    var tHeight = this.RepairScaleHeight(this.minWidth);
    if(tHeight < mxHeight){ iWidth = this.minWidth; iHeight = tHeight; }
    }
    this._styleWidth = iWidth;
    this._styleLeft = this._scaleLeft - iWidth / 2;
    }
    this._styleHeight = iHeight;
    },
    //�ԽǷ���
    RepairAngle: function(iWidth, mxWidth, iHeight, mxHeight) {
    iWidth = this.RepairWidth(iWidth, mxWidth);
    if(this.Scale){
    iHeight = this.RepairScaleHeight(iWidth);
    if(this.Max && iHeight > mxHeight){
    iHeight = mxHeight;
    iWidth = this.RepairScaleWidth(iHeight);
    }else if(this.Min && iHeight < this.minHeight){
    var tWidth = this.RepairScaleWidth(this.minHeight);
    if(tWidth < mxWidth){ iHeight = this.minHeight; iWidth = tWidth; }
    }
    }else{
    iHeight = this.RepairHeight(iHeight, mxHeight);
    }
    this._styleWidth = iWidth;
    this._styleHeight = iHeight;
    },
    //top
    RepairTop: function() {
    this._styleTop = this._fixTop - this._styleHeight;
    },
    //left
    RepairLeft: function() {
    this._styleLeft = this._fixLeft - this._styleWidth;
    },
    //height
    RepairHeight: function(iHeight, mxHeight) {
    iHeight = Math.min(this.Max ? mxHeight : iHeight, iHeight);
    iHeight = Math.max(this.Min ? this.minHeight : iHeight, iHeight, 0);
    return iHeight;
    },
    //width
    RepairWidth: function(iWidth, mxWidth) {
    iWidth = Math.min(this.Max ? mxWidth : iWidth, iWidth);
    iWidth = Math.max(this.Min ? this.minWidth : iWidth, iWidth, 0);
    return iWidth;
    },
    //�����߶�
    RepairScaleHeight: function(iWidth) {
    return Math.max(Math.round((iWidth + this._borderX) / this.Ratio - this._borderY), 0);
    },
    //�������
    RepairScaleWidth: function(iHeight) {
    return Math.max(Math.round((iHeight + this._borderY) * this.Ratio - this._borderX), 0);
    },
    //ת�����
    //ת��
    TurnRight: function(fun) {
    if(!(this.Min || this._styleWidth)){
    this._fun = fun;
    this._sideLeft = this._sideRight;
    this.Max && this._mxSet();
    return true;
    }
    },
    //ת��
    TurnLeft: function(fun) {
    if(!(this.Min || this._styleWidth)){
    this._fun = fun;
    this._sideRight = this._sideLeft;
    this._fixLeft = this._styleLeft;
    this.Max && this._mxSet();
    return true;
    }
    },
    //ת��
    TurnUp: function(fun) {
    if(!(this.Min || this._styleHeight)){
    this._fun = fun;
    this._sideDown = this._sideUp;
    this._fixTop = this._styleTop;
    this.Max && this._mxSet();
    return true;
    }
    },
    //ת��
    TurnDown: function(fun) {
    if(!(this.Min || this._styleHeight)){
    this._fun = fun;
    this._sideUp = this._sideDown;
    this.Max && this._mxSet();
    return true;
    }
    },
    //ֹͣ����
    Stop: function() {
    removeEventHandler(document, "mousemove", this._fR);
    removeEventHandler(document, "mouseup", this._fS);
    if(isIE){
    removeEventHandler(this._obj, "losecapture", this._fS);
    this._obj.releaseCapture();
    }else{
    removeEventHandler(window, "blur", this._fS);
    }
    }
    };
    </script>
    <style type="text/css">
    #rRightDown,#rLeftDown,#rLeftUp,#rRightUp,#rRight,#rLeft,#rUp,#rDown{
    position:absolute;
    background:#FFF;
    border: 1px solid #333;
    width: 6px;
    height: 6px;
    z-index:500;
    font-size:0;
    opacity: 0.5;
    filter:alpha(opacity=50);
    }

    #rLeftDown,#rRightUp{cursor:ne-resize;}
    #rRightDown,#rLeftUp{cursor:nw-resize;}
    #rRight,#rLeft{cursor:e-resize;}
    #rUp,#rDown{cursor:n-resize;}

    #rLeftDown{left:-4px;bottom:-4px;}
    #rRightUp{right:-4px;top:-4px;}
    #rRightDown{right:-4px;bottom:-4px;background-color:#00F;}
    #rLeftUp{left:-4px;top:-4px;}
    #rRight{right:-4px;top:50%;margin-top:-4px;}
    #rLeft{left:-4px;top:50%;margin-top:-4px;}
    #rUp{top:-4px;left:50%;margin-left:-4px;}
    #rDown{bottom:-4px;left:50%;margin-left:-4px;}

    #bgDiv{width:1000px; height:500px; border:1px solid #666666; position:relative;}
    #dragDiv{border:1px dashed #fff; width:100px; height:60px; top:50px; left:50px; cursor:move; }
    </style>
    <table width="1000" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td width="1000"><div id="bgDiv">
    <div id="dragDiv">
    <div id="rRightDown"> </div>
    <div id="rLeftDown"> </div>
    <div id="rRightUp"> </div>
    <div id="rLeftUp"> </div>
    <div id="rRight"> </div>
    <div id="rLeft"> </div>
    <div id="rUp"> </div>
    <div id="rDown"></div>
    </div>
    </div></td>
    <td align="center"><div id="viewDiv" style="width:300px; height:150px;border:1px solid">
    </div></td>
    </tr>
    </table>
    <a id="save" href="javascript:void(0)" onclick="save()" style="border: 1px solid #06c;color: #fff;background: #06c;padding: 10px 15px;margin-top: 24px;display: block;width: 200px;text-align: center; margin-left: 390px;border-radius: 3px;">确定截图</a>
    <a href="${ly }/company/safety-system/control-list2?flag=2" data-title="截图"
    href="javascript:void(0)" style="display: none" id="tiaozhuan">跳转</a>
      <script type="text/javascript" src="${ly}/js/html2canvas.min.js"></script>
    <script type="text/javascript">
    var ic = new ImgCropper("bgDiv", "dragDiv",
    localStorage.getItem('basic'),
    {
    Width: 1000, Height: 500, Color: "#000",
    Resize: true,
    Right: "rRight", Left: "rLeft", Up: "rUp", Down: "rDown",
    RightDown: "rRightDown", LeftDown: "rLeftDown", RightUp: "rRightUp", LeftUp: "rLeftUp",
    Preview: "viewDiv", viewWidth: 300, viewHeight: 300
    })

    function save(){
    if($("viewDiv").children[0]){

      html2canvas($("viewDiv"),{
      useCORS:true,allowTaint: false,foreignObjectRendering: true,taintTest: true,scale:1 }).then(canvas => {
      convertCanvasToImage(canvas);
      });
      function convertCanvasToImage(canvas) {
      var image = canvas.toDataURL("image/png", 1);
      console.log(image)
      localStorage.setItem('basicI', image);
      localStorage.setItem('basicType', '1');
      localStorage.setItem('areaType', 2);
      document.querySelector("#tiaozhuan").click();
      <%--localStorage.setItem('basic', '');--%>
      <%--localStorage.setItem('basic', image);--%>
      <%--document.querySelector("#tiaozhuan").click();--%>
      }
    }
    }
    </script>
    </body>
    </html>
