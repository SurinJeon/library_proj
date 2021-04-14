package library_proj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library_proj.dto.Manager;
import library_proj.service.ManagerService;
import library_proj.service.RentalService;
import library_proj.service.RentalStatusService;
import library_proj.ui.MainPage;
import library_proj.ui.content.LogInPanel;
import library_proj.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class LogInUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogIn;
	private LogInPanel pLogIn;
	private ManagerService service;
	private RentalStatusService rentalService;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInUI frame = new LogInUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogInUI() {
		service = new ManagerService();
		rentalService = new RentalStatusService();
		initialize();
	}
	private void initialize() {
		setTitle("관리자 로그인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pLogIn = new LogInPanel();
		contentPane.add(pLogIn, BorderLayout.CENTER);
		
		btnLogIn = new JButton("로그인");
		btnLogIn.addActionListener(this);
		contentPane.add(btnLogIn, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogIn) {
			actionPerformedBtnLogIn(e);
		}
	}
	protected void actionPerformedBtnLogIn(ActionEvent e) {
		try {
			Manager mng = pLogIn.getManager();
			Manager searchMn = service.selectManagerById(mng);
			if( searchMn != null) {
				if(mng.getMngAccount().equals(searchMn.getMngAccount()) && mng.getPasswd().equals(searchMn.getPasswd())) {
					rentalService.updateRentalStatusLogIn(); // 연체일 update
					
					MainPage frame = new MainPage();
					frame.setVisible(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if(mng.getMngAccount().equals("") && mng.getPasswd().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "해당 아이디가 없습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}

			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "로그인실패", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
