package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.jp.dao.AccreditationDAO;
import be.jp.dao.SchoolSkyConnection;

public class Accreditation implements Serializable {
	private static final long serialVersionUID = 5391555017131504202L;
	private int id;
	private String name;
	private ArrayList<Instructor> listInstructor = new ArrayList<Instructor>();
	private ArrayList<LessonType> listLessonType = new ArrayList<LessonType>();

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
	
	public ArrayList<Instructor> getListInstructor() {
		return listInstructor;
	}

	public void setListInstructor(ArrayList<Instructor> listInstructor) {
		this.listInstructor = listInstructor;
	}

	public ArrayList<LessonType> getListLessonType() {
		return listLessonType;
	}

	public void setListLessonType(ArrayList<LessonType> listLessonType) {
		this.listLessonType = listLessonType;
	}

	public Accreditation() {}
	
	public Accreditation(int id, String name, LessonType lessonType) {
		this.id = id;
		this.name = name;
		addLessonType(lessonType);
	}
	
	public void addLessonType(LessonType lt) {
		if(!listLessonType.contains(lt)) {
			listLessonType.add(lt);
			lt.addAccreditation(this);
		}
	}
	
	public void addInstructor(Instructor inst) {
		if(!listInstructor.contains(inst)) {
			listInstructor.add(inst);
		}
	}
	
	public static ArrayList<Accreditation> getAllAcreditation() {
		AccreditationDAO ac = new AccreditationDAO(SchoolSkyConnection.getInstance());
		return ac.finds();
	}

}
