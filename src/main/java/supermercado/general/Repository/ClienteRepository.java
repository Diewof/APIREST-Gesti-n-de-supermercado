package supermercado.general.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import supermercado.general.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

    // Usada en create() verificar que no existe ya esa identificación
    boolean existsByIdentificacion(Long identificacion);

    // Usada en update(): verificar que la nueva identificación no la tenga OTRO cliente
    boolean existsByIdentificacionAndIdNot(Long identificacion, Long id);
}
