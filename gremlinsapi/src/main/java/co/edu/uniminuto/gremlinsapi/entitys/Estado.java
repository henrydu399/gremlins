package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.uniminuto.gremlinsapi.anotations.ILabelName;
import co.edu.uniminuto.gremlinsapi.anotations.ILabelParametricaName;
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
@Table(name="ESTADOS")
@ILabelParametricaName(name = "ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="es_nombre")
	@ILabelName( name = "ID_ESTADO")
	private String esNombre;

	//bi-directional many-to-one association to Envio
	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<Envio> envios;

	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<Pedido> pedidos;

	

}