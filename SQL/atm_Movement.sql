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
-- Table structure for table `Movement`
--

DROP TABLE IF EXISTS `Movement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Movement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `credit` double DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  `debit` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4qj16d91st0faamx7ycry6xw8` (`account_id`),
  CONSTRAINT `FK4qj16d91st0faamx7ycry6xw8` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movement`
--

LOCK TABLES `Movement` WRITE;
/*!40000 ALTER TABLE `Movement` DISABLE KEYS */;
INSERT INTO `Movement` VALUES (1,200000,0,1547321024776,200000,'Money deposit to Account nº9',9),(2,9000000,0,1547321047697,9000000,'Money deposit to Account nº7',7),(3,8994300,0,1547321407554,5700,'Transference from Account nº7 to Account nº5',7),(4,5700,5700,1547321407554,0,'Transference from Account nº7 to Account nº5',5),(5,8959600,0,1547321455761,34700,'Transference from Account nº7 to Account nº6',7),(6,34700,34700,1547321455761,0,'Transference from Account nº7 to Account nº6',6),(7,4996.68,0,1547321499075,703.32,'Transference from Account nº5 to Account nº4',5),(9,199765.68,0,1547321530788,234.32,'Transference from Account nº9 to Account nº10',9),(10,234.32,234.32,1547321530788,0,'Transference from Account nº9 to Account nº10',10),(11,186122.33299999998,0,1547321546275,13643.347,'Transference from Account nº9 to Account nº11',9),(12,13643.347,13643.347,1547321546275,0,'Transference from Account nº9 to Account nº11',11),(13,0,0,1547321561246,13643.347,'Transference from Account nº11 to Account nº4',11),(15,186107.01299999998,0,1547489497798,15.32,'Restauração',9),(16,186086.653,0,1547489546717,20.36,'Copos',9),(17,186066.293,0,1547489558793,20.36,'Cinema',9),(18,185916.293,0,1547489600010,150,'IUC',9),(19,185766.293,0,1547489601746,150,'Multa',9),(20,185616.293,0,1547489602397,150,'Restaurante',9),(21,185466.293,0,1547489602864,150,'Aquisição impressora',9),(22,184266.293,0,1547489672017,1200,'Despesas Hospitalares',9),(23,184192.293,0,1547489705374,74,'Farmácia',9),(24,183992.293,0,1547489720712,200,'Livros',9),(25,183832.293,0,1547489734600,160,'Passe - transportes',9),(26,183672.293,0,1547489737529,160,'Oficina',9),(27,183612.293,0,1547489750800,60,'compras supermercado',9),(28,183312.293,0,1547489769639,300,'Hotelaria',9),(29,183012.293,0,1547489782072,300,'aluguer viatura',9);
/*!40000 ALTER TABLE `Movement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-17 19:14:34
