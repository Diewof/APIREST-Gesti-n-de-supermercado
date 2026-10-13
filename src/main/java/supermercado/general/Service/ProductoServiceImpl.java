package supermercado.general.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import supermercado.general.Repository.CategoriaRepository;
import supermercado.general.Repository.ProductRepository;
import supermercado.general.dto.ProductoRequestDTO;
import supermercado.general.dto.ProductoResponseDTO;
import supermercado.general.exception.BussinessException;
import supermercado.general.exception.ProductDuplicateException;
import supermercado.general.exception.ProductNotFoundException;
import supermercado.general.exception.ProductPriceReduction;
import supermercado.general.mapper.ProductoMapper;
import supermercado.general.model.Categoria;
import supermercado.general.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(
            ProductRepository productoRepository,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public ProductoResponseDTO create(ProductoRequestDTO dto) {

        // Regla de negocio 1: no permitir descripción duplicada (case-insensitive)
        productoRepository.findByDescripcionIgnoreCase(dto.descripcion())
            .ifPresent(existing -> {
                throw new ProductDuplicateException(
                    dto.descripcion(), existing.getUltimoIngreso());
            });

        // Regla de negocio 2: no se puede marcar disponible si la existencia es 0
        if (dto.disponible() && dto.existencia() == 0) {
            throw new BussinessException(
                "No se puede crear el producto como disponible si la existencia es 0");
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
            .orElseThrow(() ->
                new BussinessException("Categoria no encontrada"));

        Producto producto = ProductoMapper.toEntity(dto, categoria);

        Producto saved = productoRepository.save(producto);

        return ProductoMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO findById(Long id) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductoMapper.toDTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> getAll() {

        return productoRepository.findAll()
            .stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional
    public ProductoResponseDTO update(Long id, ProductoRequestDTO dto) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        // Regla de negocio 3: reducción de precio no puede superar el 30%
        // (nuevo precio < 70% del precio actual → rechazar)
        if (dto.precio() < producto.getPrecio() * 0.70) {
            throw new ProductPriceReduction(dto.precio(), producto.getPrecio());
        }

        // Regla de negocio 4: no se puede marcar disponible si la existencia es 0
        if (dto.disponible() && dto.existencia() == 0) {
            throw new BussinessException(
                "No se puede marcar el producto como disponible si la existencia es 0");
        }

        // Regla de negocio 5: si hay descripción duplicada en OTRO producto, rechazar
        productoRepository.findByDescripcionIgnoreCase(dto.descripcion())
            .ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new ProductDuplicateException(
                        dto.descripcion(), existing.getUltimoIngreso());
                }
            });

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
            .orElseThrow(() ->
                new BussinessException("Categoria no encontrada"));

        producto.setDescripcion(dto.descripcion());
        producto.setExistencia(dto.existencia());
        producto.setPrecio(dto.precio());
        producto.setUltimoIngreso(dto.ultimoIngreso() != null
            ? dto.ultimoIngreso() : LocalDate.now());
        producto.setDisponible(dto.disponible());
        producto.setCategoria(categoria);

        Producto updated = productoRepository.save(producto);

        return ProductoMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        productoRepository.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> buscar(
            String descripcion, Double minPrecio, Double maxPrecio) {

        return productoRepository.search(descripcion, minPrecio, maxPrecio)
            .stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> buscarPorStock(Integer min, Integer max) {

        return productoRepository.buscarPorExistencia(min, max)
            .stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }
}