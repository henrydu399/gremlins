package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
@Table(name="PRODUCTOS")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="p_id")
	private long pId;

	@Column(name="p_creado")
	private Timestamp pCreado;

	@Column(name="p_modificado")
	private String pModificado;


    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(name = "p_nombre")
    private String pNombre;

	@Column(name="p_precio_final")
	private BigDecimal pPrecioFinal;
	
	  @NotBlank(message = "EL estado del producto es obligatorio")
	    @Column(name = "p_estado")
	    private String pEstado;

	//bi-directional many-to-one association to PedidosDetalle
	@OneToMany(mappedBy="producto")
	private List<PedidosDetalle> pedidosDetalles;

	//bi-directional many-to-one association to Distribuidore
	@ManyToOne
	@JoinColumn(name="p_distribuidor_id")
	private Distribuidore distribuidore;

	
}