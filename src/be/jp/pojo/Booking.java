package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import be.jp.dao.BookingDAO;
import be.jp.dao.SchoolSkyConnection;

public class Booking implements Serializable{
	private static final long serialVersionUID = 8254867168558075290L;
	private int id;
	private boolean hasInsurance;
	private String schedules;
	private Date firstDate;
	private Date lastDate;
	private Period period;
	private Lesson lesson;
	private Skier skier;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getHasInsurance() {
		return hasInsurance;
	}
	
	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	
	public String getSchedules() {
		return schedules;
	}
	
	public void setSchedules(String schedules) {
		this.schedules = schedules;
	}
	
	public Date getFirstDate() {
		return firstDate;
	}
	
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	
	public Date getLastDate() {
		return lastDate;
	}
	
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
	public Period getPeriod() {
		return period;
	}
	
	public void setPeriod(Period period) {
		this.period = period;
	}
	
	public Lesson getLesson() {
		return lesson;
	}
	
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public Skier getSkier() {
		return skier;
	}
	
	public void setSkier(Skier skier) {
		this.skier = skier;
	}
	
	public Booking() {}
	
	public Booking(boolean hasInsurance, String schedules, Date firstDate, Lesson lesson, Skier skier) {
		this.hasInsurance = hasInsurance;
		this.schedules = schedules;
		this.firstDate = firstDate;
		if(lesson.getIsPrivate()) {
			this.lastDate = firstDate;
		} else {
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(firstDate);
		    calendar.add(Calendar.DAY_OF_MONTH, 6);
		    this.lastDate = calendar.getTime();
		}
		this.period = configPeriod();
		this.lesson = lesson;
		this.skier = skier;
	}
	
	public Booking(int id, boolean hasInsurance, String schedules, Date firstDate, Date lastDate, Period period, int idLesson, int idSkier) {
		this.id = id;
		this.hasInsurance = hasInsurance;
		this.schedules = schedules;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.period = period;
		this.period.addBooking(this);
		configLessonSkier(idLesson, idSkier);
	}
	
	public Booking(String schedule, Lesson lesson, boolean hasInsurance) {
		this.schedules = schedule;
		this.lesson = lesson;
		this.hasInsurance = hasInsurance;
	}
	
	private void configLessonSkier(int idLesson, int idSkier) {
		for(Lesson l:Lesson.getAllLesson()) {
			if(l.getId() == idLesson) {
				this.lesson = l;
				this.lesson.addBooking(this);
			}
		}
		
		for(Skier s:Skier.findsAllSkier()) {
			if(s.getId() == idSkier) {
				this.skier = s;
				this.skier.addBooking(this);
			}
		}
	}
	
	private Period configPeriod() {
		Period period = null;
		for(Period p:Period.getAllPeriod()) {
			if(this.firstDate.compareTo(p.getStartDate()) >= 0 && this.firstDate.compareTo(p.getEndDate()) <= 0) {
				period = p;
			}
		}
		return period;
	}
	
	public boolean createBooking() {
		BookingDAO b = new BookingDAO(SchoolSkyConnection.getInstance());
		return b.create(this);
	}
	
	public static ArrayList<Booking> getAllBooking(){
		BookingDAO b = new BookingDAO(SchoolSkyConnection.getInstance());
		return b.finds();
	}
	
	public double calculatePrice() {
		if(this.hasInsurance) {
			if(!this.lesson.getIsPrivate()) {
				if(this.schedules.equals("Les deux : de 9:00 à 12:00 et de 14:00 à 17:00")) {
					return (20 + ((lesson.getLessonPrice(0)*2) - (lesson.getLessonPrice(0)*0.15)));
				} else {
					return (20 + lesson.getLessonPrice(0));
				}
			} else {
				if(this.schedules.equals("de 12:00 à 14:00")) {
					return (20 + lesson.getLessonPrice(2));
				} else {
					return (20 + lesson.getLessonPrice(1));
				}
			}
		} else {
			if(!this.lesson.getIsPrivate()) {
				if(this.schedules.equals("Les deux : de 9:00 à 12:00 et de 14:00 à 17:00")) {
					return ((lesson.getLessonPrice(0)*2) -(lesson.getLessonPrice(0)*0.15));
				} else {
					return (lesson.getLessonPrice(0));
				}
			} else {
				if(this.schedules.equals("de 12:00 à 14:00")) {
					return (lesson.getLessonPrice(2));
				} else {
					return (lesson.getLessonPrice(1));
				}
			}
		}
	}
	
	public boolean deleteBooking() {
		BookingDAO b = new BookingDAO(SchoolSkyConnection.getInstance());
		return b.delete(this);
	}
	
	public boolean hasPlace() {
	    int nbr = 1;
	    for (Booking b : getAllBooking()) {
	        if (this.getLesson().getId() == b.getLesson().getId() && 
	            this.getFirstDate().equals(b.getFirstDate()) && 
	            this.getSchedules().equals(b.getSchedules())) {
	            nbr++;
	        }
	    }
	    return nbr < this.getLesson().getMaxBookings();
	}
}
