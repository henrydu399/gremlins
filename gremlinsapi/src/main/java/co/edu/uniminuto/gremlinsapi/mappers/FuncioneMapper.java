package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.FuncioneDTO;
import co.edu.uniminuto.gremlinsapi.dtos.FuncionePKDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Funcione;
import co.edu.uniminuto.gremlinsapi.entitys.FuncionePK;

public class FuncioneMapper {

    public static FuncioneDTO mapFuncioneToDTO(Funcione funcione) {
        return FuncioneDTO.builder()
                .id(FuncionePKDTO.builder()
                        .fId(funcione.getId().getFId())
                        .fNombre(funcione.getId().getFNombre())
                        .build())
                .fRoute(funcione.getFRoute())
                .fIsVisible(funcione.getFIsVisible())        
                .build();
    }

    public static Funcione mapDTOToFuncione(FuncioneDTO funcioneDTO) {
        return Funcione.builder()
                .id(FuncionePK.builder()
                        .fId(funcioneDTO.getId().getFId())
                        .fNombre(funcioneDTO.getId().getFNombre())
                        .build())
                .fRoute(funcioneDTO.getFRoute())
                .fIsVisible(funcioneDTO.getFIsVisible())
                // Si necesitas mapear la lista de roles, debes implementarlo aquí
                .build();
    }
}

