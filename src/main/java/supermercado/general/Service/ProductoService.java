package supermercado.general.Service;

import java.util.List;

import supermercado.general.dto.ProductoRequestDTO;
import supermercado.general.dto.ProductoResponseDTO;

public interface ProductoService {

    ProductoResponseDTO create(ProductoRequestDTO dto);

    ProductoResponseDTO findById(Long id);

    List<ProductoResponseDTO> getAll();

    ProductoResponseDTO update(Long id, ProductoRequestDTO dto);

    void delete(Long id);

    // Filtro por descripción y rango de precio (todos los params son opcionales)
    List<ProductoResponseDTO> buscar(String descripcion, Double minPrecio, Double maxPrecio);

    // Filtro por rango de existencia (stock)
    List<ProductoResponseDTO> buscarPorStock(Integer min, Integer max);
}