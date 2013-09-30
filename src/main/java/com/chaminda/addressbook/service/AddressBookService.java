/**
 * 
 */
package com.chaminda.addressbook.service;

import java.util.List;

import com.chaminda.addressbook.domain.AddressBook;
import com.chaminda.addressbook.domain.Contact;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
public interface AddressBookService extends Service<AddressBook> {

	/**
	 * 
	 */
	public AddressBook saveOrUpdate(AddressBook addressBook);

	public List<Contact> getUniqueContacts(String bookOne, String bookTwo);

	/**
	 * Finds {@link AddressBook} by the addresbook name.
	 * 
	 * @param bookName
	 *            to be retrieved from the database.
	 * @return AddressBook for the given name.
	 */
	public AddressBook findAddressBookByName(String bookName);

	public List<Contact> getAllContactsSortedByName(String bookName);

}
