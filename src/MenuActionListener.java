import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuActionListener implements ActionListener {

	private MainFrame mainWindow = null;
	private JTable table = null;
	private JScrollPane scroll = new JScrollPane(table);
	public boolean a = false;
	public MenuActionListener(MainFrame mainWindow) {
		this.mainWindow = mainWindow;
		createTable();
		System.out.println("1");
	}
	
	private void closeWindow() {
		mainWindow.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Members ...")) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			String[] header = { "id", "name", "password" };
			model.setColumnIdentifiers(header);
			model.setRowCount(0);
			
			Database.getInstance().insertJTable(model);
			System.out.println("members... ok"+" "+ a);
		} 
		else if (e.getActionCommand().equals("Book List")) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			String[] header = {"id", "isbn", "number", "authors", "title", "publisher", "book_date", "status", "regist_date"};
			model.setColumnIdentifiers(header);
			model.setRowCount(0);
			BookDatabase.getInstance().insertJTable(model);
			System.out.println("book list... ok"+" "+a);
		}
		else if (e.getActionCommand().equals(Const.ADDBOOK)) {
			System.out.println("add Book... ok");
			AddBookDialog dialog = new AddBookDialog();
			dialog.setVisible(true);
		} 
		else if (e.getActionCommand().equals(Const.LOGIN)) {
			new LoginWindows();
			closeWindow();
			System.out.println("log-in... ok");
		}
		else if (e.getActionCommand().equals(Const.LOGOUT)) {
			/* 출처: https://answerofgod.tistory.com/110 [The Answer's Engineering Blog] */
			Object[] option = { "Yes", "No" };
			int response = JOptionPane.showOptionDialog(null, "Would you like to Logout?", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
			/*0은 yes, 1은 no*/
			if (response == 0) {
				System.out.println("Yes");
				new LoginWindows();
				closeWindow();
			} else if (response == 1) {
				System.out.println("No");
			}
		}
	}
	
	private void createTable() {
			String[] header = {};
			DefaultTableModel model = new DefaultTableModel(header, 0);
			table = new JTable(model);
			scroll = new JScrollPane(table);
			mainWindow.add(scroll);
	}	
}
