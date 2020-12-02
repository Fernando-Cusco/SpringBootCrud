INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (1, 'Braulio Fernando', 'Cusco Mejia', 'admin@admin.com', '2020-11-30', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (2, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (3, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (4, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (5, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (6, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (7, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (8, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (9, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (10, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (11, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (12, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (13, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (14, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (15, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (16, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (17, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (18, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (19, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');
INSERT INTO clientes (id, nombres, apellidos, email, create_at, foto) VALUES (20, 'Ana Carmen', 'Marin Peralta', 'mariap@admin.com', '2020-11-29', '');


INSERT INTO productos (nombre, precio, create_at) VALUES ('Aro de Luz', 25, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Macbook Pro', 550, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Disco Duro', 45, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Teclado Mecanico', 125, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Monitor Asus', 555, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Audifonos', 25, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Iphone 10', 455, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Mouse Gammer', 45, NOW());


INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura #1', '.', 1, NOW());
INSERT INTO detalles_factura (cantidad, factura_id, producto_id) VALUES (2, 1, 1);
INSERT INTO detalles_factura (cantidad, factura_id, producto_id) VALUES (4, 1, 2);
INSERT INTO detalles_factura (cantidad, factura_id, producto_id) VALUES (3, 1, 4);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura #2', '.', 1, NOW());
INSERT INTO detalles_factura (cantidad, factura_id, producto_id) VALUES (3, 2, 1);
INSERT INTO detalles_factura (cantidad, factura_id, producto_id) VALUES (5, 2, 6);
INSERT INTO detalles_factura (cantidad, factura_id, producto_id) VALUES (8, 2, 5);



insert into users(username, password, enable) values ('fernando', '$2a$10$HXM0fFcKNo4vh72LA.QHC.lknuGMTrzkYDH90aOG6ikqeRyvjDuse', 1);
insert into users(username, password, enable) values ('admin', '$2a$10$c1HYDyrWk72YpC.JUYQqle0o5EFdt2avXbBbcKIaApgcmI7a5Ewta', 1);

insert into authorities (user_id, authority) values (1, 'ROLE_USER');
insert into authorities (user_id, authority) values (2, 'ROLE_USER');
insert into authorities (user_id, authority) values (2, 'ROLE_ADMIN');



