/**
 * 
 */
package com.chaminda.addressbook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaminda.addressbook.domain.Address;
import com.chaminda.addressbook.domain.AddressBook;
import com.chaminda.addressbook.domain.Contact;
import com.chaminda.addressbook.domain.ObjectFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chaminda
 *         <p/>
 *         date 18/09/2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springBeans/applicationContext.xml"})
public class AddressBookIntegrationTest {
	private static final ObjectFactory OBJECT_FACTORY = ObjectFactory.createNew();

	@Autowired
	AddressBookService addressBookService;

	@Test
	public void shouldPersistGivenValidAddressBoook() {
		// Given
		AddressBook beforeSaving = newAddressBookWithName("Book1");

		// when
		AddressBook addressBook = addressBookService.saveOrUpdate(beforeSaving);

		assertNotNull("Error in saving addressbook", addressBook);
		assertNotNull("Error in saving addressbook", addressBook.getAddressBookId());
	}

	@Test
	public void shouldReturnAddressBookByGivenName() {
		// Given
		String bookNameToSearch = "Chaminda Book";
		AddressBook createdBook = addressBookService.saveOrUpdate(newAddressBookWithName(bookNameToSearch));

		// when
		AddressBook addressBook = addressBookService.findAddressBookByName(bookNameToSearch);

		// then
		assertEquals(createdBook.getBookName(), addressBook.getBookName());
		assertEquals(createdBook.getAddressBookId(), addressBook.getAddressBookId());
	}

	private static AddressBook newAddressBookWithName(String name) {

		Address addressOfMine = OBJECT_FACTORY.createAddress("No 3", "Sandown Road", "Willetton", "6155", "WA");
		Contact mySelf = OBJECT_FACTORY.createContact("Chaminda", "Ariyaratna", "0404 728 677", "08 8561 9965",
				addressOfMine);

		Address addressOfJohn = OBJECT_FACTORY.createAddress("No 75", "Apsley Road", "Riverton", "6148", "WA");
		Contact john = OBJECT_FACTORY.createContact("John", "Barnet", "0404 755 689", "08 6558 9956", addressOfJohn);

		AddressBook addressBook = OBJECT_FACTORY.createAddressBook(name);
		addressBook.addContact(mySelf);
		addressBook.addContact(john);
		return addressBook;
	}
}
