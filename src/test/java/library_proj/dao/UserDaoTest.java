package library_proj.dao;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import library_proj.dao.Impl.UserDaoImpl;
import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.SubUserAccount;
import library_proj.dto.SubUserPhone;
import library_proj.dto.User;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

	UserDao dao = UserDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	//@Test
	public void test04SelectUserByAll() {
		System.out.printf("%s()%n", "testSelectUserByAll");
		List<User> userList = dao.selectUserByAll();
		Assert.assertNotNull(userList);
		
		userList.stream().forEach(System.out::println);
	}

	//@Test
	public void test05SelectUserByNo() {
		System.out.printf("%s()%n", "testSelectUserByNo");
		User user = new User(12001);
		List<User> searchUserList = dao.selectUserByNo(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}

	@Test
	public void test06SelectUserByName() {
		System.out.printf("%s()%n", "testSelectUserByName");
		User user = new User("김동%");
		List<User> searchUserList = dao.selectUserByName(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);

	}

	//@Test
	public void test07SelectUserByPhone() {
		System.out.printf("%s()%n", "testSelectUserByPhone");
		User user = new SubUserPhone("010-7444-1474");
		List<User> searchUserList = dao.selectUserByPhone(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}

////	@Test
	public void test08SelectUserByAccount() {
		System.out.printf("%s()%n", "testSelectUserByAccount");
		User user = new SubUserAccount("%abc%");
		
		List<User> searchUserList = UserDaoImpl.getInstance().selectUserByAccount(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}
	
	@SuppressWarnings("deprecation")
	//@Test
	public void test09SelectUserByBirth() {
		System.out.printf("%s()%n", "testSelectUserByBirth");
		User user = new User(new Date(7, 3, 5));
		System.out.println(user);
		List<User> searchUserList = dao.selectUserByBirth(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}

//	@Test
	public void test10SelectUserByView() {
		System.out.printf("%s()%n", "testSelectUserByView");
		User user = new User(12001);
		Book book = new Book("40001-1");
		RentalStatus rentalStatus = new RentalStatus(1);
		System.out.println(user);
		List<User> searchUserList = dao.selectUserByView(user, book, rentalStatus);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}
	
	//@Test
	public void test11Select() {
//		System.out.printf("%s()%n", "testSelectUserByAll");
		List<User> userList = dao.selectUserByAllForList();
		Assert.assertNotNull(userList);
		
		userList.stream().forEach(System.out::println);
	}
	
	@SuppressWarnings("deprecation")
//	@Test
	public void test01InsertUser() {
		System.out.printf("%s()%n", "testInsertUser");
		User newUser = new User(12012, "전수린", new Date(95, 10, 01), "surin@abc.com", "053-555-5555", "010-1111-2222", "대구");
		int res = dao.insertUser(newUser);
		Assert.assertEquals(1, res);
		System.out.println(newUser);
		
	}

//	@Test
	public void test02UpdateUser() {
		System.out.printf("%s()%n", "testUpdateUser");
		User upUser = new User(12012, "짱정아", new Date(93, 03, 01), "zzangstella@abc.com", "053-555-5555", "010-1111-2222", "대구");
		int res = dao.updateUser(upUser);
		Assert.assertEquals(1, res);
		System.out.println(upUser);
	}

//	@Test
	public void test03DeleteUser() {
		System.out.printf("%s()%n", "testDeleteUser");
		User delUser = new User(12012);
		int res = dao.deleteUser(delUser);
		Assert.assertEquals(1, res);
		dao.selectUserByAll().stream().forEach(System.out::println);
	}

}
