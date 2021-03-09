package library_proj.dao;

import java.util.List;

import library_proj.dto.BookCategory;

public interface BookCategoryDao {

	List<BookCategory> selectBookCategoryByAll();
	BookCategory selectBookCategoryByNo(BookCategory bookcategory);
	int insertBookCategory(BookCategory bookcategory);
	int updateBookCategory(BookCategory bookcategory);
	int deleteBookCategory(BookCategory bookcategory);
	
}
