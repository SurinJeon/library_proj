package library_proj.ui.content.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj.dto.Book;
import library_proj.service.BookService;
import library_proj.service.RentalService;
import library_proj.ui.content.BookDetailPanel;

@SuppressWarnings("serial")
public class BookTablePanelForRent extends AbstractCustomTablePanel<Book> implements MouseListener{

	private BookService service;
	private BookDetailPanel pBookDetail;
	
	
	public BookTablePanelForRent() {
		table.addMouseListener(this);
		pBookDetail = new BookDetailPanel();
	}


	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		setTableCellWidth(150, 400, 200);

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
	
	public void setService(BookService service) {
		this.service = service;
	}
	
	

	public void setList(List<Book> list) {
		this.list = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			String bookNo = (String) table.getValueAt(idx, 0);
			Book searchBook = service.showBooksByNoForDetail(new Book(bookNo));
			System.out.println(searchBook);
			if (searchBook != null) {
				pBookDetail.setBook(searchBook);
			}
		} catch (Exception e1) {
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

	public BookDetailPanel getpBookDetail() {
		return pBookDetail;
	}
	
//	public Book getBook(Book book) {
//		
//		return new Book(bookNo, bookTitle, isRented, bookCategory, count, rentalRange);
//	}
	
}












