


CREATE TABLE `message_to_user` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `message_id` varchar(50) DEFAULT NULL COMMENT '消息id',
  `user_id` int(11) DEFAULT NULL COMMENT '消息推送的人',
  `revice_flag` tinyint(4) DEFAULT '0' COMMENT '是否接收推送过0未接收 1已接收',
  `read_flag` tinyint(4) DEFAULT '0' COMMENT '是否已读0未读 1已读',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '读内容的时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除状态0 正常  1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `messages` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '资讯所属用户userId',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `descr` varchar(500) DEFAULT NULL COMMENT '简介',
  `content` text COMMENT '正文',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `send_flag` tinyint(4) DEFAULT '0' COMMENT '发送状态0未发送 1已推送',
  `send_total` int(11) DEFAULT NULL COMMENT '推送的总人数',
  `files` varchar(500) DEFAULT NULL COMMENT '附件',
  `create_utp` int(4) DEFAULT NULL COMMENT '创建者的身份',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除状0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `sensors_lib` (
  `code` int(4) NOT NULL COMMENT '主键code',
  `name` varchar(50) DEFAULT NULL COMMENT '传感器名称',
  `type` int(4) DEFAULT NULL COMMENT '传感器放置类型1 河道  2 高危作业 3 重大危险源',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `sensors_lib`(`code`,`name`,`type`) values (10,'PH传感器',1),(11,'温度传感器',1),(12,'溶氧传感器',1),(13,'电导率传感器',1),(14,'浊度传感器',1),(20,'有毒气体传感器',2),(30,'可燃气体传感器',2),(50,'温度传感器',3),(51,'液位传感器',3);



CREATE TABLE `t_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `device_sn` varchar(5) NOT NULL COMMENT '设备序列号',
  `sensor_sn` varchar(2) NOT NULL COMMENT '传感器通道号',
  `collect` float NOT NULL COMMENT '采集结果',
  `colltime` datetime NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`id`),
  KEY `IDX_TCOLLECTION_COLLECTIONTIME` (`colltime`)
) ENGINE=InnoDB AUTO_INCREMENT=8286 DEFAULT CHARSET=utf8;



CREATE TABLE `t_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_sn` varchar(5) NOT NULL COMMENT '设备序列号(唯一索引）',
  `name` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `company_id` int(11) DEFAULT NULL COMMENT '单位名称',
  `site` varchar(100) DEFAULT NULL COMMENT '放置场所',
  `phonename` varchar(11) DEFAULT NULL COMMENT '4G号码',
  `sensors` int(11) DEFAULT NULL COMMENT '附带传感器数量',
  `creator` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_name` int(11) DEFAULT NULL COMMENT '最后修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `last_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后在线时间',
  `status` int(11) DEFAULT NULL COMMENT '在线状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq__device_idx` (`device_sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



CREATE TABLE `t_sensors` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '传感器ID',
  `sensor_sn` varchar(2) NOT NULL COMMENT '传感器通道号(平台增加时产生-同一单位唯一)',
  `sensorname` varchar(20) DEFAULT NULL COMMENT '传感器名称',
  `device_sn` varchar(5) DEFAULT NULL COMMENT '设备序列号',
  `site` varchar(100) DEFAULT NULL COMMENT '放置场所',
  `company_id` int(11) DEFAULT NULL COMMENT '单位id',
  `creator` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_name` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  `sensor_type` int(4) DEFAULT NULL COMMENT '传感器类型',
  PRIMARY KEY (`id`),
  KEY `IDX_TSENSORS_DE_SE` (`sensor_sn`,`device_sn`,`sensor_type`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



CREATE TABLE `t_warnmsg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报警ID',
  `device_sn` varchar(5) NOT NULL COMMENT '设备序列号',
  `sensor_sn` varchar(2) NOT NULL COMMENT '传感器通道号',
  `sensor_type` varchar(2) NOT NULL COMMENT '传感器类型',
  `warn` tinyint(4) NOT NULL COMMENT '报警标志:1-低于下限预警 2-高于上限预警 3-低于下限报警 4-高于上限报警',
  `warntime` datetime NOT NULL COMMENT '报警上传时间',
  `cleantime` datetime DEFAULT NULL COMMENT '清除时间',
  `operator` varchar(20) DEFAULT NULL COMMENT '清除操作用户',
  `creator` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_name` int(11) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2470 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



CREATE TABLE `t_waterparamter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '水质参数ID',
  `para` varchar(20) NOT NULL COMMENT '水质参数',
  `name` varchar(10) NOT NULL COMMENT '参数名称',
  `unit` varchar(10) NOT NULL COMMENT '单位',
  `min_val` double DEFAULT NULL COMMENT '最小值',
  `max_val` double DEFAULT NULL COMMENT '最大值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;



CREATE TABLE `user_group` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `group_name` varchar(200) DEFAULT NULL COMMENT '群组名称',
  `user_id` int(11) DEFAULT NULL COMMENT '群组创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `group_type` int(4) DEFAULT '0' COMMENT '分组类型0 企业  1 区乡镇',
  `group_village` int(11) DEFAULT NULL COMMENT '具体的某个乡镇',
  `group_village_type` int(4) DEFAULT NULL COMMENT '乡镇类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `user_group_list` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `group_id` varchar(50) DEFAULT NULL COMMENT '所属群组id',
  `group_user_id` int(11) DEFAULT NULL COMMENT '群组内人员的用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE monitor_tbl ADD danger_level VARCHAR(100);
ALTER TABLE monitor_tbl ADD descr VARCHAR(500);
ALTER TABLE monitor_tbl ADD accident_type VARCHAR(200);
ALTER TABLE monitor_tbl ADD duty_people VARCHAR(100);
ALTER TABLE monitor_tbl ADD thumbnail VARCHAR(100);

