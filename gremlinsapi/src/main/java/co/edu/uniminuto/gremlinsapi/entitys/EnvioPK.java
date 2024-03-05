package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import java.util.List;

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
@Embeddable
public class EnvioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="e_id")
	private long eId;

	@Column(name="e_cliente_id", insertable=false, updatable=false)
	private long eClienteId;

	@Column(name="e_pedido_id", insertable=false, updatable=false)
	private long ePedidoId;

	

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EnvioPK)) {
			return false;
		}
		EnvioPK castOther = (EnvioPK)other;
		return 
			(this.eId == castOther.eId)
			&& (this.eClienteId == castOther.eClienteId)
			&& (this.ePedidoId == castOther.ePedidoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.eId ^ (this.eId >>> 32)));
		hash = hash * prime + ((int) (this.eClienteId ^ (this.eClienteId >>> 32)));
		hash = hash * prime + ((int) (this.ePedidoId ^ (this.ePedidoId >>> 32)));
		
		return hash;
	}
}