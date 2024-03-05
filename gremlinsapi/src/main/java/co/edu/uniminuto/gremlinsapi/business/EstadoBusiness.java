package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.entitys.Estado;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.EstadoRepository;

@Service
public class EstadoBusiness implements ILogic<Estado> {

    private final EstadoRepository estadoRepository;
    private static final Logger LOGGER = Logger.getLogger(EstadoBusiness.class.getName());

    @Autowired
    public EstadoBusiness(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Estado> getAll() throws GeneralException {
        try {
            return estadoRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los estados", e);
        }
    }

    @Override
    public void save(Estado estado) throws GeneralException {
        try {
            estadoRepository.save(estado);
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el estado", e);
        }
    }

    @Override
    public void edith(Estado estado) throws GeneralException {
        try {
            estadoRepository.save(estado);
        } catch (Exception e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el estado", e);
        }
    }

    @Override
    public Estado findT(Estado estado) throws GeneralException {
        try {
            return estadoRepository.findById(estado.getEsNombre()).orElse(null);
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el estado", e);
        }
    }

    @Override
    public List<Estado> findAllT(Estado estado) throws GeneralException {
        // Implementa lógica específica de búsqueda si es necesario
        return getAll();
    }

    @Override
    public void delete(Estado estado) throws GeneralException {
        try {
            estadoRepository.delete(estado);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el estado", e);
        }
    }

    @Override
    public void desactivate(Estado estado) throws GeneralException {
        // Implementa lógica específica de desactivación si es necesario
        throw new UnsupportedOperationException("Desactivación de estados no implementada");
    }
}

