package library_proj.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import library_proj.dto.Book;
import library_proj.dto.BookCategory;

@SuppressWarnings("serial")
public class BookDetailPanel extends JPanel {
	private JTextField tfBookTitle;
	private JTextField tfBookNo;
	private JTextField tfBookCategory;

	public BookDetailPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblBookTitle = new JLabel("도서제목:");
		lblBookTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookTitle.setBounds(12, 46, 57, 15);
		panel.add(lblBookTitle);
		
		tfBookTitle = new JTextField();
		tfBookTitle.setBounds(81, 43, 252, 21);
		panel.add(tfBookTitle);
		tfBookTitle.setColumns(10);
		
		JLabel lblBookNo = new JLabel("도서번호:");
		lblBookNo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookNo.setBounds(12, 76, 57, 15);
		panel.add(lblBookNo);
		
		tfBookNo = new JTextField();
		tfBookNo.setColumns(10);
		tfBookNo.setBounds(81, 73, 116, 21);
		panel.add(tfBookNo);
		
		JLabel lblText = new JLabel("대여도서상세정보");
		lblText.setBounds(12, 10, 185, 15);
		panel.add(lblText);
		
		JLabel lblBookCategory = new JLabel("도서구분:");
		lblBookCategory.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookCategory.setBounds(12, 104, 57, 15);
		panel.add(lblBookCategory);
		
		tfBookCategory = new JTextField();
		tfBookCategory.setColumns(10);
		tfBookCategory.setBounds(81, 101, 116, 21);
		panel.add(tfBookCategory);
	}
	
	public void setBook(Book book) {
		tfBookTitle.setText(book.getBookTitle());
		tfBookNo.setText(book.getBookNo());
		tfBookCategory.setText(book.getBookCategory().getCategoryName());
	}
	
	public Book getBook() {
		String bookNo = tfBookNo.getText();
		String bookTitle = tfBookTitle.getText();
		String bookCategoryName = tfBookCategory.getText();
		return new Book(bookNo, bookTitle, new BookCategory(bookCategoryName));
	}
	
	// validcheck 필요!!!
	
	public void clearTf() {
		tfBookTitle.setText("");
		tfBookNo.setText("");
		tfBookCategory.setText("");
	}
	
	
}
