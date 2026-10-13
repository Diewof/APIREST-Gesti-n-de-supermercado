package supermercado.general.dto;

public record ProductoResponseDTO(
    String descripcion,
    Double precio,
    Integer existencia,
    boolean disponible,
    String categoria
) {}
