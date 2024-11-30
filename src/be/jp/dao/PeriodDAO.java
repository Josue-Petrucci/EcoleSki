package be.jp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jp.pojo.Period;

public class PeriodDAO extends DAO<Period> {

	public PeriodDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Period obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Period obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Period obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Period find(Period obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Period> finds() {
		ArrayList<Period> periods = new ArrayList<Period>();
		Period p = null;
		try {
			String query = "SELECT * FROM es_period ORDER BY id ASC";
			pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				p = new Period(
						rs.getInt("id"),
						rs.getDate("startDate"),
						rs.getDate("endDate"),
						rs.getBoolean("isVacation"));
				periods.add(p);
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
		return periods;
	}

}
