--2018-05-28 添加文档推送表
CREATE TABLE `push_message_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `del` int(1) DEFAULT '0' COMMENT '1删除',
  `ctime` datetime DEFAULT NULL COMMENT '推送时间',
  `uid` int(11) DEFAULT NULL COMMENT '发送人',
  `type` int(1) DEFAULT NULL COMMENT '1 发送给企业 2发送给村 3发送给镇 4 区  5行业',
  `file_name` varchar(200) DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(200) DEFAULT NULL COMMENT '文件地址',
  `content` varchar(2000) DEFAULT NULL COMMENT '文本内容（暂未用到）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推送消息';

CREATE TABLE `push_message_user_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  `uid` int(11) DEFAULT NULL COMMENT '推送人',
  `tid` int(11) DEFAULT NULL COMMENT '接收人',
  `is_read` int(1) DEFAULT '0' COMMENT '0 已看过 1未看过',
  `u_del` int(1) DEFAULT '0' COMMENT '1 发送人删除',
  `t_del` int(1) DEFAULT '0' COMMENT '1 接收人删除',
  `push_id` int(11) DEFAULT NULL COMMENT '推送编号',
  PRIMARY KEY (`id`),
  INDEX `push_id` (`push_id`) USING BTREE ,
  INDEX `tid` (`tid`) USING BTREE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息推送详细表';

--2018-06-14 添加安全风险管控系统系列表
CREATE TABLE `a_implementation_tbl` (
  `uid` int(11) NOT NULL,
  `doc_url` varchar(200) DEFAULT NULL COMMENT '文档路径',
  `doc_name` varchar(200) DEFAULT NULL COMMENT '文档名',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统-实施方案';

CREATE TABLE `a_danger_manual_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `industry` varchar(40) DEFAULT NULL COMMENT '所属行业',
  `level1` varchar(40) DEFAULT NULL COMMENT '一级',
  `level2` varchar(40) DEFAULT NULL COMMENT '2级',
  `level3` varchar(200) DEFAULT NULL COMMENT '场所/环节/部门 三级',
  `factors` varchar(1000) DEFAULT NULL COMMENT '较大危险因素 对应风险辨识的风险名称',
  `type` varchar(100) DEFAULT NULL COMMENT '易发生的事故类型 风险因素',
  `measures` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '主要防范措施 ',
  `reference` varchar(200) DEFAULT NULL COMMENT '依据',
  `flag` varchar(10) DEFAULT '1,2,3' COMMENT '1 部位 2装置系统 3工艺',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=711 DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统-危险因素辨识与防范指导手册';

CREATE TABLE `a_company_manual_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '企业编号',
  `dmid` int(11) DEFAULT NULL COMMENT '关联的标准id，若无则是手动填写的内容',
  `level1` varchar(100) DEFAULT NULL COMMENT '一级',
  `level2` varchar(100) DEFAULT NULL COMMENT '二级',
  `level3` varchar(200) DEFAULT NULL COMMENT '场所/环节/部门 三级',
  `factors` varchar(1000) DEFAULT NULL COMMENT '较大危险因素',
  `type` varchar(100) DEFAULT NULL COMMENT '易发生的事故类型 风险名称',
  `measures` varchar(1000) DEFAULT NULL COMMENT '主要防范措施 ',
  `reference` varchar(200) DEFAULT NULL COMMENT '依据',
  `flag` varchar(10) DEFAULT NULL COMMENT '1 较大 2重大 3自定义',
  `level` varchar(10) DEFAULT NULL COMMENT '隐患级别',
  `issys` int(1) DEFAULT '0' COMMENT '0 不显示风险等级 1显示风险等级',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `gkcs` varchar(2000) DEFAULT NULL COMMENT '管控措施',
  `gkzt` varchar(2000) DEFAULT NULL COMMENT '管控主体',
  `fjgkfzr` varchar(100) DEFAULT NULL COMMENT '分级管控负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统-企业 危险因素辨识与防范';

CREATE TABLE `a_zytjfxcdpj_tbl` (
  `uid` int(11) NOT NULL,
  `cores` varchar(100) DEFAULT NULL COMMENT '分数s',
  `lev` varchar(1) DEFAULT NULL COMMENT '最终级别\r\n>320   不可容许的危险 A\r\n160～320    高度危险    B\r\n70～160 中度危险    C\r\n<70    轻度和可容许的危险   D',
  `score` double(7,2) DEFAULT NULL COMMENT '总分数',
  `danger` varchar(20) DEFAULT NULL COMMENT '危险程度',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统- 一般工贸企业作业条件风险程度评价';

CREATE TABLE `a_whp_tbl` (
  `uid` int(11) NOT NULL,
  `cores` varchar(100) DEFAULT NULL COMMENT '分数s',
  `lev` varchar(10) DEFAULT NULL COMMENT '最终级别 总分在90分以上（含90分）的为蓝色；75分（含75分）至90分的为黄色；60分（含60分）至75分的为橙色；60分以下的为红色。',
  `spe` varchar(20) DEFAULT NULL COMMENT '存在下列情况之一的企业直接判定为红色（最高风险等级）',
  `score` double(10,2) DEFAULT NULL COMMENT '总分数',
  `danger` varchar(20) DEFAULT NULL COMMENT '危险程度',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统- 危化品企业 危险化学品生产储存企业安全风险评估诊断分级指南（试行）';

CREATE TABLE `a_fxjz_tbl` (
  `uid` int(11) NOT NULL,
  `ls` varchar(100) DEFAULT NULL COMMENT 'L分数s',
  `ss` varchar(100) DEFAULT NULL COMMENT 'S分数s',
  `lev` varchar(10) DEFAULT NULL COMMENT '最终级别 R=1，2，3，蓝色低风险企业；R=4，6，黄色一般风险企业；R=8，9，橙色较大风险企业；R=12，16，红色重大风险企业',
  `l` int(3) DEFAULT NULL COMMENT 'l值',
  `s` int(3) DEFAULT NULL COMMENT 's值',
  `score` int(3) DEFAULT NULL COMMENT '总分数',
  `danger` varchar(20) DEFAULT NULL COMMENT '危险程度',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统-风险矩阵';

CREATE TABLE `a_zdfxys_tbl` (
  `uid` int(11) NOT NULL COMMENT '企业user_id',
  `zdfx` varchar(5000) DEFAULT NULL COMMENT '重大风险',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统-企业 重大风险';

--添加企业行业字段，原行业变为监管行业
alter table company_tbl add industry2 varchar(10) not Null;

CREATE TABLE `a_fxgzp_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flag` int(1) DEFAULT NULL COMMENT '1 厂级 2车间级 3班组级',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `gylc` varchar(2000) DEFAULT NULL COMMENT '工艺流程简介',
  `czfx` varchar(2000) DEFAULT NULL COMMENT '存在风险',
  `yjczcs` varchar(2000) DEFAULT NULL COMMENT '应急处置措施',
  `glcs` varchar(2000) DEFAULT NULL COMMENT '管理措施',
  `uid` int(11) DEFAULT NULL COMMENT '企业编号',
  `ctime` datetime DEFAULT NULL,
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='风险告知牌';



CREATE TABLE `a_material_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `name_en` varchar(50) DEFAULT NULL COMMENT '英文名',
  `fzs` varchar(50) DEFAULT NULL COMMENT '分子式',
  `cas` varchar(20) DEFAULT NULL COMMENT 'CAS号',
  `un` varchar(20) DEFAULT NULL COMMENT 'UN编号',
  `fzl` varchar(50) DEFAULT NULL COMMENT '分子量',
  `serial_number` varchar(10) DEFAULT NULL COMMENT '危化品序号',
  `wh_wg` varchar(20) DEFAULT NULL COMMENT '物化特性 外观与性状',
  `wh_rd` varchar(20) DEFAULT NULL COMMENT '熔点（℃）',
  `wh_fd` varchar(20) DEFAULT NULL COMMENT '沸点（℃）',
  `wh_md` varchar(20) DEFAULT NULL COMMENT '相对密度（水=1）',
  `wh_bhzqy` varchar(20) DEFAULT NULL COMMENT '饱和蒸汽压[Kpa]',
  `wh_rjx` varchar(60) DEFAULT NULL COMMENT '溶解性',
  `wx` varchar(500) DEFAULT NULL COMMENT '危险特性',
  `rs_rsx` varchar(50) DEFAULT NULL COMMENT '燃烧爆炸危险性 燃烧性',
  `rs_hxfj` varchar(50) DEFAULT NULL COMMENT '建规火险分级',
  `rs_sd` varchar(50) DEFAULT NULL COMMENT '闪点（℃）',
  `rs_zrd` varchar(50) DEFAULT NULL COMMENT '自燃点（℃）',
  `rs_bz_min` varchar(50) DEFAULT NULL COMMENT '爆炸下限(V%)',
  `rs_bz_max` varchar(50) DEFAULT NULL COMMENT '爆炸上限(V%)',
  `rs_lb` varchar(50) DEFAULT NULL COMMENT '类别',
  `rs_wdx` varchar(50) DEFAULT NULL COMMENT '稳定性',
  `rs_jhwh` varchar(50) DEFAULT NULL COMMENT '聚合危害',
  `rs_jjw` varchar(50) DEFAULT NULL COMMENT '禁忌物',
  `rs_mhff` varchar(200) DEFAULT NULL COMMENT '灭火方法',
  `dx_jcjx_zg` varchar(50) DEFAULT NULL COMMENT '毒性及危害 接触极限 中国MAC',
  `dx_jcjx_mg` varchar(50) DEFAULT NULL COMMENT '接触极限 美国TWA',
  `dx_jcjx_qsl` varchar(50) DEFAULT NULL COMMENT '毒性及危害 接触极限 前苏联MAC',
  `dx_jcjx_mg2` varchar(50) DEFAULT NULL COMMENT '接触极限 美国STEL',
  `dx_dx` varchar(50) DEFAULT NULL COMMENT '毒性',
  `dx_dx2` varchar(50) DEFAULT NULL COMMENT '毒性 LD50',
  `dx_dx3` varchar(50) DEFAULT NULL COMMENT '毒性 LC50',
  `dx_qr` varchar(50) DEFAULT NULL COMMENT '侵入途径',
  `dx_wh` varchar(50) DEFAULT NULL COMMENT '健康危害',
  `cy` varchar(200) DEFAULT NULL COMMENT '储运注意事项',
  `xlcz` varchar(200) DEFAULT NULL COMMENT '泄漏处置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物质特性表';

CREATE TABLE `a_implementation_tbl` (
  `uid` int(11) NOT NULL,
  `text0` varchar(200) DEFAULT NULL COMMENT '颁布令',
  `text1` varchar(200) DEFAULT NULL COMMENT '组织机构及职责',
  `text2` varchar(200) DEFAULT NULL COMMENT '《双控机制》运行管理责任制',
  `text3` varchar(200) DEFAULT NULL COMMENT '风险分级管控及作业指导书',
  `text4` varchar(200) DEFAULT NULL COMMENT '隐患排查治理及作业指导书',
  `text5` varchar(200) DEFAULT NULL COMMENT '考核',
  `text6` varchar(200) DEFAULT NULL COMMENT '持续改进',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全风险管控系统-实施方案';

CREATE TABLE `a_fxgzp_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flag` int(1) DEFAULT NULL COMMENT '1 厂级 2车间级 3班组级',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `fxdj` varchar(20) DEFAULT NULL COMMENT '风险等级',
  `glzrr` varchar(20) DEFAULT NULL COMMENT '管理责任人',
  `sglx` longtext COMMENT '事故类型',
  `fxys` longtext COMMENT '风险因素',
  `yjczcs` longtext COMMENT '应急防范措施',
  `uid` int(11) DEFAULT NULL COMMENT '企业编号',
  `ctime` datetime DEFAULT NULL,
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='风险告知牌';


