package co.edu.uniminuto.gremlinsapi.mappers;

import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.EnvioPKDTO;
import co.edu.uniminuto.gremlinsapi.entitys.EnvioPK;

public class EnvioPKMapper {

    public static EnvioPKDTO mapEnvioPKToDTO(EnvioPK envioPK) {
        return EnvioPKDTO.builder()
                .eId(envioPK.getEId())
                .eClienteId(envioPK.getEClienteId())
                .ePedidoId(envioPK.getEPedidoId())
                .build();
    }

    public static EnvioPK mapDTOToEnvioPK(EnvioPKDTO envioPKDTO) {
        EnvioPK envioPK = new EnvioPK();
        envioPK.setEId(envioPKDTO.getEId());
        envioPK.setEClienteId(envioPKDTO.getEClienteId());
        envioPK.setEPedidoId(envioPKDTO.getEPedidoId());
        return envioPK;
    }
}
