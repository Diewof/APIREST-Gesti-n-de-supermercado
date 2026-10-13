package supermercado.general.Service;

import java.util.List;

import supermercado.general.dto.ClienteRequestDTO;
import supermercado.general.dto.ClienteResponseDTO;

public interface ClienteService {

    ClienteResponseDTO create(ClienteRequestDTO dto);

    ClienteResponseDTO findById(Long id);

    List<ClienteResponseDTO> getAll();

    ClienteResponseDTO update(Long id, ClienteRequestDTO dto);

    void delete(Long id);

}