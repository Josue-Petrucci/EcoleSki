package be.jp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import be.jp.dao.PeriodDAO;
import be.jp.dao.SchoolSkyConnection;

public class Period implements Serializable {
	private static final long serialVersionUID = -2262477731232694192L;
	private int id;
	private Date startDate;
	private Date endDate;
	private boolean isVacation;
	private ArrayList<Booking> listBooking = new ArrayList<Booking>();

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public boolean getIsVacation() {
		return isVacation;
	}
	
	public void setIsVacation(boolean isVacation) {
		this.isVacation = isVacation;
	}

	public ArrayList<Booking> getListBooking() {
		return listBooking;
	}

	public void setListBooking(ArrayList<Booking> listBooking) {
		this.listBooking = listBooking;
	}
	
	public Period() {}
	
	public Period(int id, Date startDate, Date endDate, boolean isVacation) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isVacation = isVacation;
	}
	
	public static ArrayList<Period> getAllPeriod() {
		PeriodDAO p = new PeriodDAO(SchoolSkyConnection.getInstance());
		return p.finds();
	}
	
	public void addBooking(Booking booking) {
		if(!listBooking.contains(booking))
			listBooking.add(booking);
	}
	
	@Override
	public boolean equals(Object obj) {
		Period p = null;
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		p = (Period)obj;
		if(p.getId() == this.getId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Integer.toString(this.getId()).hashCode();
	}
}
