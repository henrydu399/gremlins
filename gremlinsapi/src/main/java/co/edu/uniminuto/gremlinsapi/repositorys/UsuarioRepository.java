package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados de ser necesario
	
	 Usuario findByEmailAndPassword(String email, String password);
	
	
}
