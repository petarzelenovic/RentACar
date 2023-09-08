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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
