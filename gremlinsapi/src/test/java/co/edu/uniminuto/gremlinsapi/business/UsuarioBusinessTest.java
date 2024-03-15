package co.edu.uniminuto.gremlinsapi.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.UsuarioRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioBusinessTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioBusiness usuarioBusiness;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfUsuarios_WhenUsuariosExist() throws GeneralException {
        // Arrange
        List<Usuario> usuarios = new ArrayList<>();
        Usuario u1 = Usuario.builder()
        		.nombre("Usuario Prueba")
        		.email("prueba@gmail.com")
        		.build();
        usuarios.add(u1);
        Usuario u2 = Usuario.builder()
        		.nombre("Usuario Prueba2")
        		.email("prueba2@gmail.com")
        		.build();
        usuarios.add(u2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = usuarioBusiness.getAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void save_ThrowsException_WhenUsuarioWithSameEmailExists() {
        // Arrange
    	Usuario u1 = Usuario.builder()
        		.nombre("Usuario Prueba")
        		.email("prueba@gmail.com")
        		.build();
        

        when(usuarioRepository.findByEmail(u1.getEmail())).thenReturn(u1);
        // Act & Assert
        assertThrows(GeneralException.class, () -> usuarioBusiness.save(u1));
    }

    
    @Test
    void edith_UpdatesUsuario_WhenUsuarioExists() throws GeneralException {
        // Arrange

    	Usuario usuarioToUpdate = Usuario.builder()
        		.nombre("Usuario Prueba")
        		.email("prueba@gmail.com")
        		.build();
        
        when(usuarioRepository.findById(usuarioToUpdate.getId())).thenReturn(Optional.of(usuarioToUpdate));

        // Act
        usuarioBusiness.edith(usuarioToUpdate);

        // Assert
        verify(usuarioRepository, times(1)).save(usuarioToUpdate);
    }

    @Test
    void edith_ThrowsException_WhenUsuarioDoesNotExist() {
    	
    	Usuario usuarioToUpdate = Usuario.builder()
        		.nombre("Usuario Prueba2")
        		.email("prueba@gmail.com")
        		.build();
    	
    	Usuario nonExistingUsuario = Usuario.builder()
        		.nombre("Usuario Prueba")
        		.email("prueba@gmail.com")
        		.build();
        // Arrange

        when(usuarioRepository.findById(nonExistingUsuario.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GeneralException.class, () -> usuarioBusiness.edith(nonExistingUsuario));
    }

    @Test
    void delete_DeletesUsuario_WhenUsuarioExists() throws GeneralException {
        // Arrange

    	Usuario usuarioToDelete = Usuario.builder()
        		.nombre("Usuario Prueba2")
        		.email("prueba@gmail.com")
        		.build();
    	
        when(usuarioRepository.findById(usuarioToDelete.getId())).thenReturn(Optional.of(usuarioToDelete));

        // Act
        usuarioBusiness.delete(usuarioToDelete);

        // Assert
        verify(usuarioRepository, times(1)).delete(usuarioToDelete);
    }

  
    // Similar tests for other methods like edith, delete, desactivate, findT, findAllT, etc.
}
