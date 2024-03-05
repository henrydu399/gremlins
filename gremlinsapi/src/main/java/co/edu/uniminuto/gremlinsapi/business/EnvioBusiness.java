package co.edu.uniminuto.gremlinsapi.business;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.constans.ErrorContanst;
import co.edu.uniminuto.gremlinsapi.entitys.Envio;
import co.edu.uniminuto.gremlinsapi.entitys.Estado;
import co.edu.uniminuto.gremlinsapi.enums.Estados;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.EnvioRepository;
import co.edu.uniminuto.gremlinsapi.repositorys.EstadoRepository;

@Service
public class EnvioBusiness implements ILogic<Envio> {

    private final EnvioRepository envioRepository;
    private static final Logger LOGGER = Logger.getLogger(EnvioBusiness.class.getName());
    
    private final EstadoRepository estadoRepository;

    @Autowired
    public EnvioBusiness(EnvioRepository envioRepository , EstadoRepository estadoRepository) {
        this.envioRepository = envioRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Envio> getAll() throws GeneralException {
        try {
            return envioRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los envíos", e);
        }
    }

    @Override
    public void save(Envio envio) throws GeneralException {
        try {
            envio.setECreado(new Timestamp(System.currentTimeMillis()));
            envioRepository.save(envio);
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el envío", e);
        }
    }

    @Override
    public void edith(Envio envio) throws GeneralException {
        try {
            Optional<Envio> optionalEnvio = envioRepository.findById(envio.getId());
            if (!optionalEnvio.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El envío a modificar no existe", null);
            }
            envio.setEModificado(new Timestamp(System.currentTimeMillis()));
            envioRepository.save(envio);
        } catch (Exception e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el envío", e);
        }
    }

    @Override
    public Envio findT(Envio envio) throws GeneralException {
        try {
            Optional<Envio> optionalEnvio = envioRepository.findById(envio.getId());
            return optionalEnvio.orElse(null);
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el envío", e);
        }
    }

    @Override
    public List<Envio> findAllT(Envio envio) throws GeneralException {
        // Implementa lógica específica de búsqueda si es necesario
        return getAll();
    }

    @Override
    public void delete(Envio envio) throws GeneralException {
        try {
            envioRepository.delete(envio);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el envío", e);
        }
    }

    @Override
    public void desactivate(Envio envio) throws GeneralException {
    	try {
			Optional<Envio> optionalEnvio = this.envioRepository.findById(envio.getId());
			 if (!optionalEnvio.isPresent()) {
	                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El envío a modificar no existe", null);
	            }
			 
			 Optional<Estado> estado =  estadoRepository.findById(Estados.DESACTIVADO.name());			 
			 optionalEnvio.get().setEstado(estado.get());
			 this.envioRepository.save(optionalEnvio.get());
		}catch (Exception e) {
			LOGGER.severe(ErrorContanst.DELETE_ERROR + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.DELETE_ERROR , e);
		}
    }
}
