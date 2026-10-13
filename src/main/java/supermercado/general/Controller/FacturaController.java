package supermercado.general.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import supermercado.general.Service.FacturaService;
import supermercado.general.dto.FacturaRequestDTO;
import supermercado.general.dto.FacturaResponseDTO;
import supermercado.general.dto.FacturaUnicoResponseDTO;

@RestController
@RequestMapping("/facturas")
@Validated
public class FacturaController {

    public final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping
    public ResponseEntity<FacturaUnicoResponseDTO> create(
            @Valid @RequestBody FacturaRequestDTO dto) {
        FacturaUnicoResponseDTO response = facturaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{nroFactura}")
    public ResponseEntity<FacturaUnicoResponseDTO> findById(@PathVariable Long nroFactura) {
        FacturaUnicoResponseDTO response = facturaService.findById(nroFactura);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponseDTO>> getAll() {
        List<FacturaResponseDTO> response = facturaService.getAll();
        return ResponseEntity.ok(response);
    }

    // Filtro: facturas de un cliente específico
    // Ejemplo: GET /facturas/cliente/3
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<FacturaUnicoResponseDTO>> findByCliente(
            @PathVariable Long clienteId) {
        List<FacturaUnicoResponseDTO> response = facturaService.findByClienteId(clienteId);
        return ResponseEntity.ok(response);
    }

    // Filtro: facturas por fecha de venta
    // Ejemplo: GET /facturas/fecha?fecha=2025-06-15
    @GetMapping("/fecha")
    public ResponseEntity<List<FacturaUnicoResponseDTO>> findByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<FacturaUnicoResponseDTO> response = facturaService.findByFecha(fecha);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{nroFactura}")
    public ResponseEntity<Void> delete(@PathVariable Long nroFactura) {
        facturaService.delete(nroFactura);
        return ResponseEntity.noContent().build();
    }
}