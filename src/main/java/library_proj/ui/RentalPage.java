package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import library_proj.dto.Book;
import library_proj.dto.User;
import library_proj.service.BookService;
import library_proj.service.RentalService;
import library_proj.service.UserService;
import library_proj.ui.content.BookDetailPanel;
import library_proj.ui.content.SearchBookComboBoxForRent;
import library_proj.ui.content.SearchUserComboBoxForRent;
import library_proj.ui.content.UserDetailPanel;
import library_proj.ui.content.list.BookTablePanel;
import library_proj.ui.content.list.BookTablePanelForRent;
import library_proj.ui.content.list.UserTablePanelForRent;

@SuppressWarnings("serial")
public class RentalPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private UserService userService;
	private BookService bookService;
	private RentalService rentService;
	private UserTablePanelForRent pUserList;
	private BookTablePanelForRent pBookList;
	private UserDetailPanel pUserDetail;
	private BookDetailPanel pBookDetail;
	private JButton btnRent;
	private SearchUserComboBoxForRent pCmbuser;
	private SearchBookComboBoxForRent pCmbBook;
	private BookTablePanel pBookListMain;
	
	public RentalPage() {
		userService = new UserService();
		bookService = new BookService();
		rentService = new RentalService();
		initialize();
//		btnRent.setEnabled(false);
	}
	private void initialize() {
		setTitle("대출화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel pSearch1 = new JPanel();
		pCenter.add(pSearch1);
		pSearch1.setLayout(new BorderLayout(0, 0));
		
		pCmbuser = new SearchUserComboBoxForRent();
		pCmbuser.setService(userService);
		pSearch1.add(pCmbuser, BorderLayout.NORTH);
		
		pUserList = pCmbuser.getpUserList();
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
		
		pCmbBook = new SearchBookComboBoxForRent();
		pCmbBook.setService(bookService);
		pSearch2.add(pCmbBook, BorderLayout.NORTH);
		
		pBookList = pCmbBook.getpBookList();
		pBookList.setService(bookService);
		pBookList.loadData();
		pSearch2.add(pBookList, BorderLayout.CENTER);
		
		pBookDetail = pBookList.getpBookDetail();
		pBookDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pBookDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnRent = new JButton("대여하기");
		btnRent.addActionListener(this);
		showBtnRent();
		pBtn.add(btnRent);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		
	}

	public void showBtnRent() {
		try {
			User user = pUserDetail.getUser();
			Book book = pBookDetail.getBook();
			if(user != null && book != null) {
				btnRent.setEnabled(true);
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRent) {
			btnRent.setEnabled(true);
			actionPerformedBtnRent(e);
		}
	}
	
	protected void actionPerformedBtnRent(ActionEvent e) {
		btnRent.setEnabled(true);
		System.out.println(pUserDetail.getUser());
		User user = pUserDetail.getUser();
		Book book = pBookDetail.getBook();
		
		if(user != null && book != null) {
			btnRent.setEnabled(true);
			rentService.transRental(user, book);
		} else {
			if(user == null) {
				JOptionPane.showMessageDialog(null, "회원을 선택해주세요.");
			} else if(book == null) {
				JOptionPane.showMessageDialog(null, "도서를 선택해주세요.");
			}
		}
		
		JOptionPane.showMessageDialog(null, "대여가 완료되었습니다.");
		
		pUserDetail.clearTf();
		pBookDetail.clearTf();
		
		pBookListMain.setService(bookService);
		pBookListMain.loadData();
	}
	public UserDetailPanel getpUserDetail() {
		return pUserDetail;
	}
	public void setpUserDetail(UserDetailPanel pUserDetail) {
		this.pUserDetail = pUserDetail;
	}
	public BookDetailPanel getpBookDetail() {
		return pBookDetail;
	}
	public void setpBookDetail(BookDetailPanel pBookDetail) {
		this.pBookDetail = pBookDetail;
	}
	public UserTablePanelForRent getpUserList() {
		return pUserList;
	}
	public void setpUserList(UserTablePanelForRent pUserList) {
		this.pUserList = pUserList;
	}
	public BookTablePanelForRent getpBookList() {
		return pBookList;
	}
	public void setpBookList(BookTablePanelForRent pBookList) {
		this.pBookList = pBookList;
	}
	public void setpBookListMain(BookTablePanel pBookListMain) {
		this.pBookListMain = pBookListMain;
	}

	
	
}





















