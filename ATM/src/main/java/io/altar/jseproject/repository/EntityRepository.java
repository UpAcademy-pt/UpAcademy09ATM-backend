package io.altar.jseproject.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.model.BaseEntity;

public abstract class EntityRepository<T extends BaseEntity> {

	@PersistenceContext
	protected EntityManager em;

	public T addToDB(T entity) {

		return em.merge(entity);// em vez de merge ele fez presist, porque o merge... caso não exista ainda
								// aquele produto, ele cria um novo
	}

	public T getById(Long id) {

		return (T) em.find(getEntityClass(), id);
	}

	public String deleteEntity(Long id) {
		T entity = em.find(getEntityClass(), id);
		em.remove(entity);

		return "O produto foi elimindo da base de dados";
	}

	public T changeEntity(T entity) {

		return em.merge(entity);
	}

	public List<T> getAll() {
		return em.createNamedQuery(getAllEntityQueryName(), getEntityClass()).getResultList();
	}


	protected abstract Class<T> getEntityClass();
	
	protected abstract String getAllEntityQueryName();

}
