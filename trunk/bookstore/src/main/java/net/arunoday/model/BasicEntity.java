/**
 * <TODO: Add Copyright>
 *
 * FILE        : BasicEntity.java
 *
 * PACKAGE     : demo.model
 *
 * CREATION DT : May 14, 2010
 */

package net.arunoday.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class for all entities.
 * 
 * @author Aparna Chaudhary ( aparna.chaudhary@gmail.com )
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BasicEntity implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;

	@Version
	@Column(name = "version")
	private int version;

	/**
	 * Default empty constructor.
	 */
	protected BasicEntity() {
		this(null);
	}

	/**
	 * Constructs a new BasicEntity with the given id.
	 * 
	 * @param id
	 *            The id of this entity.
	 */
	protected BasicEntity(Long id) {
		this.id = id;
	}

	// ========================================== Getters/Setters
	// =======================================================

	/**
	 * Returns the id of this entity.
	 * 
	 * @return The id of this entity.
	 */
	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	// =========================================== Object Methods
	// =======================================================

	/**
	 * @return
	 */
	public boolean isPersisted() {
		return id != null;
	}

	/**
	 * Determines if this object is equal to another object.
	 * 
	 * @param o
	 *            the object to check whether it is equal to this object.
	 * @return true if the objects are equal to each other, false otherwise.
	 */
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BasicEntity)) {
			return false;
		}

		final BasicEntity entity = (BasicEntity) o;

		// If id is null, only check for object identity.
		if (getId() == null || entity.getId() == null) {
			return doEquals(o);
		}

		return getId().equals(entity.getId());
	}

	/**
	 * Computes the hashcode of this object.
	 * 
	 * @return integer specifying the hashcode value
	 */
	public final int hashCode() {
		if (getId() == null) {
			return doHashCode();
		}
		return (getId().hashCode() + this.getClass().hashCode()) * 29;
	}

	/**
	 * Can/Should be overridden by concrete entities. The passed in object can be
	 * assumed to be of the same type as the entity class (casting is safe). The
	 * method should check business object equality disregarding the id of the
	 * entity.
	 * 
	 * @param object
	 *            The entity to compare with
	 * @return <code>true</code> if this entity equals business-wise to the
	 *         passed in entity, <code>false</code> otherwise
	 */
	protected boolean doEquals(Object object) {
		return super.equals(object);
	}

	/**
	 * Can/Should be overridden by concrete entities. This method should compute
	 * the business hashcode of the entity. That is, disregarding the id of the
	 * entity.
	 * 
	 * @return The business hashcode of the entity
	 */
	protected int doHashCode() {
		return super.hashCode();
	}

}
