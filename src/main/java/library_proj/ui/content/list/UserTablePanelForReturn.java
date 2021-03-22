package library_proj.ui.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj.dto.User;
import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBoxForReturn;
import library_proj.ui.content.UserDetailPanel;

@SuppressWarnings("serial")
public class UserTablePanelForReturn extends AbstractCustomTablePanel<User> implements MouseListener{
	
	private UserService service;
	private SearchUserComboBoxForReturn pcmbUser;
	private UserDetailPanel pUserDetail;
	
	public UserTablePanelForReturn() {
		table.addMouseListener(this);
		pUserDetail = new UserDetailPanel();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			int userNo = (int)table.getValueAt(idx, 0);
			
			User searchUser = service.showUserByUserNoForDetail(new User(userNo));
			if (searchUser != null) {
				// 1. 해당 행 가져와서
				// 2. 그 행을 service에서 서치한 것을 setting
				pUserDetail.setUser(searchUser);
			}
			
		} catch (Exception e1) {
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	public UserDetailPanel getpUserDetail() {
		return pUserDetail;
	}
	
}
