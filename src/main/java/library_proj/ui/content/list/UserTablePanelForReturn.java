package library_proj.ui.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.service.RentalStatusService;
import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBoxForReturn;
import library_proj.ui.content.UserDetailPanel;

@SuppressWarnings("serial")
public class UserTablePanelForReturn extends AbstractCustomTablePanel<User> implements MouseListener{
	
	private UserService service;
	private RentalStatusService rsService;
	private SearchUserComboBoxForReturn pcmbUser;
	private UserDetailPanel pUserDetail;
	private BookRentalTablePanelForReturn pBookRentalList;
	
	public UserTablePanelForReturn() {
		table.addMouseListener(this);
		rsService = new RentalStatusService();
		pBookRentalList = new BookRentalTablePanelForReturn();
		pUserDetail = new UserDetailPanel();
		pBookRentalList = new BookRentalTablePanelForReturn();
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
			
			// detail에 setting
			User searchUser = service.showUserByUserNoForDetail(new User(userNo));
			if (searchUser != null) {
				pUserDetail.setUser(searchUser);
			}
			
			List<RentalStatus> list = rsService.showRentalBooks(new User(userNo));
			List blankList = new ArrayList();
			
			if(list != null) {
				pBookRentalList.setList(list);
				pBookRentalList.setList();
			} else {
				pBookRentalList.setList(blankList);
				pBookRentalList.setList();
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

	public BookRentalTablePanelForReturn getpBookRentalList() {
		return pBookRentalList;
	}
	
	
}
