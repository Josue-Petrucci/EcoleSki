package be.jp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jp.pojo.Booking;
import be.jp.pojo.Period;

public class BookingDAO extends DAO<Booking>  {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking obj) {
		boolean result = false;
		try {
			String query = "INSERT INTO es_booking(hasInsurance,schedules,firstDate,lastDate,pid,lid,sid) VALUES(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(query);
			
			pst.setBoolean(1, obj.getHasInsurance());
			pst.setString(2, obj.getSchedules());
			pst.setDate(3, new Date(obj.getFirstDate().getTime()));
			pst.setDate(4, new Date(obj.getLastDate().getTime()));
			pst.setInt(5, obj.getPeriod().getId());
			pst.setInt(6, obj.getLesson().getId());
			pst.setInt(7, obj.getSkier().getId());
			
			int rowsAffected = pst.executeUpdate();
			if(rowsAffected > 0) {
				result = true;
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
                if (pst != null) pst.close();                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		return result;
	}

	@Override
	public boolean delete(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking find(Booking obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> finds() {
		ArrayList<Booking> listBooking = new ArrayList<Booking>();
		Booking booking = null;
		try {
			String query = "SELECT b.id AS idbooking,hasInsurance,schedules,firstDate,lastDate,lid,sid,pid,startDate,endDate,isVacation "
					     + "FROM es_booking b, es_period p WHERE b.pid=p.id ORDER BY id ASC";
			pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				booking = new Booking(
						rs.getInt("idbooking"),
						rs.getBoolean("hasInsurance"),
						rs.getString("schedules"),
						rs.getDate("firstDate"),
						rs.getDate("lastDate"),
						new Period(
								rs.getInt("pid"),
								rs.getDate("startDate"),
								rs.getDate("endDate"),
								rs.getBoolean("isVacation")),
						rs.getInt("lid"),
						rs.getInt("sid"));
				listBooking.add(booking);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
                if (pst != null) pst.close();                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}
		return listBooking;
	}

}
