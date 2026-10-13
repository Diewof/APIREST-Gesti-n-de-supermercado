package supermercado.general.exception;

import java.time.LocalDate;

// Lanzada cuando se intenta crear un producto con una descripción que ya existe en BD.
// Se mapea a HTTP 409 en GlobalExceptionHandler.
public class ProductDuplicateException extends RuntimeException {

    public ProductDuplicateException(String descripcion, LocalDate date) {
        super("El producto identificado con la descripcion " + descripcion
                + " y fecha de última entrada " + date + " ya existe");
    }
}
