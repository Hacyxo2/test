import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class BookDatabase {
	private volatile static BookDatabase instance = null;
	private Connection connection = null;
	private BookDatabase() {
		initDatabase();
	}
	public static BookDatabase getInstance() {
		if(instance == null) {
			synchronized (BookDatabase.class) {
				if(instance == null)
					instance = new BookDatabase();
			}
		}
		return instance;
	}
	public void insertMemberData(int id, String isbn, String number, String authors,
			String title, String publisher, String book_date, String status, String regist_date) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        statement.executeUpdate(
	        		"INSERT INTO book (id, isbn, number, authors, title,"
	        		+ " publisher, book_date, status, regist_date) "
	        		+ "values('"+id+"','"+isbn+"', '"+number+"','"+authors+"','"+title+"',"
	        				+ " '"+publisher+"','"+book_date+"','"+status+"','"+regist_date+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	public void insertJTable(DefaultTableModel model) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM book");
			while(rs.next())
			{
				
				String [] record = new String[9];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = rs.getString("isbn");
				record[2] = rs.getString("number");
				record[3] = rs.getString("authors");
				record[4] = rs.getString("title");
				record[5] = rs.getString("publisher");
				record[6] = rs.getString("book_date");
				record[7] = rs.getString("status");
				record[8] = rs.getString("regist_date");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initDatabase() {
		// create a database connection
        try {
			connection = DriverManager.getConnection("jdbc:sqlite:book.db");
			Statement statement = connection.createStatement();
			if( !checkExistTable("book") ) {
				statement.executeUpdate(
						"CREATE TABLE book "
						+ "(id INTEGER NOT NULL, "
						+ "isbn STRING NOT NULL, "
						+ "number STRING NOT NULL, "
						+ "authors STRING NOT NULL, "
						+ "title STRING NOT NULL, "
						+ "publisher STRING NOT NULL, "
						+ "book_date STRING NOT NULL, "
						+ "status STRING NOT NULL, "
						+ "regist_date STRING NOT NULL, "
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
