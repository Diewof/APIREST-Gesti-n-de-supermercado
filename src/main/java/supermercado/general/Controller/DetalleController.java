package supermercado.general.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import supermercado.general.Service.DetalleService;
import supermercado.general.model.Detalle;

@RestController
@RequestMapping("/detalles")
public class DetalleController {

    public final DetalleService detalleService;

    public DetalleController(DetalleService detalleService) {
        this.detalleService = detalleService;
    }

    @GetMapping
    public ResponseEntity<List<Detalle>> listar() {
        List<Detalle> response = detalleService.listar();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle> buscarPorId(@PathVariable Long id) {
        Detalle response = detalleService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

}