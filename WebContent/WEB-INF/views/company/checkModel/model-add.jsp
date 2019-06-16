<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <%@ include file="/WEB-INF/inc/back-header.inc" %>
    <title>风险分级管控 隐患排查治理智能化平台</title>
    <meta name="keywords" content="风险分级管控 隐患排查治理智能化平台">
    <meta name="description" content="风险分级管控 隐患排查治理智能化平台">
    <script src="https://unpkg.com/react@16/umd/react.production.min.js" crossorigin></script>
    <script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js" crossorigin></script>
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
    <style type="text/css">
        .input-text {
            position: relative
        }

        .txtarea_sq {
            height: 100px;
            width: 350px;
        }

        .div_model {
            float: left;
            width: 80%;
            margin-left: 10%
        }

        body .form .row {
            margin-top: 20px;
        }

        .radio-lx {
            display: inline-block;
            cursor: pointer;
            height: 50px;
            font-size: 14px;
            line-height: 50px;
            margin-bottom: 10px;
            padding: 0 35px;
            margin-right: 30px;
            background: #f5f5f5;
            border-radius: 5px;
        }

        .radio-lxxz {
            background: #D9EDF7;
            color: #31708F
        }

        .my_icon {
            font-size: 16px;
            margin-right: 5px;
            cursor: pointer;
            margin-left: 5px;
            position: relative;
            top:2px;
            font-weight: bold;
        }

        .my_left{
            margin-left: 12%;
        }

    </style>
    <script>
       var flag= '${flag}';


    </script>


    <div id="app"></div>


    <script type="text/babel">

        class AddModel extends React.Component {
            constructor() {
                super();
                this.state = {
                    tableName: '',
                    checkType: null,  //检查方式 1:日常  2:定期  3:季节  4:其他 5:综合
                    days: null,    //   定期检查天数
                    leixin: [{id: -1, name: '基础'}, {id: -2, name: '现场'}],  //可选的检查类型
                    checkedLeixin: null,   //以选择的检查类型
                    bm: [],                //可选部门数组
                    bmId: null,     //  以选择的部门id
                    bmName: null,   //  已选择部门名字
                    // gw: [],         //可选岗位数组
                    // gwId: null,     //  以选择的岗位id
                    // gwName: null,   //  已选择岗位名字
                    list: [],        //  风险项数组
                    myChecks: [],     //自定义检查项数组
                    current: null,    //当前打开的检查项index
                }
                this.host = "https://sec.dicarl.com";
                this.flag = flag;
            }

            componentWillMount() {

                this.getBasic();
            }

            componentDidMount = () => {

            }

            componentDidUpdate = () => {

            }


            getBasic = () => {   //获取企业项
                const data = {};
                fetch(`${host}/village/addCheckModel2`, {
                    method: 'POST',
                    body: data,
                    headers: {
                        'content-type': 'application/json'
                    }
                })
                    .then(res => res.json())
                    .then(json => {
					var leixin = this.state.leixin;
					if(json.danger!=null){
						 leixin = this.state.leixin.concat(json.danger);
					}
                     
                        let bm = json.map;
                        let arr = [];
                        for (let key in bm) {
                            let item = {
                                name: key,
                                id: bm[key],
                            }
                            arr.push(item)
                        }
                        bm = arr;
                        this.setState({
                            leixin,
                            bm
                        })

                    }).catch(error => {
                    console.log(error);
                    /*alert('网络错误')*/
                })
            }

            inputChange = (key, e) => {        //获取input框的值
                this.setState({
                    [key]: e.target.value
                })
            }

            addInputChange = (key, index, e) => {
                const val = e.target.value;
                let checks = this.state.myChecks;
                checks[index][key] = val;
                this.setState({
                    myChecks: checks
                })

            }

            selectChange = (key, e) => {    //获取select框的值
                const val = e.target.value;
                if (key == 'checkType') {   //如果改变的是checkType的值把days变成null
                    this.setState({
                        [key]: val,
                        days: null
                    })
                } else {
                    this.setState({
                        [key]: val
                    })
                }
            }

            LeixinSelectChange = (e) => {
                const val = e.target.value;
                if(val==0){
                    this.setState({
                        checkedLeixin: val,
                        bm:[],           //部门数组
                        bmId: 0,     //  以选择的部门id
                        bmName: null,   //  已选择部门名字
                        list:[],
                        myChecks: [],
                    })
                    return
                }
                const _self = this;
                this.setState({
                    checkedLeixin: val,
                    bmId: 0,     //  以选择的部门id
                    bmName: null,   //  已选择部门名字
                    list:[],
                    myChecks: [],
                }, () => {
                    if (val != -2&&val !=-1) {   //如果选择的是高危
                        let postData = {"type": parseInt(val)};
                        $.ajax({
                            type: "POST",
                            url: `${host}/village/findGaoWeiAndJiChu`,
                            data: postData,
                            async: false,
                            dataType: "json",
                            success: function (result) {
                                if (result.length == 0) {
                                    alert('没有可检查风险项,请选择自定义添加项')
                                }
                                _self.setState({
                                    list: result,
                                    myChecks: [],
                                })
                            },
                            complete: function (XMLHttpRequest, textStatus) {
                                // layer.close(index);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                console.log("查询失败");
                            }

                        })
                    }else{   //如果选择的是基础或现场
                        let postData = {"checkType": parseInt(val)};
                        <%--$.ajax({--%>      //旧版是请求有哪些车间  新版直接请求检查项
                            <%--type: "POST",--%>
                            <%--url: `${host}/village/findItemAll`,--%>
                            <%--data: postData,--%>
                            <%--async: false,--%>
                            <%--dataType: "json",--%>
                            <%--success: function (result) {--%>
                                <%--if (result.length == 0) {--%>
                                    <%--alert('没有可检查部门,请先添加风险点')--%>
                                <%--}--%>
                                <%--_self.setState({--%>
                                    <%--bm: result,--%>
                                    <%--myChecks: [],--%>
                                <%--})--%>
                            <%--},--%>
                            <%--complete: function (XMLHttpRequest, textStatus) {--%>
                                <%--// layer.close(index);--%>
                            <%--},--%>
                            <%--error: function (XMLHttpRequest, textStatus, errorThrown) {--%>
                                <%--console.log("查询失败");--%>
                            <%--}--%>
                        <%--})--%>
                        $.ajax({
                            type: "POST",
                            url: `${host}/village/selectDep3`,
                            data: postData,
                            async: false,
                            dataType: "json",
                            success: function (result) {
                                if (result.length == 0) {
                                    alert('没有可检查风险项,请选择自定义添加项')
                                }
                                _self.setState({
                                    list: result,
                                    myChecks: [],
                                })
                            },
                            complete: function (XMLHttpRequest, textStatus) {
                                // layer.close(index);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                console.log("查询失败");
                            }

                        })
                    }
                })
            }


            bmSelect = (e) => {      //部门选择器方法
                const val = e.target.value;
                const _self = this;
                let name = '';
                if(val==0){
                    this.setState({
                        bmId: val,
                        bmName: null,
                        current:null,
                        list:[],
                    })
                    return;
                }
                for (let key in this.state.bm) {
                    if (this.state.bm[key].dmid == val) {
                        name = this.state.bm[key].level1
                        break;
                    }
                }
                this.setState({
                    bmId: val,
                    bmName: name,
					current:null
                })
                $.ajax({
                    type: "POST",
                    url: `${host}/village/selectDep`,
                    data: {"depId": val,'dpName':name,'checkType':parseInt(this.state.checkedLeixin)},
                    async: false,
                    dataType: "json",
                    success: function (result) {
                        console.log(result);
                        if (result.length == 0) {
                            alert('没有可选择的检查项,请自定义检查')
                        }
                        _self.setState({
                            list: result,
                            myChecks: [],
                        })
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        // layer.close(index);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("查询失败");
                    }
                });
            }


            <%--gwSelect = (e) => {   // 岗位选择器方法--%>
            <%--    const val = e.target.value;--%>
            <%--    const _self = this;--%>
            <%--    let name = '';--%>
            <%--    for (let key in this.state.gw) {--%>
            <%--        if (this.state.gw[key].id == val) {--%>
            <%--            name = this.state.gw[key].name--%>
            <%--            break;--%>
            <%--        }--%>
            <%--    }--%>
            <%--    this.setState({--%>
            <%--        gwId: val,--%>
            <%--        gwName: name,--%>
            <%--    })--%>
            <%--    $.ajax({--%>
            <%--        type: "POST",--%>
            <%--        url: `${host}/village/findInspection`,--%>
            <%--        data: {depId: _self.state.bmId, sName: name},--%>
            <%--        async: false,--%>
            <%--        dataType: "json",--%>
            <%--        success: function (result) {--%>
            <%--            if (result.length == 0) {--%>
            <%--                alert('该岗位下没有可检查风险项,请选择别的岗位')--%>
            <%--            }--%>
            <%--            _self.setState({--%>
            <%--                list: result,--%>
            <%--                myChecks: [],--%>
            <%--            })--%>
            <%--        },--%>
            <%--        complete: function (XMLHttpRequest, textStatus) {--%>
            <%--            // layer.close(index);--%>
            <%--        },--%>
            <%--        error: function (XMLHttpRequest, textStatus, errorThrown) {--%>
            <%--            console.log("查询失败");--%>
            <%--        }--%>
            <%--    });--%>
            <%--}--%>

            addCheck = () => {
                if(this.state.checkedLeixin==-2&&!this.state.bmId){
                     alert('请先选择检查部位')
                    return;
                }
                let obj = {title: '', value: ''};
                let checks = this.state.myChecks;
                checks.push(obj);
                this.setState({
                    myChecks: checks
                })

            }


            deleteCheck = (index) => {
                let checks = this.state.myChecks;
                checks.splice(index, 1)
                this.setState({
                    myChecks: checks
                })

            }

            checkBox = (index1, index2) => {    //复选框方法
                let data = this.state.list;
                if (data[index1].list[index2].checked == 1) {
                    data[index1].list[index2].checked = 0;
                } else {
                    data[index1].list[index2].checked = 1;
                }
                this.setState({
                    list: data
                })
            }


            renderLevel4 = (arr, index1) => {
                let html = [];
                if (this.state.checkedLeixin == -2||this.state.checkedLeixin == -1) {
                    arr.map((item, index2) => {
                        html.push(
                            <div className="row" key={index2} style={index2==0?{marginTop:'10px'}:{}}>
                                <input type="checkbox"
                                       checked={this.state.list[index1].list[index2].checked == 1 ? true : false}
                                       style={{marginTop: '0px', marginRight: '5px'}} onClick={() => {
                                    this.checkBox(index1, index2)
                                }}/>
                                <label>{item.measures}</label>
                            </div>
                        )
                    })
                } else {
                    arr.map((item, index2) => {
                        html.push(
                            <div className="row" key={index2} style={index2==0?{marginTop:'10px'}:{}}>
                                <input type="checkbox"
                                       checked={this.state.list[index1].list[index2].checked == 1 ? true : false}
                                       style={{marginTop: '0px', marginRight: '5px'}} onClick={() => {
                                    this.checkBox(index1, index2)
                                }}/>
                                <label>{item.level2}</label>
                            </div>
                        )
                    })
                }
                return html;
            }


            save = () => {
                let state = this.state;
                // if (!state.tableName) {    //验证检查表名字是否填写
                //     alert('检查表名字必须填写');
                //     return
                // }

                if (state.checkType == 0 || state.checkType == null) {    //验证检查类型是否选择
                    alert('请选择检查类型');
                    return
                }
                if (state.checkType == 2 && state.days == null) {    //验证周期天数是否填写
                    alert('填写周期天数,只能输入数字');
                    return
                }
                if (state.checkType == 2 && state.days == 0) {    //验证周期天数是否填写
                    alert('周期天数必须大于1');
                    return
                }
                if (state.list.length == 0 && state.myChecks.length == 0) {    //验证是否有检查项
                    alert('没有选择检查项');
                    return
                }

                let postData = {
                    template: state.tableName,     //表名字
                    title: state.checkType,        //检查方式 1:日常  2:定期  3:临时
                    checkType: state.checkedLeixin, //檢查類型 -1基础 -2现场   其他高危
                    cycle: parseInt(state.days),   //检查周期天数
                    flag:this.flag,                //1:企业自查2:行政检查3:部门抽查
                    checkLevels: []                 //检查项
                }
                let isXc = state.checkedLeixin == -2||state.checkedLeixin == -1 ? true : false;  //是否为现场和基础检查
                state.list.map((item) => {
                    item.list.map((item2) => {
                        if (item2.checked == 1) {
                            let checkItem = {};
                            if (isXc) {
                                checkItem.id = item2.id;
                                checkItem.level1 = item2.level1;
                                checkItem.level2 = item2.level2;
                                checkItem.level3 = item2.level3;
                                checkItem.level4 = item2.measures;
                                checkItem.reference = item2.reference;
                                checkItem.factors = item2.factors;
                                checkItem.types = item2.types;
                                checkItem.gkcs = item2.gkcs;
                                checkItem.gkzt = item2.gkzt;
                                checkItem.checkType = state.checkedLeixin;
                                industryId:null
                            } else {
                                checkItem.id = null;
                                checkItem.level1 = item.name;
                                checkItem.level2 = item2.level2;
                                checkItem.level3 = null;
                                checkItem.level4 = null;
                                checkItem.reference = null;
                                checkItem.factors = null;
                                checkItem.types = null;
                                checkItem.gkcs = null;
                                checkItem.gkzt = null;
                                checkItem.checkType = state.checkedLeixin;
                                industryId:item2.industryId;
                            }

                            postData.checkLevels.push(checkItem);
                        }
                    })
                })
                state.myChecks.map((item) => {
                    if (item.title != '' && item.value != '') {
                        let checkItem = {};
                        if (isXc) {
                            checkItem.id = null;
                            checkItem.level1 = state.bmName;
                            checkItem.level2 = null;
                            checkItem.level3 = item.title;
                            checkItem.level4 = item.value;
                            checkItem.reference = null;
                            checkItem.factors = null;
                            checkItem.types = null;
                            checkItem.gkcs = null;
                            checkItem.gkzt = null;
                            checkItem.checkType = state.checkedLeixin;
                            industryId:null
                        } else {
                            checkItem.id = null;
                            checkItem.level1 = item.title;
                            checkItem.level2 = item.value;
                            checkItem.level3 = null;
                            checkItem.level4 = null;
                            checkItem.reference = null;
                            checkItem.factors = null;
                            checkItem.types = null;
                            checkItem.gkcs = null;
                            checkItem.gkzt = null;
                            checkItem.checkType = state.checkedLeixin;
                            industryId:null;
                        }

                        postData.checkLevels.push(checkItem);
                    }
                })
                if (postData.checkLevels.length == 0) {
                    alert('没有选择检查项');
                    return
                }
                const my_flag = this.flag
                $.ajax({
                    type: "POST",
                    url: getRootPath() + '/village/saveCheckMenu2',
                    data: JSON.stringify(postData),
                    async: false,
                    contentType: "application/json",
                    dataType: "json",
                    success: function (result) {
                        if (result.status == 0) {
                            alert('保存成功');
                            window.parent.location.href='${ly}/company/model-list-cx?flag='+my_flag+'&type=1&template=2';
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);

                        } else {
                            alert('保存失败');
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        // layer.close(index);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("添加失败")
                    }
                });
            }

            zhankai = (index) => {
                if (index == this.state.current) {
                    this.setState({
                        current: null
                    })
                } else {
                    this.setState({
                        current: index
                    })
                }


            }

            render = () => {
                const xcjc =      //如果是现场基础检查 渲染这个
                    <div>
                        <%--<div className="row cl">--%>
                            <%--<label className="form-label col-xs-4 col-sm-2"><span className="c-red">*</span>请选择检查部位--%>
                                <%--:</label>--%>
                            <%--<div className="formControls col-xs-8 col-sm-9">--%>
                                    <%--<span className="select-box inline">--%>
                                        <%--<select className="select" style={{width: '200px'}}--%>
                                                <%--onChange={this.bmSelect} value={this.state.bmId}>--%>
                                             <%--<option value="0">请选择检查部位</option>--%>
                                            <%--{this.state.bm.map((item, index) => {--%>
                                                <%--return (--%>
                                                    <%--<option value={item.dmid}>{item.level1}</option>--%>
                                                <%--)--%>
                                            <%--})}--%>
                                        <%--</select>--%>
                                    <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--{this.state.gw.length > 0 ?   //如果岗位长度null--%>
                        <%--    <div className="row cl">--%>
                        <%--        <label className="form-label col-xs-4 col-sm-2"><span className="c-red">*</span>请选择检查岗位--%>
                        <%--            :</label>--%>
                        <%--        <div className="formControls col-xs-8 col-sm-9">--%>
                        <%--            <span className="select-box inline">--%>
                        <%--                <select className="select" style={{width: '200px'}}--%>
                        <%--                        onChange={this.gwSelect}>--%>
                        <%--                     <option value="0">请选择检查岗位</option>--%>
                        <%--                    {this.state.gw.map((item, index) => {--%>
                        <%--                        return (--%>
                        <%--                            <option value={item.id}>{item.name}</option>--%>
                        <%--                        )--%>
                        <%--                    })}--%>
                        <%--                </select>--%>
                        <%--            </span>--%>
                        <%--        </div>--%>
                        <%--    </div> : null}--%>

                        {
                            this.state.list.map((item, index) => {
                                if (item.list.length > 0) {
                                    return (
                                    <div key={index}>
                                        <div className="row cl" >
                                            <label className="form-label col-lg-7 col-xs-7 col-sm-7 col-offset-1" style={{textAlign:'left'}}>
                                                {this.state.current == index ?
                                                    <i class="Hui-iconfont my_icon" onClick={() => {
                                                        this.zhankai(index)
                                                    }}></i> : <i class="Hui-iconfont my_icon" onClick={() => {
                                                        this.zhankai(index)
                                                    }}></i>}
                                                <span className="c-red">*</span>{item.name}
                                            </label>
                                        </div>
                                       {this.state.current == index ?
                                         <div className="row cl"  style={{marginTop:'0px'}}>
                                            <div className="formControls col-lg-7 col-xs-7 col-sm-7 my_left">
                                                {this.renderLevel4(item.list, index)}
                                            </div>
                                         </div>
                                        :null}
                                    </div>

                                    )
                                }
                            })
                        }

                        {this.state.myChecks.map((item, index) => {
                            return (
                                <div>

                                    <div className="row cl">
                                        <label
                                            className="form-label col-xs-4 col-sm-2">自定义检查项目{index + 1}：</label>
                                        <div className="formControls col-xs-5 col-sm-7"
                                             style={{position: 'relative'}}>
                                            <input type="text"
                                                   onChange={(e) => this.addInputChange('title', index, e)}
                                                   style={{width: '350px'}} className="input-text"
                                                   maxLength="50" placeholder="请填写检查项目(必填)"
                                                   value={this.state.myChecks[index].title}/>

                                            <i class="Hui-iconfont"
                                             style={{position: 'absolute', left: '380px',fontSize:'20px',fontWeight:'bold', cursor:'pointer'}}
                                               onClick={() => {
                                                   this.deleteCheck(index)
                                               }}></i>
                                        </div>


                                    </div>
                                    <div className="row cl">
                                        <label
                                            className="form-label col-xs-4 col-sm-2">自定义检查内容{index + 1}：</label>
                                        <div className="formControls col-xs-5 col-sm-7">
                                            <input type="text"
                                                   onChange={(e) => this.addInputChange('value', index, e)}
                                                   style={{width: '350px'}} className="input-text"
                                                   maxLength="50" placeholder="请填写检查内容(必填)"
                                                   value={this.state.myChecks[index].value}/>
                                        </div>
                                    </div>

                                </div>

                            )

                        })}
                        {this.state.bmId?
                          <div className="row cl">
                            <div className="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-30">
                                <button className="btn btn-primary radius" type="button"
                                        onClick={this.addCheck}>
                                   新增自定义检查项
                                </button>
                            </div>
                        </div>:null
                        }



                    </div>


                const gw =         //如果是高危 渲染这个
                    <div>
                        {
                            this.state.list.map((item, index) => {
                                if (item.list.length > 0) {
                                    return (
                                        <div key={index}>
                                            <div className="row cl" >
                                                <label className="form-label col-lg-7 col-xs-7 col-sm-7 col-offset-1" style={{textAlign:'left'}}>
                                                    {this.state.current == index ?
                                                        <i class="Hui-iconfont my_icon" onClick={() => {
                                                            this.zhankai(index)
                                                        }}></i> : <i class="Hui-iconfont my_icon" onClick={() => {
                                                            this.zhankai(index)
                                                        }}></i>}
                                                    <span className="c-red">*</span>{item.name}
                                                </label>
                                            </div>
                                            {this.state.current == index ?
                                                <div className="row cl"  style={{marginTop:'0px'}}>
                                                    <div className="formControls col-lg-7 col-xs-7 col-sm-7 my_left">
                                                        {this.renderLevel4(item.list, index)}
                                                    </div>
                                                </div>
                                                :null}
                                        </div>

                                    )
                                }
                            })
                        }


                        {this.state.myChecks.map((item, index) => {
                            return (
                                <div>

                                    <div className="row cl">
                                        <label
                                            className="form-label col-xs-4 col-sm-2">自定义检查项目{index + 1}：</label>
                                        <div className="formControls col-xs-5 col-sm-7"
                                             style={{position: 'relative'}}>
                                            <input type="text"
                                                   onChange={(e) => this.addInputChange('title', index, e)}
                                                   style={{width: '350px'}} className="input-text"
                                                   maxLength="50" placeholder="请填写检查项目(必填)"
                                                   value={this.state.myChecks[index].title}/>

                                            <i class="Hui-iconfont"
                                               style={{position: 'absolute', left: '380px',fontSize:'20px',fontWeight:'bold', cursor:'pointer'}}
                                               onClick={() => {
                                                   this.deleteCheck(index)
                                               }}></i>
                                        </div>


                                    </div>
                                    <div className="row cl">
                                        <label
                                            className="form-label col-xs-4 col-sm-2">自定义检查内容{index + 1}：</label>
                                        <div className="formControls col-xs-5 col-sm-7">
                                            <input type="text"
                                                   onChange={(e) => this.addInputChange('value', index, e)}
                                                   style={{width: '350px'}} className="input-text"
                                                   maxLength="50" placeholder="请填写检查内容(必填)"
                                                   value={this.state.myChecks[index].value}/>
                                        </div>
                                    </div>

                                </div>

                            )

                        })}

                        <div className="addCh3 row cl">
                            <div className="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-20">
                                <button className="btn btn-primary radius" type="button"
                                        onClick={this.addCheck}>新增自定义检查项
                                </button>
                            </div>
                        </div>
                    </div>


                return (
                    <div>
                        <form className="form form-horizontal" id="form">
                            <div className="page-container">
                                <div className="row cl">
                                    <label className="form-label col-xs-4 col-sm-2"><span
                                        className="c-red">*</span>检查表名称：</label>
                                    <div className="formControls col-xs-8 col-sm-9">
                                        <input type="text" onChange={(e) => this.inputChange('tableName', e)}
                                               style={{width: '350px'}} className="input-text"
                                               maxLength="50" placeholder="请填写检查表名称(必填)"
                                               value={this.state.tableName}/>
                                    </div>
                                </div>
                                <%--<div className="row cl">--%>
                                    <%--<label className="form-label col-xs-4 col-sm-2"><span--%>
                                        <%--className="c-red">*</span>请选择检查方式--%>
                                        <%--:</label>--%>
                                    <%--<div className="formControls col-xs-8 col-sm-9">--%>
                                    <%--<span className="select-box inline">--%>
                                        <%--<select className="select" style={{width: '150px'}}--%>
                                                <%--onChange={(e) => this.selectChange('checkType', e)}>--%>
                                        <%--<option value="0">选择检查方式</option>--%>
                                        <%--<option value="1">日常</option>--%>
                                        <%--<option value="2">定期</option>--%>
                                        <%--<option value="3">季节</option>--%>
                                        <%--<option value="4">其他</option>--%>
                                        <%--<option value="5">综合</option>--%>
                                        <%--</select>--%>
                                    <%--</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                {this.state.checkType == 2 ?
                                    <div className="row cl dq">
                                        <label className="form-label col-xs-4 col-sm-2"><span
                                            className="c-red">*</span>请填写定期时间
                                            :</label>
                                        <div className="formControls col-xs-8 col-sm-9">
                                            <input type="number" min="0" max="7" style={{width: '150px'}}
                                                   value={this.state.days}
                                                   onChange={(e) => this.inputChange('days', e)}/>
                                        </div>
                                    </div> : null
                                }

                                <div className="row cl">
                                    <label className="form-label col-xs-4 col-sm-2"><span
                                        className="c-red">*</span>请选择检查类型
                                        :</label>
                                    <div className="formControls col-xs-8 col-sm-9">
                                    <span className="select-box inline">
                                        <select className="select" style={{width: '200px'}}
                                                onChange={this.LeixinSelectChange}>
                                              <option value="0">请选择检查类型</option>
                                            {this.state.leixin.map((item, index) => {
                                                return (
                                                    <option value={item.id}>{item.name}</option>
                                                )
                                            })}
                                        </select>
                                    </span>
                                    </div>
                                </div>
                                {this.state.checkedLeixin != null&& this.state.checkedLeixin!=0?
                                    this.state.checkedLeixin == -2||this.state.checkedLeixin == -1 ?
                                        xcjc : gw : null
                                }

                            </div>
                        </form>


                        <div className="row cl">
                            <div className="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-20">
                                <button onClick={this.save} className="btn btn-primary radius"
                                        type="button"
                                        style={{
                                            paddingLeft: '70px',
                                            paddingRight: '70px',
                                            marginTop: '100px',
                                            marginBottom: '30px'
                                        }}>
                                    <i className="Hui-iconfont">&#xe632;</i>保存检查表
                                </button>

                            </div>
                        </div>
                    </div>
                )
            }
        }

        ReactDOM.render(<AddModel/>,
            document.getElementById('app'),
        )


    </script>


</html>
