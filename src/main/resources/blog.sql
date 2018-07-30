/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-07-29 03:18:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '正文',
  `categoryId` int(11) DEFAULT NULL COMMENT '版块id',
  `summary` text COMMENT '摘要',
  `author` varchar(20) DEFAULT 'admin' COMMENT '文章作者',
  `is_delete` varchar(2) DEFAULT 'N' COMMENT '是否删除',
  `date` varchar(64) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `display_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'firstPage', '首页');
INSERT INTO `category` VALUES ('2', 'webPage', 'JavaWeb');
INSERT INTO `category` VALUES ('3', 'scriptPage', '脚本/小程序');
INSERT INTO `category` VALUES ('4', 'otherPage', '一些好玩的小玩意儿');
INSERT INTO `category` VALUES ('5', 'talkPage', '闲谈');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `comm_ip` varchar(64) DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT '新注册用户' COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', '管理员');
