package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.gremlinsapi.entitys.Role;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.RoleRepository;

@Service
public class RoleBusiness implements ILogic<Role> {

    private final RoleRepository roleRepository;
    private static final Logger LOGGER = Logger.getLogger(RoleBusiness.class.getName());

    @Autowired
    public RoleBusiness(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() throws GeneralException {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en getAll: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todos los roles", e);
        }
    }

    @Override
    public void save(Role role) throws GeneralException {
        try {
            roleRepository.save(role);
        } catch (Exception e) {
            LOGGER.severe("Error en save: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el rol", e);
        }
    }

    @Override
    public void edith(Role role) throws GeneralException {
        try {
            Optional<Role> optionalRole = roleRepository.findById(role.getId());
            if (!optionalRole.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El rol a modificar no existe", null);
            }
            roleRepository.save(role);
        } catch (Exception e) {
            LOGGER.severe("Error en edith: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al editar el rol", e);
        }
    }

    @Override
    public Role findT(Role role) throws GeneralException {
        try {
            Optional<Role> optionalRole = roleRepository.findById(role.getId());
            if (optionalRole.isPresent()) {
                return optionalRole.get();
            }
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error en findT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar el rol", e);
        }
    }

    @Override
    public List<Role> findAllT(Role role) throws GeneralException {
        try {
            // Aquí puedes implementar la lógica para buscar por algún criterio específico si es necesario
            return roleRepository.findAll();
        } catch (Exception e) {
            LOGGER.severe("Error en findAllT: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar todos los roles", e);
        }
    }

    @Override
    public void delete(Role role) throws GeneralException {
        try {
            Optional<Role> optionalRole = roleRepository.findById(role.getId());
            if (!optionalRole.isPresent()) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El rol a eliminar no existe", null);
            }
            roleRepository.delete(role);
        } catch (Exception e) {
            LOGGER.severe("Error en delete: " + e.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el rol", e);
        }
    }

    @Override
    public void desactivate(Role role) throws GeneralException {
        // Aquí puedes implementar la lógica para desactivar un rol si es necesario
        throw new UnsupportedOperationException("Desactivación de roles no implementada");
    }
}

