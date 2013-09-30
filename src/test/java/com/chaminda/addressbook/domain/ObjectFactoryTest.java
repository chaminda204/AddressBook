/**
 * 
 */
package com.chaminda.addressbook.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author chaminda
 *   <p/>
 *       date 18/09/2013
 */
public class ObjectFactoryTest {
	
	   private ObjectFactory objectFactory;

	    @Before
	    public void beforeTest() {
	        objectFactory = ObjectFactory.createNew();
	    }
	    @Test

	    public void shouldNotBeNull() {
	        assertNotNull(objectFactory);
	    }
	        @Test
	    public void shouldCreateNewAddressWithGivenFields() {
	        //Given
	        String line1 = "3 ";
	        String street = "Sandown Road";
	        String city = "Willetton";
	        String state = "WA";
	        String postCode = "6155";

	        //When
	        Address address = objectFactory.createAddress(line1, street, city, postCode, state);

	        //Then
	        assertEquals(line1, address.getAddressLine1());
	        assertEquals(street, address.getStreet());
	        assertEquals(city, address.getCity());
	        assertEquals(state, address.getState());
	        assertEquals(postCode, address.getPostCode());
	    }

	    @Test
	    public void shouldCreateNewContactWithGivenFields() {
	        //Given
	        String fname = "Chaminda";
	        String lname = "Ariyaratna";
	        String homeNo = "08 5655 5555";
	        String mobileNo = "0722 455 666";
	        Address address = new Address();
	        //When
	        Contact contact = objectFactory.createContact(fname, lname, homeNo, mobileNo, address);

	        //Then
	        assertContact(fname, lname, homeNo, mobileNo, address, contact);
	    }

	    @Test
	    public void shouldCreateNewContactWithGivenFieldsAndId() {
	        //Given
	        Long id = 10L;
	        String fname = "Chaminda";
	        String lname = "Ariyaratna";
	        String homeNo = "08 5655 5555";
	        String mobileNo = "0722 455 666";
	        Address address = new Address();
	        //When
	        Contact contact = objectFactory.createContact(id, fname, lname, homeNo, mobileNo, address);

	        //Then
	        assertEquals(id, contact.getContactID());
	        assertContact(fname, lname, homeNo, mobileNo, address, contact);
	    }

	    @Test
	    public void shouldCreateNewAddressbook() {
	        //Given
	        String bookName = "MyAddressBook";
	        //When
	        AddressBook addressBook = objectFactory.createAddressBook(bookName);

	        //Then
	        assertEquals(bookName, addressBook.getBookName());
	    }

	    private void assertContact(String fname, String lname, String homeNo, String mobileNo, Address address, Contact contact) {
	        assertEquals(fname, contact.getFirstName());
	        assertEquals(lname, contact.getLastName());
	        assertEquals(homeNo, contact.getHomeNo());
	        assertEquals(mobileNo, contact.getMobileNo());
	        assertEquals(address, contact.getAddress());
	    }


}
