package supermercado.general.dto;

import java.time.LocalDate;
import java.util.List;

public record FacturaUnicoResponseDTO(
    Long nroFactura,
    String cliente,
    String descripcion,
    String observacion,
    LocalDate fechaVenta,
    List<DetalleResponseDTO> detalles,
    Integer total
) {}
