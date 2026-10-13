package supermercado.general.Service;

import java.util.List;

import supermercado.general.dto.CategoriaRequestDTO;
import supermercado.general.dto.CategoriaResponseDTO;
import supermercado.general.dto.CategoriaUnicoResponseDTO;

public interface CategoriaService {

    CategoriaResponseDTO create(CategoriaRequestDTO dto);

    CategoriaUnicoResponseDTO findById(Long id);

    List<CategoriaResponseDTO> getAll();

    CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto);

    void delete(Long id);

}