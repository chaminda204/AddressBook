/**
 * 
 */
package com.chaminda.addressbook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.chaminda.addressbook.dao.AddressBookDao;
import com.chaminda.addressbook.domain.Address;
import com.chaminda.addressbook.domain.AddressBook;
import com.chaminda.addressbook.domain.Contact;
import com.chaminda.addressbook.domain.ObjectFactory;
import com.chaminda.addressbook.exception.InvalidAddressBookException;
import com.chaminda.addressbook.service.AddressBookService;
import com.chaminda.addressbook.service.AddressBookServiceImpl;
import com.chaminda.addressbook.util.ContactComparatorTest;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
public class AddressBookServiceTest {

	   private static final ObjectFactory OBJECT_FACTORY = ObjectFactory.createNew();
	    public static final long STARTING_ID = 1L;

	    @InjectMocks
	    AddressBookService addressBookService;

	    @Mock
	    AddressBookDao addressBookDao;

	    @Before
	    public void beforeClass() {
	        addressBookService = new AddressBookServiceImpl();
	        initMocks(this);
	    }

	    @Test
	    public void shouldPersistGivenValidAddressBoook() {
	        // Given
	        AddressBook beforeSaving = getAddressBookOne();
	        AddressBook addressBookOneAfterSaved = getAddressBookOneAfterSaving();
	        given(addressBookDao.save(beforeSaving)).willReturn(addressBookOneAfterSaved);

	        //when
	        AddressBook addressBook = addressBookService.saveOrUpdate(beforeSaving);

	        assertEquals("Error in saving addressbook", addressBookOneAfterSaved, addressBook);
	    }
	    
	    @Test(expected=InvalidAddressBookException.class) 
	    public void shouldThrowExceptionWhenGivenExistingAddressBookName() {
	        // Given
	    	
	        AddressBook beforeSaving = getAddressBookOne();
	        AddressBook addressBookOneAfterSaved = getAddressBookOneAfterSaving();
	        given(addressBookDao.findByBookName(beforeSaving.getBookName())).willReturn(addressBookOneAfterSaved);
	        given(addressBookDao.save(beforeSaving)).willThrow(InvalidAddressBookException.class);

	        //when
	        AddressBook addressBook = addressBookService.saveOrUpdate(beforeSaving);

	        assertEquals("Error in saving addressbook", addressBookOneAfterSaved, addressBook);
	    }

	    @Test
	    public void shouldReturnUniqueContactsInGivenAddressBooks() {
	        // Given
	    	 String first = "Chaminda's Book";
	    	 String second = "Chaminda's Other Book";
	        given(addressBookDao.findByBookName(first)).willReturn(getAddressBookOne());
	        given(addressBookDao.findByBookName(second)).willReturn(getAddressBookTwo());
	        // When
	        List<Contact> uniqueContacts = addressBookService.getUniqueContacts(first, second);
	        // Then
	        assertNotNull("Error getting uniqueAddress", uniqueContacts);
	        assertTrue("getUniqueContacts should return an array", uniqueContacts instanceof ArrayList);
	        assertEquals("getUniqueContacts should return 2 eliments", 2, uniqueContacts.size());

	    }

	    @Test
	    public void shouldReturnAllContactsWhenOneAddressBookIsGiven() {
	        // Given
	    	 String first = "Chaminda's Book";
	        given(addressBookDao.findByBookName(first)).willReturn(getAddressBookOne());

	        // When
	        List<Contact> uniqueContacts = addressBookService.getUniqueContacts(first, null);
	        // Then
	        assertNotNull("Error getting uniqueAddress", uniqueContacts);
	        assertTrue("getUniqueContacts should return an array", uniqueContacts instanceof ArrayList);
	        assertEquals("getUniqueContacts should return 3 eliments", 2, uniqueContacts.size());

	    }

	    @Test
	    public void shouldReturnEmptyListWhenBothBooksAreNull() {
	        // Given

	        // When
	        List<Contact> uniqueContacts = addressBookService.getUniqueContacts(null, null);
	        // Then
	        assertNotNull("Error getting uniqueAddress", uniqueContacts);
	        assertTrue("getUniqueContacts should return an array", uniqueContacts instanceof ArrayList);
	        assertTrue("getUniqueContacts should return 0 eliments", uniqueContacts.isEmpty());

	    }

	    @Test
	    public void shouldReturnAddressBookByGivenName() {
	        // Given
	        String searchName = "Chaminda's Book";
	        AddressBook addressBookOneAfterSaved = getAddressBookOneAfterSaving();
	        given(addressBookDao.findByBookName(searchName)).willReturn(addressBookOneAfterSaved);

	        //when
	        AddressBook addressBook = addressBookService.findAddressBookByName(searchName);

	        //then
	        assertEquals(addressBookOneAfterSaved, addressBook);
	    }
	    
	    @Test
	    public void shouldReturnAllContactsSortedByName() {
	        // Given
	    	String bookNameToSearch = "Chaminda's Book";
	        given(addressBookDao.findByBookName(bookNameToSearch)).willReturn(ContactComparatorTest.getAddressBookForSorting());
	        // When
	        List<Contact> contactList = addressBookService.getAllContactsSortedByName(bookNameToSearch);
	        // Then
	        assertEquals("Chaminda", contactList.get(0).getFirstName());
			assertEquals("Chaminda", contactList.get(1).getFirstName());
			assertEquals("Ariyaratna", contactList.get(0).getLastName());
			assertEquals("Ariyaratne", contactList.get(1).getLastName());

	    }

	    private static AddressBook getAddressBookOneAfterSaving() {
	        AddressBook addressBook = getAddressBookOne();
	        addressBook.setAddressBookId(STARTING_ID);
	        Long contactId = STARTING_ID;

	        for (Contact contact : addressBook.getContacts()) {
	            contact.setContactID(contactId++);
	        }
	        return addressBook;
	    }

	    private static AddressBook getAddressBookOne() {

	        Address addressOfMine = OBJECT_FACTORY.createAddress("No 3", "Sandown Road", "Willetton", "6155", "WA");
	        Contact mySelf = OBJECT_FACTORY.createContact("Chaminda", "Ariyaratna", "0404 728 677", "08 8561 9965", addressOfMine);

	        Address addressOfJohn = OBJECT_FACTORY.createAddress("No 75", "Apsley Road", "Riverton", "6148", "WA");
	        Contact john = OBJECT_FACTORY.createContact("John", "Barnet", "0404 755 689", "08 6558 9956", addressOfJohn);

	        AddressBook addressBook = OBJECT_FACTORY.createAddressBook("Chaminda's Book");
	        addressBook.addContact(mySelf);
	        addressBook.addContact(john);
	        return addressBook;
	    }

	    private static AddressBook getAddressBookTwo() {

	        Address addressOfMine = OBJECT_FACTORY.createAddress("No 3", "Sandown Road", "Willetton", "6155", "WA");
	        Contact mySelf = OBJECT_FACTORY.createContact("Chaminda", "Ariyaratna", "0404 728 677", "08 8561 9965", addressOfMine);

	        Address addressOfYati = OBJECT_FACTORY.createAddress("No 198", "Canning Road", "Canning vale", "6135", "WA");
	        Contact yati = OBJECT_FACTORY.createContact("Yati", "Hong", "0405 458 954", "08 6359 9945", addressOfYati);

	        AddressBook addressBook = OBJECT_FACTORY.createAddressBook("Chaminda's other Book");
	        addressBook.addContact(mySelf);
	        addressBook.addContact(yati);
	        return addressBook;
	    }

	}
