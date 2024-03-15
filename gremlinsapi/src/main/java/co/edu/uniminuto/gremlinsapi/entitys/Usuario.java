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
import java.util.Objects;


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

	@Override
	public int hashCode() {
		return Objects.hash(creado, email, envios, estado, id, modificado, nombre, password, role, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(creado, other.creado) && Objects.equals(email, other.email)
				&& Objects.equals(envios, other.envios) && Objects.equals(estado, other.estado) && id == other.id
				&& Objects.equals(modificado, other.modificado) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(token, other.token);
	}

	
	
	
	
}