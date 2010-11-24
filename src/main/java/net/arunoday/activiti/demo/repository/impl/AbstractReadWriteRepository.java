package net.arunoday.activiti.demo.repository.impl;

import net.arunoday.activiti.demo.entity.BaseEntity;
import net.arunoday.activiti.demo.repository.ReadWriteRepository;

/**
 * Base class for all JPA based repository implementations.
 * 
 * @author Aparna Chaudhary
 */
public abstract class AbstractReadWriteRepository<T extends BaseEntity> extends
		AbstractReadRepository<T> implements ReadWriteRepository<T> {

	/**
	 * Constructs a new AbstractReadWriteRepository with a given entity class.
	 * 
	 * @param clazz
	 *            The class of the entity this repository manages.
	 */
	protected AbstractReadWriteRepository(Class<T> clazz) {
		super(clazz);
	}

	public T save(T t) {
		getEntityManager().persist(t);
		getEntityManager().flush();
		return t;
	}

	public T update(T t) {
		T merged = getEntityManager().merge(t);
		getEntityManager().flush();
		return merged;
	}

	public final T removeById(long id) {
		T entity = getById(id);
		if (entity == null) {
			return null;
		}
		getEntityManager().remove(entity);
		return entity;
	}

	public void remove(T t) {
		if (getEntityManager().contains(t)) {
			getEntityManager().remove(t);
		} else {
			T attached = getEntityManager().find(getEntityClass(), t.getId());
			getEntityManager().remove(attached);
		}
	}
}
