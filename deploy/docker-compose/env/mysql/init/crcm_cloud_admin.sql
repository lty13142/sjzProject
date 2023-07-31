
/*
 Navicat Premium Data Transfer

 Source Server         : 18研发部服务器
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 10.150.1.18:3306
 Source Schema         : crcm_cloud_admin

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 11/10/2021 16:15:35
*/

DROP DATABASE IF EXISTS `crcm_cloud_admin`;

CREATE DATABASE  `crcm_cloud_admin` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `crcm_cloud_admin`;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `file_name` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `size` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `path` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `suffix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传文件/合并文件的格式',
  `save_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储文件名',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  `upload_mode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件上传方式',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传文件的MD5',
  `chunk_count` int NULL DEFAULT NULL COMMENT '分片数量',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储桶名称',
  `upload_status` int NULL DEFAULT NULL COMMENT '上传状态 0.未上传 1.已上传部分 2 上传完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统附件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
INSERT INTO `sys_attachment` VALUES ('34b2c1c4b47647e8ae045041c7a9b924', '530fff8c48d5b9041238e57b', '44.08 kB', 'image/avatar/1/1625125881635-530fff8c48d5b9041238e57b.jpeg', '.jpeg', '1625125881635-530fff8c48d5b9041238e57b.jpeg', NULL, '2021-07-01 15:51:22', 0, '3', '7b6b059c951d1676a154bb3cdae8113c', NULL, 'test', 2);
INSERT INTO `sys_attachment` VALUES ('e35fa7e9cf19404fa8e67e95571c6d0b', '52a00c9648d5b93883e90755', '43.8 kB', 'image/avatar/1/1625129836475-52a00c9648d5b93883e90755.jpeg', '.jpeg', '1625129836475-52a00c9648d5b93883e90755.jpeg', NULL, '2021-07-01 16:57:17', 0, '3', 'dc7f4170d776e672a71e3277f0b543b8', NULL, 'test', 2);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint NULL DEFAULT NULL COMMENT '父ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `comments` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典内容',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  `sort_order` int NULL DEFAULT NULL COMMENT '排列顺序',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典代码',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `enabled` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用 0未启用，1启用，-1停用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, NULL, '设备类型', NULL, '设备类型字典', NULL, '2021-04-12 11:55:24', NULL, '2021-04-12 11:55:24', 0, 5, 'DEVICE_TYPE', '0', '1');
INSERT INTO `sys_dict` VALUES (2, 1, 'RFID设备', '3', 'RFID设备', NULL, '2021-04-12 11:55:25', NULL, '2021-04-12 11:55:25', 0, 3, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (3, 1, '水气监测设备', '4', '水气监测设备', NULL, '2021-04-12 11:55:25', NULL, '2021-04-12 11:55:25', 0, 4, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (4, 1, '摄像头', '1', '摄像头', NULL, '2021-04-12 11:55:25', NULL, '2020-07-03 17:30:17', 0, 6, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (5, 1, 'GPS设备', '2', 'GPS设备', NULL, '2021-04-12 11:55:25', NULL, '2021-04-12 11:55:25', 0, 2, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (6, 1, '数采仪设备', '5', '数采仪设备', NULL, '2021-04-12 11:55:25', NULL, '2021-04-12 11:55:25', 0, 5, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (7, NULL, '学历', NULL, '学历字典', NULL, '2019-06-22 16:05:46', NULL, '2019-06-22 16:05:46', 0, 1, 'EDUCATION', '0', '1');
INSERT INTO `sys_dict` VALUES (8, 7, '初中', '2', '初中学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 2, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (9, 7, '研究生', '6', '研究生学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 6, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (10, 7, '博士后', '8', '博士后学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 8, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (11, 7, '无', '0', '无学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 0, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (12, 7, '博士', '7', '博士学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 7, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (13, 7, '本科', '5', '本科学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 5, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (14, 7, '小学', '1', '小学学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 1, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (15, 7, '高中', '3', '高中学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 3, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (16, 7, '专科', '4', '专科学历', NULL, '2019-06-22 16:06:08', NULL, '2019-06-22 16:06:08', 0, 4, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (17, NULL, '性别', NULL, '人员性别', NULL, '2019-06-22 15:34:11', NULL, '2019-06-22 15:34:11', 0, 2, 'SEX', '0', '1');
INSERT INTO `sys_dict` VALUES (18, 17, '男', '0', '性别男', NULL, '2019-06-22 15:34:39', NULL, '2019-06-22 15:34:39', 0, 0, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (19, 17, '女', '1', '性别女', NULL, '2019-06-22 15:34:54', NULL, '2019-06-22 15:34:54', 0, 1, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (20, 17, '保密', '2', '性别保密', NULL, '2019-06-22 15:46:22', NULL, '2019-06-22 15:46:22', 0, 2, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (21, NULL, '民族', NULL, '民族字典', NULL, '2019-07-09 10:07:43', NULL, '2019-07-09 10:07:43', 0, 3, 'NATION', '0', '1');
INSERT INTO `sys_dict` VALUES (22, 21, '德昂族', '45', '德昂族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 45, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (23, 21, '回族', '2', '回族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 2, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (24, 21, '保安族', '46', '保安族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 46, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (25, 21, '蒙古族', '1', '蒙古族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 1, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (26, 21, '景颇族', '27', '景颇族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 27, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (27, 21, '裕固族', '47', '裕固族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 47, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (28, 21, '俄罗斯族', '43', '俄罗斯族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 43, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (29, 21, '羌族', '32', '羌族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 32, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (30, 21, '仡佬族', '36', '仡佬族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 36, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (31, 21, '高山族', '22', '高山族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 22, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (32, 21, '土家族', '14', '土家族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 14, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (33, 21, '布朗族', '33', '布朗族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 33, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (34, 21, '拉祜族', '23', '拉祜族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 23, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (35, 21, '鄂伦春族', '51', '鄂伦春族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 51, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (36, 21, '锡伯族', '37', '锡伯族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 37, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (37, 21, '傣族', '17', '傣族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 17, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (38, 21, '阿昌族', '38', '阿昌族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 38, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (39, 21, '汉族', '0', '汉族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 0, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (40, 21, '塔吉克族', '40', '塔吉克族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 40, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (41, 21, '朝鲜族', '9', '朝鲜族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 9, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (42, 21, '撒拉族', '34', '撒拉族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 34, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (43, 21, '苗族', '5', '苗族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 5, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (44, 21, '赫哲族', '52', '赫哲族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 52, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (45, 21, '维吾尔族', '4', '维吾尔族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 4, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (46, 21, '佤族', '20', '佤族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 20, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (47, 21, '柯尔克孜族', '28', '柯尔克孜族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 28, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (48, 21, '普米族', '39', '普米族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 39, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (49, 21, '东乡族', '25', '东乡族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 25, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (50, 21, '基诺族', '55', '基诺族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 55, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (51, 21, '珞巴族', '54', '珞巴族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 54, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (52, 21, '乌孜别克族', '42', '乌孜别克族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 42, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (53, 21, '壮族', '7', '壮族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 7, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (54, 21, '彝族', '6', '彝族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 6, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (55, 21, '水族', '24', '水族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 24, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (56, 21, '达翰尔族', '30', '达翰尔族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 30, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (57, 21, '怒族', '41', '怒族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 41, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (58, 21, '独龙族', '50', '独龙族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 50, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (59, 21, '藏族', '3', '藏族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 3, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (60, 21, '满族', '10', '满族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 10, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (61, 21, '么佬族', '31', '么佬族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 31, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (62, 21, '瑶族', '12', '瑶族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 12, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (63, 21, '畲族', '21', '畲族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 21, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (64, 21, '塔塔尔族', '49', '塔塔尔族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 49, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (65, 21, '白族', '13', '白族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 13, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (66, 21, '门巴族', '53', '门巴族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 53, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (67, 21, '鄂温克族', '44', '鄂温克族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 44, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (68, 21, '黎族', '18', '黎族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 18, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (69, 21, '侗族', '11', '侗族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 11, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (70, 21, '土族', '29', '土族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 29, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (71, 21, '哈萨克族', '16', '哈萨克族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 16, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (72, 21, '毛南族', '35', '毛南族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 35, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (73, 21, '傈僳族', '19', '傈僳族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 19, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (74, 21, '哈尼族', '15', '哈尼族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 15, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (75, 21, '布依族', '8', '布依族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 8, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (76, 21, '京族', '48', '京族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 48, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (77, 21, '纳西族', '26', '纳西族', NULL, '2019-07-09 14:16:05', NULL, '2019-07-09 14:16:05', 0, 26, NULL, '1', '1');
INSERT INTO `sys_dict` VALUES (82, 17, '1', '1', '1', NULL, '2021-06-08 16:32:31', NULL, '2021-06-08 16:32:31', 1, 3, NULL, '1', '1');

-- ----------------------------
-- Table structure for sys_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_gateway_route`;
CREATE TABLE `sys_gateway_route`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `route_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '路由ID',
  `predicates` json NULL COMMENT '断言',
  `filters` json NULL COMMENT '过滤器',
  `uri` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路由地址',
  `order` int NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否有效 0无效 1有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路由配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_gateway_route
-- ----------------------------
INSERT INTO `sys_gateway_route` VALUES (1, '管理模块', 'admin-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}]', 'lb://crcm-cloud-service-admin', 1, '2021-05-10 14:42:50', '2021-06-24 03:58:38', '0', 1);
INSERT INTO `sys_gateway_route` VALUES (2, '认证中心', 'auth-service', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://crcm-cloud-service-auth', 2, '2021-05-10 14:42:50', '2021-06-29 02:41:45', '0', 1);
INSERT INTO `sys_gateway_route` VALUES (7, '文件服务', 'file-service', '[{\"args\": {\"_genkey_0\": \"/file/**\"}, \"name\": \"Path\"}]', '[]', 'lb://crcm-cloud-service-file', 3, '2021-07-01 11:28:06', '2021-07-01 05:55:46', '0', 0);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `request_ip` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作IP',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型（1 操作日志、2异常日志）',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `action_method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `response_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回参数(json格式)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` int NULL DEFAULT NULL COMMENT '版本',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  `action_url` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求URL',
  `params` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `ua` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器信息',
  `class_path` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类路径',
  `request_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `start_time` bigint NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` bigint NULL DEFAULT NULL COMMENT '结束时间',
  `consuming_time` bigint NULL DEFAULT NULL COMMENT '消耗时间',
  `ex_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常详情信息 堆栈信息',
  `ex_desc` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常描述 e.getMessage',
  `log_level` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '代码,用于排序',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件/内容',
  `pid` bigint NULL DEFAULT NULL COMMENT '父节ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器访问路径',
  `redirect` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向页面路径',
  `button_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '按钮类型',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类别（0 菜单，1 按钮）',
  `enabled` tinyint(1) NOT NULL COMMENT '是否启用 1启用 0禁用',
  `deleted` int NOT NULL COMMENT '是否删除',
  `hidden` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏 1是 0否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '后台管理', 'system-manage', '0', 'Layout', NULL, '系统后台管理', '/admin', NULL, NULL, 'admin', '2019-11-01 14:00:53', 'admin', '2019-11-01 14:00:53', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (2, '基本管理', 'example', '01', '/system/base', 1, '', '/admin/base', '/base/user', '', '', '2019-04-29 15:25:40', '', '2021-07-02 15:54:21', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (3, '信息管理', 'example', '02', '/system/info', 1, NULL, '/admin/info', NULL, NULL, 'admin', '2019-11-06 10:49:09', NULL, '2019-11-06 10:49:09', 0, 1, 1, 0);
INSERT INTO `sys_menu` VALUES (4, '系统管理', 'example', '02', '/system/manage', 1, '', '/admin/manage', '', '', '', '2019-05-27 09:11:31', '', '2021-07-01 17:16:36', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (5, '运维管理', 'example', '05', '/system/safety', 1, '', '/admin/system/maintain', '', '', '', '2019-05-27 09:04:01', '', '2021-07-01 17:33:24', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (6, '用户管理', 'list', '01001', '/system/base/user', 2, '', '/admin/base/user', NULL, NULL, 'admin', '2019-09-24 09:52:12', 'admin', '2019-09-24 09:52:12', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (7, '角色管理', 'peoples', '01002', '/system/base/role', 2, NULL, '/admin/base/role', NULL, NULL, '', '2019-04-29 15:25:40', NULL, '2019-04-29 15:25:40', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (8, '组织管理', 'tree-table', '01003', '/system/base/org', 2, '', '/admin/base/org/org-manage', '', '', '', '2019-05-24 17:51:40', '', '2021-07-02 15:54:43', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (9, '权限管理', 'lock', '01005', '/system/base/permission', 2, '', '/admin/base/permission', '', '', '', '2019-05-24 17:54:02', '', '2021-07-02 15:24:15', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (10, '字典管理', 'dictionary', '0404', '/system/manage/dict', 4, NULL, '/admin/info/dict', NULL, NULL, '', '2019-05-27 08:56:28', 'admin', '2019-05-27 08:56:28', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (13, '菜单管理', 'list', '0401', '/system/manage/menu', 4, NULL, '/admin/manage/menu', '', NULL, '', '2019-04-29 15:25:40', '', '2019-04-29 15:25:40', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (15, '日志管理', 'logs', '0501', '/system/safety/log', 5, NULL, '/admin/system/log', NULL, NULL, '', '2019-05-27 09:05:05', 'admin', '2019-05-27 09:05:05', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (16, '运维监控', 'monitor', '0503', '/system/safety/devOps', 5, NULL, '/admin/system/devOps', NULL, NULL, '', '2019-05-27 09:10:36', 'admin', '2019-05-27 09:10:36', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (17, '添加用户', 'button', 'add', '', 6, '添加系统用户', '', '', 'add', 'admin', '2019-07-03 15:30:01', 'admin', '2021-06-25 16:17:28', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (18, '编辑用户', 'button', 'edit', '', 6, '修改用户信息', '', '', 'edit', 'admin', '2019-07-03 16:08:07', '', '2021-06-25 16:18:58', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (19, '删除用户', 'button', 'delete', '', 6, '删除系统用户', '', '', 'delete', 'admin', '2019-07-03 16:08:34', '', '2021-06-25 16:18:45', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (20, '资源权限', 'list', '0100502', '/system/base/permission/resource', 9, NULL, '/admin/base/permission/resource', NULL, NULL, '', '2019-05-24 17:56:34', 'admin', '2019-05-24 17:56:34', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (21, '菜单权限', 'list', '0100501', '/system/base/permission/menu', 9, NULL, '/admin/base/permission/menu', NULL, NULL, '', '2019-05-24 17:55:37', NULL, '2019-05-24 17:55:37', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (29, '资源管理', 'excel', '0402', '/system/manage/resource', 4, '系统资源管理', '/admin/manage/resource', '', '', 'admin', '2021-04-15 15:08:39', '', '2021-04-15 15:08:39', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (30, '角色授权', 'peoples', '0100503', '/system/base/permission/role', 9, '管理用户授权角色', '/admin/base/permission/role', NULL, NULL, 'admin', '2021-04-19 15:48:35', NULL, '2021-04-19 15:48:35', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (31, '计划任务', 'plan', '0403', '/system/manage/task', 4, '系统定时任务管理', '/admin/manage/task', '', '', 'admin', '2021-04-19 19:19:48', '', '2021-04-19 19:19:48', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (38, '客户端管理', 'international', '04', '/system/auth/client', 52, '系统客户端授权管理', '/admin/auth/client', '', '', '', '2021-06-22 17:40:50', '', '2021-07-01 17:27:32', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (39, '路由管理', 'guide', '03', '/system/gateway/route', 51, '系统路由管理', '/admin/gateway/route', '', '', '', '2021-06-22 17:46:32', '', '2021-07-01 17:27:21', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (41, '系统设置', 'setup', '0408', '/system/manage/settings', 4, '系统设置菜单', '/admin/manage/settings', '', '', 'admin', '2021-06-25 00:00:00', 'admin', '2021-06-25 15:34:23', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (42, '查询系统设置', 'button', 'view', NULL, 41, '系统设置查询', NULL, NULL, 'view', 'admin', '2021-06-25 00:00:00', 'admin', '2021-06-25 00:00:00', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (43, '新增系统设置', 'button', 'add', NULL, 41, '系统设置新增', NULL, NULL, 'add', 'admin', '2021-06-25 00:00:00', 'admin', '2021-06-25 00:00:00', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (44, '修改系统设置', 'button', 'edit', NULL, 41, '系统设置修改', NULL, NULL, 'edit', 'admin', '2021-06-25 00:00:00', 'admin', '2021-06-25 00:00:00', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (45, '删除系统设置', 'button', 'delete', NULL, 41, '系统设置删除', NULL, NULL, 'delete', 'admin', '2021-06-25 00:00:00', 'admin', '2021-06-25 00:00:00', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (46, '导出系统设置', 'button', 'export', NULL, 41, '系统设置导出', NULL, NULL, 'export', 'admin', '2021-06-25 00:00:00', 'admin', '2021-06-25 00:00:00', 1, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (47, '新增客户端', 'button', 'add', '9', 38, '新增客户端', '', '', 'add', '', '2021-06-28 10:29:32', '', '2021-06-28 10:34:27', 1, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (48, '修改客户端', 'button', 'edit', '0', 38, '客户端修改按钮', '', '', 'edit', '', '2021-06-28 10:29:32', '', '2021-06-28 10:33:13', 1, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (49, '查询客户端', 'button', 'view', '12', 38, '客户端查询', NULL, NULL, 'view', NULL, '2021-06-28 10:41:59', NULL, '2021-06-28 10:41:59', 1, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (50, '删除客户端', 'button', 'delete', '11', 38, '客户端删除', NULL, NULL, 'delete', NULL, '2021-06-28 10:42:27', NULL, '2021-06-28 10:42:27', 1, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (51, '网关管理', 'tree', '03', '/system/gateway', 1, '网关管理模块', '/admin/gateway', '', '', '', '2021-07-01 17:16:19', '', '2021-07-01 17:32:51', 0, 1, 0, 0);
INSERT INTO `sys_menu` VALUES (52, '认证管理', 'lock', '04', '/system/auth', 1, '认证管理模块', '/admin/auth', '', '', '', '2021-06-22 17:40:50', '', '2021-07-01 17:33:06', 0, 1, 0, 0);

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端(client)的访问密匙',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: \"ROLE_UNITY,ROLE_USER\"',
  `access_token_validity` int NULL DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)',
  `refresh_token_validity` int NULL DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:\r\n{\"country\":\"CN\",\"country_code\":\"086\"}',
  `auto_approve` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置用户是否自动Approval操作, 默认值为 \'false\', 可选值包括 \'true\',\'false\', \'read\',\'write\'.\\n\" +\r\n            \"该字段只适用于grant_type=\\\"authorization_code\\\"的情况,当用户登录成功后,若该值为\'true\'或支持的scope值,则会跳过用户Approve的页面, 直接授权\\n',
  `client_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端名称',
  `client_type` tinyint(1) NOT NULL COMMENT '客户端类型 0 内部客户端 1外部客户',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('bis-order', NULL, '$2a$10$1lRuxYnlEkOicDJ3Aroxouxl4OAIsan379fkKN6ofpLnwba74S9.6', 'all', 'authorization_code,password,refresh_token,client_credentials,implicit', 'http://localhost:8852/login', NULL, 43200, NULL, NULL, 'false', '订单服务端', 0, NULL, 1);
INSERT INTO `sys_oauth_client_details` VALUES ('bis-product', NULL, '$2a$10$1lRuxYnlEkOicDJ3Aroxouxl4OAIsan379fkKN6ofpLnwba74S9.6', 'all', 'authorization_code,password,refresh_token,client_credentials,implicit', 'http://localhost:8853/login', NULL, 43200, NULL, NULL, 'false', '产品服务端', 0, NULL, 2);
INSERT INTO `sys_oauth_client_details` VALUES ('crcm-cloud', NULL, '$2a$10$mNa6k2pX4zsRRMYHCWj0dOlBdMwXX7cP0Y0LkdY/snlqsaSBC9LKS', 'all', 'authorization_code,password,refresh_token,client_credentials,implicit', 'http://localhost:8868/login', NULL, 43200, NULL, NULL, 'false', '框架客户端', 0, NULL, 3);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织代码',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级',
  `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `comments` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织类型',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门组织' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, '系统管理员', 'ADMINISTRATOR', NULL, '00', '系统管理员', NULL, '2021-06-22 17:10:39', NULL, '2021-06-22 17:10:39', 0, '1', NULL);
INSERT INTO `sys_org` VALUES (2, '用户', 'USER', NULL, '01', '系统用户', '', '2021-06-22 17:20:18', '', '2021-06-22 17:21:56', 0, '0', '');
INSERT INTO `sys_org` VALUES (13, '普通用户', 'NORMAL_USER', 2, '0101', '普通用户', NULL, '2021-06-22 17:21:18', NULL, '2021-06-22 17:21:18', 0, '0', NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限ID',
  `type` int NULL DEFAULT NULL COMMENT '权限类型（0 菜单权限，1数据权限）'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 1, 1);
INSERT INTO `sys_permission` VALUES (1, 4, 1);
INSERT INTO `sys_permission` VALUES (1, 5, 1);
INSERT INTO `sys_permission` VALUES (1, 6, 1);
INSERT INTO `sys_permission` VALUES (1, 7, 1);
INSERT INTO `sys_permission` VALUES (1, 8, 1);
INSERT INTO `sys_permission` VALUES (1, 9, 1);
INSERT INTO `sys_permission` VALUES (1, 10, 1);
INSERT INTO `sys_permission` VALUES (1, 11, 1);
INSERT INTO `sys_permission` VALUES (1, 12, 1);
INSERT INTO `sys_permission` VALUES (1, 1, 0);
INSERT INTO `sys_permission` VALUES (1, 2, 0);
INSERT INTO `sys_permission` VALUES (1, 6, 0);
INSERT INTO `sys_permission` VALUES (1, 17, 0);
INSERT INTO `sys_permission` VALUES (1, 19, 0);
INSERT INTO `sys_permission` VALUES (1, 18, 0);
INSERT INTO `sys_permission` VALUES (1, 7, 0);
INSERT INTO `sys_permission` VALUES (1, 8, 0);
INSERT INTO `sys_permission` VALUES (1, 9, 0);
INSERT INTO `sys_permission` VALUES (1, 21, 0);
INSERT INTO `sys_permission` VALUES (1, 20, 0);
INSERT INTO `sys_permission` VALUES (1, 30, 0);
INSERT INTO `sys_permission` VALUES (1, 4, 0);
INSERT INTO `sys_permission` VALUES (1, 13, 0);
INSERT INTO `sys_permission` VALUES (1, 29, 0);
INSERT INTO `sys_permission` VALUES (1, 31, 0);
INSERT INTO `sys_permission` VALUES (1, 10, 0);
INSERT INTO `sys_permission` VALUES (1, 41, 0);
INSERT INTO `sys_permission` VALUES (1, 43, 0);
INSERT INTO `sys_permission` VALUES (1, 45, 0);
INSERT INTO `sys_permission` VALUES (1, 44, 0);
INSERT INTO `sys_permission` VALUES (1, 46, 0);
INSERT INTO `sys_permission` VALUES (1, 42, 0);
INSERT INTO `sys_permission` VALUES (1, 51, 0);
INSERT INTO `sys_permission` VALUES (1, 39, 0);
INSERT INTO `sys_permission` VALUES (1, 52, 0);
INSERT INTO `sys_permission` VALUES (1, 38, 0);
INSERT INTO `sys_permission` VALUES (1, 47, 0);
INSERT INTO `sys_permission` VALUES (1, 50, 0);
INSERT INTO `sys_permission` VALUES (1, 48, 0);
INSERT INTO `sys_permission` VALUES (1, 49, 0);
INSERT INTO `sys_permission` VALUES (1, 5, 0);
INSERT INTO `sys_permission` VALUES (1, 15, 0);
INSERT INTO `sys_permission` VALUES (1, 16, 0);
INSERT INTO `sys_permission` VALUES (1, 9, 1);
INSERT INTO `sys_permission` VALUES (1, 0, 1);
INSERT INTO `sys_permission` VALUES (1, 12, 1);
INSERT INTO `sys_permission` VALUES (1, 11, 1);

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务类全类名',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `parameter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` int NULL DEFAULT NULL COMMENT '状态 0-未启动 1-已启动 2-已暂停 -1-停止',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '删除状态',
  `type` int NULL DEFAULT NULL COMMENT '任务类型 1-系统任务 2-业务任务',
  `last_start_time` datetime NULL DEFAULT NULL COMMENT '上次启动时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务调度' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (1, '测试任务1', 'com.crcm.base.task.TestJob2', '0/1 * * * * ?', '', 2, '测试任务', 'admin', '2020-01-09 11:31:29', 'admin', '2021-04-19 19:42:58', 0, 1, '2021-04-19 19:40:41');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源值',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型，0 功能，1 目录',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用 1启用 -1禁用',
  `auth_type` tinyint(1) NULL DEFAULT NULL COMMENT '鉴权方式 1 权限匹配，2 路径匹配',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统资源' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '用户管理', '/admin/user/**', NULL, NULL, NULL, '2021-06-25 17:05:43', NULL, '2021-06-25 17:05:43', 0, 1, NULL, NULL);
INSERT INTO `sys_resource` VALUES (4, '新增用户', '/admin/user/save', 1, '添加用户', NULL, '2021-06-25 17:42:40', NULL, '2021-06-25 17:42:40', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (5, '修改用户', '/admin/user/update', 1, '用户信息修改', '', '2021-06-25 17:49:27', '', '2021-06-25 17:50:15', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (6, '删除用户', '/admin/user/delete', 1, '用户信息删除', NULL, '2021-06-25 17:50:52', NULL, '2021-06-25 17:50:52', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (7, '查询用户', '/admin/user/page', 1, '用户信息查询', NULL, '2021-06-25 17:51:36', NULL, '2021-06-25 17:51:36', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (8, '客户端管理', NULL, NULL, NULL, NULL, '2021-06-28 10:04:53', NULL, '2021-06-28 10:04:53', 0, 1, NULL, NULL);
INSERT INTO `sys_resource` VALUES (9, '新增客户端', '/admin/clientr/save', 8, '客户端新增', NULL, '2021-06-28 10:05:23', NULL, '2021-06-28 10:05:23', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (10, '修改客户端', '/admin/clientr/update', 8, '客户端修改', NULL, '2021-06-28 10:07:43', NULL, '2021-06-28 10:07:43', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (11, '删除客户端', '/admin/clientr/delete', 8, '客户端删除', NULL, '2021-06-28 10:08:24', NULL, '2021-06-28 10:08:24', 0, 0, 1, 2);
INSERT INTO `sys_resource` VALUES (12, '查询客户端', '/admin/clientr/page', 8, '客户端查询', NULL, '2021-06-28 10:08:47', NULL, '2021-06-28 10:08:47', 0, 0, 1, 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权标识',
  `organize` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构',
  `enabled` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '是否可用 1:是 0:否',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'ADMIN', 'SYSTEM', 1, '2019-04-29 14:35:57', '2019-04-29 14:35:57', 0);
INSERT INTO `sys_role` VALUES (2, '普通用户', 'USER', 'SYSTEM', 1, '2019-04-29 14:46:19', '2019-04-29 14:46:19', 0);

-- ----------------------------
-- Table structure for sys_settings
-- ----------------------------
DROP TABLE IF EXISTS `sys_settings`;
CREATE TABLE `sys_settings`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置代码',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置值',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统设置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_settings
-- ----------------------------
INSERT INTO `sys_settings` VALUES (2, '系统用户默认头像1', 'SYSTEM_DEFAULT_AVATAR', 'https://tupian.zhongzaiyuntu.com/test/image/avatar/4/1622018221486-default_acatar.jpeg', 'admin', '2021-04-27 17:53:33', '', '2021-06-25 15:54:56', 0);

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '租户状态 0未启用 1启用 -1 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统租户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '账户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `enabled` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否启用 1:启用 0:未启用',
  `locked` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否锁定 1:锁定 0:未锁定 ',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除 1:删除 0:未删除',
  `expired` tinyint(1) NOT NULL COMMENT '是否过期 1:过期 0:未过期',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `avatar` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自定义头像',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` int NULL DEFAULT NULL COMMENT '组织ID',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$vcuJkT5cukZff6xQv0eQ4uwseujNrY4k9JJd6Hxe.ZkLcR8U0tv1a', 1, 0, 0, 0, '2021-03-22 09:06:31', '2021-10-11 15:58:22', '15231241222', 'diaoyunnie@qq.com', '34b2c1c4b47647e8ae045041c7a9b924', '系统管理员', '', 1);
INSERT INTO `sys_user` VALUES (2, 'admin1', '$2a$10$ODmBx3hhzVbNlpUUpOI3i.LmKZqF8isqMvx1ZygQTeKWc7qSRboQW', 1, 0, 0, 0, '2021-03-30 10:31:26', '2021-03-30 10:31:29', NULL, NULL, NULL, '系统管理员1', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_detail`;
CREATE TABLE `sys_user_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `id_card_pic` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证照片（附件ID）',
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '毕业学校',
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `comments` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `person_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '人员编号',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` int NULL DEFAULT NULL COMMENT '是否删除',
  `face_pic` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件照，面部识别照',
  `current_address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '现居地址',
  `native_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `nation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '民族',
  `birthday` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `sort_order` int NULL DEFAULT NULL COMMENT '排列顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_detail
-- ----------------------------
INSERT INTO `sys_user_detail` VALUES (1, 3, '1111', '0', '11111', '', '111', '5', '111111', '111', '', '1111', '3', 'admin', '2021-04-13 16:28:26', 'admin', '2021-04-13 16:28:26', NULL, '4d2173eef4fa4595a1e0bd98b8cf382b', '11111', '11111111', '汉族', '2021-04-13 00:00:00', NULL);
INSERT INTO `sys_user_detail` VALUES (2, 4, '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2021-04-13 16:31:06', NULL, '2021-04-13 16:31:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_detail` VALUES (3, 5, '1111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2021-04-13 19:29:27', NULL, '2021-04-13 19:29:27', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_detail` VALUES (4, 6, '11111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2021-04-19 18:59:17', NULL, '2021-04-19 18:59:17', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_detail` VALUES (5, 7, '4241241', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-22 15:17:59', NULL, '2021-06-22 15:17:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
