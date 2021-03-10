package library_proj.dto;

public class SubUserAccount extends User {

	private String account;

	public SubUserAccount(String account) {
		this.account = account;
		super.setAccount(account);
	}
	
}
