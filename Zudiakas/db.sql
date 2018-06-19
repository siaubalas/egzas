-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2018 at 01:14 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `itemlist`
--

CREATE TABLE `itemlist` (
  `id` int(11) NOT NULL,
  `user` varchar(32) NOT NULL,
  `brand` varchar(32) NOT NULL,
  `title` varchar(32) NOT NULL,
  `bottle` varchar(32) NOT NULL,
  `sizeleft` int(3) NOT NULL,
  `gender` varchar(32) NOT NULL,
  `trade` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itemlist`
--

INSERT INTO `itemlist` (`id`, `user`, `brand`, `title`, `bottle`, `sizeleft`, `gender`, `trade`) VALUES
(10, 'admin', 'kepalas', 'kepalas', 'Gaiv?s', 42, 'Vyriški', 'Taip'),
(14, 'admin', 'debiol ', 'debil', 'Medžio', 50, 'Moteriški', 'Ne'),
(15, 'admin', 'asilas', 'asiliene', 'GaivÅ«s', 50, 'VyriÅ¡ki', 'Taip'),
(16, 'admin', 'vercase', 'kepalas', 'Gaiv?s', 24, 'Vyriški', 'Taip'),
(17, 'admin', 'vercase', 'kaplando', 'Gaiv?s', 24, 'Vyriški', 'Taip'),
(18, 'admin', 'vertabelo', 'academy', 'Sald?s', 52, 'Vyriški', 'Taip'),
(20, 'admin', 'paco ', 'lanuit', 'Gaiv?s', 120, 'Unisex', 'Ne'),
(22, 'admin', 'paco ', 'raboin', 'Medžio', 20, 'Moteriški', 'Taip'),
(24, 'admin', 'debilas debilodas', 'xs lanuit', 'Medžio', 450, 'Vyriški', 'Taip'),
(25, 'admin', 'poaco', 'ciau ciau', 'Gaiv?s', 50, 'Vyriški', 'Taip'),
(27, 'admin', 'debilas', 'debiland', 'Gaiv?s', 0, 'Vyriški', 'Taip'),
(28, 'admin', 'verguci', 'milano 5', 'Gaiv?s', 50, 'Vyriški', 'Taip'),
(29, 'admin', 'verguci', 'milano 5', 'Gaiv?s', 50, 'Vyriški', 'Taip'),
(32, 'jonas', 'versace', 'eau fraische', 'GaivÅ«s', 50, 'VyriÅ¡ki', 'Taip'),
(33, 'raulandas', 'vercase', 'men', 'SaldÅ«s', 15, 'Unisex', 'Ne'),
(34, 'raulandas', 'vercase', 'men', 'SaldÅ«s', 15, 'Unisex', 'Ne'),
(35, 'raulandas', 'vercase', 'men', 'SaldÅ«s', 15, 'Unisex', 'Ne'),
(36, 'admin', 'statkevicius', 'Smirdalai', 'SaldÅ«s', 1000, 'MoteriÅ¡ki', 'Taip'),
(37, 'admin', 'statkevicius', 'Smirdalai', 'SaldÅ«s', 8000, 'MoteriÅ¡ki', 'Taip'),
(38, 'tobas', 'gedas', 'tusciakas', 'GaivÅ«s', 23, 'Unisex', 'Ne'),
(39, 'tobas', 'debil', 'tusciakas', 'GaivÅ«s', 23, 'Unisex', 'Ne'),
(40, 'tomukas', 'armani', 'red sense', 'AÅ¡trÅ«s', 70, 'Unisex', 'Taip'),
(41, 'admin', 'dizaineris', 'pavadinimas', 'GaivÅ«s', 32, 'VyriÅ¡ki', 'Taip'),
(42, 'treciadienis', 'treciad', 'antradienis', 'MedÅ¾io', 50, 'VyriÅ¡ki', 'Taip');

-- --------------------------------------------------------

--
-- Table structure for table `runescape`
--

CREATE TABLE `runescape` (
  `id` int(11) NOT NULL,
  `rsn` varchar(30) NOT NULL,
  `user` varchar(40) NOT NULL,
  `kc` int(11) NOT NULL,
  `bossName` varchar(40) NOT NULL,
  `weapon` varchar(40) NOT NULL,
  `inventory` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `runescape`
--

INSERT INTO `runescape` (`id`, `rsn`, `user`, `kc`, `bossName`, `weapon`, `inventory`) VALUES
(6, 'Edwux', 'Administratorius', 1, 'The Barrows: Rise of the Six', 'Noxious Staff', 'Overloads\nSwitch Weapon or Armor'),
(7, 'asdasda', 'Edwux', 45, 'The Barrows: Rise of the Six', 'Noxious Staff', 'Overloads'),
(8, 'asdasd', 'Edwux', 15, 'The Barrows: Rise of the Six', 'Noxious Staff', 'Overloads'),
(9, 'asdasds', 'Vartotojas', 15, 'The Barrows: Rise of the Six', 'Noxious Staff', 'Overloads'),
(10, 'ASDAS', 'Admin', 15, 'The Barrows: Rise of the Six', 'Noxious Staff', 'Overloads\nSwitch Weapon or Armor\nLuck of the Dwarves\nTeleports');

-- --------------------------------------------------------

--
-- Table structure for table `spring`
--

CREATE TABLE `spring` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `level` int(3) NOT NULL,
  `votec` int(5) NOT NULL,
  `server` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spring`
--

INSERT INTO `spring` (`id`, `name`, `email`, `level`, `votec`, `server`) VALUES
(16, 'Adrijus', 'adrijus@gmail.com', 321, 1, 'adera'),
(18, 'Tomas', 'tetst@gmail.com', 21, 5, 'Adera'),
(19, 'Testukas', 'test@gmail.com', 432, 1, 'adera'),
(21, 'testukas', 'testukas@gmail.com', 32, 5, 'adera'),
(23, 'Adrijauskas', 'adrijus@gmail.com', 3, 3, 'Adera');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `user` varchar(32) NOT NULL,
  `title` varchar(32) NOT NULL,
  `description` varchar(150) NOT NULL,
  `status` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `user`, `title`, `description`, `status`) VALUES
(4, 'domas', 'tomo nedamusta ideja', 'kepalas tu ', 'Done '),
(7, 'tomas', 'task manager', 'dar nebaigei', 'Done '),
(9, 'lopas', 'nauja', 'neperstitempk', 'To Do '),
(10, 'tomas', 'task manager', 'papildyk', 'Done '),
(11, 'tomas', 'task manageris', 'padaryk task manageri on top', 'Done ');

-- --------------------------------------------------------

--
-- Table structure for table `userlist`
--

CREATE TABLE `userlist` (
  `username` varchar(30) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `userlevel` tinyint(1) UNSIGNED NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `userlist`
--

INSERT INTO `userlist` (`username`, `password`, `userlevel`, `email`, `timestamp`) VALUES
('admin', 'demo', 9, 'demo@kitm.lt', '0000-00-00 00:00:00'),
('jonas', 'demo', 1, 'jonas@jonaitis.lt', '2017-12-12 12:40:36'),
('tomas', 'demo123', 1, 'tomas@gmail.com', '2018-01-30 02:48:24'),
('debilas', 'dadada', 1, 'debilas@gmail.com', '2018-02-13 16:30:48'),
('raulandas', 'demodemo', 1, 'siaubalas@gmail.com', '2018-04-24 10:53:58'),
('tobas', 'kepalas', 1, 'tobas@gmail.com', '2018-04-24 11:09:02'),
('tomukas', 'tomukas', 1, 'tomukas@gmail.com', '2018-04-24 11:12:36'),
('lopas', 'demoo', 1, 'lopas@gmail.com', '2018-05-03 21:34:03'),
('treciadienis', 'trecia', 1, 'trec@gmail.com', '2018-04-24 11:28:15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `itemlist`
--
ALTER TABLE `itemlist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `runescape`
--
ALTER TABLE `runescape`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `rsn` (`rsn`);

--
-- Indexes for table `spring`
--
ALTER TABLE `spring`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userlist`
--
ALTER TABLE `userlist`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `itemlist`
--
ALTER TABLE `itemlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `runescape`
--
ALTER TABLE `runescape`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `spring`
--
ALTER TABLE `spring`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
