package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;

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
@Table(name="ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="es_nombre")
	private String esNombre;

	//bi-directional many-to-one association to Envio
	@OneToMany(mappedBy="estado")
	private List<Envio> envios;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="estado")
	private List<Pedido> pedidos;

	

}