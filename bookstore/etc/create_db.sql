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

# Dump of table book_author
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book_author`;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `author`;

# Dump of table author
# ------------------------------------------------------------
CREATE TABLE `author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `author` WRITE;
INSERT INTO `author` (`id`,`version`,`name`)
VALUES
	(1,0,'Aparna'),
	(2,0,'Yogesh');

UNLOCK TABLES;


# Dump of table book
# ------------------------------------------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `book` WRITE;
INSERT INTO `book` (`id`,`version`,`binding_type`,`isbn`,`page_count`,`price`,`publishing_date`,`title`)
VALUES
	(1,0,0,'111-2',120,200,NULL,'Java Concurrency in Practice'),
	(2,0,1,'123456',500,1000,NULL,'DDD'),
	(3,0,0,'46567567',234,456,NULL,'Scala Programming'),
	(4,0,1,'8908009-3',700,400,NULL,'Grails in Action'),
	(5,0,0,'234243-89',600,800,NULL,'GWT in Action');

UNLOCK TABLES;

CREATE TABLE `book_author` (
  `book_id` bigint(20) NOT NULL,
  `authors_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`authors_id`),
  KEY `FKEDCB7BA1FC50BB05` (`book_id`),
  KEY `FKEDCB7BA157960D28` (`authors_id`),
  CONSTRAINT `FKEDCB7BA157960D28` FOREIGN KEY (`authors_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKEDCB7BA1FC50BB05` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `book_author` WRITE;
INSERT INTO `book_author` (`book_id`,`authors_id`)
VALUES
	(1,1),
	(2,1),
	(2,2),
	(3,2),
	(4,1),
	(5,2);

UNLOCK TABLES;

