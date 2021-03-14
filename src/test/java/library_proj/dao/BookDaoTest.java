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
import library_proj.dto.RentalStatus;
import library_proj.dto.SubBookTitle;
import library_proj.dto.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookDaoTest {
	private static BookDao dao = BookDaoImpl.getInstance();
	
//	@After
//	public void tearDown() throws Exception {
//		System.out.println();
//	}
//
//	@Test
//	public void test04SelectBookByAll() {
//		System.out.printf("%s()%n", "testSelectBookByAll");
//		List<Book> bookList = dao.selectBookByAll();
//		Assert.assertNotNull(bookList);
//		
//		bookList.stream().forEach(System.out::println);
//	}
//
//	@Test
//	public void test05SelectBookByNo() {
//		System.out.printf("%s()%n", "testSelectBookByNo");
//		Book book = new Book("40001-1");
//		Book searchBook = dao.selectBookByNo(book);
//		Assert.assertNotNull(searchBook);
//		System.out.println(searchBook);
//	}
//
//	@Test
//	public void test06SelectBookByTitle() {
//		System.out.printf("%s()%n", "SelectBookByName");
//		Book book = new SubBookTitle("Machine learning");
//		List<Book> searchBookList = dao.selectBookByTitle(book);
//		Assert.assertNotNull(searchBookList);
//		searchBookList.stream().forEach(System.out::println);
//	}
//
//	@Test
//	public void test07SelectBookByCategory() {
//		System.out.printf("%s()%n", "SelectBookByCategory");
//		Book book = new Book(new BookCategory(4));
//		List<Book> searchBookList = dao.selectBookByCategory(book);
//		Assert.assertNotNull(searchBookList);
//		searchBookList.stream().forEach(System.out::println);
//
//	}
	
	@Test
	public void test08SelectBookByUserNo() {
		System.out.printf("%s()%n", "SelectBookByUserNo");
		User user = new User(12001);
		List<Book> searchRentalBookList = dao.selectBookByUserNo(user);
		Assert.assertNotNull(searchRentalBookList);
		searchRentalBookList.stream().forEach(System.out::println);
		System.out.println(searchRentalBookList.get(0));
//		System.out.println(searchRentalBookList);
		
	}

//	@Test
//	public void test01InsertBook() {
//		System.out.printf("%s()%n", "testInsertBook()");
//		Book newBook = new Book("40008-6", "Machine learning", 1, new BookCategory(3), 1, 5);
//		int res = dao.insertBook(newBook);
//		Assert.assertEquals(1, res);
//		dao.selectBookByAll().stream().forEach(System.out::println);
//	}
//
//	@Test
//	public void test02UpdateBook() {
//		System.out.printf("%s()%n", "testUpdateBook");
//		Book upBook = new Book("40008-6", "Machine Not learning", 1, new BookCategory(3), 1, 5);
//		int res = dao.updateBook(upBook);
//		Assert.assertEquals(1, res);
//		System.out.println(upBook);
//	}
//
//	@Test
//	public void test03DeleteBook() {
//		System.out.printf("%s()%n", "testDeleteBook");
//		Book delBook = new Book("40008-6");
//		int res = dao.deleteBook(delBook);
//		Assert.assertEquals(1, res);
//		System.out.println(res);
//	}

}
