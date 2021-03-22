package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBoxForReturn;
import library_proj.ui.content.list.UserTablePanelForReturn;
import library_proj.ui.content.UserDetailPanel;
import library_proj.ui.content.list.BookRentalTablePanel;
import library_proj.ui.content.BookDetailPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ReturnPage extends JFrame {

	private JPanel contentPane;
	private SearchUserComboBoxForReturn pCmbUser;
	private UserService userService;
	private UserTablePanelForReturn pUserList;
	private UserDetailPanel pUserDetail;
	private BookRentalTablePanel pBookRentalList;
	private BookDetailPanel pBookRentalDetail;
	
	public ReturnPage() {
		userService = new UserService();
		initialize();
	}
	private void initialize() {
		setTitle("반납화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel pSearch1 = new JPanel();
		pCenter.add(pSearch1);
		pSearch1.setLayout(new BorderLayout(0, 0));
		
		pCmbUser = new SearchUserComboBoxForReturn();
		pCmbUser.setService(userService);
		pSearch1.add(pCmbUser, BorderLayout.NORTH);
		
		pUserList = pCmbUser.getpUserList();
		pUserList.setService(userService);
		pUserList.loadData();
		
		pSearch1.add(pUserList, BorderLayout.CENTER);
		pUserList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		
		pUserDetail = pUserList.getpUserDetail();
		pUserDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pUserDetail);
		
		JPanel pSearch2 = new JPanel();
		pCenter.add(pSearch2);
		pSearch2.setLayout(new BorderLayout(0, 0));
		
		JPanel pText = new JPanel();
		FlowLayout fl_pText = (FlowLayout) pText.getLayout();
		fl_pText.setAlignment(FlowLayout.LEFT);
		pSearch2.add(pText, BorderLayout.NORTH);
		
		JLabel lblText = new JLabel("대여중인도서목록");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		pText.add(lblText);
		
		pBookRentalList = new BookRentalTablePanel();
		pSearch2.add(pBookRentalList, BorderLayout.CENTER);
		
		pBookRentalDetail = new BookDetailPanel();
		pCenter.add(pBookRentalDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		JButton btnReturn = new JButton("반납하기");
		pBtn.add(btnReturn);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
	}
	public UserDetailPanel getpUserDetail() {
		return pUserDetail;
	}
	public void setpUserDetail(UserDetailPanel pUserDetail) {
		this.pUserDetail = pUserDetail;
	}
	public BookDetailPanel getpBookRentalDetail() {
		return pBookRentalDetail;
	}
	public void setpBookRentalDetail(BookDetailPanel pBookRentalDetail) {
		this.pBookRentalDetail = pBookRentalDetail;
	} 

	
	

}
