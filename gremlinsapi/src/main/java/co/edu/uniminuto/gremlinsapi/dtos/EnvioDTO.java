package co.edu.uniminuto.gremlinsapi.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class EnvioDTO implements Serializable {
    private EnvioPKDTO id;
    private Timestamp eCreado;
    private String eDireccion;
    private Timestamp eModificado;
    private ClienteDTO cliente;
    private PedidoDTO pedido;
    private UsuarioDTO usuario;
    private EstadoDTO estado;
}

