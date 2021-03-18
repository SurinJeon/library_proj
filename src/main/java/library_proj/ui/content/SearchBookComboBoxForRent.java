package library_proj.ui.content;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj.dto.Book;
import library_proj.dto.BookCategory;
import library_proj.dto.SubBookTitle;
import library_proj.service.BookService;
import library_proj.ui.content.list.BookTablePanel;
import library_proj.ui.content.list.BookTablePanelForRent;
import library_proj.ui.exception.NotAvailableException;

@SuppressWarnings("serial")
public class SearchBookComboBoxForRent extends JPanel implements ActionListener {
	private BookService service;
	private JTextField tfSearchBook;
	private JComboBox<String> cmbSearchBook;
	private BookTablePanelForRent pBookList;
	private JButton btnSearch;
	private List<Book> list;
	
	public SearchBookComboBoxForRent() {
		pBookList = new BookTablePanelForRent();
		initialize();
		cmbSearchBook.setSelectedIndex(-1);

	}
	private void initialize() {
		setLayout(new GridLayout(0, 6, 10, 0));
		
		JLabel lblSearchBook = new JLabel("검색방법:");
		lblSearchBook.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblSearchBook);
		
		cmbSearchBook = new JComboBox<String>();
		cmbSearchBook.setModel(new DefaultComboBoxModel<String>(new String[] {"도서번호", "도서제목", "도서분류"}));
		add(cmbSearchBook);
		
		tfSearchBook = new JTextField();
		add(tfSearchBook);
		tfSearchBook.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		add(btnSearch);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
	}
	
	public void setService(BookService service) {
		this.service = service;
	}
	
	public void clearTf() {
		tfSearchBook.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		list = new ArrayList<Book>();
		
		try {
			String searchItem = (String) cmbSearchBook.getSelectedItem();
			list = switchList(searchItem);
			if(list != null) {
				pBookList.setList(list);
				pBookList.setList();
			} else {
				throw new NotAvailableException("해당 도서가 존재하지 않습니다.");
			}
		} catch (NotAvailableException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			clearTf();
			
		}
	}
	
	private List<Book> switchList(String searchItem) {
		List<Book> list = new ArrayList<Book>();
		
		switch(searchItem) {
			case "도서번호":
				list = service.showBooksByNo(new Book(tfSearchBook.getText()));
				break;
			case "도서제목":
				list = service.showBooksByTitle(new SubBookTitle(tfSearchBook.getText()));
				break;
			case "도서분류":
				list = service.showBooksByCategory(new Book(new BookCategory(Integer.parseInt(tfSearchBook.getText()))));
				break;
		}
		return list;
	}
	public BookTablePanelForRent getpBookList() {
		return pBookList;
	}
	
	

}
