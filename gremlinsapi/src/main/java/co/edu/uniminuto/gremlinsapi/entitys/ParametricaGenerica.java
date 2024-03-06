package co.edu.uniminuto.gremlinsapi.entitys;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the juz_parametrica_genericas database table.
 * 
 */
@Entity
@Table(name="parametrica_genericas")
@NamedQuery(name="JuzParametricaGenerica.findAll", query="SELECT j FROM ParametricaGenerica j")
public class ParametricaGenerica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="jp_id_parametrica", unique=true, nullable=false)
	private int jpIdParametrica;

	@Column(name="jp_id_parametrica_name", length=100)
	private String jpIdParametricaName;

	@Column(name="jp_nombre_class", length=255)
	private String jpNombreClass;

	@Column(name="jp_nombre_pakage", length=255)
	private String jpNombrePakage;

	@Column(name="jp_parametrica_name", length=100)
	private String jpParametricaName;

	@Column(name="jp_parametrica_url_api", length=100)
	private String jpParametricaUrlApi;

	public ParametricaGenerica() {
	}

	public int getJpIdParametrica() {
		return this.jpIdParametrica;
	}

	public void setJpIdParametrica(int jpIdParametrica) {
		this.jpIdParametrica = jpIdParametrica;
	}

	public String getJpIdParametricaName() {
		return this.jpIdParametricaName;
	}

	public void setJpIdParametricaName(String jpIdParametricaName) {
		this.jpIdParametricaName = jpIdParametricaName;
	}

	public String getJpNombreClass() {
		return this.jpNombreClass;
	}

	public void setJpNombreClass(String jpNombreClass) {
		this.jpNombreClass = jpNombreClass;
	}

	public String getJpNombrePakage() {
		return this.jpNombrePakage;
	}

	public void setJpNombrePakage(String jpNombrePakage) {
		this.jpNombrePakage = jpNombrePakage;
	}

	public String getJpParametricaName() {
		return this.jpParametricaName;
	}

	public void setJpParametricaName(String jpParametricaName) {
		this.jpParametricaName = jpParametricaName;
	}

	public String getJpParametricaUrlApi() {
		return this.jpParametricaUrlApi;
	}

	public void setJpParametricaUrlApi(String jpParametricaUrlApi) {
		this.jpParametricaUrlApi = jpParametricaUrlApi;
	}

}