package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="PEDIDOS_DETALLES")
@NamedQuery(name="PedidosDetalle.findAll", query="SELECT p FROM PedidosDetalle p")
public class PedidosDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidosDetallePK id;

	@Column(name="pd_cantidad")
	private int pdCantidad;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="pd_id_producto")
	private Producto producto;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="pd_pedido_id" , insertable=false, updatable=false)
	private Pedido pedido;

	
}