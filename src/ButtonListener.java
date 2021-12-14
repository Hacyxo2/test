import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ButtonListener implements ActionListener, MouseListener{
	
	private LoginWindows window = null;
	private CheckOutBook checkOutBook = null;
	private CheckInBook checkInBook = null;
	private Search search = null;
	private int id = 0;
	
	public ButtonListener(LoginWindows window) {
		this.window = window;
	}
	public ButtonListener(Search search) {
		this.search = search;
	}
	public ButtonListener(CheckOutBook checkOut) {
		this.checkOutBook = checkOut;
	}
	public ButtonListener(CheckInBook checkIn) {
		this.checkInBook = checkIn;
	}
	private void closeWindow() {
		if(this.window !=null) {
			window.dispose();
		}
		else if(this.checkOutBook !=null) {
			checkOutBook.dispose();
		}
		else if(this.checkInBook !=null) {
			checkInBook.dispose();
		}
		else if(this.search !=null) {
			search.dispose();
		}
	}
	private void createModel(String name) {
		if(name == Const.SEARCH) {
			DefaultTableModel model = (DefaultTableModel) search.getTable().getModel();
			String[] header = {"id", "isbn", "number", "authors", "title",
					"publisher", "book_date", "status", "regist_date"};
			model.setColumnIdentifiers(header);
			model.setRowCount(0);
			try {
				Database.getInstance().searchJTable(model,
						search.getComboBox().getSelectedItem().toString(), search.getSearch().getText());
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(name == Const.BOOKCHECKOUTLIST) {
			DefaultTableModel model = (DefaultTableModel) checkOutBook.getTable().getModel();
			String[] header = {"id", "isbn", "number", "authors", "title",
					"publisher", "book_date", "status", "regist_date", "check_out_name", "check_out_number"};
			model.setColumnIdentifiers(header);
			model.setRowCount(0);
			try {
				Database.getInstance().checkOutJTable(model,
						checkOutBook.getComboBox().getSelectedItem().toString(), checkOutBook.getSearch().getText());
				checkOutBook.repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(name == Const.BOOKCHECKINLIST) {
			DefaultTableModel model = (DefaultTableModel) checkInBook.getTable().getModel();
			String[] header = {"id", "isbn", "number", "authors", "title",
					"publisher", "book_date", "status", "regist_date", "check_out_name", "check_out_number"};
			model.setColumnIdentifiers(header);
			model.setRowCount(0);
			try {
					Database.getInstance().checkInJTable(model,
							checkInBook.getComboBox().getSelectedItem().toString(), checkInBook.getSearch().getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)){
			System.out.println("ok");
			/*
			 * 1. name, password�� ���� �ԷµǾ����� Ȯ���մϴ�.
			 * 1-1. ���� ���ٸ� ��� �޽����� ����մϴ�.
			 * 2. �����ͺ��̽����� member ���̺��� ��ȸ�Ͽ� �Էµ�
			 * name�� password�� ���� ���� �ִ� �� Ȯ���մϴ�.
			 * 2-1. ���� ���� �ִٸ� MainFrame ȭ���� ���ϴ�.
			 * 2-2. ���� ���� ���ٸ� ��� �޽����� ����մϴ�.
			 * */
			if(window.getNameTextField().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The name is empty");
				window.getNameTextField().requestFocus();
			}
			else if(window.getPwdTextField().getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "The password is empty");
				window.getPwdTextField().requestFocus();
			}
			else
			{
				/* �����ͺ��̽��� ���� name�� �ִ��� üũ�մϴ�. */
				//������
				if(Database.getInstance().checkExistName(window.getNameTextField().getText()) == true) {
					/* ��� ������ �����ϸ� �Ʒ��� �����մϴ�.*/
					Database.getInstance().name = window.getNameTextField().getText();
					new MainFrame();
					/* �α��� ������â�� �ݽ��ϴ�. */
					closeWindow();
				}
				//������ ��� ǥ��
				else {
					JOptionPane.showMessageDialog(null, "�ش�Ǵ� name�� �����ϴ�.");
				}
			}	
		}
		else if(e.getActionCommand().equals(Const.JOIN)) {
			MemberJoinDialog dialog = new MemberJoinDialog();
			dialog.setVisible(true);
			System.out.println("join");
		}
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			if(this.window !=null) {
				window.dispose();
			}
			else if(this.checkOutBook !=null) {
				new Search();
				closeWindow();
			}
			else if(this.checkInBook !=null) {
				new Search();
				closeWindow();
			}
			else if(this.search != null) {
				new MainFrame();
				closeWindow();
			}
			System.out.println("cancel");
		}
		else if(e.getActionCommand().equals(Const.SEARCH)) {
			if(search != null) {
				createModel(Const.SEARCH);
			}
			else if(checkOutBook != null) {
				createModel(Const.BOOKCHECKOUTLIST);
			}
			else if(checkInBook != null) {
				createModel(Const.BOOKCHECKINLIST);
			}
			
		}
		else if(e.getActionCommand().equals(Const.BOOKCHECKOUTLIST)) {
			closeWindow();
			new CheckOutBook();
		}
		else if(e.getActionCommand().equals(Const.BOOKCHECKINLIST)) {
			closeWindow();
			new CheckInBook();
		}
		else if(e.getActionCommand().equals(Const.BOOKCHECKOUT)) {
			Database.getInstance().checkOut(id);
		}
		else if(e.getActionCommand().equals(Const.BOOKCHECKIN)) {
			Database.getInstance().checkIn(id);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//Table Ŭ���� ������ SelectBook�� �����.
		if(this.checkOutBook !=null) {
			int row = checkOutBook.getTable().getSelectedRow();
			id = Integer.parseInt(checkOutBook.getTable().getValueAt(row, 0).toString());
			checkOutBook.getSelectBook().setText("");
			for (int i = 0; i < checkOutBook.getTable().getColumnCount(); i++) {
				checkOutBook.getSelectBook().setText(checkOutBook.getSelectBook().getText() + checkOutBook.getTable().getValueAt(row, i)+"         ");
			}
			System.out.println();
			System.out.println("checkOutBook");
		}
		else if(this.checkInBook !=null) {
			int row = checkInBook.getTable().getSelectedRow();
			id = Integer.parseInt(checkInBook.getTable().getValueAt(row, 0).toString());
			checkInBook.getSelectBook().setText("");
			for (int i = 0; i < checkInBook.getTable().getColumnCount(); i++) {
				checkInBook.getSelectBook().setText(checkInBook.getSelectBook().getText() + checkInBook.getTable().getValueAt(row, i)+"         ");
			}
			System.out.println();
			System.out.println("checkInBook");
		}
		else if(this.search !=null) {
			int row = search.getTable().getSelectedRow();
			search.getSelectBook().setText("");
			for (int i = 0; i < search.getTable().getColumnCount(); i++) {
				search.getSelectBook().setText(search.getSelectBook().getText() + search.getTable().getValueAt(row, i)+"         ");
			}
			System.out.println();
		}	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
