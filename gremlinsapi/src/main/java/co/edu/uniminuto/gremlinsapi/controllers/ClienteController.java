package co.edu.uniminuto.gremlinsapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.gremlinsapi.business.ClienteBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Cliente;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/clientes")
public class ClienteController implements  IController<Cliente>{

    private final ClienteBusiness clienteBusiness;
    
    private static final Logger LOGGER = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    public ClienteController(ClienteBusiness clienteBusiness) {
        this.clienteBusiness = clienteBusiness;
    }

	@GetMapping(value = "/getAll")
	@Override
	public ResponseEntity<Object> getAll() {
		try {
			 List<Cliente>  list=  this.clienteBusiness.getAll();
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Object> save(@RequestBody Cliente c) {
		try {
			  this.clienteBusiness.save(c);
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Object> edith(@RequestBody Cliente c) {
		try {
			  this.clienteBusiness.save(c);
			return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

    @PostMapping(value = "/findT/" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Object> findT(@RequestBody Cliente c) {
		try {
			Cliente cliente = this.clienteBusiness.findT(c);
			return new ResponseEntity<Object>(cliente, HttpStatus.OK);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

    @PostMapping(value = "/findAllT/" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Override   
	public ResponseEntity<Object> findAllT(Cliente c) {
		try {
			 List<Cliente>  list=  this.clienteBusiness.findAllT(c);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

    @PostMapping(value = "/delete/" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Object> delete(Cliente p) {
		try {
			  this.clienteBusiness.delete(p);
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

    
    @PostMapping(value = "/desactivate/" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Object> desactivate(@RequestBody Cliente c) {
		try {
			  this.clienteBusiness.desactivate(c);
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}catch (GeneralException e) {
			return new ResponseEntity<Object>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

    
}
