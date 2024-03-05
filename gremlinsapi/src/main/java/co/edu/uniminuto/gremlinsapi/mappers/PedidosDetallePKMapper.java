package co.edu.uniminuto.gremlinsapi.mappers;

import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.PedidosDetallePKDTO;
import co.edu.uniminuto.gremlinsapi.entitys.PedidosDetallePK;

public class PedidosDetallePKMapper {

    public static PedidosDetallePKDTO mapPedidosDetallePKToDTO(PedidosDetallePK pedidosDetallePK) {
        return PedidosDetallePKDTO.builder()
                .pdId(pedidosDetallePK.getPdId())
                .pdPedidoId(pedidosDetallePK.getPdPedidoId())
                .build();
    }

    public static PedidosDetallePK mapDTOToPedidosDetallePK(PedidosDetallePKDTO pedidosDetallePKDTO) {
        return PedidosDetallePK.builder()
                .pdId(pedidosDetallePKDTO.getPdId())
                .pdPedidoId(pedidosDetallePKDTO.getPdPedidoId())
                .build();
    }
}
