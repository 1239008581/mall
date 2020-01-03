/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : mall_user

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/12/2019 10:21:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` int(11) NOT NULL COMMENT '收藏主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `object_id` int(11) NOT NULL COMMENT '收藏事物的id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏事物的类型：commodity：商品；brand：品牌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for footprint
-- ----------------------------
DROP TABLE IF EXISTS `footprint`;
CREATE TABLE `footprint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '足迹id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commondity_id` int(11) NOT NULL COMMENT '商品id',
  `time` datetime(0) NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commodity_id` int(11) NOT NULL COMMENT '商品id',
  `commodity_num` int(11) NOT NULL DEFAULT 1 COMMENT '商品数目',
  `price` float NOT NULL COMMENT '订单总价格',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '订单是否已支付 0：未支付；1：已支付',
  `create_time` datetime(0) NOT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付信息主键id',
  `user_id` int(11) NOT NULL COMMENT '支付人id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `create_time` datetime(0) NOT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `open_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户微信openId',
  `wx_nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户微信昵称',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像地址',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '用户性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `money` float NOT NULL DEFAULT 0 COMMENT '用户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
