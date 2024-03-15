package co.edu.uniminuto.gremlinsapi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



import co.edu.uniminuto.gremlinsapi.constans.ErrorContanst;
import co.edu.uniminuto.gremlinsapi.entitys.Cliente;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.enums.Estados;
import co.edu.uniminuto.gremlinsapi.enums.RolEnum;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.ClienteRepository;
import co.edu.uniminuto.gremlinsapi.repositorys.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClienteBusiness implements ILogic<Cliente> {


    private static final Logger LOGGER = Logger.getLogger(ClienteBusiness.class.getName());
	private final ClienteRepository clienteRepository;
	
	@Autowired private UsuarioRepository usuarioRepository;
    

    @Autowired
    public ClienteBusiness(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

	@Override
	public List<Cliente> getAll() throws GeneralException {
		try {
			return this.clienteRepository.findAll();
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.READ_ERROR + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.READ_ERROR , e);
		}
	}

	@Override
	public void save(Cliente c) throws GeneralException {
		try {
			
			Optional<Usuario> user =  usuarioRepository.findById(c.getCUsuario().getId());	
			if(user.isPresent()){
				if( !user.get().getRole().getId().getRNombre().equals(RolEnum.CLIENTE.name())) {
					throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "LOS CLIENTES SOLO PUEDEN ESTAR ASOCIADOS A USUARIOS DE ROL CLIENTE" , null);
				}
			}
			
			
			
			 this.clienteRepository.save(c);
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE , e);
		}
		
	}

	@Override
	public void edith(Cliente c) throws GeneralException {
		try {
			Optional<Cliente> optionalCliente = this.clienteRepository.findById(c.getCId());
			if(!optionalCliente.isPresent()) {
				throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CLIENTE_A_MODIFICAR_NO_EXISTE , null);
			}
			
			Optional<Usuario> user =  usuarioRepository.findById(c.getCUsuario().getId());
			
			if(user.isPresent()){
				if( !user.get().getRole().getId().getRNombre().equals(RolEnum.CLIENTE.name())) {
					throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "LOS CLIENTES SOLO PUEDEN ESTAR ASOCIADOS A USUARIOS DE ROL CLIENTE" , null);
				}
			}
			
			 this.clienteRepository.save(c);
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE , e);
		}
		
	}


	@Override
	public void delete(Cliente c) throws GeneralException {
		try {
			Optional<Cliente> optionalCliente = this.clienteRepository.findById(c.getCId());
			if(!optionalCliente.isPresent()) {
				throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CLIENTE_A_MODIFICAR_NO_EXISTE , null);
			}
			 this.clienteRepository.delete(c);;
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.DELETE_ERROR + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.DELETE_ERROR , e);
		}
		
		
	}

	@Override
	public void desactivate(Cliente c) throws GeneralException {
		try {
			Optional<Cliente> optionalCliente = this.clienteRepository.findById(c.getCId());
			if(!optionalCliente.isPresent()) {
				c.setCEstado(Estados.DESACTIVADO.name());
			}
			 this.clienteRepository.save(c);
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.DELETE_ERROR + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.DELETE_ERROR , e);
		}
		
	}

	@Override
	public Cliente findT(Cliente p) throws GeneralException {
		try {
			Optional<Cliente> c =  this.clienteRepository.findT(p);
			if(c.isPresent()) {
				return c.get();
			}
			
			return null;
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE , e);
		}
	}

	@Override
	public List<Cliente> findAllT(Cliente p) throws GeneralException {
		try {
			 return this.clienteRepository.findAllT(p);
		}catch (Exception e) {
			if( e instanceof GeneralException) {
        		throw e;
        	}
			LOGGER.severe(ErrorContanst.CREATE_SAVE + " " + e.getMessage());
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorContanst.CREATE_SAVE , e);
		}
	}


  
}
