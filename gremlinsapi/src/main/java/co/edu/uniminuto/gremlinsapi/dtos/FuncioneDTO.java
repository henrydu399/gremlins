package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncioneDTO implements Serializable {
    private FuncionePKDTO id;
    private String fRoute;
    private Boolean fIsVisible;
    private List<RoleDTO> roles;
}
