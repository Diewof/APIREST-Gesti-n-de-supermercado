package supermercado.general.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import supermercado.general.Repository.DetalleRepository;
import supermercado.general.exception.BussinessException;
import supermercado.general.model.Detalle;

@Service
public class DetalleServiceImpl implements DetalleService {

    private final DetalleRepository detalleRepository;

    public DetalleServiceImpl(DetalleRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<Detalle> listar() {
        return detalleRepository.findAll();
    }

    @Override
    public Detalle buscarPorId(Long id) {
        return detalleRepository.findById(id)
                .orElseThrow(() ->
                    new BussinessException("Detalle no encontrado"));
    }

}