package io.altar.jseproject.Business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.BaseEntity;
import io.altar.jseproject.repository.EntityRepository;

public abstract class EntityBusiness<T extends EntityRepository<R>, R extends BaseEntity> {
	@Inject
	protected T repository;

	@Transactional
	public R newEntity(R entity) {

		return repository.addToDB(entity);
	}

	@Transactional
	public String deleteEntity(Long id) {

		return repository.deleteEntity(id);
	}

	public R getEntityById(Long id) {

		return repository.getById(id);
	}

	public List<R> getAllEntity() {
		return repository.getAll();
	}

	@Transactional
	public R changeEntity(R Entity) {

		return repository.changeEntity(Entity);
	}

}