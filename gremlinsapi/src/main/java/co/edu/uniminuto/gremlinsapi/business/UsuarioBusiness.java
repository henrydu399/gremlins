package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.constans.ErrorContanst;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.enums.Estados;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.UsuarioRepository;

@Service
public class UsuarioBusiness implements ILogic<Usuario> {

    private static final Logger LOGGER = Logger.getLogger(UsuarioBusiness.class.getName());
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioBusiness(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAll() throws GeneralException {
        try {
            return this.usuarioRepository.findAll();
        } catch (Exception e) {
        	if( e instanceof GeneralException) {
        		throw e;
        	}
            LOGGER.severe(ErrorContanst.READ_ERROR + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.READ_ERROR, e);
        }
    }

    @Override
    public void save(Usuario u) throws GeneralException {
        try {  	
        	Usuario user = this.usuarioRepository.findByEmail(u.getEmail());	
        	if ( Objects.nonNull(user)) {
        		throw new GeneralException(HttpStatus.BAD_REQUEST, ErrorContanst.USUARIO_EXISTENTE_CON_EMAIL, null);
        	}   	
            this.usuarioRepository.save(u);
        } catch (Exception e) {
        	if( e instanceof GeneralException) {
        		throw e;
        	}
            LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE, e);
        }
    }

    @Override
    public void edith(Usuario p) throws GeneralException {
        try {
            Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(p.getId());
            if (!optionalUsuario.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.USUARIO_A_MODIFICAR_NO_EXISTE, null);
            }
            this.usuarioRepository.save(p);
        } catch (Exception e) {
        	if( e instanceof GeneralException) {
        		throw e;
        	}
            LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE, e);
        }
    }

    @Override
    public void delete(Usuario p) throws GeneralException {
        try {
            Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(p.getId());
            if (!optionalUsuario.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.USUARIO_A_MODIFICAR_NO_EXISTE, null);
            }
            this.usuarioRepository.delete(p);
        } catch (Exception e) {
        	if( e instanceof GeneralException) {
        		throw e;
        	}
            LOGGER.severe(ErrorContanst.DELETE_ERROR + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.DELETE_ERROR, e);
        }
    }

    @Override
    public void desactivate(Usuario usuario) throws GeneralException {
        try {
            Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(usuario.getId());
            if (optionalUsuario.isPresent()) {
                usuario.setEstado(Estados.DESACTIVADO.name()  );
                this.usuarioRepository.save(usuario);
            } else {
            	
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.USUARIO_A_MODIFICAR_NO_EXISTE, null);
            }
        } catch (Exception e) {
        	if( e instanceof GeneralException) {
        		throw e;
        	}
            LOGGER.severe(ErrorContanst.DELETE_ERROR + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.DELETE_ERROR, e);
        }
    }

    @Override
    public Usuario findT(Usuario p) throws GeneralException {
        try {
            Optional<Usuario> usuario = this.usuarioRepository.findById(p.getId());
            return usuario.orElse(null);
        } catch (Exception e) {
            LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE, e);
        }
    }

    @Override
    public List<Usuario> findAllT(Usuario p) throws GeneralException {
        try {
            // Implementa tu lógica de búsqueda personalizada aquí si es necesario
            return null;
        } catch (Exception e) {
        	if( e instanceof GeneralException) {
        		throw e;
        	}
            LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE, e);
        }
    }
}
