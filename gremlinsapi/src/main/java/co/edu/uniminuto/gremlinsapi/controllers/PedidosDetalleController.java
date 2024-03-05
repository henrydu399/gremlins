package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.List;

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

import co.edu.uniminuto.gremlinsapi.business.PedidosDetalleBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.PedidosDetalle;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/pedidosDetalles")
public class PedidosDetalleController implements IController<PedidosDetalle> {

    private final PedidosDetalleBusiness pedidosDetalleBusiness;

    @Autowired
    public PedidosDetalleController(PedidosDetalleBusiness pedidosDetalleBusiness) {
        this.pedidosDetalleBusiness = pedidosDetalleBusiness;
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<Object> getAll() {
        try {
            return new ResponseEntity<>(pedidosDetalleBusiness.getAll(), HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @Override
    public ResponseEntity<Object> save(@RequestBody PedidosDetalle detallePedido) {
        try {
            pedidosDetalleBusiness.save(detallePedido);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edith")
    @Override
    public ResponseEntity<Object> edith(@RequestBody PedidosDetalle detallePedido) {
        try {
            pedidosDetalleBusiness.edith(detallePedido);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/findT")
    @Override
    public ResponseEntity<Object> findT(@RequestBody PedidosDetalle detallePedido) {
        try {
            PedidosDetalle foundDetalle = pedidosDetalleBusiness.findT(detallePedido);
            return new ResponseEntity<>(foundDetalle, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/findAllT")
    @Override
    public ResponseEntity<Object> findAllT(@RequestBody PedidosDetalle detallePedido) {
        try {
            List<PedidosDetalle> foundDetalles = pedidosDetalleBusiness.findAllT(detallePedido);
            return new ResponseEntity<>(foundDetalles, HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete")
    @Override
    public ResponseEntity<Object> delete(@RequestBody PedidosDetalle detallePedido) {
        try {
            pedidosDetalleBusiness.delete(detallePedido);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/desactivate")
    @Override
    public ResponseEntity<Object> desactivate(@RequestBody PedidosDetalle detallePedido) {
        try {
            pedidosDetalleBusiness.desactivate(detallePedido);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GeneralException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
