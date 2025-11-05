-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-11-2025 a las 10:10:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mou5labs`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripciones`
--

CREATE TABLE `inscripciones` (
  `id` int(10) UNSIGNED NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `telefono` int(9) NOT NULL,
  `dia` enum('27','28','29') NOT NULL,
  `eleccion` enum('10:00-Automatiza','12:00-Analiza','16:00-B2B','18:00-IaGenerativa','20:00-Colaboracion') NOT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `comentarios` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inscripciones`
--

INSERT INTO `inscripciones` (`id`, `dni`, `nombre`, `apellido`, `mail`, `telefono`, `dia`, `eleccion`, `empresa`, `comentarios`) VALUES
(1, '', 'Iban', 'Richard Echarri', '', 0, '27', '10:00-Automatiza', NULL, NULL),
(2, '', 'Iban', 'Richard', '', 0, '27', '10:00-Automatiza', NULL, NULL),
(3, '', 'Iban', 'Richard Echarri', '', 0, '27', '10:00-Automatiza', NULL, NULL),
(4, '', 'Iban', 'Richard Echarri', '', 0, '27', '10:00-Automatiza', NULL, NULL),
(5, '', 'Iban', 'Richard Echarri', '', 0, '27', '10:00-Automatiza', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
