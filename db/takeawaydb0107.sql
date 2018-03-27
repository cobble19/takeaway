/*
Navicat MySQL Data Transfer

Source Server         : 10.224.37.141
Source Server Version : 50528
Source Host           : 10.224.37.141:3306
Source Database       : dwyzdb

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-01-07 16:16:27
*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dwyzdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

GRANT SUPER ON dwyzdb.* TO 'test'@'%' IDENTIFIED BY 'test';
GRANT SUPER ON dwyzdb.* TO 'test'@'localhost' IDENTIFIED BY 'test';

GRANT ALL PRIVILEGES ON dwyzdb.* TO 'test'@'%' IDENTIFIED BY 'test' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON dwyzdb.* TO 'test'@'localhost' IDENTIFIED BY 'test' WITH GRANT OPTION;
FLUSH PRIVILEGES;

use dwyzdb;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_privilege
-- ----------------------------

CREATE TABLE IF NOT EXISTS `b_privilege` (
  `PRIVILEGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRIVILEGE_NAME` varchar(255) DEFAULT NULL,
  `URL` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`PRIVILEGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_privilege
-- ----------------------------
INSERT INTO `b_privilege` VALUES ('-1', 'PRIVILEGE_ALL', '/mgr/**');
INSERT INTO `b_privilege` VALUES ('1', 'pri_user', '/mgr/user*');
INSERT INTO `b_privilege` VALUES ('2', '2', '/mgr/test');
INSERT INTO `b_privilege` VALUES ('3', 'PRI_TEST', '/mgr/user/add');

-- ----------------------------
-- Table structure for b_role
-- ----------------------------

CREATE TABLE IF NOT EXISTS `b_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_role
-- ----------------------------
INSERT INTO `b_role` VALUES ('-1', 'ROLE_ADMIN');
INSERT INTO `b_role` VALUES ('5', 'ROLE_G');
INSERT INTO `b_role` VALUES ('6', 'abcd');
INSERT INTO `b_role` VALUES ('7', 'ROLE_TEST2');
INSERT INTO `b_role` VALUES ('8', 'ROLE_T');
INSERT INTO `b_role` VALUES ('9', 'R');
INSERT INTO `b_role` VALUES ('10', 'ROLE_TEST123');
INSERT INTO `b_role` VALUES ('11', 'ROLE_TEST123');
INSERT INTO `b_role` VALUES ('12', 'ROLE_TEST123');
INSERT INTO `b_role` VALUES ('13', 'ROLE_TEST123');
INSERT INTO `b_role` VALUES ('14', 'Role_t123');
INSERT INTO `b_role` VALUES ('15', 'Role_t123');
INSERT INTO `b_role` VALUES ('17', 'roletest');

-- ----------------------------
-- Table structure for b_user
-- ----------------------------

CREATE TABLE IF NOT EXISTS `b_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ENABLE` tinyint(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_user
-- ----------------------------
INSERT INTO `b_user` VALUES ('1', 'test', 'xxx', '0');

-- ----------------------------
-- Table structure for food
-- ----------------------------

CREATE TABLE IF NOT EXISTS `food` (
  `FOOD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FOOD_SELLER_ID` int(11) DEFAULT NULL COMMENT 'FOREIGN KEY',
  `FOOD_TYPE_ID` int(11) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `UNIT_PRICE` double DEFAULT NULL,
  PRIMARY KEY (`FOOD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('4', '3', '-2', '梅干菜扣肉煲仔', '18');
INSERT INTO `food` VALUES ('5', '3', '-2', '西红柿鸡蛋盖饭', '10.1');
INSERT INTO `food` VALUES ('6', '4', '-3', '牛肉火锅', '45.5');
INSERT INTO `food` VALUES ('7', '4', '-1', '羊肉火锅', '48');
INSERT INTO `food` VALUES ('8', '3', '-2', '王二特色菜', '15.5');
INSERT INTO `food` VALUES ('9', '3', '1', '虾仁馄饨', '15');
INSERT INTO `food` VALUES ('10', '4', '-2', '鸡蛋馄饨', '13');

-- ----------------------------
-- Table structure for food_menu
-- ----------------------------

CREATE TABLE IF NOT EXISTS `food_menu` (
  `FOOD_MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FOOD_SELLER_ID` int(11) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`FOOD_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_menu
-- ----------------------------

-- ----------------------------
-- Table structure for food_seller
-- ----------------------------

CREATE TABLE IF NOT EXISTS `food_seller` (
  `FOOD_SELLER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `PHONE` varchar(512) DEFAULT NULL,
  `BUSINESS_HOURS` varchar(255) DEFAULT NULL COMMENT '营业时间',
  `DELIVERY_AREA` varchar(255) DEFAULT NULL,
  `DELIVERY_PRICE_MIN` decimal(10,2) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `NOTE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FOOD_SELLER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_seller
-- ----------------------------
INSERT INTO `food_seller` VALUES ('3', '王二食府', '13912349876', '24小时', '高新区', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('4', '张三大排档', '13912349876', '10：00-20:00', '红心', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('8', 'a', '13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('20', '5', '613912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('21', 'aq', '13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('23', 'a3', '13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('24', 'a', 'a13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('25', 'a', 'a13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('26', '啊啊', '啊啊13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('27', '变变变', '版本13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('28', '出差', '长城13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('29', '多点', '等等13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('30', '恩恩', '额额13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('31', '恩恩', '额额13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('32', '多对多', '大多大13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('33', '飞飞', '方法13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');
INSERT INTO `food_seller` VALUES ('34', '搞个', '逛逛13912349876', '24小时', '大蜀山', '10.00', '大合肥的大长弓', '注意啦 有大灰狼');

-- ----------------------------
-- Table structure for food_seller_type
-- ----------------------------

CREATE TABLE IF NOT EXISTS `food_seller_type` (
  `FOOD_SELLER_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ICON` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`FOOD_SELLER_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_seller_type
-- ----------------------------
INSERT INTO `food_seller_type` VALUES ('-2', '甜点', null);
INSERT INTO `food_seller_type` VALUES ('-1', '中式餐饮', null);

-- ----------------------------
-- Table structure for food_type
-- ----------------------------

CREATE TABLE IF NOT EXISTS `food_type` (
  `FOOD_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`FOOD_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_type
-- ----------------------------
INSERT INTO `food_type` VALUES ('-3', '饮料');
INSERT INTO `food_type` VALUES ('-2', '特色饮食');
INSERT INTO `food_type` VALUES ('-1', '早餐');
INSERT INTO `food_type` VALUES ('1', '经典');
INSERT INTO `food_type` VALUES ('2', '金典3');

-- ----------------------------
-- Table structure for location_area
-- ----------------------------

CREATE TABLE IF NOT EXISTS `location_area` (
  `LOCATION_AREA_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`LOCATION_AREA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location_area
-- ----------------------------
INSERT INTO `location_area` VALUES ('-1', '蜀山区', 'hefei desc...1234');
INSERT INTO `location_area` VALUES ('1', '瑶海区', '123456');
INSERT INTO `location_area` VALUES ('2', '庐阳区', '123');
INSERT INTO `location_area` VALUES ('5', '高新区', '2');

-- ----------------------------
-- Table structure for location_business
-- ----------------------------

CREATE TABLE IF NOT EXISTS `location_business` (
  `LOCATION_BUSINESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`LOCATION_BUSINESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location_business
-- ----------------------------
INSERT INTO `location_business` VALUES ('-2', '红鑫', null);
INSERT INTO `location_business` VALUES ('-1', '马鞍山万达', '描述。。');
INSERT INTO `location_business` VALUES ('1', '天鹅湖', 'ba');

-- ----------------------------
-- Table structure for order_form
-- ----------------------------

CREATE TABLE IF NOT EXISTS `order_form` (
  `ORDER_FORM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FOOD_SELLER_ID` int(11) DEFAULT NULL,
  `DEFINE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ORDER_FORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_form
-- ----------------------------

-- ----------------------------
-- Table structure for rel_area_business
-- ----------------------------

CREATE TABLE IF NOT EXISTS `rel_area_business` (
  `REL_AREA_BUSINESS_ID` int(11) NOT NULL,
  `LOCATION_AREA_ID` int(11) DEFAULT NULL,
  `LOCATION_BUSINESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REL_AREA_BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_area_business
-- ----------------------------
INSERT INTO `rel_area_business` VALUES ('-3', '-1', '-2');
INSERT INTO `rel_area_business` VALUES ('-2', '-1', '1');
INSERT INTO `rel_area_business` VALUES ('-1', '-1', '-1');

-- ----------------------------
-- Table structure for rel_area_seller
-- ----------------------------

CREATE TABLE IF NOT EXISTS `rel_area_seller` (
  `REL_AREA_SELLER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATION_AREA_ID` int(11) DEFAULT NULL,
  `FOOD_SELLER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REL_AREA_SELLER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_area_seller
-- ----------------------------
INSERT INTO `rel_area_seller` VALUES ('4', '1', '3');
INSERT INTO `rel_area_seller` VALUES ('5', '1', '4');
INSERT INTO `rel_area_seller` VALUES ('8', '1', '7');
INSERT INTO `rel_area_seller` VALUES ('9', '-1', '8');
INSERT INTO `rel_area_seller` VALUES ('12', '-1', '11');
INSERT INTO `rel_area_seller` VALUES ('13', '1', '12');
INSERT INTO `rel_area_seller` VALUES ('16', '-1', '15');
INSERT INTO `rel_area_seller` VALUES ('21', '-1', '0');
INSERT INTO `rel_area_seller` VALUES ('23', '-1', '26');
INSERT INTO `rel_area_seller` VALUES ('24', '-1', null);
INSERT INTO `rel_area_seller` VALUES ('25', '-1', '27');
INSERT INTO `rel_area_seller` VALUES ('26', '-1', null);
INSERT INTO `rel_area_seller` VALUES ('27', '-1', null);
INSERT INTO `rel_area_seller` VALUES ('28', '1', null);
INSERT INTO `rel_area_seller` VALUES ('29', '1', '31');
INSERT INTO `rel_area_seller` VALUES ('30', '-1', '32');
INSERT INTO `rel_area_seller` VALUES ('31', '1', '33');
INSERT INTO `rel_area_seller` VALUES ('32', '-1', '34');

-- ----------------------------
-- Table structure for rel_business_seller
-- ----------------------------

CREATE TABLE IF NOT EXISTS `rel_business_seller` (
  `REL_BUSINESS_SELLER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATION_BUSINESS_ID` int(11) DEFAULT NULL,
  `FOOD_SELLER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REL_BUSINESS_SELLER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_business_seller
-- ----------------------------
INSERT INTO `rel_business_seller` VALUES ('9', '-1', '3');
INSERT INTO `rel_business_seller` VALUES ('10', '1', '4');
INSERT INTO `rel_business_seller` VALUES ('13', '-1', '7');
INSERT INTO `rel_business_seller` VALUES ('14', '-1', '8');
INSERT INTO `rel_business_seller` VALUES ('15', '-1', '9');
INSERT INTO `rel_business_seller` VALUES ('17', '-1', '11');
INSERT INTO `rel_business_seller` VALUES ('18', '-1', '12');
INSERT INTO `rel_business_seller` VALUES ('21', '-1', '15');
INSERT INTO `rel_business_seller` VALUES ('26', '-1', '0');
INSERT INTO `rel_business_seller` VALUES ('28', '-1', '26');
INSERT INTO `rel_business_seller` VALUES ('29', '-1', null);
INSERT INTO `rel_business_seller` VALUES ('30', '-1', '27');
INSERT INTO `rel_business_seller` VALUES ('31', '1', null);
INSERT INTO `rel_business_seller` VALUES ('32', '-1', null);
INSERT INTO `rel_business_seller` VALUES ('33', '-1', null);
INSERT INTO `rel_business_seller` VALUES ('34', '-1', '31');
INSERT INTO `rel_business_seller` VALUES ('35', '-1', '32');
INSERT INTO `rel_business_seller` VALUES ('36', '-1', '33');
INSERT INTO `rel_business_seller` VALUES ('37', '1', '34');

-- ----------------------------
-- Table structure for rel_food_seller_type
-- ----------------------------

CREATE TABLE IF NOT EXISTS `rel_food_seller_type` (
  `REL_FOOD_SELLER_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FOOD_SELLER_ID` int(11) DEFAULT NULL,
  `FOOD_SELLER_TYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REL_FOOD_SELLER_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_food_seller_type
-- ----------------------------
INSERT INTO `rel_food_seller_type` VALUES ('2', '3', '-2');
INSERT INTO `rel_food_seller_type` VALUES ('3', '4', '-1');
INSERT INTO `rel_food_seller_type` VALUES ('5', '26', '-1');
INSERT INTO `rel_food_seller_type` VALUES ('6', null, '-2');
INSERT INTO `rel_food_seller_type` VALUES ('7', '27', '-1');
INSERT INTO `rel_food_seller_type` VALUES ('8', null, '-1');
INSERT INTO `rel_food_seller_type` VALUES ('9', null, '-2');
INSERT INTO `rel_food_seller_type` VALUES ('10', null, '-1');
INSERT INTO `rel_food_seller_type` VALUES ('11', '31', '-1');
INSERT INTO `rel_food_seller_type` VALUES ('12', '32', '-1');
INSERT INTO `rel_food_seller_type` VALUES ('13', '33', '-2');
INSERT INTO `rel_food_seller_type` VALUES ('14', '34', '-2');

-- ----------------------------
-- Table structure for rel_privilege_role
-- ----------------------------

CREATE TABLE IF NOT EXISTS `rel_privilege_role` (
  `REL_PRIVILEGE_ROLE_ID` int(255) NOT NULL AUTO_INCREMENT,
  `PRIVILEGE_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REL_PRIVILEGE_ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_privilege_role
-- ----------------------------
INSERT INTO `rel_privilege_role` VALUES ('-1', '-1', '-1');
INSERT INTO `rel_privilege_role` VALUES ('1', '1', '6');
INSERT INTO `rel_privilege_role` VALUES ('2', '2', '6');
INSERT INTO `rel_privilege_role` VALUES ('3', '-1', null);
INSERT INTO `rel_privilege_role` VALUES ('4', '1', null);
INSERT INTO `rel_privilege_role` VALUES ('5', '-1', null);
INSERT INTO `rel_privilege_role` VALUES ('6', '1', null);
INSERT INTO `rel_privilege_role` VALUES ('7', '-1', '9');
INSERT INTO `rel_privilege_role` VALUES ('8', '1', '9');
INSERT INTO `rel_privilege_role` VALUES ('9', '-1', '15');
INSERT INTO `rel_privilege_role` VALUES ('10', '1', '15');
INSERT INTO `rel_privilege_role` VALUES ('11', '2', '15');
INSERT INTO `rel_privilege_role` VALUES ('14', '1', '13');
INSERT INTO `rel_privilege_role` VALUES ('15', '3', '-1');
INSERT INTO `rel_privilege_role` VALUES ('16', '3', '17');

-- ----------------------------
-- Table structure for rel_user_role
-- ----------------------------

CREATE TABLE IF NOT EXISTS `rel_user_role` (
  `REL_USER_ROLE_ID` int(255) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REL_USER_ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_user_role
-- ----------------------------
INSERT INTO `rel_user_role` VALUES ('-1', '-1', '-1');
INSERT INTO `rel_user_role` VALUES ('1', null, '-1');
INSERT INTO `rel_user_role` VALUES ('2', null, '6');
INSERT INTO `rel_user_role` VALUES ('3', null, '8');
INSERT INTO `rel_user_role` VALUES ('4', '3', '-1');
INSERT INTO `rel_user_role` VALUES ('5', null, '-1');
INSERT INTO `rel_user_role` VALUES ('7', '6', '-1');
INSERT INTO `rel_user_role` VALUES ('8', '6', '5');
INSERT INTO `rel_user_role` VALUES ('9', '5', '-1');

-- ----------------------------
-- Table structure for r_order_form_food
-- ----------------------------

CREATE TABLE IF NOT EXISTS `r_order_form_food` (
  `ORDER_FORM_FOOD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_FORM_ID` int(11) DEFAULT NULL,
  `FOOD_ID` int(11) DEFAULT NULL,
  `AMOUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_FORM_FOOD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_order_form_food
-- ----------------------------
