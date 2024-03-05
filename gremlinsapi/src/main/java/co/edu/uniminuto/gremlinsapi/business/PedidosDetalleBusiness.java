package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.entitys.PedidosDetalle;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.PedidosDetalleRepository;

@Service
public class PedidosDetalleBusiness implements ILogic<PedidosDetalle> {

    private static final Logger LOGGER = Logger.getLogger(PedidosDetalleBusiness.class.getName());
    private final PedidosDetalleRepository pedidosDetalleRepository;

    @Autowired
    public PedidosDetalleBusiness(PedidosDetalleRepository pedidosDetalleRepository) {
        this.pedidosDetalleRepository = pedidosDetalleRepository;
    }

    @Override
    public List<PedidosDetalle> getAll() throws GeneralException {
        try {
            return this.pedidosDetalleRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error al obtener todos los detalles del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los detalles del pedido", e);
        }
    }

    @Override
    public void save(PedidosDetalle pedidosDetalle) throws GeneralException {
        try {
            this.pedidosDetalleRepository.save(pedidosDetalle);
        } catch (Exception e) {
            LOGGER.severe("Error al guardar el detalle del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el detalle del pedido", e);
        }
    }

    @Override
    public void edith(PedidosDetalle pedidosDetalle) throws GeneralException {
        try {
            // Puedes implementar la edición según tus necesidades
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Método no implementado", null);
        } catch (Exception e) {
            LOGGER.severe("Error al editar el detalle del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el detalle del pedido", e);
        }
    }

    @Override
    public void delete(PedidosDetalle pedidosDetalle) throws GeneralException {
        try {
            this.pedidosDetalleRepository.delete(pedidosDetalle);
        } catch (Exception e) {
            LOGGER.severe("Error al eliminar el detalle del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el detalle del pedido", e);
        }
    }

    @Override
    public void desactivate(PedidosDetalle pedidosDetalle) throws GeneralException {
        try {
            // Puedes implementar la desactivación según tus necesidades
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Método no implementado", null);
        } catch (Exception e) {
            LOGGER.severe("Error al desactivar el detalle del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al desactivar el detalle del pedido", e);
        }
    }

    @Override
    public PedidosDetalle findT(PedidosDetalle pedidosDetalle) throws GeneralException {
        try {
            Optional<PedidosDetalle> optionalPedidosDetalle = this.pedidosDetalleRepository.findById(pedidosDetalle.getId());
            return optionalPedidosDetalle.orElse(null);
        } catch (Exception e) {
            LOGGER.severe("Error al buscar el detalle del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el detalle del pedido", e);
        }
    }

    @Override
    public List<PedidosDetalle> findAllT(PedidosDetalle pedidosDetalle) throws GeneralException {
        try {
            // No estoy seguro si esta funcionalidad está disponible en tu repositorio, asegúrate de verificarlo
            return this.pedidosDetalleRepository.findAll(); // Deberías ajustar esto para hacer una búsqueda específica según tus necesidades
        } catch (Exception e) {
            LOGGER.severe("Error al buscar todos los detalles del pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar todos los detalles del pedido", e);
        }
    }
}
