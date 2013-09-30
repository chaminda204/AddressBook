/**
 * 
 */
package com.chaminda.addressbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaminda.addressbook.domain.AddressBook;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
@Repository
public interface AddressBookDao extends JpaRepository<AddressBook, Long> {

	/**
	 * Returns the {@link AddressBook} instance by the parameter bookName.
	 * 
	 * @param bookName
	 *            as fetching criteria.
	 * @return return {@link AddressBook} as result.
	 */
	AddressBook findByBookName(String bookName);

}
