# Sequel Pro dump
# Version 1191
# http://code.google.com/p/sequel-pro
#
# Host: 127.0.0.1 (MySQL 5.1.30)
# Database: bookstore
# Generation Time: 2010-05-15 16:17:00 +0530
# ************************************************************

#CREATE DATABASE bookstore ;
#GRANT ALL ON bookstore.* to 'build'@'localhost' IDENTIFIED BY 'build';
#GRANT ALL ON bookstore.* to 'build'@'%' IDENTIFIED BY 'build';

#use bookstore;
# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `binding_type` int(11) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `page_count` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `publishing_date` tinyblob,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

LOCK TABLES `book` WRITE;
INSERT INTO `book` (`id`,`version`,`binding_type`,`isbn`,`page_count`,`price`,`publishing_date`,`title`)
VALUES
	(1,0,0,'111',12,200,NULL,'Java Concurrency in Practice'),
	(2,0,1,'123456',500,1000,NULL,'DDD'),
	(3,0,0,'46567567',234,456,NULL,'Scala Programming'),
	(4,0,1,'8908009-3',700,400,NULL,'Grails in Action'),
	(5,0,0,'234243-89',600,800,NULL,'GWT in Action');

UNLOCK TABLES;

