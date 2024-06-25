/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - hostelmgt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hostelmgt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hostelmgt`;

/*Table structure for table `h_allotment` */

DROP TABLE IF EXISTS `h_allotment`;

CREATE TABLE `h_allotment` (
  `ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `HostelId` bigint(20) DEFAULT NULL,
  `HostelName` varchar(225) DEFAULT NULL,
  `roomId` bigint(20) DEFAULT NULL,
  `roomNo` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_h_allotment` (`userId`),
  KEY `HostelId` (`HostelId`),
  KEY `roomId` (`roomId`),
  CONSTRAINT `FK_h_allotment` FOREIGN KEY (`userId`) REFERENCES `h_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_allotment_ibfk_1` FOREIGN KEY (`HostelId`) REFERENCES `h_hostel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_allotment_ibfk_2` FOREIGN KEY (`roomId`) REFERENCES `h_room` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_allotment` */

insert  into `h_allotment`(`ID`,`userId`,`name`,`HostelId`,`HostelName`,`roomId`,`roomNo`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,2,'Student Student',1,'Demo',1,'101','Warden@gmail.com','Warden@gmail.com','2019-10-20 20:45:06','2019-10-20 20:45:06');

/*Table structure for table `h_application` */

DROP TABLE IF EXISTS `h_application`;

CREATE TABLE `h_application` (
  `ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `hostelId` bigint(20) DEFAULT NULL,
  `hostelName` varchar(225) DEFAULT NULL,
  `qualification` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `userId` (`userId`),
  KEY `hostelId` (`hostelId`),
  CONSTRAINT `h_application_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `h_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_application_ibfk_2` FOREIGN KEY (`hostelId`) REFERENCES `h_hostel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_application` */

insert  into `h_application`(`ID`,`userId`,`name`,`hostelId`,`hostelName`,`qualification`,`address`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,2,'Student Student',1,'Demo','MCA','Adsds','adcsdc','Student@gmail.com','Student@gmail.com','2019-10-20 20:38:58','2019-10-20 20:38:58');

/*Table structure for table `h_fee` */

DROP TABLE IF EXISTS `h_fee`;

CREATE TABLE `h_fee` (
  `ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `hostelId` bigint(20) DEFAULT NULL,
  `hostelName` varchar(225) DEFAULT NULL,
  `roomId` bigint(20) DEFAULT NULL,
  `roomName` varchar(225) DEFAULT NULL,
  `totalfee` varchar(225) DEFAULT NULL,
  `pay` varchar(225) DEFAULT NULL,
  `paidfee` varchar(225) DEFAULT NULL,
  `remainingfee` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_h_fee` (`userId`),
  KEY `hostelId` (`hostelId`),
  KEY `roomId` (`roomId`),
  CONSTRAINT `FK_h_fee` FOREIGN KEY (`userId`) REFERENCES `h_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_fee_ibfk_1` FOREIGN KEY (`hostelId`) REFERENCES `h_hostel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_fee_ibfk_2` FOREIGN KEY (`roomId`) REFERENCES `h_room` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_fee` */

insert  into `h_fee`(`ID`,`userId`,`name`,`hostelId`,`hostelName`,`roomId`,`roomName`,`totalfee`,`pay`,`paidfee`,`remainingfee`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,2,'Student Student',1,'Demo',1,'101','2000','1000','1000','1000','Warden@gmail.com','Warden@gmail.com','2019-10-20 20:45:15','2019-10-20 20:45:15');

/*Table structure for table `h_hostel` */

DROP TABLE IF EXISTS `h_hostel`;

CREATE TABLE `h_hostel` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `type` varchar(225) DEFAULT NULL,
  `contact` varchar(225) DEFAULT NULL,
  `address` varchar(755) DEFAULT NULL,
  `description` varchar(755) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fee` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_hostel` */

insert  into `h_hostel`(`ID`,`name`,`type`,`contact`,`address`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`fee`) values (1,'Demo','Boys','9165415596','Adsds','Sxs','Admin@gmail.com','Admin@gmail.com','2019-10-20 20:37:41','2019-10-20 20:37:41','2000');

/*Table structure for table `h_role` */

DROP TABLE IF EXISTS `h_role`;

CREATE TABLE `h_role` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_role` */

insert  into `h_role`(`ID`,`name`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Admin','Administration',NULL,NULL,'2019-10-16 19:47:44','2019-10-16 19:47:40'),(2,'Student','Student',NULL,NULL,'2019-10-16 19:48:03','2019-10-16 19:48:00'),(3,'Warden','Warden',NULL,NULL,'2019-10-16 19:48:15','2019-10-16 19:48:18');

/*Table structure for table `h_room` */

DROP TABLE IF EXISTS `h_room`;

CREATE TABLE `h_room` (
  `ID` bigint(20) NOT NULL,
  `roomNo` varchar(225) DEFAULT NULL,
  `hostelId` bigint(20) DEFAULT NULL,
  `description` varchar(755) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_h_room` (`hostelId`),
  CONSTRAINT `FK_h_room` FOREIGN KEY (`hostelId`) REFERENCES `h_hostel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_room` */

insert  into `h_room`(`ID`,`roomNo`,`hostelId`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'101',1,'dcd','Admin@gmail.com','Admin@gmail.com','2019-10-20 20:38:04','2019-10-20 20:38:04'),(2,'102',1,'dc','Admin@gmail.com','Admin@gmail.com','2019-10-20 20:38:10','2019-10-20 20:38:10');

/*Table structure for table `h_user` */

DROP TABLE IF EXISTS `h_user`;

CREATE TABLE `h_user` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Image` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_user` */

insert  into `h_user`(`ID`,`firstName`,`lastName`,`login`,`password`,`dob`,`mobileNo`,`roleId`,`gender`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`Image`) values (1,'Gogo','gogo','Admin@gmail.com','123','2019-10-16','8586548585',1,'Male',NULL,NULL,'2019-10-24 13:09:32','2019-10-16 19:51:24','gPZwCbdS.jpg'),(2,'Student','Student','Student@gmail.com','Student@123','1997-10-06','8586548585',2,'Male','root','Admin@gmail.com','2019-10-16 19:51:33','2019-10-24 13:06:33','dummy-profile.png'),(3,'Warden','Warden','Warden@gmail.com','Warden@123','1997-10-06','9695654565',3,'Female','Admin@gmail.com','Admin@gmail.com','2019-10-16 20:03:56','2019-10-24 13:07:14','5b517aeb400f9-bpfull.jpg'),(4,'Demo','Demo','Demo@gmail.com','Demo@123','1997-10-06','9165415595',2,'Male','root','root','2019-10-24 13:16:01','2019-10-24 13:16:17','patrik_hudak.jpeg');

/*Table structure for table `h_visitor` */

DROP TABLE IF EXISTS `h_visitor`;

CREATE TABLE `h_visitor` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `contactNo` varchar(225) DEFAULT NULL,
  `studentName` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `relation` varchar(225) DEFAULT NULL,
  `purpose` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_visitor` */

insert  into `h_visitor`(`ID`,`name`,`contactNo`,`studentName`,`address`,`relation`,`purpose`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Lila Bowman','9165415596','Student','sdsdds','Father','Leave','Warden@gmail.com','Warden@gmail.com','2019-10-23 20:24:59','2019-10-23 20:24:59');

/*Table structure for table `h_warden` */

DROP TABLE IF EXISTS `h_warden`;

CREATE TABLE `h_warden` (
  `ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `hostelId` bigint(20) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `FK_h_warden` (`userId`),
  KEY `hostelId` (`hostelId`),
  CONSTRAINT `FK_h_warden` FOREIGN KEY (`userId`) REFERENCES `h_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `h_warden_ibfk_1` FOREIGN KEY (`hostelId`) REFERENCES `h_hostel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_warden` */

insert  into `h_warden`(`ID`,`userId`,`name`,`login`,`hostelId`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,3,'Warden Warden','Warden@gmail.com',1,'Admin@gmail.com','Admin@gmail.com','2019-10-20 20:38:22','2019-10-20 20:38:22');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
