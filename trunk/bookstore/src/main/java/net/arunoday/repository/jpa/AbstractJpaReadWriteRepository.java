/**
 * <TODO: Add Copyright>
 *
 * FILE        : AbstractJpaDao.java
 *
 * PACKAGE     : demo.repository.jpa
 *
 * CREATION DT : May 14, 2010
 */

package net.arunoday.repository.jpa;

import net.arunoday.model.BasicEntity;
import net.arunoday.repository.ReadWriteRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for all JPA based dao implementations.
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
@Repository
@Transactional(readOnly = false)
public abstract class AbstractJpaReadWriteRepository<T extends BasicEntity> extends AbstractJpaReadRepository<T>
        implements ReadWriteRepository<T> {

    /**
     * Constructs a new AbstractJpaReadWriteRepository with a given entity class.
     * 
     * @param clazz The class of the entity this repository manages.
     */
    protected AbstractJpaReadWriteRepository(Class<T> clazz) {
        super(clazz);
    }

    /**
     * @see ReadWriteRepository#save(Object)
     */
    public long save(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
        return t.getId();
    }

    /**
     * @see ReadWriteRepository#update(Object)
     */
    public T update(T t) {
        return getEntityManager().merge(t);
    }

    /**
     * @see ReadWriteRepository#removeById(long)
     */
    public final T removeById(long id) {
        T entity = getById(id);
        if (entity == null) {
            return null;
        }
        getEntityManager().remove(entity);
        return entity;
    }

}
