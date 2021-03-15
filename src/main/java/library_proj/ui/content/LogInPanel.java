package library_proj.ui.content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj.LogInUI;
import library_proj.dto.Manager;
import library_proj.service.ManagerService;
import library_proj.ui.MainPage;
import library_proj.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class LogInPanel extends JPanel implements ActionListener {
	private JTextField tfId;
	private JPasswordField pfPasswd;
	private JButton btnLogIn;
	private ManagerService service;
	
	public LogInPanel() {

		initialize();
		service = new ManagerService();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pLogIn = new JPanel();
		add(pLogIn);
		pLogIn.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblId = new JLabel("ID");
		pLogIn.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		
		tfId = new JTextField();
		pLogIn.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblPasswd = new JLabel("비밀번호");
		pLogIn.add(lblPasswd);
		lblPasswd.setHorizontalAlignment(SwingConstants.TRAILING);
		
		pfPasswd = new JPasswordField();
		pLogIn.add(pfPasswd);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		
		btnLogIn = new JButton("로그인");
		btnLogIn.addActionListener(this);
		pBtn.add(btnLogIn);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogIn) {
			actionPerformedBtnLogIn(e);
		}
	}
	
	public Manager getManager() {
		
		validCheck();
		String id = tfId.getText().trim();
		String passwd = new String(pfPasswd.getPassword());
//		Manager manager = new Manager(tfId.getText().trim(), passwd);
		return new Manager(id, passwd); 
	}
	
	private void validCheck() {
		String passwd = new String(pfPasswd.getPassword());
		if(tfId.getText().trim().contentEquals("") || passwd.equals("")) {
			throw new InvalidCheckException();
		}
		
	}
	protected void actionPerformedBtnLogIn(ActionEvent e) {

		String passwd = new String(pfPasswd.getPassword());
		Manager manager = new Manager(tfId.getText().trim(), passwd);
		Manager searchMn = service.selectManagerById(manager);
		
		if(searchMn != null) {
			if(passwd.equals(searchMn.getPasswd())) {
				MainPage frame = new MainPage();
				frame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if(tfId.getText().trim().equals("") && passwd.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			} else {
			JOptionPane.showMessageDialog(null, "해당 아이디가 없습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		
	}
	
}
