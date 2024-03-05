package co.edu.uniminuto.gremlinsapi.business;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.entitys.Producto;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.IProductoRepository;

@Service
public class ProductoBusiness implements ILogic<Producto> {

    private final IProductoRepository productoRepository;
    private static final Logger LOGGER = Logger.getLogger(ProductoBusiness.class.getName());

    @Autowired
    private Validator validator;

    @Autowired
    public ProductoBusiness(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAll() throws GeneralException {
        try {
            return productoRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los productos", e);
        }
    }

    @Override
    public void save(Producto producto) throws GeneralException {
        try {
            validateProducto(producto);
            producto.setPCreado(new Timestamp(System.currentTimeMillis()));
            productoRepository.save(producto);
        } catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el producto", e);
        }
    }

    @Override
    public void edith(Producto producto) throws GeneralException {
        try {
            validateProducto(producto);
            producto.setPModificado(String.valueOf(System.currentTimeMillis()));
            Optional<Producto> optionalProducto = productoRepository.findById(producto.getPId());
            if (!optionalProducto.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El producto a modificar no existe", null);
            }
            productoRepository.save(producto);
        } catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el producto", e);
        }
    }

    @Override
    public Producto findT(Producto producto) throws GeneralException {
        try {
            Optional<Producto> optionalProducto = productoRepository.findById(producto.getPId());
            if (optionalProducto.isPresent()) {
                return optionalProducto.get();
            }
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el producto", e);
        }
    }

    @Override
    public List<Producto> findAllT(Producto producto) throws GeneralException {
        try {
            // Aquí puedes implementar la lógica para buscar por algún criterio específico si es necesario
            return productoRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en findAllT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar todos los productos", e);
        }
    }

    @Override
    public void delete(Producto producto) throws GeneralException {
        try {
            Optional<Producto> optionalProducto = productoRepository.findById(producto.getPId());
            if (!optionalProducto.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El producto a eliminar no existe", null);
            }
            productoRepository.delete(producto);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el producto", e);
        }
    }

    @Override
    public void desactivate(Producto producto) throws GeneralException {
        // Aquí puedes implementar la lógica para desactivar un producto si es necesario
        throw new UnsupportedOperationException("Desactivación de productos no implementada");
    }

    private void validateProducto(Producto producto) throws GeneralException {
        Set<ConstraintViolation<Producto>> violations = validator.validate(producto);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Producto> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new GeneralException(HttpStatus.BAD_REQUEST, errorMessage.toString(), null);
        }
    }
}

