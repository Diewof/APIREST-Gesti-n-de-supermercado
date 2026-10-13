package supermercado.general.mapper;

import supermercado.general.dto.DetalleResponseDTO;
import supermercado.general.model.Detalle;

public class DetalleMapper {
    public static DetalleResponseDTO toDTO(Detalle detalle) {
        return new DetalleResponseDTO(
            detalle.getId(),
            detalle.getProducto() != null ? detalle.getProducto().getDescripcion() : null,
            detalle.getCantidad(),
            detalle.getProducto() != null ? detalle.getProducto().getPrecio() : null,
            detalle.getSubTotal()
        );
    }

}