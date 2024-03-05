package co.edu.uniminuto.gremlinsapi.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidosDetalleDTO implements Serializable {
    private PedidosDetallePKDTO id;
    private int pdCantidad;
    private ProductoDTO producto;
}

