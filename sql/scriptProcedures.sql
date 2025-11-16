# Obtener todos los productos
DELIMITER $$
CREATE PROCEDURE getAllProductos() 
BEGIN
	SELECT * FROM productos;
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