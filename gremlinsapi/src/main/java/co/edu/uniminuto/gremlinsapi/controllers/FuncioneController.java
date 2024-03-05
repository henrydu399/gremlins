package co.edu.uniminuto.gremlinsapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.business.FuncioneBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Funcione;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/funciones")
public class FuncioneController implements IController<Funcione> {

    private final FuncioneBusiness funcioneBusiness;

    @Autowired
    public FuncioneController(FuncioneBusiness funcioneBusiness) {
        this.funcioneBusiness = funcioneBusiness;
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<Object> getAll() {
        try {
            return new ResponseEntity<>(funcioneBusiness.getAll(), HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<Object> save(@RequestBody Funcione funcione) {
        try {
            funcioneBusiness.save(funcione);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    @Override
    public ResponseEntity<Object> edith(@RequestBody Funcione funcione) {
        try {
            funcioneBusiness.save(funcione);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findT")
    @Override
    public ResponseEntity<Object> findT(@RequestBody Funcione funcione) {
        try {
            return new ResponseEntity<>(funcioneBusiness.findT(funcione), HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findAllT")
    @Override
    public ResponseEntity<Object> findAllT(@RequestBody Funcione funcione) {
        try {
            return new ResponseEntity<>(funcioneBusiness.findAllT(funcione), HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    @Override
    public ResponseEntity<Object> delete(@RequestBody Funcione funcione) {
        try {
            funcioneBusiness.delete(funcione);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/desactivate")
    @Override
    public ResponseEntity<Object> desactivate(@RequestBody Funcione funcione) {
        try {
            funcioneBusiness.desactivate(funcione);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }
}
