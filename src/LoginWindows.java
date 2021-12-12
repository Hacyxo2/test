import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private LoginButtonListener buttonListener = null;
	private JTextField nameTextField = null;
	private JPasswordField pwdTextField = null;

	public LoginWindows() {
		buttonListener = new LoginButtonListener(this);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		getContentPane().add(panel1);

		JPanel panelCenter = new JPanel();
		panel1.add(panelCenter, BorderLayout.CENTER);
		panelCenter.add(new JLabel("name       "));
		nameTextField = new JTextField(20);
		panelCenter.add(nameTextField);
		panelCenter.add(new JLabel("password"));
		pwdTextField = new JPasswordField(20);
		panelCenter.add(pwdTextField);

		JPanel panel2 = new JPanel();
		panel1.add(panel2, BorderLayout.SOUTH);
		JButton okButton = new JButton(Const.OK);
		okButton.addActionListener(buttonListener);
		panel2.add(okButton);
		JButton cancelButton = new JButton(Const.CANCEL);
		cancelButton.addActionListener(buttonListener);
		panel2.add(cancelButton);
		JButton joinButton = new JButton(Const.JOIN);
		joinButton.addActionListener(buttonListener);
		panel2.add(joinButton);

		setLocationRelativeTo(null);
		setSize(350, 150);
		setVisible(true);
	}
	public JTextField getNameTextField() {
		return nameTextField;
	}
	public JPasswordField getPwdTextField() {
		return pwdTextField;
	}
}
