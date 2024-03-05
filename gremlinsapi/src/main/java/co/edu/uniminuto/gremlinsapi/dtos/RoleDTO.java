package co.edu.uniminuto.gremlinsapi.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO implements Serializable {
    private RolePKDTO id;
    private List<FuncioneDTO> funciones;
    private List<UsuarioDTO> usuarios;
}

