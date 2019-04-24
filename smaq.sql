/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : smaq

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 03/12/2018 13:43:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a_company_manual_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_company_manual_tbl`;
CREATE TABLE `a_company_manual_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '企业编号',
  `dmid` int(11) NULL DEFAULT NULL COMMENT '关联的标准id，若无则是手动填写的内容',
  `level1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级',
  `level2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级',
  `level3` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场所/环节/部门 三级',
  `factors` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '较大危险因素',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '易发生的事故类型 风险名称',
  `measures` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要防范措施 ',
  `reference` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '依据',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 较大 2重大 3自定义',
  `level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '隐患级别',
  `issys` int(1) NULL DEFAULT 0 COMMENT '0 不显示风险等级 1显示风险等级',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gkcs` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管控措施',
  `gkzt` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管控主体',
  `fjgkfzr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分级管控负责人',
  `material` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物质特性，化学因素',
  `gy` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高危工艺',
  `hxys` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物理因素',
  `wlbs` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料辨识',
  `flag2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '0 不加入公告牌 1加入公告牌',
  `gwzy` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高危作业',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `level`(`level`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6859 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-企业 危险因素辨识与防范' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_company_manual_zp_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_company_manual_zp_tbl`;
CREATE TABLE `a_company_manual_zp_tbl`  (
  `acm_id` int(11) NOT NULL DEFAULT 0,
  `level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危险因素自评等级，红色、橙色、黄色、蓝色',
  PRIMARY KEY (`acm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '危险因素自评等级' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_danger_manual_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_danger_manual_tbl`;
CREATE TABLE `a_danger_manual_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `industry` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `level1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级',
  `level2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '2级',
  `level3` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场所/环节/部门 三级',
  `factors` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '较大危险因素',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '易发生的事故类型 风险名称',
  `measures` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主要防范措施 ',
  `reference` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '依据',
  `flag2` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1 内容加入公告牌',
  `level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '分险级别',
  `flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1 对应较大风险辨识 3对应一般和较小风险辨识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3702 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-危险因素辨识与防范指导手册' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_danger_type_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_danger_type_tbl`;
CREATE TABLE `a_danger_type_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-危险因素辨识与防范指导手册-事故类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_fxgzp_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_fxgzp_tbl`;
CREATE TABLE `a_fxgzp_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flag` int(1) NULL DEFAULT NULL COMMENT '1 厂级 2车间级 3班组级',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `fxdj` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风险等级',
  `glzrr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理责任人',
  `sglx` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '事故类型',
  `fxys` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '风险因素',
  `fxys_zd` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '重大风险',
  `yjczcs` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '应急防范措施',
  `uid` int(11) NULL DEFAULT NULL COMMENT '企业编号',
  `ctime` datetime NULL DEFAULT NULL,
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `isedit` int(1) NULL DEFAULT 0 COMMENT '1 自定义修改过，改过的不再自动生成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4408 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '风险告知牌' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_fxjz_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_fxjz_tbl`;
CREATE TABLE `a_fxjz_tbl`  (
  `uid` int(11) NOT NULL COMMENT 'a_company_manual_tbl 的编号',
  `ls` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'L分数s',
  `ss` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'S分数s',
  `lev` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终级别 R=1，2，3，蓝色低风险企业；R=4，6，黄色一般风险企业；R=8，9，橙色较大风险企业；R=12，16，红色重大风险企业',
  `l` int(3) NULL DEFAULT NULL COMMENT 'l值',
  `s` int(3) NULL DEFAULT NULL COMMENT 's值',
  `score` int(3) NULL DEFAULT NULL COMMENT '总分数',
  `danger` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危险程度',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-风险矩阵' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_gwyj_sh_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_gwyj_sh_tbl`;
CREATE TABLE `a_gwyj_sh_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '伤害',
  `measures` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '措施',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位应急-伤害类型及应急处理措施' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_gwyj_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_gwyj_tbl`;
CREATE TABLE `a_gwyj_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `depart_id` int(11) NULL DEFAULT NULL COMMENT '组织架构 部门id',
  `sh_ids` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '涉及的伤害s',
  `fzr1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要负责人',
  `fzr2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门负责人',
  `fzr3` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工段负责人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位应急卡' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_gy_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_gy_tbl`;
CREATE TABLE `a_gy_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '危险化工工艺目录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_gzk_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_gzk_tbl`;
CREATE TABLE `a_gzk_tbl`  (
  `uid` int(11) NOT NULL COMMENT 'a_company_manual_tbl 的编号',
  `hxys` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '化学因素   物质特性中选择',
  `wlys` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物理因素 a_hxysgzk_tbl中选择',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业告知卡' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_hxysgzk_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_hxysgzk_tbl`;
CREATE TABLE `a_hxysgzk_tbl`  (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物理因素告知卡' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_implementation_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_implementation_tbl`;
CREATE TABLE `a_implementation_tbl`  (
  `uid` int(11) NOT NULL,
  `text0` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '颁布令',
  `textz1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '组织机构及职责',
  `text1` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '组织机构及职责',
  `text2` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '《双控机制》运行管理责任制',
  `text3` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '风险分级管控及作业指导书',
  `text4` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '隐患排查治理及作业指导书',
  `text5` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '考核',
  `text6` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '持续改进',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-实施方案' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_industry_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_industry_tbl`;
CREATE TABLE `a_industry_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-行业表 用于 危险因素辨识与防范指导手册等标准' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_material_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_material_tbl`;
CREATE TABLE `a_material_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `name_en` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名',
  `fzs` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分子式',
  `cas` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CAS号',
  `un` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UN编号',
  `fzl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分子量',
  `serial_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危化品序号',
  `wh_wg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物化特性 外观与性状',
  `wh_rd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '熔点（℃）',
  `wh_fd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '沸点（℃）',
  `wh_md` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对密度（水=1）',
  `wh_bhzqy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '饱和蒸汽压[Kpa]',
  `wh_rjx` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '溶解性',
  `wx` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危险特性',
  `rs_rsx` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '燃烧爆炸危险性 燃烧性',
  `rs_hxfj` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建规火险分级',
  `rs_sd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '闪点（℃）',
  `rs_zrd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自燃点（℃）',
  `rs_bz_min` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爆炸下限(V%)',
  `rs_bz_max` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爆炸上限(V%)',
  `rs_lb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `rs_wdx` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '稳定性',
  `rs_jhwh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聚合危害',
  `rs_jjw` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禁忌物',
  `rs_mhff` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '灭火方法',
  `dx_jcjx_zg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毒性及危害 接触极限 中国MAC',
  `dx_jcjx_mg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接触极限 美国TWA',
  `dx_jcjx_qsl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毒性及危害 接触极限 前苏联MAC',
  `dx_jcjx_mg2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接触极限 美国STEL',
  `dx_dx` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毒性',
  `dx_dx2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毒性 LD50',
  `dx_dx3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毒性 LC50',
  `dx_qr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '侵入途径',
  `dx_wh` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康危害',
  `cy` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '储运注意事项',
  `xlcz` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '泄漏处置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1454 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物质特性表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_whp_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_whp_tbl`;
CREATE TABLE `a_whp_tbl`  (
  `uid` int(11) NOT NULL,
  `cores` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分数s',
  `lev` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终级别 总分在90分以上（含90分）的为蓝色；75分（含75分）至90分的为黄色；60分（含60分）至75分的为橙色；60分以下的为红色。',
  `spe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存在下列情况之一的企业直接判定为红色（最高风险等级）',
  `score` double(10, 2) NULL DEFAULT NULL COMMENT '总分数',
  `danger` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危险程度',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统- 危化品企业 危险化学品生产储存企业安全风险评估诊断分级指南（试行）' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_zdfxys_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_zdfxys_tbl`;
CREATE TABLE `a_zdfxys_tbl`  (
  `uid` int(11) NOT NULL COMMENT '企业user_id',
  `zdfx` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重大风险',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统-企业 重大风险' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for a_zytjfxcdpj_tbl
-- ----------------------------
DROP TABLE IF EXISTS `a_zytjfxcdpj_tbl`;
CREATE TABLE `a_zytjfxcdpj_tbl`  (
  `uid` int(11) NOT NULL COMMENT 'a_company_manual_tbl 的编号',
  `cores` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分数s',
  `lev` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终级别\r\n>320	不可容许的危险	A\r\n160～320	高度危险	B\r\n70～160	中度危险	C\r\n<70	轻度和可容许的危险	D',
  `score` double(7, 2) NULL DEFAULT NULL COMMENT '总分数',
  `danger` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危险程度',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全风险管控系统- 一般工贸企业作业条件风险程度评价' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for app_token_tbl
-- ----------------------------
DROP TABLE IF EXISTS `app_token_tbl`;
CREATE TABLE `app_token_tbl`  (
  `access_token` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'app端登录用',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `expires` int(11) NULL DEFAULT NULL COMMENT '有效期，过期后需要重新登录',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`access_token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c3p0testtable
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable`  (
  `a` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'c3p0testtable' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for company_iskey_tbl
-- ----------------------------
DROP TABLE IF EXISTS `company_iskey_tbl`;
CREATE TABLE `company_iskey_tbl`  (
  `user_id` int(11) NOT NULL,
  `key1` int(1) NULL DEFAULT 0 COMMENT '1 镇重点',
  `key2` int(1) NULL DEFAULT 0 COMMENT '1 区重点',
  `key3` int(1) NULL DEFAULT 0 COMMENT '1 市重点',
  `key4` int(1) NULL DEFAULT 0 COMMENT '1 省重点',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业 镇 重点 区重点 市重点 省重点' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for company_reg_tbl
-- ----------------------------
DROP TABLE IF EXISTS `company_reg_tbl`;
CREATE TABLE `company_reg_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) NULL DEFAULT NULL COMMENT '所属市',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '机构名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '企业注册机构基库' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for company_tbl
-- ----------------------------
DROP TABLE IF EXISTS `company_tbl`;
CREATE TABLE `company_tbl`  (
  `user_id` int(11) NOT NULL COMMENT '主键',
  `village_id` int(11) NULL DEFAULT NULL COMMENT '所属村id',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `reg_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业注册类型',
  `reg_type2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自定义企业类型',
  `region_id` int(11) NULL DEFAULT NULL COMMENT '地区id',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `legal` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `legal_contact` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `capital` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册资本(万元)',
  `establish` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成立日期',
  `business` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业期限',
  `scope` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营范围',
  `license` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照',
  `reg_region` int(11) NULL DEFAULT NULL COMMENT '企业注册省市',
  `authority` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记机关',
  `economic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经济类型',
  `fixed` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定资产(千元)',
  `staff` int(11) NULL DEFAULT NULL COMMENT '员工总数',
  `income` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主营业务收入',
  `industry` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `danger` int(1) NULL DEFAULT NULL COMMENT '是否高危作业 1是',
  `dangers` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高危作业分类',
  `hazard` int(1) NULL DEFAULT NULL COMMENT '重大危险源 1是',
  `dlevel` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风险等级',
  `charge` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要负责人',
  `charge_contact` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人电话',
  `safety` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专兼职安全员',
  `safety_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专兼职安全员联系方式',
  `safety_m` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全管理部门负责人',
  `safety_m_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全管理部门负责人联系方式',
  `duty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业值班电话',
  `postage` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业邮政编码',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业邮箱',
  `pic1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全生产管理网络图',
  `pic2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全（生产）许可证',
  `is_key` int(1) NULL DEFAULT 0 COMMENT '0 非重点 1 重点',
  `industry2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行业，原行业为监管行业',
  `scale` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规模类型 规上、中小、小微',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_id_2`(`user_id`) USING BTREE,
  INDEX `village_id`(`village_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cyryjkjh_tbl
-- ----------------------------
DROP TABLE IF EXISTS `cyryjkjh_tbl`;
CREATE TABLE `cyryjkjh_tbl`  (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '劳动者姓名',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查日期 年月',
  `jcxmzy` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查项目摘要',
  `jcjg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查结果',
  `jcdw` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查单位',
  `zybwhys` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接触的职业病危害因素名称',
  `tjlb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体检类别 在岗，岗前，离岗， 诊断， 复查',
  `uid` int(11) NULL DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '从业人员健康监护' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for danger_lib_tbl
-- ----------------------------
DROP TABLE IF EXISTS `danger_lib_tbl`;
CREATE TABLE `danger_lib_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `name2` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '别名',
  `cas` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'cas号',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2829 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '危险信息源库' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for detection_tbl
-- ----------------------------
DROP TABLE IF EXISTS `detection_tbl`;
CREATE TABLE `detection_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检测类别',
  `report_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评价时间',
  `expiration_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '到期时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `jcd` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '监测点',
  `whys` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检测职业病危害因素',
  `jcjg` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检测结果',
  `jcdw` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检测单位',
  `jcbh` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检测编号',
  `ba` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备案情况及备案表',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  CONSTRAINT `detection_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 257 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '检测' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for district_tbl
-- ----------------------------
DROP TABLE IF EXISTS `district_tbl`;
CREATE TABLE `district_tbl`  (
  `user_id` int(11) NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区名',
  `region_id` int(11) NULL DEFAULT NULL COMMENT '地区',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '市id',
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `district_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for evaluation_tbl
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_tbl`;
CREATE TABLE `evaluation_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `flag` int(1) NULL DEFAULT NULL COMMENT '1 安全生产类 2 职业健康类',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '安全生产类\r\n1.预评价\r\n2.验收评价\r\n3.现状评价\r\n4.危化品经营证评价\r\n5.危化品生产证评价\r\n6.危化品使用证评价\r\n7.重大危险源评估\r\n8.安全条件论证报告\r\n9.安全条件综合分析报告\r\n职业健康类\r\n1.预评价\r\n2.控效评价\r\n3.现状评价',
  `report_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '评价报告名称',
  `report_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '编制日期',
  `expiration_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '有效期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '编制单位',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档路径',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  CONSTRAINT `evaluation_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 178 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '评价评估' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for examination_tbl
-- ----------------------------
DROP TABLE IF EXISTS `examination_tbl`;
CREATE TABLE `examination_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在岗，岗前，离岗， 诊断， 复查\r\n',
  `contact_condition` int(100) NULL DEFAULT NULL COMMENT '接触情况 0无   1有 废弃',
  `contact_members` int(11) NULL DEFAULT NULL COMMENT '接触人数 废弃',
  `healthcheck_member` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体检人数',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体检时间',
  `end_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到期时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `project` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体检项目',
  `whys` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业病危害因素',
  `tjjg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体检机构',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 800 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职业健康检查' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for facilities_tbl
-- ----------------------------
DROP TABLE IF EXISTS `facilities_tbl`;
CREATE TABLE `facilities_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `flag` int(1) NULL DEFAULT 1 COMMENT '1 消防 2应急',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种类',
  `model` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `equipped_position` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配备位置',
  `amount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `expiry_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  CONSTRAINT `facilities_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1585 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消防，应急设施配备' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for feng_tbl
-- ----------------------------
DROP TABLE IF EXISTS `feng_tbl`;
CREATE TABLE `feng_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `cas` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'cas号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '粉尘类' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for global_region_tbl
-- ----------------------------
DROP TABLE IF EXISTS `global_region_tbl`;
CREATE TABLE `global_region_tbl`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '区域编号',
  `parent_id` int(32) NULL DEFAULT NULL COMMENT '所属父类',
  `region_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `region_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域类型  0：中国、1：省份、2：城市、3：区县',
  `city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3426 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for hua_tbl
-- ----------------------------
DROP TABLE IF EXISTS `hua_tbl`;
CREATE TABLE `hua_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `cas` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'cas号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 375 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '化学因素' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for import_record_tbl
-- ----------------------------
DROP TABLE IF EXISTS `import_record_tbl`;
CREATE TABLE `import_record_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'excel名',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `create_time` datetime NULL DEFAULT NULL COMMENT '时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '企业导入记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for industry_lib_tbl
-- ----------------------------
DROP TABLE IF EXISTS `industry_lib_tbl`;
CREATE TABLE `industry_lib_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '1 1级  22级',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '行业类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for industry_tbl
-- ----------------------------
DROP TABLE IF EXISTS `industry_tbl`;
CREATE TABLE `industry_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '企业自查行业分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for law_doc_tbl
-- ----------------------------
DROP TABLE IF EXISTS `law_doc_tbl`;
CREATE TABLE `law_doc_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排查文件名',
  `flag` int(11) NULL DEFAULT 2 COMMENT '1自查 2执法3第三方4检查',
  `doc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排查文件url',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(10) NULL DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for library_tbl
-- ----------------------------
DROP TABLE IF EXISTS `library_tbl`;
CREATE TABLE `library_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL COMMENT '1所属行业 \r\n2风险分级\r\n5评价类型\r\n6检测类别\r\n7高危作业\r\n\r\n11安管人员类别\r\n12危险化学品安全管理人员\r\n13特种作业人员\r\n',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `cycle` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '周期（tyep=5  =6',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 153 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '分类基库表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for lightning_protection_tbl
-- ----------------------------
DROP TABLE IF EXISTS `lightning_protection_tbl`;
CREATE TABLE `lightning_protection_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测项目',
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测内容',
  `last_test_time` datetime NULL DEFAULT NULL COMMENT '上次检测时间',
  `expiration_time` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `test_results` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测结果',
  `detection_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测单位',
  `test_report_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测报告编号',
  `remark` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '防雷防静电设施安全检查表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for material_tbl
-- ----------------------------
DROP TABLE IF EXISTS `material_tbl`;
CREATE TABLE `material_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `material` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原材料',
  `annual_consumption` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年用量',
  `maximum_storage` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最大储存量',
  `state` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物态',
  `storage_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '储存地点',
  `storage_method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '储存方式',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1501 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主要原辅材料' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mequipment_tbl
-- ----------------------------
DROP TABLE IF EXISTS `mequipment_tbl`;
CREATE TABLE `mequipment_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `equipment_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `size` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号规格',
  `process_parameters` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工艺参数',
  `amount` int(11) NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3546 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主要设备清单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for message_to_user
-- ----------------------------
DROP TABLE IF EXISTS `message_to_user`;
CREATE TABLE `message_to_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `message_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '消息推送的人',
  `revice_flag` tinyint(4) NULL DEFAULT 0 COMMENT '是否接收推送过0未接收 1已接收',
  `read_flag` tinyint(4) NULL DEFAULT 0 COMMENT '是否已读0未读 1已读',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '读内容的时间',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态0 正常  1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '资讯所属用户userId',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `descr` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '正文',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `send_flag` tinyint(4) NULL DEFAULT 0 COMMENT '发送状态0未发送 1已推送',
  `send_total` int(11) NULL DEFAULT NULL COMMENT '推送的总人数',
  `files` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `create_utp` int(4) NULL DEFAULT NULL COMMENT '创建者的身份',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状0正常 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for monitor_tbl
-- ----------------------------
DROP TABLE IF EXISTS `monitor_tbl`;
CREATE TABLE `monitor_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `psw` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '企业',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `danger_level` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descr` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accident_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duty_people` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `thumbnail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '摄像头' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for notice_tbl
-- ----------------------------
DROP TABLE IF EXISTS `notice_tbl`;
CREATE TABLE `notice_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `source` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '来源',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '1' COMMENT '1 公告 2安全之家',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '公告内容',
  `time` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序 倒序',
  `type2` int(11) NULL DEFAULT NULL COMMENT '21 法规-危化  22 工贸 24 消防 25 港口\r\n26 应急 27职位\r\n31 标准规范-危化  32 工贸 34 消防 35 港口\r\n36 应急 37职位\r\n1 怎样当好安全员 3一周事故警示 4监管动态 5安全常识\r\n10 法律',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  CONSTRAINT `notice_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 769 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '公告' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for officials_tbl
-- ----------------------------
DROP TABLE IF EXISTS `officials_tbl`;
CREATE TABLE `officials_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执法部门',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执法人员姓名',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `uid` int(11) NULL DEFAULT NULL COMMENT '所属 区县，市，省',
  `time` datetime NULL DEFAULT NULL,
  `flag` int(1) NULL DEFAULT NULL COMMENT '4村级 3镇级 6区级 7市级 8省级',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '执法人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pequipment_lib_tbl
-- ----------------------------
DROP TABLE IF EXISTS `pequipment_lib_tbl`;
CREATE TABLE `pequipment_lib_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level1` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `level2` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '劳动防护用品配备 分类库' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pequipment_tbl
-- ----------------------------
DROP TABLE IF EXISTS `pequipment_tbl`;
CREATE TABLE `pequipment_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `lib_id` int(11) NULL DEFAULT NULL COMMENT '劳保用品类型 1一般劳动防护用品 2特种劳动防护用品 3其他',
  `work` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '工种',
  `cycle` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '发放日期',
  `next_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '下次发放日期',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '数量',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `lib_id`(`lib_id`) USING BTREE,
  CONSTRAINT `pequipment_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 756 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '劳动防护用品配备' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ping_tbl
-- ----------------------------
DROP TABLE IF EXISTS `ping_tbl`;
CREATE TABLE `ping_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_tbl
-- ----------------------------
DROP TABLE IF EXISTS `product_tbl`;
CREATE TABLE `product_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称 0无 1 有',
  `efficiency` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产能力 ',
  `lastyear_production` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上年度产量',
  `maximum_storage` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最大储存量',
  `storage_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '储存地点',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 662 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主要产品' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for production_process_diagram_tbl
-- ----------------------------
DROP TABLE IF EXISTS `production_process_diagram_tbl`;
CREATE TABLE `production_process_diagram_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目',
  `description` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制程描述',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '材料\r\n',
  `material_specifications` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '材料规格\r\n',
  `personnel_requirements` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员要求\r\n\r\n',
  `inspection_standards` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查标准\r\n',
  `operating_rules` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作规定\r\n',
  `equipment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备/工具\r\n',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 291 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '生产工艺流程' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for push_mess_tbl
-- ----------------------------
DROP TABLE IF EXISTS `push_mess_tbl`;
CREATE TABLE `push_mess_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推送信息编号',
  `district_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地区编号',
  `notice_id` int(11) NOT NULL COMMENT '公告编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台公告推送表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for push_message_tbl
-- ----------------------------
DROP TABLE IF EXISTS `push_message_tbl`;
CREATE TABLE `push_message_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `del` int(1) NULL DEFAULT 0 COMMENT '1删除',
  `ctime` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `uid` int(11) NULL DEFAULT NULL COMMENT '发送人',
  `type` int(1) NULL DEFAULT NULL COMMENT '1 发送给企业 2发送给村 3发送给镇 4 区  5行业',
  `file_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本内容（暂未用到）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '推送消息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for push_message_user_tbl
-- ----------------------------
DROP TABLE IF EXISTS `push_message_user_tbl`;
CREATE TABLE `push_message_user_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `uid` int(11) NULL DEFAULT NULL COMMENT '推送人',
  `tid` int(11) NULL DEFAULT NULL COMMENT '接收人',
  `is_read` int(1) NULL DEFAULT 0 COMMENT '0 已看过 1未看过',
  `u_del` int(1) NULL DEFAULT 0 COMMENT '1 发送人删除',
  `t_del` int(1) NULL DEFAULT 0 COMMENT '1 接收人删除',
  `push_id` int(11) NULL DEFAULT NULL COMMENT '推送编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `push_id`(`push_id`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息推送详细表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qcc_data_tbl
-- ----------------------------
DROP TABLE IF EXISTS `qcc_data_tbl`;
CREATE TABLE `qcc_data_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '数据',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2961 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企查查数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for regulation_tbl
-- ----------------------------
DROP TABLE IF EXISTS `regulation_tbl`;
CREATE TABLE `regulation_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `management_system` int(1) NULL DEFAULT NULL COMMENT '管理制度(0无1有） 废弃',
  `mis_inforce` int(1) NULL DEFAULT NULL COMMENT '是否在有效期 废弃',
  `regulations` int(1) NULL DEFAULT NULL COMMENT '操作规程 废弃',
  `ris_inforce` int(1) NULL DEFAULT NULL COMMENT '是否在有效期（操作规程） 废弃',
  `safety` int(1) NULL DEFAULT NULL COMMENT '安全生产责任 废弃',
  `sis_inforce` int(1) NULL DEFAULT NULL COMMENT '在有效期（安全生产责任制） 废弃',
  `implementation` int(1) NULL DEFAULT NULL COMMENT '责任落实情况 废弃',
  `contingency` int(1) NULL DEFAULT NULL COMMENT '应急预案 废弃',
  `process_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `cis_inforce` int(1) NULL DEFAULT NULL COMMENT '在有效期(应急预案） 废弃',
  `cis_danger` int(1) NULL DEFAULT NULL COMMENT '是否构成重大危险源',
  `danger_lvl` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '重大危险源等级',
  `danger_record_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '重大危险源备案日期',
  `danger_end_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '重大危险源有效期',
  `danger_material` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '构成重大危险源的主要物质\r\n物质名称/理论计算量，物质名称/理论计算量，物质名称/理论计算量',
  `process` int(1) NULL DEFAULT NULL COMMENT '重点监管的危险化工工艺',
  `process_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '工艺名称',
  `process_control` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '化工工艺 是否满足自控要求',
  `process_record_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '危险化工工艺备案日期',
  `chemic` int(1) NULL DEFAULT NULL COMMENT '重点监管的危险化学品',
  `chemic_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '化学品物质名称',
  `chemic_control` int(1) NULL DEFAULT NULL COMMENT '化学品 是否满足自控要求',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `regulation_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1172 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '企业规章制度' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for safety_facilities_tbl
-- ----------------------------
DROP TABLE IF EXISTS `safety_facilities_tbl`;
CREATE TABLE `safety_facilities_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `size` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号规格',
  `process_parameters` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工艺参数',
  `amount` int(11) NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1279 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全设施登记台账' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for security_tbl
-- ----------------------------
DROP TABLE IF EXISTS `security_tbl`;
CREATE TABLE `security_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '类别 ',
  `certificate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '证书编号',
  `forensic_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '取证时间',
  `audit_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '审核时间（最近）',
  `review_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '复审时间（下次）',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  CONSTRAINT `security_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '安全管理人员' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sensors_lib
-- ----------------------------
DROP TABLE IF EXISTS `sensors_lib`;
CREATE TABLE `sensors_lib`  (
  `code` int(4) NOT NULL COMMENT '主键code',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传感器名称',
  `type` int(4) NULL DEFAULT NULL COMMENT '传感器放置类型1 河道  2 高危作业 3 重大危险源',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sequipment_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sequipment_tbl`;
CREATE TABLE `sequipment_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `se_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '特种设备名称',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  `file_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备档案号',
  `amount` int(11) NULL DEFAULT NULL COMMENT '数量',
  `detection_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检测时间',
  `expiration_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '到期时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9195 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '特种设备' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sperson_tbl
-- ----------------------------
DROP TABLE IF EXISTS `sperson_tbl`;
CREATE TABLE `sperson_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `flag` int(1) NULL DEFAULT NULL COMMENT '1安全管理人员\r\n2危险化学品安全管理人员\r\n3特种作业人员',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '工种',
  `certificate_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '证书编号',
  `forensic_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '取证时间',
  `audit_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '审核时间（最近）',
  `review_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '复审时间（下次）',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`user_id`) USING BTREE,
  CONSTRAINT `sperson_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3268 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '持证上岗 安全管理人员，危险化学品安全管理人员，特种作业人员，特种设备操作人员' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for standard_tbl
-- ----------------------------
DROP TABLE IF EXISTS `standard_tbl`;
CREATE TABLE `standard_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '证书类型\r\n1.危化品经营许可证\r\n2.危化品生产许可证\r\n3.危化品使用许可证\r\n4.危化品登记证\r\n5.易制毒化学品备案\r\n6.易制爆化学品备案\r\n7.标准化二级证书\r\n8.标准化三级证书\r\n9.港口经营许可证\r\n10.成品油零售经营批准证书\r\n11.小微企业标准化证书',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片地址',
  `certificate_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '证书编号',
  `time_get` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '取证时间',
  `time_exp` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 149 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '安全生产标准化证书' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_accident_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_accident_tbl`;
CREATE TABLE `t_accident_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所在部门',
  `time` datetime NULL DEFAULT NULL COMMENT '事故时间',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '事故类别',
  `person_death` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '死亡人数',
  `person_miss` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '失踪人数',
  `person_injured` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '受伤人数',
  `economic_loss` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '经济损失',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '事故报告和处理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_check_admin_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_check_admin_tbl`;
CREATE TABLE `t_check_admin_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL COMMENT '1 责令限期整改指令书\r\n2 整改复查意见书\r\n3 行政处罚告知书\r\n4 行政（当场）处罚决定书（单位）\r\n5 行政（当场）处罚决定书（个人）\r\n6 行政处罚决定书（单位）\r\n7 行政处罚决定书（个人）\r\n8 现场检查记录\r\n',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del` int(1) NULL DEFAULT 0,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '自查对应行政执法' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_check_document_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_check_document_tbl`;
CREATE TABLE `t_check_document_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) NULL DEFAULT NULL COMMENT '检查表id',
  `copy` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '文书详情',
  `flag2` int(1) NULL DEFAULT 2 COMMENT '1自查 2执法3第三方4检查',
  `flag` int(1) NULL DEFAULT NULL COMMENT '1 责令限期整改指令书\r\n2 整改复查意见书\r\n3 行政处罚告知书\r\n4 行政（当场）处罚决定书（单位）\r\n5 行政（当场）处罚决定书（个人）\r\n6 行政处罚决定书（单位）\r\n7 行政处罚决定书（个人）\r\n8 现场检查记录',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '企业id',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `check_id`(`check_id`) USING BTREE,
  INDEX `create_user`(`create_user`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `t_check_document_tbl_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `t_check_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_check_document_tbl_ibfk_2` FOREIGN KEY (`create_user`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_check_document_tbl_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1785 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '行政检查文书' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_check_item_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_check_item_tbl`;
CREATE TABLE `t_check_item_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查标准详情',
  `level_id` int(11) NULL DEFAULT NULL COMMENT '检查分类',
  `levels` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `reference` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查参照',
  `part_id` int(11) NULL DEFAULT NULL COMMENT '装置或设施id',
  `check_id` int(11) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL COMMENT '1 合格 2不合格 3已复查',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '不合格描述',
  `files` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查图片',
  `suggest` int(1) NULL DEFAULT NULL COMMENT '1 立即整改 2限期整改',
  `deadline` datetime NULL DEFAULT NULL COMMENT '限期整改期限',
  `plan_time` datetime NULL DEFAULT NULL COMMENT '预计复查时间',
  `recheck_time` datetime NULL DEFAULT NULL COMMENT '实际复查时间',
  `recheck_file` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '复查图片s',
  `recheck_memo` varchar(5000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '复查描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `check_id`(`check_id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 264948 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '检查 检查项' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_check_part_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_check_part_tbl`;
CREATE TABLE `t_check_part_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) NULL DEFAULT NULL,
  `levels` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查分类s',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部位或装置名称',
  `part_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门照片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12065 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '装置或设施' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_check_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_check_tbl`;
CREATE TABLE `t_check_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flag` int(1) NULL DEFAULT NULL COMMENT '1 企业自查 2行政执法 3第三方排查',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查标题',
  `depart` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '被检查部门',
  `depart_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门照片',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '企业id',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `model_id` int(11) NULL DEFAULT NULL COMMENT '模板id',
  `type` int(1) NULL DEFAULT NULL COMMENT '1 日常 2定期 3临时',
  `industry_id` int(11) NULL DEFAULT NULL COMMENT '检查行业id',
  `industry_type` int(1) NULL DEFAULT NULL COMMENT '1 基础 2现场 3高危',
  `expect_time` datetime NULL DEFAULT NULL COMMENT '预计检查时间',
  `real_time` datetime NULL DEFAULT NULL COMMENT '实际检查时间',
  `cheker` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查人',
  `contact` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查人联系方式',
  `dapart_contact` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '被检查部门负责人',
  `status` int(1) NULL DEFAULT NULL COMMENT '1 未检查 2 已检查',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `check_company` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查单位',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `model_id`(`model_id`) USING BTREE,
  INDEX `industry_id`(`industry_id`) USING BTREE,
  CONSTRAINT `t_check_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_check_tbl_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `t_model_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_check_tbl_ibfk_3` FOREIGN KEY (`industry_id`) REFERENCES `t_industry_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11158 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '自查检查总表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_clean_warn_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_clean_warn_setting`;
CREATE TABLE `t_clean_warn_setting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '涓婚?id',
  `max_times` int(4) NULL DEFAULT NULL COMMENT '???娓???堕?',
  `creator` int(11) NULL DEFAULT NULL COMMENT '??缓?',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '??缓?堕?',
  `update_name` int(11) NULL DEFAULT NULL COMMENT '???淇??浜',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '???淇???堕?',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `device_sn` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备序列号',
  `sensor_sn` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器通道号',
  `collect` float NOT NULL COMMENT '采集结果',
  `colltime` datetime NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_TCOLLECTION_COLLECTIONTIME`(`colltime`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9494 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_company_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_company_tbl`;
CREATE TABLE `t_company_tbl`  (
  `user_id` int(11) NOT NULL,
  `industry_id` int(11) NULL DEFAULT NULL COMMENT '企业行业id',
  `industry1` int(11) NULL DEFAULT NULL COMMENT '基础所属',
  `industry2` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '现场检查所属行业',
  `industry3` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '5大高危所属ids',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '企业 自查 5大高危 现场 行业信息记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_contingency_plan_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_contingency_plan_tbl`;
CREATE TABLE `t_contingency_plan_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '应急预案名称',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '颁布时间',
  `filing_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备案时间',
  `active_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备案有效期',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档url',
  `djb_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '应急预案备案登记表',
  `djb_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '应急预案' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_sn` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备序列号(唯一索引）',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '单位名称',
  `site` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '放置场所',
  `phonename` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '4G号码',
  `sensors` int(11) NULL DEFAULT NULL COMMENT '附带传感器数量',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_name` int(11) NULL DEFAULT NULL COMMENT '最后修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `last_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后在线时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '在线状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq__device_idx`(`device_sn`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_drill_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_drill_tbl`;
CREATE TABLE `t_drill_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '主要内容',
  `drill_time` datetime NULL DEFAULT NULL COMMENT '演练时间',
  `doc_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '应急预案演练' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_industry_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_industry_tbl`;
CREATE TABLE `t_industry_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL COMMENT '1 基础\r\n2 现场\r\n3 五大高危行业',
  `memo` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '企业自查行业\r\n基础-一般工贸企业\r\n基础-危化品企业\r\n现场-行业' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_item_serious_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_item_serious_tbl`;
CREATE TABLE `t_item_serious_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `levelid` int(11) NULL DEFAULT NULL,
  `keywords` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查项重点匹配关键词表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_item_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_item_tbl`;
CREATE TABLE `t_item_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '自查标准项具体描述',
  `level_id` int(11) NULL DEFAULT NULL,
  `reference` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '部分参考依据',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_2`(`level_id`) USING BTREE,
  CONSTRAINT `t_item_tbl_ibfk_1` FOREIGN KEY (`level_id`) REFERENCES `t_level_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6171 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '检查项目' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_level_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_level_tbl`;
CREATE TABLE `t_level_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level1` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'Ⅱ级隐患自查标准',
  `level2` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'Ⅲ级隐患自查标准',
  `level3` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `industry_id` int(11) NULL DEFAULT NULL COMMENT '所属行业',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_1`(`industry_id`) USING BTREE,
  CONSTRAINT `t_level_tbl_ibfk_1` FOREIGN KEY (`industry_id`) REFERENCES `t_industry_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3232 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '自查级别分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_model_part_item_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_model_part_item_tbl`;
CREATE TABLE `t_model_part_item_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `level_id` int(11) NULL DEFAULT NULL,
  `reference` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `part_id` int(11) NULL DEFAULT NULL COMMENT '装置或设施id',
  `model_id` int(11) NULL DEFAULT NULL COMMENT '模板id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `part_id`(`part_id`) USING BTREE,
  INDEX `model_id`(`model_id`) USING BTREE,
  CONSTRAINT `t_model_part_item_tbl_ibfk_1` FOREIGN KEY (`part_id`) REFERENCES `t_model_part_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_model_part_item_tbl_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `t_model_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '模板检查 检查项 废弃' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_model_part_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_model_part_tbl`;
CREATE TABLE `t_model_part_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门 或装置',
  `model_id` int(11) NULL DEFAULT NULL COMMENT '模板id',
  `levels` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查分类s',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `model_id`(`model_id`) USING BTREE,
  CONSTRAINT `t_model_part_tbl_ibfk_1` FOREIGN KEY (`model_id`) REFERENCES `t_model_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1421 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '检查计划模板 模块表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_model_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_model_tbl`;
CREATE TABLE `t_model_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查计划名',
  `user_id` int(11) NULL DEFAULT NULL,
  `flag` int(1) NULL DEFAULT 1 COMMENT '1 自查 3第三方',
  `type` int(1) NULL DEFAULT NULL COMMENT '1 日常 2定期 3临时',
  `part` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被检查部门',
  `industry_id` int(11) NULL DEFAULT NULL COMMENT '检查行业id',
  `industry_type` int(1) NOT NULL COMMENT '1 基础\\r\\n2 现场\\r\\n3 五大高危行业',
  `cycle` int(4) NULL DEFAULT NULL COMMENT '定期周期天数',
  `next_time` datetime NULL DEFAULT NULL COMMENT '下次创建时间',
  `next_check_time` datetime NULL DEFAULT NULL COMMENT '下次预期检查时间',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `open` int(1) NULL DEFAULT 0 COMMENT '1 自动生成下一次的检查表',
  `use_time` datetime NULL DEFAULT NULL COMMENT '最近使用时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '模板创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `industry_id`(`industry_id`) USING BTREE,
  CONSTRAINT `t_model_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_model_tbl_ibfk_2` FOREIGN KEY (`industry_id`) REFERENCES `t_industry_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1351 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '检查计划模板表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_recheck_item_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_recheck_item_tbl`;
CREATE TABLE `t_recheck_item_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recheck_id` int(11) NULL DEFAULT NULL,
  `check_item_id` int(11) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL COMMENT '2 未整改 3已整改',
  `file` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  `deadline` datetime NULL DEFAULT NULL COMMENT '整改期限',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '复查描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `recheck_id`(`recheck_id`) USING BTREE,
  INDEX `check_item_id`(`check_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4180 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '复查结果' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_recheck_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_recheck_tbl`;
CREATE TABLE `t_recheck_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '企业id',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `status` int(1) NULL DEFAULT NULL COMMENT '1 未全部整改 2全部整改',
  `create_time` datetime NULL DEFAULT NULL COMMENT '复查时间',
  `next_time` datetime NULL DEFAULT NULL COMMENT '未合格项 限期检查时间',
  `checker` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查人员',
  `check_company` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '检查单位',
  `dapart_contact` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '被检部门负责人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `check_id`(`check_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 690 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '复查意见表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_rectification_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_rectification_tbl`;
CREATE TABLE `t_rectification_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) NULL DEFAULT NULL,
  `item1` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '立即整改项',
  `item2` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '限期整改项',
  `item3` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '严重隐患',
  `deadline` datetime NULL DEFAULT NULL COMMENT '限期时间',
  `plan_time` datetime NULL DEFAULT NULL COMMENT '计划复查时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '企业id',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '生成时间',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `check_id`(`check_id`) USING BTREE,
  CONSTRAINT `t_rectification_tbl_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `t_check_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1034 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '检查结果整改意见表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sensors
-- ----------------------------
DROP TABLE IF EXISTS `t_sensors`;
CREATE TABLE `t_sensors`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '传感器ID',
  `sensor_sn` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器通道号(平台增加时产生-同一单位唯一)',
  `sensorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传感器名称',
  `device_sn` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备序列号',
  `site` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '放置场所',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '单位id',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_name` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `sensor_type` int(4) NULL DEFAULT NULL COMMENT '传感器类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_TSENSORS_DE_SE`(`sensor_sn`, `device_sn`, `sensor_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_tindustry_tbl
-- ----------------------------
DROP TABLE IF EXISTS `t_tindustry_tbl`;
CREATE TABLE `t_tindustry_tbl`  (
  `id` int(11) NOT NULL COMMENT '企业行业id',
  `in1` int(11) NULL DEFAULT NULL COMMENT '对应基础资料id',
  `in2a` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现场多选1',
  `in2o` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现场必填',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业所属行业 与 检查项目匹配' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_warnmsg
-- ----------------------------
DROP TABLE IF EXISTS `t_warnmsg`;
CREATE TABLE `t_warnmsg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报警ID',
  `device_sn` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备序列号',
  `sensor_sn` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器通道号',
  `sensor_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '传感器类型',
  `warn` tinyint(4) NOT NULL COMMENT '报警标志:1-低于下限预警 2-高于上限预警 3-低于下限报警 4-高于上限报警',
  `warntime` datetime NOT NULL COMMENT '报警上传时间',
  `cleantime` datetime NULL DEFAULT NULL COMMENT '清除时间',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '清除操作用户',
  `creator` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_name` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5063 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_waterparamter
-- ----------------------------
DROP TABLE IF EXISTS `t_waterparamter`;
CREATE TABLE `t_waterparamter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '水质参数ID',
  `para` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '水质参数',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数名称',
  `unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位',
  `min_val` double NULL DEFAULT NULL COMMENT '最小值',
  `max_val` double NULL DEFAULT NULL COMMENT '最大值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table2_tbl
-- ----------------------------
DROP TABLE IF EXISTS `table2_tbl`;
CREATE TABLE `table2_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件名',
  `is_type` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `type` int(11) NULL DEFAULT NULL COMMENT '=1 word =2 excel',
  `create_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 742 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table3_tbl
-- ----------------------------
DROP TABLE IF EXISTS `table3_tbl`;
CREATE TABLE `table3_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_type` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '=1 word  =2excel',
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 513 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table4_tbl
-- ----------------------------
DROP TABLE IF EXISTS `table4_tbl`;
CREATE TABLE `table4_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '=1 word  =2excel',
  `is_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table5_tbl
-- ----------------------------
DROP TABLE IF EXISTS `table5_tbl`;
CREATE TABLE `table5_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_type` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `type` int(11) NULL DEFAULT NULL COMMENT '0 excel  1 word',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '隐患排查治疗标准文件库' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table6_tbl
-- ----------------------------
DROP TABLE IF EXISTS `table6_tbl`;
CREATE TABLE `table6_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `type` int(11) NULL DEFAULT NULL COMMENT '0 excel  1 word ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '较大危险因素辨识与防范指导手册' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_tbl
-- ----------------------------
DROP TABLE IF EXISTS `table_tbl`;
CREATE TABLE `table_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件名',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `type` int(11) NULL DEFAULT NULL COMMENT '=1 word     =2 excel',
  `is_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for town_tbl
-- ----------------------------
DROP TABLE IF EXISTS `town_tbl`;
CREATE TABLE `town_tbl`  (
  `user_id` int(11) NOT NULL,
  `accountNum` int(11) NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '镇名',
  `region_id` int(11) NULL DEFAULT NULL COMMENT '地区',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `district_id` int(11) NULL DEFAULT NULL COMMENT '区id',
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `town_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '城镇信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trade_company_tbl
-- ----------------------------
DROP TABLE IF EXISTS `trade_company_tbl`;
CREATE TABLE `trade_company_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户编号/行业编号',
  `tid` int(11) NULL DEFAULT NULL COMMENT '企业编号',
  `iskey` int(1) NULL DEFAULT 0 COMMENT '是否重点',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1267 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行业关联企业' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trade_tbl
-- ----------------------------
DROP TABLE IF EXISTS `trade_tbl`;
CREATE TABLE `trade_tbl`  (
  `user_id` int(11) NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业名称',
  `region_id` int(11) NULL DEFAULT NULL COMMENT '地区',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `trade_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行业表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `group_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群组名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '群组创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `group_type` int(4) NULL DEFAULT 0 COMMENT '分组类型0 企业  1 区乡镇',
  `group_village` int(11) NULL DEFAULT NULL COMMENT '具体的某个乡镇',
  `group_village_type` int(4) NULL DEFAULT NULL COMMENT '乡镇类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_group_list
-- ----------------------------
DROP TABLE IF EXISTS `user_group_list`;
CREATE TABLE `user_group_list`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属群组id',
  `group_user_id` int(11) NULL DEFAULT NULL COMMENT '群组内人员的用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_tbl
-- ----------------------------
DROP TABLE IF EXISTS `user_tbl`;
CREATE TABLE `user_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `psw` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_type` int(11) NULL DEFAULT NULL COMMENT '1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省',
  `is_freeze` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0正常 冻结',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '1 删除',
  `longitude` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35351 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for village_tbl
-- ----------------------------
DROP TABLE IF EXISTS `village_tbl`;
CREATE TABLE `village_tbl`  (
  `user_id` int(11) NOT NULL,
  `accountNum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '村名',
  `region_id` int(11) NULL DEFAULT NULL COMMENT '地区id',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `town_id` int(11) NULL DEFAULT NULL COMMENT '所属镇',
  `states` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `region_id`(`region_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '村信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zzjg_company_tbl
-- ----------------------------
DROP TABLE IF EXISTS `zzjg_company_tbl`;
CREATE TABLE `zzjg_company_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `utime` datetime NULL DEFAULT NULL COMMENT '最新跟新时间',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名',
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 239 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织架构 公司' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zzjg_department_tbl
-- ----------------------------
DROP TABLE IF EXISTS `zzjg_department_tbl`;
CREATE TABLE `zzjg_department_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `utime` datetime NULL DEFAULT NULL COMMENT '最新跟新时间',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名',
  `cid` int(11) NULL DEFAULT NULL COMMENT '公司编号',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `level` int(1) NULL DEFAULT NULL COMMENT '部门层级',
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1377 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织架构 部门' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zzjg_personnel_tbl
-- ----------------------------
DROP TABLE IF EXISTS `zzjg_personnel_tbl`;
CREATE TABLE `zzjg_personnel_tbl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `utime` datetime NULL DEFAULT NULL COMMENT '最新跟新时间',
  `del` int(1) NULL DEFAULT 0 COMMENT '1 删除',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `cid` int(11) NULL DEFAULT NULL COMMENT '所属公司',
  `dpid` int(11) NULL DEFAULT NULL COMMENT '部门',
  `did` int(11) NULL DEFAULT NULL COMMENT '班组',
  `uid` int(11) NULL DEFAULT NULL,
  `position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 800 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织架构 员工' ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for
-- ----------------------------
DROP VIEW IF EXISTS `company_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `company_view` AS select 
`a`.`user_id` AS `user_id`,`a`.`is_key` AS `is_key`,
`a`.`village_id` AS `village_id`,
`a`.`name` AS `name`,
`a`.`reg_type` AS `reg_type`,
`a`.`region_id` AS `region_id`,
`a`.`address` AS `address`,`a`.`legal` AS `legal`,
`a`.`legal_contact` AS `legal_contact`,
`a`.`capital` AS `capital`,
`a`.`establish` AS `establish`,
`a`.`business` AS `business`,
`a`.`scope` AS `scope`,
`a`.`license` AS `license`,
`a`.`reg_region` AS `reg_region`,
`a`.`authority` AS `authority`,
`a`.`economic` AS `economic`,
`a`.`fixed` AS `fixed`,
`a`.`staff` AS `staff`,
`a`.`income` AS `income`,
`a`.`industry` AS `industry`,
`a`.`industry2` AS `industry2`,
`a`.`scale` AS `scale`,
`a`.`danger` AS `danger`,
`a`.`dangers` AS `dangers`,
`a`.`hazard` AS `hazard`,
`a`.`dlevel` AS `dlevel`,
`a`.`charge` AS `charge`,
`a`.`charge_contact` AS `charge_contact`,
`a`.`safety` AS `safety`,
`a`.`safety_contact` AS `safety_contact`,
`a`.`safety_m` AS `safety_m`,
`a`.`safety_m_contact` AS `safety_m_contact`,
`a`.`duty` AS `duty`,`a`.`postage` AS `postage`,
`a`.`email` AS `email`,`a`.`pic1` AS `pic1`,
`a`.`pic2` AS `pic2`,
(select `user_tbl`.`is_freeze` from `user_tbl` where (`user_tbl`.`id` = `a`.`user_id`)) AS `is_freeze`,
(select `user_tbl`.`is_del` from `user_tbl` where (`user_tbl`.`id` = `a`.`user_id`)) AS `is_del`,
b.`name` AS `village_name`,
c.name as town_name,
c.`user_id` AS `town_id`,
d.name district_name,
d.user_id district_id
from `company_tbl` `a`
left JOIN village_tbl b on a.village_id = b.user_id
left join town_tbl c on b.town_id = c.user_id
left join district_tbl d on c.district_id = d.user_id ;

-- ----------------------------
-- View structure for global_region_view
-- ----------------------------
DROP VIEW IF EXISTS `global_region_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `global_region_view` AS select `a`.`id` AS `id`,(case when (`a`.`region_type` = '3') then concat(`c`.`region_name`,`b`.`region_name`,`a`.`region_name`) when (`a`.`region_type` = '2') then concat(`b`.`region_name`,`a`.`region_name`) else `a`.`region_name` end) AS `region_name`,`a`.`region_type` AS `region_type`,`a`.`city_code` AS `city_code`,(case when (`a`.`region_type` = '3') then `c`.`id` when (`a`.`region_type` = '2') then `b`.`id` else `a`.`id` end) AS `province_id`,(case when (`a`.`region_type` = '3') then `b`.`id` when (`a`.`region_type` = '2') then `a`.`id` else NULL end) AS `city_id` from ((`global_region_tbl` `a` left join `global_region_tbl` `b` on((`a`.`parent_id` = `b`.`id`))) left join `global_region_tbl` `c` on((`b`.`parent_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for recheck_yj_view
-- ----------------------------
DROP VIEW IF EXISTS `recheck_yj_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `recheck_yj_view` AS select `t1`.`id` AS `id`,(select count(0) from `t_recheck_tbl` where (`t_recheck_tbl`.`check_id` = `t1`.`check_id`)) AS `c`,`t2`.`user_id` AS `user_id` from (`t_rectification_tbl` `t1` left join `t_check_tbl` `t2` on((`t1`.`check_id` = `t2`.`id`))) where ((`t2`.`flag` = 2) and (`t1`.`del` = 0)) ;

-- ----------------------------
-- View structure for t_check_item_view
-- ----------------------------
DROP VIEW IF EXISTS `t_check_item_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `t_check_item_view` AS select `t1`.`id` AS `id`,`t1`.`levels` AS `levels`,`t2`.`real_time` AS `real_time`,`t2`.`flag` AS `flag`,`t2`.`type` AS `type`,`t2`.`user_id` AS `user_id`,`t2`.`industry_type` AS `industry_type`,`t1`.`part_id` AS `part_id`,`t1`.`level_id` AS `level_id`,`t2`.`depart` AS `depart`,`t2`.`check_company` AS `check_company`,`tcp`.`name` AS `part_name`,`tcp`.`part_img` AS `part_img`,`t1`.`status` AS `status`,`t1`.`check_id` AS `check_id`,`t1`.`content` AS `content`,`t1`.`deadline` AS `deadline`,`t1`.`files` AS `files`,`t1`.`memo` AS `memo`,`t1`.`plan_time` AS `plan_time`,`t1`.`recheck_file` AS `recheck_file`,`t1`.`recheck_time` AS `recheck_time`,`t1`.`recheck_memo` AS `recheck_memo`,`t1`.`reference` AS `reference`,`t1`.`suggest` AS `suggest`,`t2`.`title` AS `title`,`t4`.`village_id` AS `village_id`,`t4`.`name` AS `name`,`t4`.`village_name` AS `village_name`,`t4`.`town_name` AS `town_name`,`t4`.`town_id` AS `town_id`,`t4`.`district_name` AS `district_name`,`t4`.`district_id` AS `district_id`,if(find_in_set(`t1`.`id`,`t3`.`item3`),1,0) AS `d`,(select `t_company_tbl`.`industry_id` from `t_company_tbl` where (`t_company_tbl`.`user_id` = `t2`.`user_id`)) AS `industry_id` from ((((`t_check_item_tbl` `t1` left join `t_check_tbl` `t2` on((`t1`.`check_id` = `t2`.`id`))) left join `t_check_part_tbl` `tcp` on((`t1`.`part_id` = `tcp`.`id`))) left join `t_rectification_tbl` `t3` on((`t1`.`check_id` = `t3`.`check_id`))) left join `company_view` `t4` on((`t2`.`user_id` = `t4`.`user_id`))) where ((`t2`.`del` = 0) and (`t2`.`status` = 2) and (`t1`.`status` > 1) and (`t4`.`is_del` = '0')) ;

-- ----------------------------
-- View structure for t_check_item_view2
-- ----------------------------
DROP VIEW IF EXISTS `t_check_item_view2`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `t_check_item_view2` AS select `t1`.`id` AS `id`,`t1`.`levels` AS `levels`,`t2`.`real_time` AS `real_time`,`t2`.`industry_type` AS `industry_type`,`t2`.`flag` AS `flag`,`t2`.`type` AS `type`,`t2`.`user_id` AS `user_id`,`t1`.`status` AS `status`,`t1`.`check_id` AS `check_id`,`b`.`user_id` AS `village_id`,`c`.`user_id` AS `town_id`,`d`.`user_id` AS `district_id`,if(find_in_set(`t1`.`id`,`t3`.`item3`),1,0) AS `d`,(select `t_company_tbl`.`industry_id` from `t_company_tbl` where (`t_company_tbl`.`user_id` = `t2`.`user_id`)) AS `industry_id` from ((((((((`t_check_item_tbl` `t1` left join `t_check_tbl` `t2` on((`t1`.`check_id` = `t2`.`id`))) left join `t_check_part_tbl` `tcp` on((`t1`.`part_id` = `tcp`.`id`))) left join `t_rectification_tbl` `t3` on((`t1`.`check_id` = `t3`.`check_id`))) left join `user_tbl` `e` on((`t2`.`user_id` = `e`.`id`))) left join `company_tbl` `a` on((`t2`.`user_id` = `a`.`user_id`))) left join `village_tbl` `b` on((`a`.`village_id` = `b`.`user_id`))) left join `town_tbl` `c` on((`b`.`town_id` = `c`.`user_id`))) left join `district_tbl` `d` on((`c`.`district_id` = `d`.`user_id`))) where ((`t2`.`del` = 0) and (`t2`.`status` = 2) and (`t1`.`status` > 1) and (`e`.`is_del` = '0')) ;

-- ----------------------------
-- Function structure for company_scale_int
-- ----------------------------
DROP FUNCTION IF EXISTS `company_scale_int`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `company_scale_int`(scale varchar(20)) RETURNS int(1)
BEGIN
	DECLARE x int(1) DEFAULT 4;
 if(scale = '规上')
	then set x = 0;
end if;
 if(scale = '中小')
	then set x = 1;
end if;
 if(scale = '小微')
	then set x = 2;
end if;
	RETURN x;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for GPS_DOUBLE_CHANGE
-- ----------------------------
DROP FUNCTION IF EXISTS `GPS_DOUBLE_CHANGE`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `GPS_DOUBLE_CHANGE`(coordinates double) RETURNS double
BEGIN
	#Routine body goes here...
	DECLARE x DOUBLE(50,6) DEFAULT 0.000001;
  DECLARE y VARCHAR(60) DEFAULT "";
  IF(coordinates IS NOT NULL) THEN
    SET y = SUBSTRING_INDEX(CAST(coordinates AS CHAR),".",-1);
    IF(SUBSTRING(y,6) = 0) THEN
      SET x = coordinates + x;
    ELSE 
      SET x = coordinates;
    END IF;
  END IF;
	RETURN x;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
