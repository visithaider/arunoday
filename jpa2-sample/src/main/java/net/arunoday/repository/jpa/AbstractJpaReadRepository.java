package net.arunoday.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.arunoday.model.BaseEntity;
import net.arunoday.repository.ReadRepository;

import org.springframework.stereotype.Repository;

/**
 * A base class for all JPA based read repository implementations.
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
@Repository
public abstract class AbstractJpaReadRepository<T extends BaseEntity> implements ReadRepository<T> {

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
        return getEntityManager().createQuery("select e from " + getEntityClass().getName() + " e").getResultList();
    }
}
