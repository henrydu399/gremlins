package co.edu.uniminuto.gremlinsapi.exceptions;

import org.springframework.http.HttpStatus;

import co.edu.uniminuto.gremlinsapi.utils.UtilGson;

public class GeneralException  extends RuntimeException{
	
	  private static final long serialVersionUID = 1L;
	  
	  private HttpStatus status;
	  private String messageLogical;

	  

	    public GeneralException(HttpStatus status, String messageLogical , Exception e) {
		super(e);
		this.status = status;
		this.messageLogical = messageLogical;
		
	}

		public HttpStatus getStatus() {
	        return status;
	    }
		
		public HttpStatus getHttpStatus() {
	        return status;
	    }

		public String getMessageLogical() {
			return   UtilGson.SerializeObjet(messageLogical);
		}

		public void setMessageLogical(String messageLogical) {
			this.messageLogical = messageLogical;
		}

	    
	    
}
