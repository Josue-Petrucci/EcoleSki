package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class LessonType implements Serializable {
	private static final long serialVersionUID = 2000590571815708816L;
	private int id;
	private String levelName;
	private double price;
	private Accreditation accreditation;
	private ArrayList<Lesson> listLesson = new ArrayList<Lesson>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLevelName() {
		return levelName;
	}
	
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Accreditation getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public ArrayList<Lesson> getListLesson() {
		return listLesson;
	}

	public void setListLesson(ArrayList<Lesson> listLesson) {
		this.listLesson = listLesson;
	}

	public LessonType() {}
	
	public LessonType(int id, String levelName, double price, Accreditation accreditation) {
		this.id = id;
		this.levelName = levelName;
		this.price = price;
		this.accreditation = accreditation;
	}
	
	public void addLesson(Lesson l) {
		if(!listLesson.contains(l))
			listLesson.add(l);
	}
	
	@Override
	public boolean equals(Object obj) {
		LessonType lt = null;
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		lt = (LessonType)obj;
		if(lt.getLevelName().equals(this.getLevelName()) & lt.getId() == this.getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.getLevelName().hashCode();
	}
}
