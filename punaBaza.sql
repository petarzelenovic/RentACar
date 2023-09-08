/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.21-MariaDB : Database - rent_a_car
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rent_a_car` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `rent_a_car`;

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `brandID` bigint(20) NOT NULL AUTO_INCREMENT,
  `brandName` varchar(20) NOT NULL,
  `brandModel` varchar(20) NOT NULL,
  PRIMARY KEY (`brandID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

/*Data for the table `brand` */

insert  into `brand`(`brandID`,`brandName`,`brandModel`) values 
(1,'Toyota','Corolla'),
(2,'Audi','A3'),
(3,'Audi','Q5'),
(4,'Toyota','Celica'),
(5,'Mercedes','E220'),
(6,'Porsche','911'),
(7,'Ford','Fiesta'),
(8,'Citroen','C3'),
(9,'Citroen','C5'),
(10,'Toyota','Camry'),
(11,'Toyota','Yaris'),
(12,'Audi','A5'),
(13,'Audi','A4'),
(14,'Audi','A7'),
(15,'Bmw','F10'),
(16,'Bmw','E60'),
(17,'Bmw','M4'),
(18,'Bmw','M3'),
(19,'Ford','Focus'),
(20,'Citroen','Cactus'),
(21,'Honda','Civic'),
(22,'Mazda','3'),
(23,'Kia','Sportage');

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `regNumber` varchar(10) NOT NULL,
  `horsePower` int(11) NOT NULL,
  `color` varchar(20) NOT NULL,
  `brandID` bigint(20) NOT NULL,
  PRIMARY KEY (`regNumber`,`horsePower`,`color`,`brandID`),
  KEY `brandID` (`brandID`),
  CONSTRAINT `car_ibfk_2` FOREIGN KEY (`brandID`) REFERENCES `brand` (`brandID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `car` */

insert  into `car`(`regNumber`,`horsePower`,`color`,`brandID`) values 
('BG-222-CE',192,'Narandzasta',4),
('BG-223-UT',65,'Bela',11),
('BG-224-YJ',134,'Crna',13),
('BG-357-MA',135,'Siva',22),
('BG-543-JC',124,'Ljubicasta',2),
('BG-643-UG',370,'Crna',17),
('BG-743-BN',140,'Crna',10),
('NS-224-HG',120,'Plava',23);

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `clientID` bigint(20) NOT NULL AUTO_INCREMENT,
  `jmbg` varchar(20) NOT NULL,
  `clientName` varchar(20) NOT NULL,
  `clientLastName` varchar(20) NOT NULL,
  `clientContact` varchar(20) NOT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`clientID`),
  KEY `userID` (`userID`),
  CONSTRAINT `client_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `client` */

insert  into `client`(`clientID`,`jmbg`,`clientName`,`clientLastName`,`clientContact`,`userID`) values 
(1,'2601001712501','Petar','Zelenovic','0638049697',1),
(2,'2511000856411','Zarko','Lazetic','066334562',1),
(3,'2007000156644','Milan','Srdic','060334544',1),
(5,'1604000643331','Marko','Markovic','0648559679',1),
(6,'0506002647743','Milorad','Mladenovic','062445658',1),
(7,'2204996134331','Marija','Zaric','0658863493',1),
(8,'1008989715543','Marko','Pantovic','0664562332',5),
(9,'1401995712343','Zorica','Jojic','0664536754',1),
(10,'2704001671454','Veljko','Simic','06635543232',1),
(11,'0506992764132','Ratko','Martinovic','06244566745',1);

/*Table structure for table `confirmation` */

DROP TABLE IF EXISTS `confirmation`;

CREATE TABLE `confirmation` (
  `confirmationID` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  `rentPrice` double NOT NULL,
  `category` varchar(20) NOT NULL,
  `regNumber` varchar(20) NOT NULL,
  `clientID` bigint(20) NOT NULL,
  `priceListItemID` bigint(20) NOT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`confirmationID`),
  KEY `clientID` (`clientID`),
  KEY `userID` (`userID`),
  KEY `priceListItemID` (`priceListItemID`),
  KEY `confirmation_ibfk_1` (`regNumber`),
  CONSTRAINT `confirmation_ibfk_1` FOREIGN KEY (`regNumber`) REFERENCES `car` (`regNumber`) ON DELETE CASCADE,
  CONSTRAINT `confirmation_ibfk_2` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`),
  CONSTRAINT `confirmation_ibfk_4` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `confirmation_ibfk_5` FOREIGN KEY (`priceListItemID`) REFERENCES `pricelistitem` (`priceListItemID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `confirmation` */

insert  into `confirmation`(`confirmationID`,`dateFrom`,`dateTo`,`rentPrice`,`category`,`regNumber`,`clientID`,`priceListItemID`,`userID`) values 
(5,'2023-09-25','2023-09-27',60,'D','BG-543-JC',1,28,1),
(6,'2023-09-15','2023-09-18',90,'C','BG-543-JC',1,11,1),
(7,'2023-09-01','2023-09-07',240,'B','BG-224-YJ',6,10,1),
(10,'2023-09-19','2023-09-26',210,'D','BG-223-UT',1,28,1),
(11,'2023-09-08','2023-09-12',112,'B','BG-224-YJ',2,34,1),
(14,'2023-09-28','2023-09-30',70,'C','BG-543-JC',1,27,1),
(15,'2023-09-07','2023-09-15',176,'D','BG-223-UT',1,20,1),
(17,'2023-09-01','2023-09-08',196,'B','NS-224-HG',1,34,1);

/*Table structure for table `pricelist` */

DROP TABLE IF EXISTS `pricelist`;

CREATE TABLE `pricelist` (
  `priceListID` bigint(20) NOT NULL AUTO_INCREMENT,
  `validFrom` date NOT NULL,
  `validTo` date NOT NULL,
  `userID` bigint(20) NOT NULL,
  PRIMARY KEY (`priceListID`),
  KEY `userID` (`userID`),
  CONSTRAINT `pricelist_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `pricelist` */

insert  into `pricelist`(`priceListID`,`validFrom`,`validTo`,`userID`) values 
(12,'2023-09-01','2023-09-09',1),
(13,'2023-09-10','2023-09-16',1),
(14,'2023-08-30','2023-08-31',1),
(15,'2023-09-20','2023-09-29',1),
(18,'2023-10-05','2023-10-14',1);

/*Table structure for table `pricelistitem` */

DROP TABLE IF EXISTS `pricelistitem`;

CREATE TABLE `pricelistitem` (
  `priceListItemID` bigint(20) NOT NULL AUTO_INCREMENT,
  `priceListID` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `currency` varchar(20) NOT NULL,
  `payingPer` varchar(20) NOT NULL,
  `category` varchar(20) NOT NULL,
  PRIMARY KEY (`priceListItemID`,`priceListID`),
  KEY `priceListID` (`priceListID`),
  CONSTRAINT `pricelistitem_ibfk_2` FOREIGN KEY (`priceListID`) REFERENCES `pricelist` (`priceListID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

/*Data for the table `pricelistitem` */

insert  into `pricelistitem`(`priceListItemID`,`priceListID`,`price`,`currency`,`payingPer`,`category`) values 
(9,12,54,'EUR','DAILY','A'),
(10,12,40,'EUR','DAILY','B'),
(11,12,30,'EUR','DAILY','C'),
(12,12,25,'EUR','DAILY','D'),
(13,12,10,'EUR','HOURLY','A'),
(14,12,8,'EUR','HOURLY','B'),
(15,12,6,'EUR','HOURLY','C'),
(16,12,4,'EUR','HOURLY','D'),
(18,13,32,'EUR','DAILY','B'),
(19,13,25,'EUR','DAILY','C'),
(20,13,22,'EUR','DAILY','D'),
(21,13,15,'EUR','HOURLY','A'),
(22,13,13,'EUR','HOURLY','B'),
(23,13,10,'EUR','HOURLY','C'),
(24,13,8,'EUR','HOURLY','D'),
(25,14,44,'EUR','DAILY','A'),
(26,14,40,'EUR','DAILY','B'),
(27,14,35,'EUR','DAILY','C'),
(28,14,30,'EUR','DAILY','D'),
(29,14,15,'EUR','HOURLY','A'),
(30,14,12,'EUR','HOURLY','B'),
(31,14,10,'EUR','HOURLY','C'),
(32,14,7,'EUR','HOURLY','D'),
(33,15,30,'EUR','DAILY','A'),
(34,15,28,'EUR','DAILY','B'),
(35,15,24,'EUR','DAILY','C'),
(36,15,22,'EUR','DAILY','D'),
(37,15,10,'EUR','HOURLY','A'),
(38,15,8,'EUR','HOURLY','B'),
(39,15,6,'EUR','HOURLY','C'),
(40,15,5,'EUR','HOURLY','D'),
(43,18,58,'EUR','DAILY','A'),
(44,18,55,'EUR','DAILY','B'),
(45,18,50,'EUR','DAILY','C'),
(46,18,40,'EUR','DAILY','D'),
(47,18,15,'EUR','HOURLY','A'),
(48,18,13,'EUR','HOURLY','B'),
(49,18,10,'EUR','HOURLY','C'),
(50,18,8,'EUR','HOURLY','D');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`userID`,`userName`,`password`,`email`,`firstName`,`lastName`) values 
(1,'petar','petar','petar@gmail.com','Petar','Zelenovic'),
(2,'marko','marko','marko@gmaiil.com','Marko','Radivojevic'),
(3,'nikola','nikola','nikola@gmail.com','Nikola','Barac'),
(4,'lazar','lazar','lazar@gmail.com','Lazar','Stosic'),
(5,'luka','luka','luka@gmail.com','Luka','Zdravkovic');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
