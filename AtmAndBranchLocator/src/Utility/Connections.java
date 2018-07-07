package Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connections {
	
	private static Connection connection;
	static{
		try{
			Class.forName(ConfigProvider.Driver);
			connection = DriverManager.getConnection
					(ConfigProvider.db_atm_branch_locator_Con_URL, ConfigProvider.dbuname, ConfigProvider.dbpassword);
			
		}catch(Exception e){
			System.out.println("Exception in ConnectionProvider: " + e);
		}
	}
	
	public static Connection getCon(){
		return connection;
	}

}
