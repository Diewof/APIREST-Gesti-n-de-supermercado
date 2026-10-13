package supermercado.general.dto;

public record ClienteResponseDTO(
    Long id,
    String nombreCompleto,
    String correoElectronico,
    String telefono
) {}
