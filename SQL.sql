-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: atm
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhxhbysiiar58qrqrjspwuovhj` (`user_id`),
  CONSTRAINT `FKhxhbysiiar58qrqrjspwuovhj` FOREIGN KEY (`user_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (5,4996.68,'CGD',3),(6,34700,'BIC',3),(7,8959600,'NB',3),(9,180356.293,'NB',8),(10,234.32,'Montepio',8),(11,3568,'Montepio',8);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `espechial` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tel` bigint(20) DEFAULT NULL,
  `token` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
INSERT INTO `Client` VALUES (1,'manager@mail.com',_binary '','Manager','-146771471',111222333,1645541190),(3,'Manel@mail.com',_binary '\0','Manel','103785431',938002003,NULL),(8,'joaquim@mail.com',_binary '\0','joaquim','106854338',245787652,NULL),(11,'qwerty.com',_binary '\0','TOZE dedeta','3003444',1874529,NULL);
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movement`
--

DROP TABLE IF EXISTS `movement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `credit` double DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  `debit` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4qj16d91st0faamx7ycry6xw8` (`account_id`),
  CONSTRAINT `FK4qj16d91st0faamx7ycry6xw8` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movement`
--

LOCK TABLES `movement` WRITE;
/*!40000 ALTER TABLE `movement` DISABLE KEYS */;
INSERT INTO `movement` VALUES (1,200000,0,1547321024776,200000,'Money deposit to Account nº9','Deposit',9),(2,9000000,0,1547321047697,9000000,'Money deposit to Account nº7','Deposit',7),(3,8994300,0,1547321407554,5700,'Transference from Account nº7 to Account nº5','Transfer',7),(4,5700,5700,1547321407554,0,'Transference from Account nº7 to Account nº5','Transfer',5),(6,34700,34700,1547321455761,0,'Transference from Account nº7 to Account nº6','Transfer',6),(7,4996.68,0,1547321499075,703.32,'Transference from Account nº5 to Account nº4','Transfer',5),(9,199765.68,0,1547321530788,234.32,'Transference from Account nº9 to Account nº10','Transfer',9),(10,234.32,234.32,1547321530788,0,'Transference from Account nº9 to Account nº10','Transfer',10),(11,186122.33299999998,0,1547321546275,13643.347,'Transference from Account nº9 to Account nº11','Transfer',9),(12,13643.347,13643.347,1547321546275,0,'Transference from Account nº9 to Account nº11','Transfer',11),(13,0,0,1547321561246,13643.347,'Transference from Account nº11 to Account nº4','Transfer',11),(15,186107.01299999998,0,1547489497798,15.32,'Restauração','Alimentação',9),(16,186086.653,0,1547489546717,20.36,'Copos','Alimentação',9),(17,186066.293,0,1547489558793,20.36,'Cinema','Entertenimento',9),(18,185916.293,0,1547489600010,150,'IUC','Estado',9),(19,185766.293,0,1547489601746,150,'Multa','Estado',9),(20,185616.293,0,1547489602397,150,'Restaurante','Alimentação',9),(21,185466.293,0,1547489602864,150,'Aquisição impressora','Tecnologia',9),(22,184266.293,0,1547489672017,1200,'Despesas Hospitalares','Saúde',9),(23,184192.293,0,1547489705374,74,'Farmácia','Saúde',9),(24,183992.293,0,1547489720712,200,'Livros','Cultura',9),(25,183832.293,0,1547489734600,160,'Passe - transportes','Transportes',9),(26,183672.293,0,1547489737529,160,'Oficina','Transportes',9),(27,183612.293,0,1547489750800,60,'compras supermercado','Alimentação',9),(28,183312.293,0,1547489769639,300,'Hotelaria','Hotelaria ',9),(29,183012.293,0,1547489782072,300,'aluguer viatura','Transportes',9),(30,180656.293,0,1547815795586,2356,'Transference from Account nº9 to Account nº11','Transfer',9),(31,2590,2356,1547815795586,0,'Transference from Account nº9 to Account nº11','Transfer',11),(32,3490,0,1547815978764,900,NULL,'deposit',11),(33,180356.293,0,1547816039394,300,'Café','pickup',9),(35,3516,26,1547855685213,0,'Transference from Account nº12 to Account nº11','Transfer',11),(37,3542,26,1547855876319,0,'Transference from Account nº13 to Account nº11','Transfer',11),(39,3568,26,1547855983538,0,'Transference from Account nº13 to Account nº11','Transfer',11);
/*!40000 ALTER TABLE `movement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-21 16:28:56
