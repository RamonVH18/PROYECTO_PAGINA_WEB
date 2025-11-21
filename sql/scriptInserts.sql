INSERT INTO productos(numero, nombre, descripcion, precio, stock, img, tipo) VALUES(1, 'Coco obleas',
 'Deliciosas obleas rellenas de coco, bajas en calorías.',
 50.00,
 100,
 'img/obleas1.jpeg',
 'OBLEAS'),

(2, 'Chips de camote adobado',
 'Chips de camote deshidratado con un toque de adobo. El snack crujiente perfecto.',
 35.00,
 100,
 'img/chips1.jpeg',
 'CHIPS'),

(3, 'Maicitos salsa negra',
 'Maíz inflado, sin conservadores ni grasas añadidas.',
 30.00,
 100,
 'img/maicitos1.jpeg',
 'MAICITOS'),

(4, 'Choco obleas',
 'Obleas de amaranto rellenas de chocolate. Con delicioso sabor y saludables.',
 50.00,
 100,
 'img/obleas2.jpeg',
 'OBLEAS'),

(5, 'Chips de taro adobado',
 'Chips de taro adobado, naturalmente dulces y deliciosamente crujientes.',
 35.00,
 100,
 'img/chips2.jpeg',
 'CHIPS'),

(6, 'Nueces con chocolate',
 'Nueces cubiertas con chocolate amargo, sin azúcar.',
 55.00,
 100,
 'img/otros1.jpeg',
 'OTROS');
 
 INSERT INTO usuarios(numero, nombre, apellidoPaterno, apellidoMaterno, email, rol, contrasenia) VALUES(
	1,
    "Isabel",
    "Valenzuela",
    "Rocha",
    "nutriyummy1@gmail.com",
    "ADMIN",
    "$2a$10$.IqzgkDym09JoB0qPUXb3eFsD8wiklFS9hUaTNuiYwpRPwBR7Z6LC" #admin123
 );