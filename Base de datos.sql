-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         9.6.0 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.17.0.7270
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para supermercado
CREATE DATABASE IF NOT EXISTS `supermercado` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `supermercado`;

-- Volcando estructura para tabla supermercado.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(80) NOT NULL,
  `ubicacion` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla supermercado.categorias: ~7 rows (aproximadamente)
REPLACE INTO `categorias` (`id`, `descripcion`, `ubicacion`) VALUES
	(1, 'Lácteos', 'Pasillo 3'),
	(2, 'Carnes', 'Pasillo 1'),
	(3, 'Verduras', 'Pasillo 1'),
	(4, 'Frutas', 'Pasillo 2'),
	(5, 'Cereales', 'Pasillo 3'),
	(6, 'Aseo', 'Pasillo 3'),
	(7, 'Dulces', 'Pasillo 3');

-- Volcando estructura para tabla supermercado.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacidad_credito` int DEFAULT NULL,
  `correo_electronico` varchar(60) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `identificacion` bigint DEFAULT NULL,
  `nombre_completo` varchar(60) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tipo_identificacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla supermercado.clientes: ~4 rows (aproximadamente)
REPLACE INTO `clientes` (`id`, `capacidad_credito`, `correo_electronico`, `direccion`, `fecha_ingreso`, `identificacion`, `nombre_completo`, `telefono`, `tipo_identificacion`) VALUES
	(1, 500000, 'maria.garcia@email.com', 'Calle 45 # 32-10, Medellín', '2026-05-28', 1023456789, 'María García López', '3001234567', 'CC'),
	(3, 850000, 'juan.ramirez@email.com', 'Carrera 52 # 14-25, Bello', '2026-05-28', 1034567890, 'Juan Esteban Ramírez', '3019876543', 'CC'),
	(4, 1200000, 'laura.fernandez@email.com', 'Calle 78 # 50-12, Envigado', '2026-05-28', 1045678912, 'Laura Fernández Gómez', '3124567890', 'CC'),
	(5, 700000, 'sofia.herrera@email.com', 'Calle 90 # 70-11, Medellín', '2026-05-28', 1089123456, 'Sofía Herrera Molina', '3009988776', 'NIT');

-- Volcando estructura para tabla supermercado.detalles
CREATE TABLE IF NOT EXISTS `detalles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `nro_factura` bigint DEFAULT NULL,
  `producto_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh6w084nyoo5xcjr8urvu28ryj` (`nro_factura`),
  KEY `FKio4oyl8qt5jclekxp7bwws2iy` (`producto_id`),
  CONSTRAINT `FKh6w084nyoo5xcjr8urvu28ryj` FOREIGN KEY (`nro_factura`) REFERENCES `facturas` (`nro_factura`),
  CONSTRAINT `FKio4oyl8qt5jclekxp7bwws2iy` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla supermercado.detalles: ~14 rows (aproximadamente)
REPLACE INTO `detalles` (`id`, `cantidad`, `nro_factura`, `producto_id`) VALUES
	(1, 3, 1, 1),
	(2, 1, 1, 2),
	(3, 5, 2, 3),
	(4, 2, 2, 4),
	(5, 10, 3, 1),
	(6, 4, 3, 5),
	(7, 2, 3, 2),
	(8, 6, 4, 8),
	(9, 3, 4, 3),
	(10, 1, 4, 5),
	(12, 1, 6, 6),
	(13, 1, 6, 7),
	(14, 1, 7, 3),
	(15, 1, 7, 4);

-- Volcando estructura para tabla supermercado.facturas
CREATE TABLE IF NOT EXISTS `facturas` (
  `nro_factura` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_venta` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`nro_factura`),
  KEY `FK1qiuk10rfkovhlfpsk7oic0v8` (`cliente_id`),
  CONSTRAINT `FK1qiuk10rfkovhlfpsk7oic0v8` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla supermercado.facturas: ~6 rows (aproximadamente)
REPLACE INTO `facturas` (`nro_factura`, `descripcion`, `fecha_venta`, `observacion`, `cliente_id`) VALUES
	(1, 'Compra semanal', '2026-05-28', 'Pago en efectivo', 1),
	(2, 'Pedido a oficina', '2026-05-28', 'Entregar antes de las 5 PM', 3),
	(3, 'Compra para evento', '2026-05-28', 'Incluye descuento especial', 4),
	(4, 'Pedido familiar', '2026-05-28', 'Enviar por domicilio', 5),
	(6, 'Compra de fin de mes', '2026-05-28', 'Facturar a nombre de empresa', 5),
	(7, 'Compra de fin de mes', '2026-05-28', 'Facturar a nombre de empresa', 5);

-- Volcando estructura para tabla supermercado.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) DEFAULT NULL,
  `disponible` bit(1) NOT NULL,
  `existencia` int NOT NULL,
  `precio` double NOT NULL,
  `ultimo_ingreso` date DEFAULT NULL,
  `categoria_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2fwq10nwymfv7fumctxt9vpgb` (`categoria_id`),
  CONSTRAINT `FK2fwq10nwymfv7fumctxt9vpgb` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla supermercado.productos: ~16 rows (aproximadamente)
REPLACE INTO `productos` (`id`, `descripcion`, `disponible`, `existencia`, `precio`, `ultimo_ingreso`, `categoria_id`) VALUES
	(1, 'Leche entera x1L', b'1', 37, 3200, '2026-05-28', 1),
	(2, 'Yogur natural', b'1', 7, 25000.5, '2026-05-28', 1),
	(3, 'Pollo LB', b'1', 1, 25500.5, '2026-05-28', 2),
	(4, 'Cerdo LB', b'1', 7, 15500.5, '2026-05-28', 2),
	(5, 'Res LB', b'1', 5, 35500.5, '2026-05-28', 2),
	(6, 'Yuca Lb', b'1', 19, 2500, '2026-05-28', 3),
	(7, 'Apio Lb', b'1', 19, 5500, '2026-05-28', 3),
	(8, 'Papa Lb', b'1', 44, 4500, '2026-05-28', 3),
	(9, 'Arroz Lb', b'1', 50, 6500, '2026-05-28', 4),
	(10, 'Trigo Lb', b'1', 20, 6500, '2026-05-28', 4),
	(11, 'Lentejas Lb', b'1', 10, 8500, '2026-05-28', 5),
	(12, 'Garbanzos Lb', b'1', 70, 8500, '2026-05-28', 5),
	(13, 'Jabon', b'1', 30, 4500, '2026-05-28', 6),
	(14, 'Cloro', b'1', 20, 6500, '2026-05-28', 6),
	(15, 'Chocolates', b'1', 20, 6500, '2026-05-28', 7),
	(16, 'Papitas', b'0', 0, 6500, '2026-05-28', 7);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
