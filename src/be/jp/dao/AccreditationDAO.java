package be.jp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jp.pojo.Accreditation;
import be.jp.pojo.LessonType;

public class AccreditationDAO extends DAO<Accreditation> {

	public AccreditationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Accreditation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Accreditation obj) {
		// TODO Auto-generated method stub
		return false;
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
			String query = "SELECT a.id AS aid, name, l.id AS lid, levelName, price FROM ES_AccreditationLessontype al,"
					     + " ES_Accreditation a, ES_LessonType l WHERE al.aid = a.id AND al.ltid = l.id ORDER BY a.id, l.id";
			pst = conn.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int acid = rs.getInt("aid");
				LessonType lt = new LessonType(rs.getInt("lid"), rs.getString("levelName"), rs.getInt("price"));
				
				Accreditation existeAc = null;
				for(Accreditation a:acs) {
					if(a.getId() == acid) {
						existeAc = a;
						break;
					}
				}
				
				if(existeAc != null) {
					existeAc.addLessonType(lt);
				} else {
					Accreditation ac = new Accreditation(acid, rs.getString("name"), lt);
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
