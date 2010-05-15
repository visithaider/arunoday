/**
 * 
 */

package net.arunoday.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

/**
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "book")
public class Book extends BasicEntity {

    @Column(name = "title")
    @NotNull
    @Size(min = 5, max = 30)
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "price")
    private float price;

    @Column(name = "binding_type")
    private BindingType bindingType;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
    @Column(name = "publishing_date")
    private LocalDate publishingDate;

    @Column(name = "page_count")
    private int pageCount;

    /**
	 * 
	 */
    public Book() {
        super();
    }

    /**
     * @param id
     */
    public Book(Long id) {
        super(id);
    }

    /**
     * @param title
     */
    public Book(String title) {
        this(title, null);
    }

    /**
     * @param title
     * @param isbn
     */
    public Book(String title, String isbn) {
        super();
        this.title = title;
        this.isbn = isbn;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the isbn.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn The isbn to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return Returns the price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price The price to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return Returns the bindingType.
     */
    public BindingType getBindingType() {
        return bindingType;
    }

    /**
     * @param bindingType The bindingType to set.
     */
    public void setBindingType(BindingType bindingType) {
        this.bindingType = bindingType;
    }

    /**
     * @return Returns the publishingDate.
     */
    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    /**
     * @param publishingDate The publishingDate to set.
     */
    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    /**
     * @return Returns the pageCount.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount The pageCount to set.
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}