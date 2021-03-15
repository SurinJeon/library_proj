package library_proj.service;

import java.util.List;

import library_proj.dao.UserDao;
import library_proj.dao.Impl.UserDaoImpl;
import library_proj.dto.User;

public class UserService {

	private UserDao daoUser = UserDaoImpl.getInstance();
	
	public List<User> showUsers(){

		List<User> list = daoUser.selectUserByAllForList();
		list.stream().forEach(System.out::println);
		return daoUser.selectUserByAllForList();
	}
	
	
}
