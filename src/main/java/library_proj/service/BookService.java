package library_proj.service;

import java.util.List;

import library_proj.dao.BookDao;
import library_proj.dao.Impl.BookDaoImpl;
import library_proj.dto.Book;

public class BookService {

	private BookDao daoBook = BookDaoImpl.getInstance();

	public List<Book> showBooks() {
		return daoBook.selectBookByAll();
	}
	
	public List<Book> showBooksByNo(Book book){
		return daoBook.selectBookByNo(book);
	}
	
	public List<Book> showBooksByTitle(Book book){
		return daoBook.selectBookByTitle(book);
	}
	
	public List<Book> showBooksByCategory(Book book){
		return daoBook.selectBookByCategory(book);
	}
}
