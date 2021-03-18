package library_proj.ui.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.service.RentalStatusService;
import library_proj.service.UserService;
import library_proj.ui.content.SearchUserComboBox;
import library_proj.ui.exception.NullListException;

@SuppressWarnings("serial")
public class UserTablePanel extends AbstractCustomTablePanel<User> implements MouseListener{
	
	private UserService service;
	private SearchUserComboBox pcmbUser;
	private RentalStatusService rentalService;
//	private List<User> userList;
	private List<RentalStatus> rentList;
	private BookRentalTablePanel pBookRentalList;
	
	public UserTablePanel() {
		table.addMouseListener(this);
//		list = new ArrayList<User>();
		pBookRentalList = new BookRentalTablePanel();
		rentalService = new RentalStatusService();
		
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
//		list = pcmbUser.getList();
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
	
	public void setrentList(List<RentalStatus> list) {
		this.rentList = list;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		try {
			List<RentalStatus> rentList = new ArrayList<RentalStatus>();
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			int userNo = (int) table.getValueAt(idx, 0);
			rentList = rentalService.showRentalBooks(new User(userNo));
			if(rentList != null) {
			
//			rentList.stream().forEach(System.out::println);
			
			pBookRentalList.setList(rentList);
			pBookRentalList.setList();
			} else {
				List list = new ArrayList();
				pBookRentalList.setList(list);
				pBookRentalList.setList();
				
				throw new NullListException("대여도서가 없습니다.");
			}
		} catch (NullListException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
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

	public BookRentalTablePanel getpBookRentalList() {
		return pBookRentalList;
	}

	
	
}
