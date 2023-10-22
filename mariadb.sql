-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.2.1-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for diet-tracker
CREATE DATABASE IF NOT EXISTS `diet-tracker` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `diet-tracker`;

-- Dumping structure for table diet-tracker.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(75) NOT NULL,
  `password` varchar(75) NOT NULL,
  `role` varchar(50) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table diet-tracker.user: ~4 rows (approximately)
INSERT INTO `user` (`id`, `email`, `password`, `role`) VALUES
	(4, 'admin@admin.com', '$2a$10$kDf8CANThWyHu45k5Sj1Iug3ZzoMWWN2ly/CDgBc6KUHiZANNyXz6', 'ADMIN'),
	(5, 'test1@gmail.com', '$2a$10$vMbgt8JkavfZYR0a7vKCqeF5UqJeW7o3ShL4sMZuD1GBdZBBHPYMG', 'USER'),
	(6, 'billybob@gmail.com', '$2a$10$kS6qy.rFYieIK5V4y6unROrSuBxhwe9Wovxk9.KiYY/eStS4upNr6', 'USER'),
	(13, 'lol@lol.com', '$2a$10$Qnpu7DQO51NpkwcybbVtEOkXk6DvU3PW2OD/E5eBMsg/jXxVQ.CW2', 'USER');

-- Dumping structure for table diet-tracker.user_item
CREATE TABLE IF NOT EXISTS `user_item` (
  `user_id` bigint(20) unsigned NOT NULL,
  `item_id` bigint(20) unsigned NOT NULL,
  `quantity` double unsigned NOT NULL DEFAULT 1,
  `meal` int(11) NOT NULL DEFAULT 1,
  `date` date NOT NULL DEFAULT curdate(),
  PRIMARY KEY (`user_id`,`item_id`,`meal`,`date`) USING BTREE,
  KEY `FK_user_item_food` (`item_id`),
  CONSTRAINT `FK_user_item_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table diet-tracker.user_item: ~15 rows (approximately)
INSERT INTO `user_item` (`user_id`, `item_id`, `quantity`, `meal`, `date`) VALUES
	(4, 2009474, 1, 1, '2023-10-22'),
	(4, 2092152, 1, 1, '2023-10-20'),
	(4, 2344749, 2, 1, '2023-10-20'),
	(5, 167782, 1, 1, '2023-10-21'),
	(5, 748967, 1, 1, '2023-10-22'),
	(5, 2066523, 2, 3, '2023-10-22'),
	(5, 2341232, 3, 2, '2023-10-22'),
	(5, 2341752, 1, 1, '2023-10-21'),
	(5, 2342288, 4, 3, '2023-10-20'),
	(5, 2343010, 0.25, 1, '2023-10-22'),
	(5, 2343212, 1, 1, '2023-10-22'),
	(5, 2344720, 1, 2, '2023-10-22'),
	(5, 2344876, 1.5, 3, '2023-10-22'),
	(5, 2345585, 1, 2, '2023-10-22'),
	(6, 2341752, 0.5, 1, '2023-10-20');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
