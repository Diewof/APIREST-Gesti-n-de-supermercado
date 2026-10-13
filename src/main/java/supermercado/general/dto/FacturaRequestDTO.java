package supermercado.general.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FacturaRequestDTO(

    @NotNull(message = "El ID del cliente es obligatorio")
    Long clienteId,

    @Size(max = 100, message = "La descripción no puede superar los 100 caracteres")
    String descripcion,

    @Size(max = 255, message = "La observación no puede superar los 255 caracteres")
    String observacion,

    @NotEmpty(message = "La factura debe tener al menos un detalle")
    @Valid
    List<DetalleRequestDTO> detalles

) {}
