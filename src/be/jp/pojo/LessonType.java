package be.jp.pojo;

import java.io.Serializable;

public class LessonType implements Serializable {
	private static final long serialVersionUID = 2000590571815708816L;
	private int id;
	private String levelName;
	private int price;
	private Accreditation accreditation;
	
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Accreditation getAccreditation() {
		return accreditation;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public LessonType() {}
	
	public LessonType(int id, String levelName, int price, Accreditation accreditation) {
		this.id = id;
		this.levelName = levelName;
		this.price = price;
		this.accreditation = accreditation;
	}
}
