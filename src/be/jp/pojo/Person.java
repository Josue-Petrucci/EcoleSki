package be.jp.pojo;

import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable {
	private static final long serialVersionUID = -171294985381962159L;
	protected int id;
	protected String name;
	protected String firstname;
	protected String email;
	protected String phone;
	protected Date dob;
	protected String street;
	protected String houseNbr;
	protected String city;
	protected String postalCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getHouseNbr() {
		return houseNbr;
	}
	public void setHouseNbr(String houseNbr) {
		this.houseNbr = houseNbr;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public Person() {}
	
	public Person(String name, String firstname, String email, String phone, Date dob, 
			String street, String houseNbr, String city, String postalCode) {
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.street = street;
		this.houseNbr = houseNbr;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public String verifietForm() {
		if(name.isEmpty()) {
			return "Name is required!";
		} else if(firstname.isEmpty()) {
			return "Firstname is required!";
		} else if(email.isEmpty()) {
			return "Email is required!";
		} else if(phone.isEmpty()) {
			return "Phone number is required!";
		}   else if(dob == null) {
			return "Date of birth is required!";
		} else if(street.isEmpty()) {
			return "the street is required!";
		} else if(houseNbr.isEmpty()) {
			return "the house number is required!";
		} else if(city.isEmpty()) {
			return "the city is required!";
		} else if(postalCode.isEmpty()) {
			return "the postal code is required!";
		}		
		if (!name.matches("[a-zA-ZÀ-ÿ]+")) {
			return "The name must contain only letters!";
        } else if(!firstname.matches("[a-zA-ZÀ-ÿ]+")) {
        	return "The firstname must contain only letters!";
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        	return "Invalid email format!";
        } else if (!phone.matches("^(\\+\\d{1,3}|00\\d{1,3})?[- ]?(0?\\d{1,2})?([- ]?\\d{2}){4}$")) {
        	return "Invalid Phone number format!";
        } else if(!street.matches("[A-Za-zÀ-ÿ ]+")) {
        	return "The street must contain only letters!";
        } else if(!houseNbr.matches("[1-9][0-9]{0,2}")) {
        	return "The house number must contain only number!";
        } else if(!city.matches("[A-Za-zÀ-ÿ ]+")) {
        	return "The city must contain only letters!";
        } else if(!postalCode.matches("[1-9][0-9]{0,3}")) {
        	return "The postal code must contain only number!";
        }
		return	null;
	}
	
	@Override
	public boolean equals(Object obj) {
		Person p = null;
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		p = (Person)obj;
		if(p.getName().equals(this.getName()) & p.getFirstname().equals(this.getFirstname())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode() + getFirstname().hashCode() + getEmail().hashCode() + getPhone().hashCode();
	}
	
	@Override
	public String toString() {
		return "Id: " + id + "\nName: " + name + " " + firstname + "\nDate of birth: " + dob + "\nEmail: " + email + "\nPhone: " + phone;
	}
}
