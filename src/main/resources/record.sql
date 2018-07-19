/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : apms

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-16 16:24:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `userId` varchar(255) NOT NULL,
  `time` timestamp NOT NULL ,
  `ip` varchar(255) DEFAULT NULL,
  `recordId` varchar(255) NOT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of record
-- ----------------------------
