package library_proj.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import library_proj.dao.Impl.ManagerDaoImpl;
import library_proj.dto.Manager;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerDaoTest {

	private static ManagerDao dao = ManagerDaoImpl.getInstance();
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectManagerByAll() {
		System.out.printf("%s()%n", "testSelectManagerByAll");
		List<Manager> managerList = dao.selectManagerByAll();
		Assert.assertNotNull(managerList);
		
		managerList.stream().forEach(System.out::println);
		
	}

	@Test
	public void test05SelectManagerByNo() {
		System.out.printf("%s()%n", "testSelectManagerByNo");
		Manager mng = new Manager("manager_1@lib.go.kr");
		Manager searchMng = dao.selectManagerByNo(mng);
		Assert.assertNotNull(searchMng);
		System.out.println(searchMng);
	}

	@Test
	public void test01InsertManager() {
		System.out.printf("%s()%n", "testInsertManager");
		Manager newMng = new Manager("manager_4@lib.go.kr", "11223344");
		int res = dao.insertManager(newMng);
		Assert.assertEquals(1, res);
		System.out.println("반영된 행 갯수 : " + res);
		System.out.println(dao.selectManagerByNo(newMng));
	}

	@Test
	public void test02UpdateManager() {
		System.out.printf("%s()%n", "testUpdateManager");
		Manager upMng = new Manager("manager_4@lib.go.kr", "rootroot");
		int res = dao.updateManager(upMng);
		Assert.assertEquals(1, res);
		System.out.println("반영된 행 갯수 : " + res);
		System.out.println(dao.selectManagerByNo(upMng));
	}

	@Test
	public void test03DeleteManager() {
		System.out.printf("%s()%n", "testDeleteManager");
		Manager delMng = new Manager("manager_4@lib.go.kr");
		int res = dao.deleteManager(delMng);
		Assert.assertEquals(1, res);
		System.out.println("반영된 행 갯수 : " + res);
		dao.selectManagerByAll().stream().forEach(System.out::println);
	}
	

}
