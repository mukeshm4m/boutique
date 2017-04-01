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
  `Role` varchar(45) NOT NULL,
  `StoreId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashier`
--

LOCK TABLES `cashier` WRITE;
/*!40000 ALTER TABLE `cashier` DISABLE KEYS */;
INSERT INTO `cashier` VALUES (1,'Junaid','a','a','Cashier',1),(2,'Sarfraz','admin','admin','Admin',NULL),(3,'Mukesh','mukesh','kumar','Admin',NULL),(4,'Sanjay Kumar','sanjay','kumar','Cashier',4);
/*!40000 ALTER TABLE `cashier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversionrate`
--

DROP TABLE IF EXISTS `conversionrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversionrate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Currency` varchar(45) DEFAULT NULL,
  `Rate` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversionrate`
--

LOCK TABLES `conversionrate` WRITE;
/*!40000 ALTER TABLE `conversionrate` DISABLE KEYS */;
INSERT INTO `conversionrate` VALUES (1,'CDF',1348),(2,'EURO',0.92);
/*!40000 ALTER TABLE `conversionrate` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (3,'IN-3232-IN','SP-32323','RFN-3232313',5505,'cash','Customer','Mukesh','2017-03-18 02:03:22',NULL,'USD','Chaman',1),(4,'2323','3133','RFN-3232313',1075,'cash','Distributor','Sanjay','2017-03-18 02:40:31',NULL,'USD','Chaman',1),(5,'32','22324','RFN-3244',3775,'cash','Customer','Lalwani','2017-03-18 02:44:09',NULL,'USD','Chaman',1),(6,'asdf','4334343','RDA-2342343233',3700,'cash','Distributor','Kumar','2017-03-18 14:14:21',NULL,'USD','Chaman',1),(7,'2341','2323','asdf02323',21500,'cash','Customer','ad','2017-03-19 14:20:12',NULL,'USD','adf',1),(8,NULL,'3434-34342','RB-2323',3965,'cash','Customer','Waseem','2017-03-19 14:26:16',NULL,'USD','Sanjay',1),(9,NULL,'32434','2322323',3405,'cash','Customer','Kumar','2017-03-19 15:55:10',NULL,'USD','Sanjay',1),(10,NULL,'323434-3434','RFA-32434343',1730,'cash','Customer','Waseem','2017-03-19 18:21:00',NULL,'USD','Kumar',1),(11,NULL,'32334-2323','RFN-342323-233',1640,'cash','Customer','Waseem','2017-03-19 21:02:11',NULL,'USD','Santosh',1),(13,NULL,'3232023','FAE-3232',1750,'cash','Customer','asd','2017-03-19 21:34:14',NULL,'USD','Sasd',1),(14,NULL,'4334-33434','ARA-4e3434',4200,'cash','Customer','Kumar','2017-03-19 21:42:36',NULL,'USD','Test',1),(15,NULL,'343-343-034','RAD-43434-3434',2775,'cash','Customer','Parkash','2017-03-19 21:46:39',NULL,'USD','Gotam',4),(16,NULL,'434-343-34','232-2323',1100,'cash','Customer','Dis new','2017-03-19 22:02:54','2017-03-17 00:00:00','USD','New Client',1),(17,NULL,'6576868','879798798876',315,'cash','Customer','jkl','2017-03-21 23:21:28','2017-03-21 00:00:00','USD','iokl',1),(18,NULL,'43343-3434','RFA-4343-3434',9600,'cash','Customer','Butt','2017-03-22 20:51:55','2017-03-22 00:00:00','USD','Usman',1),(19,NULL,'SP-43434-343','RFA-34343-343',2905,'cash','Customer','Mansha','2017-03-22 21:02:39','2017-03-22 00:00:00','USD','Yousef',1),(20,NULL,'3234-234','RA-434-3434',2000,'cash','Customer','Waleed','2017-03-22 21:06:14','2017-03-23 00:00:00','USD','Vaaceph',1),(21,NULL,'333','333',17000,'cash','Customer','asdf','2017-03-24 21:00:17','2017-03-24 00:00:00','USD','Dsd',4),(22,NULL,'2345-45e','234542534',4700,'cash','Customer','Khana','2017-03-24 22:09:15','2017-03-24 00:00:00','USD','Ashok',1),(23,NULL,'3434-343-34','RA-3243-3434',1675,'cash','Customer','Usman','2017-03-25 13:26:03','2017-03-25 00:00:00','USD','Walid',1),(24,NULL,'2323-2323-23','SP-3234-3434-3',4700,'cash','Customer','Salman','2017-03-25 16:54:54','2017-03-24 00:00:00','USD','Waseem',1),(25,NULL,'434-03343-34','RFA-343-3434',11500,'cash','Customer','Lalwani','2017-03-26 12:01:29','2017-03-24 00:00:00','USD','Chaman',1),(26,NULL,'2323-3243-34','RFA-32434-3434',3050,'cash','Customer','Khana','2017-03-26 16:34:58','2017-03-25 00:00:00','USD','Sant',4),(30,NULL,'2324-2343','RFA-232-232-23',1959.9999999999998,'cash','Customer','asd','2017-03-26 22:43:45','2017-03-24 00:00:00','CDF','asd',4),(31,NULL,'434-343-434-','FAR-2343-343',3080,'cash','Customer','asdf','2017-03-26 22:44:54','2017-03-24 00:00:00','CDF','asd',4),(34,NULL,'2323-232-2','23-23-23',84,'cash','Business to Business','Raja','2017-03-26 23:01:35','2017-03-24 00:00:00','CDF','Rameez',4);
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
  `TotalAmount` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoiceproduct`
--

LOCK TABLES `invoiceproduct` WRITE;
/*!40000 ALTER TABLE `invoiceproduct` DISABLE KEYS */;
INSERT INTO `invoiceproduct` VALUES (3,3,3,3,120),(4,3,1,2,1000),(5,3,5,4,4400),(6,4,1,2,1000),(7,4,4,3,90),(8,5,1,3,1500),(9,5,4,3,90),(10,5,5,2,2200),(11,6,1,3,1500),(12,6,5,2,2200),(13,7,1,43,21500),(14,8,5,3,3300),(15,8,3,19,760),(16,9,3,3,120),(17,9,5,3,3300),(18,10,1,3,1500),(19,10,3,3,120),(20,10,4,5,150),(21,11,3,4,160),(22,11,1,3,1500),(24,13,4,10,300),(25,13,1,3,1500),(26,14,1,3,1500),(27,14,6,2,2700),(28,15,4,3,90),(29,15,6,2,2700),(30,16,1,2,1000),(31,16,4,4,120),(32,17,3,9,360),(33,18,1,3,1500),(34,18,6,6,8100),(35,19,3,3,120),(36,19,2,4,2800),(37,20,1,4,2000),(38,21,1,34,17000),(39,22,5,3,3300),(40,22,2,2,1400),(41,23,1,3,1500),(42,23,4,7,210),(43,24,1,4,2000),(44,24,6,2,2700),(45,25,8,5,1000),(46,25,2,15,10500),(47,26,1,3,1500),(48,26,6,1,1350),(49,26,8,1,200),(51,30,2,4,2800),(52,31,5,4,4400),(54,34,3,3,120);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Galaxy S6','New Galaxy S6',500,1),(2,'iPhone 6','iPhone 6',700,1),(3,'Zone Sim','Zong sim with best packages',40,2),(4,'Ufone Sim','Ufone sim.. tum hi tuo ho',50,2),(5,'Dell Core i5','New Dell Core i5',1100,3),(6,'HP Core i7','HP Core i7 brand new laptop',1350,3),(8,'Camel Mountain',NULL,200,4),(9,'iPhone 7',NULL,404,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (1,'Mobile'),(2,'Sim'),(3,'Laptop'),(4,'Bag'),(5,'Cream');
/*!40000 ALTER TABLE `productcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Quantity` int(11) DEFAULT NULL,
  `StoreId` int(11) DEFAULT NULL,
  `ProductId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,65,1,1),(2,75,1,8),(3,20,2,8),(4,0,1,2),(5,10,1,3),(6,6,1,4),(7,10,1,5),(8,12,1,6),(9,32,2,1),(10,11,2,2),(11,22,2,3),(12,33,2,4),(13,44,2,5),(14,55,2,6),(15,0,4,1),(16,0,4,2),(17,0,4,3),(18,0,4,4),(19,0,4,5),(20,0,4,6),(21,0,4,8),(22,0,1,9),(23,0,2,9),(24,0,4,9);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'Blue Area Ltd'),(2,'Boston'),(4,'New York');
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

-- Dump completed on 2017-04-01 10:47:50
