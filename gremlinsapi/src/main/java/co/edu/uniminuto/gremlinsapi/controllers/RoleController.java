package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.List;
import java.util.logging.Logger;

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

import co.edu.uniminuto.gremlinsapi.business.RoleBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Role;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/roles")
public class RoleController implements IController<Role> {

    private final RoleBusiness roleBusiness;
    private static final Logger LOGGER = Logger.getLogger(RoleController.class.getName());

    @Autowired
    public RoleController(RoleBusiness roleBusiness) {
        this.roleBusiness = roleBusiness;
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<Object> getAll() {
        try {
            List<Role> roles = roleBusiness.getAll();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<Object> save(@RequestBody Role role) {
        try {
            roleBusiness.save(role);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }

    @PutMapping("/")
    @Override
    public ResponseEntity<Object> edith(@RequestBody Role role) {
        try {
            roleBusiness.edith(role);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }

    @PostMapping("/findT")
    @Override
    public ResponseEntity<Object> findT(@RequestBody Role role) {
        try {
            Role foundRole = roleBusiness.findT(role);
            return new ResponseEntity<>(foundRole, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }

    @PostMapping("/findAllT")
    @Override
    public ResponseEntity<Object> findAllT(@RequestBody Role role) {
        try {
            List<Role> roles = roleBusiness.findAllT(role);
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }

    @PostMapping("/delete")
    @Override
    public ResponseEntity<Object> delete(@RequestBody Role role) {
        try {
            roleBusiness.delete(role);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }

    @PostMapping("/desactivate")
    @Override
    public ResponseEntity<Object> desactivate(@RequestBody Role role) {
        try {
            roleBusiness.desactivate(role);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());
        }
    }
}
