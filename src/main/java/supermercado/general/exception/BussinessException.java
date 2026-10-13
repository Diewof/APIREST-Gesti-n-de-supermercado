package supermercado.general.exception;

// Excepción para violaciones de reglas de negocio genéricas.
// Unchecked (hereda de RuntimeException): no requiere declararse en las firmas de los métodos.
// Se mapea a HTTP 422 en GlobalExceptionHandler.
public class BussinessException extends RuntimeException {

    public BussinessException(String message) {
        super(message);
    }
}
