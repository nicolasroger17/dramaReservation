-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 25, 2014 at 12:18 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tp5_communication`
--

-- --------------------------------------------------------

--
-- Table structure for table `drama`
--

CREATE TABLE IF NOT EXISTS `drama` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `remaining_places` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `drama`
--

INSERT INTO `drama` (`id`, `name`, `remaining_places`) VALUES
(1, 'Le marriage de Figaro', 244),
(2, 'Le malade imaginaire', 188),
(3, 'Hamlet', 54),
(4, 'La cantatrice chauve', 2),
(5, 'Les femmes savantes', 82),
(6, 'Phèdre', 137),
(7, 'Andromaque', 41);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `drama_id` int(10) NOT NULL,
  `firstname` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `number_of_places` int(3) NOT NULL,
  `reservation_code` int(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`id`, `drama_id`, `firstname`, `name`, `number_of_places`, `reservation_code`) VALUES
(10, 7, 'Nicolas', 'ROGER', 4, 40183),
(11, 2, 'top', 'top', 2, 55028),
(12, 2, 'rezrzere', 'rezererez', 3, 70929),
(13, 1, 'nin', 'roger', 1, 87830),
(14, 1, 'dsd', 'dsds', 1, 92508),
(15, 1, 'dsqdqsd', 'dsdqsd', 1, 1132),
(16, 1, 'fdsfsdf', 'dssdfs', 1, 69039),
(17, 2, 'dsqdqsd', 'dsdsqd', 3, 41932),
(18, 3, 'ppp', 'Nicola', 2, 97108),
(19, 3, 'ppp', 'Nicola', 4, 79731),
(20, 4, 'ppp', 'Nicola', 2, 97397),
(21, 4, 'Nicolas', 'ROGER', 1, 56954),
(22, 5, 'pppppds', 'jkjjk', 2, 73973),
(23, 6, 'dsqdqsd', 'dsqdsqd', 2, 83300),
(24, 6, 'dsqdqsd', 'dsqdsqd', 2, 9020),
(25, 6, 'dsqdqsd', 'dsqdsqd', 2, 73390);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
