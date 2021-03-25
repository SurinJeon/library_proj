package library_proj.ui.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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
//		rsService = new ReturnSearchService();
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
//		service = new RentalStatusService();
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
			Book book = bookService.showBooksByNoForDetail(new Book(bookNo));
			
			ReturnPage frame = new ReturnPage();
			frame.getpUserDetail().setUser(userDetail);
			frame.getpBookRentalDetail().setBook(book);
			frame.setVisible(true);
			
			//회원/도서목록 select되게 해야함
		}
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
