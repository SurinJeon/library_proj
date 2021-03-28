package library_proj.ui.content.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.SubBookTitle;
import library_proj.dto.User;
import library_proj.service.BookService;
import library_proj.service.RentalStatusService;
import library_proj.service.UserService;
import library_proj.ui.RentalPage;
import library_proj.ui.ReturnPage;

@SuppressWarnings("serial")
public class BookTablePanel extends AbstractCustomTablePanel<Book> implements MouseListener{

	private BookService service;
	private RentalStatusService rsService;
	private UserService userService;
	
	public BookTablePanel() {
		rsService = new RentalStatusService();
		userService = new UserService();
		table.addMouseListener(this);
		
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		setTableCellWidth(150, 400, 200);
		setTableCellCondition(0, 1, 2);
	}

	@Override
	protected Object[] toArray(Book b) {
		return new Object[] {
				b.getBookNo(),
				b.getBookTitle(),
				b.getIsRented() == 1 ? "대여가능" : "대여불가"
				};
	}

	@Override
	protected void initList() {
		list = service.showBooks();
		
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"도서번호", "도서제목", "대출여부"};
	}
	
	public void setTableCellCondition(int...idx) {
		ConditionTableCellRender ctcr = new ConditionTableCellRender();
		TableColumnModel tcm = super.getTable().getColumnModel();
		
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(ctcr);
		}
	}
	
	public void setService(BookService service) {
		this.service = service;
	}
	
	private class ConditionTableCellRender extends JLabel implements TableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(
				JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column) {
			setText(value == null ? "" : value.toString());
			setOpaque(true);
			String isRented = (String) table.getValueAt(row, 2);
			if (isRented.equals("대여불가")) {
				setBackground(Color.red);
			} else {
				setBackground(Color.white);
			}
			
			setHorizontalAlignment(SwingConstants.CENTER);
			return this;
		}
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			String bookNo = (String)table.getValueAt(idx, 0);
			String bookTitle = (String)table.getValueAt(idx, 1);
			Book bookDetail = service.showBooksByNoForDetail(new Book(bookNo));
			
			if (bookDetail != null && bookDetail.getIsRented() != 0) {
				RentalPage frame = new RentalPage();
				frame.getpBookDetail().setBook(bookDetail);
				frame.setVisible(true);
				
				List<Book> searchBook = frame.getpBookList().getList().stream()
						.filter(book -> book.getBookNo().equals(bookNo))
						.collect(Collectors.toList());
				Book book = searchBook.get(0);
				int idxRent = frame.getpBookList().getList().indexOf(book);
				frame.getpBookList().table.setRowSelectionInterval(idxRent, idxRent);
				
			} else {
				RentalStatus user = rsService.showUserByBookTitleNoView(new Book(bookNo));
				
				ReturnPage frame = new ReturnPage();
				
				// user 찾아서 셀 선택되게 하기
				List<User> searchUser = frame.getpUserList().getList()
						.stream().filter(userList -> userList.getUserNo()==user.getUserNo().getUserNo())
						.collect(Collectors.toList());
				
				User userForList = searchUser.get(0);
				User userForDetail = userService.showUserByUserNoForDetail(new User(user.getUserNo().getUserNo()));
				int idxRent = frame.getpUserList().getList().indexOf(userForList);
				frame.getpUserList().table.setRowSelectionInterval(idxRent, idxRent);
				
				// userDetail에 값 채우기
				frame.getpUserDetail().setUser(userForDetail);
				
				// bookRentalDatail에 값 채우기
				frame.getpBookRentalDetail().setBook(bookDetail);
				
				//bookRentalList 뜨게하기
				System.out.println(user.getUserNo().getUserNo());
				List<RentalStatus> list = rsService.showRentalBooks(new User(user.getUserNo().getUserNo()));
				frame.getpBookRentalList().setList(list);
				frame.getpBookRentalList().setList();
				
				// bookRentalList 셀 선택되게 하기
				List<RentalStatus> searchRentalStatus = list.stream()
						.filter(rentalList -> rentalList.getBookNo().getBookNo().equals(bookNo))
						.collect(Collectors.toList());
				RentalStatus rentalStatus = searchRentalStatus.get(0);
				int idxRs = frame.getpBookRentalList().getList().indexOf(rentalStatus);
				frame.getpBookRentalList().table.setRowSelectionInterval(idxRs, idxRs);
				
				frame.setVisible(true);
			}
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












