package supermercado.general.mapper;

import supermercado.general.dto.FacturaResponseDTO;
import supermercado.general.dto.FacturaUnicoResponseDTO;
import supermercado.general.model.Factura;

public class FacturaMapper {

    public static FacturaUnicoResponseDTO toUnicoDTO(Factura factura) {
        return new FacturaUnicoResponseDTO(
            factura.getNroFactura(),
            factura.getCliente() != null ? factura.getCliente().getNombreCompleto() : null,
            factura.getDescripcion(),
            factura.getObservacion(),
            factura.getFechaVenta(),
            factura.getDetalles() != null
                ? factura.getDetalles().stream().map(DetalleMapper::toDTO).toList()
                : null,
            factura.getTotal()
        );
    }

        public static FacturaResponseDTO toDTO(Factura factura) {
        return new FacturaResponseDTO(
            factura.getNroFactura(),
            factura.getCliente() != null ? factura.getCliente().getNombreCompleto() : null,
            factura.getDescripcion(),
            factura.getObservacion(),
            factura.getFechaVenta(),
            factura.getTotal()
        );
    }

}