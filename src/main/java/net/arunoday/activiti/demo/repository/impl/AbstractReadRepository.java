package net.arunoday.activiti.demo.repository.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import net.arunoday.activiti.demo.entity.BaseEntity;
import net.arunoday.activiti.demo.repository.ReadRepository;

import org.hibernate.Session;
import org.springframework.orm.jpa.JpaTemplate;


/**
 * A base class for all Hibernate based read repository implementations.
 * 
 * @author Aparna Chaudhary
 */
public abstract class AbstractReadRepository<T extends BaseEntity> implements
		ReadRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;
	@Resource
	private EntityManagerFactory entityManagerFactory;

	private final Class<T> clazz;

	private JpaTemplate jpaTemplate;

	/**
	 * Constructs a new AbstractReadRepository with a given entity class.
	 * 
	 * @param clazz
	 *            The class of the entity this repository manages.
	 */
	protected AbstractReadRepository(Class<T> clazz) {
		this.clazz = clazz;
	}

	@PostConstruct
	void init() {
		jpaTemplate = new JpaTemplate(entityManagerFactory);
	}

	public final EntityManager getEntityManager() {
		if (entityManager == null)
			throw new IllegalStateException(
					"Entity manager has not been injected");
		return entityManager;
	}

	protected final Class<T> getEntityClass() {
		return clazz;
	}

	protected Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}

	protected JpaTemplate getJpaTemplate() {
		return jpaTemplate;
	}

	/**
	 * {@inheritDoc}
	 */
	public T getById(long id) {
		return entityManager.find(clazz, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery(
				"select e from " + getEntityClass().getName() + " e")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<T> find(String queryString, Object... args) {
		return getJpaTemplate().find(queryString, args);
	}

	@SuppressWarnings("unchecked")
	public List<Long> findAllIds() {
		return entityManager.createQuery(
				"select e.id from " + getEntityClass().getName() + " e")
				.getResultList();
	}

}
