package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoDTO implements Serializable {
    private String esNombre;
    private List<EnvioDTO> envios;
    private List<PedidoDTO> pedidos;
}

