package supermercado.general.exception;

// Lanzada cuando se busca, actualiza o elimina un producto con un ID que no existe.
// Se mapea a HTTP 404 en GlobalExceptionHandler.
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Producto con ID " + id + " no encontrada");
    }
}
