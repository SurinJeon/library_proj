package library_proj.dao;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import library_proj.dao.Impl.UserDaoImpl;
import library_proj.dto.Book;
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

	@Test
	public void test04SelectUserByAll() {
		System.out.printf("%s()%n", "testSelectUserByAll");
		List<User> userList = dao.selectUserByAll();
		Assert.assertNotNull(userList);
		
		userList.stream().forEach(System.out::println);
	}

	@Test
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
		User user = new User("배진태");
		List<User> searchUserList = dao.selectUserByName(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);

	}

	@Test
	public void test07SelectUserByPhone() {
		System.out.printf("%s()%n", "testSelectUserByPhone");
		User user = new SubUserPhone("010-7444-1474");
		List<User> searchUserList = dao.selectUserByPhone(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}

	@Test
	public void test08SelectUserByAccount() {
		System.out.printf("%s()%n", "testSelectUserByAccount");
		User user = new SubUserAccount("happymoon@abc.com");
		
		List<User> searchUserList = dao.selectUserByAccount(user);
		Assert.assertNotNull(searchUserList);
		searchUserList.stream().forEach(System.out::println);
	}

//	@Test
//	public void test01InsertUser() {
//		System.out.printf("%s()%n", "testInsertUser");
//		
//		Calendar cal = Calendar.getInstance();
//		cal.set(1995, 8, 1);
//		User newUser = new User(12012, "전수린", new Date(cal.getTimeInMillis()), "surin@abc.com", "053-555-5555", "010-1111-2222", "대구");
//		
//		int res = dao.insertUser(newUser);
//		Assert.assertEquals(1, res);
//		
//	}

	@Test
	public void test02UpdateUser() {
		fail("Not yet implemented");
		System.out.printf("%s()%n", "testUpdateUser");
	}

	@Test
	public void test03DeleteUser() {
		fail("Not yet implemented");
		System.out.printf("%s()%n", "testDeleteUser");
	}

}
