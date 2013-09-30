/**
 * 
 */
package com.chaminda.addressbook.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Timestamp dateCreated;

	@Version
	private Timestamp lastUpdated;

	/**
	 * Getter method for dateCreated
	 * 
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Setter method for dateCreated
	 * 
	 * @param dateCreated
	 *            for setting dateCreated value
	 */
	public void setDateCreated(final Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Getter method for lastUpdated
	 * 
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Setter method for lastUpdated
	 * 
	 * @param lastUpdated
	 *            for setting lastUpdated value
	 */
	public void setLastUpdated(final Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * This operations sets the timestamp when the object is updating.
	 */
	@PreUpdate
	public void setUpdateTimestamp() {
		setLastUpdated(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * This operations sets the timestamp when the object is created.
	 */
	@PrePersist
	public void setCreatedDate() {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		setDateCreated(timestamp);
		setLastUpdated(timestamp);
	}

}
