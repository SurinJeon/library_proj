package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import library_proj.service.BookService;
import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBoxForRent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import library_proj.ui.content.list.UserTablePanelForRent;
import library_proj.ui.content.SearchBookComboBoxForRent;
import library_proj.ui.content.list.BookTablePanelForRent;
import library_proj.ui.content.UserDetailPanel;
import library_proj.ui.content.BookDetailPanel;

@SuppressWarnings("serial")
public class RentalPage extends JFrame {

	private JPanel contentPane;
	private UserService userService;
	private BookService bookService;
	private UserTablePanelForRent pUserList;
	
	
	public RentalPage() {
		userService = new UserService();
		bookService = new BookService();
		initialize();
	}
	private void initialize() {
		setTitle("대출화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 648, 800);
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
		
		SearchUserComboBoxForRent pCmbuser = new SearchUserComboBoxForRent();
		pCmbuser.setService(userService);
		pSearch1.add(pCmbuser, BorderLayout.NORTH);
		
		pUserList = pCmbuser.getpUserList();
		pUserList.setService(userService);
		pUserList.loadData();
		pSearch1.add(pUserList, BorderLayout.CENTER);
		pUserList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		
		UserDetailPanel pUserDetail = new UserDetailPanel();
		pUserDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pUserDetail);
		
		JPanel pSearch2 = new JPanel();
		pCenter.add(pSearch2);
		pSearch2.setLayout(new BorderLayout(0, 0));
		
		SearchBookComboBoxForRent pCmbBook = new SearchBookComboBoxForRent();
		pCmbBook.setService(bookService);
		pSearch2.add(pCmbBook, BorderLayout.NORTH);
		
		BookTablePanelForRent pBookList = pCmbBook.getpBookList();
		pBookList.setService(bookService);
		pBookList.loadData();
		pSearch2.add(pBookList, BorderLayout.CENTER);
		
		BookDetailPanel pBookDetail = new BookDetailPanel();
		pBookDetail.setBorder(new EmptyBorder(5, 0, 5, 0));
		pCenter.add(pBookDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		JButton btnRent = new JButton("대여하기");
		pBtn.add(btnRent);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
	}

}
