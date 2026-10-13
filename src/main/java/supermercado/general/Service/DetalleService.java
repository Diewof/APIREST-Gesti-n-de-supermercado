package supermercado.general.Service;

import java.util.List;

import supermercado.general.model.Detalle;

public interface DetalleService {

    List<Detalle> listar();

    Detalle buscarPorId(Long id);

}