/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function cambiarCantidad(idProducto, delta) {
    let input = document.getElementById("cantidad_" + idProducto);
    let cantidad = parseInt(input.value || "0") + delta;

    if (cantidad < 1)
        cantidad = 1;

    input.value = cantidad;
    actualizarCantidad(idProducto);
}

let timer = null;
function actualizarCantidad(idProducto) {
    clearTimeout(timer);

    timer = setTimeout(() => {
        let input = document.getElementById("cantidad_" + idProducto);
        let cantidad = parseInt(input.value);

        if (isNaN(cantidad) || cantidad < 1)
            return; // permite borrar

        fetch("ActualizarCantidadCarrito", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: `idProducto=${idProducto}&cambio=${cantidad}`
        })
                .then(r => r.json())
                .then(data => {
                    if (data.error) {
                        mostrarMensajeError(data.error);
                    } else {
                        // Actualizar total del producto
                        document.getElementById("totalProd_" + idProducto).innerText =
                                "$" + data.totalProducto;
                        
                        // Actualizar resumen
                        actualizarTotales(data);
                    }
                });
    }, 350);
}

function actualizarTotales(data) {
    const subtotal = parseFloat(data.subtotal);
    const iva = parseFloat(data.iva);
    const total = parseFloat(data.total);

    document.getElementById("subtotal").innerText = "$" + subtotal.toFixed(2);
    document.getElementById("iva").innerText = "$" + iva.toFixed(2);
    document.getElementById("total").innerText = "$" + total.toFixed(2);
}

function mostrarMensajeError(msg) {
    const cont = document.getElementById("mensaje-js");

    cont.innerHTML = `
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${msg}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>`;
}