package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
public class PedidosDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="pd_id")
	private long pdId;

	@Column(name="pd_pedido_id", insertable=false, updatable=false)
	private long pdPedidoId;

	@Override
	public int hashCode() {
		return Objects.hash(pdId, pdPedidoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidosDetallePK other = (PedidosDetallePK) obj;
		return pdId == other.pdId && pdPedidoId == other.pdPedidoId;
	}

	
}