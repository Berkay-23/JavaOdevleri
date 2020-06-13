-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: otomasyon
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(10) COLLATE utf8_turkish_ci NOT NULL,
  `isim` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `soyisim` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `ePosta` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `sifre` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `dogumTarihi` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `tc` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `telefon` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('BÇ_0','Berkay','Çavuşoğlu','190541023@firat.edu.tr','Berkay23.','8 Aralık 2000','00000000000','05450000000'),('BÇ_2','Behzat','Ç','behzatC@gmail.com','Bc34,','27 Temmuz 1972','28598807469','05428746857'),('CG_0','M Can','Gümüş','can_123@hotmail.com','can123','28 Mayıs 2001','47957678423','05327945858'),('EÇ_0','Eymen','Çavuşoğlu','eymencavusoglu@hotmail.com','eymen23.','30 Aralık 2017','02579657857','05458897566'),('SÇ_0','Sami Hüseyin','Çorumlu','sami@gmail.com','147258','31 Temmuz 1985','12457898785','05478951478'),('SÇ_1','Süleyman','Çakır','s.çakır@istanbulSefiri.com','Çakır44!','27 Ocak 1965','34004040034','05342344034');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yetkili`
--

DROP TABLE IF EXISTS `yetkili`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yetkili` (
  `id` int NOT NULL AUTO_INCREMENT,
  `kullanici_adi` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  `sifre` varchar(45) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yetkili`
--

LOCK TABLES `yetkili` WRITE;
/*!40000 ALTER TABLE `yetkili` DISABLE KEYS */;
INSERT INTO `yetkili` VALUES (3,'admin','admin'),(4,'Fırat Üniversitesi','Tek Fak');
/*!40000 ALTER TABLE `yetkili` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13 23:31:10
