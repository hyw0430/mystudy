/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.0.67-community-nt : Database - spring7
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring7` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `spring7`;

/*Table structure for table `ssh_department` */

DROP TABLE IF EXISTS `ssh_department`;

CREATE TABLE `ssh_department` (
  `ID` int(11) NOT NULL auto_increment,
  `DEPARTMENT_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

/*Data for the table `ssh_department` */

insert  into `ssh_department`(`ID`,`DEPARTMENT_NAME`) values (1,'开发部'),(2,'总裁办'),(3,'公关部'),(4,'财务部');

/*Table structure for table `ssh_employee` */

DROP TABLE IF EXISTS `ssh_employee`;

CREATE TABLE `ssh_employee` (
  `ID` int(11) NOT NULL auto_increment,
  `LAST_NAME` varchar(255) default NULL,
  `EMAIL` varchar(255) default NULL,
  `BIRTH` date default NULL,
  `CREATE_TIME` datetime default NULL,
  `DEPARTMENT_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_kfaoihyj5oll835mvidvgsxp` (`DEPARTMENT_ID`),
  CONSTRAINT `FK_kfaoihyj5oll835mvidvgsxp` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `ssh_department` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=gb2312;

/*Data for the table `ssh_employee` */

insert  into `ssh_employee`(`ID`,`LAST_NAME`,`EMAIL`,`BIRTH`,`CREATE_TIME`,`DEPARTMENT_ID`) values (13,'刚哥','gg@atguigu.com','1990-12-12','2015-08-19 14:48:39',2),(14,'DDEEFF','def@163.com','1990-12-12','2015-09-05 20:55:21',3),(15,'AAA','aaa@atguigu.com','1990-12-12','2015-09-05 21:38:09',4),(18,'尚硅谷','atguigu@atguigu.com','2013-05-28','2015-09-06 21:54:53',1),(19,'女孩子','nhz@atguigu.com','1990-12-12','2015-10-31 14:15:41',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
