package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utility.Connections;
import bean.LoginBean;


public class LoginDao {
	
	public static boolean validate(LoginBean bean){
		boolean status = false;
		
		try{
			Connection con = Connections.getCon();
			PreparedStatement ps = con
					.prepareStatement("select * from tbl_registereduser where USERNAME=? and PASSWORD=?");
			ps.setString(1, bean.getUser_name());
			ps.setString(2, bean.getPassword());
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			
		}catch(Exception e){
			System.out.println("Exception in LoginDAO: " + e);
		}
		
		return status;
	}

}
