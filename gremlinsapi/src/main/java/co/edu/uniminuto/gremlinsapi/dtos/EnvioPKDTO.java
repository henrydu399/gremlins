package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvioPKDTO implements Serializable {
    private long eId;
    private long eClienteId;
    private long ePedidoId;
}

