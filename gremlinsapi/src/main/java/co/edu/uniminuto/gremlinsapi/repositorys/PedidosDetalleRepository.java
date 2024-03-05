package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.PedidosDetalle;
import co.edu.uniminuto.gremlinsapi.entitys.PedidosDetallePK;

@Repository
public interface PedidosDetalleRepository extends JpaRepository<PedidosDetalle, PedidosDetallePK> {
    
    // Puedes agregar métodos adicionales según tus necesidades
    
}

