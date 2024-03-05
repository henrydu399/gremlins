package co.edu.uniminuto.gremlinsapi.mappers;

import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.RolePKDTO;
import co.edu.uniminuto.gremlinsapi.entitys.RolePK;

public class RolePKMapper {

    public static RolePKDTO mapRolePKToDTO(RolePK rolePK) {
        return RolePKDTO.builder()
                .rId(rolePK.getRId())
                .rNombre(rolePK.getRNombre())
                .build();
    }

    public static RolePK mapDTOToRolePK(RolePKDTO rolePKDTO) {
        return RolePK.builder()
                .rId(rolePKDTO.getRId())
                .rNombre(rolePKDTO.getRNombre())
                .build();
    }
}
