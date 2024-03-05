package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;

import co.edu.uniminuto.gremlinsapi.dtos.PedidoDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Pedido;

public class PedidoMapper {

    public static PedidoDTO mapPedidoToDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .pId(pedido.getPId())
                .pCreado(pedido.getPCreado())
                .pModificado(pedido.getPModificado())
                .pTotal(pedido.getPTotal())
                .envios(pedido.getEnvios().stream().map(EnvioMapper::mapEnvioToDTO).collect(Collectors.toList()))
                .cliente(ClienteMapper.mapClienteToDTO(pedido.getCliente()))
                .estado(EstadoMapper.mapEstadoToDTO(pedido.getEstado()))
                .pedidosDetalles(pedido.getPedidosDetalles().stream().map(PedidosDetalleMapper::mapPedidosDetalleToDTO).collect(Collectors.toList()))
                .build();
    }

    public static Pedido mapDTOToPedido(PedidoDTO pedidoDTO) {
        return Pedido.builder()
                .pId(pedidoDTO.getPId())
                .pCreado(pedidoDTO.getPCreado())
                .pModificado(pedidoDTO.getPModificado())
                .pTotal(pedidoDTO.getPTotal())
                // Si necesitas mapear las listas de envíos, detalles de pedidos, etc., debes implementarlo aquí
                .build();
    }
}


