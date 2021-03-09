package library_proj.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import library_proj.dao.Impl.BookCategoryDaoImpl;
import library_proj.dto.BookCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookCategoryDaoTest {

	private static BookCategoryDao dao = BookCategoryDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectBookCategoryByAll() {
		System.out.printf("%s()%n", "testSelectManagerByAll");
		List<BookCategory> bookCategoryList = dao.selectBookCategoryByAll();
		Assert.assertNotNull(bookCategoryList);

		bookCategoryList.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectBookCategoryByNo() {
		System.out.printf("%s()%n", "SelectBookCategoryByNo");
		BookCategory bc = new BookCategory(4);
		BookCategory searchBc = dao.selectBookCategoryByNo(bc);
		Assert.assertNotNull(searchBc);
		System.out.println(searchBc);
	}

	@Test
	public void test01InsertBookCategory() {
		System.out.printf("%s()%n", "testInsertBookCategory");
		BookCategory newBc = new BookCategory(5, "문학");
		int res = dao.insertBookCategory(newBc);
		Assert.assertEquals(1, res);
		System.out.println("반영된 행 갯수 : " + res);
		System.out.println(dao.selectBookCategoryByNo(newBc));
	}

	@Test
	public void test02UpdateBookCategory() {
		System.out.printf("%s()%n", "testUpdateBookCategory");
		BookCategory upBc = new BookCategory(5, "철학");
		int res = dao.updateBookCategory(upBc);
		Assert.assertEquals(1, res);
		System.out.println("반영된 행 갯수 : " + res);
		System.out.println(dao.selectBookCategoryByNo(upBc));
	}

	@Test
	public void test03DeleteBookCategory() {
		System.out.printf("%s()%n", "testDeleteBookCategory");
		BookCategory delBc = new BookCategory(5);
		int res = dao.deleteBookCategory(delBc);
		Assert.assertEquals(1, res);
		System.out.println("반영된 행 갯수 : " + res);
		dao.selectBookCategoryByAll().stream().forEach(System.out::println);
	}

}
