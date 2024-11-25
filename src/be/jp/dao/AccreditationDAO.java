package be.jp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jp.pojo.Accreditation;
import be.jp.pojo.Instructor;

public class AccreditationDAO extends DAO<Accreditation> {

	public AccreditationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Accreditation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean AddAccreditation(Accreditation ac, Instructor in) {
		boolean result = false;
		try {
			String query = "INSERT INTO es_instructoraccreditation(inid, aid) VALUES(?,?)";
			pst = conn.prepareStatement(query);
			
			pst.setInt(1, in.getId());
			pst.setInt(2, ac.getId());
			
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
	public boolean delete(Accreditation obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean deleteAccreditation(Accreditation ac, Instructor in) {
		boolean result = false;
		try{
			String query = "DELETE FROM es_instructoraccreditation WHERE inid = ? AND aid = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, in.getId());
			pst.setInt(2, ac.getId());
			pst.executeUpdate();
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public boolean update(Accreditation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Accreditation find(Accreditation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Accreditation> finds() {
		ArrayList<Accreditation> acs = new ArrayList<Accreditation>();
		try {
			String query = "SELECT a.id AS aid, name, l.id AS lid, levelName, price FROM ES_Accreditation a, ES_LessonType l"
					     + " WHERE a.id = l.idAccreditation ORDER BY a.id, l.id";
			pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int acid = rs.getInt("aid");
				
				Accreditation existeAc = null;
				for(Accreditation a:acs) {
					if(a.getId() == acid) {
						existeAc = a;
					}
				}
				
				if(existeAc != null) {
					existeAc.addLessonType(rs.getInt("lid"), rs.getString("levelName"), rs.getInt("price"));
				} else {
					Accreditation ac = new Accreditation(acid, rs.getString("name"), rs.getInt("lid"), rs.getString("levelName"), rs.getInt("price"));
					acs.add(ac);
				}
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
		return acs;
	}

}
