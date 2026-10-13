package supermercado.general.mapper;

import supermercado.general.dto.ClienteRequestDTO;
import supermercado.general.dto.ClienteResponseDTO;
import supermercado.general.model.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();

        cliente.setIdentificacion(dto.identificacion());
        cliente.setTipoIdentificacion(dto.tipoIdentificacion());
        cliente.setNombreCompleto(dto.nombreCompleto());
        cliente.setDireccion(dto.direccion());
        cliente.setTelefono(dto.telefono());
        cliente.setCorreoElectronico(dto.correoElectronico());
        cliente.setCapacidadCredito(dto.capacidadCredito());
        cliente.setFechaIngreso(dto.fechaIngreso());

        return cliente;
    }

public static ClienteResponseDTO toDTO(Cliente cliente) {
    return new ClienteResponseDTO(
        cliente.getId(),
        cliente.getNombreCompleto(),
        cliente.getCorreoElectronico(),
        cliente.getTelefono()
    );
}

}