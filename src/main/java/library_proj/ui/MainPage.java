package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library_proj.service.BookService;
import library_proj.service.RentalStatusService;
import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBox;
import library_proj.ui.content.list.BookRentalTablePanel;
import library_proj.ui.content.list.UserTablePanel;
import library_proj.ui.content.SearchBookComboBox;
import library_proj.ui.content.list.BookTablePanel;

@SuppressWarnings("serial")
public class MainPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnRent;
	private JButton btnReturn;
	private UserService userService;
	private RentalStatusService rentalService;
	private BookService bookService;
	private UserTablePanel pUserList;
	private BookRentalTablePanel pBookRentalList;
	private BookTablePanel pBookList;
	
	public MainPage() {
		userService = new UserService();
		rentalService = new RentalStatusService();
		bookService = new BookService();

		initialize();
	}
	
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 900);
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
		
		JButton btnUserMng = new JButton("회원관리");
		pBtn.add(btnUserMng);
		
		JButton btnBookMng = new JButton("도서관리");
		pBtn.add(btnBookMng);
		
		JPanel pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 5, 0));
		
		JPanel pUser = new JPanel();
		pSearch.add(pUser);
		pUser.setLayout(new BorderLayout(0, 0));
		
		SearchUserComboBox pCmbUser = new SearchUserComboBox();
		pCmbUser.setService(userService);
		pUser.add(pCmbUser, BorderLayout.NORTH);
		
		pUserList = pCmbUser.getpUserList();
		pUserList.setService(userService);
		pUserList.loadData();
		pUser.add(pUserList, BorderLayout.CENTER);
		
		JPanel pBook = new JPanel();
		pSearch.add(pBook);
		pBook.setLayout(new BorderLayout(0, 0));
		
		SearchBookComboBox pCmbBook = new SearchBookComboBox();
		pCmbBook.setService(bookService);
		pBook.add(pCmbBook, BorderLayout.NORTH);
		
		pBookList = pCmbBook.getpBookList();
		pBookList.setService(bookService);
		pBookList.loadData();
		pBook.add(pBookList, BorderLayout.CENTER);

		pBookRentalList = pUserList.getpBookRentalList();
		contentPane.add(pBookRentalList, BorderLayout.SOUTH);
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
