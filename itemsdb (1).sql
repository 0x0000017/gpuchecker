-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2020 at 11:14 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `itemsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_items`
--

CREATE TABLE `tbl_items` (
  `item_ID` int(20) NOT NULL,
  `item_name` varchar(20) NOT NULL,
  `item_quantity` int(20) NOT NULL,
  `item_price` int(20) NOT NULL,
  `item_memory` int(20) NOT NULL,
  `vendor_ID` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_items`
--

INSERT INTO `tbl_items` (`item_ID`, `item_name`, `item_quantity`, `item_price`, `item_memory`, `vendor_ID`) VALUES
(10, 'GTX 1050ti', 233, 3232, 2, 1),
(12, 'RX 580', 1222, 10000, 8, 2),
(13, 'GTX 750ti', 2323, 3000, 2, 3),
(14, 'GTX 950 OCV3', 200, 4000, 2, 4),
(15, 'GTX 1650 OC', 3000, 8500, 4, 5),
(16, 'GTX 980ti STRIX', 2000, 9500, 6, 6),
(17, 'GTX 750ti', 100, 3500, 2, 9),
(18, 'RTX 3090', 1, 44000, 24, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_misc`
--

CREATE TABLE `tbl_misc` (
  `misc_ID` int(20) NOT NULL,
  `misc_vendorName` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_misc`
--

INSERT INTO `tbl_misc` (`misc_ID`, `misc_vendorName`) VALUES
(1, 'NVidia'),
(2, 'AMD'),
(3, 'PALIT'),
(4, 'MSI'),
(5, 'GIGABYTE'),
(6, 'ASUS'),
(7, 'EVGA'),
(8, 'SAPPHIRE'),
(9, 'ZOTAC');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_items`
--
ALTER TABLE `tbl_items`
  ADD PRIMARY KEY (`item_ID`),
  ADD KEY `vendor_ID` (`vendor_ID`);

--
-- Indexes for table `tbl_misc`
--
ALTER TABLE `tbl_misc`
  ADD PRIMARY KEY (`misc_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_items`
--
ALTER TABLE `tbl_items`
  MODIFY `item_ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tbl_misc`
--
ALTER TABLE `tbl_misc`
  MODIFY `misc_ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_items`
--
ALTER TABLE `tbl_items`
  ADD CONSTRAINT `tbl_items_ibfk_1` FOREIGN KEY (`vendor_ID`) REFERENCES `tbl_misc` (`misc_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
