package co.edu.uniminuto.gremlinsapi.business;

import java.util.List;

import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;

public interface ILogic<T> {

	List<T> getAll() throws GeneralException;

	void save(T u) throws GeneralException;

	void edith(T p) throws GeneralException;

	T findT(T p) throws GeneralException;

	List<T> findAllT(T p) throws GeneralException;

	void delete(T p) throws GeneralException;

	public void desactivate(T usuario) throws GeneralException;

}
