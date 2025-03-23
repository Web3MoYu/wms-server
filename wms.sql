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

 Date: 23/03/2025 22:03:17
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
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901260650958168066', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000016', 1, '2025-03-16 21:13:58', '2025-03-18 23:50:41', 1, '2025-03-16 21:13:58', '2025-03-16 21:13:58');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901260906638766082', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000017', 1, '2025-03-16 21:14:57', '2025-03-16 23:19:50', 1, '2025-03-16 21:14:58', '2025-03-16 21:14:58');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901261572346089473', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000018', 1, '2025-03-16 21:17:36', '2025-03-18 23:50:02', 1, '2025-03-16 21:17:36', '2025-03-16 21:17:37');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901262266201432065', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000019', 1, '2025-03-16 21:20:22', '2025-03-16 23:19:42', 1, '2025-03-16 21:20:22', '2025-03-16 21:20:22');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901262412079325186', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000020', 1, '2025-03-16 21:20:57', '2025-03-16 23:19:33', 1, '2025-03-16 21:20:57', '2025-03-16 21:20:57');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901262793828098050', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000021', 1, '2025-03-16 21:22:28', '2025-03-16 23:19:28', 1, '2025-03-16 21:22:28', '2025-03-16 21:22:28');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901263906639859713', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000022', 1, '2025-03-16 21:26:54', '2025-03-16 23:17:22', 1, '2025-03-16 21:26:54', '2025-03-16 21:26:54');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901264036545843202', 3, '审批通知', '你有一笔订单需要审批', '1898621397732356097', '质检员1', '1896450516030771201', '张三', 0, 0, 'OR20250316000023', 1, '2025-03-16 21:27:25', NULL, 1, '2025-03-16 21:27:25', '2025-03-16 21:27:25');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901292501055737857', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000024', 1, '2025-03-16 23:20:31', '2025-03-18 23:50:01', 1, '2025-03-16 23:20:31', '2025-03-16 23:20:31');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901292666646859778', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000025', 1, '2025-03-16 23:21:11', '2025-03-18 23:49:59', 1, '2025-03-16 23:21:11', '2025-03-16 23:21:11');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901292782229295105', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250316000026', 1, '2025-03-16 23:21:38', '2025-03-18 23:49:58', 1, '2025-03-16 23:21:38', '2025-03-16 23:21:38');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1901639425411756034', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250317000001', 1, '2025-03-17 22:19:04', '2025-03-18 23:49:56', 1, '2025-03-17 22:19:04', '2025-03-17 22:19:04');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902021875568713730', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250318000001', 1, '2025-03-18 23:38:48', '2025-03-18 23:49:53', 1, '2025-03-18 23:38:48', '2025-03-18 23:38:48');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902230469028827138', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250319000001', 1, '2025-03-19 13:27:40', '2025-03-19 13:27:51', 1, '2025-03-19 13:27:40', '2025-03-19 13:27:40');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902662249351442433', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250320000001', 1, '2025-03-20 18:03:25', '2025-03-20 18:03:33', 1, '2025-03-20 18:03:25', '2025-03-20 18:03:25');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902667448069615618', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250320000002', 1, '2025-03-20 18:24:04', '2025-03-20 20:00:15', 1, '2025-03-20 18:24:04', '2025-03-20 18:24:04');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902677173171613698', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250320000003', 1, '2025-03-20 19:02:43', '2025-03-20 20:00:14', 1, '2025-03-20 19:02:43', '2025-03-20 19:02:43');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902679982914891777', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250320000004', 1, '2025-03-20 19:13:53', '2025-03-20 19:13:57', 1, '2025-03-20 19:13:53', '2025-03-20 19:13:53');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902691618971152386', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250320000005', 1, '2025-03-20 20:00:07', '2025-03-20 20:00:15', 1, '2025-03-20 20:00:07', '2025-03-20 20:00:07');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902709015484526593', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250320000006', 1, '2025-03-20 21:09:15', '2025-03-21 18:57:22', 1, '2025-03-20 21:09:15', '2025-03-20 21:09:15');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902710048113782786', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896450516030771201', '张三', 1, 0, 'OR20250320000007', 1, '2025-03-20 21:13:21', '2025-03-20 21:16:28', 1, '2025-03-20 21:13:21', '2025-03-20 21:13:21');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1902897492268310529', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250321000001', 1, '2025-03-21 09:38:11', '2025-03-21 18:57:18', 1, '2025-03-21 09:38:11', '2025-03-21 09:38:11');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903043838417727490', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250321000002', 1, '2025-03-21 19:19:43', '2025-03-21 19:19:51', 1, '2025-03-21 19:19:43', '2025-03-21 19:19:43');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903053083615875074', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250321000003', 1, '2025-03-21 19:56:27', '2025-03-21 19:56:34', 1, '2025-03-21 19:56:27', '2025-03-21 19:56:27');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903065207775875074', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250321000005', 1, '2025-03-21 20:44:37', '2025-03-23 18:38:30', 1, '2025-03-21 20:44:37', '2025-03-21 20:44:37');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903066668786176001', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250321000006', 1, '2025-03-21 20:50:26', '2025-03-23 18:38:28', 1, '2025-03-21 20:50:26', '2025-03-21 20:50:26');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903068405811040258', 3, '审批通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250321000007', 1, '2025-03-21 20:57:20', '2025-03-23 18:33:11', 1, '2025-03-21 20:57:20', '2025-03-21 20:57:20');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903748639584051201', 3, '订单通知', '你有一笔订单需要审批', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'OR20250323000001', 1, '2025-03-23 18:00:20', '2025-03-23 18:00:26', 1, '2025-03-23 18:00:20', '2025-03-23 18:00:20');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903749831814963201', 2, '质检通知', '你有一笔质检订单', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 0, 0, 'QI20250323000003', 3, '2025-03-23 18:05:05', NULL, 1, '2025-03-23 18:05:05', '2025-03-23 18:05:05');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903750421299224578', 2, '质检通知', '你有一笔质检订单', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 1, 0, 'QI20250323000004', 3, '2025-03-23 18:07:25', '2025-03-23 18:44:07', 1, '2025-03-23 18:07:25', '2025-03-23 18:07:25');
INSERT INTO `notif_msg` (`id`, `type`, `title`, `content`, `recipient_id`, `recipient_name`, `sender_id`, `sender_name`, `read_status`, `priority`, `related_biz_id`, `related_biz_type`, `send_time`, `read_time`, `is_system`, `create_time`, `update_time`) VALUES ('1903760180664492033', 2, '质检通知', '你有一笔质检订单', '1896569037967204353', '负责人1', '1896569037967204353', '负责人1', 0, 0, 'QI20250323000005', 3, '2025-03-23 18:46:12', NULL, 1, '2025-03-23 18:46:12', '2025-03-23 18:46:12');
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
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595811946115073', 'test51', '- test5\n- 22222', '1896450516030771201', '2025-03-21 00:00:00', '2025-03-21 00:00:00', 2, 2, 0, '2025-03-04 00:17:33', '2025-03-21 00:00:00');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1896595848650469377', 'ttt', 'tt', '1896450516030771201', '2025-03-04 00:19:03', '2025-03-09 23:57:24', 2, 2, 0, '2025-03-04 00:17:42', '2025-03-04 00:17:42');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1898322064898162689', 'test1', 'ttttt2321321', '1896450516030771201', '2025-03-08 18:37:31', '2025-03-08 18:37:34', 2, 0, 0, '2025-03-08 18:37:04', '2025-03-08 18:37:16');
INSERT INTO `notif_notice` (`id`, `title`, `content`, `publisher`, `publish_time`, `end_time`, `status`, `priority`, `is_top`, `create_time`, `update_time`) VALUES ('1899365839092523010', '测试', '测试使用', '1896450516030771201', '2025-03-11 00:00:00', '2025-03-11 00:00:00', 2, 0, 0, '2025-03-11 00:00:00', '2025-03-11 00:00:00');
COMMIT;

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
  `approver` varchar(50) DEFAULT NULL COMMENT '审核人',
  `inspector` varchar(50) DEFAULT NULL COMMENT '质检员',
  `expected_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `actual_time` datetime DEFAULT NULL COMMENT '实际到达时间',
  `total_amount` decimal(12,2) DEFAULT '0.00' COMMENT '总金额',
  `total_quantity` int DEFAULT '0' COMMENT '总数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
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
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901256687328329729', 'OR20250316000016', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-17 00:00:00', NULL, 229028.00, 2, -2, 0, '3332221', '2025-03-16 20:58:13', '2025-03-18 23:37:57');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901260875722555394', 'OR20250316000017', 1, 1, '1896450516030771201', '1896569037967204353', '1896569037967204353', '2025-03-18 00:00:00', NULL, 114514.00, 1, -1, 0, '22', '2025-03-16 21:14:51', '2025-03-18 22:33:56');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901261536392638465', 'OR20250316000018', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-16 21:17:25', NULL, 114514.00, 1, -2, 0, 'test2', '2025-03-16 21:17:29', '2025-03-18 23:31:51');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901262247603990530', 'OR20250316000019', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-24 00:00:00', NULL, 114514.00, 1, -2, 0, '3232', '2025-03-16 21:20:18', '2025-03-18 23:35:01');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901262411731300353', 'OR20250316000020', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-24 00:00:00', NULL, 3199.00, 1, -2, 0, '22', '2025-03-16 21:20:57', '2025-03-18 23:36:14');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901262793488461825', 'OR20250316000021', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-16 00:00:00', NULL, 114514.00, 1, -2, 0, '数量不对', '2025-03-16 21:22:28', '2025-03-18 22:35:06');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901263906006626306', 'OR20250316000022', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-23 00:00:00', NULL, 222.00, 1, -1, 0, '2321', '2025-03-16 21:26:54', '2025-03-18 13:29:01');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901264036248154113', 'OR20250316000023', 1, 1, '1896450516030771201', '1898621397732356097', '1896569037967204353', '2025-03-30 00:00:00', NULL, 333.00, 1, -1, 0, '213', '2025-03-16 21:27:25', '2025-03-16 21:27:25');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901292500430876673', 'OR20250316000024', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-23 00:00:00', NULL, 6899.00, 1, -1, 0, '2', '2025-03-16 23:20:31', '2025-03-16 23:20:31');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901292666336571393', 'OR20250316000025', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-16 23:20:58', NULL, 2599.00, 1, -1, 0, '1111', '2025-03-16 23:21:11', '2025-03-16 23:21:11');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901292781944172545', 'OR20250316000026', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-21 00:00:00', NULL, 4499.00, 1, -1, 0, 'test', '2025-03-16 23:21:38', '2025-03-16 23:21:38');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901639423306203137', 'OR20250317000001', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-25 00:00:00', NULL, 1633023.00, 57, 1, 0, '审批通过', '2025-03-17 22:19:04', '2025-03-20 19:59:17');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902021874201374721', 'OR20250318000001', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-24 00:00:00', NULL, 114514.00, 1, 1, 0, '审批通过', '2025-03-18 23:38:47', '2025-03-20 17:57:43');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902230467757924354', 'OR20250319000001', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-14 00:00:00', NULL, 114514.00, 1, 1, 0, '审批通过', '2025-03-19 13:27:40', '2025-03-20 18:00:22');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902662247778566146', 'OR20250320000001', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-21 00:00:00', NULL, 227667.00, 33, 1, 0, '审批通过', '2025-03-20 18:03:24', '2025-03-20 18:03:46');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902667446882643969', 'OR20250320000002', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-21 00:00:00', NULL, 96990.00, 10, 1, 0, '审批通过', '2025-03-20 18:24:04', '2025-03-20 18:25:08');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902677172638953473', 'OR20250320000003', 1, 1, '1896569037967204353', '1896569037967204353', '1895108523139411969', '2025-03-21 00:00:00', NULL, 114514.00, 1, 1, 0, '审批通过', '2025-03-20 19:02:43', '2025-03-20 19:09:43');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902679982424174593', 'OR20250320000004', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-31 00:00:00', NULL, 9699.00, 1, 1, 0, '审批通过', '2025-03-20 19:13:53', '2025-03-20 19:14:40');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902691618656595969', 'OR20250320000005', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-20 00:00:00', NULL, 6899.00, 1, 1, 3, '审批通过', '2025-03-20 20:00:07', '2025-03-20 20:46:42');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902709014880526337', 'OR20250320000006', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-20 00:00:00', NULL, 114514.00, 1, -1, 0, '323123', '2025-03-20 21:09:14', '2025-03-20 23:42:15');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902710047799189506', 'OR20250320000007', 1, 1, '1896450516030771201', '1896569037967204353', '1898621397732356097', '2025-03-20 00:00:00', NULL, 11999.00, 1, 1, 0, '审批通过', '2025-03-20 21:13:21', '2025-03-20 23:42:55');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902897490049560577', 'OR20250321000001', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-22 00:00:00', NULL, 1630151.00, 1135, 1, 0, '审批通过', '2025-03-21 09:38:10', '2025-03-21 19:22:36');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903043836442255361', 'OR20250321000002', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-21 00:00:00', NULL, 13987.00, 23, 0, 0, '', '2025-03-21 19:19:42', '2025-03-21 19:19:42');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903053083020304386', 'OR20250321000003', 1, 1, '1896569037967204353', '1896569037967204353', '1896569037967204353', '2025-03-21 00:00:00', NULL, 114514.00, 1, 1, 0, '审批通过', '2025-03-21 19:56:27', '2025-03-23 18:46:12');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903065062929801218', 'OR20250321000005', 1, 1, '1896569037967204353', '1896569037967204353', '1896569037967204353', '2025-03-31 00:00:00', NULL, 0.00, 1, 1, 0, '审批通过', '2025-03-21 20:44:03', '2025-03-23 18:07:25');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903066667402063874', 'OR20250321000006', 1, 1, '1896569037967204353', '1896569037967204353', '1896569037967204353', '2025-03-31 00:00:00', NULL, 122.00, 1, 1, 0, '审批通过', '2025-03-21 20:50:25', '2025-03-23 17:17:43');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903068405110599682', 'OR20250321000007', 1, 1, '1896569037967204353', '1896569037967204353', '1898621397732356097', '2025-03-22 00:00:00', NULL, 229028.00, 4, 1, 0, '审批通过', '2025-03-21 20:57:20', '2025-03-22 00:31:23');
INSERT INTO `order_in` (`id`, `order_no`, `type`, `order_type`, `creator`, `approver`, `inspector`, `expected_time`, `actual_time`, `total_amount`, `total_quantity`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903748637742727169', 'OR20250323000001', 1, 1, '1896569037967204353', '1896569037967204353', '1896569037967204353', '2025-03-31 00:00:00', NULL, 1459756.00, 24, 1, 0, '审批通过', '2025-03-23 18:00:20', '2025-03-23 18:05:04');
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
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `amount` decimal(12,2) NOT NULL COMMENT '金额',
  `area_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域ID',
  `location` json DEFAULT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `batch_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `expiry_date` date DEFAULT NULL COMMENT '过期日期',
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
-- Records of order_in_item
-- ----------------------------
BEGIN;
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901256688284631041', '1901256687328329729', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 2, 0, 114514.00, 229028.00, '', '[]', '2025031600000034', '2025-03-03', NULL, -2, 0, '3332221', '2025-03-16 20:58:13', '2025-03-18 23:37:57');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901260875999379457', '1901260875722555394', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '', '[]', '2025031600000035', '2025-03-24', NULL, -1, 0, '22', '2025-03-16 21:14:51', '2025-03-18 22:33:56');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901261537747398657', '1901261536392638465', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '', '[]', '2025031600000036', '2025-03-20', NULL, -2, 0, 'test2', '2025-03-16 21:17:29', '2025-03-18 23:31:51');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901262247780151297', '1901262247603990530', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '', '[]', '2025031600000037', '2025-03-03', NULL, -2, 0, '3232', '2025-03-16 21:20:18', '2025-03-18 23:35:01');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901262411899072514', '1901262411731300353', '1897314226491072514', 'Apple Watch', 'Apple-01', 1, 0, 3199.00, 3199.00, '', '[]', '2025031600000038', '2025-03-26', NULL, -2, 0, '22', '2025-03-16 21:20:57', '2025-03-18 23:36:14');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901262793631068161', '1901262793488461825', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '', '[]', '2025031600000039', '2025-03-23', NULL, -2, 0, '数量不对', '2025-03-16 21:22:28', '2025-03-18 22:35:06');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901263906224730114', '1901263906006626306', '1901150656145268737', 'RedMi', 'RedMi', 1, 0, 222.00, 222.00, '', '[]', '2025031600000040', '2025-03-26', NULL, -1, 0, '2321', '2025-03-16 21:26:54', '2025-03-18 13:29:01');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901264036390760450', '1901264036248154113', '1901150656321429505', '小米手环', 'XIAOMI-BAND-01', 1, 0, 333.00, 333.00, '', '[]', '2025031600000041', '2025-03-16', NULL, -1, 0, '213', '2025-03-16 21:27:25', '2025-03-16 21:27:25');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901292500678340609', '1901292500430876673', '1897314424227340290', 'iPhone 15', 'Apple-02', 1, 0, 6899.00, 6899.00, '', '[]', '2025031600000042', '2025-03-24', NULL, -1, 0, '2', '2025-03-16 23:20:31', '2025-03-16 23:20:31');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901292666487566338', '1901292666336571393', '1897315963788566529', 'Galaxy Ring', 'Sansung-02', 1, 0, 2599.00, 2599.00, '', '[]', '2025031600000043', '2025-03-02', NULL, -1, 0, '1111', '2025-03-16 23:21:11', '2025-03-16 23:21:11');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901292782070001665', '1901292781944172545', '10', '小米手机', 'XIAOMI-04', 1, 0, 4499.00, 4499.00, '', '[]', '2025031600000044', '2025-03-23', NULL, -1, 0, 'test', '2025-03-16 23:21:38', '2025-03-16 23:21:38');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901639425076199426', '1901639423306203137', '1897314424227340290', 'iPhone 15', 'Apple-02', 33, 0, 6899.00, 227667.00, '1898645979654766593', '[{\"shelfId\": \"1898711159952617473\", \"storageIds\": [\"1899421665572352004\"]}]', '2025031700000001', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-17 22:19:04', '2025-03-20 19:59:17');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901639425092976642', '1901639423306203137', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 12, 0, 114514.00, 1374168.00, '1898645979654766593', '[{\"shelfId\": \"1898711159952617473\", \"storageIds\": [\"1899421665572352005\"]}]', '2025031700000002', '2025-03-30', NULL, 1, 0, '审批通过', '2025-03-17 22:19:04', '2025-03-20 19:59:17');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901639425097170945', '1901639423306203137', '1897315963788566529', 'Galaxy Ring', 'Sansung-02', 11, 0, 2599.00, 28589.00, '1898645979654766593', '[{\"shelfId\": \"1898711159952617473\", \"storageIds\": [\"1899421665572352006\"]}]', '2025031700000003', '2025-03-23', NULL, 1, 0, '审批通过', '2025-03-17 22:19:04', '2025-03-20 19:59:17');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1901639425097170946', '1901639423306203137', '1897315963788566529', 'Galaxy Ring', 'Sansung-02', 1, 0, 2599.00, 2599.00, '1898645979654766593', '[{\"shelfId\": \"1898711159952617473\", \"storageIds\": [\"1899421665572352007\"]}]', '2025031700000004', '2025-03-27', NULL, 1, 0, '审批通过', '2025-03-17 22:19:04', '2025-03-20 19:59:17');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902021874838908930', '1902021874201374721', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '1898645979654766593', '[{\"shelfId\": \"1898700659026018306\", \"storageIds\": [\"1898787401099874306\"]}, {\"shelfId\": \"1898707410911625217\", \"storageIds\": [\"1898787743397023749\"]}]', '2025031800000001', '2025-03-17', NULL, 1, 0, '审批通过', '2025-03-18 23:38:47', '2025-03-20 17:57:43');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902230468567425026', '1902230467757924354', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '1898645979654766593', '[{\"shelfId\": \"1898707410911625217\", \"storageIds\": [\"1899421665568157702\", \"1899421665568157703\", \"1899421665572352002\"]}, {\"shelfId\": \"1898711159952617473\", \"storageIds\": [\"1898787743401218050\", \"1898787743401218051\", \"1898787743401218052\"]}]', '2025031900000001', '2025-03-17', NULL, 1, 0, '审批通过', '2025-03-19 13:27:40', '2025-03-20 18:00:22');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902662248906833921', '1902662247778566146', '1897314424227340290', 'iPhone 15', 'Apple-02', 33, 0, 6899.00, 227667.00, '1898648671391301633', '[{\"shelfId\": \"1898713048458977282\", \"storageIds\": [\"1899424239755128835\", \"1899424239755128836\", \"1899424239755128837\"]}]', '2025032000000001', '2025-03-19', NULL, 1, 0, '审批通过', '2025-03-20 18:03:24', '2025-03-20 18:03:46');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902667447599869954', '1902667446882643969', '1897315660058042369', 'Galaxy', 'Sansung-01', 10, 0, 9699.00, 96990.00, '1898646093588840449', '[{\"shelfId\": \"1898713048442200066\", \"storageIds\": [\"1899422154909216772\", \"1899422154909216771\", \"1899422154909216770\"]}]', '2025032000000002', '2025-03-03', NULL, 1, 0, '审批通过', '2025-03-20 18:24:04', '2025-03-20 18:25:08');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902677172915777537', '1902677172638953473', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '1898645979654766593', '[{\"shelfId\": \"1898711159952617473\", \"storageIds\": [\"1898787743401218053\"]}]', '2025032000000003', '2025-03-20', NULL, 1, 0, '审批通过', '2025-03-20 19:02:43', '2025-03-20 19:09:43');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902679982663249922', '1902679982424174593', '1897315660058042369', 'Galaxy', 'Sansung-01', 1, 0, 9699.00, 9699.00, '1898645979654766593', '[{\"shelfId\": \"1898700659026018306\", \"storageIds\": [\"1899421665568157701\"]}]', '2025032000000004', '2025-03-19', NULL, 1, 0, '审批通过', '2025-03-20 19:13:53', '2025-03-20 19:14:40');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902691618786619393', '1902691618656595969', '1897314424227340290', 'iPhone 15', 'Apple-02', 1, 0, 6899.00, 6899.00, '1898646093588840449', '[{\"shelfId\": \"1898711613084250114\", \"storageIds\": [\"1898788794388942854\", \"1899422154900828161\"]}, {\"shelfId\": \"1898713048362508289\", \"storageIds\": [\"1899422154905022472\", \"1899422154905022471\"]}]', '2025032000000005', '2025-03-17', NULL, 1, 3, '审批通过', '2025-03-20 20:00:07', '2025-03-20 20:46:42');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902709015094435842', '1902709014880526337', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '', '[]', '2025032000000006', '2025-03-20', NULL, -1, 0, '323123', '2025-03-20 21:09:14', '2025-03-20 23:42:16');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902710047962767362', '1902710047799189506', '1897315128870412290', 'MacBook', 'Apple-03', 1, 0, 11999.00, 11999.00, '1898645979654766593', '[{\"shelfId\": \"1898711268564119554\", \"storageIds\": [\"1898787743401218057\", \"1898787743401218056\"]}]', '2025032000000007', '2025-03-16', NULL, 1, 0, '审批通过', '2025-03-20 21:13:21', '2025-03-20 23:42:55');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902897491890860034', '1902897490049560577', '1897314424227340290', 'iPhone 15', 'Apple-02', 1, 0, 6899.00, 6899.00, '1898645979654766593', '[{\"shelfId\": \"1898711268564119554\", \"storageIds\": [\"1899421665572352008\", \"1899421665572352009\"]}]', '2025032100000001', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-21 09:38:11', '2025-03-21 19:22:36');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902897491911831553', '1902897490049560577', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 12, 0, 114514.00, 1374168.00, '1898646093588840449', '[{\"shelfId\": \"1898711613084250114\", \"storageIds\": [\"1898788794376359937\"]}]', '2025032100000002', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-21 09:38:11', '2025-03-21 19:22:36');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1902897491911831554', '1902897490049560577', '1901150656145268737', 'RedMi', 'RedMi', 1122, 0, 222.00, 249084.00, '1898645979654766593', '[{\"shelfId\": \"1898711268564119554\", \"storageIds\": [\"1899421665572352010\"]}]', '2025032100000003', '2025-03-30', NULL, 1, 0, '审批通过', '2025-03-21 09:38:11', '2025-03-21 19:22:36');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903043838048673793', '1903043836442255361', '1897314424227340290', 'iPhone 15', 'Apple-02', 2, 0, 6899.00, 13798.00, '', '[]', '2025032100000009', '2025-03-22', NULL, 0, 0, '', '2025-03-21 19:19:42', '2025-03-21 19:19:42');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903043838082228225', '1903043836442255361', '1903043837746679810', 'HUAWEI Mate 70', 'HUAIWEI-MATE-70', 21, 0, 9.00, 189.00, '', '[]', '2025032100000010', '2025-03-30', NULL, 0, 0, '', '2025-03-21 19:19:42', '2025-03-21 19:19:42');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903053083238408193', '1903053083020304386', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 1, 0, 114514.00, 114514.00, '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1898788794384748551\"]}]', '2025032100000011', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-21 19:56:27', '2025-03-23 18:46:12');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903065206899286018', '1903065062929801218', '1903065079589572609', 'Apple Watch S9', 'APPLE-SWATH-S9', 1, 0, 0.00, 0.00, '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1898788794380554246\"]}]', '2025032100000012', '2025-03-23', NULL, 1, 0, '审批通过', '2025-03-21 20:44:03', '2025-03-23 18:07:25');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903066668463222786', '1903066667402063874', '1903066668219953153', '小米（MI）笔记本电脑', 'XIAOMI-BOOK', 1, 0, 122.00, 122.00, '1898645979654766593', '[{\"shelfId\": \"1898711316639232001\", \"storageIds\": [\"1899421665576546309\"]}]', '2025032100000013', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-21 20:50:26', '2025-03-23 17:17:43');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903068405546807298', '1903068405110599682', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 2, 0, 114514.00, 229028.00, '1898645979654766593', '[{\"shelfId\": \"1898711268564119554\", \"storageIds\": [\"1899421665576546306\"]}, {\"shelfId\": \"1898711316639232001\", \"storageIds\": [\"1899421665576546308\"]}]', '2025032100000014', '2025-03-09', NULL, 1, 0, '审批通过', '2025-03-21 20:57:20', '2025-03-22 00:31:23');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903068405551001601', '1903068405110599682', '1903068405374840833', '一加 Ace 5', 'OPPP-ONEPLUS', 1, 0, 0.00, 0.00, '1898646093588840449', '[{\"shelfId\": \"1898711613084250114\", \"storageIds\": [\"1899422154900828164\"]}]', '2025032100000015', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-21 20:57:20', '2025-03-22 00:31:23');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903068405559390210', '1903068405110599682', '1903068405488087041', 'HUAWEI Mate 60', 'HUAIWEI-MATE-60', 1, 0, 0.00, 0.00, '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1898788794376359938\"]}]', '2025032100000016', '2025-03-18', NULL, 1, 0, '审批通过', '2025-03-21 20:57:20', '2025-03-22 00:31:23');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903748639034572802', '1903748637742727169', '1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', 12, 0, 114514.00, 1374168.00, '1898645979654766593', '[{\"shelfId\": \"1898711316639232001\", \"storageIds\": [\"1899421665576546310\"]}, {\"shelfId\": \"1898711358733266946\", \"storageIds\": [\"1898788205311528962\"]}]', '2025032300000005', '2025-03-31', NULL, 1, 0, '审批通过', '2025-03-23 18:00:20', '2025-03-23 18:05:04');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903748639080710145', '1903748637742727169', '1897314424227340290', 'iPhone 15', 'Apple-02', 11, 0, 6899.00, 75889.00, '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1899422154905022468\", \"1899422154905022467\"]}]', '2025032300000006', '2025-03-02', NULL, 1, 0, '审批通过', '2025-03-23 18:00:20', '2025-03-23 18:05:04');
INSERT INTO `order_in_item` (`id`, `order_id`, `product_id`, `product_name`, `product_code`, `expected_quantity`, `actual_quantity`, `price`, `amount`, `area_id`, `location`, `batch_number`, `production_date`, `expiry_date`, `status`, `quality_status`, `remark`, `create_time`, `update_time`) VALUES ('1903748639084904450', '1903748637742727169', '1897315660058042369', 'Galaxy', 'Sansung-01', 1, 0, 9699.00, 9699.00, '1898648889922928641', '[{\"shelfId\": \"1898714876810616834\", \"storageIds\": [\"1899425383768322051\"]}]', '2025032300000007', '2025-03-24', NULL, 1, 0, '审批通过', '2025-03-23 18:00:20', '2025-03-23 18:05:04');
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
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
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
  `location` json NOT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `batch_number` varchar(100) DEFAULT NULL COMMENT '批次号',
  `status` tinyint(1) DEFAULT '0' COMMENT '0-待审核，1-审批通过，2-入库中/出库中，3-已完成，-1-已取消 -2-审批拒绝',
  `quality_status` tinyint(1) DEFAULT '0' COMMENT '质检状态：0-未质检，1-质检通过，2-质检不通过，3-部分异常',
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
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('10', '小米手机', 'XIAOMI-04', '1897216435166351362', '小米', '小米 15', '256+12', 4499.00, 67, 495, '这是一台小米手机', '2008-09-13 05:46:27', '2025-03-22 00:27:15');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897311533005512706', 'iPhone 15 pro', 'iPhone-15-pro', '1896968524388765697', '苹果', 'pro', '256', 114514.00, 1, 999, 'iphone', '2025-03-05 23:41:34', '2025-03-22 00:21:02');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897312189133070338', '小米电脑', 'XIAOMI-03', '1897309348356136962', '小米', 'REDMIBOOKPRO14', '512+16', 5699.00, 0, 999, '这是一台小米笔记本', '2025-03-05 23:44:11', '2025-03-22 00:27:13');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897312489487179777', '小米充电宝', 'XIAOMI-02', '1897310915058704385', '小米', '小米自带线充电宝', '20000 33W', 149.00, 0, 999, '这是个小米充电宝', '2025-03-05 23:45:23', '2025-03-22 00:27:11');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897312735277588481', '小米手环', 'XIAOMI-01', '1897309279468888066', '小米', '小米手环9', '白色', 239.00, 0, 999, NULL, '2025-03-05 23:46:21', '2025-03-22 00:27:08');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897313553900871682', 'VIVO手机', 'VIVO-01', '1897216478392848385', 'VIVO', 'vivo X200 Pro', '12G+256', 5189.00, 0, 999, '这是一台vivo X200 Pro 2亿超级长焦镜头', '2025-03-05 23:49:36', '2025-03-22 00:27:18');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897313956239482881', 'OPPO手机', 'OPPO-01', '1897216516095447042', 'OPPO', 'OPPO Find N5', '16+512', 9999.00, 0, 999, '这是一台OPPO Find N5折叠旗舰', '2025-03-05 23:51:12', '2025-03-22 00:27:21');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897314226491072514', 'Apple Watch', 'Apple-01', '1896971847028326401', '苹果', 'Apple Watch s10', 'WIFI版本', 3199.00, 0, 999, '这是一台Apple Watch手表，美丽的废物', '2025-03-05 23:52:17', '2025-03-22 00:27:23');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897314424227340290', 'iPhone 15', 'Apple-02', '1896968524388765697', 'Apple', 'iPhone 15 Pro', '256', 6899.00, 0, 999, '', '2025-03-05 23:53:04', '2025-03-05 23:53:04');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897315128870412290', 'MacBook', 'Apple-03', '1896968763237601282', 'Apple', 'Macbook air', '256', 11999.00, 0, 999, '这是一台MacBook Air笔记本', '2025-03-05 23:55:52', '2025-03-22 00:27:02');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897315660058042369', 'Galaxy', 'Sansung-01', '1897217199603421185', '三星', 'Galaxy S25 Ultra', '12+512', 9699.00, 0, 999, '三星手机', '2025-03-05 23:57:58', '2025-03-22 00:27:00');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1897315963788566529', 'Galaxy Ring', 'Sansung-02', '1897309641751896066', '三星', 'Galaxy Ring', '10号（直径19.8毫米）', 2599.00, 0, 999, NULL, '2025-03-05 23:59:11', '2025-03-22 00:26:54');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1901150656145268737', 'RedMi', 'RedMi', '1897216435166351362', '小米', 'RedMi', 'RedMi', 222.00, 0, 999, 'Redmi', '2025-03-16 13:56:52', '2025-03-22 00:26:49');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1901150656321429505', '小米手环', 'XIAOMI-BAND-01', '1897309279468888066', 'XIAOMI', '小米手环9', 'S9', 239.00, 0, 999, '小米手环9', '2025-03-16 13:56:52', '2025-03-22 00:26:41');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1901154713333276673', 'vivo X200', 'VIVO-X200', '1896968296944242689', 'VIVO', 'X200', '12+256', 3899.00, 0, 999, '', '2025-03-16 14:13:00', '2025-03-22 00:25:21');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1903043837746679810', 'HUAWEI Mate 70', 'HUAIWEI-MATE-70', '1903040945136537601', 'HUAWEI', 'Mate', '512', 9.00, 0, 999, '', '2025-03-21 19:19:42', '2025-03-22 00:21:04');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1903065079589572609', 'Apple Watch S9', 'APPLE-SWATH-S9', '1896971847028326401', 'Apple', 'S9', 'S9', 3799.00, 0, 999, '', '2025-03-21 20:44:06', '2025-03-22 00:24:22');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1903066668219953153', '小米（MI）笔记本电脑', 'XIAOMI-BOOK', '1897309348356136962', 'REDMI', '2024', '13 i5', 3699.00, 0, 999, '', '2025-03-21 20:50:25', '2025-03-22 00:23:47');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1903068405374840833', '一加 Ace 5', 'OPPP-ONEPLUS', '1897216516095447042', '一加', 'Ace', '12+256', 2299.00, 0, 11, '', '2025-03-21 20:57:19', '2025-03-22 00:22:55');
INSERT INTO `product` (`id`, `product_name`, `product_code`, `category_id`, `brand`, `model`, `spec`, `price`, `min_stock`, `max_stock`, `description`, `create_time`, `update_time`) VALUES ('1903068405488087041', 'HUAWEI Mate 60', 'HUAIWEI-MATE-60', '1903040945136537601', 'HUAWEI', 'pro', '256', 7899.00, 0, 999, '', '2025-03-21 20:57:19', '2025-03-22 00:21:08');
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
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1903040636817444866', '华为', 'HUAIWEI', NULL, 0, '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `product_cat` (`id`, `category_name`, `category_code`, `parent_id`, `sort`, `create_time`, `update_time`) VALUES ('1903040945136537601', '手机', 'HUAIWEI-PHONE', '1903040636817444866', 1, '2025-03-21 00:00:00', '2025-03-21 00:00:00');
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
  `inspector` varchar(50) NOT NULL COMMENT '质检员',
  `inspection_time` datetime DEFAULT NULL COMMENT '质检时间',
  `status` tinyint(1) NOT NULL COMMENT '0-未质检，1-通过，2-不通过，3-部分异常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_inspection_no` (`inspection_no`),
  KEY `idx_related_order_id` (`related_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='质检记录表';

-- ----------------------------
-- Records of quality_inspection
-- ----------------------------
BEGIN;
INSERT INTO `quality_inspection` (`id`, `inspection_no`, `inspection_type`, `related_order_id`, `related_order_no`, `inspector`, `inspection_time`, `status`, `remark`, `create_time`, `update_time`) VALUES ('1903749831374639105', 'QI20250323000003', 1, '1903748637742727169', 'OR20250323000001', '1896569037967204353', NULL, 0, NULL, '2025-03-23 18:05:04', '2025-03-23 18:05:04');
INSERT INTO `quality_inspection` (`id`, `inspection_no`, `inspection_type`, `related_order_id`, `related_order_no`, `inspector`, `inspection_time`, `status`, `remark`, `create_time`, `update_time`) VALUES ('1903750420871389186', 'QI20250323000004', 1, '1903065062929801218', 'OR20250321000005', '1896569037967204353', NULL, 0, NULL, '2025-03-23 18:07:25', '2025-03-23 18:07:25');
INSERT INTO `quality_inspection` (`id`, `inspection_no`, `inspection_type`, `related_order_id`, `related_order_no`, `inspector`, `inspection_time`, `status`, `remark`, `create_time`, `update_time`) VALUES ('1903760180337328129', 'QI20250323000005', 1, '1903053083020304386', 'OR20250321000003', '1896569037967204353', NULL, 0, NULL, '2025-03-23 18:46:12', '2025-03-23 18:46:12');
COMMIT;

-- ----------------------------
-- Table structure for quality_inspection_item
-- ----------------------------
DROP TABLE IF EXISTS `quality_inspection_item`;
CREATE TABLE `quality_inspection_item` (
  `id` varchar(32) NOT NULL COMMENT '明细ID',
  `inspection_id` varchar(32) NOT NULL COMMENT '质检记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `batch_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次号',
  `area_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区域id',
  `location` json NOT NULL COMMENT '具体位置，格式\n[\n  {\n      shelfId:,\n      storageIds:[]\n  }\n]',
  `inspection_quantity` int NOT NULL COMMENT '质检数量',
  `qualified_quantity` int DEFAULT '0' COMMENT '合格数量',
  `unqualified_quantity` int DEFAULT '0' COMMENT '不合格数量',
  `quality_status` tinyint(1) DEFAULT NULL COMMENT '质检结果：1-合格，2-不合格',
  `reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '异常原因',
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
INSERT INTO `quality_inspection_item` (`id`, `inspection_id`, `product_id`, `batch_number`, `area_id`, `location`, `inspection_quantity`, `qualified_quantity`, `unqualified_quantity`, `quality_status`, `reason`, `remark`, `create_time`, `update_time`) VALUES ('1903749831466913793', '1903749831374639105', '1897311533005512706', '2025032300000005', '1898645979654766593', '[{\"shelfId\": \"1898711316639232001\", \"storageIds\": [\"1899421665576546310\"]}, {\"shelfId\": \"1898711358733266946\", \"storageIds\": [\"1898788205311528962\"]}]', 12, 0, 0, NULL, NULL, NULL, '2025-03-23 18:05:04', '2025-03-23 18:05:04');
INSERT INTO `quality_inspection_item` (`id`, `inspection_id`, `product_id`, `batch_number`, `area_id`, `location`, `inspection_quantity`, `qualified_quantity`, `unqualified_quantity`, `quality_status`, `reason`, `remark`, `create_time`, `update_time`) VALUES ('1903749831487885313', '1903749831374639105', '1897314424227340290', '2025032300000006', '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1899422154905022468\", \"1899422154905022467\"]}]', 11, 0, 0, NULL, NULL, NULL, '2025-03-23 18:05:04', '2025-03-23 18:05:04');
INSERT INTO `quality_inspection_item` (`id`, `inspection_id`, `product_id`, `batch_number`, `area_id`, `location`, `inspection_quantity`, `qualified_quantity`, `unqualified_quantity`, `quality_status`, `reason`, `remark`, `create_time`, `update_time`) VALUES ('1903749831492079617', '1903749831374639105', '1897315660058042369', '2025032300000007', '1898648889922928641', '[{\"shelfId\": \"1898714876810616834\", \"storageIds\": [\"1899425383768322051\"]}]', 1, 0, 0, NULL, NULL, NULL, '2025-03-23 18:05:04', '2025-03-23 18:05:04');
INSERT INTO `quality_inspection_item` (`id`, `inspection_id`, `product_id`, `batch_number`, `area_id`, `location`, `inspection_quantity`, `qualified_quantity`, `unqualified_quantity`, `quality_status`, `reason`, `remark`, `create_time`, `update_time`) VALUES ('1903750420993024001', '1903750420871389186', '1903065079589572609', '2025032100000012', '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1898788794380554246\"]}]', 1, 0, 0, NULL, NULL, NULL, '2025-03-23 18:07:25', '2025-03-23 18:07:25');
INSERT INTO `quality_inspection_item` (`id`, `inspection_id`, `product_id`, `batch_number`, `area_id`, `location`, `inspection_quantity`, `qualified_quantity`, `unqualified_quantity`, `quality_status`, `reason`, `remark`, `create_time`, `update_time`) VALUES ('1903760180429602817', '1903760180337328129', '1897311533005512706', '2025032100000011', '1898646093588840449', '[{\"shelfId\": \"1898713048312176642\", \"storageIds\": [\"1898788794384748551\"]}]', 1, 0, 0, NULL, NULL, NULL, '2025-03-23 18:46:12', '2025-03-23 18:46:12');
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
-- Records of stock
-- ----------------------------
BEGIN;
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1899515924861112321', '10', 'XIAOMI-04', '1898645979654766593', '[{\"shelfId\": \"1898700659026018306\", \"storageIds\": [\"1898772262124433410\"]}]', 4, 4, 0, 1, '202503120143', '2025-03-12', '2025-03-12 00:00:00', '2025-03-16 00:00:00');
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1899517082635857921', '10', 'XIAOMI-04', '1898645979654766593', '[{\"shelfId\": \"1898700659026018306\", \"storageIds\": [\"1898770317204676609\"]}, {\"shelfId\": \"1898707410911625217\", \"storageIds\": [\"1898787743397023746\", \"1898787743397023747\"]}]', 7, 7, 0, 0, '202503120145', '2025-03-12', '2025-03-12 00:00:00', '2025-03-16 00:00:00');
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1901113345416245250', '1897312189133070338', 'XIAOMI-03', '1898645979654766593', '[{\"shelfId\": \"1898700659026018306\", \"storageIds\": [\"1898787361241403393\"]}]', 12, 12, 0, 0, '20250312', '2025-03-16', '2025-03-16 00:00:00', '2025-03-16 00:00:00');
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1902197427526643713', '1897311533005512706', 'iPhone-15-pro', '1898646093588840449', '[{\"shelfId\": \"1898711613084250114\", \"storageIds\": [\"1898788794380554245\", \"1899422154900828162\", \"1898788794384748550\", \"1899422154900828163\"]}]', 13, 13, 0, 0, '202503190222', '2025-03-19', '2025-03-19 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1903043027373576193', '1897311533005512706', 'iPhone-15-pro', '1898645979654766593', '[{\"shelfId\": \"1898711358733266946\", \"storageIds\": [\"1898788205307334657\", \"1898788205307334658\", \"1898788205311528961\"]}]', 4, 4, 0, 0, '22222', '2025-03-21', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1903051652737454081', '1897311533005512706', 'iPhone-15-pro', '1898645979654766593', '[{\"shelfId\": \"1898711316639232001\", \"storageIds\": [\"1898787743401218058\", \"1898787743405412353\"]}]', 13, 13, 0, 0, '2321313', '2025-03-21', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `stock` (`id`, `product_id`, `product_code`, `area_id`, `location`, `quantity`, `available_quantity`, `locked_quantity`, `alert_status`, `batch_number`, `production_date`, `create_time`, `update_time`) VALUES ('1903054850328023042', '1897315128870412290', 'Apple-03', '1898645979654766593', '[{\"shelfId\": \"1898711316639232001\", \"storageIds\": [\"1898787743405412354\", \"1898787743401218059\"]}]', 13, 13, 0, 0, '223231', '2025-03-21', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
COMMIT;

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
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1', '0', '系统管理', 'sys:system:index', 'system', '/sys', 'sys', '1', 0, 'SettingFilled', NULL, 4);
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
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898742323941711874', '0', '库存管理', 'inventory:index', NULL, '/inventory', 'inventory', 'inventory', 0, 'FolderOutlined', NULL, 6);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898742877954744321', '1898742323941711874', '库存管理', 'inventory:stock:list', NULL, '/inventory/stock', 'stock', 'inventory/stock/StockManager', 1, 'FolderOpenOutlined', NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898743020414279682', '1898742877954744321', '新增库存', 'inventory:stock:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898743112613470210', '1898742877954744321', '查询库存', 'inventory:stock:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898743207987748866', '1898742877954744321', '删除库存', 'inventory:stock:delete', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1898743347016343553', '1898742877954744321', '修改库存', 'inventory:stock:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1899793881640120322', '0', '订单管理', 'order:index', NULL, '/order', 'order', 'order', 0, 'CopyOutlined', NULL, 7);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1899799080110829570', '1899793881640120322', '出入库订单', 'order:in-out:index', NULL, '/order/in-out', 'in-out', 'order/index/OrderManager', 1, 'FileOutlined', NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1899799244787593218', '1899799080110829570', '新增订单', 'order:in-out:add', NULL, NULL, NULL, NULL, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1899799309673476098', '1899799080110829570', '查询订单', 'order:in-out:list', NULL, NULL, NULL, NULL, 2, NULL, NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1899799411314044930', '1899799080110829570', '修改订单信息', 'order:in-out:update', NULL, NULL, NULL, NULL, 2, NULL, NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1899799758292037634', '1899799080110829570', '撤销订单', 'order:in-out:cancel', NULL, NULL, NULL, NULL, 2, NULL, NULL, 4);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1901276512964567041', '0', '消息列表', 'msg:index', NULL, '/msg', 'msg', '/msg/MsgManager', 0, 'MessageOutlined', NULL, 3);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1901937464682139650', '1899793881640120322', '订单审核', 'order:in-out:approval', NULL, '/order/approval', 'approval', 'order/approval/ApprovalManager', 1, 'SignatureOutlined', NULL, 2);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `title`, `code`, `name`, `menu_url`, `route_path`, `component_path`, `type`, `icon`, `parent_name`, `order_num`) VALUES ('1902922104712609793', '1899793881640120322', '质检管理', 'order:inspect:list', NULL, '/order/inspect', 'inspect', 'order/inspect/InspectManager.tsx', 1, 'CheckCircleOutlined', NULL, 3);
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
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350217', '1', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129018601474', '10', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129022795778', '11', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129026990081', '12', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129026990082', '13', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895012693696311298', '14', '2');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990004875266', '14', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726251712513', '14', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129026990083', '14', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1903039117703430145', '14', '1903038877038460929');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129026990084', '15', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990004875267', '16', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184386', '16', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184387', '17', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184388', '18', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726255906822', '1896112867624316929', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350218', '1896112867624316929', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1903039117711818754', '1896112867624316929', '1903038877038460929');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726255906817', '1896450719001530369', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184389', '1896450719001530369', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1903039117711818753', '1896450719001530369', '1903038877038460929');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726255906818', '1896574154749648897', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184390', '1896574154749648897', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726255906819', '1896574239608807426', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184391', '1896574239608807426', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726255906820', '1896574312614862850', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129031184392', '1896574312614862850', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902714726255906821', '1896578355911335938', '1896569176085635074');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129035378689', '1896578355911335938', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350219', '1896933229702303745', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350210', '1896933596880064513', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350211', '1896934922678587394', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129035378690', '1896935484505608194', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129035378691', '1896935554269466625', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129035378692', '1896935634133209089', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572993', '1896935702085128193', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572994', '1896935962220056577', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572995', '1896936053794295809', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572996', '1896936142247972865', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572997', '1896936200251002882', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350220', '1898318008138002434', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350212', '1898318297842774018', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350213', '1898318743814729730', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350214', '1898319154214793218', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572998', '1898319369038655489', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129039572999', '1898319450349432833', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767297', '1898319502652403713', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767298', '1898319640343015425', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767299', '1898320147279179778', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767300', '1898320212148285441', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767301', '1898320300824260609', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767302', '1898320375264768001', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767303', '1898320540847501313', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767304', '1898320597013426178', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129043767305', '1898320660913647617', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961601', '1898320714181308417', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350221', '1898742323941711874', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350215', '1898742877954744321', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961602', '1898743020414279682', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961603', '1898743112613470210', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961604', '1898743207987748866', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961605', '1898743347016343553', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129060544513', '1899793881640120322', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129056350216', '1899799080110829570', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961606', '1899799244787593218', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961607', '1899799309673476098', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129047961608', '1899799411314044930', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155905', '1899799758292037634', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1901276734348320769', '1901276512964567041', '1898621150062899201');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155906', '1901276512964567041', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155907', '1901937464682139650', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129060544514', '1902922104712609793', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1895322990004875268', '2', '1894983839904628738');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155913', '2', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155914', '3', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155915', '4', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155908', '5', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155909', '6', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155910', '7', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155911', '8', '1');
INSERT INTO `sys_role_menu` (`role_menu_id`, `menu_id`, `role_id`) VALUES ('1902922129052155912', '9', '1');
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
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1895108523139411969', 'ozZst5jnGKeYmjm44YvqgKZwopPE', 'test2', '负责人2', '6578009717a47ef44887472047ac3ccf030b9e26', '$2a$10$WFp/2U0luq0v0tnAkf4amu', '13031392681', 't2@qq.com', '负责人2', 1, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Z6Ec4tMxZDeOGmaXibRYD9MhDKM3wBboXARLkKa0a3xdnO0jA0VTsMOCo8hERBNZgKKWEImausKVDEGbsiavJTg/132', '2025-02-27 21:47:36', '2025-03-20 21:31:50');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1896450516030771201', 'ozZst5pBqcrenAykhaeKDMCDTJnE', 'admin', '张三2', '24bb322d23d8a77508695e794453e44692772cee', '$2a$10$ao2B7.TgV/05mQzlsynMfe', '13880681423', '3040114965@qq.com', 'testtttt', 1, 'http://localhost:9000/wms/avatar/df65cb4e-0557-4d08-91f3-0a215d6924a4.webp', '2025-03-03 14:40:12', '2025-03-03 14:40:12');
INSERT INTO `sys_user` (`user_id`, `wx_id`, `username`, `real_name`, `password`, `salt`, `phone`, `email`, `nick_name`, `sex`, `avatar`, `create_time`, `update_time`) VALUES ('1896569037967204353', NULL, 'test1', '负责人1', '46770e5d61cce5728a5a0696b7a04e6ebac94365', '$2a$10$4CBdzvOCcd/5Vkt8nLSRMe', '13880681425', 't1@qq.com', '负责人1', 0, 'http://localhost:9000/wms/avatar/e7bf1c14-2b38-4b1b-bc44-5e2f3917928c.webp', '2025-03-03 22:31:10', '2025-03-17 22:33:50');
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
INSERT INTO `sys_user_role` (`user_role_id`, `user_id`, `role_id`) VALUES ('1896569037979787265', '1896569037967204353', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=utf8mb3;

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
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1898649633312980994', 'F区', 'F', '1898648071593222145', 1, '这是F区1', '2025-03-09 16:18:42', '2025-03-11 15:02:11');
INSERT INTO `wms_area` (`id`, `area_name`, `area_code`, `area_manager`, `status`, `description`, `create_time`, `update_time`) VALUES ('1903041289530818562', 'G', 'G', '1896450516030771201', 1, NULL, '2025-03-21 00:00:00', '2025-03-21 00:00:00');
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
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1899355150122721281', '1898649633312980994', 'F区', '1898648071593222145', '负责人6', '13122232227', 1, NULL, '2025-03-11 15:02:11', '2025-03-11 15:02:11');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1899355150135304194', '1898649633312980994', 'F区', '1898622873313693698', '质检员6', '13111111116', 0, NULL, '2025-03-11 15:02:11', '2025-03-11 15:02:11');
INSERT INTO `wms_area_inspector` (`id`, `area_id`, `area_name`, `inspector_id`, `inspector_name`, `inspector_phone`, `is_primary`, `remark`, `create_time`, `update_time`) VALUES ('1903041428941094913', '1903041289530818562', 'G', '1896450516030771201', '张三2', '13880681423', 1, NULL, '2025-03-21 00:00:00', '2025-03-21 00:00:00');
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
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_shelf_code` (`shelf_code`,`area_id`) USING BTREE,
  KEY `idx_area_id` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='货架表';

-- ----------------------------
-- Records of wms_shelf
-- ----------------------------
BEGIN;
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898700659026018306', '1898645979654766593', '1号1', '01', 1, '2025-03-09 19:41:28', '2025-03-11 00:00:00');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898707410911625217', '1898645979654766593', '2号', '02', 1, '2025-03-09 20:08:18', '2025-03-09 20:08:18');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898711159952617473', '1898645979654766593', '3号', '03', 1, '2025-03-09 20:23:12', '2025-03-09 20:23:23');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898711268564119554', '1898645979654766593', '4号', '04', 1, '2025-03-09 20:23:37', '2025-03-09 20:23:37');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898711316639232001', '1898645979654766593', '5号', '05', 1, '2025-03-09 20:23:49', '2025-03-09 20:23:49');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898711358733266946', '1898645979654766593', '6号', '06', 1, '2025-03-09 20:23:59', '2025-03-09 20:23:59');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898711613084250114', '1898646093588840449', '1号', '01', 1, '2025-03-09 20:25:00', '2025-03-09 20:25:00');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898713048312176642', '1898646093588840449', '2号', '02', 1, '2025-03-09 20:30:38', '2025-03-09 20:30:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898713048362508289', '1898646093588840449', '3号', '03', 1, '2025-03-09 20:30:38', '2025-03-09 20:30:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898713048417034241', '1898646093588840449', '4号', '04', 1, '2025-03-09 20:30:38', '2025-03-09 20:30:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898713048433811458', '1898646093588840449', '5号', '05', 1, '2025-03-09 20:30:38', '2025-03-09 20:30:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898713048442200066', '1898646093588840449', '6号', '06', 1, '2025-03-09 20:30:38', '2025-03-09 20:30:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898713048458977282', '1898648671391301633', '1号', '01', 1, '2025-03-09 20:30:38', '2025-03-09 20:30:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714518344425474', '1898648671391301633', '2号', '02', 1, '2025-03-09 20:36:32', '2025-03-09 20:36:32');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714518344425475', '1898648671391301633', '3号', '03', 1, '2025-03-09 20:36:32', '2025-03-09 20:36:32');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714518344425476', '1898648671391301633', '4号', '04', 1, '2025-03-09 20:36:32', '2025-03-09 20:36:32');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714518348619777', '1898648671391301633', '5号', '05', 1, '2025-03-09 20:36:32', '2025-03-09 20:36:32');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714518348619778', '1898648671391301633', '6号', '06', 1, '2025-03-09 20:36:32', '2025-03-09 20:36:32');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876806422530', '1898648889922928641', '1号', '01', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876806422531', '1898648889922928641', '2号', '02', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876810616833', '1898648889922928641', '3号', '03', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876810616834', '1898648889922928641', '4号', '04', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876814811137', '1898648889922928641', '5号', '05', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876814811138', '1898648889922928641', '6号', '06', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876814811139', '1898649182169448450', '1号', '01', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876814811140', '1898649182169448450', '2号', '02', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876814811141', '1898649182169448450', '3号', '03', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876814811142', '1898649182169448450', '4号', '04', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876819005442', '1898649182169448450', '5号', '05', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898714876819005443', '1898649182169448450', '6号', '06', 1, '2025-03-09 20:37:58', '2025-03-09 20:37:58');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715064572829697', '1898649633312980994', '1号', '01', 1, '2025-03-09 20:38:42', '2025-03-09 20:38:42');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715064572829698', '1898649633312980994', '2号', '02', 1, '2025-03-09 20:38:42', '2025-03-09 20:38:42');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715064572829699', '1898649633312980994', '3号', '03', 1, '2025-03-09 20:38:42', '2025-03-09 20:38:42');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715064577024001', '1898649633312980994', '4号', '04', 1, '2025-03-09 20:38:42', '2025-03-09 20:41:59');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715064577024002', '1898649633312980994', '5号', '05', 1, '2025-03-09 20:38:42', '2025-03-09 20:42:02');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715064577024003', '1898649633312980994', '6号', '06', 1, '2025-03-09 20:38:42', '2025-03-09 20:38:42');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715968055271425', '1898645979654766593', '7号', '07', 1, '2025-03-09 20:42:18', '2025-03-09 23:09:35');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1898715968055271426', '1898645979654766593', '8号', '08', 0, '2025-03-09 20:42:18', '2025-03-09 20:42:38');
INSERT INTO `wms_shelf` (`id`, `area_id`, `shelf_name`, `shelf_code`, `status`, `create_time`, `update_time`) VALUES ('1903041623430971393', '1903041289530818562', '1号', '01', 1, '2025-03-21 00:00:00', '2025-03-21 00:00:00');
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

-- ----------------------------
-- Records of wms_storage_location
-- ----------------------------
BEGIN;
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898770317204676609', '1898645979654766593', '1898700659026018306', '001', 'A-01-001', 0, '10', '2025-03-10 00:18:16', '2025-03-10 00:19:34');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898772262124433410', '1898645979654766593', '1898700659026018306', '002', 'A-01-002', 0, '10', '2025-03-10 00:25:59', '2025-03-10 01:25:46');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787361241403393', '1898645979654766593', '1898700659026018306', '003', 'A-01-003', 0, '10', '2025-03-10 01:25:59', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787401099874306', '1898645979654766593', '1898700659026018306', '004', 'A-01-004', 0, '1897311533005512706', '2025-03-10 01:26:09', '2025-03-10 01:26:09');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743397023746', '1898645979654766593', '1898707410911625217', '001', 'A-02-001', 0, '10', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743397023747', '1898645979654766593', '1898707410911625217', '002', 'A-02-002', 0, '10', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743397023748', '1898645979654766593', '1898707410911625217', '003', 'A-02-003', 0, '1897312189133070338', '2025-03-10 01:27:30', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743397023749', '1898645979654766593', '1898707410911625217', '004', 'A-02-004', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218050', '1898645979654766593', '1898711159952617473', '001', 'A-03-001', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218051', '1898645979654766593', '1898711159952617473', '002', 'A-03-002', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218052', '1898645979654766593', '1898711159952617473', '003', 'A-03-003', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218053', '1898645979654766593', '1898711159952617473', '004', 'A-03-004', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218054', '1898645979654766593', '1898711268564119554', '001', 'A-04-001', 0, '1897312189133070338', '2025-03-10 01:27:30', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218055', '1898645979654766593', '1898711268564119554', '002', 'A-04-002', 0, '1897312189133070338', '2025-03-10 01:27:30', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218056', '1898645979654766593', '1898711268564119554', '003', 'A-04-003', 0, '1897315128870412290', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218057', '1898645979654766593', '1898711268564119554', '004', 'A-04-004', 0, '1897315128870412290', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218058', '1898645979654766593', '1898711316639232001', '001', 'A-05-001', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743401218059', '1898645979654766593', '1898711316639232001', '002', 'A-05-002', 0, '1897315128870412290', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743405412353', '1898645979654766593', '1898711316639232001', '003', 'A-05-003', 0, '1897311533005512706', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898787743405412354', '1898645979654766593', '1898711316639232001', '004', 'A-05-004', 0, '1897315128870412290', '2025-03-10 01:27:30', '2025-03-10 01:27:30');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788205307334657', '1898645979654766593', '1898711358733266946', '001', 'A-06-001', 0, '1897311533005512706', '2025-03-10 01:29:21', '2025-03-10 01:29:21');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788205307334658', '1898645979654766593', '1898711358733266946', '002', 'A-06-002', 0, '1897311533005512706', '2025-03-10 01:29:21', '2025-03-10 01:29:21');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788205311528961', '1898645979654766593', '1898711358733266946', '003', 'A-06-003', 0, '1897311533005512706', '2025-03-10 01:29:21', '2025-03-10 01:29:21');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788205311528962', '1898645979654766593', '1898711358733266946', '004', 'A-06-004', 0, '1897311533005512706', '2025-03-10 01:29:21', '2025-03-10 01:29:21');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794376359937', '1898646093588840449', '1898711613084250114', '001', 'B-01-001', 0, '1897311533005512706', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794376359938', '1898646093588840449', '1898713048312176642', '001', 'B-02-001', 0, '1903068405488087041', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794380554241', '1898646093588840449', '1898713048362508289', '001', 'B-03-001', 1, NULL, '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794380554242', '1898646093588840449', '1898713048417034241', '001', 'B-04-001', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794380554243', '1898646093588840449', '1898713048433811458', '001', 'B-05-001', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794380554244', '1898646093588840449', '1898713048442200066', '001', 'B-06-001', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794380554245', '1898646093588840449', '1898711613084250114', '002', 'B-01-002', 0, '1897311533005512706', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794380554246', '1898646093588840449', '1898713048312176642', '002', 'B-02-002', 0, '1903065079589572609', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794384748546', '1898646093588840449', '1898713048362508289', '002', 'B-03-002', 1, NULL, '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794384748547', '1898646093588840449', '1898713048417034241', '002', 'B-04-002', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794384748548', '1898646093588840449', '1898713048433811458', '002', 'B-05-002', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794384748549', '1898646093588840449', '1898713048442200066', '002', 'B-06-002', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794384748550', '1898646093588840449', '1898711613084250114', '003', 'B-01-003', 0, '1897311533005512706', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794384748551', '1898646093588840449', '1898713048312176642', '003', 'B-02-003', 0, '1897311533005512706', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794388942850', '1898646093588840449', '1898713048362508289', '003', 'B-03-003', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794388942851', '1898646093588840449', '1898713048417034241', '003', 'B-04-003', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794388942852', '1898646093588840449', '1898713048433811458', '003', 'B-05-003', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794388942853', '1898646093588840449', '1898713048442200066', '003', 'B-06-003', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794388942854', '1898646093588840449', '1898711613084250114', '004', 'B-01-004', 0, '1897314424227340290', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794393137154', '1898646093588840449', '1898713048312176642', '004', 'B-02-004', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794393137155', '1898646093588840449', '1898713048362508289', '004', 'B-03-004', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794393137156', '1898646093588840449', '1898713048417034241', '004', 'B-04-004', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794393137157', '1898646093588840449', '1898713048433811458', '004', 'B-05-004', 1, '', '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898788794393137158', '1898646093588840449', '1898713048442200066', '004', 'B-06-004', 1, NULL, '2025-03-10 01:31:41', '2025-03-10 01:31:41');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476076589057', '1898648671391301633', '1898713048458977282', '001', 'C-01-001', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476076589058', '1898648671391301633', '1898713048458977282', '002', 'C-01-002', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476076589059', '1898648671391301633', '1898713048458977282', '003', 'C-01-003', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476076589060', '1898648671391301633', '1898713048458977282', '004', 'C-01-004', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783362', '1898648671391301633', '1898714518344425474', '001', 'C-02-001', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783363', '1898648671391301633', '1898714518344425474', '002', 'C-02-002', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783364', '1898648671391301633', '1898714518344425474', '003', 'C-02-003', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783365', '1898648671391301633', '1898714518344425474', '004', 'C-02-004', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783366', '1898648671391301633', '1898714518344425475', '001', 'C-03-001', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783367', '1898648671391301633', '1898714518344425475', '002', 'C-03-002', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783368', '1898648671391301633', '1898714518344425475', '003', 'C-03-003', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783369', '1898648671391301633', '1898714518344425475', '004', 'C-03-004', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783370', '1898648671391301633', '1898714518344425476', '001', 'C-04-001', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476080783371', '1898648671391301633', '1898714518344425476', '002', 'C-04-002', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977666', '1898648671391301633', '1898714518344425476', '003', 'C-04-003', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977667', '1898648671391301633', '1898714518344425476', '004', 'C-04-004', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977668', '1898648671391301633', '1898714518348619777', '001', 'C-05-001', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977669', '1898648671391301633', '1898714518348619777', '002', 'C-05-002', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977670', '1898648671391301633', '1898714518348619777', '003', 'C-05-003', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977671', '1898648671391301633', '1898714518348619777', '004', 'C-05-004', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977672', '1898648671391301633', '1898714518348619778', '001', 'C-06-001', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977673', '1898648671391301633', '1898714518348619778', '002', 'C-06-002', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977674', '1898648671391301633', '1898714518348619778', '003', 'C-06-003', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789476084977675', '1898648671391301633', '1898714518348619778', '004', 'C-06-004', 1, '', '2025-03-10 01:34:24', '2025-03-10 01:34:24');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890532544514', '1898648889922928641', '1898714876806422530', '001', 'D-01-001', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738817', '1898648889922928641', '1898714876806422530', '002', 'D-01-002', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738818', '1898648889922928641', '1898714876806422530', '003', 'D-01-003', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738819', '1898648889922928641', '1898714876806422530', '004', 'D-01-004', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738820', '1898648889922928641', '1898714876806422531', '001', 'D-02-001', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738821', '1898648889922928641', '1898714876806422531', '003', 'D-02-003', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738822', '1898648889922928641', '1898714876806422531', '002', 'D-02-002', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738823', '1898648889922928641', '1898714876806422531', '004', 'D-02-004', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738824', '1898648889922928641', '1898714876810616833', '001', 'D-03-001', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738825', '1898648889922928641', '1898714876810616833', '004', 'D-03-004', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738826', '1898648889922928641', '1898714876810616833', '002', 'D-03-002', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738827', '1898648889922928641', '1898714876810616833', '003', 'D-03-003', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738828', '1898648889922928641', '1898714876810616834', '001', 'D-04-001', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738829', '1898648889922928641', '1898714876810616834', '004', 'D-04-004', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738830', '1898648889922928641', '1898714876810616834', '002', 'D-04-002', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738831', '1898648889922928641', '1898714876810616834', '003', 'D-04-003', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738832', '1898648889922928641', '1898714876814811137', '001', 'D-05-001', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738833', '1898648889922928641', '1898714876814811137', '002', 'D-05-002', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890536738834', '1898648889922928641', '1898714876814811137', '004', 'D-05-004', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890540933121', '1898648889922928641', '1898714876814811137', '003', 'D-05-003', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890540933122', '1898648889922928641', '1898714876814811138', '001', 'D-06-001', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890540933123', '1898648889922928641', '1898714876814811138', '002', 'D-06-002', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890540933124', '1898648889922928641', '1898714876814811138', '003', 'D-06-003', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898789890540933125', '1898648889922928641', '1898714876814811138', '004', 'D-06-004', 1, '', '2025-03-10 01:36:02', '2025-03-10 01:36:02');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359237627906', '1898649182169448450', '1898714876814811139', '004', 'E-01-004', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822210', '1898649182169448450', '1898714876814811139', '003', 'E-01-003', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822211', '1898649182169448450', '1898714876814811139', '002', 'E-01-002', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822212', '1898649182169448450', '1898714876814811139', '001', 'E-01-001', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822213', '1898649182169448450', '1898714876814811140', '004', 'E-02-004', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822214', '1898649182169448450', '1898714876814811140', '003', 'E-02-003', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822215', '1898649182169448450', '1898714876814811140', '002', 'E-02-002', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359241822216', '1898649182169448450', '1898714876814811140', '001', 'E-02-001', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359246016514', '1898649182169448450', '1898714876814811141', '004', 'E-03-004', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359246016515', '1898649182169448450', '1898714876814811141', '003', 'E-03-003', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359246016516', '1898649182169448450', '1898714876814811141', '002', 'E-03-002', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359246016517', '1898649182169448450', '1898714876814811141', '001', 'E-03-001', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359250210817', '1898649182169448450', '1898714876814811142', '004', 'E-04-004', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359250210818', '1898649182169448450', '1898714876814811142', '003', 'E-04-003', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359250210819', '1898649182169448450', '1898714876814811142', '002', 'E-04-002', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359250210820', '1898649182169448450', '1898714876814811142', '001', 'E-04-001', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359250210821', '1898649182169448450', '1898714876819005442', '004', 'E-05-004', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359254405122', '1898649182169448450', '1898714876819005442', '003', 'E-05-003', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359254405123', '1898649182169448450', '1898714876819005442', '002', 'E-05-002', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359254405124', '1898649182169448450', '1898714876819005442', '001', 'E-05-001', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359254405125', '1898649182169448450', '1898714876819005443', '004', 'E-06-004', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359258599425', '1898649182169448450', '1898714876819005443', '003', 'E-06-003', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359258599426', '1898649182169448450', '1898714876819005443', '002', 'E-06-002', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898790359258599427', '1898649182169448450', '1898714876819005443', '001', 'E-06-001', 1, '', '2025-03-10 01:37:54', '2025-03-10 01:37:54');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027662884865', '1898649633312980994', '1898715064572829697', '001', 'F-01-001', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027662884866', '1898649633312980994', '1898715064572829697', '002', 'F-01-002', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027662884867', '1898649633312980994', '1898715064572829697', '003', 'F-01-003', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079170', '1898649633312980994', '1898715064572829697', '004', 'F-01-004', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079171', '1898649633312980994', '1898715064572829698', '001', 'F-02-001', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079172', '1898649633312980994', '1898715064572829698', '002', 'F-02-002', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079173', '1898649633312980994', '1898715064572829698', '003', 'F-02-003', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079174', '1898649633312980994', '1898715064572829698', '004', 'F-02-004', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079175', '1898649633312980994', '1898715064572829699', '001', 'F-03-001', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027667079176', '1898649633312980994', '1898715064572829699', '002', 'F-03-002', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273473', '1898649633312980994', '1898715064572829699', '003', 'F-03-003', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273474', '1898649633312980994', '1898715064572829699', '004', 'F-03-004', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273475', '1898649633312980994', '1898715064577024001', '001', 'F-04-001', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273476', '1898649633312980994', '1898715064577024001', '002', 'F-04-002', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273477', '1898649633312980994', '1898715064577024001', '003', 'F-04-003', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273478', '1898649633312980994', '1898715064577024001', '004', 'F-04-004', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027671273479', '1898649633312980994', '1898715064577024002', '001', 'F-05-001', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467777', '1898649633312980994', '1898715064577024002', '002', 'F-05-002', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467778', '1898649633312980994', '1898715064577024002', '003', 'F-05-003', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467779', '1898649633312980994', '1898715064577024002', '004', 'F-05-004', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467780', '1898649633312980994', '1898715064577024003', '001', 'F-06-001', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467781', '1898649633312980994', '1898715064577024003', '002', 'F-06-002', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467782', '1898649633312980994', '1898715064577024003', '003', 'F-06-003', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1898791027675467783', '1898649633312980994', '1898715064577024003', '004', 'F-06-004', 1, '', '2025-03-10 01:40:33', '2025-03-10 01:40:33');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665568157698', '1898645979654766593', '1898700659026018306', '005', 'A-01-005', 0, '1897312189133070338', '2025-03-11 00:00:00', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665568157699', '1898645979654766593', '1898700659026018306', '006', 'A-01-006', 0, '1897312189133070338', '2025-03-11 00:00:00', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665568157700', '1898645979654766593', '1898700659026018306', '007', 'A-01-007', 0, '1897312189133070338', '2025-03-11 00:00:00', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665568157701', '1898645979654766593', '1898700659026018306', '008', 'A-01-008', 0, '1897315660058042369', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665568157702', '1898645979654766593', '1898707410911625217', '005', 'A-02-005', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665568157703', '1898645979654766593', '1898707410911625217', '006', 'A-02-006', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352002', '1898645979654766593', '1898707410911625217', '007', 'A-02-007', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352003', '1898645979654766593', '1898707410911625217', '008', 'A-02-008', 0, '1897312189133070338', '2025-03-11 00:00:00', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352004', '1898645979654766593', '1898711159952617473', '005', 'A-03-005', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352005', '1898645979654766593', '1898711159952617473', '006', 'A-03-006', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352006', '1898645979654766593', '1898711159952617473', '007', 'A-03-007', 0, '1897315963788566529', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352007', '1898645979654766593', '1898711159952617473', '008', 'A-03-008', 0, '1897315963788566529', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352008', '1898645979654766593', '1898711268564119554', '005', 'A-04-005', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352009', '1898645979654766593', '1898711268564119554', '006', 'A-04-006', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665572352010', '1898645979654766593', '1898711268564119554', '007', 'A-04-007', 0, '1901150656145268737', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546306', '1898645979654766593', '1898711268564119554', '008', 'A-04-008', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546307', '1898645979654766593', '1898711316639232001', '005', 'A-05-005', 0, '1897312189133070338', '2025-03-11 00:00:00', '2025-03-19 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546308', '1898645979654766593', '1898711316639232001', '006', 'A-05-006', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546309', '1898645979654766593', '1898711316639232001', '007', 'A-05-007', 0, '1903066668219953153', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546310', '1898645979654766593', '1898711316639232001', '008', 'A-05-008', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546311', '1898645979654766593', '1898711358733266946', '005', 'A-06-005', 1, NULL, '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546312', '1898645979654766593', '1898711358733266946', '006', 'A-06-006', 1, NULL, '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546313', '1898645979654766593', '1898711358733266946', '007', 'A-06-007', 1, NULL, '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899421665576546314', '1898645979654766593', '1898711358733266946', '008', 'A-06-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154900828161', '1898646093588840449', '1898711613084250114', '005', 'B-01-005', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154900828162', '1898646093588840449', '1898711613084250114', '006', 'B-01-006', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154900828163', '1898646093588840449', '1898711613084250114', '007', 'B-01-007', 0, '1897311533005512706', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154900828164', '1898646093588840449', '1898711613084250114', '008', 'B-01-008', 0, '1903068405374840833', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154900828165', '1898646093588840449', '1898713048312176642', '005', 'B-02-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022466', '1898646093588840449', '1898713048312176642', '006', 'B-02-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022467', '1898646093588840449', '1898713048312176642', '007', 'B-02-007', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022468', '1898646093588840449', '1898713048312176642', '008', 'B-02-008', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022469', '1898646093588840449', '1898713048362508289', '005', 'B-03-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022470', '1898646093588840449', '1898713048362508289', '006', 'B-03-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022471', '1898646093588840449', '1898713048362508289', '007', 'B-03-007', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022472', '1898646093588840449', '1898713048362508289', '008', 'B-03-008', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022473', '1898646093588840449', '1898713048417034241', '005', 'B-04-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022474', '1898646093588840449', '1898713048417034241', '006', 'B-04-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022475', '1898646093588840449', '1898713048417034241', '007', 'B-04-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022476', '1898646093588840449', '1898713048417034241', '008', 'B-04-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022477', '1898646093588840449', '1898713048433811458', '005', 'B-05-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022478', '1898646093588840449', '1898713048433811458', '006', 'B-05-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022479', '1898646093588840449', '1898713048433811458', '007', 'B-05-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154905022480', '1898646093588840449', '1898713048433811458', '008', 'B-05-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154909216769', '1898646093588840449', '1898713048442200066', '005', 'B-06-005', 1, NULL, '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154909216770', '1898646093588840449', '1898713048442200066', '006', 'B-06-006', 0, '1897315660058042369', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154909216771', '1898646093588840449', '1898713048442200066', '007', 'B-06-007', 0, '1897315660058042369', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899422154909216772', '1898646093588840449', '1898713048442200066', '008', 'B-06-008', 0, '1897315660058042369', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239755128834', '1898648671391301633', '1898713048458977282', '005', 'C-01-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239755128835', '1898648671391301633', '1898713048458977282', '006', 'C-01-006', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239755128836', '1898648671391301633', '1898713048458977282', '007', 'C-01-007', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239755128837', '1898648671391301633', '1898713048458977282', '008', 'C-01-008', 0, '1897314424227340290', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323138', '1898648671391301633', '1898714518344425474', '005', 'C-02-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323139', '1898648671391301633', '1898714518344425474', '006', 'C-02-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323140', '1898648671391301633', '1898714518344425474', '007', 'C-02-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323141', '1898648671391301633', '1898714518344425474', '008', 'C-02-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323142', '1898648671391301633', '1898714518344425475', '005', 'C-03-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323143', '1898648671391301633', '1898714518344425475', '006', 'C-03-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323144', '1898648671391301633', '1898714518344425475', '007', 'C-03-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323145', '1898648671391301633', '1898714518344425475', '008', 'C-03-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323146', '1898648671391301633', '1898714518344425476', '005', 'C-04-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323147', '1898648671391301633', '1898714518344425476', '006', 'C-04-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323148', '1898648671391301633', '1898714518344425476', '007', 'C-04-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239759323149', '1898648671391301633', '1898714518344425476', '008', 'C-04-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517441', '1898648671391301633', '1898714518348619777', '005', 'C-05-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517442', '1898648671391301633', '1898714518348619777', '006', 'C-05-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517443', '1898648671391301633', '1898714518348619777', '007', 'C-05-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517444', '1898648671391301633', '1898714518348619777', '008', 'C-05-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517445', '1898648671391301633', '1898714518348619778', '005', 'C-06-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517446', '1898648671391301633', '1898714518348619778', '006', 'C-06-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517447', '1898648671391301633', '1898714518348619778', '007', 'C-06-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899424239763517448', '1898648671391301633', '1898714518348619778', '008', 'C-06-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425103307796481', '1898648889922928641', '1898714876806422530', '005', 'D-01-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425103307796482', '1898648889922928641', '1898714876806422530', '006', 'D-01-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425103307796483', '1898648889922928641', '1898714876806422530', '007', 'D-01-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425103311990785', '1898648889922928641', '1898714876806422530', '008', 'D-01-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127746', '1898648889922928641', '1898714876806422531', '005', 'D-02-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127747', '1898648889922928641', '1898714876806422531', '006', 'D-02-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127748', '1898648889922928641', '1898714876806422531', '007', 'D-02-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127749', '1898648889922928641', '1898714876806422531', '008', 'D-02-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127750', '1898648889922928641', '1898714876810616833', '005', 'D-03-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127751', '1898648889922928641', '1898714876810616833', '006', 'D-03-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127752', '1898648889922928641', '1898714876810616833', '007', 'D-03-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383764127753', '1898648889922928641', '1898714876810616833', '008', 'D-03-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322049', '1898648889922928641', '1898714876810616834', '005', 'D-04-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322050', '1898648889922928641', '1898714876810616834', '006', 'D-04-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322051', '1898648889922928641', '1898714876810616834', '007', 'D-04-007', 0, '1897315660058042369', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322052', '1898648889922928641', '1898714876810616834', '008', 'D-04-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322053', '1898648889922928641', '1898714876814811137', '005', 'D-05-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322054', '1898648889922928641', '1898714876814811137', '006', 'D-05-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322055', '1898648889922928641', '1898714876814811137', '007', 'D-05-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322056', '1898648889922928641', '1898714876814811137', '008', 'D-05-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322057', '1898648889922928641', '1898714876814811138', '005', 'D-06-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322058', '1898648889922928641', '1898714876814811138', '006', 'D-06-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322059', '1898648889922928641', '1898714876814811138', '007', 'D-06-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899425383768322060', '1898648889922928641', '1898714876814811138', '008', 'D-06-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017867395074', '1898649182169448450', '1898714876814811139', '005', 'E-01-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017871589377', '1898649182169448450', '1898714876814811139', '006', 'E-01-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017871589378', '1898649182169448450', '1898714876814811139', '007', 'E-01-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017871589379', '1898649182169448450', '1898714876814811139', '008', 'E-01-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017875783681', '1898649182169448450', '1898714876814811140', '005', 'E-02-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017875783682', '1898649182169448450', '1898714876814811140', '006', 'E-02-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017875783683', '1898649182169448450', '1898714876814811140', '007', 'E-02-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017875783684', '1898649182169448450', '1898714876814811140', '008', 'E-02-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017875783685', '1898649182169448450', '1898714876814811141', '005', 'E-03-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017875783686', '1898649182169448450', '1898714876814811141', '006', 'E-03-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017879977985', '1898649182169448450', '1898714876814811141', '007', 'E-03-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017879977986', '1898649182169448450', '1898714876814811141', '008', 'E-03-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017879977987', '1898649182169448450', '1898714876814811142', '005', 'E-04-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017879977988', '1898649182169448450', '1898714876814811142', '006', 'E-04-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017879977989', '1898649182169448450', '1898714876814811142', '007', 'E-04-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017879977990', '1898649182169448450', '1898714876814811142', '008', 'E-04-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017884172289', '1898649182169448450', '1898714876819005442', '005', 'E-05-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017884172290', '1898649182169448450', '1898714876819005442', '006', 'E-05-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017884172291', '1898649182169448450', '1898714876819005442', '007', 'E-05-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017884172292', '1898649182169448450', '1898714876819005442', '008', 'E-05-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017884172293', '1898649182169448450', '1898714876819005443', '005', 'E-06-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017888366594', '1898649182169448450', '1898714876819005443', '006', 'E-06-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017888366595', '1898649182169448450', '1898714876819005443', '007', 'E-06-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426017888366596', '1898649182169448450', '1898714876819005443', '008', 'E-06-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263385174017', '1898649633312980994', '1898715064572829697', '005', 'F-01-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263385174018', '1898649633312980994', '1898715064572829697', '006', 'F-01-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263385174019', '1898649633312980994', '1898715064572829697', '007', 'F-01-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263385174020', '1898649633312980994', '1898715064572829697', '008', 'F-01-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263385174021', '1898649633312980994', '1898715064572829698', '005', 'F-02-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368321', '1898649633312980994', '1898715064572829698', '006', 'F-02-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368322', '1898649633312980994', '1898715064572829698', '007', 'F-02-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368323', '1898649633312980994', '1898715064572829698', '008', 'F-02-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368324', '1898649633312980994', '1898715064572829699', '005', 'F-03-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368325', '1898649633312980994', '1898715064572829699', '006', 'F-03-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368326', '1898649633312980994', '1898715064572829699', '007', 'F-03-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368327', '1898649633312980994', '1898715064572829699', '008', 'F-03-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368328', '1898649633312980994', '1898715064577024001', '005', 'F-04-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368329', '1898649633312980994', '1898715064577024001', '006', 'F-04-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263389368330', '1898649633312980994', '1898715064577024001', '007', 'F-04-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562626', '1898649633312980994', '1898715064577024001', '008', 'F-04-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562627', '1898649633312980994', '1898715064577024002', '005', 'F-05-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562628', '1898649633312980994', '1898715064577024002', '006', 'F-05-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562629', '1898649633312980994', '1898715064577024002', '007', 'F-05-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562630', '1898649633312980994', '1898715064577024002', '008', 'F-05-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562631', '1898649633312980994', '1898715064577024003', '005', 'F-06-005', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562632', '1898649633312980994', '1898715064577024003', '006', 'F-06-006', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562633', '1898649633312980994', '1898715064577024003', '007', 'F-06-007', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1899426263393562634', '1898649633312980994', '1898715064577024003', '008', 'F-06-008', 1, '', '2025-03-11 00:00:00', '2025-03-11 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042066739544065', '1903041289530818562', '1903041623430971393', '001', 'G-01-001', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283769610242', '1903041289530818562', '1903041623430971393', '002', 'G-01-002', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283773804546', '1903041289530818562', '1903041623430971393', '003', 'G-01-003', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283773804547', '1903041289530818562', '1903041623430971393', '004', 'G-01-004', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283773804548', '1903041289530818562', '1903041623430971393', '005', 'G-01-005', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283773804549', '1903041289530818562', '1903041623430971393', '006', 'G-01-006', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283777998850', '1903041289530818562', '1903041623430971393', '007', 'G-01-007', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
INSERT INTO `wms_storage_location` (`id`, `area_id`, `shelf_id`, `location_code`, `location_name`, `status`, `product_id`, `create_time`, `update_time`) VALUES ('1903042283777998851', '1903041289530818562', '1903041623430971393', '008', 'G-01-008', 1, '', '2025-03-21 00:00:00', '2025-03-21 00:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
