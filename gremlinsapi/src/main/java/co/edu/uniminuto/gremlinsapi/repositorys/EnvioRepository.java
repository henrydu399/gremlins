package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Envio;
import co.edu.uniminuto.gremlinsapi.entitys.EnvioPK;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, EnvioPK> {
}
