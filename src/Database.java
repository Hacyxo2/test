import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Database {
	
	private volatile static Database instance = null;
	private Connection connection = null;
	private Database() {
		initDatabase();
	}
	public static Database getInstance() {
		if(instance == null) {
			synchronized (Database.class) {
				if(instance == null)
					instance = new Database();
			}
		}
		return instance;
	}
	public void insertMemberData(String name, char[] password) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        statement.executeUpdate(
	        		"INSERT INTO member (name, pwd) "
	        		+ "values('"+name+"', '"+password+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	public void insertJTable(DefaultTableModel model) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM member");
			
			while(rs.next())
			{
				String []record = new String[3];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = rs.getString("name");
				record[2] = rs.getString("pwd");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkExistName(String name) {
		boolean retValue = false; //있으면 true, 없으면 false
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM member WHERE name='"+name+"'");
			if(rs.getInt(1) >=  1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retValue;
	}
	
	private void initDatabase() {
		// create a database connection
        try {
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			Statement statement = connection.createStatement();
			if( !checkExistTable("member") ) {
				statement.executeUpdate(
						"CREATE TABLE member "
						+ "(id INTEGER NOT NULL, "
						+ "name STRING NOT NULL, "
						+ "pwd STRING NOT NULL, "
						+ "PRIMARY KEY(ID AUTOINCREMENT))");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkExistTable(String tableName) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE name='"+tableName+"'");
			if(rs.getInt(1) == 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
}
