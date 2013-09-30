/**
 * 
 */
package com.chaminda.addressbook.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.chaminda.addressbook.domain.Address;
import com.chaminda.addressbook.domain.AddressBook;
import com.chaminda.addressbook.domain.Contact;
import com.chaminda.addressbook.domain.ObjectFactory;

/**
 * @author chaminda
 *   <p/>
 *       date 18/09/2013
 */
public class ContactComparatorTest {
	
	 private static final ObjectFactory OBJECT_FACTORY = ObjectFactory.createNew();
	@Test
	public void shouldArrangeContactsInNameOrder(){
		
		//given
		AddressBook addressBook = getAddressBookForSorting();
		List<Contact> contactList = new ArrayList<Contact>();
		contactList.addAll(addressBook.getContacts());

		// when
		Collections.sort(contactList, new ContactComparator());
		
		// then
		assertEquals("Chaminda", contactList.get(0).getFirstName());
		assertEquals("Chaminda", contactList.get(1).getFirstName());
		assertEquals("Ariyaratna", contactList.get(0).getLastName());
		assertEquals("Ariyaratne", contactList.get(1).getLastName());
		
	}
	
    public static AddressBook getAddressBookForSorting() {

        Address addressOfMine = OBJECT_FACTORY.createAddress("No 3", "Sandown Road", "Willetton", "6155", "WA");
        Contact mySelf = OBJECT_FACTORY.createContact("Chaminda", "Ariyaratna", "0404 728 677", "08 8561 9965", addressOfMine);
        Contact mySleldWithChangedSurname = OBJECT_FACTORY.createContact("Chaminda", "Ariyaratne", "0404 728 677", "08 8561 9965", addressOfMine);

        Address addressOfJohn = OBJECT_FACTORY.createAddress("No 75", "Apsley Road", "Riverton", "6148", "WA");
        Contact john = OBJECT_FACTORY.createContact("John", "Barnet", "0404 755 689", "08 6558 9956", addressOfJohn);

        AddressBook addressBook = OBJECT_FACTORY.createAddressBook("Chaminda's Book");
        addressBook.addContact(mySelf);
        addressBook.addContact(john);
        addressBook.addContact(mySleldWithChangedSurname);
        
        return addressBook;
    }
	




}
