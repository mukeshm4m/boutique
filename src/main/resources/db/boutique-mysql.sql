-- MySQL dump 10.13  Distrib 5.6.31, for Win64 (x86_64)
--
-- Host: localhost    Database: boutique
-- ------------------------------------------------------
-- Server version	5.6.31-log

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
-- Table structure for table `cashier`
--

DROP TABLE IF EXISTS `cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashier` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `StoreId` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashier`
--

LOCK TABLES `cashier` WRITE;
/*!40000 ALTER TABLE `cashier` DISABLE KEYS */;
INSERT INTO `cashier` VALUES (1,'Junaid','a','a',1);
/*!40000 ALTER TABLE `cashier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceNo` varchar(45) DEFAULT NULL,
  `SlipNo` varchar(45) DEFAULT NULL,
  `PaymentReferenceNo` varchar(45) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `PaymentMode` varchar(45) DEFAULT NULL,
  `InvoiceType` varchar(45) DEFAULT NULL,
  `DepositorName` varchar(45) DEFAULT NULL,
  `PaymentDateTime` datetime DEFAULT NULL,
  `PurchaseDateTime` datetime DEFAULT NULL,
  `Currency` varchar(45) DEFAULT NULL,
  `ClientName` varchar(45) DEFAULT NULL,
  `CashierId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (3,'IN-3232-IN','SP-32323','RFN-3232313',5505,'cash','Customer','Mukesh','2017-03-18 02:03:22',NULL,'USD','Chaman',1),(4,'2323','3133','RFN-3232313',1075,'cash','Distributor','Sanjay','2017-03-18 02:40:31',NULL,'USD','Chaman',1),(5,'32','22324','RFN-3244',3775,'cash','Customer','Lalwani','2017-03-18 02:44:09',NULL,'USD','Chaman',1),(6,'asdf','4334343','RDA-2342343233',3700,'cash','Distributor','Kumar','2017-03-18 14:14:21',NULL,'USD','Chaman',1),(7,'2341','2323','asdf02323',21500,'cash','Customer','ad','2017-03-19 14:20:12',NULL,'USD','adf',1),(8,NULL,'3434-34342','RB-2323',3965,'cash','Customer','Waseem','2017-03-19 14:26:16',NULL,'USD','Sanjay',1),(9,NULL,'32434','2322323',3405,'cash','Customer','Kumar','2017-03-19 15:55:10',NULL,'USD','Sanjay',1),(10,NULL,'323434-3434','RFA-32434343',1730,'cash','Customer','Waseem','2017-03-19 18:21:00',NULL,'USD','Kumar',1),(11,NULL,'32334-2323','RFN-342323-233',1640,'cash','Customer','Waseem','2017-03-19 21:02:11',NULL,'USD','Santosh',1),(13,NULL,'3232023','FAE-3232',1750,'cash','Customer','asd','2017-03-19 21:34:14',NULL,'USD','Sasd',1),(14,NULL,'4334-33434','ARA-4e3434',4200,'cash','Customer','Kumar','2017-03-19 21:42:36',NULL,'USD','Test',1),(15,NULL,'343-343-034','RAD-43434-3434',2775,'cash','Customer','Parkash','2017-03-19 21:46:39',NULL,'USD','Gotam',NULL),(16,NULL,'434-343-34','232-2323',1100,'cash','Customer','Dis new','2017-03-19 22:02:54','2017-03-17 00:00:00','USD','New Client',1);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoiceproduct`
--

DROP TABLE IF EXISTS `invoiceproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoiceproduct` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceId` int(11) DEFAULT NULL,
  `ProductId` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoiceproduct`
--

LOCK TABLES `invoiceproduct` WRITE;
/*!40000 ALTER TABLE `invoiceproduct` DISABLE KEYS */;
INSERT INTO `invoiceproduct` VALUES (3,3,3,3),(4,3,1,2),(5,3,5,4),(6,4,1,2),(7,4,4,3),(8,5,1,3),(9,5,4,3),(10,5,5,2),(11,6,1,3),(12,6,5,2),(13,7,1,43),(14,8,5,3),(15,8,3,19),(16,9,3,3),(17,9,5,3),(18,10,1,3),(19,10,3,3),(20,10,4,5),(21,11,3,4),(22,11,1,3),(24,13,4,10),(25,13,1,3),(26,14,1,3),(27,14,6,2),(28,15,4,3),(29,15,6,2),(30,16,1,2),(31,16,4,4);
/*!40000 ALTER TABLE `invoiceproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Description` text,
  `Price` double DEFAULT NULL,
  `ProductCategoryId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Galaxy S6','New Galaxy S6',500,1),(2,'iPhone 6','New iPhone 6 mobile',700,1),(3,'Zone Sim','Zong sim with best packages',35,2),(4,'Ufone Sim','Ufone sim.. tum hi tuo ho',25,2),(5,'Dell Core i5','New Dell Core i5',1100,3),(6,'HP Core i7','HP Core i7 brand new laptop',1350,3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcategory`
--

DROP TABLE IF EXISTS `productcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productcategory` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (1,'Mobile'),(2,'Sim'),(3,'Laptop');
/*!40000 ALTER TABLE `productcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'Blue Area');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-21 23:07:03
