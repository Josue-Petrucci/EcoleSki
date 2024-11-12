package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import be.jp.dao.SchoolSkyConnection;
import be.jp.dao.InstructorDAO;

public class Instructor extends Person implements Serializable{
	private static final long serialVersionUID = 8317097111846822301L;
	private ArrayList<Accreditation> listAccreditation;
	
	public ArrayList<Accreditation> getListAccreditation() {
		return listAccreditation;
	}

	public void setListAccreditation(ArrayList<Accreditation> listAccreditation) {
		this.listAccreditation = listAccreditation;
	}

	public Instructor() {}
	
	public Instructor(String name, String firstname, String email, String phone, Date dob, 
			String street, String houseNbr, String city, String postalCode, Accreditation accreditation) {
		super(name, firstname, email, phone, dob, street, houseNbr, city, postalCode);
		listAccreditation = new ArrayList<Accreditation>();
		addAccreditation(accreditation);
	}

	public void addAccreditation(Accreditation ac) {
		if(!listAccreditation.contains(ac)) {
			listAccreditation.add(ac);
			ac.addInstructor(this);
		}
	}
	
	public String creatInstructor() {
		String message = this.verifietForm();
		if(message == null) {
			InstructorDAO i = new InstructorDAO(SchoolSkyConnection.getInstance());
			if(i.create(this))
				JOptionPane.showMessageDialog(null, "A " + name + " " + firstname + " has been created !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Creation error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return message;
	}
}
