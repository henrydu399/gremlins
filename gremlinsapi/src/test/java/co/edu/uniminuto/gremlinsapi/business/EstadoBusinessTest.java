package co.edu.uniminuto.gremlinsapi.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.uniminuto.gremlinsapi.entitys.Estado;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.repositorys.EstadoRepository;

class EstadoBusinessTest {

    @Mock
    private EstadoRepository estadoRepository;

    @InjectMocks
    private EstadoBusiness estadoBusiness;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfEstados_WhenEstadosExist() throws GeneralException {
        // Arrange
        List<Estado> estados = new ArrayList<>();
        estados.add(Estado.builder().esNombre("Activo").build());
        estados.add(Estado.builder().esNombre("Inactivo").build());

        when(estadoRepository.findAll()).thenReturn(estados);

        // Act
        List<Estado> result = estadoBusiness.getAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void save_CallsSaveMethodOfEstadoRepository_WhenEstadoIsSaved() throws GeneralException {
        // Arrange
        Estado estado = Estado.builder().esNombre("Activo").build();

        // Act
        estadoBusiness.save(estado);

        // Assert
        verify(estadoRepository, times(1)).save(estado);
    }

    @Test
    void edith_CallsSaveMethodOfEstadoRepository_WhenEstadoIsEdited() throws GeneralException {
        // Arrange
        Estado estado = Estado.builder().esNombre("Activo").build();

        // Act
        estadoBusiness.edith(estado);

        // Assert
        verify(estadoRepository, times(1)).save(estado);
    }

    @Test
    void findT_ReturnsEstado_WhenEstadoExists() throws GeneralException {
        // Arrange
        Estado estado = Estado.builder().esNombre("Activo").build();

        when(estadoRepository.findById(estado.getEsNombre())).thenReturn(Optional.of(estado));

        // Act
        Estado result = estadoBusiness.findT(estado);

        // Assert
        assertNotNull(result);
        assertEquals(estado, result);
    }

    @Test
    void findT_ReturnsNull_WhenEstadoDoesNotExist() throws GeneralException {
        // Arrange
        Estado estado = Estado.builder().esNombre("Activo").build();

        when(estadoRepository.findById(estado.getEsNombre())).thenReturn(Optional.empty());

        // Act
        Estado result = estadoBusiness.findT(estado);

        // Assert
        assertNull(result);
    }

    @Test
    void findAllT_ReturnsListOfEstados_WhenEstadosExist() throws GeneralException {
        // Arrange
        List<Estado> estados = new ArrayList<>();
        estados.add(Estado.builder().esNombre("Activo").build());
        estados.add(Estado.builder().esNombre("Inactivo").build());

        when(estadoRepository.findAll()).thenReturn(estados);

        // Act
        List<Estado> result = estadoBusiness.findAllT(null);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void delete_CallsDeleteMethodOfEstadoRepository_WhenEstadoIsDeleted() throws GeneralException {
        // Arrange
        Estado estado = Estado.builder().esNombre("Activo").build();

        // Act
        estadoBusiness.delete(estado);

        // Asserts
        verify(estadoRepository, times(1)).delete(estado);
    }

    @Test
    void desactivate_ThrowsUnsupportedOperationException() {
        // Arrange
        Estado estado = Estado.builder().esNombre("Activo").build();

        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> estadoBusiness.desactivate(estado));
    }
}

