-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: gestiondocument
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `DocumentID` int NOT NULL AUTO_INCREMENT,
  `DocumentName` varchar(50) DEFAULT NULL,
  `DocumentDate` datetime DEFAULT NULL,
  `StorageAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DocumentID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,'Gestion Document.sql','2022-06-21 18:46:28','/user/EFREI/MasterCamp/TP4'),(2,'Vote en Ligne.sql','2022-06-21 18:48:07','/user/EFREI/MasterCamp/SolutionFactory/Projet'),(3,'Site Internet.html',NULL,'/user/EFREI/MasterCamp/SolutionFactory/Projet'),(4,'Mon CV.docx','2022-06-21 18:54:23','/user/PERSO'),(5,'Lettre de Motivation.docx','2022-06-21 19:06:59','/user/PERSO'),(6,'Cours Theorie des Graphes.pdf','2022-06-21 19:07:54','/user/EFREI/S06/Maths'),(7,'Exercices Theorie des Graphes.pdf','2022-06-21 19:08:38','/user/EFREI/S06/Maths'),(8,'Exercices Corrigés Theorie des Graphes.pdf','2022-06-21 19:09:31','/user/EFREI/S06/Maths'),(9,'Appli.js','2022-06-21 19:11:29','/user/EFREI/S06/JavaScript'),(10,'Application.native.js','2022-06-21 19:12:24','/user/PERSO/ReactNative/Projet');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-21 19:14:00
