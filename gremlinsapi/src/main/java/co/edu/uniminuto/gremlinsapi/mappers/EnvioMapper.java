package co.edu.uniminuto.gremlinsapi.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.EnvioDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Envio;

public class EnvioMapper {

    private static final EnvioPKMapper envioPKMapper = new EnvioPKMapper();
    private static final EstadoMapper estadoMapper = new EstadoMapper();

    public static EnvioDTO mapEnvioToDTO(Envio envio) {
        return EnvioDTO.builder()
                .id(EnvioPKMapper.mapEnvioPKToDTO(envio.getId()))
                .eCreado(envio.getECreado())
                .eDireccion(envio.getEDireccion())
                .eModificado(envio.getEModificado())          
                .estado(EstadoMapper.mapEstadoToDTO(envio.getEstado()))
                .build();
    }

    public static Envio mapDTOToEnvio(EnvioDTO envioDTO) {
        return Envio.builder()
                .id(EnvioPKMapper.mapDTOToEnvioPK(envioDTO.getId()))
                .eCreado(envioDTO.getECreado())
                .eDireccion(envioDTO.getEDireccion())
                .eModificado(envioDTO.getEModificado())             
                .estado(EstadoMapper.mapDTOToEstado(envioDTO.getEstado()))
                .build();
    }
}

