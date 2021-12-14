import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GUI Test");
		setSize(800, 500);
		
		createMenu();
		
		setVisible(true);
		//화면 가운데 생성
		setLocationRelativeTo(null);
	}
	private void createMenu() {
		/* Menu를 만듭니다. */
		JMenuBar mb = new JMenuBar();
		JMenu personMenu = new JMenu("Person");
		JMenuItem loginMenuItem = new JMenuItem(Const.LOGIN);
		JMenuItem logoutMenuItem = new JMenuItem(Const.LOGOUT);
		personMenu.add(loginMenuItem);
		personMenu.add(logoutMenuItem);
		
		JMenu memberMenu = new JMenu("Members");
		JMenuItem membersMenuItem = new JMenuItem(Const.MEMBERS);
		memberMenu.add(membersMenuItem);
		
		JMenu bookMenu = new JMenu("Book");
		JMenuItem addBookMenuItem = new JMenuItem(Const.ADDBOOK);
		JMenuItem bookListMenuItem = new JMenuItem(Const.BOOKLIST);
		bookMenu.add(addBookMenuItem);
		bookMenu.add(bookListMenuItem);
		
		JMenu functionMenu = new JMenu("function");
		JMenuItem searchMenuItem = new JMenuItem(Const.SEARCH);
		functionMenu.add(searchMenuItem);
		
		mb.add(personMenu);
		mb.add(memberMenu);
		mb.add(bookMenu);
		mb.add(functionMenu);
		
		MenuActionListener menuListener = new MenuActionListener(this);
		loginMenuItem.addActionListener(menuListener);
		logoutMenuItem.addActionListener(menuListener);
		membersMenuItem.addActionListener(menuListener);
		addBookMenuItem.addActionListener(menuListener);
		bookListMenuItem.addActionListener(menuListener);
		searchMenuItem.addActionListener(menuListener);
		setJMenuBar(mb);
	}
}