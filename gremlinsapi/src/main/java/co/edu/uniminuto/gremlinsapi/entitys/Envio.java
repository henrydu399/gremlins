package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="ENVIOS")
@NamedQuery(name="Envio.findAll", query="SELECT e FROM Envio e")
public class Envio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnvioPK id;

	@Column(name="e_creado")
	private Timestamp eCreado;

	@Column(name="e_direccion")
	private String eDireccion;

	@Column(name="e_modificado")
	private Timestamp eModificado;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="e_cliente_id" , insertable=false, updatable=false)
	private Cliente cliente;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="e_pedido_id" , insertable=false, updatable=false)
	private Pedido pedido;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="e_usuario_transportador_id")
	private Usuario usuario;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="e_estado")
	private Estado estado;

	

	@Override
	public String toString() {
		return "Envio [id=" + id + ", eCreado=" + eCreado + ", eDireccion=" + eDireccion + ", eModificado="
				+ eModificado + ", cliente=" + cliente + ", pedido=" + pedido + ", usuario=" + usuario + ", estado="
				+ estado + "]";
	}

	
	
}