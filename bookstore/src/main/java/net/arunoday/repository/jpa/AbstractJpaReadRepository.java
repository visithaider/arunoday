/**
 * <TODO: Add Copyright>
 *
 * FILE        : AbstractJpaReadDao.java
 *
 * PACKAGE     : demo.repository.jpa
 *
 * CREATION DT : May 14, 2010
 */

package net.arunoday.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.arunoday.model.BasicEntity;
import net.arunoday.repository.ReadRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * A base class for all JPA based read repository implementations.
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
@Repository
@Transactional(readOnly=true)
public abstract class AbstractJpaReadRepository<T extends BasicEntity> implements ReadRepository<T> {

    private final Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Constructs a new AbstractJpaReadRepository with a given entity class.
     * 
     * @param clazz The class of the entity this repository manages.
     */
    protected AbstractJpaReadRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getById(long id) {
        return entityManager.find(clazz, id);
    }

    protected final Class<T> getEntityClass() {
        return clazz;
    }

    public final EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
		// org.hibernate.Session session = (Session)
		// getEntityManager().getDelegate();
		// return session.createCriteria(Country.class).list();
         return getEntityManager().createQuery("select e from " + getEntityClass().getName() + " e").getResultList();
    }
}
