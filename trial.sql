-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: trial
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'Дмитриев Иван Михайлович','log1','34eb73c229e99448e6f24ef9268faa7e375761a86ff1bd88a5e5bfc18f231b82e44dd6e68ac4a5a8fa02f7f33cf648c1e2fb149724f48faaa4e557497e21d46d'),(2,'Четвериков Евгений Глебович','log2','f7c27d9ced576b101cfba1d44e18c33c2e890f8288e4239618a98df5979c64ffc55d36f49cf75d1b6a898e7cea809dcd146ac7c2f9c088accb6688a578b8d7d7'),(3,'Макаров Александр Геннадьевич','log3','dffd2fe2225b292d57157f47fbdc00dc6b81a16f2b55f640094466737ccec0de899afdcdf6613b71ddd774c6dd6d641e9ffa2efb9168073c149eda8356fd4cac'),(4,'Мартынов Сергей Васильевич','log4','5f80899c50120b5958f33bfc720b4948d37a24094d4f537e01f74001007e4979e7062f6c367f1298c2f53bce3d85aaa9af6232309a96354a3b0f45a4dad6c235'),(5,'Сундеев Павел Александрович','log5','eae02d2999444e5832e04e9a973b3b465da37f5093f0d4fd4479cd67dccd17762ccb5fbba45303f3074a190ada87591539be174743768ae547e9abdb1e767f3a');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `analyzes` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'Гребеник Андрей Игоревич',0),(2,'Каменев Алексей Владимирович',1),(3,'Великорецкий Михаил Николаевич',1),(4,'Лебедев Дмитрий Николаевич',1),(5,'Полтавский Александр Александрови',1),(6,'Цимбал Сергей Иванович',1),(7,'Бильченко Сергей Владимирович',1),(8,'Карамшук Максим Анатольевич',1),(9,'Руднев Александр Владимирович',1),(10,'Шестаков Алексей Михайлович',1),(11,'Подольский Владислав Николаевич',0),(12,'Осолодченко Леонид Владимирович',1),(13,'Круглов Александр Николаевич',1),(14,'Елманов Игорь Валентинович',1),(15,'Макацария Лукьян Сергеевич',0);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `id_doctor` bigint unsigned NOT NULL,
  `id_patient` bigint unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (1,1),(1,2),(1,3),(2,4),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),(4,11),(4,12),(4,13),(5,14),(5,15);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-20 17:01:56
