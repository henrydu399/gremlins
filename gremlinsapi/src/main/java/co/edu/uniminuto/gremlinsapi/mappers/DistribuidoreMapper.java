package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.DistribuidoreDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Distribuidore;

import java.util.List;
import java.util.stream.Collectors;

public class DistribuidoreMapper {

    public static DistribuidoreDTO mapDistribuidoreToDTO(Distribuidore distribuidor) {
        return DistribuidoreDTO.builder()
                .dId(distribuidor.getDId())
                .dCreado(distribuidor.getDCreado())
                .dEmail(distribuidor.getDEmail())
                .dModificado(distribuidor.getDModificado())
                .dNit(distribuidor.getDNit())
                .dRazonSocial(distribuidor.getDRazonSocial())
                .dTelefono(distribuidor.getDTelefono())
                .dEstado(distribuidor.getDEstado())
                .build();
    }

    public static Distribuidore mapDTOToDistribuidore(DistribuidoreDTO distribuidorDTO) {
        return Distribuidore.builder()
                .dId(distribuidorDTO.getDId())
                .dCreado(distribuidorDTO.getDCreado())
                .dEmail(distribuidorDTO.getDEmail())
                .dModificado(distribuidorDTO.getDModificado())
                .dNit(distribuidorDTO.getDNit())
                .dRazonSocial(distribuidorDTO.getDRazonSocial())
                .dTelefono(distribuidorDTO.getDTelefono())
                .dEstado(distribuidorDTO.getDEstado())
                .build();
    }
}

