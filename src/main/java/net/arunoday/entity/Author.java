/**
 * 
 */

package net.arunoday.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "author")
public class Author extends BasicEntity {

    @Column(name = "name")
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String name;

    /**
     * 
     */
    public Author() {
        super();
    }

    /**
     * @param id
     */
    public Author(Long id) {
        super(id);
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

}
