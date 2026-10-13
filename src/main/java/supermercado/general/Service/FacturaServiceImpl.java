package supermercado.general.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import supermercado.general.Repository.ClienteRepository;
import supermercado.general.Repository.DetalleRepository;
import supermercado.general.Repository.FacturaRepository;
import supermercado.general.Repository.ProductRepository;
import supermercado.general.dto.DetalleRequestDTO;
import supermercado.general.dto.FacturaRequestDTO;
import supermercado.general.dto.FacturaResponseDTO;
import supermercado.general.dto.FacturaUnicoResponseDTO;
import supermercado.general.exception.BussinessException;
import supermercado.general.mapper.FacturaMapper;
import supermercado.general.model.Cliente;
import supermercado.general.model.Detalle;
import supermercado.general.model.Factura;
import supermercado.general.model.Producto;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductRepository productoRepository;
    private final DetalleRepository detalleRepository;

    public FacturaServiceImpl(
            FacturaRepository facturaRepository,
            ClienteRepository clienteRepository,
            ProductRepository productoRepository,
            DetalleRepository detalleRepository) {

        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.detalleRepository = detalleRepository;
    }

    @Override
    @Transactional
    public FacturaUnicoResponseDTO create(FacturaRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId())
            .orElseThrow(() ->
                new BussinessException("Cliente no encontrado"));

        // Regla de negocio 1: no se permite el mismo producto dos veces en la factura
        Set<Long> productosVistos = new HashSet<>();
        for (DetalleRequestDTO det : dto.detalles()) {
            if (!productosVistos.add(det.productoId())) {
                throw new BussinessException(
                    "El producto con ID " + det.productoId()
                    + " aparece más de una vez en la factura. "
                    + "Consolide las cantidades en un único detalle");
            }
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setDescripcion(dto.descripcion());
        factura.setObservacion(dto.observacion());
        factura.setFechaVenta(LocalDate.now());

        Factura savedFactura = facturaRepository.save(factura);

        List<Detalle> detallesGuardados = new ArrayList<>();

        for (DetalleRequestDTO detalleDTO : dto.detalles()) {

            Producto producto = productoRepository
                .findById(detalleDTO.productoId())
                .orElseThrow(() ->
                    new BussinessException("Producto con ID "
                        + detalleDTO.productoId() + " no encontrado"));

            // Regla de negocio 2: solo se pueden facturar productos disponibles
            if (!producto.getDisponible()) {
                throw new BussinessException(
                    "El producto '" + producto.getDescripcion()
                    + "' no está disponible para la venta");
            }

            // Regla de negocio 3 (ya existía): stock suficiente
            if (producto.getExistencia() < detalleDTO.cantidad()) {
                throw new BussinessException(
                    "No hay stock suficiente para '"
                    + producto.getDescripcion()
                    + "'. Disponible: " + producto.getExistencia()
                    + ", solicitado: " + detalleDTO.cantidad());
            }

            producto.setExistencia(
                producto.getExistencia() - detalleDTO.cantidad());

            // Si al descontar el stock queda en 0, marcar como no disponible
            if (producto.getExistencia() == 0) {
                producto.setDisponible(false);
            }

            productoRepository.save(producto);

            Detalle detalle = new Detalle();
            detalle.setFactura(savedFactura);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.cantidad());

            detalleRepository.save(detalle);
            detallesGuardados.add(detalle);
        }

        savedFactura.setDetalles(detallesGuardados);

        return FacturaMapper.toUnicoDTO(savedFactura);
    }

    @Override
    @Transactional(readOnly = true)
    public FacturaUnicoResponseDTO findById(Long nroFactura) {

        Factura factura = facturaRepository.findById(nroFactura)
            .orElseThrow(() ->
                new BussinessException("Factura no encontrada"));

        return FacturaMapper.toUnicoDTO(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaResponseDTO> getAll() {

        return facturaRepository.findAll()
            .stream()
            .map(FacturaMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional
    public void delete(Long nroFactura) {

        Factura factura = facturaRepository.findById(nroFactura)
            .orElseThrow(() ->
                new BussinessException("Factura no encontrada"));

        facturaRepository.delete(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaUnicoResponseDTO> findByClienteId(Long clienteId) {

        // Verificar que el cliente existe antes de buscar
        if (!clienteRepository.existsById(clienteId)) {
            throw new BussinessException(
                "Cliente con ID " + clienteId + " no encontrado");
        }

        return facturaRepository.findByClienteId(clienteId)
            .stream()
            .map(FacturaMapper::toUnicoDTO)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaUnicoResponseDTO> findByFecha(LocalDate fecha) {

        return facturaRepository.findByFechaVenta(fecha)
            .stream()
            .map(FacturaMapper::toUnicoDTO)
            .toList();
    }
}
