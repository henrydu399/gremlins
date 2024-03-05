package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="FUNCIONES")
@NamedQuery(name="Funcione.findAll", query="SELECT f FROM Funcione f")
public class Funcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FuncionePK id;
	
	@Column(name="f_route")
	private String fRoute;
	
	@Column(name="f_is_visible")
	private Boolean fIsVisible;

	//bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="FUNCIONES_ROLES"
		, joinColumns={
			@JoinColumn(name="fr_funcion_id", referencedColumnName="f_id"),
			@JoinColumn(name="fr_funcion_nombre", referencedColumnName="f_nombre")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fr_rol_id", referencedColumnName="r_id"),
			@JoinColumn(name="fr_rol_nombre", referencedColumnName="r_nombre")
			}
		)
	private List<Role> roles;


}