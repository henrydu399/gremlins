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
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.business.DistribuidoreBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Distribuidore;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/distribuidores")
public class DistribuidorController implements IController<Distribuidore> {

    private final DistribuidoreBusiness distribuidorBusiness;
    private static final Logger LOGGER = Logger.getLogger(DistribuidorController.class.getName());

    @Autowired
    public DistribuidorController(DistribuidoreBusiness distribuidorBusiness) {
        this.distribuidorBusiness = distribuidorBusiness;
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        try {
            List<Distribuidore> list = this.distribuidorBusiness.getAll();
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
    public ResponseEntity<Object> save(@RequestBody Distribuidore distribuidor) {
        try {
            this.distribuidorBusiness.save(distribuidor);
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
    public ResponseEntity<Object> edith(@RequestBody Distribuidore distribuidor) {
        try {
            this.distribuidorBusiness.edith(distribuidor);
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
    public ResponseEntity<Object> delete(@RequestBody Distribuidore distribuidor) {
        try {
            this.distribuidorBusiness.delete(distribuidor);
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
    public ResponseEntity<Object> desactivate(@RequestBody Distribuidore distribuidor) {
        try {
            this.distribuidorBusiness.desactivate(distribuidor);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en desactivate: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Object> findT(Distribuidore distribuidor) {
        try {
            Distribuidore foundDistribuidor = this.distribuidorBusiness.findT(distribuidor);
            return new ResponseEntity<>(foundDistribuidor, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/buscarTodos")
    public ResponseEntity<Object> findAllT(Distribuidore distribuidor) {
        try {
            List<Distribuidore> list = this.distribuidorBusiness.findAllT(distribuidor);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe(e.getMessageLogical());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.severe("Error en findAllT: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
}
