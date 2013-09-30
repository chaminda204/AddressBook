/**
 * 
 */
package com.chaminda.addressbook.service;

import com.chaminda.addressbook.domain.AbstractEntity;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
public interface Service<T extends AbstractEntity> {

	/**
	 * Create or update operation for entities.
	 * 
	 * @param entity
	 *            to be saved in the database.
	 * @return Created/Updated entity.
	 */
	T saveOrUpdate(T entity);
}
