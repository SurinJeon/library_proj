package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import library_proj.dto.Book;
import library_proj.dto.User;
import library_proj.service.ReturnService;
import library_proj.service.UserService;
import library_proj.ui.content.BookDetailPanel;
import library_proj.ui.content.SearchUserComboBoxForReturn;
import library_proj.ui.content.UserDetailPanel;
import library_proj.ui.content.list.BookRentalTablePanelForReturn;
import library_proj.ui.content.list.UserTablePanelForReturn;

@SuppressWarnings("serial")
public class ReturnPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private SearchUserComboBoxForReturn pCmbUser;
	private UserService userService;
	private ReturnService returnService;
	private UserTablePanelForReturn pUserList;
	private UserDetailPanel pUserDetail;
	private BookRentalTablePanelForReturn pBookRentalList;
	private BookDetailPanel pBookRentalDetail;
	private JButton btnReturn;
	
	public ReturnPage() {
		userService = new UserService();
		returnService = new ReturnService();
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
		
		pBookRentalList = pUserList.getpBookRentalList();
		pBookRentalList.loadData();
		pSearch2.add(pBookRentalList, BorderLayout.CENTER);
		
		pBookRentalDetail = pBookRentalList.getpBookRentalDetail();
		pCenter.add(pBookRentalDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReturn) {
			actionPerformedBtnReturn(e);
		}
	}
	protected void actionPerformedBtnReturn(ActionEvent e) {
		User user = pUserDetail.getUser();
		Book book = pBookRentalDetail.getBook();
		
		System.out.println(user);
		System.out.println(book);
		
		if(user != null && book != null) {
			returnService.transReturn(book);
		} else {
			if(user == null) {
				JOptionPane.showMessageDialog(null, "회원을 선택해주세요.");
			} else if(book != null){
				JOptionPane.showMessageDialog(null, "도서를 선택해주세요.");
			}
		}
		
		JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");
		
		pUserDetail.clearTf();
		pBookRentalDetail.clearTf();
	}
	public UserTablePanelForReturn getpUserList() {
		return pUserList;
	}
	public void setpUserList(UserTablePanelForReturn pUserList) {
		this.pUserList = pUserList;
	}
	public BookRentalTablePanelForReturn getpBookRentalList() {
		return pBookRentalList;
	}
	public void setpBookRentalList(BookRentalTablePanelForReturn pBookRentalList) {
		this.pBookRentalList = pBookRentalList;
	}
	
	
}











