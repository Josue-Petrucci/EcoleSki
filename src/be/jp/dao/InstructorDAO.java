package be.jp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.jp.pojo.Instructor;

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
		// TODO Auto-generated method stub
		return null;
	}

}
