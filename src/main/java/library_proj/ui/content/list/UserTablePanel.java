package library_proj.ui.content.list;

import javax.swing.SwingConstants;

import library_proj.dto.User;
import library_proj.service.UserService;

@SuppressWarnings("serial")
public class UserTablePanel extends AbstractCustomTablePanel<User> {
	
	private UserService service;
	
	
	@Override
	protected void setAlignAndWidth() {
		
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);
		setTableCellWidth(100, 100, 250, 250);
		
	}

	@Override
	protected Object[] toArray(User u) {
		return new Object[] {u.getUserNo(), u.getUserName(), u.getTel(), u.getPhone()};
	}

	@Override
	protected void initList() {
		list = service.showUsers();
		System.out.println(list);
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "이름", "전화번호", "휴대전화"};
	}

	public void setService(UserService service) {
		this.service = service;
	}
	
}
