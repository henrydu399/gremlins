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

import co.edu.uniminuto.gremlinsapi.business.PedidoBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Pedido;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/pedidos")
public class PedidoController implements IController<Pedido> {

    private final PedidoBusiness pedidoBusiness;

    private static final Logger LOGGER = Logger.getLogger(PedidoController.class.getName());

    @Autowired
    public PedidoController(PedidoBusiness pedidoBusiness) {
        this.pedidoBusiness = pedidoBusiness;
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<Object> getAll() {
        try {
            List<Pedido> pedidos = this.pedidoBusiness.getAll();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error al obtener todos los pedidos: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> save(@RequestBody Pedido pedido) {
        try {
            this.pedidoBusiness.save(pedido);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (GeneralException e) {
            LOGGER.severe("Error al guardar el pedido: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> edith(@RequestBody Pedido pedido) {
        try {
            this.pedidoBusiness.edith(pedido);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (GeneralException e) {
            LOGGER.severe("Error al editar el pedido: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/findT/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> findT(@RequestBody Pedido pedido) {
        try {
            Pedido encontrado = this.pedidoBusiness.findT(pedido);
            return new ResponseEntity<>(encontrado, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error al buscar el pedido: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/findAllT/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> findAllT(@RequestBody Pedido pedido) {
        try {
            List<Pedido> pedidos = this.pedidoBusiness.findAllT(pedido);
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error al buscar todos los pedidos: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/delete/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> delete(@RequestBody Pedido pedido) {
        try {
            this.pedidoBusiness.delete(pedido);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error al eliminar el pedido: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/desactivate/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> desactivate(@RequestBody Pedido pedido) {
        try {
            this.pedidoBusiness.desactivate(pedido);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (GeneralException e) {
            LOGGER.severe("Error al desactivar el pedido: " + e.getMessage());
            return new ResponseEntity<>(e.getMessageLogical(), HttpStatus.BAD_REQUEST);
        }
    }
}
