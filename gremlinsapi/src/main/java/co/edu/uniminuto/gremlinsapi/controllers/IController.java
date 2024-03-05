package co.edu.uniminuto.gremlinsapi.controllers;

import org.springframework.http.ResponseEntity;

public interface IController<T> {
	ResponseEntity<Object> getAll();

	ResponseEntity<Object> save(T u);

	ResponseEntity<Object> edith(T p);

	ResponseEntity<Object> findT(T p);

	ResponseEntity<Object> findAllT(T p);

	ResponseEntity<Object> delete(T p);

	ResponseEntity<Object> desactivate(T usuario);
}
