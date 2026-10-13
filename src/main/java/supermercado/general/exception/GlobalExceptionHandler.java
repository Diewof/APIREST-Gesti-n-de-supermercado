package supermercado.general.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Intercepta todas las excepciones no manejadas en los controladores y las convierte
// en respuestas HTTP con el código de estado apropiado.
// Para agregar una nueva excepción: crear la clase en exception/ y añadir un @ExceptionHandler aquí.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validaciones de formato (@NotBlank, @Min, @Size, etc.) → HTTP 400
    // Retorna un mapa con cada campo fallido y su mensaje: { "campo": "mensaje" }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors); // HTTP 400
    }

    // Descripción de producto duplicada → HTTP 409
    @ExceptionHandler(ProductDuplicateException.class)
    public ResponseEntity<Map<String, String>> handleProductDuplicateException(ProductDuplicateException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // HTTP 409
    }

    // Categoría no registrada en el sistema → HTTP 422
    @ExceptionHandler(CategoriaNoValidaException.class)
    public ResponseEntity<Map<String, String>> handleCategoriaNoValida(CategoriaNoValidaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error); // HTTP 422
    }

    // Producto no encontrado por ID → HTTP 404
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> productNotFound(ProductNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // HTTP 404
    }

    // Reglas de negocio genéricas (precio 0, disponible sin stock, rango inválido, etc.) → HTTP 422
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<Map<String, String>> handleBussinessException(BussinessException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error); // HTTP 422
    }

    // Reducción de precio mayor al 70% → HTTP 422
    @ExceptionHandler(ProductPriceReduction.class)
    public ResponseEntity<Map<String, String>> handleProductPriceReduction(ProductPriceReduction ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error); // HTTP 422
    }

}
