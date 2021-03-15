package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import library_proj.service.UserService;
import library_proj.ui.content.list.UserTablePanel;
import library_proj.ui.content.SearchUserComboBox;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnRent;
	private JButton btnReturn;
	private UserService service;
	
	public MainPage() {
		service = new UserService();
		initialize();
	}
	
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pBtn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pBtn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		btnRent = new JButton("대출하기");
		btnRent.addActionListener(this);
		pBtn.add(btnRent);
		
		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);
		
		JPanel pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 5, 0));
		
		JPanel pUser = new JPanel();
		pSearch.add(pUser);
		pUser.setLayout(new BorderLayout(0, 0));
		
		SearchUserComboBox pCmbUser = new SearchUserComboBox();
		pUser.add(pCmbUser, BorderLayout.NORTH);
		
		UserTablePanel pUserList = new UserTablePanel();
		pUserList.setService(service);
		pUserList.loadData();
		pUser.add(pUserList, BorderLayout.CENTER);
		
		JPanel pBook = new JPanel();
		pSearch.add(pBook);
		pBook.setLayout(new BorderLayout(0, 0));
		
		JPanel pCmbBook = new JPanel();
		pBook.add(pCmbBook, BorderLayout.NORTH);
		
		JPanel pBookList = new JPanel();
		pBook.add(pBookList, BorderLayout.CENTER);
		

		JPanel pList = new JPanel();
		contentPane.add(pList, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReturn) {
			actionPerformedBtnReturn(e);
		}
		if (e.getSource() == btnRent) {
			actionPerformedBtnRent(e);
		}
	}
	protected void actionPerformedBtnRent(ActionEvent e) {
		RentalPage frame = new RentalPage();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnReturn(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setVisible(true);
	}
}
