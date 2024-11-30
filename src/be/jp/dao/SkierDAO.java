package be.jp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		boolean result = false;
		try{
			String query = "DELETE FROM es_skier WHERE id = ?";
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
	public boolean update(Skier obj) {
		boolean result = false;
		try {
			String query = "UPDATE es_skier SET name = ?, firstname = ?, email = ?, phone = ?, dob = ?, street = ?, houseNbr = ?, city = ?, postalCode = ? WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, obj.getName());
	        pst.setString(2, obj.getFirstname());
	        pst.setString(3, obj.getEmail());
	        pst.setString(4, obj.getPhone());
	        pst.setDate(5, new java.sql.Date(obj.getDob().getTime()));
	        pst.setString(6, obj.getStreet());
	        pst.setInt(7, Integer.parseInt(obj.getHouseNbr()));
	        pst.setString(8, obj.getCity());
	        pst.setInt(9, Integer.parseInt(obj.getPostalCode()));
	        pst.setInt(10, obj.getId());
	        pst.executeUpdate();
	        result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public Skier find(Skier obj) {
		Skier skier = null;
		try {
			String query = "SELECT id FROM es_skier WHERE phone=?";
			pst = conn.prepareStatement(query);
			
			pst.setString(1, obj.getPhone());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				skier = new Skier(rs.getInt("id"));
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
		return skier;
	}

	@Override
	public ArrayList<Skier> finds() {
		ArrayList<Skier> skiers = new ArrayList<Skier>();
		Skier s = null;
		try {
			String query = "SELECT * FROM es_skier ORDER BY name ASC, firstname ASC";
			pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				s = new Skier(
						rs.getString("name"),
						rs.getString("firstname"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getDate("dob"),
						rs.getString("street"),
						Integer.toString(rs.getInt("houseNbr")),
						rs.getString("city"),
						Integer.toString(rs.getInt("postalCode")),
						rs.getInt("id"));
				
				skiers.add(s);
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
		return skiers;
	}

}
