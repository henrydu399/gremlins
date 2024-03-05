package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Distribuidore;

@Repository
public interface DistribuidorRepository extends JpaRepository<Distribuidore, Long> {
}
