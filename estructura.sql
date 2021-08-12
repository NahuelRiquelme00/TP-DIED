--CREATE SCHEMA tp_died;
--DROP SCHEMA tp_died CASCADE;

CREATE TYPE tp_died.estadoEstacion AS ENUM ('OPERATIVA','EN_MANTENIMIENTO');
CREATE TYPE tp_died.estadoLineaTransporte AS ENUM ('ACTIVA','NO_ACTIVA');
CREATE TYPE tp_died.estadoTrayecto AS ENUM ('ACTIVO','NO_ACTIVO');
CREATE TYPE tp_died.colorLinea AS ENUM ('ROJA', 'NARANJA', 'AMARILLA', 'AZUL', 'VERDE_CLARA', 'VERDE_OSCURA');

CREATE TABLE tp_died.estacion(
	id_estacion serial PRIMARY KEY,
	nombre varchar(30) UNIQUE NOT NULL,
	horarioApertura time NOT NULL,
	horarioCierre time NOT NULL,
	estado tp_died.estadoEstacion NOT NULL
);

CREATE TABLE tp_died.mantenimiento(
	id_mantenimiento serial PRIMARY KEY,
	id_estacion integer REFERENCES tp_died.estacion(id_estacion) ON DELETE CASCADE,
	fechaInicio date,
	fechaFin date,
	observaciones varchar(200)
);

CREATE TABLE tp_died.linea_de_transporte(
	id_linea serial PRIMARY KEY,
	nombre varchar(30) NOT NULL,
	color tp_died.colorLinea NOT NULL,
	estado tp_died.estadoLineaTransporte NOT NULL,
	UNIQUE (nombre, color)
);

CREATE TABLE tp_died.trayecto(
	id_trayecto serial PRIMARY KEY,
	id_linea integer NOT NULL REFERENCES tp_died.linea_de_transporte(id_linea) ON DELETE CASCADE,
	id_estacion_origen integer NOT NULL REFERENCES tp_died.estacion(id_estacion) ON DELETE CASCADE,
	id_estacion_destino integer NOT NULL REFERENCES tp_died.estacion(id_estacion) ON DELETE CASCADE,
	distancia double precision NOT NULL,
	duracion integer NOT NULL,
	capacidad integer NOT NULL,
	estado tp_died.estadoTrayecto NOT NULL,
	costo double precision NOT NULL
);

CREATE TABLE tp_died.boleto(
	id_boleto serial PRIMARY KEY,
	correo_cliente varchar(50) NOT NULL,
	nombre_cliente varchar(50) NOT NULL,
	fecha_venta date NOT NULL,
	origen varchar(30) NOT NULL,
	destino varchar(30) NOT NULL,
	camino varchar(300) NOT NULL,
	costo double precision NOT NULL
);