package net.arunoday.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "country")
public class Country extends BaseEntity {

    @Column(name = "code")
    private String code;

    /**
     * Constructs a new Country. (Default empty constructor).
     */
    public Country() {
        this(null, null);
    }

    /**
     * Constructs a new Country with a given code and name.
     * 
     * @param code The code of the country (usually ISO-3166 code)
     */
    public Country(String code) {
        this(null, code);
    }

    /**
     * Constructs a new Country with a given id, code and name.
     * 
     * @param id The id of the country.
     * @param code The code of the country (usually ISO-3166 code).
     */
    public Country(Long id, String code) {
        super(id);
        this.code = code;
    }

    // =========================================== Object Methods ======================================================

    @Override
    protected boolean doEquals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        Country other = (Country) o;
        return new EqualsBuilder().append(getCode(), other.getCode()).isEquals();
    }

    protected int doHashCode() {
        return new HashCodeBuilder().append(getCode()).toHashCode();
    }

    // ============================================== Setter/Getter ====================================================

    /**
     * Returns the code of the country. Usually ISO-3166 code.
     * 
     * @return The code of the country.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the country. Usually ISO-3166 code.
     * 
     * @param code the given country code.
     */
    public void setCode(String code) {
        this.code = code;
    }
}
