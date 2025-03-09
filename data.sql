/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80404 (8.4.4)
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80404 (8.4.4)
 File Encoding         : 65001

 Date: 09/03/2025 16:19:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notif_msg
-- ----------------------------
DROP TABLE IF EXISTS `notif_msg`;
CREATE TABLE `notif_msg` (
  `id` varchar(32) NOT NULL COMMENT '通知ID',
  `type` tinyint(1) NOT NULL COMMENT '通知类型：1-库存预警，2-质检通知，3-订单状态，4-异常通知，5-补货通知，6-其他',
  `title` varchar(200) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '通知内容',
  `recipient_id` varchar(32) NOT NULL COMMENT '接收人ID',
  `recipient_name` varchar(50) DEFAULT NULL COMMENT '接收人姓名',
  `sender_id` varchar(32) DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(50) DEFAULT NULL COMMENT '发送人姓名',
  `read_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '阅读状态：0-未读，1-已读',
  `priority` tinyint(1) DEFAULT '0' COMMENT '优先级：0-普通，1-重要，2-紧急',
  `related_biz_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关联业务ID,可能是入库id，出库id等用于跳转页面',
  `related_biz_type` tinyint(1) DEFAULT NULL COMMENT '关联业务类型：1-入库单，2-出库单，3-质检单，4-异常标记，5-库存预警',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `is_system` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否系统通知：0-否，1-是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_recipient_id` (`recipient_id`),
  KEY `idx_read_status` (`read_status`),
  KEY `idx_send_time` (`send_time`),
  KEY `idx_related_business_id` (`related_biz_id`),
  KEY `idx_notification_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息通知表';

-- ----------------------------
-- Records of notif_msg
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for notif_notice
-- ----------------------------
DROP TABLE IF EXISTS `notif_notice`;
CREATE TABLE `notif_notice` (
  `id` varchar(32) NOT NULL COMMENT '公告ID',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `publisher` varchar(50) NOT NULL COMMENT '发布人',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布，2-废弃',
  `priority` tinyint(1) DEFAULT '0' COMMENT '优先级：0-普通，1-重要，2-紧急',
  `is_top` tinyint(1) DEFAULT '0' COMMENT '是否置顶：0-否，1-是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_is_top` (`is_top`),
  KEY `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公告表';

-- ----------------------------
-- Records of notif_notice
-- ----------------------------
BEGIN;
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595494315667457', 'test1', '- test1', '1896450516030771201', NULL, NULL, 0, 0, 0, '2025-03-04 00:16:18', '2025-03-04 00:16:18');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595552192868353', 'test2', '- test4444', '1896450516030771201', '2025-03-04 00:18:12', '2025-03-04 00:18:32', 2, 0, 1, '2025-03-04 00:16:31', '2025-03-04 00:18:26');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595694174253058', 'test3', '- test3', '1896450516030771201', NULL, NULL, 0, 1, 0, '2025-03-04 00:17:05', '2025-03-04 00:17:05');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595754597396481', 'test4', '- test4', '1896450516030771201', NULL, NULL, 0, 1, 1, '2025-03-04 00:17:20', '2025-03-04 00:17:20');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595811946115073', 'test5', '- test5', '1896450516030771201', NULL, NULL, 0, 2, 0, '2025-03-04 00:17:33', '2025-03-04 00:17:33');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595848650469377', 'ttt', 'tt', '1896450516030771201', '2025-03-04 00:19:03', NULL, 1, 2, 0, '2025-03-04 00:17:42', '2025-03-04 00:17:42');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1898322064898162689', 'test1', 'ttttt2321321', '1896450516030771201', '2025-03-08 18:37:31', '2025-03-08 18:37:34', 2, 0, 0, '2025-03-08 18:37:04', '2025-03-08 18:37:16');
COMMIT;

-- ----------------------------
-- Table structure for order_in
-- ----------------------------
DROP TABLE IF EXISTS `order_in`;
CREATE TABLE `order_in` (
  `id` varchar(32) NOT NULL COMMENT '入库订单ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `type` tinyint(1) NOT NULL COMMENT '类型：0-出库，1入库',
  `order_type` tinyint(1) NOT NULL COMMENT '订单类型：1-采购入库，2-退货入库，3-其他入库',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  `approver` varchar(50) DEFAULT NULL COMMENT '审核人',
  `inspector` varchar(50) DEFAULT NULL COMMENT '质检员',
  `expected_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `actual_time` datetime DEFAULT NULL COMMENT '实际到达时间',
  `total_amount` decimal(12,2) DEFAULT '0.00' COMMENT '总金额',
  `total_quantity` int DEFAULT '0' COMMENT '总数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-待审核，1-已审核待入库，2-已部分入库，3-已完全入库，-1-已取消',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检中，2-质检通过，3-质检不通过',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库订单表';

-- ----------------------------
-- Records of order_in
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_in_item
-- ----------------------------
DROP TABLE IF EXISTS `order_in_item`;
CREATE TABLE `order_in_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `order_id` varchar(32) NOT NULL COMMENT '入库订单ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `expected_quantity` int NOT NULL COMMENT '预期数量',
  `actual_quantity` int DEFAULT '0' COMMENT '实际数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '金额',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:[],\n      storageId:[]\n  }\n]',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `expiry_date` date DEFAULT NULL COMMENT '过期日期',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0-待入库，1-已部分入库，2-已完全入库',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库订单明细表';

-- ----------------------------
-- Records of order_in_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_out
-- ----------------------------
DROP TABLE IF EXISTS `order_out`;
CREATE TABLE `order_out` (
  `id` varchar(32) NOT NULL COMMENT '出库订单ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `type` tinyint(1) NOT NULL COMMENT '类型：0-出库，1入库',
  `order_type` tinyint(1) NOT NULL COMMENT '订单类型：1-销售出库，2-调拨出库，3-其他出库',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  `approver` varchar(50) DEFAULT NULL COMMENT '审核人',
  `inspector` varchar(50) DEFAULT NULL COMMENT '质检员',
  `expected_time` datetime DEFAULT NULL COMMENT '预计出库时间',
  `actual_time` datetime DEFAULT NULL COMMENT '实际出库时间',
  `total_amount` decimal(12,2) DEFAULT '0.00' COMMENT '总金额',
  `total_quantity` int DEFAULT '0' COMMENT '总数量',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-待审核，1-已审核待出库，2-已部分出库，3-已完全出库，-1-已取消',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检中，2-质检通过，3-质检不通过',
  `delivery_address` varchar(255) DEFAULT NULL COMMENT '配送地址',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库订单表';

-- ----------------------------
-- Records of order_out
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_out_item
-- ----------------------------
DROP TABLE IF EXISTS `order_out_item`;
CREATE TABLE `order_out_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `order_id` varchar(32) NOT NULL COMMENT '出库订单ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `expected_quantity` int NOT NULL COMMENT '预期数量',
  `actual_quantity` int DEFAULT '0' COMMENT '实际数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '金额',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:[],\n      storageId:[]\n  }\n]',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0-待出库，1-已部分出库，2-已完全出库',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库订单明细表';

-- ----------------------------
-- Records of order_out_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `category_id` varchar(32) DEFAULT NULL COMMENT '分类ID',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `spec` varchar(200) DEFAULT NULL COMMENT '规格',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `min_stock` int DEFAULT '0' COMMENT '最小库存（预警阈值）',
  `max_stock` int DEFAULT '999999' COMMENT '最大库存',
  `description` text COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_code` (`product_code`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品表';

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('10', '小米手机', 'XIAOMI-04', '1897216435166351362', '小米', '小米 15', '256+12', 4499.00, 67, 495, '这是一台小米手机', '2008-09-13 05:46:27', '2025-03-05 23:48:20');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897311533005512706', 'test1', 'test1', '', 'test', 'test', 'test1', 114514.00, 0, 999, 'test1', '2025-03-05 23:41:34', '2025-03-05 23:41:53');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897312189133070338', '小米电脑', 'XIAOMI-03', '1897309348356136962', '小米', 'REDMIBOOKPRO14', '512+16', 5699.00, 0, 999, '这是一台小米笔记本', '2025-03-05 23:44:11', '2025-03-05 23:48:14');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897312489487179777', '小米充电宝', 'XIAOMI-02', '1897310915058704385', '小米', '小米自带线充电宝', '20000 33W', 149.00, 0, 999, '这是个小米充电宝', '2025-03-05 23:45:23', '2025-03-05 23:48:08');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897312735277588481', '小米手环', 'XIAOMI-01', '1897309279468888066', '小米', '小米手环9', '白色', 239.00, 0, 999, NULL, '2025-03-05 23:46:21', '2025-03-05 23:48:02');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897313553900871682', 'VIVO手机', 'VIVO-01', '1897216478392848385', 'VIVO', 'vivo X200 Pro', '12G+256', 5189.00, 0, 999, '这是一台vivo X200 Pro 2亿超级长焦镜头', '2025-03-05 23:49:36', '2025-03-05 23:49:36');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897313956239482881', 'OPPO手机', 'OPPO-01', '1897216516095447042', 'OPPO', 'OPPO Find N5', '16+512', 9999.00, 0, 999, '这是一台OPPO Find N5折叠旗舰', '2025-03-05 23:51:12', '2025-03-05 23:51:12');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897314226491072514', 'Apple Watch', 'Apple-01', '1896971847028326401', '苹果', 'Apple Watch s10', 'WIFI版本', 3199.00, 0, 999, '这是一台Apple Watch手表，美丽的废物', '2025-03-05 23:52:17', '2025-03-05 23:52:17');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897314424227340290', 'iPhone 15', 'Apple-02', '1896968524388765697', 'Apple', 'iPhone 15 Pro', '256', 6899.00, 0, 999, '', '2025-03-05 23:53:04', '2025-03-05 23:53:04');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897315128870412290', 'MacBook', 'Apple-03', '1896968763237601282', 'Apple', 'Macbook air', '256', 11999.00, 0, 999, '这是一台MacBook Air笔记本', '2025-03-05 23:55:52', '2025-03-05 23:55:52');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897315660058042369', 'Galaxy', 'Sansung-01', '1897217199603421185', '三星', 'Galaxy S25 Ultra', '12+512', 9699.00, 0, 999, '三星手机', '2025-03-05 23:57:58', '2025-03-05 23:57:58');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897315963788566529', 'Galaxy Ring', 'Sansung-02', '1897309641751896066', '三星', 'Galaxy Ring', '10号（直径19.8毫米）', 2599.00, 0, 999, NULL, '2025-03-05 23:59:11', '2025-03-05 23:59:11');
COMMIT;

-- ----------------------------
-- Table structure for product_cat
-- ----------------------------
DROP TABLE IF EXISTS `product_cat`;
CREATE TABLE `product_cat` (
  `id` varchar(32) NOT NULL COMMENT '分类ID',
  `category_name` varchar(100) NOT NULL COMMENT '分类名称',
  `category_code` varchar(50) NOT NULL COMMENT '分类编码',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `sort` int DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_code` (`category_code`,`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品分类表';

-- ----------------------------
-- Records of product_cat
-- ----------------------------
BEGIN;
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896968270889226241', '小米', 'XIAOMI', NULL, 0, '2025-03-05 00:57:34', '2025-03-05 00:57:34');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896968296944242689', 'VIVO', 'VIVO', NULL, 0, '2025-03-05 00:57:41', '2025-03-05 00:57:41');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896968332419665921', 'OPPO', 'OPPO', NULL, 0, '2025-03-05 00:57:49', '2025-03-05 00:57:49');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896968383002972161', '苹果', 'APPLE', NULL, 0, '2025-03-05 00:58:01', '2025-03-05 00:58:01');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896968524388765697', '手机', 'IPhone', '1896968383002972161', 1, '2025-03-05 00:58:35', '2025-03-05 00:58:35');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896968763237601282', '笔记本', 'mac', '1896968383002972161', 2, '2025-03-05 00:59:32', '2025-03-05 23:34:22');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1896971847028326401', '手表', 'APPLE_WATCH', '1896968383002972161', 0, '2025-03-05 01:11:47', '2025-03-05 01:12:07');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897216435166351362', '手机', 'Phone', '1896968270889226241', 0, '2025-03-05 17:23:41', '2025-03-05 17:23:41');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897216478392848385', '手机', 'Phone', '1896968296944242689', 0, '2025-03-05 17:23:52', '2025-03-05 17:23:52');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897216516095447042', '手机', 'Phone', '1896968332419665921', 0, '2025-03-05 17:24:01', '2025-03-05 17:24:01');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897217130401599490', '三星', 'SAMSUNG', NULL, 0, '2025-03-05 17:26:27', '2025-03-05 17:26:27');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897217199603421185', '手机', 'Phone', '1897217130401599490', 0, '2025-03-05 17:26:44', '2025-03-05 17:26:44');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897309279468888066', '手环', 'Smart_band', '1896968270889226241', 1, '2025-03-05 23:32:37', '2025-03-05 23:33:03');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897309348356136962', '笔记本电脑', 'laptop', '1896968270889226241', 0, '2025-03-05 23:32:54', '2025-03-05 23:33:24');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897309641751896066', '智能戒指', 'smart_ring', '1897217130401599490', 1, '2025-03-05 23:34:04', '2025-03-05 23:34:04');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1897310915058704385', '充电宝', 'powerbank', '1896968270889226241', 0, '2025-03-05 23:39:07', '2025-03-05 23:39:07');
COMMIT;

-- ----------------------------
-- Table structure for quality_exception_mark
-- ----------------------------
DROP TABLE IF EXISTS `quality_exception_mark`;
CREATE TABLE `quality_exception_mark` (
  `id` varchar(32) NOT NULL COMMENT '异常ID',
  `exception_no` varchar(32) NOT NULL COMMENT '异常编号',
  `exception_type` tinyint(1) NOT NULL COMMENT '异常类型：1-质量异常，2-数量异常，3-其他异常',
  `related_order_id` varchar(32) DEFAULT NULL COMMENT '关联订单ID',
  `related_order_no` varchar(32) DEFAULT NULL COMMENT '关联订单编号',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `shelf_id` varchar(32) DEFAULT NULL COMMENT '货架ID',
  `location_id` varchar(32) DEFAULT NULL COMMENT '库位ID',
  `reporter` varchar(50) NOT NULL COMMENT '报告人',
  `handler` varchar(50) DEFAULT NULL COMMENT '处理人',
  `exception_description` varchar(500) NOT NULL COMMENT '异常描述',
  `handling_suggestion` varchar(500) DEFAULT NULL COMMENT '处理建议',
  `handling_result` varchar(500) DEFAULT NULL COMMENT '处理结果',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-待处理，1-处理中，2-已处理',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_exception_no` (`exception_no`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='异常标记表';

-- ----------------------------
-- Records of quality_exception_mark
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for quality_inspection
-- ----------------------------
DROP TABLE IF EXISTS `quality_inspection`;
CREATE TABLE `quality_inspection` (
  `id` varchar(32) NOT NULL COMMENT '质检ID',
  `inspection_no` varchar(32) NOT NULL COMMENT '质检编号',
  `inspection_type` tinyint(1) NOT NULL COMMENT '质检类型：1-入库质检，2-出库质检，3-库存质检',
  `related_order_id` varchar(32) DEFAULT NULL COMMENT '关联订单ID',
  `related_order_no` varchar(32) DEFAULT NULL COMMENT '关联订单编号',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `inspector` varchar(50) NOT NULL COMMENT '质检员',
  `inspection_time` datetime NOT NULL COMMENT '质检时间',
  `status` tinyint(1) NOT NULL COMMENT '质检状态：1-已完成，2-异常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_inspection_no` (`inspection_no`),
  KEY `idx_related_order_id` (`related_order_id`),
  KEY `idx_area_id` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='质检记录表';

-- ----------------------------
-- Records of quality_inspection
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for quality_inspection_item
-- ----------------------------
DROP TABLE IF EXISTS `quality_inspection_item`;
CREATE TABLE `quality_inspection_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `inspection_id` varchar(32) NOT NULL COMMENT '质检记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `location_id` varchar(32) DEFAULT NULL COMMENT '库位ID',
  `inspection_quantity` int NOT NULL COMMENT '质检数量',
  `qualified_quantity` int NOT NULL DEFAULT '0' COMMENT '合格数量',
  `unqualified_quantity` int NOT NULL DEFAULT '0' COMMENT '不合格数量',
  `quality_status` tinyint(1) NOT NULL COMMENT '质检结果：1-合格，2-不合格',
  `exception_reason` varchar(500) DEFAULT NULL COMMENT '异常原因',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_inspection_id` (`inspection_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='质检明细表';

-- ----------------------------
-- Records of quality_inspection_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` varchar(32) NOT NULL COMMENT '库存ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_code` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:[],\n      storageId:[]\n  }\n]',
  `quantity` int NOT NULL DEFAULT '0' COMMENT '数量',
  `available_quantity` int NOT NULL DEFAULT '0' COMMENT '可用数量',
  `locked_quantity` int NOT NULL DEFAULT '0' COMMENT '锁定数量',
  `alert_status` tinyint(1) DEFAULT '0' COMMENT '预警状态：0-正常，1-低于最小库存，2-超过最大库存',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `expiry_date` date DEFAULT NULL COMMENT '过期日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存表';

-- ----------------------------
-- Records of stock
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for stock_alert
-- ----------------------------
DROP TABLE IF EXISTS `stock_alert`;
CREATE TABLE `stock_alert` (
  `id` varchar(32) NOT NULL COMMENT '记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `current_quantity` int NOT NULL COMMENT '当前数量',
  `min_stock` int NOT NULL COMMENT '最小库存',
  `max_stock` int NOT NULL COMMENT '最大库存',
  `alert_type` tinyint(1) NOT NULL COMMENT '预警类型：1-库存不足，2-库存过多',
  `alert_time` datetime NOT NULL COMMENT '预警时间',
  `is_handled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否处理：0-否，1-是',
  `handler` varchar(50) DEFAULT NULL COMMENT '处理人',
  `handling_time` datetime DEFAULT NULL COMMENT '处理时间',
  `handling_method` varchar(500) DEFAULT NULL COMMENT '处理方法',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_alert_time` (`alert_time`),
  KEY `idx_is_handled` (`is_handled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品库存预警记录表';

-- ----------------------------
-- Records of stock_alert
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for stock_check
-- ----------------------------
DROP TABLE IF EXISTS `stock_check`;
CREATE TABLE `stock_check` (
  `id` varchar(32) NOT NULL COMMENT '盘点ID',
  `check_no` varchar(32) NOT NULL COMMENT '盘点单号',
  `check_type` tinyint(1) NOT NULL COMMENT '盘点类型：1-全面盘点，2-抽样盘点，3-动态盘点',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  `checker` varchar(50) DEFAULT NULL COMMENT '盘点人',
  `plan_start_time` datetime DEFAULT NULL COMMENT '计划开始时间',
  `plan_end_time` datetime DEFAULT NULL COMMENT '计划结束时间',
  `actual_start_time` datetime DEFAULT NULL COMMENT '实际开始时间',
  `actual_end_time` datetime DEFAULT NULL COMMENT '实际结束时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-待盘点，1-盘点中，2-已完成',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_check_no` (`check_no`),
  KEY `idx_area_id` (`area_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='盘点单表';

-- ----------------------------
-- Records of stock_check
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for stock_check_item
-- ----------------------------
DROP TABLE IF EXISTS `stock_check_item`;
CREATE TABLE `stock_check_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `check_id` varchar(32) NOT NULL COMMENT '盘点单ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:[],\n      storageId:[]\n  }\n]',
  `system_quantity` int NOT NULL COMMENT '系统数量',
  `actual_quantity` int DEFAULT NULL COMMENT '实际数量',
  `difference_quantity` int DEFAULT NULL COMMENT '差异数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-待盘点，1-已盘点',
  `is_difference` tinyint(1) DEFAULT '0' COMMENT '是否有差异：0-无，1-有',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_check_id` (`check_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_is_difference` (`is_difference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='盘点明细表';

-- ----------------------------
-- Records of stock_check_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for stock_movement
-- ----------------------------
DROP TABLE IF EXISTS `stock_movement`;
CREATE TABLE `stock_movement` (
  `id` varchar(32) NOT NULL COMMENT '记录ID',
  `movement_no` varchar(32) NOT NULL COMMENT '变动编号',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `shelf_id` varchar(32) DEFAULT NULL COMMENT '货架ID',
  `location_id` varchar(32) DEFAULT NULL COMMENT '库位ID',
  `movement_type` tinyint(1) NOT NULL COMMENT '变动类型：1-入库，2-出库，3-调整，4-退货，5-报损',
  `before_quantity` int NOT NULL COMMENT '变动前数量',
  `movement_quantity` int NOT NULL COMMENT '变动数量',
  `after_quantity` int NOT NULL COMMENT '变动后数量',
  `related_order_id` varchar(32) DEFAULT NULL COMMENT '关联订单ID',
  `related_order_no` varchar(32) DEFAULT NULL COMMENT '关联订单编号',
  `operator` varchar(50) NOT NULL COMMENT '操作人',
  `movement_time` datetime NOT NULL COMMENT '变动时间',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_movement_no` (`movement_no`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_movement_type` (`movement_type`),
  KEY `idx_movement_time` (`movement_time`),
  KEY `idx_related_order_id` (`related_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存变动记录表';

-- ----------------------------
-- Records of stock_movement
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for stock_restock
-- ----------------------------
DROP TABLE IF EXISTS `stock_restock`;
CREATE TABLE `stock_restock` (
  `id` varchar(32) NOT NULL COMMENT '请求ID',
  `request_no` varchar(32) NOT NULL COMMENT '请求编号',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) NOT NULL COMMENT '产品编码',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `current_quantity` int NOT NULL COMMENT '当前数量',
  `request_quantity` int NOT NULL COMMENT '请求数量',
  `requester` varchar(50) NOT NULL COMMENT '请求人',
  `approver` varchar(50) DEFAULT NULL COMMENT '审批人',
  `request_reason` varchar(500) DEFAULT NULL COMMENT '请求原因',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `approval_time` datetime DEFAULT NULL COMMENT '审批时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-待审批，1-已审批待执行，2-已执行，3-已拒绝',
  `is_auto` tinyint(1) DEFAULT '0' COMMENT '是否自动生成：0-否，1-是',
  `related_order_id` varchar(32) DEFAULT NULL COMMENT '关联订单ID',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_request_no` (`request_no`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='补货请求表';

-- ----------------------------
-- Records of stock_restock
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(255) NOT NULL COMMENT '菜单id',
  `parent_id` varchar(255) NOT NULL COMMENT '父级id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限字段',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由name',
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '跳转路径',
  `route_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由path',
  `component_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '组件路径',
  `type` tinyint(1) NOT NULL COMMENT '类型(0 目录 1 菜单 2 按钮)',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `parent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '上级菜单名称',
  `order_num` int DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`menu_id`,`type`,`parent_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表，用于存放菜单和权限';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1', '0', '系统管理', 'sys:system:index', 'system', '/sys', 'sys', '1', 0, 'SettingFilled', NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('10', '3', '编辑角色', 'sys:role:update', NULL, NULL, NULL, NULL, 2, '', '角色管理', 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('11', '4', '新增菜单', 'sys:menu:add', NULL, NULL, NULL, NULL, 2, '', '菜单管理', 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('12', '4', '删除菜单', 'sys:menu:delete', NULL, NULL, NULL, NULL, 2, '', '菜单管理', 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('13', '4', '编辑菜单', 'sys:menu:update', NULL, NULL, NULL, NULL, 2, '', '菜单管理', 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('14', '0', '首页', 'home:index', 'index', '/index', 'index', 'home/Home', 0, 'NodeIndexOutlined', NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('15', '3', '分配权限', 'sys:role:allot', NULL, NULL, NULL, NULL, 2, '', '角色管理', 5);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('16', '2', '查询用户', 'sys:user:list', NULL, NULL, NULL, NULL, 2, NULL, '用户管理', 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('17', '3', '查询角色', 'sys:role:list', NULL, NULL, NULL, NULL, 2, NULL, '角色管理', 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('18', '4', '查询菜单', 'sys:menu:list', NULL, NULL, NULL, NULL, 2, NULL, '菜单管理', 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896112867624316929', '0', '公告管理', 'msg:notice:index', '', '/notice', 'notice', 'notice/NoticeManager', 0, 'SignatureOutlined', NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896450719001530369', '1896112867624316929', '查询公告', 'msg:notice:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896574154749648897', '1896112867624316929', '修改公告', 'msg:notice:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896574239608807426', '1896112867624316929', '删除公告', 'msg:notice:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896574312614862850', '1896112867624316929', '新增公告', 'msg:notice:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896578355911335938', '1896112867624316929', '发布公告', 'msg:notice:publish', NULL, NULL, NULL, NULL, 2, NULL, NULL, 5);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896933229702303745', '0', '产品管理', 'product:index', NULL, '/product', 'product', 'product', 0, 'ProductOutlined', NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896933596880064513', '1896933229702303745', '产品管理', 'product:list', NULL, '/product/index', 'index', 'product/index/ProductManager', 1, 'ShoppingOutlined', NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896934922678587394', '1896933229702303745', '类别管理', 'product:cat:index', NULL, '/product/category', 'category', 'product/category/CategoryManager', 1, 'ForkOutlined', NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896935484505608194', '1896933596880064513', '新增产品', 'product:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896935554269466625', '1896933596880064513', '删除产品', 'product:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896935634133209089', '1896933596880064513', '修改产品', 'product:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896935702085128193', '1896933596880064513', '查询产品', 'product:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896935962220056577', '1896934922678587394', '新增类别', 'product:cat:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896936053794295809', '1896934922678587394', '删除类别', 'product:cat:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896936142247972865', '1896934922678587394', '修改类别', 'product:cat:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1896936200251002882', '1896934922678587394', '查询类别', 'product:cat:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898318008138002434', '0', '仓库管理', 'location:index', NULL, '/location', 'location', 'location', 0, 'ShopOutlined', NULL, 5);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898318297842774018', '1898318008138002434', '区域管理', 'location:area:index', NULL, '/location/area', 'area', 'location/area/AreaManager', 1, 'GoldOutlined', NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898318743814729730', '1898318008138002434', '货架管理', 'location:shelf', NULL, '/location/shelf', 'shelf', 'location/shelf/ShelfManager', 1, 'TableOutlined', NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898319154214793218', '1898318008138002434', '库位管理', 'location:storage:index', NULL, '/location/storage', 'storage', 'location/storage/StorageManager', 1, 'HddOutlined', NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898319369038655489', '1898318297842774018', '查询区域列表', 'location:area:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898319450349432833', '1898318297842774018', '修改区域', 'location:area:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898319502652403713', '1898318297842774018', '新增区域', 'location:area:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898319640343015425', '1898318297842774018', '删除区域', 'location:area:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320147279179778', '1898318743814729730', '新增货架', 'location:shelf:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320212148285441', '1898318743814729730', '删除货架', 'location:shelf:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320300824260609', '1898318743814729730', '修改货架', 'location:shelf:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320375264768001', '1898318743814729730', '查询货架列表', 'location:shelf:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320540847501313', '1898319154214793218', '新增库位', 'location:storage:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320597013426178', '1898319154214793218', '删除库位', 'location:storage:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320660913647617', '1898319154214793218', '修改库位', 'location:storage:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898320714181308417', '1898319154214793218', '查询库位', 'location:storage:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('2', '1', '用户管理', 'sys:user:index', 'sysUserList', '/sys/userIndex', 'userIndex', 'sys/user/UserManager', 1, 'TeamOutlined', '系统管理', 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('3', '1', '角色管理', 'sys:role:index', 'sysRoleList', '/sys/roleIndex', 'roleIndex', 'sys/role/RoleManager', 1, 'CommentOutlined', '系统管理', 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('4', '1', '菜单管理', 'sys:menu:index', 'sysMenuList', '/sys/menuIndex', 'menuIndex', 'sys/menu/MenuManager', 1, 'FolderOutlined', '系统管理', 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('5', '2', '新增用户', 'sys:user:add', NULL, NULL, NULL, NULL, 2, '', '用户管理', 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('6', '2', '删除用户', 'sys:user:delete', NULL, NULL, NULL, NULL, 2, '', '用户管理', 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('7', '2', '编辑用户', 'sys:user:update', NULL, NULL, NULL, NULL, 2, '', '用户管理', 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('8', '3', '新增角色', 'sys:role:add', NULL, NULL, NULL, NULL, 2, '', '角色管理', 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('9', '3', '删除角色', 'sys:role:delete', NULL, NULL, NULL, NULL, 2, '', '角色管理', 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(255) NOT NULL COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `type` tinyint(1) NOT NULL COMMENT '角色类型(0:超级管理员,1:管理员,2:员工)',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `type`) VALUES ('1', 'superAdmin', '管理员', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `type`) VALUES ('1896569176085635074', '区域负责人', '', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `type`) VALUES ('1898621150062899201', '质检员', '', 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_menu_id` varchar(255) NOT NULL COMMENT '角色菜单id',
  `menu_id` varchar(255) NOT NULL COMMENT '菜单id',
  `role_id` varchar(255) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`role_menu_id`,`menu_id`,`role_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表，用于存放角色和菜单的关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990009069570', '1', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702789', '1', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947562536961', '10', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947566731266', '11', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947566731267', '12', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947570925569', '13', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895012693696311298', '14', '2');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990004875266', '14', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947570925570', '14', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947570925571', '15', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990004875267', '16', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947570925572', '16', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947575119874', '17', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947575119875', '18', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702790', '1896112867624316929', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947575119876', '1896450719001530369', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947575119877', '1896574154749648897', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947575119878', '1896574239608807426', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947575119879', '1896574312614862850', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508482', '1896578355911335938', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702791', '1896933229702303745', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702787', '1896933596880064513', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702788', '1896934922678587394', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508483', '1896935484505608194', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508484', '1896935554269466625', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508485', '1896935634133209089', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508486', '1896935702085128193', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508487', '1896935962220056577', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508488', '1896936053794295809', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508489', '1896936142247972865', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508490', '1896936200251002882', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702792', '1898318008138002434', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702793', '1898318297842774018', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702794', '1898318743814729730', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702795', '1898319154214793218', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702796', '1898319369038655489', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897089', '1898319450349432833', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897090', '1898319502652403713', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897091', '1898319640343015425', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897092', '1898320147279179778', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897093', '1898320212148285441', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897094', '1898320300824260609', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897095', '1898320375264768001', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897096', '1898320540847501313', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897097', '1898320597013426178', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947591897098', '1898320660913647617', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947596091393', '1898320714181308417', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990004875268', '2', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508491', '2', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702785', '3', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947587702786', '4', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947579314177', '5', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947579314178', '6', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947579314179', '7', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947579314180', '8', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1898321947583508481', '9', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `wx_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密盐值',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `sex` tinyint NOT NULL DEFAULT '0' COMMENT '性别女0-男1',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像的图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1895108523139411969', NULL, 'test2', '负责人2', '6578009717a47ef44887472047ac3ccf030b9e26', '$2a$10$WFp/2U0luq0v0tnAkf4amu', '13031392681', 't2@qq.com', '负责人2', 1, 'http://localhost:9000/wms/avatar/a4032de0-9522-4c94-895a-8e788104e625.webp', '2025-02-27 21:47:36', '2025-03-09 16:12:58');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1896450516030771201', NULL, 'admin', '张三', '24bb322d23d8a77508695e794453e44692772cee', '$2a$10$ao2B7.TgV/05mQzlsynMfe', '13880681423', '3040114965@qq.com', '张三', 1, 'http://localhost:9000/wms/avatar/8af00da7-f04f-43bb-aa34-232c4eaa28de.webp', '2025-03-03 14:40:12', '2025-03-03 14:40:12');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1896569037967204353', NULL, 'test1', '负责人1', '46770e5d61cce5728a5a0696b7a04e6ebac94365', '$2a$10$4CBdzvOCcd/5Vkt8nLSRMe', '13880681425', 't1@qq.com', '负责人1', 0, NULL, '2025-03-03 22:31:10', '2025-03-09 16:13:19');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1896569293849108482', NULL, 'test4', '负责人4', '09805fa7c6a3f7b8f5026d4e76d0fdf94b778557', '$2a$10$SDTRKFIh5nxzzxSItHUKiO', '13880681422', 't4@qq.com', '负责人4', 0, NULL, '2025-03-03 22:32:11', '2025-03-09 16:13:09');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898621397732356097', NULL, 'inspector1', '质检员1', 'fab4db02c1fa2621756d40f8af12f855fff5ad5f', '$2a$10$.g3bL7nlhvPV9g6S08g08u', '13111111111', '1@qq.com', '质检员1', 0, NULL, '2025-03-09 14:26:30', '2025-03-09 14:26:30');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898621550283386881', NULL, 'inspector2', '质检员2', '13406cd7a9351608328203bb3f75f93e3b9e822e', '$2a$10$3Nbv8/YOETZxMGWRxRyAiu', '13111111112', '2@qq.com', '质检员2', 0, NULL, '2025-03-09 14:27:07', '2025-03-09 14:31:05');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898621669850411010', NULL, 'inspector3', '质检员3', '26605b7eaac942fc1821b663817c00fd7a4bafd4', '$2a$10$3okiNL8CAkXrImFfWngb3u', '13111111113', '3@qq.com', '质检员3', 1, NULL, '2025-03-09 14:27:35', '2025-03-09 14:31:16');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898621821399003138', NULL, 'inspector4', '质检员4', 'fd1fdc761131df145c8294df8d37c96d5548a973', '$2a$10$Iav5TW00Ic0EXBKJ.c1XMO', '13111111114', '4@qq.com', '质检员4', 1, NULL, '2025-03-09 14:28:12', '2025-03-09 14:31:24');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898622012529242114', NULL, 'inspectot5', '质检员5', '47ca774a20c06ff6ec6b904c1466affec6b1d24c', '$2a$10$3dJl7wIPxMeTif1E3UZCWO', '13111111115', '5@qq.com', '质检员5', 0, NULL, '2025-03-09 14:28:57', '2025-03-09 14:31:31');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898622873313693698', NULL, 'inspector6', '质检员6', 'cd969a34f88d681b67a5797372baacc2654b4243', '$2a$10$EEKIINFxgQ9a0yxaTtexM.', '13111111116', '6@qq.com', '质检员6', 1, NULL, '2025-03-09 14:32:22', '2025-03-09 14:32:22');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898647819238727682', NULL, 'test3', '负责人3', '0653726e2d928a39d1b6ba2597efa888f30d2201', '$2a$10$ymxNOTlatpmWieIoPFLGNu', '13122232224', 't3@qq.com', '负责人3', 0, NULL, '2025-03-09 16:11:30', '2025-03-09 16:12:43');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898647975300390914', NULL, 'test5', '负责人5', '4d6674720361d3eeb2510b4b8ec8a257e0380541', '$2a$10$AtboNsovgmXtavF3wBjs5e', '13122232225', 't5@qq.com', '负责人5', 0, NULL, '2025-03-09 16:12:07', '2025-03-09 16:12:07');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1898648071593222145', NULL, 'test6', '负责人6', '5676aa97de3db59d99009bfb6307eb4ddfd2b7aa', '$2a$10$RfsYBtuOHJEpImH8wczMBu', '13122232227', 't6@qq.com', '负责人6', 0, NULL, '2025-03-09 16:12:30', '2025-03-09 16:12:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_role_id` varchar(255) NOT NULL COMMENT '用户角色id',
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `role_id` varchar(255) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_role_id`,`user_id`,`role_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表用于表示一个用户哪几个角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1895108523147800578', '1895108523139411969', '1896569176085635074');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1896450516051742721', '1896450516030771201', '1');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1896569037979787265', '1896569037967204353', '1896569176085635074');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1896569293865885697', '1896569293849108482', '1896569176085635074');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898621397749133313', '1898621397732356097', '1898621150062899201');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898621550316941313', '1898621550283386881', '1898621150062899201');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898621669858799617', '1898621669850411010', '1898621150062899201');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898621821407391746', '1898621821399003138', '1898621150062899201');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898622012537630722', '1898622012529242114', '1898621150062899201');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898622873322082306', '1898622873313693698', '1898621150062899201');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898647819251310594', '1898647819238727682', '1896569176085635074');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898647975317168129', '1898647975300390914', '1896569176085635074');
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1898648071605805058', '1898648071593222145', '1896569176085635074');
COMMIT;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_area
-- ----------------------------
DROP TABLE IF EXISTS `wms_area`;
CREATE TABLE `wms_area` (
  `id` varchar(32) NOT NULL COMMENT '区域ID',
  `area_name` varchar(100) NOT NULL COMMENT '区域名称',
  `area_code` varchar(50) NOT NULL COMMENT '区域编码',
  `area_manager` varchar(50) DEFAULT NULL COMMENT '区域负责人',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `description` varchar(500) DEFAULT NULL COMMENT '区域描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_area_code` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域表';

-- ----------------------------
-- Records of wms_area
-- ----------------------------
BEGIN;
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898645979654766593', 'A区', 'A', '1896569037967204353', 1, '这是A区', '2025-03-09 16:04:11', '2025-03-09 16:15:28');
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898646093588840449', 'B区', 'B', '1895108523139411969', 1, '这是B区', '2025-03-09 16:04:39', '2025-03-09 16:15:22');
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898648671391301633', 'C区', 'C', '1898647819238727682', 1, '这是C区', '2025-03-09 16:14:53', '2025-03-09 16:14:53');
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898648889922928641', 'D区', 'D', '1896569293849108482', 1, '这是D区', '2025-03-09 16:15:45', '2025-03-09 16:15:45');
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898649182169448450', 'E区', 'E', '1898647975300390914', 1, '这是E区', '2025-03-09 16:16:55', '2025-03-09 16:16:55');
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898649633312980994', 'F区', 'F', '1898648071593222145', 1, '这是F区', '2025-03-09 16:18:42', '2025-03-09 16:18:42');
COMMIT;

-- ----------------------------
-- Table structure for wms_area_inspector
-- ----------------------------
DROP TABLE IF EXISTS `wms_area_inspector`;
CREATE TABLE `wms_area_inspector` (
  `id` varchar(32) NOT NULL COMMENT '关系ID',
  `area_id` varchar(32) NOT NULL COMMENT '区域ID',
  `area_name` varchar(100) DEFAULT NULL COMMENT '区域名称',
  `inspector_id` varchar(32) NOT NULL COMMENT '质检员ID',
  `inspector_name` varchar(50) NOT NULL COMMENT '质检员姓名',
  `inspector_phone` varchar(20) DEFAULT NULL COMMENT '质检员联系电话',
  `is_primary` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否主要负责人：0-否，1-是',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_area_inspector` (`area_id`,`inspector_id`),
  KEY `idx_area_id` (`area_id`),
  KEY `idx_inspector_id` (`inspector_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='区域质检员关系表';

-- ----------------------------
-- Records of wms_area_inspector
-- ----------------------------
BEGIN;
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648671403884545', '1898648671391301633', 'C区', '1898647819238727682', '负责人3', '13122232224', 1, NULL, '2025-03-09 16:14:53', '2025-03-09 16:14:53');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648671403884546', '1898648671391301633', 'C区', '1898621669850411010', '质检员3', '13111111113', 0, NULL, '2025-03-09 16:14:53', '2025-03-09 16:14:53');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648793089032194', '1898646093588840449', 'B区', '1895108523139411969', '负责人2', '13031392681', 1, NULL, '2025-03-09 16:15:22', '2025-03-09 16:15:22');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648793093226498', '1898646093588840449', 'B区', '1898621550283386881', '质检员2', '13111111112', 0, NULL, '2025-03-09 16:15:22', '2025-03-09 16:15:22');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648816572940290', '1898645979654766593', 'A区', '1896569037967204353', '负责人1', '13880681425', 1, NULL, '2025-03-09 16:15:28', '2025-03-09 16:15:28');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648816577134594', '1898645979654766593', 'A区', '1898621397732356097', '质检员1', '13111111111', 0, NULL, '2025-03-09 16:15:28', '2025-03-09 16:15:28');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648889935511554', '1898648889922928641', 'D区', '1896569293849108482', '负责人4', '13880681422', 1, NULL, '2025-03-09 16:15:45', '2025-03-09 16:15:45');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898648889935511555', '1898648889922928641', 'D区', '1898621821399003138', '质检员4', '13111111114', 0, NULL, '2025-03-09 16:15:45', '2025-03-09 16:15:45');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898649182182031362', '1898649182169448450', 'E区', '1898647975300390914', '负责人5', '13122232225', 1, NULL, '2025-03-09 16:16:55', '2025-03-09 16:16:55');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898649182186225666', '1898649182169448450', 'E区', '1898622012529242114', '质检员5', '13111111115', 0, NULL, '2025-03-09 16:16:55', '2025-03-09 16:16:55');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898649633317175298', '1898649633312980994', 'F区', '1898648071593222145', '负责人6', '13122232227', 1, NULL, '2025-03-09 16:18:42', '2025-03-09 16:18:42');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1898649633321369602', '1898649633312980994', 'F区', '1898622873313693698', '质检员6', '13111111116', 0, NULL, '2025-03-09 16:18:42', '2025-03-09 16:18:42');
COMMIT;

-- ----------------------------
-- Table structure for wms_shelf
-- ----------------------------
DROP TABLE IF EXISTS `wms_shelf`;
CREATE TABLE `wms_shelf` (
  `id` varchar(32) NOT NULL COMMENT '货架ID',
  `area_id` varchar(32) NOT NULL COMMENT '区域ID',
  `shelf_name` varchar(100) NOT NULL COMMENT '货架名称',
  `shelf_code` varchar(50) NOT NULL COMMENT '货架编码',
  `row_num` int DEFAULT NULL COMMENT '行数',
  `column_num` int DEFAULT NULL COMMENT '列数',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_shelf_code` (`shelf_code`),
  KEY `idx_area_id` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='货架表';

-- ----------------------------
-- Records of wms_shelf
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wms_storage_location
-- ----------------------------
DROP TABLE IF EXISTS `wms_storage_location`;
CREATE TABLE `wms_storage_location` (
  `id` varchar(32) NOT NULL COMMENT '库位ID',
  `area_id` varchar(32) NOT NULL COMMENT '区域ID',
  `shelf_id` varchar(32) NOT NULL COMMENT '货架ID',
  `location_code` varchar(50) NOT NULL COMMENT '库位编码',
  `location_name` varchar(100) NOT NULL COMMENT '库位名称',
  `full_location_code` varchar(100) NOT NULL COMMENT '完整库位编码(如A-02-001)',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-占用，1-空闲，2-禁用',
  `product_id` varchar(32) DEFAULT NULL COMMENT '当前存放的产品ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_full_location_code` (`full_location_code`),
  KEY `idx_area_id` (`area_id`),
  KEY `idx_shelf_id` (`shelf_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库位表';

-- ----------------------------
-- Records of wms_storage_location
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
