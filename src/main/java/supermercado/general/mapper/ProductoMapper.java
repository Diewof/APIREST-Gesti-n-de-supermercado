package supermercado.general.mapper;

import supermercado.general.dto.ProductoRequestDTO;
import supermercado.general.dto.ProductoResponseDTO;
import supermercado.general.model.Categoria;
import supermercado.general.model.Producto;

public class ProductoMapper {

    public static Producto toEntity(ProductoRequestDTO dto, Categoria categoria) {

        Producto producto = new Producto();

        producto.setDescripcion(dto.descripcion());
        producto.setExistencia(dto.existencia());
        producto.setPrecio(dto.precio());
        producto.setUltimoIngreso(dto.ultimoIngreso());
        producto.setDisponible(dto.disponible());
        producto.setCategoria(categoria);

        return producto;
    }

    public static ProductoResponseDTO toDTO(Producto producto) {
        return new ProductoResponseDTO(
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getExistencia(),
            producto.getDisponible(),
            producto.getCategoria() != null ? producto.getCategoria().getDescripcion() : null
        );
    }
}