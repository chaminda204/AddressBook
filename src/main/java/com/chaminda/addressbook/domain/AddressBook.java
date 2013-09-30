/**
 * 
 */
package com.chaminda.addressbook.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
@Entity
@Table(name = "addtess_book")
public class AddressBook extends AbstractEntity {

	/**
    *
    */
	private static final long serialVersionUID = 4878822284601521065L;

	@Column(name = "address_book_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressBookId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_book_id")
	private Set<Contact> contacts;

	private String bookName;

	public AddressBook() {
		contacts = new HashSet<Contact>();

	}

	/**
	 * @return the addressBookId
	 */
	public Long getAddressBookId() {
		return addressBookId;
	}

	/**
	 * @param addressBookId
	 *            the addressBookId to set
	 */
	public void setAddressBookId(Long addressBookId) {
		this.addressBookId = addressBookId;
	}

	/**
	 * @return the contacts
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *            the contacts to set
	 */
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}

	/**
	 * @return the ownerName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param ownerName
	 *            the ownerName to set
	 */
	public void setBookName(String ownerName) {
		this.bookName = ownerName;
	}

	public boolean containsContact(Contact contact) {
		return getContacts().contains(contact);
	}

}
