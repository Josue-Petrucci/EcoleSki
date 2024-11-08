package be.jp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jp.pojo.Skier;

public class SkierDAO extends DAO<Skier> {

	public SkierDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Skier obj) {
		boolean result = false;
		try {
			String query = "INSERT INTO es_skier(name,firstname,email,phone,dob,street,houseNbr,city,postalCode) VALUES(?,?,?,?,?,?,?,?,?)";
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
	public boolean delete(Skier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Skier obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Skier find(Skier obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Skier> finds() {
		// TODO Auto-generated method stub
		return null;
	}

}