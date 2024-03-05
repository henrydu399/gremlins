package co.edu.uniminuto.gremlinsapi.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO implements Serializable {
    private Long cId;
    private String cNombre;
    private String cTelefono;
    private String cDireccion;
    private String cEstado;
    private List<EnvioDTO> envios;
    private List<PedidoDTO> pedidos;
}

