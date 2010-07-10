package net.arunoday.repository;

/**
 * 
 * A basic interface for all repository implementations to implement.
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
public interface ReadWriteRepository<T> extends ReadRepository<T> {

    /**
     * Saves the given object in the data store.
     * 
     * @param t The object to be saved
     * @return The identifier of the object in the data store
     */
    long save(T t);

    /**
     * Updates the given object in the data store. It is assumed that the given object id identifiable (hold an id) and
     * already exists in the data store. This method returns the updated object.
     * 
     * @param t The object to be updated in the data store
     * @return The updated object
     */
    T update(T t);

    /**
     * Removes the object identified by the given id from the data store.
     * 
     * @param id The id of the object to be removed from the data store
     * @return The removed object
     */
    T removeById(long id);

}
