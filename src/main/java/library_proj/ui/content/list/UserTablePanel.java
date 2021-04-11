package library_proj.ui.content.list;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.service.BookService;
import library_proj.service.RentalStatusService;
import library_proj.service.UserService;
import library_proj.ui.RentalPage;
import library_proj.ui.content.SearchUserComboBox;
import library_proj.ui.content.UserDetailPanel;
import library_proj.ui.exception.NullListException;

@SuppressWarnings("serial")
public class UserTablePanel extends AbstractCustomTablePanel<User> implements MouseListener{
	
	private UserService service;
	private BookService bookService;
	private SearchUserComboBox pcmbUser;
	private RentalStatusService rentalService;
	private List<RentalStatus> rentList;
	private BookRentalTablePanel pBookRentalList;
	private UserDetailPanel pUserDetail;
	private BookTablePanel pBookListMain;
	
	public UserTablePanel() {
		table.addMouseListener(this);
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
		
		if(e.getClickCount() == 2) {
			JTable table = (JTable)e.getSource();
			int idx = table.getSelectedRow();
			int userNo = (Integer)table.getValueAt(idx, 0);
		
			User userDetail = service.showUserByUserNoForDetail(new User(userNo));
			RentalPage frame = new RentalPage();
			frame.setpBookListMain(pBookListMain);
			frame.getpUserDetail().setUser(userDetail);
			frame.setVisible(true);
			
			
			
			List<User> searchUser = frame.getpUserList().getList()
					.stream().filter(user -> user.getUserNo()==userNo)
					.collect(Collectors.toList());
			User user = searchUser.get(0);
			int idxRent = frame.getpUserList().getList().indexOf(user);
			frame.getpUserList().table.setRowSelectionInterval(idxRent, idxRent);
			frame.getpUserList().table.scrollRectToVisible(new Rectangle(table.getCellRect(idxRent, 0, true)));
			
		}
		
		
		if(e.getClickCount() == 1) {
			try {
				List<RentalStatus> rentList = new ArrayList<RentalStatus>();
				JTable table = (JTable)e.getSource();
				int idx = table.getSelectedRow();
				int userNo = (int) table.getValueAt(idx, 0);
				rentList = rentalService.showRentalBooks(new User(userNo));
				
				if(rentList != null) {
					pBookRentalList.setList(rentList);
					pBookRentalList.setList();
				} else {
					List list = new ArrayList();
					pBookRentalList.setList(list);
					pBookRentalList.setList();
				}
			} catch (NullListException e1) {
				e1.printStackTrace();
			}
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

	public void setpBookListMain(BookTablePanel pBookListMain) {
		this.pBookListMain = pBookListMain;
	}
	
	
	
}
