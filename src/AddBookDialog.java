import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddBookDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField = null;
	private JTextField isbnField = null;
	private JTextField numberField = null;
	private JTextField authorsField = null;
	private JTextField titleField = null;
	private JTextField publisherField = null;
	private JTextField book_dateField = null;
	private JTextField statusField = null;
	private JTextField regist_dateField = null;

	public AddBookDialog() {
		setTitle("Add Book");
		setModal(true);
		setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        getContentPane().add(panel1);
        
        JPanel panelCenter = new JPanel();
        panel1.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new FlowLayout());
        idField = new JTextField(20);
        panelCenter.add(new JLabel("-----------------------id-----------------------"));
        panelCenter.add(idField);
        isbnField = new JTextField(20);
        panelCenter.add(new JLabel("----------------------isbn----------------------"));
        panelCenter.add(isbnField);
        numberField = new JTextField(20);
        panelCenter.add(new JLabel("--------------------number--------------------"));
        panelCenter.add(numberField);
        authorsField = new JTextField(20);
        panelCenter.add(new JLabel("--------------------authors--------------------"));
        panelCenter.add(authorsField);
        titleField = new JTextField(20);
        panelCenter.add(new JLabel("----------------------title----------------------"));
        panelCenter.add(titleField);
        publisherField = new JTextField(20);
        panelCenter.add(new JLabel("-------------------publisher-------------------"));
        panelCenter.add(publisherField);
        book_dateField = new JTextField(20);
        panelCenter.add(new JLabel("-------------------book date-------------------"));
        panelCenter.add(book_dateField);
        statusField = new JTextField(20);
        panelCenter.add(new JLabel("---------------------status---------------------"));
        panelCenter.add(statusField);
        regist_dateField = new JTextField(20);
        panelCenter.add(new JLabel("-------------------regist date-------------------"));
        panelCenter.add(regist_dateField);
        
        JPanel panel2 = new JPanel();
        panel1.add(panel2, BorderLayout.SOUTH);
        JButton okBtn = new JButton(Const.OK);
        okBtn.addActionListener(this);
        panel2.add(okBtn);
        JButton cancelBtn = new JButton(Const.CANCEL);
        cancelBtn.addActionListener(this);
        panel2.add(cancelBtn);
        setSize(300, 540);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); //다이얼로그 제거
            }
        });
        setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)) {
			/* 1. name과 password에 값이 있는지 체크합니다. */
			if(idField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The name is empty");
				idField.requestFocus();
			}
			if(isbnField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The isbn is empty");
				isbnField.requestFocus();
			}
			else if(numberField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The number is empty");
				numberField.requestFocus();
			}
			else if(authorsField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The authors is empty");
				authorsField.requestFocus();
			}
			else if(titleField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The title is empty");
				titleField.requestFocus();
			}
			else if(publisherField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The publisher is empty");
				publisherField.requestFocus();
			}
			else if(book_dateField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The book_dateField is empty");
				book_dateField.requestFocus();
			}
			else if(statusField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The status is empty");
				statusField.requestFocus();
			}
			else if(regist_dateField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "The regist_date is empty");
				regist_dateField.requestFocus();
			}
			else
			{
					/* 데이터베이스에 값을 입력합니다. */
					BookDatabase.getInstance().insertMemberData(
							Integer.parseInt(idField.getText()), //책의 식별자
							isbnField.getText(),      //도서의 ISBN값
							numberField.getText(),    //책의 등록번호(도서관리번호)
							authorsField.getText(),   //책의 저자
							titleField.getText(),     //책 제목
							publisherField.getText(), //출판사
							book_dateField.getText(), //출판일
							statusField.getText(),    //대출 상태
							regist_dateField.getText()); // 등록일
				
				dispose();	
			}
		}
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			dispose();
		}
	}

}
