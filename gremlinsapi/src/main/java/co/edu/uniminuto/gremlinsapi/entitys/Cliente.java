package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Table(name="CLIENTES", schema = "GREMLINS" )
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "c_id")
	    private Long cId;

	 
	    @Column(name = "c_nombre")
	    private String cNombre;


	    @Column(name = "c_telefono", unique = true)
	    private String cTelefono;

	  
	    @Column(name = "c_direccion")
	    private String cDireccion;
	    
	    
	    @Column(name = "c_estado")
	    private String cEstado;
	    
	    @ManyToOne
		@JoinColumn(name="c_usuario_id")
		private Usuario cUsuario;

	//bi-directional many-to-one association to Envio
	@OneToMany(mappedBy="cliente")
	private List<Envio> envios;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos;


}