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
			 * 1. name, password에 값이 입력되었는지 확인합니다.
			 * 1-1. 만약 없다면 경고 메시지를 출력합니다.
			 * 2. 데이터베이스에서 member 테이블을 조회하여 입력된
			 * name과 password가 같은 같이 있는 지 확인합니다.
			 * 2-1. 같은 값이 있다면 MainFrame 화면을 띄웁니다.
			 * 2-2. 같은 값이 없다면 경고 메시지를 출력합니다.
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
				/* 데이터베이스에 같은 name이 있는지 체크합니다. */
				//있으면
				if(Database.getInstance().checkExistName(window.getNameTextField().getText()) == true) {
					/* 모든 조건이 성립하면 아래를 실행합니다.*/
					new MainFrame();
					/* 로그인 윈도우창은 닫습니다. */
					closeWindow();
				}
				//없으면 경고 표시
				else {
					JOptionPane.showMessageDialog(null, "해당되는 name이 없습니다.");
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
