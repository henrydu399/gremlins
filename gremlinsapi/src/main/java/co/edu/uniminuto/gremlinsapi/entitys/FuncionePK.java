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
public class FuncionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="f_id")
	private long fId;

	@Column(name="f_nombre")
	private String fNombre;


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FuncionePK)) {
			return false;
		}
		FuncionePK castOther = (FuncionePK)other;
		return 
			(this.fId == castOther.fId)
			&& this.fNombre.equals(castOther.fNombre);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.fId ^ (this.fId >>> 32)));
		hash = hash * prime + this.fNombre.hashCode();
		
		return hash;
	}
}