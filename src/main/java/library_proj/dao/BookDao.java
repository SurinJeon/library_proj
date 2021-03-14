package library_proj.dao;

import java.util.List;

import library_proj.dto.Book;
import library_proj.dto.User;

public interface BookDao {

	List<Book> selectBookByAll();
	Book selectBookByNo(Book book);
	List<Book> selectBookByTitle(Book book);
	List<Book> selectBookByCategory(Book book);
	List<Book> selectBookByUserNo(User user);
	int insertBook(Book book);
	int updateBook(Book book);
	int deleteBook(Book book);
	
}
