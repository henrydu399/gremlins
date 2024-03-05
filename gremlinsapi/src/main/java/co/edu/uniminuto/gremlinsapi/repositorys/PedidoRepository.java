package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    // Puedes agregar métodos adicionales según tus necesidades
    
}
