package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="DISTRIBUIDORES")
@NamedQuery(name="Distribuidore.findAll", query="SELECT d FROM Distribuidore d")
public class Distribuidore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="d_id")
	private long dId;

	@Column(name="d_creado")
	private Timestamp dCreado;

	@Column(name="d_email")
	private String dEmail;

	@Column(name="d_modificado")
	private Timestamp dModificado;

	@Column(name="d_nit")
	private String dNit;

	@Column(name="d_razon_social")
	private String dRazonSocial;

	@Column(name="d_telefono")
	private String dTelefono;
	
	
	 
	//bi-directional many-to-one association to Estado
		@ManyToOne
		@JoinColumn(name="d_estado")
		private Estado dEstado;

	//bi-directional many-to-one association to Producto
		  @JsonIgnore
	@OneToMany(mappedBy="distribuidore")
	private List<Producto> productos;

}