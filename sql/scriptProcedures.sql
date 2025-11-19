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
    IN p_nombre VARCHAR(50),
    IN p_descripcion VARCHAR(100),
    IN p_precio FLOAT,
    IN p_stock INT,
    IN p_img VARCHAR(255),
    IN p_tipo ENUM('OBLEAS', 'MAICITOS', 'CHIPS', 'OTROS'),
    OUT p_numero_generado INT
)
BEGIN
    DECLARE nuevoNumero INT;

    -- Obtener el siguiente número disponible
    SELECT IFNULL(MAX(numero), 0) + 1 INTO nuevoNumero
    FROM productos;

    -- Insertar el producto
    INSERT INTO productos(numero, nombre, descripcion, precio, stock, img, tipo) VALUES(
		nuevoNumero, 
        p_nombre, 
        p_descripcion, 
        p_precio, 
        p_stock, 
        p_img, 
        p_tipo
	);

    -- Devolver el número generado
    SET p_numero_generado = nuevoNumero;
END $$
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

# Obtener productos de un tipo específico
DELIMITER $$
CREATE PROCEDURE getProductosPorTipo(
	IN p_tipo ENUM("OBLEAS", "MAICITOS", "CHIPS", "OTROS")
)
BEGIN
	SELECT * FROM productos WHERE tipo = p_tipo;
END $$
DELIMITER ;

# Validar si existe un producto con un numero específico
DELIMITER $$
CREATE FUNCTION existeProductoNumero(p_numero INT)
RETURNS TINYINT
DETERMINISTIC
BEGIN
    DECLARE existe TINYINT DEFAULT 0;

    SELECT COUNT(*) INTO existe
    FROM productos
    WHERE numero = p_numero;

    IF existe > 0 THEN
        RETURN 1; -- Sí existe
    ELSE
        RETURN 0; -- No existe
    END IF;
END$$
DELIMITER ;

# Obtener los productos mejores vendidos
DELIMITER $$
CREATE PROCEDURE getMejoresVendidos()
BEGIN
	SELECT
		p.id,
        p.nombre, 
        p.descripcion,
        p.img
    FROM productos AS p
    INNER JOIN detalles_ventas AS d
        ON p.id = d.idProducto
    GROUP BY p.id, p.nombre, p.descripcion
    ORDER BY SUM(d.cantidad) DESC
    LIMIT 3;
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

# Obtener un usuario específico
DELIMITER $$
CREATE PROCEDURE getUsuario(
	IN u_id INT
) 
BEGIN
	SELECT * FROM usuarios WHERE id = u_id;
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
    IN u_nombre VARCHAR(50),
    IN u_apellidoPaterno VARCHAR(50),
    IN u_apellidoMaterno VARCHAR(50),
    IN u_email VARCHAR(50),
    IN u_rol ENUM('ADMIN', 'CLIENTE'),
    IN u_contrasenia VARCHAR(20),
    OUT u_numero_generado INT
)
BEGIN
	DECLARE nuevoNumero INT;

    -- Obtener el siguiente número disponible
    SELECT IFNULL(MAX(numero), 0) + 1 INTO nuevoNumero
    FROM usuarios;

	INSERT INTO usuarios(numero, nombre, apellidoPaterno, apellidoMaterno, email, rol, contrasenia) VALUES(
		nuevoNumero,
        u_nombre,
        u_apellidoPaterno,
        u_apellidoMaterno,
        u_email,
        u_rol,
        u_contrasenia
    );
    
    -- Devolver el número generado
    SET u_numero_generado = nuevoNumero;
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

# Validar si existe un usuario con un numero específico
DELIMITER $$
CREATE FUNCTION existeUsuarioNumero(u_numero INT)
RETURNS TINYINT
DETERMINISTIC
BEGIN
    DECLARE existe TINYINT DEFAULT 0;

    SELECT COUNT(*) INTO existe
    FROM usuarios
    WHERE numero = u_numero;

    IF existe > 0 THEN
        RETURN 1; -- Sí existe
    ELSE
        RETURN 0; -- No existe
    END IF;
END$$
DELIMITER ;

# Validar si ya existe un email
DELIMITER $$
CREATE FUNCTION existeEmailUsuario(u_email INT)
RETURNS TINYINT
DETERMINISTIC
BEGIN
    DECLARE existe TINYINT DEFAULT 0;

    SELECT COUNT(*) INTO existe
    FROM usuarios
    WHERE email = u_email;

    IF existe > 0 THEN
        RETURN 1; -- Sí existe
    ELSE
        RETURN 0; -- No existe
    END IF;
END$$
DELIMITER ;

# Verificar si hay más de un usuario ADMIN en el sistema
DELIMITER $$
CREATE FUNCTION hayMasAdmins()
RETURNS TINYINT
DETERMINISTIC
BEGIN
    DECLARE total TINYINT DEFAULT 0;

    SELECT COUNT(*) INTO total
    FROM usuarios
    WHERE rol = "ADMIN";

    IF total > 1 THEN
        RETURN 1; -- Sí hay más de 1
    ELSE
        RETURN 0; -- Es el único admin que queda
    END IF;
END$$
DELIMITER ;

# --------------- VENTAS ---------------
# Obtener todas las ventas junto con sus detalles
DELIMITER $$
CREATE PROCEDURE getAllVentas() 
BEGIN
    SELECT 
        v.id,
        v.folio,
        v.fechaHora,
        v.idUsuario,

        d.id AS idDetalle,
        d.idProducto,
        d.cantidad,
        d.precio AS precioUnitario,
        d.iva,

        -- Total del detalle: subtotal + IVA (ambos son dinero)
        (d.cantidad * d.precio) + d.iva AS totalDetalle,

        -- Total de toda la venta, sumando todos los detalles
        (
            SELECT SUM((d2.cantidad * d2.precio) + d2.iva)
            FROM detalles_ventas d2
            WHERE d2.idVenta = v.id
        ) AS totalVenta

    FROM ventas AS v
    INNER JOIN detalles_ventas AS d
        ON v.id = d.idVenta
    ORDER BY v.id, d.id;
END$$
DELIMITER ;
