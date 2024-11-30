package be.jp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jp.pojo.Lesson;

public class LessonDAO extends DAO<Lesson> {

	public LessonDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Lesson obj) {
		boolean result = false;
		try {
			String query = "INSERT INTO es_lesson(minbookings,maxbookings,isprivate,inid,ltid) VALUES(?,?,?,?,?)";
			pst = conn.prepareStatement(query);
			
			pst.setInt(1, obj.getMinBookings());
			pst.setInt(2, obj.getMaxBookings());
			pst.setBoolean(3, obj.getIsPrivate());
			pst.setInt(4, obj.getInstructor().getId());
			pst.setInt(5, obj.getLessonType().getId());
			
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
	public boolean delete(Lesson obj) {
		boolean result = false;
		try{
			String query = "DELETE FROM es_lesson WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, obj.getId());
			pst.executeUpdate();
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public boolean update(Lesson obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Lesson find(Lesson obj) {
		Lesson lesson = null;
		try {
			String query = "SELECT id FROM es_lesson WHERE inid=?";
			pst = conn.prepareStatement(query);
			
			pst.setInt(1, obj.getInstructor().getId());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				lesson = new Lesson(rs.getInt("id"));
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
		return lesson;
	}

	@Override
	public ArrayList<Lesson> finds() {
		ArrayList<Lesson> listLesson = new ArrayList<Lesson>();
		Lesson lesson = null;
		try {
			String query = "SELECT * FROM es_lesson ORDER BY id ASC";
			pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				lesson = new Lesson(
						rs.getInt("id"),
						rs.getInt("minbookings"),
						rs.getInt("maxbookings"),
						rs.getBoolean("isprivate"),
						rs.getInt("inid"),
						rs.getInt("ltid"));
				listLesson.add(lesson);
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
		return listLesson;
	}

}
