package co.edu.uniminuto.gremlinsapi.dtos;

public class ColumnDto {
	
  private String  nameColumn;
  private String  model;
  private String  type;
  private Boolean  auto;
  private Boolean  requeride;
  private int       max;
  private int       min;
  private String   nameLabel;
/**
 * @return the nameColumn
 */
public String getNameColumn() {
	return nameColumn;
}
/**
 * @param nameColumn the nameColumn to set
 */
public void setNameColumn(String nameColumn) {
	this.nameColumn = nameColumn;
}
/**
 * @return the model
 */
public String getModel() {
	return model;
}
/**
 * @param model the model to set
 */
public void setModel(String model) {
	this.model = model;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the auto
 */
public Boolean getAuto() {
	return auto;
}
/**
 * @param auto the auto to set
 */
public void setAuto(Boolean auto) {
	this.auto = auto;
}
/**
 * @return the requeride
 */
public Boolean getRequeride() {
	return requeride;
}
/**
 * @param requeride the requeride to set
 */
public void setRequeride(Boolean requeride) {
	this.requeride = requeride;
}
/**
 * @return the max
 */
public int getMax() {
	return max;
}
/**
 * @param max the max to set
 */
public void setMax(int max) {
	this.max = max;
}
/**
 * @return the min
 */
public int getMin() {
	return min;
}
/**
 * @param min the min to set
 */
public void setMin(int min) {
	this.min = min;
}
/**
 * @return the nameLabel
 */
public String getNameLabel() {
	return nameLabel;
}
/**
 * @param nameLabel the nameLabel to set
 */
public void setNameLabel(String nameLabel) {
	this.nameLabel = nameLabel;
}
  
  

}
