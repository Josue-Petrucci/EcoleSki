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
