package co.edu.uniminuto.gremlinsapi.validations;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;



import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@Component
public class LoginValidation {
	
	private final String ERROR_PASSWORD = "El password no puede estar vacio";
	private final String ERROR_CORREO = "El email no puede estar vacio";
	private final String ERROR_SISTEMA = "El sistema no puede estar vacio";
	
	
	public void login(Usuario e) throws GeneralException{
		
		if( Objects.isNull(e.getEmail())) {
			 throw new GeneralException(HttpStatus.BAD_REQUEST, "Error con el parametro email", null);
		}
		if( Objects.isNull(e.getPassword())) {
			 throw new GeneralException(HttpStatus.BAD_REQUEST, "Error con el parametro password", null);
		}
		
	}

}
