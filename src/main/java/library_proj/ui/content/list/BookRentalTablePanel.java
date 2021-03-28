package library_proj.ui.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.service.BookService;
import library_proj.service.RentalStatusService;
import library_proj.service.UserService;
import library_proj.ui.RentalPage;
import library_proj.ui.ReturnPage;
import library_proj.ui.content.UserDetailPanel;

@SuppressWarnings("serial")
public class BookRentalTablePanel extends AbstractCustomTablePanel<RentalStatus> implements MouseListener{
	private RentalStatusService service;
	private UserService userService;
	private UserDetailPanel pUserDetail;
	private BookService bookService;
//	private ReturnSearchService rsService;
	
	public BookRentalTablePanel() {
		userService = new UserService();
		bookService = new BookService();
		list = new ArrayList<RentalStatus>();
		table.addMouseListener(this);
		
	}


	
	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);
		setTableCellWidth(100, 300, 250, 250);
		
	}

	@Override
	protected Object[] toArray(RentalStatus r) {
		return new Object[] {
				r.getBookNo().getBookNo(),
				r.getBookNo().getBookTitle(),
				r.getDelayDate() <= 0 ? "" : r.getDelayDate(),
				r.getRentalDate()};
	}

	@Override
	protected void initList() {
		list = new ArrayList<RentalStatus>();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"도서번호", "도서제목", "도서연체일", "도서대여일"};
	}

	public void setService(RentalStatusService service) {
		this.service = service;
	}
	
	public void setList(List<RentalStatus> list) {
		this.list = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			String bookNo = (String)table.getValueAt(idx, 0);
			RentalStatus rentalstatus = service.showUserByBookTitle(new Book(bookNo));
			int userNo = rentalstatus.getUserNo().getUserNo();
			
			User userDetail = userService.showUserByUserNoForDetail(new User(userNo));
			Book bookDetail = bookService.showBooksByNoForDetail(new Book(bookNo));
			
			ReturnPage frame = new ReturnPage();
			
			setUserListAndDetail(userNo, userDetail, frame);
			
			List<RentalStatus> list = service.showRentalBooks(new User(userNo));
			setBookRentalListVisible(frame, list);
			
			setBookRentalListAndDetail(bookNo, bookDetail, frame, list);
			
			frame.setVisible(true);
//			frame.setpBookListMain(pBookListMain);
			
			//회원/도서목록 select되게 해+야함
		}
	}



	public void setBookRentalListAndDetail(String bookNo, Book bookDetail, ReturnPage frame, List<RentalStatus> list) {
		List<RentalStatus> searchRentalStatus = list.stream()
				.filter(rentalList -> rentalList.getBookNo().getBookNo().equals(bookNo))
				.collect(Collectors.toList());
		RentalStatus rentalStatus = searchRentalStatus.get(0);
		int idxRs = frame.getpBookRentalList().getList().indexOf(rentalStatus);
		frame.getpBookRentalList().table.setRowSelectionInterval(idxRs, idxRs);
		
		frame.getpBookRentalDetail().setBook(bookDetail);
	}



	public void setBookRentalListVisible(ReturnPage frame, List<RentalStatus> list) {
		frame.getpBookRentalList().setList(list);
		frame.getpBookRentalList().setList();
	}



	public void setUserListAndDetail(int userNo, User userDetail, ReturnPage frame) {
		List<User> searchUser = frame.getpUserList().getList()
				.stream().filter(user -> user.getUserNo()==userNo)
				.collect(Collectors.toList());
		User user = searchUser.get(0);
		int idxRent = frame.getpUserList().getList().indexOf(user);
		frame.getpUserList().table.setRowSelectionInterval(idxRent, idxRent);
		frame.getpUserDetail().setUser(userDetail);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
