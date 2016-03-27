-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Hoszt: 127.0.0.1
-- L�trehoz�s ideje: 2016. M�r 27. 21:36
-- Szerver verzi�: 5.7.10-log
-- PHP verzi�: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Adatb�zis: `beershop`
--

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `beer`
--

CREATE TABLE IF NOT EXISTS `beer` (
  `id` bigint(20) NOT NULL,
  `alcoholLevel` double NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `capacity` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_midwiatn25b1u6adr7n1listr` (`name`),
  UNIQUE KEY `UK_fi0xpc19rx2wj58ec19rkjydc` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `cargo`
--

CREATE TABLE IF NOT EXISTS `cargo` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dateOfOrder` datetime DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKifftdxramgjviji0diildao2u` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `cargo_beer`
--

CREATE TABLE IF NOT EXISTS `cargo_beer` (
  `Cargo_id` bigint(20) NOT NULL,
  `beers_id` bigint(20) NOT NULL,
  KEY `FKf3fedawi0l1hodoho3m3tk7a0` (`beers_id`),
  KEY `FKbxuy9cypnm5b4i45j2esfppe2` (`Cargo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A t�bla adatainak ki�rat�sa `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3),
(3),
(3),
(3);

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKalr5p23og62h622vbxyqoltcs` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A t�bla adatainak ki�rat�sa `role`
--

INSERT INTO `role` (`id`, `name`, `user_id`) VALUES
(1, 'ROLE_USER', NULL);

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `dateOfBirth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `points` double DEFAULT NULL,
  `rank` varchar(32) DEFAULT 'Amatuer',
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A t�bla adatainak ki�rat�sa `user`
--

INSERT INTO `user` (`id`, `dateOfBirth`, `email`, `password`, `points`, `rank`, `username`) VALUES
(1, '1995-10-20 00:00:00', 'holi60@gmail.com', 'asderlow', 0, 'Amatuer', 'Holi60');

-- --------------------------------------------------------

--
-- T�bla szerkezet ehhez a t�bl�hoz `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `User_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_tc5k40i3kit8944syrd366vy1` (`roles_id`),
  KEY `FKc52d1rv3ijbpu6lo2v3rej1tx` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A t�bla adatainak ki�rat�sa `user_role`
--

INSERT INTO `user_role` (`User_id`, `roles_id`) VALUES
(1, 1);

--
-- Megk�t�sek a ki�rt t�bl�khoz
--

--
-- Megk�t�sek a t�bl�hoz `cargo`
--
ALTER TABLE `cargo`
  ADD CONSTRAINT `FKbtwcxkj4sph8e7xw86b7me68e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKifftdxramgjviji0diildao2u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Megk�t�sek a t�bl�hoz `cargo_beer`
--
ALTER TABLE `cargo_beer`
  ADD CONSTRAINT `FKbxuy9cypnm5b4i45j2esfppe2` FOREIGN KEY (`Cargo_id`) REFERENCES `cargo` (`id`),
  ADD CONSTRAINT `FKf3fedawi0l1hodoho3m3tk7a0` FOREIGN KEY (`beers_id`) REFERENCES `beer` (`id`);

--
-- Megk�t�sek a t�bl�hoz `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FKalr5p23og62h622vbxyqoltcs` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Megk�t�sek a t�bl�hoz `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK7qnwwe579g9frolyprat52l4d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKc52d1rv3ijbpu6lo2v3rej1tx` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
