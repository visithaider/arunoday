/**
 * 
 */

package net.arunoday.repository;

import java.util.List;

import net.arunoday.entity.Book;

/**
 * Repository interface for Book entity
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
public interface BookRepository extends ReadWriteRepository<Book> {

    /**
     * Finds books from the database based on title.
     * 
     * @param title title of the book
     * @return Books that match the given title
     */
    List<Book> findBooksByTitle(String title);

    /**
     * Finds books based on the isbn
     * 
     * @param isbn isbn of the book
     * @return
     */
    List<Book> findBooksByISBN(String isbn);

    /**
     * Finds books either by author or by title.
     * 
     * @param searchToken could be a word from author name or title
     * @return books that matches the given search token
     */
    List<Book> findBooks(String searchToken);
    
    List<String> findBookTitles(String searchToken);


}