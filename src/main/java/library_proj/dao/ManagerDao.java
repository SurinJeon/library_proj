package library_proj.dao;

import java.util.List;

import library_proj.dto.Manager;

public interface ManagerDao {

	List<Manager> selectManagerByAll();
	Manager selectManagerByNo(Manager manager);
	int insertManager(Manager manager);
	int updateManager(Manager manager);
	int deleteManager(Manager manager);
	
}
