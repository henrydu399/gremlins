package co.edu.uniminuto.gremlinsapi.mappers;

import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.FuncionePKDTO;
import co.edu.uniminuto.gremlinsapi.entitys.FuncionePK;

public class FuncionePKMapper {

    public static FuncionePKDTO mapFuncionePKToDTO(FuncionePK funcionePK) {
        return FuncionePKDTO.builder()
                .fId(funcionePK.getFId())
                .fNombre(funcionePK.getFNombre())
                .build();
    }

    public static FuncionePK mapDTOToFuncionePK(FuncionePKDTO funcionePKDTO) {
        return FuncionePK.builder()
                .fId(funcionePKDTO.getFId())
                .fNombre(funcionePKDTO.getFNombre())
                .build();
    }
}
