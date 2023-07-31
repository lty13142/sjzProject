/*
 Navicat Premium Data Transfer

 Source Server         : 27mysql-Dev
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 10.150.1.27:3306
 Source Schema         : crcm_cloud_nacos

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/07/2022 11:37:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'crcm-cloud-gateway-dev', 'DEFAULT_GROUP', 'spring:\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: -1ms\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  cloud:\n    gateway:\n      discovery:\n        locator:\n        # 让gateway通过服务发现找到其他的微服务\n          enabled: true\n    # sentinel:\n    #   enabled: true # 是否开启。默认为 true 开启\n    #   eager: true # 是否饥饿加载。默认为 false 关闭\n    #   transport:\n    #     port: 8719 # 假如被占用了会自动从8719开始依次+1扫描。直至找到未被占用的端口，默认8719\n    #     dashboard: 10.150.1.30:8888 # 指定控制台服务的地址\n\n  ', '338648eba17530d4f735ea49a39936fa', '2022-07-16 16:36:20', '2022-07-16 16:36:37', 'nacos', '10.35.11.50', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (3, 'crcm-cloud-service-auth-dev', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true', '5593c533dc92cffc39277e1931dc3fb2', '2022-07-16 16:45:29', '2022-07-16 16:52:54', 'nacos', '10.35.11.50', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (10, 'crcm-cloud-service-admin-dev', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true\n  #配置rabbitMq 服务器\n  rabbitmq:\n    host: 10.150.1.27\n    port: 5672\n    username: admin\n    password: rabbit123\n\nsecurity:\n  enable: true\n  #是否限制服务只能通过网关访问\n  gateway-fetch-only: true\n  oauth2:\n    client-id: crcm-cloud-admin\n    client-secret: GT2LgqNwI1w5zrzy1iMccXHaKXBRS6MK\n    token-store:\n      # token 存储方式 jwt redis redis-custom\n      type: redis-custom\n    ignore:\n      urls:\n        - /open/**\n        - /favicon.ico\n        - /**/actuator/**\n        - /user/login/**\n        - /test/**\n    resource:\n      id: crcm-cloud-admin\n', '35c0e62f1b4d12d23d7aff5e77c1488f', '2022-07-16 17:01:08', '2022-07-16 17:01:19', 'nacos', '10.35.11.50', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (12, 'crcm-cloud-service-file-dev', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true', '5593c533dc92cffc39277e1931dc3fb2', '2022-07-16 17:27:45', '2022-07-16 17:29:40', 'nacos', '10.35.11.50', '', '', '文件服务开发配置', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'crcm-cloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  #Redis配置\r\n  redis:\r\n    #Redis索引0~15，默认为0\r\n    database: 0\r\n    host: 10.150.1.35\r\n    port: 39477\r\n    #密码（默认为空）\r\n    password: kPN65zUP\r\n    # 这里标明使用lettuce配置\r\n    lettuce:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 200\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: -1ms\r\n        #连接池中的最大空闲连接\r\n        max-idle: 10\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n        #连接超时时间（毫秒）\r\n    timeout: 10000ms\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n        # 让gateway通过服务发现找到其他的微服务\r\n          enabled: true\r\n    # sentinel:\r\n    #   enabled: true # 是否开启。默认为 true 开启\r\n    #   eager: true # 是否饥饿加载。默认为 false 关闭\r\n    #   transport:\r\n    #     port: 8719 # 假如被占用了会自动从8719开始依次+1扫描。直至找到未被占用的端口，默认8719\r\n    #     dashboard: 10.150.1.30:8888 # 指定控制台服务的地址\r\n\r\n  ', '7d2c4dac1f13eac51ee7fbcc3ea8b2b3', '2022-07-16 08:36:20', '2022-07-16 16:36:20', NULL, '10.35.11.50', 'I', '', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'crcm-cloud-gateway-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  #Redis配置\r\n  redis:\r\n    #Redis索引0~15，默认为0\r\n    database: 0\r\n    host: 10.150.1.35\r\n    port: 39477\r\n    #密码（默认为空）\r\n    password: kPN65zUP\r\n    # 这里标明使用lettuce配置\r\n    lettuce:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 200\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: -1ms\r\n        #连接池中的最大空闲连接\r\n        max-idle: 10\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n        #连接超时时间（毫秒）\r\n    timeout: 10000ms\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n        # 让gateway通过服务发现找到其他的微服务\r\n          enabled: true\r\n    # sentinel:\r\n    #   enabled: true # 是否开启。默认为 true 开启\r\n    #   eager: true # 是否饥饿加载。默认为 false 关闭\r\n    #   transport:\r\n    #     port: 8719 # 假如被占用了会自动从8719开始依次+1扫描。直至找到未被占用的端口，默认8719\r\n    #     dashboard: 10.150.1.30:8888 # 指定控制台服务的地址\r\n\r\n  ', '7d2c4dac1f13eac51ee7fbcc3ea8b2b3', '2022-07-16 08:36:37', '2022-07-16 16:36:37', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'crcm-cloud-service-auth-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true&tinyInt1isBit=false\r\n    user: root\r\n    password: 123456cc\r\n  #Redis配置\r\n  redis:\r\n    #Redis索引0~15，默认为0\r\n    database: 0\r\n    host: 10.150.1.27\r\n    port: 6379\r\n    #密码（默认为空）\r\n    password: 123456\r\n    #使用JSON序列化redis值\r\n    serialize: json\r\n    # 这里标明使用lettuce配置\r\n    lettuce:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 200\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: 10000\r\n        #连接池中的最大空闲连接\r\n        max-idle: 10\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n        #连接超时时间（毫秒）\r\n    timeout: 10000ms\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://localhost:8859\r\n        username: admin\r\n        password: zzyt@123\r\n        instance:\r\n          prefer-ip: true', '2e921394c6c40ad488667dfc61b6e476', '2022-07-16 08:45:28', '2022-07-16 16:45:29', NULL, '10.35.11.50', 'I', '', '');
INSERT INTO `his_config_info` VALUES (3, 4, 'crcm-cloud-service-auth-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true&tinyInt1isBit=false\r\n    user: root\r\n    password: 123456cc\r\n  #Redis配置\r\n  redis:\r\n    #Redis索引0~15，默认为0\r\n    database: 0\r\n    host: 10.150.1.27\r\n    port: 6379\r\n    #密码（默认为空）\r\n    password: 123456\r\n    #使用JSON序列化redis值\r\n    serialize: json\r\n    # 这里标明使用lettuce配置\r\n    lettuce:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 200\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: 10000\r\n        #连接池中的最大空闲连接\r\n        max-idle: 10\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n        #连接超时时间（毫秒）\r\n    timeout: 10000ms\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://localhost:8859\r\n        username: admin\r\n        password: zzyt@123\r\n        instance:\r\n          prefer-ip: true', '2e921394c6c40ad488667dfc61b6e476', '2022-07-16 08:51:04', '2022-07-16 16:51:04', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (3, 5, 'crcm-cloud-service-auth-dev', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/middle_unified_auth?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true', 'c9c0760f73d287e09482dcacf78d7967', '2022-07-16 08:52:05', '2022-07-16 16:52:05', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (3, 6, 'crcm-cloud-service-auth-dev', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/crcm-cloud-admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true', '1c3598084ec72e51a1e879f986bbfa7d', '2022-07-16 08:52:54', '2022-07-16 16:52:54', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 7, 'crcm-cloud-service-admin', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\r\n    username: root\r\n    password: 123456cc\r\n  #Redis配置\r\n  redis:\r\n    #Redis索引0~15，默认为0\r\n    database: 0\r\n    host: 10.150.1.27\r\n    port: 6379\r\n    #密码（默认为空）\r\n    password: 123456\r\n    #使用JSON序列化redis值\r\n    serialize: json\r\n    # 这里标明使用lettuce配置\r\n    lettuce:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 200\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: 10000\r\n        #连接池中的最大空闲连接\r\n        max-idle: 10\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n        #连接超时时间（毫秒）\r\n    timeout: 10000ms\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://localhost:8859\r\n        username: admin\r\n        password: zzyt@123\r\n        instance:\r\n          prefer-ip: true', 'add91f0a7787a81a82e68ca37f37c016', '2022-07-16 08:54:11', '2022-07-16 16:54:11', NULL, '10.35.11.50', 'I', '', '');
INSERT INTO `his_config_info` VALUES (7, 8, 'crcm-cloud-service-admin', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\r\n    username: root\r\n    password: 123456cc\r\n  #Redis配置\r\n  redis:\r\n    #Redis索引0~15，默认为0\r\n    database: 0\r\n    host: 10.150.1.27\r\n    port: 6379\r\n    #密码（默认为空）\r\n    password: 123456\r\n    #使用JSON序列化redis值\r\n    serialize: json\r\n    # 这里标明使用lettuce配置\r\n    lettuce:\r\n      pool:\r\n        #连接池最大连接数（使用负值表示没有限制）\r\n        max-active: 200\r\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\r\n        max-wait: 10000\r\n        #连接池中的最大空闲连接\r\n        max-idle: 10\r\n        #连接池中的最小空闲连接\r\n        min-idle: 0\r\n        #连接超时时间（毫秒）\r\n    timeout: 10000ms\r\n  boot:\r\n    admin:\r\n      client:\r\n        url: http://localhost:8859\r\n        username: admin\r\n        password: zzyt@123\r\n        instance:\r\n          prefer-ip: true', 'add91f0a7787a81a82e68ca37f37c016', '2022-07-16 08:58:35', '2022-07-16 16:58:35', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (7, 9, 'crcm-cloud-service-admin', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true\n  #配置rabbitMq 服务器\n  rabbitmq:\n    host: 10.150.1.27\n    port: 5672\n    username: admin\n    password: rabbit123', 'a1118bb094c423995ac9e13e05a4d5ab', '2022-07-16 08:59:27', '2022-07-16 16:59:27', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 10, 'crcm-cloud-service-admin-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n\r\n#mybatis配置\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/**/*.xml,classpath:mapper/*.xml\r\n  #MyBaits 别名包扫描路径，开启后在 Mapper 对应的 XML 文件中可以直接使用类名，而不用使用全限定的类名，多个package用逗号或者分号分隔\r\n  typeAliasesPackage: com.crcm.auth.entity\r\n  global-config:\r\n    #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\n    id-type: 0\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 2\r\n    #驼峰下划线转换\r\n    db-column-underline: true\r\n    #刷新mapper 调试神器\r\n    refresh-mapper: true\r\n    #是否开启大写命名，默认不开启\r\n    capital-mode: true\r\n    #逻辑删除配置\r\n    #逻辑已删除值,(逻辑删除下有效)\r\n    logic-delete-value: 1\r\n    #逻辑未删除值,(逻辑删除下有效)\r\n    logic-not-delete-value: 0\r\n  configuration:\r\n    #驼峰命名\r\n    map-underscore-to-camel-case: true\r\n    #mybatis二级缓存\r\n    cache-enabled: false\r\n    #打印执行的sql，开发时候用\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    #配置JdbcTypeForNull, oracle数据库必须配置\r\n    jdbc-type-for-null: \'null\'\r\n    #MyBatis 自动映射时未知列或未知属性处理策略 NONE：不做任何处理 (默认值), WARNING：以日志的形式打印相关警告信息, FAILING：当作映射失败处理，并抛出异常和详细信息\r\n    auto-mapping-unknown-column-behavior: warning\r\n\r\nlogging:\r\n  level:\r\n    com.baomidou.mybatisplus.samples: info\r\n\r\n#开放监控节点\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: health, info, refresh, bus-refresh\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\nmessage:\r\n  enable-mq: false\r\n\r\nfeign:\r\n  httpclient:\r\n    enabled: true\r\n  sentinel:\r\n    enabled: true', 'a8cfc63f322446509999f334cba8e4fd', '2022-07-16 09:01:08', '2022-07-16 17:01:08', NULL, '10.35.11.50', 'I', '', '');
INSERT INTO `his_config_info` VALUES (10, 11, 'crcm-cloud-service-admin-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  jackson:\r\n    date-format: yyyy-MM-dd HH:mm:ss\r\n    time-zone: GMT+8\r\n\r\n#mybatis配置\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/**/*.xml,classpath:mapper/*.xml\r\n  #MyBaits 别名包扫描路径，开启后在 Mapper 对应的 XML 文件中可以直接使用类名，而不用使用全限定的类名，多个package用逗号或者分号分隔\r\n  typeAliasesPackage: com.crcm.auth.entity\r\n  global-config:\r\n    #主键类型  0:\"数据库ID自增\", 1:\"用户输入ID\",2:\"全局唯一ID (数字类型唯一ID)\", 3:\"全局唯一ID UUID\";\r\n    id-type: 0\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 2\r\n    #驼峰下划线转换\r\n    db-column-underline: true\r\n    #刷新mapper 调试神器\r\n    refresh-mapper: true\r\n    #是否开启大写命名，默认不开启\r\n    capital-mode: true\r\n    #逻辑删除配置\r\n    #逻辑已删除值,(逻辑删除下有效)\r\n    logic-delete-value: 1\r\n    #逻辑未删除值,(逻辑删除下有效)\r\n    logic-not-delete-value: 0\r\n  configuration:\r\n    #驼峰命名\r\n    map-underscore-to-camel-case: true\r\n    #mybatis二级缓存\r\n    cache-enabled: false\r\n    #打印执行的sql，开发时候用\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\r\n    #配置JdbcTypeForNull, oracle数据库必须配置\r\n    jdbc-type-for-null: \'null\'\r\n    #MyBatis 自动映射时未知列或未知属性处理策略 NONE：不做任何处理 (默认值), WARNING：以日志的形式打印相关警告信息, FAILING：当作映射失败处理，并抛出异常和详细信息\r\n    auto-mapping-unknown-column-behavior: warning\r\n\r\nlogging:\r\n  level:\r\n    com.baomidou.mybatisplus.samples: info\r\n\r\n#开放监控节点\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: health, info, refresh, bus-refresh\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\nmessage:\r\n  enable-mq: false\r\n\r\nfeign:\r\n  httpclient:\r\n    enabled: true\r\n  sentinel:\r\n    enabled: true', 'a8cfc63f322446509999f334cba8e4fd', '2022-07-16 09:01:19', '2022-07-16 17:01:19', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (7, 12, 'crcm-cloud-service-admin', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://10.150.1.27:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: 10.150.1.27\n    port: 6379\n    #密码（默认为空）\n    password: 123456\n    #使用JSON序列化redis值\n    serialize: json\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms\n  boot:\n    admin:\n      client:\n        url: http://localhost:8859\n        username: admin\n        password: zzyt@123\n        instance:\n          prefer-ip: true\n  #配置rabbitMq 服务器\n  rabbitmq:\n    host: 10.150.1.27\n    port: 5672\n    username: admin\n    password: rabbit123\n\nsecurity:\n  enable: true\n  #是否限制服务只能通过网关访问\n  gateway-fetch-only: true\n  oauth2:\n    client-id: crcm-cloud-admin\n    client-secret: GT2LgqNwI1w5zrzy1iMccXHaKXBRS6MK\n    token-store:\n      # token 存储方式 jwt redis redis-custom\n      type: redis-custom\n    ignore:\n      urls:\n        - /open/**\n        - /favicon.ico\n        - /**/actuator/**\n        - /user/login/**\n        - /test/**\n    resource:\n      id: crcm-cloud-admin\n', '35c0e62f1b4d12d23d7aff5e77c1488f', '2022-07-16 09:01:23', '2022-07-16 17:01:24', NULL, '10.35.11.50', 'D', '', '');
INSERT INTO `his_config_info` VALUES (0, 13, 'crcm-cloud-service-file-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://10.150.1.27:3306/middle_unified_auth?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\r\n    username: root\r\n    password: 123456cc', '4ba90feab708c510c7c1ab7e0b935326', '2022-07-16 09:27:45', '2022-07-16 17:27:45', NULL, '10.35.11.50', 'I', '', '');
INSERT INTO `his_config_info` VALUES (12, 14, 'crcm-cloud-service-file-dev', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://10.150.1.27:3306/middle_unified_auth?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true\r\n    username: root\r\n    password: 123456cc', '4ba90feab708c510c7c1ab7e0b935326', '2022-07-16 09:29:10', '2022-07-16 17:29:10', 'nacos', '10.35.11.50', 'U', '', '');
INSERT INTO `his_config_info` VALUES (12, 15, 'crcm-cloud-service-file-dev', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    type: com.zaxxer.hikari.HikariDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://${MYSQL_HOST:crcm-cloud-mysql}:3306/crcm_cloud_admin?useunicode=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&nullCatalogMeansCurrent=true&autoReconnect=true&tinyInt1isBit=false\n    username: root\n    password: 123456cc\n  #Redis配置\n  redis:\n    #Redis索引0~15，默认为0\n    database: 0\n    host: ${REDIS_HOST:crcm-cloud-redis}\n    port: 16379\n    #密码（默认为空）\n    password: 123456cc\n    # 这里标明使用lettuce配置\n    lettuce:\n      pool:\n        #连接池最大连接数（使用负值表示没有限制）\n        max-active: 200\n        #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 10000\n        #连接池中的最大空闲连接\n        max-idle: 10\n        #连接池中的最小空闲连接\n        min-idle: 0\n        #连接超时时间（毫秒）\n    timeout: 10000ms', '299f5a08de7be3a64ae2357595aa089f', '2022-07-16 09:29:39', '2022-07-16 17:29:40', 'nacos', '10.35.11.50', 'U', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
