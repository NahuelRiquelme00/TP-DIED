--tp_died.estacion (id_estacion, nombre, horarioApertura, horarioCierre, estadoEstacion) 
INSERT INTO tp_died.estacion
VALUES (DEFAULT,'A','08:00','16:00','OPERATIVA'),
	   (DEFAULT,'B','08:00','16:00','OPERATIVA'),
	   (DEFAULT,'C','08:00','16:00','OPERATIVA'),
	   (DEFAULT,'D','08:00','16:00','OPERATIVA'),
	   (DEFAULT,'E','08:00','16:00','OPERATIVA'),
	   (DEFAULT,'F','09:00','21:00','OPERATIVA'),
	   (DEFAULT,'G','09:00','21:00','OPERATIVA'),
	   (DEFAULT,'H','09:00','21:00','OPERATIVA'),
	   (DEFAULT,'X','10:00','22:00','OPERATIVA'),
	   (DEFAULT,'Y','10:00','22:00','OPERATIVA'),
	   (DEFAULT,'Z','10:00','22:00','OPERATIVA');

--tp_died.mantenimiento (id_mantenimiento, id_estacion, fechaInicio, fechaFin, Observaciones)  
INSERT INTO tp_died.mantenimiento
VALUES (DEFAULT,1,'2021-03-21','2021-03-28','Mantenimiento de puertas'),
		(DEFAULT,2,'2021-01-02','2021-01-16','Mantenimiento de ventanas'),
		(DEFAULT,3,'2021-05-22','2021-05-23','Mantenimiento de ventanas'),
		(DEFAULT,4,'2021-04-13','2021-04-16','Mantenimiento de ventanas'),
		(DEFAULT,5,'2021-06-08','2021-06-12','Mantenimiento de ventanas'),
		(DEFAULT,6,'2021-03-14','2021-03-21','Mantenimiento de puertas'),
		(DEFAULT,7,'2021-05-05','2021-05-05','Mantenimiento de puertas'),
		(DEFAULT,8,'2021-01-13','2021-01-15','Mantenimiento de puertas'),
		(DEFAULT,9,'2020-12-08','2020-12-22','Mantenimiento de motor'),
		(DEFAULT,10,'2021-07-01','2021-07-05','Mantenimiento de motor'),
		(DEFAULT,11,'2021-06-01','2021-06-02','Mantenimiento de motor'),
		(DEFAULT,1,'2021-07-23','2021-07-30','Mantenimiento de motor'),
		(DEFAULT,2,'2021-04-24','2021-04-27','Mantenimiento de motor'),
		(DEFAULT,3,'2021-06-15','2021-06-15','Mantenimiento de motor'),
		(DEFAULT,4,'2021-07-23','2021-07-25','Mantenimiento de motor');
	   

--LineaDeTransporte (id_linea, nombre, color, estadoLinea)
INSERT INTO tp_died.linea_de_transporte
VALUES (DEFAULT,'Linea C','ROJA','ACTIVA'),
	   (DEFAULT,'Linea 114','NARANJA','ACTIVA'),
	   (DEFAULT,'Linea 8','AMARILLA','ACTIVA'),
	   (DEFAULT,'Linea 4','AZUL','ACTIVA'),
	   (DEFAULT,'Metro A1','VERDE_CLARA','ACTIVA'),
	   (DEFAULT,'Linea C','VERDE_OSCURA','ACTIVA');

--tp_died.trayecto(id_trayecto, id_linea, id_estacion_origen, id_estacion_destino, distancia, duracion, capacidad, estado, costo)
INSERT INTO tp_died.trayecto 
VALUES	(DEFAULT,5,1,2,5.0,6,40,'ACTIVO',40),
		(DEFAULT,5,2,4,6.5,8,40,'ACTIVO',50),
		(DEFAULT,5,4,5,2.4,6,40,'ACTIVO',40),
		(DEFAULT,2,1,3,4.0,12,100,'ACTIVO',20),
		(DEFAULT,2,3,6,3.2,10,100,'ACTIVO',18),
		(DEFAULT,2,6,7,2.0,7,100,'ACTIVO',18),
		(DEFAULT,1,4,6,8.0,40,35,'ACTIVO',60),
		(DEFAULT,1,6,9,9.2,50,35,'ACTIVO',65),
		(DEFAULT,1,9,10,4.0,25,35,'ACTIVO',50),
		(DEFAULT,1,10,11,1.8,15,35,'ACTIVO',23),
		(DEFAULT,4,9,3,1.9,3,50,'ACTIVO',12),
		(DEFAULT,4,3,4,3.8,5,50,'ACTIVO',15),
		(DEFAULT,4,4,5,2.4,4,50,'ACTIVO',13),
		(DEFAULT,4,5,8,3.7,5,50,'ACTIVO',15),
		(DEFAULT,3,7,5,1.9,15,25,'ACTIVO',40),
		(DEFAULT,3,5,8,3.7,30,25,'ACTIVO',58),
		(DEFAULT,6,1,9,2.6,5,40,'ACTIVO',35),
		(DEFAULT,6,9,10,4.0,9,40,'ACTIVO',50),
		(DEFAULT,6,10,11,1.8,5,40,'ACTIVO',28);
		

--tp_died.boleto(id_boleto, correo_cliente, nombre_cliente, fecha_venta, origen, destino, camino, costo)
INSERT INTO tp_died.boleto
VALUES (DEFAULT,'nahuelriquelme00@gmail.com','Nahuel Riquelme','2020-09-05','Estacion XY','Estacion ZW','Camino',150);