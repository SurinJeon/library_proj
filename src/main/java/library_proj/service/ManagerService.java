package library_proj.service;

import library_proj.dao.ManagerDao;
import library_proj.dao.Impl.ManagerDaoImpl;
import library_proj.dto.Manager;

public class ManagerService {

	private ManagerDao daoMn = ManagerDaoImpl.getInstance();
	
	public Manager selectManagerById(Manager manager) {
		return daoMn.selectManagerById(manager);
	}
}
