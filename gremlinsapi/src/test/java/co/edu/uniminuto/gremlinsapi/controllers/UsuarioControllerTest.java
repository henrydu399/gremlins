package co.edu.uniminuto.gremlinsapi.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.uniminuto.gremlinsapi.business.UsuarioBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioBusiness usuarioBusiness;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario();
        
        usuario.setNombre("Usuario de prueba");
        usuario.setEmail("henryduarte399@gmail.com");
        usuario.setPassword("12345678");
        // Define cualquier configuración adicional necesaria para las pruebas
    }

    @Test
    public void testGetAll() throws GeneralException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        when(usuarioBusiness.getAll()).thenReturn(usuarios);
        ResponseEntity<Object> responseEntity = usuarioController.getAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuarios, responseEntity.getBody());
    }

    // Escribe pruebas similares para los demás métodos del controlador

    @Test
    public void testSave() throws GeneralException {
        doNothing().when(usuarioBusiness).save(usuario);
        ResponseEntity<Object> responseEntity = usuarioController.save(usuario);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testEdith() throws GeneralException {
        doNothing().when(usuarioBusiness).edith(usuario);

        ResponseEntity<Object> responseEntity = usuarioController.edith(usuario);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testDelete() throws GeneralException {
        doNothing().when(usuarioBusiness).delete(usuario);

        ResponseEntity<Object> responseEntity = usuarioController.delete(usuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testDesactivate() throws GeneralException {
        doNothing().when(usuarioBusiness).desactivate(usuario);

        ResponseEntity<Object> responseEntity = usuarioController.desactivate(usuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testFindT() throws GeneralException {
        when(usuarioBusiness.findT(usuario)).thenReturn(usuario);

        ResponseEntity<Object> responseEntity = usuarioController.findT(usuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    public void testFindAllT() throws GeneralException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        when(usuarioBusiness.findAllT(usuario)).thenReturn(usuarios);

        ResponseEntity<Object> responseEntity = usuarioController.findAllT(usuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuarios, responseEntity.getBody());
    }
}
