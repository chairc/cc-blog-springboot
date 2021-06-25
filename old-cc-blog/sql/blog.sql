/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 29/07/2020 00:08:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_blog_article
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_article`;
CREATE TABLE `cc_blog_article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_private_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `article_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `article_main` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `article_author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `article_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `article_click_num` int(11) NULL DEFAULT NULL,
  `article_browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `article_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `article_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_comment`;
CREATE TABLE `cc_blog_comment`  (
  `comment_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_entertainment
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_entertainment`;
CREATE TABLE `cc_blog_entertainment`  (
  `entertainment_id` int(11) NOT NULL AUTO_INCREMENT,
  `entertainment_private_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `entertainment_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `entertainment_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `entertainment_image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`entertainment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_friend_link`;
CREATE TABLE `cc_blog_friend_link`  (
  `friend_link_id` int(11) NOT NULL AUTO_INCREMENT,
  `friend_link_private_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `friend_link_user_head_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `friend_link_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `friend_link_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `friend_link_check` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`friend_link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_head_image
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_head_image`;
CREATE TABLE `cc_blog_head_image`  (
  `head_image_id` int(11) NOT NULL AUTO_INCREMENT,
  `head_image_private_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `head_image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`head_image_id`) USING BTREE,
  INDEX `head_image_id`(`head_image_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_message
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_message`;
CREATE TABLE `cc_blog_message`  (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_private_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_main` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `message_weight` int(1) NULL DEFAULT NULL,
  `message_user_private_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `message_user_private_id`(`message_user_private_id`) USING BTREE,
  CONSTRAINT `message_user_private_id` FOREIGN KEY (`message_user_private_id`) REFERENCES `cc_blog_user` (`user_common_private_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_permission
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_permission`;
CREATE TABLE `cc_blog_permission`  (
  `permission_id` int(11) NOT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `permission_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_role`;
CREATE TABLE `cc_blog_role`  (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE,
  INDEX `role_id`(`role_id`, `role_name`) USING BTREE,
  INDEX `role_id_2`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_user
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_user`;
CREATE TABLE `cc_blog_user`  (
  `user_common_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_common_private_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_common_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_common_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_common_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_common_head_image_id` int(11) NULL DEFAULT NULL,
  `user_common_friend_link_id` int(11) NULL DEFAULT NULL,
  `user_common_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_secret_real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_secret_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_secret_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_secret_birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_secret_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_secret_age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_secret_wechat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_secret_qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_secret_weibo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_secret_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_safe_logtime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_safe_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_safe_question` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_safe_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_safe_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_safe_browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_safe_weight` int(1) NULL DEFAULT NULL,
  `user_safe_permission` int(11) NULL DEFAULT NULL,
  `user_safe_role` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_common_id`, `user_common_private_id`) USING BTREE,
  INDEX `user_safe_permission`(`user_safe_permission`) USING BTREE,
  INDEX `user_safe_role`(`user_safe_role`) USING BTREE,
  INDEX `user_common_head_image_id`(`user_common_head_image_id`) USING BTREE,
  INDEX `user_common_friend_link_id`(`user_common_friend_link_id`) USING BTREE,
  INDEX `user_common_private_id`(`user_common_private_id`) USING BTREE,
  CONSTRAINT `user_common_friend_link_id` FOREIGN KEY (`user_common_friend_link_id`) REFERENCES `cc_blog_friend_link` (`friend_link_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_common_head_image_id` FOREIGN KEY (`user_common_head_image_id`) REFERENCES `cc_blog_head_image` (`head_image_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_safe_permission` FOREIGN KEY (`user_safe_permission`) REFERENCES `cc_blog_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_safe_role` FOREIGN KEY (`user_safe_role`) REFERENCES `cc_blog_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_blog_wps
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_wps`;
CREATE TABLE `cc_blog_wps`  (
  `wps_id` int(100) NOT NULL AUTO_INCREMENT,
  `wps_sid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`wps_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_demo
-- ----------------------------
DROP TABLE IF EXISTS `user_demo`;
CREATE TABLE `user_demo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
