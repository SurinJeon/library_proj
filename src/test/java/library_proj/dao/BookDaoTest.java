package library_proj.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import library_proj.dao.Impl.BookDaoImpl;
import library_proj.dto.Book;
import library_proj.dto.BookCategory;
import library_proj.dto.subBookTitle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookDaoTest {

	private static BookDao dao = BookDaoImpl.getInstance();
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectBookByAll() {
		System.out.printf("%s()%n", "testSelectBookByAll");
		List<Book> bookList = dao.selectBookByAll();
		Assert.assertNotNull(bookList);
		
		bookList.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectBookByNo() {
		System.out.printf("%s()%n", "testSelectBookByNo");
		Book book = new Book("40001-1");
		Book searchBook = dao.selectBookByNo(book);
		Assert.assertNotNull(searchBook);
		System.out.println(searchBook);
	}

	@Test
	public void test06SelectBookByName() {
		System.out.printf("%s()%n", "SelectBookByName");
		Book book = new subBookTitle("Machine learning");
		Book searchBook = dao.selectBookByTitle(book);
		Assert.assertNotNull(searchBook);
		System.out.println(searchBook);
	}

	@Test
	public void test01SelectBookByCategory() {
		System.out.printf("%s()%n", "SelectBookByCategory");
		Book book = new Book(new BookCategory(4));
		Book searchBook = dao.selectBookByCategory(book);
		Assert.assertNotNull(searchBook);
		System.out.println(searchBook);
	}

	@Test
	public void test02InsertBook() {
//		new Book(bookNo, bookTitle, isRented, bookCategory, count, rentalRange)
		System.out.printf("%s()%n", "testInsertBook()");
		Book newBook = new Book("40008-6", "Machine learning", 1, new BookCategory(3), 1, 5);
		int res = dao.insertBook(newBook);
		Assert.assertEquals(1, res);
		System.out.println(res);
		dao.selectBookByAll().stream().forEach(System.out::println);
	}


	
	@Test
	public void test03DeleteBook() {
		System.out.printf("%s()%n", "testDeleteBook");
		Book delBook = new Book("40008-6");
		int res = dao.deleteBook(delBook);
		Assert.assertEquals(1, res);
		System.out.println(res);
		dao.selectBookByAll().stream().forEach(System.out::println);
	}

}
