/**
 * 
 */
package com.chaminda.addressbook.domain;

/**
 * @author chaminda
 *   <p/>
 *       date 18/09/2013
 */
public class ObjectFactory {
	 private ObjectFactory(){}

	    public Address createAddress(String line1, String street, String city, String postCode, String state) {
	        Address address = new Address();
	        address.setAddressLine1(line1);
	        address.setCity(city);
	        address.setStreet(street);
	        address.setState(state);
	        address.setPostCode(postCode);
	        return address;
	    }

	    public Contact createContact(Long id, String fname, String lname, String homeNo, String mobileNo, Address address) {
	        Contact contact = createContact(fname,lname, homeNo,mobileNo,address);
	        contact.setContactID(id);
	        return contact;
	    }

	    public Contact createContact(String fname, String lname, String homeNo, String mobileNo, Address address) {
	        Contact contact = new Contact();
	        contact.setFirstName(fname);
	        contact.setLastName(lname);
	        contact.setHomeNo(homeNo);
	        contact.setMobileNo(mobileNo);
	        contact.setAddress(address);
	        return contact;
	    }

	    public AddressBook createAddressBook(String name) {
	        AddressBook addressBook = new AddressBook();
	        addressBook.setBookName(name);
	        return addressBook;
	    }

	    public static ObjectFactory createNew() {
	        return new ObjectFactory();
	    }
	}
