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
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="u_id")
	private long id;

	@Column(name="u_creado")
	private Timestamp creado;

	@Column(name="u_email")
	private String email;

	@Column(name="u_estado")
	private String estado;

	@Column(name="u_modificado")
	private Timestamp modificado;

	@Column(name="u_nombre")
	private String nombre;

	@Column(name="u_password")
	private String password;
	
	@Transient
	private String token;

	//bi-directional many-to-one association to Envio
	@OneToMany(mappedBy="usuario")
	private List<Envio> envios;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="u_rol_id", referencedColumnName="r_id"),
		@JoinColumn(name="u_rol_nombre", referencedColumnName="r_nombre")
		})
	private Role role;

	
}