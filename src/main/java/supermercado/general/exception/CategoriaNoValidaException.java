package supermercado.general.exception;

// Lanzada cuando la categoría enviada no pertenece al conjunto de categorías válidas del sistema.
// Se mapea a HTTP 422 en GlobalExceptionHandler.
public class CategoriaNoValidaException extends RuntimeException {

    public CategoriaNoValidaException(String categoria) {
        super("La categoría '" + categoria + "' no está registrada en el sistema");
    }
}
