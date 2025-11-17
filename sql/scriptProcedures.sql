# --------------- PRODUCTOS ---------------
# Obtener todos los productos
DELIMITER $$
CREATE PROCEDURE getAllProductos() 
BEGIN
	SELECT * FROM productos ORDER BY numero;
END$$
DELIMITER ;

# Editar un producto
DELIMITER $$
CREATE PROCEDURE editarProducto(
    IN p_id INT,
    IN p_numero INT,
    IN p_nombre VARCHAR(50),
    IN p_descripcion VARCHAR(100),
    IN p_precio FLOAT,
    IN p_stock INT,
    IN p_img VARCHAR(255),
    IN p_tipo ENUM('OBLEAS', 'MAICITOS', 'CHIPS', 'OTROS')
)
BEGIN
    UPDATE productos
    SET
        numero = p_numero,
        nombre = p_nombre,
        descripcion = p_descripcion,
        precio = p_precio,
        stock = p_stock,
        img = p_img,
        tipo = p_tipo
    WHERE id = p_id;
END $$
DELIMITER ;

# Insertar un producto
DELIMITER $$
CREATE PROCEDURE insertarProducto(
    IN p_numero INT,
    IN p_nombre VARCHAR(50),
    IN p_descripcion VARCHAR(100),
    IN p_precio FLOAT,
    IN p_stock INT,
    IN p_img VARCHAR(255),
    IN p_tipo ENUM('OBLEAS', 'MAICITOS', 'CHIPS', 'OTROS')
)
BEGIN
	INSERT INTO productos(numero, nombre, descripcion, precio, stock, img, tipo) VALUES(
		p_numero,
        p_nombre,
        p_descripcion,
        p_precio,
        p_stock,
        p_img,
        p_tipo
    );
END$$
DELIMITER ;

# Eliminar un producto
DELIMITER $$
CREATE PROCEDURE eliminarProducto(
	IN p_id INT
)
BEGIN
	DELETE FROM productos WHERE id = p_id;
END $$
DELIMITER ;

# Obtener un producto específico
DELIMITER $$
CREATE PROCEDURE getProducto(
	IN p_id INT
)
BEGIN
	SELECT * FROM productos WHERE id = p_id;
END $$
DELIMITER ;

# --------------- USUARIOS ---------------
# Obtener todos los usuarios
DELIMITER $$
CREATE PROCEDURE getAllUsuarios() 
BEGIN
	SELECT * FROM usuarios;
END$$
DELIMITER ;

# Editar un usuario
DELIMITER $$
CREATE PROCEDURE editarUsuario(
    IN u_id INT,
    IN u_numero INT,
    IN u_nombre VARCHAR(50),
    IN u_apellidoPaterno VARCHAR(50),
    IN u_apellidoMaterno VARCHAR(50),
    IN u_email VARCHAR(50),
    IN u_rol ENUM('ADMIN', 'CLIENTE'),
    IN u_contrasenia VARCHAR(20)
)
BEGIN
    UPDATE usuarios
    SET
        numero = u_numero,
        nombre = u_nombre,
        apellidoPaterno = u_apellidoPaterno,
        apellidoMaterno = u_apellidoMaterno,
        email = u_email,
        rol = u_rol,
        contrasenia = u_contrasenia
    WHERE id = u_id;
END $$
DELIMITER ;

# Insertar un usuario
DELIMITER $$
CREATE PROCEDURE insertarUsuario(
    IN u_numero INT,
    IN u_nombre VARCHAR(50),
    IN u_apellidoPaterno VARCHAR(50),
    IN u_apellidoMaterno VARCHAR(50),
    IN u_email VARCHAR(50),
    IN u_rol ENUM('ADMIN', 'CLIENTE'),
    IN u_contrasenia VARCHAR(20)
)
BEGIN
	INSERT INTO usuarios(numero, nombre, apellidoPaterno, apellidoMaterno, email, rol, contrasenia) VALUES(
		u_numero,
        u_nombre,
        u_apellidoPaterno,
        u_apellidoMaterno,
        u_email,
        u_rol,
        u_contrasenia
    );
END$$
DELIMITER ;

# Eliminar un usuario
DELIMITER $$
CREATE PROCEDURE eliminarUsuario(
	IN u_id INT
)
BEGIN
	DELETE FROM usuarios WHERE id = u_id;
END $$
DELIMITER ;