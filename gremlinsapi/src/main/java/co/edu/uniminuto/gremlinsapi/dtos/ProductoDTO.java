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
public class ProductoDTO implements Serializable {
    private long pId;
    private Timestamp pCreado;
    private String pModificado;
    private String pNombre;
    private BigDecimal pPrecioFinal;
    private String pEstado;
    private List<PedidosDetalleDTO> pedidosDetalles;
    private DistribuidoreDTO distribuidore;
}

