package co.edu.uniminuto.gremlinsapi.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.PedidosDetalleDTO;
import co.edu.uniminuto.gremlinsapi.entitys.PedidosDetalle;

public class PedidosDetalleMapper {


    public static PedidosDetalleDTO mapPedidosDetalleToDTO(PedidosDetalle pedidosDetalle) {
        return PedidosDetalleDTO.builder()
                .id(PedidosDetallePKMapper.mapPedidosDetallePKToDTO(pedidosDetalle.getId()))
                .pdCantidad(pedidosDetalle.getPdCantidad())
                .producto(ProductoMapper.mapProductoToDTO(pedidosDetalle.getProducto()))
                .build();
    }

    public static PedidosDetalle mapDTOToPedidosDetalle(PedidosDetalleDTO pedidosDetalleDTO) {
        return PedidosDetalle.builder()
                .id(PedidosDetallePKMapper.mapDTOToPedidosDetallePK(pedidosDetalleDTO.getId()))
                .pdCantidad(pedidosDetalleDTO.getPdCantidad())
                // Si necesitas mapear el producto, debes implementarlo aquí
                .build();
    }
}
