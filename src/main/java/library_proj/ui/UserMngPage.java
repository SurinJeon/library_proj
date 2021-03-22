package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class UserMngPage extends JFrame {

	private JPanel contentPane;

	public UserMngPage() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pAddUser = new JPanel();
		tabbedPane.addTab("회원 추가", null, pAddUser, null);
		
		JPanel pModifyUser = new JPanel();
		tabbedPane.addTab("회원 수정", null, pModifyUser, null);
		
		JPanel pDeleteUser = new JPanel();
		tabbedPane.addTab("회원 삭제", null, pDeleteUser, null);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		JButton btnConfrim = new JButton("확인");
		pBtn.add(btnConfrim);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
	}

}
