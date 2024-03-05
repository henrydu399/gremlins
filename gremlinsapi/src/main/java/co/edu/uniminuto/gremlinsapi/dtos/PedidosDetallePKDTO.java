package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidosDetallePKDTO implements Serializable {
    private long pdId;
    private long pdPedidoId;
}

