-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2022 at 11:34 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mbrsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `addbal`
--

CREATE TABLE `addbal` (
  `AccNo` varchar(100) NOT NULL,
  `Amount` bigint(255) UNSIGNED NOT NULL,
  `Date` date DEFAULT current_timestamp(),
  `Method` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addbal`
--

INSERT INTO `addbal` (`AccNo`, `Amount`, `Date`, `Method`) VALUES
('19203033', 14500, '2022-04-22', 'VISA'),
('19203040', 1400, '2022-04-22', 'Mastercard'),
('145621', 1400, '2022-04-23', 'Mastercard'),
('11489', 14561, '2022-04-23', 'VISA'),
('19203033', 5000, '2022-04-23', 'Mastercard'),
('19203033', 5000, '2022-04-23', 'VISA'),
('014505141', 500, '2022-04-23', 'Mastercard'),
('0147565542', 1400, '2022-04-23', 'Mastercard'),
('034434', 30, '2022-04-23', 'Mastercard'),
('034434', 30, '2022-04-23', 'Mastercard'),
('034434', 30, '2022-04-23', 'Mastercard'),
('034434', 30, '2022-04-23', 'Mastercard');

-- --------------------------------------------------------

--
-- Table structure for table `recharge`
--

CREATE TABLE `recharge` (
  `Cnum` varchar(20) NOT NULL,
  `Amount` int(30) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Sim` varchar(20) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recharge`
--

INSERT INTO `recharge` (`Cnum`, `Amount`, `Type`, `Sim`, `Date`) VALUES
('01745782155', 1450, 'Prepaid', 'GP', '2022-04-22'),
('01745782155', 1450, 'Prepaid', 'GP', '2022-04-22'),
('01746518685', 100, 'Prepaid', 'GP', '2022-04-23'),
('0154212124', 20000, 'Prepaid', 'Teletalk', '2022-04-23'),
('19203046', 40, 'Prepaid', 'GP', '2022-04-23');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `amount` int(11) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `amount`) VALUES
('admin', '1234', 11400),
('user', 'pass', 16080);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
