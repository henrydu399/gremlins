package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.entitys.Pedido;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.PedidoRepository;

@Service
public class PedidoBusiness implements ILogic<Pedido> {

    private static final Logger LOGGER = Logger.getLogger(PedidoBusiness.class.getName());
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoBusiness(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> getAll() throws GeneralException {
        try {
            return this.pedidoRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error al obtener todos los pedidos: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los pedidos", e);
        }
    }

    @Override
    public void save(Pedido pedido) throws GeneralException {
        try {
            this.pedidoRepository.save(pedido);
        } catch (Exception e) {
            LOGGER.severe("Error al guardar el pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el pedido", e);
        }
    }

    @Override
    public void edith(Pedido pedido) throws GeneralException {
        try {
            Optional<Pedido> optionalPedido = this.pedidoRepository.findById(pedido.getPId());
            if (!optionalPedido.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El pedido a modificar no existe", null);
            }
            this.pedidoRepository.save(pedido);
        } catch (Exception e) {
            LOGGER.severe("Error al editar el pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el pedido", e);
        }
    }

    @Override
    public void delete(Pedido pedido) throws GeneralException {
        try {
            Optional<Pedido> optionalPedido = this.pedidoRepository.findById(pedido.getPId());
            if (!optionalPedido.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El pedido a eliminar no existe", null);
            }
            this.pedidoRepository.delete(pedido);
        } catch (Exception e) {
            LOGGER.severe("Error al eliminar el pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el pedido", e);
        }
    }

    @Override
    public void desactivate(Pedido pedido) throws GeneralException {
        try {
            Optional<Pedido> optionalPedido = this.pedidoRepository.findById(pedido.getPId());
            if (!optionalPedido.isPresent()) {
                // Aquí puedes ajustar el mensaje según tus necesidades
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El pedido a desactivar no existe", null);
            }
            this.pedidoRepository.save(pedido);
        } catch (Exception e) {
            LOGGER.severe("Error al desactivar el pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al desactivar el pedido", e);
        }
    }

    @Override
    public Pedido findT(Pedido pedido) throws GeneralException {
        try {
            Optional<Pedido> optionalPedido = this.pedidoRepository.findById(pedido.getPId());
            return optionalPedido.orElse(null);
        } catch (Exception e) {
            LOGGER.severe("Error al buscar el pedido: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el pedido", e);
        }
    }

    @Override
    public List<Pedido> findAllT(Pedido pedido) throws GeneralException {
        try {
            // No estoy seguro si esta funcionalidad está disponible en tu repositorio, asegúrate de verificarlo
            return this.pedidoRepository.findAll(); // Deberías ajustar esto para hacer una búsqueda específica según tus necesidades
        } catch (Exception e) {
            LOGGER.severe("Error al buscar todos los pedidos: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar todos los pedidos", e);
        }
    }
}
