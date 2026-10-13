package supermercado.general.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import supermercado.general.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {

    List<Detalle> findByFacturaNroFactura(Long nroFactura);

    List<Detalle> findByProductoId(Long productoId);

}