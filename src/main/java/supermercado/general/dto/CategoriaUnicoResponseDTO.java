package supermercado.general.dto;

import java.util.List;

public record CategoriaUnicoResponseDTO(
    Long id,
    String descripcion,
    String ubicacion,
    List<ProductoResponseDTO> productos
) {}