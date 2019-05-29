/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.20 : Database - ims
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ims` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ims`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `yy_pay` */

DROP TABLE IF EXISTS `yy_pay`;

CREATE TABLE `yy_pay` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL COMMENT '商户订单号',
  `flow_no` varchar(64) NOT NULL COMMENT '支付流水号',
  `order_amt` double NOT NULL COMMENT '订单金额',
  `cur_type` varchar(5) NOT NULL COMMENT '币种',
  `trade_time` varchar(14) NOT NULL COMMENT '支付成功时间',
  `status` varchar(2) NOT NULL COMMENT '订单处理状态(0:未支付,1:已支付；,2:支付失败,31:退款成功（退甬易宝）,37:打款成功(退银行卡))',
  `type` varchar(2) NOT NULL COMMENT '订单类型(10:支付,30:退款)',
  `bank_id` varchar(4) DEFAULT NULL COMMENT '通道编码',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '通道名称',
  `customer_name` varchar(12) DEFAULT NULL COMMENT '交易人姓名',
  `customer_no` varchar(128) DEFAULT NULL COMMENT '交易人身份证号码',
  `fee` double DEFAULT NULL COMMENT '交易手续费',
  `refund_no` varchar(64) DEFAULT NULL COMMENT '退款订单号',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='甬易对账文件表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
