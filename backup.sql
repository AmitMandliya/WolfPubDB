-- MySQL dump 10.14  Distrib 5.5.57-MariaDB, for Linux (x86_64)
--
-- Host: classdb2.csc.ncsu.edu    Database: bpatel24
-- ------------------------------------------------------
-- Server version	5.5.60-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ACCOUNTANTS`
--

DROP TABLE IF EXISTS `ACCOUNTANTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACCOUNTANTS` (
  `AccountantID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`AccountantID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACCOUNTANTS`
--

LOCK TABLES `ACCOUNTANTS` WRITE;
/*!40000 ALTER TABLE `ACCOUNTANTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACCOUNTANTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ARTICLES`
--

DROP TABLE IF EXISTS `ARTICLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ARTICLES` (
  `ArticleID` int(16) NOT NULL DEFAULT '0',
  `CreationDate` date NOT NULL,
  `Text` varchar(2048) DEFAULT NULL,
  `Title` varchar(16) NOT NULL,
  `PublicationID` int(11) NOT NULL,
  PRIMARY KEY (`ArticleID`),
  KEY `ArticleCreationDateIndex` (`CreationDate`),
  KEY `ARTICLES_FK_2` (`PublicationID`),
  CONSTRAINT `ARTICLES_FK_2` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON DELETE CASCADE,
  CONSTRAINT `ARTICLES_ibfk_1` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ARTICLES`
--

LOCK TABLES `ARTICLES` WRITE;
/*!40000 ALTER TABLE `ARTICLES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ARTICLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AUTHOREDBY`
--

DROP TABLE IF EXISTS `AUTHOREDBY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AUTHOREDBY` (
  `PublicationID` int(16) NOT NULL DEFAULT '0',
  `StaffID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PublicationID`,`StaffID`),
  KEY `FK_staff_author` (`StaffID`),
  CONSTRAINT `FK_public_books` FOREIGN KEY (`PublicationID`) REFERENCES `BOOKS` (`PublicationID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_staff_author` FOREIGN KEY (`StaffID`) REFERENCES `AUTHORS` (`StaffID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTHOREDBY`
--

LOCK TABLES `AUTHOREDBY` WRITE;
/*!40000 ALTER TABLE `AUTHOREDBY` DISABLE KEYS */;
/*!40000 ALTER TABLE `AUTHOREDBY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AUTHORS`
--

DROP TABLE IF EXISTS `AUTHORS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AUTHORS` (
  `StaffID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`StaffID`),
  CONSTRAINT `AUTHORS_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON DELETE CASCADE,
  CONSTRAINT `AUTHORS_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTHORS`
--

LOCK TABLES `AUTHORS` WRITE;
/*!40000 ALTER TABLE `AUTHORS` DISABLE KEYS */;
/*!40000 ALTER TABLE `AUTHORS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOKS`
--

DROP TABLE IF EXISTS `BOOKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOOKS` (
  `PublicationID` int(16) NOT NULL DEFAULT '0',
  `ISBN` bigint(20) DEFAULT NULL,
  `Edition` int(8) NOT NULL,
  `Topic` varchar(16) NOT NULL,
  PRIMARY KEY (`PublicationID`),
  UNIQUE KEY `ISBN` (`ISBN`),
  KEY `TopicIndex` (`Topic`),
  CONSTRAINT `fk_2_name` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON DELETE CASCADE,
  CONSTRAINT `BOOKS_ibfk_1` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOKS`
--

LOCK TABLES `BOOKS` WRITE;
/*!40000 ALTER TABLE `BOOKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `BOOKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHAPTERS`
--

DROP TABLE IF EXISTS `CHAPTERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CHAPTERS` (
  `ChapterID` int(16) NOT NULL DEFAULT '0',
  `CreationDate` date NOT NULL,
  `Text` varchar(2048) DEFAULT NULL,
  `Title` varchar(128) NOT NULL,
  `Category` varchar(16) NOT NULL,
  `PublicationID` int(16) NOT NULL,
  PRIMARY KEY (`ChapterID`),
  KEY `BOOKS_FK_2` (`PublicationID`),
  CONSTRAINT `BOOKS_FK_2` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON DELETE CASCADE,
  CONSTRAINT `CHAPTERS_ibfk_1` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHAPTERS`
--

LOCK TABLES `CHAPTERS` WRITE;
/*!40000 ALTER TABLE `CHAPTERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `CHAPTERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CITIES`
--

DROP TABLE IF EXISTS `CITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CITIES` (
  `City` varchar(16) NOT NULL DEFAULT '',
  `Location` int(11) NOT NULL,
  PRIMARY KEY (`City`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CITIES`
--

LOCK TABLES `CITIES` WRITE;
/*!40000 ALTER TABLE `CITIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `CITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLEARDUES`
--

DROP TABLE IF EXISTS `CLEARDUES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLEARDUES` (
  `PaymentID` int(16) NOT NULL DEFAULT '0',
  `DistributorID` int(16) DEFAULT NULL,
  `AccountantID` int(16) DEFAULT NULL,
  `PaymentDate` date NOT NULL,
  `PaymentAmount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `DistributorID` (`DistributorID`),
  KEY `AccountantID` (`AccountantID`),
  KEY `PaymentDateIndex` (`PaymentDate`),
  CONSTRAINT `CLEARDUES_ibfk_1` FOREIGN KEY (`DistributorID`) REFERENCES `DISTRIBUTORS` (`DistributorID`) ON UPDATE CASCADE,
  CONSTRAINT `CLEARDUES_ibfk_2` FOREIGN KEY (`AccountantID`) REFERENCES `ACCOUNTANTS` (`AccountantID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLEARDUES`
--

LOCK TABLES `CLEARDUES` WRITE;
/*!40000 ALTER TABLE `CLEARDUES` DISABLE KEYS */;
/*!40000 ALTER TABLE `CLEARDUES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DISTRIBUTORS`
--

DROP TABLE IF EXISTS `DISTRIBUTORS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DISTRIBUTORS` (
  `DistributorID` int(16) NOT NULL DEFAULT '0',
  `Address` varchar(128) NOT NULL,
  `Name` varchar(64) NOT NULL,
  `Contact` varchar(16) NOT NULL,
  `City` varchar(16) NOT NULL,
  `ContactPerson` varchar(64) NOT NULL,
  `DistributorType` varchar(16) NOT NULL,
  `Balance` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`DistributorID`),
  KEY `FK_distr` (`City`),
  CONSTRAINT `FK_distr` FOREIGN KEY (`City`) REFERENCES `CITIES` (`City`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISTRIBUTORS`
--

LOCK TABLES `DISTRIBUTORS` WRITE;
/*!40000 ALTER TABLE `DISTRIBUTORS` DISABLE KEYS */;
/*!40000 ALTER TABLE `DISTRIBUTORS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EDITEDBY`
--

DROP TABLE IF EXISTS `EDITEDBY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EDITEDBY` (
  `PublicationID` int(16) NOT NULL DEFAULT '0',
  `StaffID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PublicationID`,`StaffID`),
  KEY `EDITEDBY_ibfk_3` (`StaffID`),
  CONSTRAINT `EDITEDBY_ibfk_3` FOREIGN KEY (`StaffID`) REFERENCES `EDITORS` (`StaffID`) ON UPDATE CASCADE,
  CONSTRAINT `EDITEDBY_ibfk_1` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EDITEDBY`
--

LOCK TABLES `EDITEDBY` WRITE;
/*!40000 ALTER TABLE `EDITEDBY` DISABLE KEYS */;
/*!40000 ALTER TABLE `EDITEDBY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EDITORS`
--

DROP TABLE IF EXISTS `EDITORS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EDITORS` (
  `StaffID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`StaffID`),
  CONSTRAINT `EDITORS_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON DELETE CASCADE,
  CONSTRAINT `EDITORS_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EDITORS`
--

LOCK TABLES `EDITORS` WRITE;
/*!40000 ALTER TABLE `EDITORS` DISABLE KEYS */;
/*!40000 ALTER TABLE `EDITORS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GENERATEPAYMENT`
--

DROP TABLE IF EXISTS `GENERATEPAYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GENERATEPAYMENT` (
  `StaffID` int(11) NOT NULL,
  `PaycheckID` int(16) NOT NULL,
  `AccountantID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Amount` decimal(9,2) NOT NULL,
  `IsClaimed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PaycheckID`),
  KEY `StaffID` (`StaffID`),
  KEY `AccountantID` (`AccountantID`),
  CONSTRAINT `GENERATEPAYMENT_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON UPDATE CASCADE,
  CONSTRAINT `GENERATEPAYMENT_ibfk_2` FOREIGN KEY (`AccountantID`) REFERENCES `ACCOUNTANTS` (`AccountantID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GENERATEPAYMENT`
--

LOCK TABLES `GENERATEPAYMENT` WRITE;
/*!40000 ALTER TABLE `GENERATEPAYMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `GENERATEPAYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JOURNALISTS`
--

DROP TABLE IF EXISTS `JOURNALISTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JOURNALISTS` (
  `StaffID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`StaffID`),
  CONSTRAINT `JOURNALISTS_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON DELETE CASCADE,
  CONSTRAINT `JOURNALISTS_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `STAFF` (`StaffID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JOURNALISTS`
--

LOCK TABLES `JOURNALISTS` WRITE;
/*!40000 ALTER TABLE `JOURNALISTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `JOURNALISTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERS` (
  `OrderID` int(16) NOT NULL DEFAULT '0',
  `OrderDate` date NOT NULL,
  `ExpectedDate` date NOT NULL,
  `ShippingCost` decimal(10,2) DEFAULT NULL,
  `IsPaid` tinyint(1) NOT NULL,
  `NumberCopies` int(16) NOT NULL,
  `Price` decimal(9,2) NOT NULL,
  `DistributorID` int(16) DEFAULT NULL,
  `PublicationID` int(16) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `DistributorID` (`DistributorID`),
  KEY `PublicationID` (`PublicationID`),
  CONSTRAINT `ORDERS_ibfk_1` FOREIGN KEY (`DistributorID`) REFERENCES `DISTRIBUTORS` (`DistributorID`) ON UPDATE CASCADE,
  CONSTRAINT `ORDERS_ibfk_2` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERSANDBILL`
--

DROP TABLE IF EXISTS `ORDERSANDBILL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERSANDBILL` (
  `OrderID` int(16) NOT NULL DEFAULT '0',
  `BillDate` date NOT NULL,
  `AccountantID` int(16) NOT NULL,
  `BillAmount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `FK_ord` (`AccountantID`),
  CONSTRAINT `FK_ord` FOREIGN KEY (`AccountantID`) REFERENCES `ACCOUNTANTS` (`AccountantID`) ON UPDATE CASCADE,
  CONSTRAINT `ORDERSANDBILL_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `ORDERS` (`OrderID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERSANDBILL`
--

LOCK TABLES `ORDERSANDBILL` WRITE;
/*!40000 ALTER TABLE `ORDERSANDBILL` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORDERSANDBILL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERIODICPUBLICATIONS`
--

DROP TABLE IF EXISTS `PERIODICPUBLICATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERIODICPUBLICATIONS` (
  `PublicationID` int(16) NOT NULL DEFAULT '0',
  `Periodicity` varchar(32) NOT NULL,
  `IssueNumber` int(16) NOT NULL,
  `Type` varchar(16) NOT NULL,
  `Category` varchar(16) NOT NULL,
  PRIMARY KEY (`PublicationID`),
  CONSTRAINT `pp_fk_2` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON DELETE CASCADE,
  CONSTRAINT `PERIODICPUBLICATIONS_ibfk_1` FOREIGN KEY (`PublicationID`) REFERENCES `PUBLICATIONS` (`PublicationID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERIODICPUBLICATIONS`
--

LOCK TABLES `PERIODICPUBLICATIONS` WRITE;
/*!40000 ALTER TABLE `PERIODICPUBLICATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERIODICPUBLICATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PUBLICATIONS`
--

DROP TABLE IF EXISTS `PUBLICATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PUBLICATIONS` (
  `PublicationID` int(16) NOT NULL,
  `Title` varchar(64) NOT NULL,
  `PublicationDate` date DEFAULT NULL,
  PRIMARY KEY (`PublicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PUBLICATIONS`
--

LOCK TABLES `PUBLICATIONS` WRITE;
/*!40000 ALTER TABLE `PUBLICATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PUBLICATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STAFF`
--

DROP TABLE IF EXISTS `STAFF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STAFF` (
  `StaffID` int(16) NOT NULL,
  `IsInvited` tinyint(1) NOT NULL,
  `StaffName` varchar(64) NOT NULL,
  `Type` varchar(16) NOT NULL,
  PRIMARY KEY (`StaffID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STAFF`
--

LOCK TABLES `STAFF` WRITE;
/*!40000 ALTER TABLE `STAFF` DISABLE KEYS */;
/*!40000 ALTER TABLE `STAFF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WRITTENBY`
--

DROP TABLE IF EXISTS `WRITTENBY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WRITTENBY` (
  `ArticleID` int(16) NOT NULL DEFAULT '0',
  `StaffID` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ArticleID`,`StaffID`),
  KEY `WRITTENBY_ibfk_3` (`StaffID`),
  CONSTRAINT `WRITTENBY_ibfk_3` FOREIGN KEY (`StaffID`) REFERENCES `JOURNALISTS` (`StaffID`) ON UPDATE CASCADE,
  CONSTRAINT `WRITTENBY_ibfk_1` FOREIGN KEY (`ArticleID`) REFERENCES `ARTICLES` (`ArticleID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WRITTENBY`
--

LOCK TABLES `WRITTENBY` WRITE;
/*!40000 ALTER TABLE `WRITTENBY` DISABLE KEYS */;
/*!40000 ALTER TABLE `WRITTENBY` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-07 10:16:09
