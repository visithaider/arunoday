package net.arunoday.repository;

import java.util.List;

import net.arunoday.model.Country;

/**
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
public interface CountryRepository extends ReadWriteRepository<Country> {

    /**
     * Retrieve all counties from the system.
     * 
     * @return list of all countries
     */
    List<Country> findAll();

    /**
     * Get an existing country. Will return null if no Country is found by the given country code.
     * 
     * @param code the given country code to get a Country for
     * @return the country
     */
    Country getByCode(String code);
}
