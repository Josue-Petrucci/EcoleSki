package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class LessonType implements Serializable {
	private static final long serialVersionUID = 2000590571815708816L;
	private int id;
	private String levelName;
	private int price;
	private ArrayList<Accreditation> listAccreditation = new ArrayList<Accreditation>();
	
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
	
	public ArrayList<Accreditation> getListAccreditation() {
		return listAccreditation;
	}
	
	public void setListAccreditation(ArrayList<Accreditation> listAccreditation) {
		this.listAccreditation = listAccreditation;
	}
	
	public LessonType() {}
	
	public LessonType(int id, String levelName, int price) {
		this.id = id;
		this.levelName = levelName;
		this.price = price;
	}
	
	public void addAccreditation(Accreditation ac) {
		if(!listAccreditation.contains(ac)) {
			listAccreditation.add(ac);
			ac.addLessonType(this);
		}
	}
	
}
