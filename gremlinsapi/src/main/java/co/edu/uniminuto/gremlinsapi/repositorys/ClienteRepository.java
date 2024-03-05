package co.edu.uniminuto.gremlinsapi.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.gremlinsapi.entitys.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente>{
   

	 default List<Cliente> findAllT(Cliente cliente) {
	        if (cliente == null) {
	            return null;
	        }
	        Specification<Cliente> specification = Specification.where(null);
	        
	        if (cliente.getCId() != 0) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cId"), cliente.getCId()));
	        }
	        if (cliente.getCDireccion() != null) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cDireccion"), cliente.getCDireccion()));
	        }
	        if (cliente.getCNombre() != null) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cNombre"), cliente.getCNombre()));
	        }
	        if (cliente.getCTelefono() != null) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cTelefono"), cliente.getCTelefono()));
	        }
	        
	        return findAll(specification);
	    }
	
	 default Optional<Cliente> findT(Cliente cliente) {
	        if (cliente == null) {
	            return null;
	        }
	        Specification<Cliente> specification = Specification.where(null);
	        
	        if (cliente.getCId() != 0) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cId"), cliente.getCId()));
	        }
	        if (cliente.getCDireccion() != null) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cDireccion"), cliente.getCDireccion()));
	        }
	        if (cliente.getCNombre() != null) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cNombre"), cliente.getCNombre()));
	        }
	        if (cliente.getCTelefono() != null) {
	            specification = specification.and((root, query, criteriaBuilder) ->
	                    criteriaBuilder.equal(root.get("cTelefono"), cliente.getCTelefono()));
	        }
	        
	        return findOne(specification);
	    }
}