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

 Date: 11/04/2025 20:55:39
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
-- Table structure for order_in
-- ----------------------------
DROP TABLE IF EXISTS `order_in`;
CREATE TABLE `order_in` (
  `id` varchar(32) NOT NULL COMMENT '入库订单ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `type` tinyint(1) NOT NULL COMMENT '类型：0-出库，1入库',
  `order_type` tinyint(1) NOT NULL COMMENT '订单类型：1-采购入库，2-自动入库',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  `approver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '审核人',
  `inspector` varchar(50) DEFAULT NULL COMMENT '质检员',
  `expected_time` datetime NOT NULL COMMENT '预计到达时间',
  `actual_time` datetime DEFAULT NULL COMMENT '实际到达时间',
  `total_amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `total_quantity` int NOT NULL DEFAULT '0' COMMENT '总数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库订单表';

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
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `amount` decimal(12,2) NOT NULL COMMENT '金额',
  `area_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `batch_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `status` tinyint(1) DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入库订单明细表';

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
  `approver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '审核人',
  `inspector` varchar(50) DEFAULT NULL COMMENT '质检员',
  `expected_time` datetime NOT NULL COMMENT '预计出库时间',
  `actual_time` datetime DEFAULT NULL COMMENT '实际出库时间',
  `total_amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `total_quantity` int NOT NULL DEFAULT '0' COMMENT '总数量',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配送地址',
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库订单表';

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
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `amount` decimal(12,2) NOT NULL COMMENT '金额',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `batch_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出库订单明细表';

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
  `min_stock` int DEFAULT '4' COMMENT '最小库存（预警阈值）',
  `max_stock` int DEFAULT '1000' COMMENT '最大库存',
  `description` text COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_code` (`product_code`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品表';

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
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
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
-- Table structure for quality_inspection
-- ----------------------------
DROP TABLE IF EXISTS `quality_inspection`;
CREATE TABLE `quality_inspection` (
  `id` varchar(32) NOT NULL COMMENT '质检ID',
  `inspection_no` varchar(32) NOT NULL COMMENT '质检编号',
  `inspection_type` tinyint(1) NOT NULL COMMENT '质检类型：1-入库质检，2-出库质检，3-库存质检',
  `related_order_id` varchar(32) DEFAULT NULL COMMENT '关联订单ID',
  `related_order_no` varchar(32) DEFAULT NULL COMMENT '关联订单编号',
  `inspector` varchar(50) NOT NULL COMMENT '质检员',
  `inspection_time` datetime DEFAULT NULL COMMENT '质检时间',
  `status` tinyint(1) NOT NULL COMMENT '0-未质检，1-通过，2-不通过，3-部分异常',
  `receive_status` tinyint NOT NULL DEFAULT '0' COMMENT '上架状态：0-未上架,1-已完成,2-进行中',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_inspection_no` (`inspection_no`),
  KEY `idx_related_order_id` (`related_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='质检记录表';

-- ----------------------------
-- Table structure for quality_inspection_item
-- ----------------------------
DROP TABLE IF EXISTS `quality_inspection_item`;
CREATE TABLE `quality_inspection_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `inspection_id` varchar(32) NOT NULL COMMENT '质检记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `batch_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次号',
  `area_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区域id',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `inspection_quantity` int NOT NULL COMMENT '质检数量',
  `qualified_quantity` int DEFAULT '0' COMMENT '合格数量',
  `unqualified_quantity` int DEFAULT '0' COMMENT '不合格数量',
  `quality_status` tinyint(1) DEFAULT NULL COMMENT '质检结果：1-合格，2-不合格',
  `receive_status` tinyint NOT NULL DEFAULT '0' COMMENT '上架状态：0-未上架,1-已完成,2-进行中',
  `reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '异常原因',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_inspection_id` (`inspection_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='质检明细表';

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` varchar(32) NOT NULL COMMENT '库存ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `product_code` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `quantity` int NOT NULL DEFAULT '0' COMMENT '数量',
  `available_quantity` int NOT NULL DEFAULT '0' COMMENT '可用数量',
  `locked_quantity` int NOT NULL DEFAULT '0' COMMENT '锁定数量',
  `alert_status` tinyint(1) DEFAULT '0' COMMENT '预警状态：0-正常，1-低于最小库存，2-超过最大库存',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库存表';

-- ----------------------------
-- Table structure for stock_alert
-- ----------------------------
DROP TABLE IF EXISTS `stock_alert`;
CREATE TABLE `stock_alert` (
  `id` varchar(32) NOT NULL COMMENT '记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
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
-- Table structure for stock_check_item
-- ----------------------------
DROP TABLE IF EXISTS `stock_check_item`;
CREATE TABLE `stock_check_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `check_id` varchar(32) NOT NULL COMMENT '盘点单ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
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
-- Table structure for stock_movement
-- ----------------------------
DROP TABLE IF EXISTS `stock_movement`;
CREATE TABLE `stock_movement` (
  `id` varchar(32) NOT NULL COMMENT '记录ID',
  `movement_no` varchar(32) NOT NULL COMMENT '变动编号',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `area_id` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `movement_type` tinyint(1) NOT NULL COMMENT '变动类型：1-入库，2-出库，3-调整',
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
-- Table structure for stock_restock
-- ----------------------------
DROP TABLE IF EXISTS `stock_restock`;
CREATE TABLE `stock_restock` (
  `id` varchar(32) NOT NULL COMMENT '请求ID',
  `request_no` varchar(32) NOT NULL COMMENT '请求编号',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=1983 DEFAULT CHARSET=utf8mb3;

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
-- Table structure for wms_shelf
-- ----------------------------
DROP TABLE IF EXISTS `wms_shelf`;
CREATE TABLE `wms_shelf` (
  `id` varchar(32) NOT NULL COMMENT '货架ID',
  `area_id` varchar(32) NOT NULL COMMENT '区域ID',
  `shelf_name` varchar(100) NOT NULL COMMENT '货架名称',
  `shelf_code` varchar(50) NOT NULL COMMENT '货架编码',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_shelf_code` (`shelf_code`,`area_id`) USING BTREE,
  KEY `idx_area_id` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='货架表';

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
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-占用，1-空闲，2-禁用',
  `product_id` varchar(32) DEFAULT NULL COMMENT '当前存放的产品ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_area_id` (`area_id`),
  KEY `idx_shelf_id` (`shelf_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='库位表';

SET FOREIGN_KEY_CHECKS = 1;
