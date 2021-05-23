package SQLConnectivity;

import java.sql.*;

public class DemoSQLConnection {

	public static void main(String[] args) throws Exception
	{
//		String url="jdbc:mysql://localhost:3306/MyDatabase";
		String url="jdbc:mysql://localhost:3306/MyDatabase?characterEncoding=utf8&useSSL=false&useUnicode=true";

		String uname="root";
		String pass="Pass@123";
		
		String query = "select distinct Address from student;";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		rs.next();
		String name = rs.getString("Name");
		
		System.out.println(name);
		
		st.close();
		con.close();

	}
}
