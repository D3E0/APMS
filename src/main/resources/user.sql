/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : apms

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-16 16:25:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `institute` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1170199024', '李海洋', '男', '机械学院', '机械1701');
INSERT INTO `user` VALUES ('1170199172', '范佳乐', '女', '信息学院', '软工171');
INSERT INTO `user` VALUES ('1170199201', '胡人杰', '男', '信息学院', '软工171');
INSERT INTO `user` VALUES ('1170210012', '李宁', '男', '信息学院', '计科171');
INSERT INTO `user` VALUES ('1170210022', '朱俊凯', '男', '信息学院', '计科171');
INSERT INTO `user` VALUES ('1170210046', '蒋乐欣', '男', '信息学院', '计算机172');
INSERT INTO `user` VALUES ('1170210053', '李少杰', '男', '信息学院', '计算机172');
INSERT INTO `user` VALUES ('1170210054', '沈春杰', '男', '信息学院', '计科172');
INSERT INTO `user` VALUES ('1170220008', '刘焮萍', '女', '信息学院', '软工172');
INSERT INTO `user` VALUES ('1170220026', '魏泽钜', '男', '信息学院', '通信171');
INSERT INTO `user` VALUES ('1170220047', '张浩波', '男', '信息学院', '通信172');
INSERT INTO `user` VALUES ('1170230010', '肖泽霖', '男', '信息学院', '电信171');
INSERT INTO `user` VALUES ('1170232016', '陈杰', '男', '信息学院', '物联网工程171');
INSERT INTO `user` VALUES ('1170280050', '苗田雨', '男', '信息学院', '数字媒体技术172');
INSERT INTO `user` VALUES ('1170280059', '陈森然', '男', '信息学院', '数字媒体技术172');
INSERT INTO `user` VALUES ('1170290049', '阮治玮', '男', '信息学院', '软件工程172');
INSERT INTO `user` VALUES ('1170290059', '凌锦涛', '男', '信息学院', '软工172');
INSERT INTO `user` VALUES ('1170290064', '孙浩', '男', '信息学院', '软件工程172');
