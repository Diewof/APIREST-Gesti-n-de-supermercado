package supermercado.general.Service;

import java.time.LocalDate;
import java.util.List;

import supermercado.general.dto.FacturaRequestDTO;
import supermercado.general.dto.FacturaResponseDTO;
import supermercado.general.dto.FacturaUnicoResponseDTO;

public interface FacturaService {

    FacturaUnicoResponseDTO create(FacturaRequestDTO dto);

    FacturaUnicoResponseDTO findById(Long nroFactura);

    List<FacturaResponseDTO> getAll();

    void delete(Long nroFactura);

    // Filtro: todas las facturas de un cliente
    List<FacturaUnicoResponseDTO> findByClienteId(Long clienteId);

    // Filtro: todas las facturas de una fecha específica
    List<FacturaUnicoResponseDTO> findByFecha(LocalDate fecha);
}
