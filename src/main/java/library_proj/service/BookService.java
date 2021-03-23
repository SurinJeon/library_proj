package library_proj.service;

import java.util.List;

import library_proj.dao.BookDao;
import library_proj.dao.Impl.BookDaoImpl;
import library_proj.dto.Book;
import library_proj.dto.User;

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
	
	public Book showBooksByNoForDetail(Book book) {
		return daoBook.selectBookByNoForDetail(book);
	}
	
	public List<Book> showBooksByNoForRent(Book book){
		return daoBook.selectBookByNoForRent(book);
	}
	
	public List<Book> showBooksByTitleForRent(Book book){
		return daoBook.selectBookByTitleForRent(book);
	}
	
	public List<Book> showBooksByCategoryForRent(Book book){
		return daoBook.selectBookByCategoryForRent(book);
	}
	
	public List<Book> showBooksByUserNo(User user){
		return daoBook.selectBookByUserNo(user);
	}
}
