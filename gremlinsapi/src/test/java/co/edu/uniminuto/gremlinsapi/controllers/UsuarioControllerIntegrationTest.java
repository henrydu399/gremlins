package co.edu.uniminuto.gremlinsapi.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.uniminuto.gremlinsapi.Application;
import co.edu.uniminuto.gremlinsapi.business.UsuarioBusiness;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioControllerIntegrationTest {

  

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:7080/gremlinsapi/usuarios";
        // Inicializa la base de datos o configura el estado necesario para las pruebas
    }

    @Test
    public void testGetAll() throws Exception {
        // Llama al método real de getAll() en usuarioBusiness
        List<Usuario> usuarios = usuarioBusiness.getAll();

        // Realiza una solicitud HTTP GET al endpoint del controlador
        ResponseEntity<Usuario[]> responseEntity = restTemplate.getForEntity(baseUrl + "/getAll", Usuario[].class);
        List<Usuario> usuarios2 = Arrays.asList(responseEntity.getBody());
        // Verifica que la respuesta HTTP tenga el código de estado esperado y los datos esperados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
 
        assertEquals(usuarios.size(),usuarios2.size()  );
    }

    // Escribe pruebas similares para los demás métodos del controlador

}
