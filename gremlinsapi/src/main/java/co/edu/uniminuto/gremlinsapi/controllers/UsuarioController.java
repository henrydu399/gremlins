package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.business.UsuarioBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/usuarios")
public class UsuarioController implements IController<Usuario> {

    private final UsuarioBusiness usuarioBusiness;
    private static final Logger LOGGER = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    public UsuarioController(UsuarioBusiness usuarioBusiness) {
        this.usuarioBusiness = usuarioBusiness;
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        try {
            List<Usuario> list = this.usuarioBusiness.getAll();
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
    public ResponseEntity<Object> save(@RequestBody Usuario u) {
        try {
            this.usuarioBusiness.save(u);
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
    public ResponseEntity<Object> edith(@RequestBody Usuario p) {
        try {
            this.usuarioBusiness.edith(p);
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
    public ResponseEntity<Object> delete(@RequestBody Usuario p) {
        try {
            this.usuarioBusiness.delete(p);
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
    public ResponseEntity<Object> desactivate(@RequestBody Usuario usuario) {
        try {
            this.usuarioBusiness.desactivate(usuario);
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
    public ResponseEntity<Object> findT(Usuario p) {
        try {
            Usuario usuario = this.usuarioBusiness.findT(p);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
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
    public ResponseEntity<Object> findAllT(Usuario p) {
        try {
            List<Usuario> list = this.usuarioBusiness.findAllT(p);
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
