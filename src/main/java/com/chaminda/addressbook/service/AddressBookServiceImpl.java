/**
 * 
 */
package com.chaminda.addressbook.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chaminda.addressbook.dao.AddressBookDao;
import com.chaminda.addressbook.domain.AddressBook;
import com.chaminda.addressbook.domain.Contact;
import com.chaminda.addressbook.exception.InvalidAddressBookException;
import com.chaminda.addressbook.util.ContactComparator;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
@Component("addressBookService")
public class AddressBookServiceImpl implements AddressBookService {

	@Autowired
	AddressBookDao addressBookDao;

	/**
	 * {@inheritDoc}
	 */
	public AddressBook saveOrUpdate(AddressBook addressBook) {
		AddressBook teamPersisted = null;
		
		if(addressBook.getAddressBookId() == null)
		{
			AddressBook retrived = addressBookDao.findByBookName(addressBook.getBookName());
			
			if(retrived == null)
			{
				teamPersisted = addressBookDao.save(addressBook);
			}else
			{
				throw new InvalidAddressBookException("There is an existing addressbook with the same name");
			}
		}else
		{
			teamPersisted = addressBookDao.save(addressBook);
		}

		return teamPersisted;
	}

	/**
	 * Finds unique contacts in the given address books
	 * 
	 * @param bookOne
	 *            to compare the contacts
	 * @param bookTwo
	 *            to compare the contacts
	 * @return {@link List} of unique contacts in given addressbooks.
	 */
	public List<Contact> getUniqueContacts(String first, String second) {
		List<Contact> uniqueContacts = new ArrayList<Contact>();
		
		AddressBook bookOne = addressBookDao.findByBookName(first);
		AddressBook bookTwo = addressBookDao.findByBookName(second);
		
		if (bookOne != null && bookTwo != null) {
			Set<Contact> union = new HashSet<Contact>();
			union.addAll(bookOne.getContacts());
			union.addAll(bookTwo.getContacts());
			uniqueContacts = getUniqueContactsFromUnion(bookOne, bookTwo, union);

		} else if (bookOne != null) {

			uniqueContacts.addAll(bookOne.getContacts());

		} else if (bookTwo != null) {

			uniqueContacts.addAll(bookTwo.getContacts());
		}

		return uniqueContacts;
	}

	private List<Contact> getUniqueContactsFromUnion(AddressBook bookOne, AddressBook bookTwo, Set<Contact> union) {

		final List<Contact> uniqueContacts = new ArrayList<Contact>();

		for (Contact contact : union) {

			if (!(bookOne.containsContact(contact) && bookTwo.containsContact(contact))) {
				uniqueContacts.add(contact);
			}
		}
		return uniqueContacts;
	}

	/**
	 * {@inheritDoc}
	 */
	public AddressBook findAddressBookByName(String bookName) {

		return addressBookDao.findByBookName(bookName);
	}

	@Override
	public List<Contact> getAllContactsSortedByName(String bookName) {
		List<Contact> contactList = new ArrayList<Contact>();
		AddressBook addressBook = addressBookDao.findByBookName(bookName);
		
		if(addressBook != null){
			contactList.addAll(addressBook.getContacts());
			Collections.sort(contactList, new ContactComparator());
		}
		return contactList;
	}

}
