package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.entitys.Funcione;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.FuncioneRepository;

@Service
public class FuncioneBusiness implements ILogic<Funcione> {

    private final FuncioneRepository funcioneRepository;
    private static final Logger LOGGER = Logger.getLogger(FuncioneBusiness.class.getName());

    @Autowired
    public FuncioneBusiness(FuncioneRepository funcioneRepository) {
        this.funcioneRepository = funcioneRepository;
    }

    @Override
    public List<Funcione> getAll() throws GeneralException {
        try {
            return funcioneRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todas las funciones", e);
        }
    }

    @Override
    public void save(Funcione funcione) throws GeneralException {
        try {
            funcioneRepository.save(funcione);
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar la función", e);
        }
    }

    @Override
    public void edith(Funcione funcione) throws GeneralException {
        try {
            funcioneRepository.save(funcione);
        } catch (Exception e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar la función", e);
        }
    }

    @Override
    public Funcione findT(Funcione funcione) throws GeneralException {
        try {
            Optional<Funcione> optionalFuncione = funcioneRepository.findById(funcione.getId());
            return optionalFuncione.orElse(null);
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar la función", e);
        }
    }

    @Override
    public List<Funcione> findAllT(Funcione funcione) throws GeneralException {
        // Implementa lógica específica de búsqueda si es necesario
        return getAll();
    }

    @Override
    public void delete(Funcione funcione) throws GeneralException {
        try {
            funcioneRepository.delete(funcione);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la función", e);
        }
    }

    @Override
    public void desactivate(Funcione funcione) throws GeneralException {
        // Implementa lógica específica de desactivación si es necesario
        throw new UnsupportedOperationException("Desactivación de funciones no implementada");
    }
}
