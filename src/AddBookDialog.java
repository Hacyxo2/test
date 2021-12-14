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
                dispose(); //���̾�α� ����
            }
        });
        setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Const.OK)) {
			/* 1. name�� password�� ���� �ִ��� üũ�մϴ�. */
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
					/* �����ͺ��̽��� ���� �Է��մϴ�. */
					BookDatabase.getInstance().insertMemberData(
							Integer.parseInt(idField.getText()), //å�� �ĺ���
							isbnField.getText(),      //������ ISBN��
							numberField.getText(),    //å�� ��Ϲ�ȣ(����������ȣ)
							authorsField.getText(),   //å�� ����
							titleField.getText(),     //å ����
							publisherField.getText(), //���ǻ�
							book_dateField.getText(), //������
							statusField.getText(),    //���� ����
							regist_dateField.getText()); // �����
				
				dispose();	
			}
		}
		else if(e.getActionCommand().equals(Const.CANCEL)) {
			dispose();
		}
	}

}
