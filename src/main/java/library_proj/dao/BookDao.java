package library_proj.dao;

import java.util.List;

import library_proj.dto.Book;

public interface BookDao {

	List<Book> selectBookByAll();
	Book selectBookByNo(Book book);
	int insertBook(Book book);
	int updateBook(Book book);
	int deleteBook(Book book);
	
}
