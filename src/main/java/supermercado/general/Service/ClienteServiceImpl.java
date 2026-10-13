package supermercado.general.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import supermercado.general.Repository.ClienteRepository;
import supermercado.general.dto.ClienteRequestDTO;
import supermercado.general.dto.ClienteResponseDTO;
import supermercado.general.exception.BussinessException;
import supermercado.general.mapper.ClienteMapper;
import supermercado.general.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteResponseDTO create(ClienteRequestDTO dto) {

        // Regla de negocio: no se permiten dos clientes con la misma identificación
        if (clienteRepository.existsByIdentificacion(dto.identificacion())) {
            throw new BussinessException(
                "Ya existe un cliente con identificación "
                + dto.identificacion());
        }

        Cliente cliente = ClienteMapper.toEntity(dto);

        Cliente saved = clienteRepository.save(cliente);

        return ClienteMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {

        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() ->
                new BussinessException("Cliente no encontrado"));

        return ClienteMapper.toDTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> getAll() {

        return clienteRepository.findAll()
            .stream()
            .map(ClienteMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() ->
                new BussinessException("Cliente no encontrado"));

        // Regla de negocio: si se cambia la identificación, verificar que
        // no la tenga ya otro cliente distinto
        if (!cliente.getIdentificacion().equals(dto.identificacion())
                && clienteRepository.existsByIdentificacionAndIdNot(
                        dto.identificacion(), id)) {
            throw new BussinessException(
                "La identificación " + dto.identificacion()
                + " ya está registrada en otro cliente");
        }

        cliente.setIdentificacion(dto.identificacion());
        cliente.setTipoIdentificacion(dto.tipoIdentificacion());
        cliente.setNombreCompleto(dto.nombreCompleto());
        cliente.setDireccion(dto.direccion());
        cliente.setTelefono(dto.telefono());
        cliente.setCorreoElectronico(dto.correoElectronico());
        cliente.setCapacidadCredito(dto.capacidadCredito());
        cliente.setFechaIngreso(dto.fechaIngreso());

        Cliente updated = clienteRepository.save(cliente);

        return ClienteMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() ->
                new BussinessException("Cliente no encontrado"));

        clienteRepository.delete(cliente);
    }
}
