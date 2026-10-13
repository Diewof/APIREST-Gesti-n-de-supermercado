package supermercado.general.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import supermercado.general.Service.ProductoService;
import supermercado.general.dto.ProductoRequestDTO;
import supermercado.general.dto.ProductoResponseDTO;

@RestController
@RequestMapping("/productos")
@Validated
public class ProductoController {

    public final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> create(
            @Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO response = productoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> findById(@PathVariable Long id) {
        ProductoResponseDTO response = productoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAll() {
        List<ProductoResponseDTO> response = productoService.getAll();
        return ResponseEntity.ok(response);
    }

    // Filtro por descripción y rango de precio (todos los params son opcionales)
    // Ejemplo: GET /productos/buscar?descripcion=leche&minPrecio=1000&maxPrecio=5000
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> buscar(
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Double minPrecio,
            @RequestParam(required = false) Double maxPrecio) {
        List<ProductoResponseDTO> response = productoService.buscar(descripcion, minPrecio, maxPrecio);
        return ResponseEntity.ok(response);
    }

    // Filtro por rango de existencia (stock)
    // Ejemplo: GET /productos/stock?min=0&max=10
    @GetMapping("/stock")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorStock(
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {
        List<ProductoResponseDTO> response = productoService.buscarPorStock(min, max);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        ProductoResponseDTO response = productoService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}