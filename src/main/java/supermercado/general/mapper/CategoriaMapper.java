package supermercado.general.mapper;

import supermercado.general.dto.CategoriaRequestDTO;
import supermercado.general.dto.CategoriaResponseDTO;
import supermercado.general.dto.CategoriaUnicoResponseDTO;
import supermercado.general.model.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDTO dto) {

        Categoria categoria = new Categoria();

        categoria.setDescripcion(dto.descripcion());
        categoria.setUbicacion(dto.ubicacion());

        return categoria;
    }

public static CategoriaResponseDTO toDTO(Categoria categoria) {
    return new CategoriaResponseDTO(
        categoria.getId(),
        categoria.getDescripcion(),
        categoria.getUbicacion()
    );
}

public static CategoriaUnicoResponseDTO toUnicoDTO(Categoria categoria) {
    return new CategoriaUnicoResponseDTO (
        categoria.getId(),
        categoria.getDescripcion(),
        categoria.getUbicacion(),
        categoria.getProductos()
            .stream()
            .map(ProductoMapper::toDTO)
            .toList()
    );
}

}