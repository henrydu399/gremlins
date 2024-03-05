package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePKDTO implements Serializable {
    private long rId;
    private String rNombre;
}
