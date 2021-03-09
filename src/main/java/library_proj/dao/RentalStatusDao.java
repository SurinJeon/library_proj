package library_proj.dao;

import java.util.List;

import library_proj.dto.RentalStatus;

public interface RentalStatusDao {

	// join부분을 view로 만들어야됨 (잊지말기...)
	List<RentalStatus> selectRentalStatusByAll();
	RentalStatus selectRentalStatusByNo(RentalStatus rentalstatus);
	int insertRentalStatus(RentalStatus rentalstatus);
	int updateRentalStatus(RentalStatus rentalstatus);
	int deleteRentalStatus(RentalStatus rentalstatus);
	
}
