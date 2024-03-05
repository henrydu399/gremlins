package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.RoleDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Role;

public class RoleMapper {

   
    public static RoleDTO mapRoleToDTO(Role role) {
        return RoleDTO.builder()
                .id(RolePKMapper.mapRolePKToDTO(role.getId()))
                .funciones(role.getFunciones().stream().map(FuncioneMapper::mapFuncioneToDTO).collect(Collectors.toList()))
                .build();
    }

    public static Role mapDTOToRole(RoleDTO roleDTO) {
        return Role.builder()
                .id(RolePKMapper.mapDTOToRolePK(roleDTO.getId()))
                // Si necesitas mapear las listas de funciones y usuarios, debes implementarlo aquí
                .build();
    }
}

