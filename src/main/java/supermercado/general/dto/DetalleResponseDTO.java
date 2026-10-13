package supermercado.general.dto;

public record DetalleResponseDTO(
    Long id,
    String producto,
    Integer cantidad,
    Double precioUnitario,
    Integer subTotal
) {}
