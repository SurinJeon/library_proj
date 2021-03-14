package library_proj.dao;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import library_proj.dao.Impl.RentalStatusDaoImpl;
import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentalStatusDaoTest {
	RentalStatusDao dao = RentalStatusDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectRentalStatusByAll() {
		System.out.printf("%s()%n", "testSelectRentalStatusByAll");
		List<RentalStatus> rentalList = dao.selectRentalStatusByAll();
		Assert.assertNotNull(rentalList);
		
		rentalList.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectRentalStatusByUserNo() {
		System.out.printf("%s()%n", "testSelectRentalStatusByUserNo");
		User user = new User(12001);
		List<RentalStatus> rentalList = dao.selectRentalStatusByUserNo(user);
		Assert.assertNotNull(rentalList);
		
		rentalList.stream().forEach(System.out::println);
	}

	@Test
	public void test01InsertRentalStatus() {
		System.out.printf("%s()%n", "testInsertRentalStatus");
		Book book = new Book("40006-1");
		User user = new User(12005);
		RentalStatus newRs = new RentalStatus(book, user, new Date(21, 2, 9));
		int res = dao.insertRentalStatus(newRs);
		Assert.assertEquals(1, res);
		System.out.println(newRs);
		
	}
		
		

	@Test
	public void test02UpdateRentalStatus() {
		System.out.printf("%s()%n", "testUpdateRentalStatus");
		Book book = new Book("40006-1");
		User user = new User(12005);
		RentalStatus upRs = new RentalStatus(9, book, user, new Date(21, 2, 9), new Date(21, 2, 12), 0);
		int res = dao.updateRentalStatus(upRs);
		Assert.assertEquals(1, res);
		System.out.println(upRs);
		
	}

	@Test
	public void test03DeleteRentalStatus() {
		System.out.printf("%s()%n", "testDeleteRentalStatus");
		RentalStatus delRs = new RentalStatus(11);
		int res = dao.deleteRentalStatus(delRs);
		Assert.assertEquals(1, res);
		dao.selectRentalStatusByAll().stream().forEach(System.out::println);
	}

}
