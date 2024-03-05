package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionePKDTO implements Serializable {
    private long fId;
    private String fNombre;
}

