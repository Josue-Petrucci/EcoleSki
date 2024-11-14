package be.jp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Instructor;
import be.jp.pojo.LessonType;

public class InstructorDAO extends DAO<Instructor> {

	public InstructorDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Instructor obj) {
		boolean result = false;
		try {
			String query = "INSERT INTO es_instructor(name,firstname,email,phone,dob,street,houseNbr,city,postalCode) VALUES(?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(query);
			
			pst.setString(1, obj.getName());
			pst.setString(2, obj.getFirstname());
			pst.setString(3, obj.getEmail());
			pst.setString(4, obj.getPhone());
			pst.setDate(5, new Date(obj.getDob().getTime()));
			pst.setString(6, obj.getStreet());
			pst.setInt(7, Integer.parseInt(obj.getHouseNbr()));
			pst.setString(8, obj.getCity());
			pst.setInt(9, Integer.parseInt(obj.getPostalCode()));
			
			int rowsAffected = pst.executeUpdate();
			if(rowsAffected > 0) {			
				ResultSet rs = pst.executeQuery("SELECT instructor_seq.CURRVAL FROM dual");
				Integer instructorId = -1;
				if (rs.next()) {
		            instructorId = rs.getInt(1);
		        }
				if (instructorId != -1) {
		            String queryAssociation = "INSERT INTO ES_InstructorAccreditation(inid, aid) VALUES (?,?)";
		            PreparedStatement pstAssociation = conn.prepareStatement(queryAssociation);
		            pstAssociation.setInt(1, instructorId);
		            pstAssociation.setInt(2, obj.getListAccreditation().get(0).getId());

		            pstAssociation.executeUpdate();
		            pstAssociation.close();
		        }
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
	public boolean delete(Instructor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Instructor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Instructor find(Instructor obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Instructor> finds() {
		ArrayList<Instructor> is = new ArrayList<Instructor>();
		Accreditation ac = null;
		Instructor in = null;
		try {
			String query = "SELECT i.Id AS instructor_id,i.name AS instructor_name,i.firstname AS instructor_firstname,i.email AS instructor_email,"
					+ "i.phone AS instructor_phone,i.dob AS instructor_dob,i.street AS instructor_street,i.houseNbr AS instructor_houseNbr,"
					+ "i.city AS instructor_city,i.postalCode AS instructor_postalCode,"
					+ "a.id AS accreditation_id,a.name AS accreditation_name, "
					+ "lt.id AS lessontype_id,lt.levelName AS lessontype_levelName,lt.price AS lessontype_price "
					+ "FROM ES_Instructor i,ES_InstructorAccreditation ia,ES_Accreditation a,ES_AccreditationLessontype al,ES_LessonType lt "
					+ "WHERE ia.inid = i.Id AND ia.aid = a.id AND al.aid = a.id AND al.ltid = lt.id "
					+ "ORDER BY i.name, i.firstname, a.id, lt.id";
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			int lastAcid = -1, lastInid = -1;
			while(rs.next()) {
				int acid = rs.getInt("accreditation_id");
				int inid = rs.getInt("instructor_id");
				
				LessonType lt = new LessonType(rs.getInt("lessontype_id"), rs.getString("lessontype_levelName"), rs.getInt("lessontype_price"));				
				
				if(acid == lastAcid) {
					ac.addLessonType(lt);
				} else {
					ac = new Accreditation(acid, rs.getString("accreditation_name"), lt);
				}
				lastAcid = rs.getInt("accreditation_id");
				
				if(inid == lastInid) {
					in.addAccreditation(ac);
				} else {
					in = new Instructor(
							rs.getString("instructor_name"),
							rs.getString("instructor_firstname"),
							rs.getString("instructor_email"),
							rs.getString("instructor_phone"),
							rs.getDate("instructor_dob"),
							rs.getString("instructor_street"),
							rs.getString("instructor_houseNbr"),
							rs.getString("instructor_city"),
							rs.getString("instructor_postalCode"),
							ac,
							rs.getInt("instructor_id"));
					is.add(in);
				}
				lastInid = rs.getInt("instructor_id");
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
		return is;
	}

}
