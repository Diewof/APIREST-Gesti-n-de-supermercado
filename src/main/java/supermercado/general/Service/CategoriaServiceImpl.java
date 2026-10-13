package supermercado.general.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import supermercado.general.Repository.CategoriaRepository;
import supermercado.general.dto.CategoriaRequestDTO;
import supermercado.general.dto.CategoriaResponseDTO;
import supermercado.general.dto.CategoriaUnicoResponseDTO;
import supermercado.general.exception.BussinessException;
import supermercado.general.mapper.CategoriaMapper;
import supermercado.general.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto) {

        // Regla de negocio: no se permiten categorías con descripción duplicada
        if (categoriaRepository.existsByDescripcionIgnoreCase(dto.descripcion())) {
            throw new BussinessException(
                "Ya existe una categoría con la descripción '"
                + dto.descripcion() + "'");
        }

        Categoria categoria = CategoriaMapper.toEntity(dto);

        Categoria saved = categoriaRepository.save(categoria);

        return CategoriaMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaUnicoResponseDTO findById(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() ->
                new BussinessException("Categoria con id " + id + " no encontrada"));

        return CategoriaMapper.toUnicoDTO(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> getAll() {

        return categoriaRepository.findAll()
            .stream()
            .map(CategoriaMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() ->
                new BussinessException("Categoria con id " + id + " no encontrada"));

        // Regla de negocio: no se puede poner una descripción que ya usa OTRA categoría
        if (categoriaRepository.existsByDescripcionIgnoreCaseAndIdNot(
                dto.descripcion(), id)) {
            throw new BussinessException(
                "Ya existe otra categoría con la descripción '"
                + dto.descripcion() + "'");
        }

        categoria.setDescripcion(dto.descripcion());
        categoria.setUbicacion(dto.ubicacion());

        Categoria updated = categoriaRepository.save(categoria);

        return CategoriaMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() ->
                new BussinessException("Categoria con id " + id + " no encontrada"));

        // Regla de negocio: no se puede eliminar una categoría que tiene productos
        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
            throw new BussinessException(
                "No se puede eliminar la categoría '"
                + categoria.getDescripcion()
                + "' porque tiene " + categoria.getProductos().size()
                + " producto(s) asociado(s)");
        }

        categoriaRepository.delete(categoria);
    }
}