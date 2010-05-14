/**
 * <TODO: Add Copyright>
 *
 * FILE        : JpaCountryDao.java
 *
 * PACKAGE     : in.verse.repository.jpa
 *
 * CREATION DT : May 14, 2010
 */

package net.arunoday.repository.jpa;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import net.arunoday.model.Country;
import net.arunoday.repository.CountryRepository;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
@Repository(value = "countryRepository")
public class JpaCountryRepository extends AbstractJpaReadWriteRepository<Country> implements CountryRepository {

    protected JpaCountryRepository() {
        super(Country.class);
    }

    /**
     * {@inheritDoc}
     */
    public Country getByCode(String code) {
        Query query = getEntityManager().createQuery("SELECT c FROM Country c WHERE c.code= :countryCode");
        query.setParameter("countryCode", code);

        try {
            return (Country) query.getSingleResult();
        }
        catch (NoResultException nre) {
            return null;
        }
        catch (NonUniqueResultException nure) {
            return null;
        }
    }
}
