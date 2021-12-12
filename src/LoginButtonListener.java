import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginButtonListener implements ActionListener{
	
	
	private LoginWindows window = null;
	public LoginButtonListener(LoginWindows window) {
		this.window = window;
	}
	private void closeWindow() {
		window.dispose();
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
			closeWindow();
			System.out.println("cancel");
		}
	}

}
