package library_proj.ui.content.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import library_proj.dto.Book;
import library_proj.service.BookService;
import library_proj.ui.RentalPage;

@SuppressWarnings("serial")
public class BookTablePanel extends AbstractCustomTablePanel<Book> implements MouseListener{

	private BookService service;
	
	public BookTablePanel() {
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
			String searchBookNo = (String)table.getValueAt(idx, 0);
			
			Book book = service.showBooksByNoForDetail(new Book(searchBookNo));
			
			if (book != null && book.getIsRented() != 0) {
				RentalPage frame = new RentalPage();
				frame.getpBookDetail().setBook(book);
				frame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "대여가 불가능한 도서입니다.");
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












