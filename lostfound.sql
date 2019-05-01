SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `telephone` varchar(20) NOT NULL PRIMARY KEY UNIQUE KEY,
  `password` varchar(16) DEFAULT NULL,
  `realname` varchar(30) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `isadmin` bit DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13319557866', 'admin', '管理员', '13319557866@163.com', '1');
INSERT INTO `user` VALUES ('15574897427', 'xiaoli', '小李', '15574897427@163.com', '0');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY,
  `typename` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('7', '宠物');
INSERT INTO `type` VALUES ('8', '卡');
INSERT INTO `type` VALUES ('9', '书籍');
INSERT INTO `type` VALUES ('10', '钱包');
INSERT INTO `type` VALUES ('11', '手机');
INSERT INTO `type` VALUES ('12', '书包');

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY,
  `areaname` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('2', '理科楼');
INSERT INTO `area` VALUES ('4', '文科楼');
INSERT INTO `area` VALUES ('5', '综合教学楼');
INSERT INTO `area` VALUES ('6', '超市');

-- ----------------------------
-- Table structure for `lost`
-- ----------------------------
DROP TABLE IF EXISTS `lost`;
CREATE TABLE `lost` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY,
  `describe` longtext,
  `title` varchar(30) DEFAULT NULL,
  `pictures` varchar(50) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `user_telephone` varchar(20) NOT NULL,
  `adminjudge` int(1) DEFAULT 0,
  KEY `FKA04ED3CBB21C6F00` (`area_id`),
  KEY `FKA04ED3CBD5DEBE40` (`user_telephone`),
  KEY `FKA04ED3CBABCA53E0` (`type_id`),
  CONSTRAINT `FKA04ED3CBABCA53E0` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `FKA04ED3CBB21C6F00` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`),
  CONSTRAINT `FKA04ED3CBD5DEBE40` FOREIGN KEY (`user_telephone`) REFERENCES `user` (`telephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lost
-- ----------------------------
INSERT INTO `lost` VALUES ('1', '今天在综合教学楼上自习，期间我上了个厕所，回到座位时我桌子上的书已经不见了', '丢了一本书', '/LostFound/img-lost/20141017-06-49-13-IP配置.PNG', '1', '2014-10-17 00:00:00', '5', '9', '133195578661', '1');
INSERT INTO `lost` VALUES ('3', '钱包丢失了钱包丢失了钱包丢失了', '钱包丢失了', '/LostFound/img-lost/20141018-01-38-10-234.jpg', '1', '2014-10-18 00:00:00', '6', '10', '13319557866', '1');
INSERT INTO `lost` VALUES ('5', '宠物在文科楼丢了', '宠物丢了', '/LostFound/img-lost/20141018-01-40-56-werw.jpg', '1', '2014-10-18 00:00:00', '4', '7', '13319557866', '0');
INSERT INTO `lost` VALUES ('6', '做实验的时候丢了', '钱包丢失了', '/LostFound/img-lost/20141018-01-41-55-234.jpg', '1', '2014-10-18 00:00:00', '6', '10', '13319557866', '0');
INSERT INTO `lost` VALUES ('7', '在理科楼实验室的书丢了', '丢了一本书', '/LostFound/img-lost/20141018-01-42-22-Koala.jpg', '1', '2014-10-18 00:00:00', '2', '9', '13319557866', '1');
INSERT INTO `lost` VALUES ('8', '今天在上机课的时候钱包放桌上，出去上个厕所回来就没有了啊', '我的钱包丢了呢', '/LostFound/img-lost/20141030-10-45-59-123.jpg', '1', '2014-10-30 00:00:00', '6', '10', '13319557866','1');
INSERT INTO `lost` VALUES ('11', '几天实验课手机放桌上，出去上了个厕所回来手机就丢了呢', '实验课手机丢了', '/LostFound/img-lost/20141030-10-50-25-43.jpg', '1', '2014-10-30 00:00:00', '6', '11', '13319557866', '1');

-- ----------------------------
-- Table structure for `find`
-- ----------------------------
DROP TABLE IF EXISTS `find`;
CREATE TABLE `find` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY,
  `describe` longtext,
  `title` varchar(30) DEFAULT NULL,
  `pictures` varchar(50) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `user_telephone` varchar(20) NOT NULL,
  `adminjudge` int(1) DEFAULT 0,
  KEY `FKA04ED3CBB21C6F01` (`area_id`),
  KEY `FKA04ED3CBD5DEBE41` (`user_telephone`),
  KEY `FKA04ED3CBABCA53E1` (`type_id`),
  CONSTRAINT `FKA04ED3CBABCA53E1` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `FKA04ED3CBB21C6F01` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`),
  CONSTRAINT `FKA04ED3CBD5DEBE41` FOREIGN KEY (`user_telephone`) REFERENCES `user` (`telephone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of find
-- ----------------------------
INSERT INTO `find` VALUES ('1', '今天在综合教学楼上自习，看到一本书在地上', '捡到一本书', '/LostFound/img-lost/20141017-06-49-13-IP配置.PNG', '1', '2014-10-17 00:00:00', '5', '9', '13319557866', '1');
INSERT INTO `find` VALUES ('2', '超市门口捡到一个钱包', '捡到一个钱包', '/LostFound/img-lost/20141018-01-38-10-234.jpg', '1', '2014-10-18 00:00:00', '6', '10', '13319557866', '0');
