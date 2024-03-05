package co.edu.uniminuto.gremlinsapi.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniminuto.gremlinsapi.dtos.UsuarioDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;

public class UsuarioMapper {



    public static UsuarioDTO mapUsuarioToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .creado(usuario.getCreado())
                .email(usuario.getEmail())
                .estado(usuario.getEstado())
                .modificado(usuario.getModificado())
                .nombre(usuario.getNombre())
                .password(usuario.getPassword())
                .token(usuario.getToken())
                .envios(usuario.getEnvios().stream().map(EnvioMapper::mapEnvioToDTO).collect(Collectors.toList()))
                .role(RoleMapper.mapRoleToDTO(usuario.getRole()))
                .build();
    }

    public static Usuario mapDTOToUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .creado(usuarioDTO.getCreado())
                .email(usuarioDTO.getEmail())
                .estado(usuarioDTO.getEstado())
                .modificado(usuarioDTO.getModificado())
                .nombre(usuarioDTO.getNombre())
                .password(usuarioDTO.getPassword())
                .token(usuarioDTO.getToken())
                // Si necesitas mapear la lista de envíos, debes implementarlo aquí
                .build();
    }
}
