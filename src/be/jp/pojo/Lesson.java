package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.jp.dao.InstructorDAO;
import be.jp.dao.LessonDAO;
import be.jp.dao.SchoolSkyConnection;

public class Lesson implements Serializable {
	private static final long serialVersionUID = -324482670891978020L;
	private int id;
	private int minBookings;
	private int maxBookings;
	private Instructor instructor;
	private LessonType lessonType;
	private boolean isPrivate;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMinBookings() {
		return minBookings;
	}
	
	public void setMinBookings(int minBookings) {
		this.minBookings = minBookings;
	}
	
	public int getMaxBookings() {
		return maxBookings;
	}
	
	public void setMaxBookings(int maxBookings) {
		this.maxBookings = maxBookings;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public LessonType getLessonType() {
		return lessonType;
	}
	
	public void setLessonType(LessonType lessonType) {
		this.lessonType = lessonType;
	}

	public boolean getIsPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public Lesson() {}
	
	public Lesson(int id) { this.id = id;}
	
	public Lesson(Instructor instructor, LessonType lessonType, boolean isPrivate) {
		this.instructor = instructor;
		this.lessonType = lessonType;
		this.isPrivate = isPrivate;
		manageMinMaxBookings();
	}
	
	public Lesson(int id, int minBookings, int maxBookings, boolean isPrivate, int idInstructor, int idLessonType) {
		this(id);
		this.minBookings = minBookings;
		this.maxBookings = maxBookings;
		this.isPrivate = isPrivate;
		configInstructorLessonType(idInstructor, idLessonType);
	}
	
	private void configInstructorLessonType(int idInstructor, int idLessonType) {
		InstructorDAO i = new InstructorDAO(SchoolSkyConnection.getInstance());
		for(Instructor in:i.finds()) {
			if(in.getId() == idInstructor) {
				this.instructor = in;
				for(Accreditation a: in.getListAccreditation()) {
					for(LessonType lt:a.getListLessonType()) {
						if(lt.getId() == idLessonType)
							this.lessonType = lt;
					}
				}
			}
		}
	}
	
	private void manageMinMaxBookings() {
		if(lessonType.getAccreditation().getId() == 1 || lessonType.getAccreditation().getId() == 2) {
			this.minBookings = 5;
			this.maxBookings = 8;
		} else {
			if(lessonType.getAccreditation().getId() == 3 || lessonType.getAccreditation().getId() == 4) {
				if(lessonType.getLevelName().equals("Compétition") || lessonType.getLevelName().equals("Hors-piste")) {
					this.minBookings = 5;
					this.maxBookings = 8;
				} else {
					this.minBookings = 6;
					this.maxBookings = 10;
				}
			} else {
				this.minBookings = 6;
				this.maxBookings = 10;
			}
		}
	}
	
	public double getLessonPrice(int nbrHours) {
		if(this.isPrivate) {
			if(nbrHours == 1)
				return 60;
			else if(nbrHours == 2)
				return 90;
		}
		return lessonType.getPrice();
	}
	
	public Lesson isExist() {
		LessonDAO l = new LessonDAO(SchoolSkyConnection.getInstance());
		return l.find(this);
	}
	
	public boolean createLesson() {
		LessonDAO l = new LessonDAO(SchoolSkyConnection.getInstance());
		return l.create(this);
	}
	
	public static ArrayList<Lesson> getAllLesson(){
		LessonDAO l = new LessonDAO(SchoolSkyConnection.getInstance());
		return l.finds();
	}
	
	public boolean deleteLesson() {
		LessonDAO l = new LessonDAO(SchoolSkyConnection.getInstance());
		if(l.delete(this)) {
			JOptionPane.showMessageDialog(null, "The Lesson has been deleted !", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Deletion error, contact the IT manager !", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
