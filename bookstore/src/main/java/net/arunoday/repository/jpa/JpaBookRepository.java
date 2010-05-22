/**
 * 
 */

package net.arunoday.repository.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import net.arunoday.model.Book;
import net.arunoday.repository.BookRepository;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@Repository(value = "bookRepository")
public class JpaBookRepository extends AbstractJpaReadWriteRepository<Book> implements BookRepository {

    protected JpaBookRepository() {
        super(Book.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.arunoday.repository.BookRepository#findBooksByTitle(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Book> findBooksByTitle(String title) {
        // Hibernate Criteria
        return ((Session) getEntityManager().getDelegate()).createCriteria(Book.class).add(
                Restrictions.like("title", title, MatchMode.ANYWHERE)).list();

        // JPA Criteria Query
        // CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        // CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
        // Root<Book> bookRoot = criteriaQuery.from(Book.class);
        // ParameterExpression<String> titleParam = builder.parameter(String.class);
        //
        // criteriaQuery.select(bookRoot).where(builder.like(titleParam, bookRoot.get(Book_.title).as(String.class)));
        // TypedQuery<Book> query = getEntityManager().createQuery(criteriaQuery);
        // query.setParameter(titleParam, title);
        //
        // List<Book> books = query.getResultList();
        // return books;

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.arunoday.repository.BookRepository#findBooksByISBN(java.lang.String)
     */
    public List<Book> findBooksByISBN(String isbn) {
        String jpql = "SELECT b FROM Book b WHERE b.isbn = :isbn";

        // JPA1 Implementation
        // Query query = getEntityManager().createQuery(jpql);

        // JPA2 Implementation
        TypedQuery<Book> query = getEntityManager().createQuery(jpql, Book.class);

        query.setParameter("isbn", isbn);
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.arunoday.repository.BookRepository#findBooks(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<Book> findBooks(String searchToken) {
        
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(getEntityManager());
        // create native Lucene query
        String[] fields = new String[] { "title", "authors.name", "publishingDate" };
        QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_29, fields, new StandardAnalyzer(
                Version.LUCENE_29));
        Query query = null;
        try {
            query = parser.parse(searchToken);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        // wrap Lucene query in a javax.persistence.Query
        FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);
        // execute search
        return persistenceQuery.getResultList();
    }

}
