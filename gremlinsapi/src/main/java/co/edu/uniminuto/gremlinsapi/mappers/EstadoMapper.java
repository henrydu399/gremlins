package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.EstadoDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Estado;

public class EstadoMapper {

    public static EstadoDTO mapEstadoToDTO(Estado estado) {
        return EstadoDTO.builder()
                .esNombre(estado.getEsNombre())
                .build();
    }

    public static Estado mapDTOToEstado(EstadoDTO estadoDTO) {
        return Estado.builder()
                .esNombre(estadoDTO.getEsNombre())
                .build();
    }
}

