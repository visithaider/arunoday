/**
 * 
 */

package net.arunoday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.joda.time.LocalDate;

/**
 * Represents Book Entity.
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "book")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = { @TokenFilterDef(factory = LowerCaseFilterFactory.class) })
// @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = { @Parameter(name = "language", value =
// "English") })
public class Book extends BasicEntity {

    @Column(name = "title")
    @NotNull
    @Size(min = 5, max = 30)
    @Field(index = Index.TOKENIZED, store = Store.YES)
    @Analyzer(definition = "customanalyzer")
    private String title;

    @Column(name = "isbn")
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String isbn;

    @Column(name = "categories")
    @NotNull
    @Field(index = Index.TOKENIZED, store = Store.YES)
    @Analyzer(definition = "customanalyzer")
    private String categories;

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
    public Float getPrice() {
        return price;
    }

    /**
     * @param price The price to set.
     */
    public void setPrice(Float price) {
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
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount The pageCount to set.
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return Returns the categories.
     */
    public String getCategories() {
        return categories;
    }

    /**
     * @param categories The categories to set.
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }

    /**
     * @param price The price to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @param pageCount The pageCount to set.
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Book [bindingType=" + bindingType + ", isbn=" + isbn + ", pageCount=" + pageCount + ", price=" + price
                + ", publishingDate=" + publishingDate + ", title=" + title + "]";
    }

}