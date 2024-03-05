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

import co.edu.uniminuto.gremlinsapi.entitys.Distribuidore;
import co.edu.uniminuto.gremlinsapi.enums.Estados;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.DistribuidorRepository;

@Service
public class DistribuidoreBusiness implements ILogic<Distribuidore> {

    private final DistribuidorRepository distribuidorRepository;
    private static final Logger LOGGER = Logger.getLogger(DistribuidoreBusiness.class.getName());

    @Autowired
    private Validator validator;

    @Autowired
    public DistribuidoreBusiness(DistribuidorRepository distribuidorRepository) {
        this.distribuidorRepository = distribuidorRepository;
    }

    @Override
    public List<Distribuidore> getAll() throws GeneralException {
        try {
            return distribuidorRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los distribuidores", e);
        }
    }

    @Override
    public void save(Distribuidore distribuidor) throws GeneralException {
        try {
            validateDistribuidor(distribuidor);
            distribuidor.setDCreado(new Timestamp(System.currentTimeMillis()));
            distribuidorRepository.save(distribuidor);
        } catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el distribuidor", e);
        }
    }

    @Override
    public void edith(Distribuidore distribuidor) throws GeneralException {
        try {
            validateDistribuidor(distribuidor);
            distribuidor.setDModificado(new Timestamp(System.currentTimeMillis()));
            Optional<Distribuidore> optionalDistribuidor = distribuidorRepository.findById(distribuidor.getDId());
            if (!optionalDistribuidor.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El distribuidor a modificar no existe", null);
            }
            distribuidorRepository.save(distribuidor);
        } catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el distribuidor", e);
        }
    }

    @Override
    public Distribuidore findT(Distribuidore distribuidor) throws GeneralException {
        try {
            Optional<Distribuidore> optionalDistribuidor = distribuidorRepository.findById(distribuidor.getDId());
            if (optionalDistribuidor.isPresent()) {
                return optionalDistribuidor.get();
            }
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el distribuidor", e);
        }
    }

    @Override
    public List<Distribuidore> findAllT(Distribuidore distribuidor) throws GeneralException {
        try {
            // Aquí puedes implementar la lógica para buscar por algún criterio específico si es necesario
            return distribuidorRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en findAllT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar todos los distribuidores", e);
        }
    }

    @Override
    public void delete(Distribuidore distribuidor) throws GeneralException {
        try {
            Optional<Distribuidore> optionalDistribuidor = distribuidorRepository.findById(distribuidor.getDId());
            if (!optionalDistribuidor.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El distribuidor a eliminar no existe", null);
            }
            distribuidorRepository.delete(distribuidor);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el distribuidor", e);
        }
    }

    @Override
    public void desactivate(Distribuidore distribuidor) throws GeneralException {
        try {
            Optional<Distribuidore> optionalDistribuidor = distribuidorRepository.findById(distribuidor.getDId());
            if (!optionalDistribuidor.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El distribuidor a eliminar no existe", null);
            }
            optionalDistribuidor.get().setDEstado(Estados.DESACTIVADO.name());
            this.distribuidorRepository.save(optionalDistribuidor.get());
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el distribuidor", e);
        }
    }

    private void validateDistribuidor(Distribuidore distribuidor) throws GeneralException {
        Set<ConstraintViolation<Distribuidore>> violations = validator.validate(distribuidor);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Distribuidore> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new GeneralException(HttpStatus.BAD_REQUEST, errorMessage.toString(), null);
        }
    }
}
