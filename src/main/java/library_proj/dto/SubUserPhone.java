package library_proj.dto;

public class SubUserPhone extends User {

	private String phone;

	public SubUserPhone(String phone) {
		this.phone = phone;
		super.setPhone(phone);
	}
	
	
	
}
