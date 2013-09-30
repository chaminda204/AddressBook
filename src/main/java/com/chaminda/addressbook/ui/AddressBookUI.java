/**
 * 
 */
package com.chaminda.addressbook.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.chaminda.addressbook.domain.AddressBook;
import com.chaminda.addressbook.domain.Contact;
import com.chaminda.addressbook.domain.ObjectFactory;
import com.chaminda.addressbook.service.AddressBookService;
import com.chaminda.addressbook.util.SpringBeanProvider;

/**
 * @author chaminda
 *         <p/>
 *         date 18/09/2013
 */
public class AddressBookUI {
	
	private static final String OPTION_NO = "N";

	private static final String OPTION_EXIT = "0";

	private static final String OPTION_NEW = "1";

	private static final String OPTION_UPDATE = "2";

	private static final String OPTION_FIND_CONTACT = "3";

	private static final String OPTION_UNIQUE = "4";

	@Autowired
	AddressBookService addressBookService;

	private static BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

	public void start() {

		 loadApplicationContext();
		String userCommand = null;
		System.out.println("****************  Welcome Address book  ****************");
		do {
			
			mainMenue();
			System.out.println();
			userCommand = readData("Please select your option ");

			if (userCommand.equals(OPTION_NEW)) {

				addNewAddressBook();

			} else if (userCommand.equals(OPTION_UPDATE)) {

				updateAddressBook();

			} else if (userCommand.equals(OPTION_FIND_CONTACT)) {

				diaplayAllTheContactsInAddressBook();

			} else if (userCommand.equals(OPTION_UNIQUE)) {

				diaplayUniqueContactsInAddressBooks();
				
			} 

		} while (!OPTION_EXIT.equals(userCommand));

		System.out.println("****************       Good Bye         *****************");
	}

	public static String readData(final String userCommand) {
		try {
			System.out.print(userCommand);
			return userInputReader.readLine().trim();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new AddressBookUI().start();
	}

	private void mainMenue() {

		System.out.println("USER INPUT COMMANDS.");
		System.out.println("Press 1 to create a new address book");
		System.out.println("Press 2 to update existing address book");
		System.out.println("Press 3 to find contacts in your address book");
		System.out.println("Press 4 to get unique contacts in address books");
		System.out.println("Press 0 to exit the application");
	}

	private void addNewAddressBook() {

		System.out.println("You have selected Option 1 - Creating a new address book");

		String addressBookName = readData("Please type the name of the address book ");
		AddressBook addressBook = ObjectFactory.createNew().createAddressBook(addressBookName);
		String isnewContact = readData("Do you want to add new contacts - Press N to go back ");

		addNewContactToAddressBook(isnewContact, addressBook);
	}

	private void addNewcontact(AddressBook addressBook) {
		// TODO Auto-generated method stub
		String isnewContact;
		do {
			String firstName = readData("Please type the first name");
			String lastName = readData("Please type the last name");
			String homeNo = readData("Please type home number");
			String mobileNo = readData("Please type the mobile number");
			Contact contact = ObjectFactory.createNew().createContact(firstName, lastName, homeNo, mobileNo, null);
			addressBook.addContact(contact);
			isnewContact = readData("Do you want to add new contacts - Press N to go back ");
		} while (!OPTION_NO.equalsIgnoreCase(isnewContact));

	}

	private void updateAddressBook() {

		System.out.println("You have selected Option 2 - Updating existing record");

		String addressBookName = readData("Please type the name of the address book to update ");

		AddressBook addressBook = addressBookService.findAddressBookByName(addressBookName);

		if (addressBook != null) {
			String isnewContact = readData("Do you want to add new contacts - Press N to go back ");

			addNewContactToAddressBook(isnewContact, addressBook);
		}
	}

	private void diaplayAllTheContactsInAddressBook() {

		System.out.println("You have selected Option 3 - Find contacts in addressbook");

		String addressBookName = readData("Please type the name of the address book to find contacts ");

		List<Contact> contacts = addressBookService.getAllContactsSortedByName(addressBookName);

		dispalyContacts(contacts);

	}

	private void dispalyContacts(List<Contact> contacts) {

		if(!contacts.isEmpty()){
			System.out.println("-------Displaying Contacts --------");
			for (Contact contact : contacts) {
				System.out.println(contact);
			}
			System.out.println("------ End of Contacts-------");
		}else
		{
			System.out.println("-------No Data to Display --------");
		}

	}

	private void diaplayUniqueContactsInAddressBooks() {

		System.out.println("You have selected Option 4 - Find unique contacts in addressbooks");

		String bookOne = readData("Please type the name of the first address book ");
		String bookTwo = readData("Please type the name of the second address book ");

		List<Contact> contacts = addressBookService.getUniqueContacts(bookOne, bookTwo);

		if(!contacts.isEmpty()){
			System.out.println("-------Displaying Unique Contacts --------");
			for (Contact contact : contacts) {
				System.out.println(contact);
			}
			System.out.println("------ End of Unique Contacts-------");
		}else
		{
			System.out.println("-------No Data to Display --------");
		}

	}

	private void addNewContactToAddressBook(String isnewContact, AddressBook addressBook) {

		if (!isnewContact.equalsIgnoreCase(OPTION_NO)) {
			addNewcontact(addressBook);
			
			addressBookService.saveOrUpdate(addressBook);
			System.out.println("Addressbook information saved");
		}
	}

	private void loadApplicationContext() {
		addressBookService = (AddressBookService) SpringBeanProvider.getBean("addressBookService");
	}

}

