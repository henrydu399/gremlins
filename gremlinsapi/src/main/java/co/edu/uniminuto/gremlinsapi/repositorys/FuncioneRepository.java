package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Funcione;
import co.edu.uniminuto.gremlinsapi.entitys.FuncionePK;

@Repository
public interface FuncioneRepository extends JpaRepository<Funcione, FuncionePK> {
    // Puedes agregar métodos de consulta personalizados si es necesario
}
