package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.business.ProductoBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Producto;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController implements IController<Producto> {

    private final ProductoBusiness productoBusiness;
    private static final Logger LOGGER = Logger.getLogger(ProductoController.class.getName());

    @Autowired
    public ProductoController(ProductoBusiness productoBusiness) {
        this.productoBusiness = productoBusiness;
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<Object> getAll() {
        try {
            List<Producto> productos = productoBusiness.getAll();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> save(@RequestBody Producto producto) {
        try {
            productoBusiness.save(producto);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (GeneralException e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> edith(@RequestBody Producto producto) {
        try {
            productoBusiness.edith(producto);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (GeneralException e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/findT/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> findT(@RequestBody Producto producto) {
        try {
            Producto foundProducto = productoBusiness.findT(producto);
            return new ResponseEntity<>(foundProducto, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/findAllT/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> findAllT(@RequestBody Producto producto) {
        try {
            List<Producto> productos = productoBusiness.findAllT(producto);
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error en findAllT: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/delete/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> delete(@RequestBody Producto producto) {
        try {
            productoBusiness.delete(producto);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/desactivate/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> desactivate(@RequestBody Producto producto) {
        try {
            productoBusiness.desactivate(producto);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error en desactivate: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
