package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import be.jp.dao.SchoolSkyConnection;
import be.jp.dao.SkierDAO;

public class Skier extends Person implements Serializable {
	private static final long serialVersionUID = -1385894825879357159L;
	private ArrayList<Booking> listBooking = new ArrayList<Booking>();

	public ArrayList<Booking> getListBooking() {
		return listBooking;
	}

	public void setListBooking(ArrayList<Booking> listBooking) {
		this.listBooking = listBooking;
	}

	public Skier() {}
	
	public Skier(String name, String firstname, String email, String phone, Date dob, 
			String street, String houseNbr, String city, String postalCode) {
		super(name, firstname, email, phone, dob, street, houseNbr, city, postalCode);
	}
	
	public Skier(String name, String firstname, String email, String phone, Date dob, 
			String street, String houseNbr, String city, String postalCode , int id) {
		this(name, firstname, email, phone, dob, street, houseNbr, city, postalCode);
		this.id = id;
	}
	
	public String creatSkier() {
		String message = this.verifietForm();
		if(message == null) {
			SkierDAO s = new SkierDAO(SchoolSkyConnection.getInstance());
			if(s.create(this))
				JOptionPane.showMessageDialog(null, "A " + name + " " + firstname + " has been created !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Creation error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return message;
	}
	
	public static ArrayList<Skier> findsAllSkier(){
		SkierDAO s = new SkierDAO(SchoolSkyConnection.getInstance());
		return s.finds();
	}
	
	public boolean deleteSkier() {
		SkierDAO s = new SkierDAO(SchoolSkyConnection.getInstance());
		if(s.delete(this)) {
			JOptionPane.showMessageDialog(null, "The skier has been deleted !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Deletion error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean updateSkier(Skier skier) {
		String message = skier.verifietForm();
		if(message == null) {
			setName(skier.getName());
			setFirstname(skier.getFirstname());
			setEmail(skier.getEmail());
			setPhone(skier.getPhone());
			setDob(skier.getDob());
			setStreet(skier.getStreet());
			setHouseNbr(skier.getHouseNbr());
			setCity(skier.getCity());
			setPostalCode(skier.getPostalCode());
			SkierDAO s = new SkierDAO(SchoolSkyConnection.getInstance());
			if(s.update(this)) {
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
	
	public void addBooking(Booking booking) {
		if(!listBooking.contains(booking))
			listBooking.add(booking);
	}
}
