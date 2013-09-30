/**
 * 
 */
package com.chaminda.addressbook.util;

import java.util.Comparator;

import com.chaminda.addressbook.domain.Contact;

/**
 * @author chaminda
 *         <p/>
 *         date 18/09/2013
 */
public class ContactComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact contactOne, Contact contactTwo) {

		int value = contactOne.getFirstName().compareTo(contactTwo.getFirstName());
		if (value != 0) {
			return value;
		}

		return contactOne.getLastName().compareTo(contactTwo.getLastName());
	}

}
