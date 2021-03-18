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
	}

	public void actionPerformed(ActionEvent e) {
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
	
}
