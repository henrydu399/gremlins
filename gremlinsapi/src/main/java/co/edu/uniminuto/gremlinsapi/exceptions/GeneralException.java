package co.edu.uniminuto.gremlinsapi.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException  extends Exception{
	
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
			return messageLogical;
		}

		public void setMessageLogical(String messageLogical) {
			this.messageLogical = messageLogical;
		}

	    
	    
}
