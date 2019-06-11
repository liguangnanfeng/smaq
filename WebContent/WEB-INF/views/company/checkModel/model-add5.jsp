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
            top: 2px;
            font-weight: bold;
        }

        .my_left {
            margin-left: 12%;
        }

        .my_left2 {
            margin-left: 14%;
        }

    </style>
    <script>
        var dmname = "${dmname}";
        var dmid = "${dmid}";
        var checkType = "${checkType}";
        var industryType = "${industryType}";

    </script>
    <script type="text/javascript">
        function eat() {
            var b = null, l1 = '', c = 1;
            var b2 = null, l2 = '', c2 = 1;
            $("tbody tr").each(function () {
                var td = $(this).children("td").eq(0);
                var td2 = $(this).children("td").eq(1);
                var l1_ = td.text();
                var l2_ = td2.text();
                //Same to top level
                if (l1 == l1_) {
                    td.remove();
                    c = c + 1;
                    if (l2 == l2_) {
                        td2.remove();
                        c2 = c2 + 1;

                    } else {
                        l2 = l2_;
                        if (b2 != null) {
                            b2.attr("rowspan", c2);
                            c2 = 1;
                        }
                        b2 = td2;
                    }

                } else {//Diffrent to top level
                    l1 = l1_;
                    if (b != null) {
                        b.attr("rowspan", c);
                        c = 1;
                    }
                    b = td;
                    l2 = l2_;
                    if (b2 != null) {
                        b2.attr("rowspan", c2);
                        c2 = 1;
                    }
                    b2 = td2;

                }
            })
            if (b != null) {
                b.attr("rowspan", c);
            }
            if (b2 != null) {
                b2.attr("rowspan", c2);
            }

        }
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
                this.dmname = dmname;
                this.dmid = dmid;
                this.checkType = checkType;
                this.industryType = industryType;
                const arrs = ['日常', '定期', '季节', '其他', '综合'];
                const aa = arrs[checkType - 1];
                const objs = {
                    '-1': '基础',
                    '-2': '现场'
                };
                const bb = objs[industryType];
                this.tableName = dmname + aa + bb + '检查表';
            }

            componentWillMount() {
                this.getBasic();
            }

            componentDidMount = () => {

            }

            componentDidUpdate = () => {

            }


            getBasic = () => {   //获取企业项
                const _self = this;
                $.ajax({
                    type: "POST",
                    url: `${host}/village/selectDep2`,
                    data: {"depId": this.dmid, 'dpName': this.dmname, 'checkType': parseInt(this.industryType)},
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


            addCheck = () => {
                if (this.state.checkedLeixin == -2 && !this.state.bmId) {
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
                arr.map((item, index2) => {
                    html.push(
                        <div className="row" key={index2} style={index2 == 0 ? {marginTop: '10px'} : {}}>
                            <input type="checkbox"
                                   checked={this.state.list[index1].list[index2].checked == 1 ? true : false}
                                   style={{marginTop: '0px', marginRight: '5px'}} onClick={() => {
                                this.checkBox(index1, index2)
                            }}/>
                            <label>{item.measures}</label>
                        </div>
                    )
                })
                return html;
            }


            save = () => {
                let state = this.state;
                // if (!state.tableName) {    //验证检查表名字是否填写
                //     alert('检查表名字必须填写');
                //     return
                // }

                if (this.checkType == 2 && state.days == null) {    //验证周期天数是否填写
                    alert('填写周期天数,只能输入数字');
                    return
                }
                if (this.checkType == 2 && state.days == 0) {    //验证周期天数是否填写
                    alert('周期天数必须大于1');
                    return
                }
                if (state.list.length == 0 && state.myChecks.length == 0) {    //验证是否有检查项
                    alert('没有选择检查项');
                    return
                }

                let postData = {
                    template: this.tableName,     //表名字
                    title: this.checkType,        //检查方式 1:日常  2:定期  3:季节 4:其他 5:综合
                    checkType: this.industryType, //檢查類型 -1基础 -2现场   其他高危
                    cycle: parseInt(state.days),   //检查周期天数
                    checkLevels: []                 //检查项
                }
                state.list.map((item) => {
                    item.list.map((item2) => {
                        if (item2.checked == 1) {
                            let checkItem = {};
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
                            checkItem.checkType = this.industryType;
                            industryId:null
                            postData.checkLevels.push(checkItem);
                        }
                    })
                })
                state.myChecks.map((item) => {
                    if (item.title != '' && item.value != '') {
                        let checkItem = {};
                        checkItem.id = null;
                        checkItem.level1 = this.dmname;
                        checkItem.level2 = null;
                        checkItem.level3 = item.title;
                        checkItem.level4 = item.value;
                        checkItem.reference = null;
                        checkItem.factors = null;
                        checkItem.types = null;
                        checkItem.gkcs = null;
                        checkItem.gkzt = null;
                        checkItem.checkType = this.industryType;
                        industryId:null
                        postData.checkLevels.push(checkItem);
                    }
                })
                if (postData.checkLevels.length == 0) {
                    alert('没有选择检查项');
                    return
                }

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
                            window.parent.location.href = '${ly }/company/model-list-main';
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
                console.log(this.state.list)
                const arr = ['日常检查', '定期检查', '季节检查', '其他检查', '综合检查'];
                const checkType = arr[this.checkType - 1];
                const obj = {
                    '-1': '基础检查',
                    '-2': '现场检查'
                };
                const industryType = obj[this.industryType];


                const xcjc =      //如果是现场基础检查 渲染这个
                    <div>
                        <table id="xxx" className="table table-border table-bordered table-bg table-hover table-sort">
                            <thead>
                            <tr className="text-c">
                                <th style={{minWidth:'80px'}}>部位</th>
                                <th style={{minWidth:'80px'}}>岗位</th>
                                <th style={{minWidth:'150px'}}>风险点</th>
                                <th style={{minWidth:'150px'}}>风险内容</th>
                                <th style={{minWidth:'50px'}} className="div-pcz">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.list.map((item, index) => {
                                return (
                                    <tr>
                                        <td className="text-c">1</td>
                                        <td className="text-c">2</td>
                                        <td className="text-c">3</td>
                                        <td className="text-c">4</td>
                                        <td className="text-c">勾选</td>
                                    </tr>
                                )
                            })}
                            </tbody>
                        </table>
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
                                               style={{
                                                   position: 'absolute',
                                                   left: '380px',
                                                   fontSize: '20px',
                                                   fontWeight: 'bold',
                                                   cursor: 'pointer'
                                               }}
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

                        <div className="row cl">
                            <div className="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-30">
                                <button className="btn btn-primary radius" type="button"
                                        onClick={this.addCheck}>
                                    新增自定义检查项
                                </button>
                            </div>
                        </div>

                    </div>

                return (
                    <div>
                        <form className="form form-horizontal" id="form">
                            <div className="page-container">

                                {this.checkType == 2 ?
                                    <div className="row cl dq">
                                        <label className="form-label col-xs-4 col-sm-2"><span
                                            className="c-red">*</span>定期时间
                                            :</label>
                                        <div className="formControls col-xs-8 col-sm-9">
                                            <input type="number" min="0" max="7" style={{width: '350px'}}
                                                   className="input-text"
                                                   value={this.state.days}
                                                   onChange={(e) => this.inputChange('days', e)}/>
                                        </div>
                                    </div> : null
                                }

                                {xcjc}
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
