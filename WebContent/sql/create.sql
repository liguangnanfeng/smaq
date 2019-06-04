SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_token_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `app_token_tbl`;
CREATE TABLE `app_token_tbl` (
  `access_token` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'app端登录用',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `expires` int(11) DEFAULT NULL COMMENT '有效期，过期后需要重新登录',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_token_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `c3p0testtable`
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable` (
  `a` char(1) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='c3p0testtable';

-- ----------------------------
-- Records of c3p0testtable
-- ----------------------------

-- ----------------------------
-- Table structure for `company_iskey_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `company_iskey_tbl`;
CREATE TABLE `company_iskey_tbl` (
  `user_id` int(11) NOT NULL,
  `key1` int(1) DEFAULT '0' COMMENT '1 镇重点',
  `key2` int(1) DEFAULT '0' COMMENT '1 区重点',
  `key3` int(1) DEFAULT '0' COMMENT '1 市重点',
  `key4` int(1) DEFAULT '0' COMMENT '1 省重点',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业 镇 重点 区重点 市重点 省重点';

-- ----------------------------
-- Records of company_iskey_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `company_reg_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `company_reg_tbl`;
CREATE TABLE `company_reg_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region_id` int(11) DEFAULT NULL COMMENT '所属市',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机构名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='企业注册机构基库';

-- ----------------------------
-- Records of company_reg_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `company_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `company_tbl`;
CREATE TABLE `company_tbl` (
  `user_id` int(11) NOT NULL COMMENT '主键',
  `village_id` int(11) DEFAULT NULL COMMENT '所属村id',
  `name` varchar(200) DEFAULT NULL COMMENT '企业名称',
  `reg_type` varchar(50) DEFAULT NULL COMMENT '企业注册类型',
  `reg_type2` varchar(50) DEFAULT NULL COMMENT '自定义企业类型',
  `region_id` int(11) DEFAULT NULL COMMENT '地区id',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `legal` varchar(200) DEFAULT NULL COMMENT '法定代表人',
  `legal_contact` varchar(200) DEFAULT NULL COMMENT '联系方式',
  `capital` varchar(200) DEFAULT NULL COMMENT '注册资本(万元)',
  `establish` varchar(50) DEFAULT NULL COMMENT '成立日期',
  `business` varchar(200) DEFAULT NULL COMMENT '营业期限',
  `scope` varchar(2000) DEFAULT NULL COMMENT '经营范围',
  `license` varchar(50) DEFAULT NULL COMMENT '营业执照',
  `reg_region` int(11) DEFAULT NULL COMMENT '企业注册省市',
  `authority` varchar(50) DEFAULT NULL COMMENT '登记机关',
  `economic` varchar(50) DEFAULT NULL COMMENT '经济类型',
  `fixed` varchar(50) DEFAULT NULL COMMENT '固定资产(千元)',
  `staff` int(11) DEFAULT NULL COMMENT '员工总数',
  `income` varchar(50) DEFAULT NULL COMMENT '主营业务收入',
  `industry` varchar(100) DEFAULT NULL COMMENT '所属行业',
  `danger` int(1) DEFAULT NULL COMMENT '是否高危作业 1是',
  `dangers` varchar(200) DEFAULT NULL COMMENT '高危作业分类',
  `hazard` int(1) DEFAULT NULL COMMENT '重大危险源 1是',
  `dlevel` varchar(10) DEFAULT NULL COMMENT '风险等级',
  `charge` varchar(50) DEFAULT NULL COMMENT '主要负责人',
  `charge_contact` varchar(200) DEFAULT NULL COMMENT '负责人电话',
  `safety` varchar(50) DEFAULT NULL COMMENT '专兼职安全员',
  `safety_contact` varchar(50) DEFAULT NULL COMMENT '专兼职安全员联系方式',
  `safety_m` varchar(50) DEFAULT NULL COMMENT '安全管理部门负责人',
  `safety_m_contact` varchar(50) DEFAULT NULL COMMENT '安全管理部门负责人联系方式',
  `duty` varchar(50) DEFAULT NULL COMMENT '企业值班电话',
  `postage` varchar(10) DEFAULT NULL COMMENT '企业邮政编码',
  `email` varchar(200) DEFAULT NULL COMMENT '企业邮箱',
  `pic1` varchar(255) DEFAULT NULL COMMENT '安全生产管理网络图',
  `pic2` varchar(255) DEFAULT NULL COMMENT '安全（生产）许可证',
  `is_key` int(1) DEFAULT '0' COMMENT '0 非重点 1 重点',
  PRIMARY KEY (`user_id`),
  KEY `user_id` (`village_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息表';

-- ----------------------------
-- Records of company_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `cyryjkjh_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `cyryjkjh_tbl`;
CREATE TABLE `cyryjkjh_tbl` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '劳动者姓名',
  `time` varchar(50) DEFAULT NULL COMMENT '检查日期 年月',
  `jcxmzy` varchar(200) DEFAULT NULL COMMENT '检查项目摘要',
  `jcjg` varchar(50) DEFAULT NULL COMMENT '检查结果',
  `jcdw` varchar(50) DEFAULT NULL COMMENT '检查单位',
  `zybwhys` varchar(50) DEFAULT NULL COMMENT '接触的职业病危害因素名称',
  `tjlb` varchar(50) DEFAULT NULL COMMENT '体检类别 在岗，岗前，离岗， 诊断， 复查',
  `uid` int(11) DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='从业人员健康监护';

-- ----------------------------
-- Records of cyryjkjh_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `danger_lib_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `danger_lib_tbl`;
CREATE TABLE `danger_lib_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `name2` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '别名',
  `cas` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'cas号',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2829 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='危险信息源库';

-- ----------------------------
-- Records of danger_lib_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `detection_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `detection_tbl`;
CREATE TABLE `detection_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检测类别',
  `report_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评价时间',
  `expiration_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '到期时间',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `jcd` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '监测点',
  `whys` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检测职业病危害因素',
  `jcjg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检测结果',
  `jcdw` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检测单位',
  `jcbh` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检测编号',
  `ba` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备案情况及备案表',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `detection_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检测';

-- ----------------------------
-- Records of detection_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `district_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `district_tbl`;
CREATE TABLE `district_tbl` (
  `user_id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '区名',
  `region_id` int(11) DEFAULT NULL COMMENT '地区',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `city_id` int(11) DEFAULT NULL COMMENT '市id',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `district_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区信息表';

-- ----------------------------
-- Records of district_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `evaluation_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_tbl`;
CREATE TABLE `evaluation_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `flag` int(1) DEFAULT NULL COMMENT '1 安全生产类 2 职业健康类',
  `type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '安全生产类\r\n1.预评价\r\n2.验收评价\r\n3.现状评价\r\n4.危化品经营证评价\r\n5.危化品生产证评价\r\n6.危化品使用证评价\r\n7.重大危险源评估\r\n8.安全条件论证报告\r\n9.安全条件综合分析报告\r\n职业健康类\r\n1.预评价\r\n2.控效评价\r\n3.现状评价',
  `report_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评价报告名称',
  `report_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编制日期',
  `expiration_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '有效期',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `unit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编制单位',
  `doc_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档路径',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `evaluation_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='评价评估';

-- ----------------------------
-- Records of evaluation_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `examination_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `examination_tbl`;
CREATE TABLE `examination_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(11) DEFAULT NULL COMMENT '在岗，岗前，离岗， 诊断， 复查\r\n',
  `contact_condition` int(100) DEFAULT NULL COMMENT '接触情况 0无   1有 废弃',
  `contact_members` int(11) DEFAULT NULL COMMENT '接触人数 废弃',
  `healthcheck_member` varchar(11) DEFAULT NULL COMMENT '体检人数',
  `time` varchar(50) DEFAULT NULL COMMENT '体检时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '到期时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `project` varchar(200) DEFAULT NULL COMMENT '体检项目',
  `whys` varchar(200) DEFAULT NULL COMMENT '职业病危害因素',
  `tjjg` varchar(50) DEFAULT NULL COMMENT '体检机构',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=779 DEFAULT CHARSET=utf8 COMMENT='职业健康检查';

-- ----------------------------
-- Records of examination_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `facilities_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `facilities_tbl`;
CREATE TABLE `facilities_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `flag` int(1) DEFAULT '1' COMMENT '1 消防 2应急',
  `type` varchar(255) DEFAULT NULL COMMENT '种类',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `equipped_position` varchar(100) DEFAULT NULL COMMENT '配备位置',
  `amount` varchar(11) DEFAULT NULL COMMENT '数量',
  `expiry_date` varchar(50) DEFAULT NULL COMMENT '有效期',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `facilities_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1363 DEFAULT CHARSET=utf8 COMMENT='消防，应急设施配备';

-- ----------------------------
-- Records of facilities_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `feng_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `feng_tbl`;
CREATE TABLE `feng_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `cas` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'cas号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='粉尘类';

-- ----------------------------
-- Records of feng_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `global_region_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `global_region_tbl`;
CREATE TABLE `global_region_tbl` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '区域编号',
  `parent_id` int(32) DEFAULT NULL COMMENT '所属父类',
  `region_name` varchar(120) DEFAULT NULL COMMENT '区域名称',
  `region_type` varchar(1) DEFAULT NULL COMMENT '区域类型  0：中国、1：省份、2：城市、3：区县',
  `city_code` varchar(10) DEFAULT NULL COMMENT '城市编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3426 DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of global_region_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `hua_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `hua_tbl`;
CREATE TABLE `hua_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `cas` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'cas号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='化学因素';

-- ----------------------------
-- Records of hua_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `import_record_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `import_record_tbl`;
CREATE TABLE `import_record_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'excel名',
  `url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '路径',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='企业导入记录';

-- ----------------------------
-- Records of import_record_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `industry_lib_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `industry_lib_tbl`;
CREATE TABLE `industry_lib_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1 1级  22级',
  `pid` int(11) DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='行业类别';

-- ----------------------------
-- Records of industry_lib_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `industry_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `industry_tbl`;
CREATE TABLE `industry_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `del` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='企业自查行业分类表';

-- ----------------------------
-- Records of industry_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `law_doc_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `law_doc_tbl`;
CREATE TABLE `law_doc_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `doc_name` varchar(255) DEFAULT NULL COMMENT '排查文件名',
  `flag` int(11) DEFAULT '2' COMMENT '1自查 2执法3第三方4检查',
  `doc_url` varchar(255) DEFAULT NULL COMMENT '排查文件url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(10) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of law_doc_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `library_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `library_tbl`;
CREATE TABLE `library_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '1所属行业 \r\n2风险分级\r\n5评价类型\r\n6检测类别\r\n7高危作业\r\n\r\n11安管人员类别\r\n12危险化学品安全管理人员\r\n13特种作业人员\r\n',
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cycle` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '周期（tyep=5  =6',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分类基库表';

-- ----------------------------
-- Records of library_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `lightning_protection_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `lightning_protection_tbl`;
CREATE TABLE `lightning_protection_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `project` varchar(255) DEFAULT NULL COMMENT '检测项目',
  `content` varchar(5000) DEFAULT NULL COMMENT '检测内容',
  `last_test_time` datetime DEFAULT NULL COMMENT '上次检测时间',
  `expiration_time` datetime DEFAULT NULL COMMENT '到期时间',
  `test_results` varchar(5000) DEFAULT NULL COMMENT '检测结果',
  `detection_unit` varchar(255) DEFAULT NULL COMMENT '检测单位',
  `test_report_number` varchar(255) DEFAULT NULL COMMENT '检测报告编号',
  `remark` varchar(5000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='防雷防静电设施安全检查表';

-- ----------------------------
-- Records of lightning_protection_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `material_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `material_tbl`;
CREATE TABLE `material_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `material` varchar(100) DEFAULT NULL COMMENT '原材料',
  `annual_consumption` varchar(100) DEFAULT NULL COMMENT '年用量',
  `maximum_storage` varchar(200) DEFAULT NULL COMMENT '最大储存量',
  `state` varchar(100) DEFAULT NULL COMMENT '物态',
  `storage_location` varchar(100) DEFAULT NULL COMMENT '储存地点',
  `storage_method` varchar(100) DEFAULT NULL COMMENT '储存方式',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1144 DEFAULT CHARSET=utf8 COMMENT='主要原辅材料';

-- ----------------------------
-- Records of material_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `mequipment_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `mequipment_tbl`;
CREATE TABLE `mequipment_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `equipment_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `size` varchar(50) DEFAULT NULL COMMENT '型号规格',
  `process_parameters` varchar(100) DEFAULT NULL COMMENT '工艺参数',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1511 DEFAULT CHARSET=utf8 COMMENT='主要设备清单';

-- ----------------------------
-- Records of mequipment_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_tbl`;
CREATE TABLE `monitor_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `code` varchar(500) DEFAULT NULL COMMENT '序列号',
  `user` varchar(50) DEFAULT NULL COMMENT '用户名',
  `psw` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_id` int(11) DEFAULT NULL COMMENT '企业',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='摄像头';

-- ----------------------------
-- Records of monitor_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `notice_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `notice_tbl`;
CREATE TABLE `notice_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `source` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '来源',
  `type` varchar(1) COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '1 公告 2安全之家',
  `content` longtext COLLATE utf8_unicode_ci COMMENT '公告内容',
  `time` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sort` int(11) DEFAULT '0' COMMENT '排序 倒序',
  `type2` int(11) DEFAULT NULL COMMENT '21 法规-危化  22 工贸 24 消防 25 港口\r\n26 应急 27职位\r\n31 标准规范-危化  32 工贸 34 消防 35 港口\r\n36 应急 37职位\r\n1 怎样当好安全员 3一周事故警示 4监管动态 5安全常识\r\n10 法律',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `notice_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=694 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='公告';

-- ----------------------------
-- Records of notice_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `officials_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `officials_tbl`;
CREATE TABLE `officials_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart` varchar(50) DEFAULT NULL COMMENT '执法部门',
  `name` varchar(30) DEFAULT NULL COMMENT '执法人员姓名',
  `code` varchar(100) DEFAULT NULL COMMENT '编号',
  `uid` int(11) DEFAULT NULL COMMENT '所属 区县，市，省',
  `time` datetime DEFAULT NULL,
  `flag` int(1) DEFAULT NULL COMMENT '4村级 3镇级 6区级 7市级 8省级',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='执法人员表';

-- ----------------------------
-- Records of officials_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `pequipment_lib_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `pequipment_lib_tbl`;
CREATE TABLE `pequipment_lib_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level1` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level2` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='劳动防护用品配备 分类库';

-- ----------------------------
-- Records of pequipment_lib_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `pequipment_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `pequipment_tbl`;
CREATE TABLE `pequipment_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `lib_id` int(11) DEFAULT NULL COMMENT '劳保用品类型 1一般劳动防护用品 2特种劳动防护用品 3其他',
  `work` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工种',
  `cycle` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发放日期',
  `next_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '下次发放日期',
  `number` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '数量',
  `memo` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `lib_id` (`lib_id`) USING BTREE,
  CONSTRAINT `pequipment_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='劳动防护用品配备';

-- ----------------------------
-- Records of pequipment_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `ping_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `ping_tbl`;
CREATE TABLE `ping_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ping_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `production_process_diagram_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `production_process_diagram_tbl`;
CREATE TABLE `production_process_diagram_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `project` varchar(255) DEFAULT NULL COMMENT '项目',
  `description` varchar(5000) DEFAULT NULL COMMENT '制程描述',
  `material` varchar(255) DEFAULT NULL COMMENT '材料\r\n',
  `material_specifications` varchar(255) DEFAULT NULL COMMENT '材料规格\r\n',
  `personnel_requirements` varchar(255) DEFAULT NULL COMMENT '人员要求\r\n\r\n',
  `inspection_standards` varchar(255) DEFAULT NULL COMMENT '检查标准\r\n',
  `operating_rules` varchar(255) DEFAULT NULL COMMENT '操作规定\r\n',
  `equipment` varchar(255) DEFAULT NULL COMMENT '设备/工具\r\n',
  `doc_name` varchar(255) DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) DEFAULT NULL COMMENT '文档地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='生产工艺流程';

-- ----------------------------
-- Records of production_process_diagram_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `product_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `product_tbl`;
CREATE TABLE `product_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称 0无 1 有',
  `efficiency` varchar(100) DEFAULT NULL COMMENT '生产能力 ',
  `lastyear_production` varchar(100) DEFAULT NULL COMMENT '上年度产量',
  `storage_location` varchar(100) DEFAULT NULL COMMENT '储存地点',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8 COMMENT='主要产品';

-- ----------------------------
-- Records of product_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `qcc_data_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `qcc_data_tbl`;
CREATE TABLE `qcc_data_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(255) DEFAULT NULL COMMENT '企业名称',
  `data` longtext COMMENT '数据',
  `status` int(1) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of qcc_data_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `regulation_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `regulation_tbl`;
CREATE TABLE `regulation_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `management_system` int(1) DEFAULT NULL COMMENT '管理制度(0无1有） 废弃',
  `mis_inforce` int(1) DEFAULT NULL COMMENT '是否在有效期 废弃',
  `regulations` int(1) DEFAULT NULL COMMENT '操作规程 废弃',
  `ris_inforce` int(1) DEFAULT NULL COMMENT '是否在有效期（操作规程） 废弃',
  `safety` int(1) DEFAULT NULL COMMENT '安全生产责任 废弃',
  `sis_inforce` int(1) DEFAULT NULL COMMENT '在有效期（安全生产责任制） 废弃',
  `implementation` int(1) DEFAULT NULL COMMENT '责任落实情况 废弃',
  `contingency` int(1) DEFAULT NULL COMMENT '应急预案 废弃',
  `process_img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cis_inforce` int(1) DEFAULT NULL COMMENT '在有效期(应急预案） 废弃',
  `cis_danger` int(1) DEFAULT NULL COMMENT '是否构成重大危险源',
  `danger_lvl` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '重大危险源等级',
  `danger_record_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '重大危险源备案日期',
  `danger_end_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '重大危险源有效期',
  `danger_material` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '构成重大危险源的主要物质\r\n物质名称/理论计算量，物质名称/理论计算量，物质名称/理论计算量',
  `process` int(1) DEFAULT NULL COMMENT '重点监管的危险化工工艺',
  `process_name` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工艺名称',
  `process_control` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '化工工艺 是否满足自控要求',
  `process_record_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '危险化工工艺备案日期',
  `chemic` int(1) DEFAULT NULL COMMENT '重点监管的危险化学品',
  `chemic_name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '化学品物质名称',
  `chemic_control` int(1) DEFAULT NULL COMMENT '化学品 是否满足自控要求',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `regulation_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=285 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='企业规章制度';

-- ----------------------------
-- Records of regulation_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `safety_facilities_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `safety_facilities_tbl`;
CREATE TABLE `safety_facilities_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `size` varchar(50) DEFAULT NULL COMMENT '型号规格',
  `process_parameters` varchar(100) DEFAULT NULL COMMENT '工艺参数',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of safety_facilities_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `security_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `security_tbl`;
CREATE TABLE `security_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类别 ',
  `certificate_number` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证书编号',
  `forensic_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '取证时间',
  `audit_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核时间（最近）',
  `review_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '复审时间（下次）',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `security_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='安全管理人员';

-- ----------------------------
-- Records of security_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `sequipment_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `sequipment_tbl`;
CREATE TABLE `sequipment_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `se_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '特种设备名称',
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型',
  `file_number` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设备档案号',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `detection_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检测时间',
  `expiration_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '到期时间',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `sequipment_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='特种设备';

-- ----------------------------
-- Records of sequipment_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `sperson_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `sperson_tbl`;
CREATE TABLE `sperson_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `flag` int(1) DEFAULT NULL COMMENT '1安全管理人员\r\n2危险化学品安全管理人员\r\n3特种作业人员',
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工种',
  `certificate_number` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证书编号',
  `forensic_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '取证时间',
  `audit_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核时间（最近）',
  `review_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '复审时间（下次）',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`) USING BTREE,
  CONSTRAINT `sperson_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='持证上岗 安全管理人员，危险化学品安全管理人员，特种作业人员，特种设备操作人员';

-- ----------------------------
-- Records of sperson_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `standard_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `standard_tbl`;
CREATE TABLE `standard_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证书类型\r\n1.危化品经营许可证\r\n2.危化品生产许可证\r\n3.危化品使用许可证\r\n4.危化品登记证\r\n5.易制毒化学品备案\r\n6.易制爆化学品备案\r\n7.标准化二级证书\r\n8.标准化三级证书\r\n9.港口经营许可证\r\n10.成品油零售经营批准证书\r\n11.小微企业标准化证书',
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `certificate_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '证书编号',
  `time_get` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '取证时间',
  `time_exp` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='安全生产标准化证书';

-- ----------------------------
-- Records of standard_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `table2_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `table2_tbl`;
CREATE TABLE `table2_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件名',
  `is_type` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '路径',
  `type` int(11) DEFAULT NULL COMMENT '=1 word =2 excel',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of table2_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `table3_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `table3_tbl`;
CREATE TABLE `table3_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_type` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '=1 word  =2excel',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of table3_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `table4_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `table4_tbl`;
CREATE TABLE `table4_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '=1 word  =2excel',
  `is_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of table4_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `table5_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `table5_tbl`;
CREATE TABLE `table5_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_type` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '路径',
  `type` int(11) DEFAULT NULL COMMENT '0 excel  1 word',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='隐患排查治疗标准文件库';

-- ----------------------------
-- Records of table5_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `table_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `table_tbl`;
CREATE TABLE `table_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件名',
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '路径',
  `type` int(11) DEFAULT NULL COMMENT '=1 word     =2 excel',
  `is_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of table_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `town_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `town_tbl`;
CREATE TABLE `town_tbl` (
  `user_id` int(11) NOT NULL,
  `accountNum` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '镇名',
  `region_id` int(11) DEFAULT NULL COMMENT '地区',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `district_id` int(11) DEFAULT NULL COMMENT '区id',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `town_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城镇信息表';

-- ----------------------------
-- Records of town_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_company_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `trade_company_tbl`;
CREATE TABLE `trade_company_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户编号/行业编号',
  `tid` int(11) DEFAULT NULL COMMENT '企业编号',
  `iskey` int(1) DEFAULT '0' COMMENT '是否重点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of trade_company_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `trade_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `trade_tbl`;
CREATE TABLE `trade_tbl` (
  `user_id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '行业名称',
  `region_id` int(11) DEFAULT NULL COMMENT '地区',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `trade_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of trade_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_accident_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_accident_tbl`;
CREATE TABLE `t_accident_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所在部门',
  `time` datetime DEFAULT NULL COMMENT '事故时间',
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '事故类别',
  `person_death` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '死亡人数',
  `person_miss` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '失踪人数',
  `person_injured` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '受伤人数',
  `economic_loss` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '经济损失',
  `doc_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='事故报告和处理';

-- ----------------------------
-- Records of t_accident_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_check_admin_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_check_admin_tbl`;
CREATE TABLE `t_check_admin_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1 责令限期整改指令书\r\n2 整改复查意见书\r\n3 行政处罚告知书\r\n4 行政（当场）处罚决定书（单位）\r\n5 行政（当场）处罚决定书（个人）\r\n6 行政处罚决定书（单位）\r\n7 行政处罚决定书（个人）\r\n8 现场检查记录\r\n',
  `content` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `del` int(1) DEFAULT '0',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='自查对应行政执法';

-- ----------------------------
-- Records of t_check_admin_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_check_document_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_check_document_tbl`;
CREATE TABLE `t_check_document_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) DEFAULT NULL COMMENT '检查表id',
  `copy` text COLLATE utf8_unicode_ci COMMENT '文书详情',
  `flag2` int(1) DEFAULT '2' COMMENT '1自查 2执法3第三方4检查',
  `flag` int(1) DEFAULT NULL COMMENT '1 责令限期整改指令书\r\n2 整改复查意见书\r\n3 行政处罚告知书\r\n4 行政（当场）处罚决定书（单位）\r\n5 行政（当场）处罚决定书（个人）\r\n6 行政处罚决定书（单位）\r\n7 行政处罚决定书（个人）\r\n8 现场检查记录',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `user_id` int(11) DEFAULT NULL COMMENT '企业id',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `check_id` (`check_id`) USING BTREE,
  KEY `create_user` (`create_user`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `t_check_document_tbl_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `t_check_tbl` (`id`),
  CONSTRAINT `t_check_document_tbl_ibfk_2` FOREIGN KEY (`create_user`) REFERENCES `user_tbl` (`id`),
  CONSTRAINT `t_check_document_tbl_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='行政检查文书';

-- ----------------------------
-- Records of t_check_document_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_check_item_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_check_item_tbl`;
CREATE TABLE `t_check_item_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查标准详情',
  `level_id` int(11) DEFAULT NULL COMMENT '检查分类',
  `levels` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reference` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查参照',
  `part_id` int(11) DEFAULT NULL COMMENT '装置或设施id',
  `check_id` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '1 合格 2不合格 3已复查',
  `memo` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '不合格描述',
  `files` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查图片',
  `suggest` int(1) DEFAULT NULL COMMENT '1 立即整改 2限期整改',
  `deadline` datetime DEFAULT NULL COMMENT '限期整改期限',
  `plan_time` datetime DEFAULT NULL COMMENT '预计复查时间',
  `recheck_time` datetime DEFAULT NULL COMMENT '实际复查时间',
  `recheck_file` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '复查图片s',
  `recheck_memo` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '复查描述',
  PRIMARY KEY (`id`),
  KEY `check_id` (`check_id`) USING BTREE,
  KEY `part_id` (`part_id`) USING BTREE,
  CONSTRAINT `t_check_item_tbl_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `t_check_tbl` (`id`),
  CONSTRAINT `t_check_item_tbl_ibfk_2` FOREIGN KEY (`part_id`) REFERENCES `t_check_part_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81950 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检查 检查项';

-- ----------------------------
-- Records of t_check_item_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_check_part_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_check_part_tbl`;
CREATE TABLE `t_check_part_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) DEFAULT NULL,
  `levels` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查分类s',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部位或装置名称',
  `part_img` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门照片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1655 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='装置或设施';

-- ----------------------------
-- Records of t_check_part_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_check_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_check_tbl`;
CREATE TABLE `t_check_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flag` int(1) DEFAULT NULL COMMENT '1 企业自查 2行政执法 3第三方排查',
  `title` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查标题',
  `depart` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被检查部门',
  `depart_img` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门照片',
  `user_id` int(11) DEFAULT NULL COMMENT '企业id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `model_id` int(11) DEFAULT NULL COMMENT '模板id',
  `type` int(1) DEFAULT NULL COMMENT '1 日常 2定期 3临时',
  `industry_id` int(11) DEFAULT NULL COMMENT '检查行业id',
  `industry_type` int(1) DEFAULT NULL COMMENT '1 基础 2现场 3高危',
  `expect_time` datetime DEFAULT NULL COMMENT '预计检查时间',
  `real_time` datetime DEFAULT NULL COMMENT '实际检查时间',
  `cheker` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查人',
  `contact` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查人联系方式',
  `dapart_contact` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被检查部门负责人',
  `status` int(1) DEFAULT NULL COMMENT '1 未检查 2 已检查',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  `check_company` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查单位',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `model_id` (`model_id`) USING BTREE,
  KEY `industry_id` (`industry_id`) USING BTREE,
  CONSTRAINT `t_check_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`),
  CONSTRAINT `t_check_tbl_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `t_model_tbl` (`id`),
  CONSTRAINT `t_check_tbl_ibfk_3` FOREIGN KEY (`industry_id`) REFERENCES `t_industry_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1574 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='自查检查总表';

-- ----------------------------
-- Records of t_check_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_company_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_company_tbl`;
CREATE TABLE `t_company_tbl` (
  `user_id` int(11) NOT NULL,
  `industry_id` int(11) DEFAULT NULL COMMENT '企业行业id',
  `industry1` int(11) DEFAULT NULL COMMENT '基础所属',
  `industry2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '现场检查所属行业',
  `industry3` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '5大高危所属ids',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='企业 自查 5大高危 现场 行业信息记录';

-- ----------------------------
-- Records of t_company_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_contingency_plan_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_contingency_plan_tbl`;
CREATE TABLE `t_contingency_plan_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应急预案名称',
  `create_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '颁布时间',
  `filing_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备案时间',
  `active_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备案有效期',
  `doc_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档url',
  `djb_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应急预案备案登记表',
  `djb_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='应急预案';

-- ----------------------------
-- Records of t_contingency_plan_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_drill_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_drill_tbl`;
CREATE TABLE `t_drill_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '主要内容',
  `drill_time` datetime DEFAULT NULL COMMENT '演练时间',
  `doc_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文档url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='应急预案演练';

-- ----------------------------
-- Records of t_drill_tbl
-- ----------------------------

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

-- ----------------------------
-- Table structure for `t_item_serious_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_item_serious_tbl`;
CREATE TABLE `t_item_serious_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `levelid` int(11) DEFAULT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_item_serious_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_item_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_item_tbl`;
CREATE TABLE `t_item_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '自查标准项具体描述',
  `level_id` int(11) DEFAULT NULL,
  `reference` varchar(500) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '部分参考依据',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`level_id`) USING BTREE,
  CONSTRAINT `t_item_tbl_ibfk_1` FOREIGN KEY (`level_id`) REFERENCES `t_level_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6127 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检查项目';

-- ----------------------------
-- Records of t_item_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_level_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_level_tbl`;
CREATE TABLE `t_level_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ⅱ级隐患自查标准',
  `level2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ⅲ级隐患自查标准',
  `level3` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `industry_id` int(11) DEFAULT NULL COMMENT '所属行业',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`industry_id`) USING BTREE,
  CONSTRAINT `t_level_tbl_ibfk_1` FOREIGN KEY (`industry_id`) REFERENCES `t_industry_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3231 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='自查级别分类';

-- ----------------------------
-- Records of t_level_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_model_part_item_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_model_part_item_tbl`;
CREATE TABLE `t_model_part_item_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level_id` int(11) DEFAULT NULL,
  `reference` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `part_id` int(11) DEFAULT NULL COMMENT '装置或设施id',
  `model_id` int(11) DEFAULT NULL COMMENT '模板id',
  PRIMARY KEY (`id`),
  KEY `part_id` (`part_id`) USING BTREE,
  KEY `model_id` (`model_id`) USING BTREE,
  CONSTRAINT `t_model_part_item_tbl_ibfk_1` FOREIGN KEY (`part_id`) REFERENCES `t_model_part_tbl` (`id`),
  CONSTRAINT `t_model_part_item_tbl_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `t_model_tbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='模板检查 检查项 废弃';

-- ----------------------------
-- Records of t_model_part_item_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_model_part_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_model_part_tbl`;
CREATE TABLE `t_model_part_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门 或装置',
  `model_id` int(11) DEFAULT NULL COMMENT '模板id',
  `levels` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查分类s',
  PRIMARY KEY (`id`),
  KEY `model_id` (`model_id`) USING BTREE,
  CONSTRAINT `t_model_part_tbl_ibfk_1` FOREIGN KEY (`model_id`) REFERENCES `t_model_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检查计划模板 模块表';

-- ----------------------------
-- Records of t_model_part_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_model_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_model_tbl`;
CREATE TABLE `t_model_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '检查计划名',
  `user_id` int(11) DEFAULT NULL,
  `flag` int(1) DEFAULT '1' COMMENT '1 自查 3第三方',
  `type` int(1) DEFAULT NULL COMMENT '1 日常 2定期 3临时',
  `part` varchar(50) DEFAULT NULL COMMENT '被检查部门',
  `industry_id` int(11) DEFAULT NULL COMMENT '检查行业id',
  `industry_type` int(1) NOT NULL COMMENT '1 基础\\r\\n2 现场\\r\\n3 五大高危行业',
  `cycle` int(4) DEFAULT NULL COMMENT '定期周期天数',
  `next_time` datetime DEFAULT NULL COMMENT '下次创建时间',
  `next_check_time` datetime DEFAULT NULL COMMENT '下次预期检查时间',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  `open` int(1) DEFAULT '0' COMMENT '1 自动生成下一次的检查表',
  `use_time` datetime DEFAULT NULL COMMENT '最近使用时间',
  `create_time` datetime DEFAULT NULL COMMENT '模板创建时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `industry_id` (`industry_id`) USING BTREE,
  CONSTRAINT `t_model_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`),
  CONSTRAINT `t_model_tbl_ibfk_2` FOREIGN KEY (`industry_id`) REFERENCES `t_industry_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 COMMENT='检查计划模板表';

-- ----------------------------
-- Records of t_model_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_recheck_item_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_recheck_item_tbl`;
CREATE TABLE `t_recheck_item_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recheck_id` int(11) DEFAULT NULL,
  `check_item_id` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '2 未整改 3已整改',
  `file` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片',
  `deadline` datetime DEFAULT NULL COMMENT '整改期限',
  `memo` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '复查描述',
  PRIMARY KEY (`id`),
  KEY `recheck_id` (`recheck_id`) USING BTREE,
  KEY `check_item_id` (`check_item_id`) USING BTREE,
  CONSTRAINT `t_recheck_item_tbl_ibfk_1` FOREIGN KEY (`check_item_id`) REFERENCES `t_check_item_tbl` (`id`),
  CONSTRAINT `t_recheck_item_tbl_ibfk_2` FOREIGN KEY (`recheck_id`) REFERENCES `t_recheck_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1025 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='复查结果';

-- ----------------------------
-- Records of t_recheck_item_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_recheck_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_recheck_tbl`;
CREATE TABLE `t_recheck_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '企业id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `status` int(1) DEFAULT NULL COMMENT '1 未全部整改 2全部整改',
  `create_time` datetime DEFAULT NULL COMMENT '复查时间',
  `next_time` datetime DEFAULT NULL COMMENT '未合格项 限期检查时间',
  `checker` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查人员',
  `check_company` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查单位',
  `dapart_contact` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '被检部门负责人',
  PRIMARY KEY (`id`),
  KEY `check_id` (`check_id`) USING BTREE,
  CONSTRAINT `t_recheck_tbl_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `t_check_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='复查意见表';

-- ----------------------------
-- Records of t_recheck_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_rectification_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_rectification_tbl`;
CREATE TABLE `t_rectification_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_id` int(11) DEFAULT NULL,
  `item1` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '立即整改项',
  `item2` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '限期整改项',
  `item3` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '严重隐患',
  `deadline` datetime DEFAULT NULL COMMENT '限期时间',
  `plan_time` datetime DEFAULT NULL COMMENT '计划复查时间',
  `user_id` int(11) DEFAULT NULL COMMENT '企业id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '生成时间',
  `del` int(1) DEFAULT '0' COMMENT '1 删除',
  PRIMARY KEY (`id`),
  KEY `check_id` (`check_id`) USING BTREE,
  CONSTRAINT `t_rectification_tbl_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `t_check_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='检查结果整改意见表';

-- ----------------------------
-- Records of t_rectification_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `t_tindustry_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `t_tindustry_tbl`;
CREATE TABLE `t_tindustry_tbl` (
  `id` int(11) NOT NULL COMMENT '企业行业id',
  `in1` int(11) DEFAULT NULL COMMENT '对应基础资料id',
  `in2a` varchar(50) DEFAULT NULL COMMENT '现场多选1',
  `in2o` varchar(50) DEFAULT NULL COMMENT '现场必填',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业所属行业 与 检查项目匹配';

-- ----------------------------
-- Records of t_tindustry_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `user_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `user_tbl`;
CREATE TABLE `user_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(200) DEFAULT NULL COMMENT '登录名',
  `psw` varchar(200) DEFAULT NULL COMMENT '密码',
  `user_type` int(11) DEFAULT NULL COMMENT '1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省',
  `is_freeze` varchar(1) DEFAULT '0' COMMENT '0正常 冻结',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` varchar(1) DEFAULT '0' COMMENT '1 删除',
  `longitude` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=464 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of user_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for `village_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `village_tbl`;
CREATE TABLE `village_tbl` (
  `user_id` int(11) NOT NULL,
  `accountNum` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '村名',
  `region_id` int(11) DEFAULT NULL COMMENT '地区id',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `town_id` int(11) DEFAULT NULL COMMENT '所属镇',
  `states` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `region_id` (`region_id`) USING BTREE,
  CONSTRAINT `village_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`),
  CONSTRAINT `village_tbl_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `global_region_tbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='村信息表';

-- ----------------------------
-- Records of village_tbl
-- ----------------------------

-- ----------------------------
-- View structure for `company_view`
-- ----------------------------
DROP VIEW IF EXISTS `company_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `company_view` AS select `a`.`user_id` AS `user_id`,`a`.`is_key` AS `is_key`,`a`.`village_id` AS `village_id`,`a`.`name` AS `name`,`a`.`reg_type` AS `reg_type`,`a`.`region_id` AS `region_id`,`a`.`address` AS `address`,`a`.`legal` AS `legal`,`a`.`legal_contact` AS `legal_contact`,`a`.`capital` AS `capital`,`a`.`establish` AS `establish`,`a`.`business` AS `business`,`a`.`scope` AS `scope`,`a`.`license` AS `license`,`a`.`reg_region` AS `reg_region`,`a`.`authority` AS `authority`,`a`.`economic` AS `economic`,`a`.`fixed` AS `fixed`,`a`.`staff` AS `staff`,`a`.`income` AS `income`,`a`.`industry` AS `industry`,`a`.`danger` AS `danger`,`a`.`dangers` AS `dangers`,`a`.`hazard` AS `hazard`,`a`.`dlevel` AS `dlevel`,`a`.`charge` AS `charge`,`a`.`charge_contact` AS `charge_contact`,`a`.`safety` AS `safety`,`a`.`safety_contact` AS `safety_contact`,`a`.`safety_m` AS `safety_m`,`a`.`safety_m_contact` AS `safety_m_contact`,`a`.`duty` AS `duty`,`a`.`postage` AS `postage`,`a`.`email` AS `email`,`a`.`pic1` AS `pic1`,`a`.`pic2` AS `pic2`,(select `user_tbl`.`is_del` from `user_tbl` where (`user_tbl`.`id` = `a`.`user_id`)) AS `is_del`,`b`.`name` AS `village_name`,`c`.`name` AS `town_name`,`c`.`user_id` AS `town_id`,`d`.`name` AS `district_name`,`d`.`user_id` AS `district_id` from (((`company_tbl` `a` left join `village_tbl` `b` on((`a`.`village_id` = `b`.`user_id`))) left join `town_tbl` `c` on((`b`.`town_id` = `c`.`user_id`))) left join `district_tbl` `d` on((`c`.`district_id` = `d`.`user_id`))) ;

-- ----------------------------
-- View structure for `global_region_view`
-- ----------------------------
DROP VIEW IF EXISTS `global_region_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `global_region_view` AS select `a`.`id` AS `id`,(case when (`a`.`region_type` = '3') then concat(`c`.`region_name`,`b`.`region_name`,`a`.`region_name`) when (`a`.`region_type` = '2') then concat(`b`.`region_name`,`a`.`region_name`) else `a`.`region_name` end) AS `region_name`,`a`.`region_type` AS `region_type`,`a`.`city_code` AS `city_code`,(case when (`a`.`region_type` = '3') then `c`.`id` when (`a`.`region_type` = '2') then `b`.`id` else `a`.`id` end) AS `province_id`,(case when (`a`.`region_type` = '3') then `b`.`id` when (`a`.`region_type` = '2') then `a`.`id` else NULL end) AS `city_id` from ((`global_region_tbl` `a` left join `global_region_tbl` `b` on((`a`.`parent_id` = `b`.`id`))) left join `global_region_tbl` `c` on((`b`.`parent_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `recheck_yj_view`
-- ----------------------------
DROP VIEW IF EXISTS `recheck_yj_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `recheck_yj_view` AS select `t1`.`id` AS `id`,(select count(0) from `t_recheck_tbl` where (`t_recheck_tbl`.`check_id` = `t1`.`check_id`)) AS `c`,`t2`.`user_id` AS `user_id` from (`t_rectification_tbl` `t1` left join `t_check_tbl` `t2` on((`t1`.`check_id` = `t2`.`id`))) where ((`t2`.`flag` = 2) and (`t1`.`del` = 0)) ;

-- ----------------------------
-- View structure for `t_check_item_view`
-- ----------------------------
DROP VIEW IF EXISTS `t_check_item_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `t_check_item_view` AS select `t1`.`id` AS `id`,`t1`.`levels` AS `levels`,`t2`.`real_time` AS `real_time`,`t2`.`flag` AS `flag`,`t2`.`type` AS `type`,`t2`.`user_id` AS `user_id`,`t2`.`industry_type` AS `industry_type`,`t1`.`part_id` AS `part_id`,`t1`.`level_id` AS `level_id`,`t2`.`depart` AS `depart`,`t2`.`check_company` AS `check_company`,`tcp`.`name` AS `part_name`,`tcp`.`part_img` AS `part_img`,`t1`.`status` AS `status`,`t1`.`check_id` AS `check_id`,`t1`.`content` AS `content`,`t1`.`deadline` AS `deadline`,`t1`.`files` AS `files`,`t1`.`memo` AS `memo`,`t1`.`plan_time` AS `plan_time`,`t1`.`recheck_file` AS `recheck_file`,`t1`.`recheck_time` AS `recheck_time`,`t1`.`recheck_memo` AS `recheck_memo`,`t1`.`reference` AS `reference`,`t1`.`suggest` AS `suggest`,`t2`.`title` AS `title`,`t4`.`village_id` AS `village_id`,`t4`.`name` AS `name`,`t4`.`village_name` AS `village_name`,`t4`.`town_name` AS `town_name`,`t4`.`town_id` AS `town_id`,`t4`.`district_name` AS `district_name`,`t4`.`district_id` AS `district_id`,if(find_in_set(`t1`.`id`,`t3`.`item3`),1,0) AS `d`,(select `t_company_tbl`.`industry_id` from `t_company_tbl` where (`t_company_tbl`.`user_id` = `t2`.`user_id`)) AS `industry_id` from ((((`t_check_item_tbl` `t1` left join `t_check_tbl` `t2` on((`t1`.`check_id` = `t2`.`id`))) left join `t_check_part_tbl` `tcp` on((`t1`.`part_id` = `tcp`.`id`))) left join `t_rectification_tbl` `t3` on((`t1`.`check_id` = `t3`.`check_id`))) left join `company_view` `t4` on((`t2`.`user_id` = `t4`.`user_id`))) where ((`t2`.`del` = 0) and (`t2`.`status` = 2) and (`t1`.`status` > 1) and (`t4`.`is_del` = '0')) ;

-- ----------------------------
-- View structure for `t_check_item_view2`
-- ----------------------------
DROP VIEW IF EXISTS `t_check_item_view2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `t_check_item_view2` AS select `t1`.`id` AS `id`,`t1`.`levels` AS `levels`,`t2`.`real_time` AS `real_time`,`t2`.`industry_type` AS `industry_type`,`t2`.`flag` AS `flag`,`t2`.`type` AS `type`,`t2`.`user_id` AS `user_id`,`t1`.`status` AS `status`,`t1`.`check_id` AS `check_id`,`b`.`user_id` AS `village_id`,`c`.`user_id` AS `town_id`,`d`.`user_id` AS `district_id`,if(find_in_set(`t1`.`id`,`t3`.`item3`),1,0) AS `d`,(select `t_company_tbl`.`industry_id` from `t_company_tbl` where (`t_company_tbl`.`user_id` = `t2`.`user_id`)) AS `industry_id` from ((((((((`t_check_item_tbl` `t1` left join `t_check_tbl` `t2` on((`t1`.`check_id` = `t2`.`id`))) left join `t_check_part_tbl` `tcp` on((`t1`.`part_id` = `tcp`.`id`))) left join `t_rectification_tbl` `t3` on((`t1`.`check_id` = `t3`.`check_id`))) left join `user_tbl` `e` on((`t2`.`user_id` = `e`.`id`))) left join `company_tbl` `a` on((`t2`.`user_id` = `a`.`user_id`))) left join `village_tbl` `b` on((`a`.`village_id` = `b`.`user_id`))) left join `town_tbl` `c` on((`b`.`town_id` = `c`.`user_id`))) left join `district_tbl` `d` on((`c`.`district_id` = `d`.`user_id`))) where ((`t2`.`del` = 0) and (`t2`.`status` = 2) and (`t1`.`status` > 1) and (`e`.`is_del` = '0')) ;
