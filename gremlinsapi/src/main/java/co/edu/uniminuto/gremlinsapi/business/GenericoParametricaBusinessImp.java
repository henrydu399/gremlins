
package co.edu.uniminuto.gremlinsapi.business;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.anotations.ILabelName;
import co.edu.uniminuto.gremlinsapi.anotations.ILabelParametricaName;
import co.edu.uniminuto.gremlinsapi.dtos.ColumnDto;
import co.edu.uniminuto.gremlinsapi.dtos.TableDto;
import co.edu.uniminuto.gremlinsapi.entitys.ParametricaGenerica;
import co.edu.uniminuto.gremlinsapi.repositorys.IParametricaGenericaRepository;
import co.edu.uniminuto.gremlinsapi.utils.UtilGson;





@Service
public class GenericoParametricaBusinessImp {

	private final String ID = "javax.persistence.Id";
	private final String INCREMENTAL = "javax.persistence.GeneratedValue";
	private final String NO_NULL = "org.springframework.lang.NonNull";
	private final String LABEL ="co.system.out.jusgadoapi.api.anotations.ILabelName";
	
	private final String LABEL_PARAMETRICA ="co.system.out.jusgadoapi.api.anotations.ILabelParametricaName";
	
	private final String SERIALIZE = "serialVersionUID";
	private final String LIST = "java.util.List";
	
	private final String TIME_STAMP = "java.sql.Timestamp";
	
	
	static Logger logger = Logger.getLogger(GenericoParametricaBusinessImp.class.getName());

	@Autowired
	IParametricaGenericaRepository parametricasRepository;

	public List<TableDto> findAllParametricasSystem() throws ClassNotFoundException {
		List<TableDto> listOfPostDTO = null;
		List<ParametricaGenerica> listaEntidadesParametricas = parametricasRepository.findAll();
		if (listaEntidadesParametricas != null && listaEntidadesParametricas.size() > 0) {
			listOfPostDTO = new ArrayList<TableDto>();
			for (ParametricaGenerica parametricaEntity : listaEntidadesParametricas) {
				try {
					
				}catch (Exception e) {
					logger.log(Level.SEVERE, "ERROR CARGANDO LAS PARAMETRICAS :"+ UtilGson.SerializeObjet(parametricaEntity ) );  
				}
				TableDto table = new TableDto();
				table.setIdparametrica(parametricaEntity.getJpIdParametricaName());
				table.setParametricaName(parametricaEntity.getJpParametricaName());
				table.setParametricaUrlApi(parametricaEntity.getJpParametricaUrlApi());
				
			
				this.GetNameParametricaAnotations(table, parametricaEntity.getJpNombrePakage());
				
              table.setColumn(getColumnInEntity(parametricaEntity.getJpNombrePakage()));  
              listOfPostDTO.add(table);

			}

		}

		return listOfPostDTO;
	}
	
	private TableDto  GetNameParametricaAnotations( TableDto table, String nameClass) throws ClassNotFoundException {
		
				
		Class<?> _class = Class.forName(nameClass);
		Annotation[] annotations = _class.getAnnotations();
		for (Annotation an : annotations) {
			if (an.annotationType().getName().equals(LABEL_PARAMETRICA)) {
				ILabelParametricaName anotacion =  (ILabelParametricaName) an;
				if ( anotacion.name() != null) {
					table.setParametricaName(anotacion.name());
				}
				
			}
		}
		
		return table;
	}

	private List<ColumnDto> getColumnInEntity(String nameClass) throws ClassNotFoundException {
		List<ColumnDto> out = new ArrayList<ColumnDto>();
		try {

			Class<?> _class = Class.forName(nameClass);
			Field properties[] = _class.getDeclaredFields();
			for (int i = 0; i < properties.length; i++) {
				Field field = properties[i];
				ColumnDto column = new ColumnDto();
				column.setNameColumn(field.getName());
				column.setType(field.getType().getName());

				column = getAnotacionesConfig(field.getAnnotations(),column );
				// LOGICA QUE DESCARTA ALGUNAS COLUMNAS 
				if(        field.getName().equals(SERIALIZE) 
						|| field.getType().getName().equals(LIST) 
						|| field.getType().getName().equals(TIME_STAMP)
						) {
					continue;
				}
				
				
				
				out.add(column);

				// System.out.println(field.getName() + " > " + field.getType());

			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(GenericoParametricaBusinessImp.class.getName()).log(Level.SEVERE, null, ex);
		}

		return out;
	}

	private ColumnDto  getAnotacionesConfig(Annotation[] annotations, ColumnDto column) {

		for (Annotation an : annotations) {

			if (an.annotationType().getName().equals(ID)) {

			}
			if (an.annotationType().getName().equals(INCREMENTAL)) {
				column.setAuto(true);
			}

			if (an.annotationType().getName().equals(NO_NULL)) {
				column.setRequeride(true);
			}
			if (an.annotationType().getName().equals(ID)) {

			}	
			if( an.annotationType().getName().equals(LABEL)) {
				ILabelName anotacion =  (ILabelName) an;
				column.setNameLabel(anotacion.name());
			}
			
			
		
		}
		return column;
	}

}
