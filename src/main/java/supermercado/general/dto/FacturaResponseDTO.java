package supermercado.general.dto;

import java.time.LocalDate;

public record FacturaResponseDTO(
    Long nroFactura,
    String cliente,
    String descripcion,
    String observacion,
    LocalDate fechaVenta,
    Integer total
) {}