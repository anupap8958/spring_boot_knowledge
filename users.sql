-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2023 at 07:04 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test-course`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` int(11) NOT NULL DEFAULT 0,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `age`, `address`, `created_at`, `updated_at`) VALUES
(1, 'user1', 'user1@gmail.com', 21, 'bangkok', '2023-03-06 05:47:29', '2023-03-06 05:47:29'),
(2, 'user2', 'user2@gmail.com', 25, 'bangkok', '2023-03-06 05:49:53', '2023-03-06 05:54:53'),
(3, 'user3', 'user3@gmail.com', 18, 'nakhon pathom', '2023-03-06 05:59:02', '2023-03-06 05:59:02'),
(4, 'user4', 'user4@gmail.com', 12, 'bangkok', '2023-03-06 05:59:25', '2023-03-06 05:59:25'),
(5, 'user5', 'user5@gmail.com', 19, 'nakhon pathom', '2023-03-06 05:59:54', '2023-03-06 05:59:54'),
(6, 'user6', 'user6@gmail.com', 16, 'nakhon pathom', '2023-03-06 06:00:09', '2023-03-06 06:00:09'),
(7, 'user7', 'user7@gmail.com', 24, 'bangkok', '2023-03-06 06:00:25', '2023-03-06 06:00:25'),
(8, 'user8', 'user8@gmail.com', 17, 'bangkok', '2023-03-06 06:00:45', '2023-03-06 06:00:45'),
(9, 'user9', 'user9@gmail.com', 28, 'nakhon pathom', '2023-03-06 06:01:04', '2023-03-06 06:01:04'),
(10, 'user10', 'user10@gmail.com', 14, 'nakhon pathom', '2023-03-06 06:01:30', '2023-03-06 06:01:30');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
