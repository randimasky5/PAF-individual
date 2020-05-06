-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 03:36 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `testsnrooms`
--

CREATE TABLE `testsnrooms` (
  `test_id` int(8) NOT NULL,
  `test_name` varchar(250) NOT NULL,
  `test_cost` varchar(50) NOT NULL,
  `test_desc` longtext NOT NULL,
  `room_no` varchar(10) NOT NULL,
  `hospital_name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testsnrooms`
--

INSERT INTO `testsnrooms` (`test_id`, `test_name`, `test_cost`, `test_desc`, `room_no`, `hospital_name`) VALUES
(42, 'sugar+test', '100.00', 'avoid+food+(10+hours)', 'LAB001', 'arogya+hosital'),
(43, 'Pusher checkup', '100.00', 'get better sleep before  test', 'Lab001', 'Hemas hospital'),
(44, 'test 01', '1000.00', 'description', 'lab001', 'Hospital'),
(45, 'test 02', '500.00', 'des test', '112', 'hemas'),
(46, 'test03', '400.00', 'descrption', 'lab001', 'hospital');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `testsnrooms`
--
ALTER TABLE `testsnrooms`
  ADD PRIMARY KEY (`test_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `testsnrooms`
--
ALTER TABLE `testsnrooms`
  MODIFY `test_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
