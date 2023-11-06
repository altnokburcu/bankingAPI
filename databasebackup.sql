-- MySQL dump 10.13  Distrib 8.2.0, for macos13 (x86_64)
--
-- Host: localhost    Database: mydatabase
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uuid` bigint DEFAULT '0',
  `owner` varchar(255) NOT NULL,
  `balance` bigint NOT NULL,
  `currency` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (11,3113748762064733353,'Zeliş Altınok',0,'TL','2023-11-04 18:37:43',0),(12,5547294678628847466,'Duygu Altın',30000,'TL','2023-11-04 18:51:33',0),(13,7896152962668054410,'Zeliş Altınok',400,'TL','2023-11-05 11:09:49',0),(14,6744435196416576774,'Zeliş Altı',12345,'TL','2023-11-05 11:10:28',0),(15,3269823528515685943,'Zeliş Altınok',600,'TL','2023-11-05 11:12:00',0),(16,4736551498448456802,'Zeliş Altınok',600,'TL','2023-11-05 11:12:19',0),(17,7896152962668054411,'Burcu Altınok',1120,'TL','2023-11-05 11:17:11',1),(18,7896152962668054412,'Burcu Altınok',179900,'TL','2023-11-05 11:19:04',0),(21,5999554568058063943,'Burcu Altınok',580,'TL','2023-11-05 12:45:22',0),(22,2601026642077633134,'Ali Ahmet',160,'TL','2023-11-06 10:27:50',1),(23,4035938332810955452,'Zeliş Altınok',40000,'USD','2023-11-06 11:18:12',0),(24,8109260138398960774,'Feriha Yılmaz',40000,'TL','2023-11-06 11:18:28',0),(25,7212472854858974509,'Emir Sarrafoğlu',2800000040,'TL','2023-11-06 11:18:50',0),(26,4324021630318364290,'Hande Sarrafoğlu',2800000000,'TL','2023-11-06 12:18:30',0),(27,8546286075028524656,'Ayşe Sarrafoğlu',280000000,'TL','2023-11-06 17:45:00',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uuid` bigint DEFAULT '0',
  `account_id` bigint NOT NULL,
  `amount` bigint NOT NULL COMMENT 'can be negative or positive',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (1,6359683351177807430,4,122,'2023-11-02 13:25:48',0),(2,5861989027180528213,8,80000,'2023-11-02 13:49:26',0),(4,1197138608392127620,3,90000,'2023-11-05 13:14:56',0),(5,8897637317179820719,7,90000,'2023-11-05 13:16:30',0),(6,8903919396147184092,8,80000,'2023-11-05 13:31:00',0),(8,7903764476140601967,7896152962668054412,90000,'2023-11-06 10:38:54',0),(10,7254726226650809272,7896152962668054412,90000,'2023-11-06 17:38:56',0),(12,6730989274719274632,7896152962668054412,100,'2023-11-06 17:44:11',0),(13,2258111600571600326,7896152962668054412,100,'2023-11-06 18:28:50',0),(14,7100300681317270202,7896152962668054412,100,'2023-11-06 18:29:59',0);
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfers`
--

DROP TABLE IF EXISTS `transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uuid` bigint DEFAULT '0',
  `from_account_id` bigint NOT NULL,
  `to_account_id` bigint NOT NULL,
  `amount` bigint NOT NULL COMMENT 'must be positive',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `from_account_id` (`from_account_id`),
  KEY `to_account_id` (`to_account_id`),
  KEY `from_account_id_2` (`from_account_id`,`to_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfers`
--

LOCK TABLES `transfers` WRITE;
/*!40000 ALTER TABLE `transfers` DISABLE KEYS */;
INSERT INTO `transfers` VALUES (1,2449101458838998081,2601026642077633134,7896152962668054411,20,'2023-11-06 18:26:00',0),(2,3616059406661403705,2601026642077633134,7896152962668054411,20,'2023-11-06 18:27:54',0),(3,9139212164073472337,2601026642077633134,7896152962668054411,20,'2023-11-06 18:31:53',0),(4,4097961595252525278,2601026642077633134,7896152962668054411,20,'2023-11-06 18:32:23',0),(5,6517456428971672165,2601026642077633134,7896152962668054411,20,'2023-11-06 18:35:35',1);
/*!40000 ALTER TABLE `transfers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-06 21:45:51