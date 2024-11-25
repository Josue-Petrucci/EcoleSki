package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import be.jp.dao.SchoolSkyConnection;
import be.jp.dao.AccreditationDAO;
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
	
	public Instructor(String name, String firstname, String email, String phone, Date dob, 
			String street, String houseNbr, String city, String postalCode, Accreditation accreditation, int id) {
		this(name, firstname, email, phone, dob, street, houseNbr, city, postalCode, accreditation);
		this.id = id;
	}
	
	public Instructor(String name, String firstname, String email, String phone, Date dob, 
			String street, String houseNbr, String city, String postalCode) {
		super(name, firstname, email, phone, dob, street, houseNbr, city, postalCode);
	}

	public void addAccreditation(Accreditation ac) {
		if(!listAccreditation.contains(ac)) {
			listAccreditation.add(ac);
			ac.addInstructor(this);
		}
	}
	
	public void deleteAccreditation(Accreditation ac) {
		if(listAccreditation.contains(ac))
			listAccreditation.remove(ac);
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
	
	public static ArrayList<Instructor> getAllInstructor(){
		InstructorDAO i = new InstructorDAO(SchoolSkyConnection.getInstance());
		return i.finds();
	}
	
	public boolean isAccreditate(Accreditation accreditation) {
		for(Accreditation a:listAccreditation) {
			if(a.getId() == accreditation.getId() & a.getName().equals(accreditation.getName()))
				return true;
		}
		return false;
	}
	
	public boolean updateInstructor(Instructor instructor) {
		String message = instructor.verifietForm();
		if(message == null) {
			setName(instructor.getName());
			setFirstname(instructor.getFirstname());
			setEmail(instructor.getEmail());
			setPhone(instructor.getPhone());
			setDob(instructor.getDob());
			setStreet(instructor.getStreet());
			setHouseNbr(instructor.getHouseNbr());
			setCity(instructor.getCity());
			setPostalCode(instructor.getPostalCode());
			InstructorDAO i = new InstructorDAO(SchoolSkyConnection.getInstance());
			if(i.update(this)) {
				JOptionPane.showMessageDialog(null, "The person has been update !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, message, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	public boolean deleteInstructor() {
		InstructorDAO i = new InstructorDAO(SchoolSkyConnection.getInstance());
		if(i.delete(this)) {
			JOptionPane.showMessageDialog(null, "The person has been deleted !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Deletion error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void manageAccreditation(String code, Accreditation accreditation) {
		AccreditationDAO a = new AccreditationDAO(SchoolSkyConnection.getInstance());
		if(code.equals("Add")) {
			if(a.AddAccreditation(accreditation, this)) {
				addAccreditation(accreditation);
				JOptionPane.showMessageDialog(null, "The Accreditation has been Add !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Deletion error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else {
			if(listAccreditation.size() > 1) {
				if(a.deleteAccreditation(accreditation, this)) {
					deleteAccreditation(accreditation);
					JOptionPane.showMessageDialog(null, "The Accreditation has been deleted !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Deletion error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Cannot be deleted minimum 1 accreditation !", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
