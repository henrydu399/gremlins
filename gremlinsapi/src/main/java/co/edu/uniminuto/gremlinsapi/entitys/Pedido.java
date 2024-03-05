package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="PEDIDOS")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="p_id")
	private long pId;

	@Column(name="p_creado")
	private Timestamp pCreado;

	@Column(name="p_modificado")
	private Timestamp pModificado;

	@Column(name="p_total")
	private BigDecimal pTotal;

	//bi-directional many-to-one association to Envio
	@OneToMany(mappedBy="pedido")
	private List<Envio> envios;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="p_cliente_id")
	private Cliente cliente;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="p_estado")
	private Estado estado;

	//bi-directional many-to-one association to PedidosDetalle
	@OneToMany(mappedBy="pedido")
	private List<PedidosDetalle> pedidosDetalles;

	
}