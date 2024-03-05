package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO implements Serializable {
    private long pId;
    private Timestamp pCreado;
    private Timestamp pModificado;
    private BigDecimal pTotal;
    private List<EnvioDTO> envios;
    private ClienteDTO cliente;
    private EstadoDTO estado;
    private List<PedidosDetalleDTO> pedidosDetalles;
}
