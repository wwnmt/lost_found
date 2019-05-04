CREATE DATABASE `lost_found`;

USE `lost_found`;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `studentid` varchar(11) NOT NULL PRIMARY KEY UNIQUE KEY,
  `telephone` varchar(20),
  `password` varchar(16) DEFAULT NULL,
  `realname` varchar(30) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `isadmin` int(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('sx001', '13319557866', 'admin', '管理员', '13319557866@163.com', '1');
INSERT INTO `user` VALUES ('sx002', '18795996968', 'test', 'wwn', '18795996968@163.com', '1');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY AUTO_INCREMENT,
  `typename` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '宠物');
INSERT INTO `type` VALUES ('2', '卡');
INSERT INTO `type` VALUES ('3', '书籍');
INSERT INTO `type` VALUES ('4', '钱包');
INSERT INTO `type` VALUES ('5', '手机');
INSERT INTO `type` VALUES ('6', '书包');

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY AUTO_INCREMENT,
  `areaname` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '理科楼');
INSERT INTO `area` VALUES ('2', '文科楼');
INSERT INTO `area` VALUES ('3', '综合教学楼');
INSERT INTO `area` VALUES ('4', '超市');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY  AUTO_INCREMENT,
  `studentid` varchar(11) NOT NULL,
  `msgid` int(11) NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `commentTime` datetime NOT NULL,
  KEY `FKA04ED3CBB21C6F02` (`studentid`),
  KEY `FKA04ED3CBD5DEBE42` (`msgid`),
  CONSTRAINT `FKA04ED3CBD5DEBE42` FOREIGN KEY (`msgid`) REFERENCES `message` (`id`),
  CONSTRAINT `FKA04ED3CBB21C6F02` FOREIGN KEY (`studentid`) REFERENCES `user` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', 'sx001', '1', '正好我捡到一本书', '2018-10-18 12:14:00');


-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL PRIMARY KEY UNIQUE KEY AUTO_INCREMENT,
  `studentid` varchar(20) NOT NULL,
  `msgtype` int(1) DEFAULT 0,
  `title` varchar(30) DEFAULT NULL,
  `description` longtext,
  `uptime` datetime DEFAULT NULL,
  `status` int(1) NOT NULL,
  `type_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `losttime` datetime DEFAULT NULL,
  `contact_name` varchar(30) NOT NULL,
  `contact_phone` varchar(20) NOT NULL,
  `adminjudge` int(1) DEFAULT 0,
  KEY `FKA04ED3CBB21C6F00` (`area_id`),
  KEY `FKA04ED3CBD5DEBE40` (`studentid`),
  KEY `FKA04ED3CBABCA53E0` (`type_id`),
  CONSTRAINT `FKA04ED3CBABCA53E0` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `FKA04ED3CBB21C6F00` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`),
  CONSTRAINT `FKA04ED3CBD5DEBE40` FOREIGN KEY (`studentid`) REFERENCES `user` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', 'sx001', '1', '丢了一本书','今天在综合教学楼上自习，期间我上了个厕所，回到座位时我桌子上的书已经不见了', '2018-10-18 00:00:00', '0','6','3','/LostFound/img-lost/20141017-06-49-13-IP配置.PNG', '2018-10-17 00:00:00','小李','13319557866', '1');
INSERT INTO `message` VALUES ('2', 'sx001', '1','钱包丢失了','钱包丢失了钱包丢失了钱包丢失了', '2018-10-18 00:00:00', '0','4','3','/LostFound/img-lost/20141018-01-38-10-234.jpg','2018-10-17 00:00:00','小李','13319557866', '1');
INSERT INTO `message` VALUES ('3', 'sx001', '1','宠物丢了','宠物在文科楼丢了', '2018-10-18 00:00:00', '0','1','2','/LostFound/img-lost/20141018-01-40-56-werw.jpg', '2018-10-18 00:00:00','小李','13319557866', '0');
INSERT INTO `message` VALUES ('4', 'sx002', '1','钱包丢失了','做实验的时候丢了', '2018-10-18 00:00:00', '0','4','1','/LostFound/img-lost/20141018-01-41-55-234.jpg', '2018-10-18 00:00:00','小李','13319557866', '0');
INSERT INTO `message` VALUES ('5', 'sx001', '1','丢了一本书','在理科楼实验室的书丢了', '2018-10-18 00:00:00', '0','6','1','/LostFound/img-lost/20141018-01-42-22-Koala.jpg', '2018-10-18 00:00:00','小李','13319557866', '1');
INSERT INTO `message` VALUES ('6', 'sx001', '1','我的钱包丢了呢','今天在上机课的时候钱包放桌上，出去上个厕所回来就没有了啊', '2018-10-18 00:00:00', '0','4','3','/LostFound/img-lost/20141030-10-45-59-123.jpg', '2018-10-18 00:00:00','小李','13319557866','1');
INSERT INTO `message` VALUES ('7', 'sx001', '1','实验课手机丢了','几天实验课手机放桌上，出去上了个厕所回来手机就丢了呢', '2018-10-18 00:00:00', '0','5','1','/LostFound/img-lost/20141030-10-50-25-43.jpg', '2018-10-18 00:00:00','小李','13319557866', '1');
INSERT INTO `message` VALUES ('8', 'sx001', '2','捡到一本书', '今天在综合教学楼上自习，看到一本书在地上', '2019-05-01 00:00:00', '0', '6','3','/LostFound/img/20141017-06-49-13-IP配置.PNG','2019-04-30 15:00:00','小李','13319557866','1');
INSERT INTO `message` VALUES ('9', 'sx002', '2','捡到一个钱包', '超市门口捡到一个钱包', '2019-05-01 12:00:00', '0','4','4','/LostFound/img/20141018-01-38-10-234.jpg','2019-04-30 16:00:00','小李','13319557866','0');