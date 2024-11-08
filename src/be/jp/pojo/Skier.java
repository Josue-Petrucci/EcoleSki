package be.jp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.swing.JOptionPane;

import be.jp.dao.SchoolSkyConnection;
import be.jp.dao.SkierDAO;

public class Skier extends Person implements Serializable {
	private static final long serialVersionUID = -1385894825879357159L;

	public Skier() {}
	
	public Skier(String name, String firstname, String email, String phoneNbr, Date dob, 
			String street, String houseNbr, String city, String postalCode) {
		super(name, firstname, email, phoneNbr, dob, street, houseNbr, city, postalCode);
	}
	
	public String creatSkier() {
		String message = null;
		
		if(name.isEmpty()) {
			message = "Name is required!";
		} else if(firstname.isEmpty()) {
			message = "Firstname is required!";
		} else if(email.isEmpty()) {
			message = "Email is required!";
		} else if(phone.isEmpty()) {
			message = "Phone number is required!";
		}   else if(dob == null) {
			message = "Date of birth is required!";
		} else if(street.isEmpty()) {
			message = "the street is required!";
		} else if(houseNbr.isEmpty()) {
			message = "the house number is required!";
		} else if(city.isEmpty()) {
			message = "the city is required!";
		} else if(postalCode.isEmpty()) {
			message = "the postal code is required!";
		}
		
		if (!name.matches("[a-zA-ZÀ-ÿ]+")) {
            message = "The name must contain only letters!";
        } else if(!firstname.matches("[a-zA-ZÀ-ÿ]+")) {
        	message = "The firstname must contain only letters!";
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            message = "Invalid email format!";
        } else if (!phone.matches("^(\\+\\d{1,3}|00\\d{1,3})?[- ]?(0?\\d{1,2})?([- ]?\\d{2}){4}$")) {
            message = "Invalid Phone number format!";
        } else if(!street.matches("[A-Za-zÀ-ÿ ]+")) {
        	message = "The street must contain only letters!";
        } else if(!houseNbr.matches("[1-9][0-9]{0,2}")) {
        	message = "The house number must contain only number!";
        } else if(!city.matches("[A-Za-zÀ-ÿ ]+")) {
        	message = "The city must contain only letters!";
        } else if(!postalCode.matches("[1-9][0-9]{0,3}")) {
        	message = "The postal code must contain only number!";
        }
		
		if(message == null) {
			SkierDAO p = new SkierDAO(SchoolSkyConnection.getInstance());
			if(p.create(this))
				JOptionPane.showMessageDialog(null, "A " + name + " " + firstname + " has been created !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Creation error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return message;
	}
}
