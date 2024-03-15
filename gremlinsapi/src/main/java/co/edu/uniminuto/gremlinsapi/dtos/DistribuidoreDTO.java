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
public class DistribuidoreDTO implements Serializable {
    private long dId;
    private Timestamp dCreado;
    private String dEmail;
    private Timestamp dModificado;
    private String dNit;
    private String dRazonSocial;
    private String dTelefono;
    private EstadoDTO dEstado;
    private List<ProductoDTO> productos;
}

