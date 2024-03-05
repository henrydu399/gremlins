package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {
    // Puedes agregar métodos de consulta personalizados si es necesario
}

