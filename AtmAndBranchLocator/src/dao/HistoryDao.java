package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utility.Connections;
import bean.HistoryBean;
import bean.LoginBean;

public class HistoryDao {
	public static boolean updateHistory(LoginBean bean, HistoryBean hb){
		boolean status = true;
		
		try{
			Connection con = Connections.getCon();
			String sql = "SELECT count(*) FROM tbl_history";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int recordCount = 0;
			if(rs.next()) {
				recordCount = rs.getInt(1) + 1;
			}					
			sql = "INSERT INTO tbl_history values("+recordCount+",'"+bean.getUser_name()+"','"+hb.getBank_name()+"','"+hb.getType()+"','"+hb.getLocality()+"','"+hb.getAddress()+"','"+hb.getZip()+"','"+hb.getCoordinates()+"')";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		}catch(Exception e){
			System.out.println("Exception in HistoryDao: " + e);
			status=false;
		}		
		return status;
	}
}
