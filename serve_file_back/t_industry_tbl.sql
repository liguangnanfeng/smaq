/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.138_3306
Source Server Version : 50528
Source Host           : 192.168.1.138:3306
Source Database       : smaq

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-06-29 11:06:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_industry_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_industry_tbl`;
CREATE TABLE `t_industry_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1 基础\r\n2 现场\r\n3 五大高危行业',
  `memo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='企业自查行业\r\n基础-一般工贸企业\r\n基础-危化品企业\r\n现场-行业';

-- ----------------------------
-- Records of t_industry_tbl
-- ----------------------------
INSERT INTO `t_industry_tbl` VALUES ('1', '工贸企业', '1', null);
INSERT INTO `t_industry_tbl` VALUES ('2', '化工企业((危险化学品)生产、经营、使用、加油站', '1', null);
INSERT INTO `t_industry_tbl` VALUES ('3', '非化工液氨使用企业', '3', '适用于使用液氨作为原料（或辅料）从事生产活动且有液氨储罐的非化工企业');
INSERT INTO `t_industry_tbl` VALUES ('4', '粉尘涉爆企业', '3', null);
INSERT INTO `t_industry_tbl` VALUES ('5', '高温熔融金属现场运输', '3', null);
INSERT INTO `t_industry_tbl` VALUES ('6', '工贸行业有限空间安全作业', '3', null);
INSERT INTO `t_industry_tbl` VALUES ('7', '冶金企业煤气', '3', null);
INSERT INTO `t_industry_tbl` VALUES ('8', '纺织行业', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('9', '非煤矿山行业', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('10', '化工企业', '2', '适用于：使用危化品从事生产的化工企业、化学制药企业及一般化工企业');
INSERT INTO `t_industry_tbl` VALUES ('11', '机械行业', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('12', '加油站', '2', '适用于：汽油加油站。');
INSERT INTO `t_industry_tbl` VALUES ('13', '建材', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('14', '有色金属', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('15', '印刷包装', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('16', '冶金', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('17', '危化品生产企业', '2', '适用于：危险化学品生产企业');
INSERT INTO `t_industry_tbl` VALUES ('18', '危化品经营', '2', '适用于：分装、充装经营企业，仓储经营企业，除加油站外带固定储存设施的经营企业。');
INSERT INTO `t_industry_tbl` VALUES ('20', '市场事故', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('21', '食品和饮料', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('22', '商业零售经营单位(1000m2以上）', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('23', '商业零售经营单位(100㎡-1000㎡）', '2', null);
INSERT INTO `t_industry_tbl` VALUES ('24', '其他', '2', null);
