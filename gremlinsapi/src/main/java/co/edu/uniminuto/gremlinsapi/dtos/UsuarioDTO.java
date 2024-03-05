package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO implements Serializable {
    private long id;
    private Timestamp creado;
    private String email;
    private String estado;
    private Timestamp modificado;
    private String nombre;
    private String password;
    private String token;
    private List<EnvioDTO> envios;
    private RoleDTO role;
}
