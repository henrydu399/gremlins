package co.edu.uniminuto.gremlinsapi.dtos;

import java.util.List;

public class TableDto {

	private String idparametrica;
	private String parametricaName;
	private String parametricaUrlApi;
	private List<ColumnDto> campos;

	/**
	 * @return the idparametrica
	 */
	public String getIdparametrica() {
		return idparametrica;
	}

	/**
	 * @param idparametrica the idparametrica to set
	 */
	public void setIdparametrica(String idparametrica) {
		this.idparametrica = idparametrica;
	}

	/**
	 * @return the parametricaName
	 */
	public String getParametricaName() {
		return parametricaName;
	}

	/**
	 * @param parametricaName the parametricaName to set
	 */
	public void setParametricaName(String parametricaName) {
		this.parametricaName = parametricaName;
	}

	/**
	 * @return the parametricaUrlApi
	 */
	public String getParametricaUrlApi() {
		return parametricaUrlApi;
	}

	/**
	 * @param parametricaUrlApi the parametricaUrlApi to set
	 */
	public void setParametricaUrlApi(String parametricaUrlApi) {
		this.parametricaUrlApi = parametricaUrlApi;
	}

	/**
	 * @return the column
	 */
	public List<ColumnDto> getColumn() {
		return campos;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(List<ColumnDto> column) {
		this.campos = column;
	}

	
	
	

}
