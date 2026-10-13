package supermercado.general.exception;

// Lanzada cuando el nuevo precio en un PUT es menor al 30% del precio actual.
// Protege contra errores de digitación que reducirían el precio accidentalmente.
// Se mapea a HTTP 422 en GlobalExceptionHandler.
public class ProductPriceReduction extends RuntimeException {

    public ProductPriceReduction(Double precio, Double precio2) {
        super("El precio no se puede reducir mas de 30% y el precio que se indico fue:  "
                + precio + " el precio minimo permitido es: " + (precio2 * 0.71));
    }
}
