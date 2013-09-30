/**
 * 
 */
package com.chaminda.addressbook.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chaminda
 *         <p/>
 *         date 17/09/2013
 */
@Entity
@Table(name = "contact")
public class Contact extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7662255679157151800L;

	@Column(name = "contact_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactID;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "home_no")
	private String homeNo;

	@Embedded
	private Address address;

	/**
	 * @return the contactID
	 */
	public Long getContactID() {
		return contactID;
	}

	/**
	 * @param contactID
	 *            the contactID to set
	 */
	public void setContactID(Long contactID) {
		this.contactID = contactID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo
	 *            the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the homeNo
	 */
	public String getHomeNo() {
		return homeNo;
	}

	/**
	 * @param homeNo
	 *            the homeNo to set
	 */
	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((homeNo == null) ? 0 : homeNo.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobileNo == null) ? 0 : mobileNo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {

			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Contact other = (Contact) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (homeNo == null) {
			if (other.homeNo != null) {
				return false;
			}
		} else if (!homeNo.equals(other.homeNo)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (mobileNo == null) {
			if (other.mobileNo != null) {
				return false;
			}
		} else if (!mobileNo.equals(other.mobileNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (firstName != null) {
			builder.append(firstName);
			builder.append(" ");
		}
		if (lastName != null) {
			builder.append(lastName);

		}

		if (homeNo != null) {
			builder.append(" : Home ");
			builder.append(homeNo);
		}

		if (mobileNo != null) {
			builder.append(" : Mobile ");
			builder.append(mobileNo);
		}

		return builder.toString();

	}

}
