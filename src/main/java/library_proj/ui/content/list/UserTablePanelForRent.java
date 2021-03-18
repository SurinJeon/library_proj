package library_proj.ui.content.list;

import java.util.List;

import javax.swing.SwingConstants;

import library_proj.dto.User;
import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBoxForRent;

@SuppressWarnings("serial")
public class UserTablePanelForRent extends AbstractCustomTablePanel<User>{
	
	private UserService service;
	private SearchUserComboBoxForRent pcmbUser;
	
	public UserTablePanelForRent() {
		
	}

	@Override
	protected void setAlignAndWidth() {
		
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);
		setTableCellWidth(150, 100, 250, 250);
		
	}

	@Override
	protected Object[] toArray(User u) {
		return new Object[] {u.getUserNo(), u.getUserName(), u.getTel(), u.getPhone()};
	}

	@Override
	protected void initList() {
		list = service.showUsers();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "이름", "전화번호", "휴대전화"};
	}

	public void setService(UserService service) {
		this.service = service;
	}
	
	public void setList(List<User> list) {
		this.list = list;
	}

}
