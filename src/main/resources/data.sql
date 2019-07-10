/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : Halo

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 10/07/2019 10:44:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of app
-- ----------------------------
BEGIN;
INSERT INTO `app` VALUES (1, 'first_app', '第一个应用', 'first_app_key', 'first_bundle_id', 'first_package_name', 'first_google_public_key', 'first_notify_url', '2019-07-10 02:41:49.164', '2019-07-10 02:42:14.647');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
