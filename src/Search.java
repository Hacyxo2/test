import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;

public class Search extends JFrame {
	private static final long serialVersionUID = 1L;
	private ButtonListener buttonListener = null;
	private JTextField search = null;
	private JTable table = null;
	private JScrollPane scroll = new JScrollPane(table);
	private JComboBox<Object> comboBox = null;
	private JTextField selectBook= null;
	
	public Search() {
		buttonListener = new ButtonListener(this);
		setTitle("Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		getContentPane().add(panel1);
		JPanel panelNorth = new JPanel();
		panel1.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));
		
		comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"id", "isbn", "number", "authors", "title", "publisher","book_date", "status", "regist_date", "check_out_name", "check_out_number"}));
		panelNorth.add(comboBox);
		
		search = new JTextField();
		panelNorth.add(search);
		search.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(buttonListener);
		panelNorth.add(btnNewButton);
		
		JPanel panelCenter = new JPanel();
		panel1.add(panelCenter, BorderLayout.CENTER);
		
		String[] header = {"¤¾¤·"};
		DefaultTableModel model = new DefaultTableModel(header, 0){
			public boolean isCellEditable(int i, int c){
				return false;
			}
		};
		panelCenter.setLayout(null);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(buttonListener);
		scroll = new JScrollPane(table);
		scroll.setBounds(12, 5, 900, 400);
		panelCenter.add(scroll);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 415, 900, 20);
		panelCenter.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		selectBook = new JTextField();
		selectBook.setDisabledTextColor(Color.WHITE);
		selectBook.setSelectedTextColor(Color.WHITE);
		selectBook.setSelectionColor(Color.BLACK);
		selectBook.setEditable(false);
		selectBook.setBackground(Color.GRAY);
		selectBook.setText("");
		panel.add(selectBook);
		selectBook.setColumns(10);
		JPanel panel2 = new JPanel();
		panel1.add(panel2, BorderLayout.SOUTH);
		
		
		JButton cancelButton = new JButton(Const.CANCEL);
		JButton checkOutButton = new JButton(Const.BOOKCHECKOUTLIST);
		JButton checkInButton = new JButton(Const.BOOKCHECKINLIST);
		
		cancelButton.addActionListener(buttonListener);
		checkOutButton.addActionListener(buttonListener);
		checkInButton.addActionListener(buttonListener);
		
		panel2.add(cancelButton);
		panel2.add(checkOutButton);
		panel2.add(checkInButton);
		
		setLocationRelativeTo(null);
		setSize(940, 540);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public JTextField getSearch() {
		return search;
	}
	public JTable getTable() {
		return table;
	}
	public JScrollPane getScroll() {
		return scroll;
	}
	public JComboBox<Object> getComboBox() {
		return comboBox;
	}
	public JTextField getSelectBook() {
		
		return selectBook;
	}
}
