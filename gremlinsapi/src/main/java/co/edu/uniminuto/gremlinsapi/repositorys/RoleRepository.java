package co.edu.uniminuto.gremlinsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Role;
import co.edu.uniminuto.gremlinsapi.entitys.RolePK;

@Repository
public interface RoleRepository extends JpaRepository<Role, RolePK> {
}