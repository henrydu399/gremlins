package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.business.EstadoBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Estado;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/estados")
public class EstadoController implements IController<Estado> {

    private final EstadoBusiness estadoBusiness;
    private static final Logger LOGGER = Logger.getLogger(EstadoController.class.getName());

    @Autowired
    public EstadoController(EstadoBusiness estadoBusiness) {
        this.estadoBusiness = estadoBusiness;
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        try {
            List<Estado> list = this.estadoBusiness.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Estado estado) {
        try {
            this.estadoBusiness.save(estado);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<Object> edith(@RequestBody Estado estado) {
        try {
            this.estadoBusiness.edith(estado);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en edit: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Estado estado) {
        try {
            this.estadoBusiness.delete(estado);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/desactivar")
    public ResponseEntity<Object> desactivate(@RequestBody Estado estado) {
        try {
            this.estadoBusiness.desactivate(estado);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (UnsupportedOperationException e) {
            LOGGER.severe(e.getMessage());
            return new ResponseEntity<>("Desactivación de estados no implementada", HttpStatus.NOT_IMPLEMENTED);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en desactivate: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  

	@Override
	public ResponseEntity<Object> findT(Estado p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> findAllT(Estado p) {
		// TODO Auto-generated method stub
		return null;
	}
	
}