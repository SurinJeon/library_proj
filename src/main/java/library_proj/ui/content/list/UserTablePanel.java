package library_proj.ui.content.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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
//	private List<User> userList;
	private List<RentalStatus> rentList;
	private BookRentalTablePanel pBookRentalList;
	private UserDetailPanel pUserDetail;
	
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
			// 1. rentalstatus에서 bookNo로 검색을 한 다음 userNo를 찾기 << dao작성해야됨
			// 2. 그 userNo를 통해서 rentalpage에 setUser하기!
		
			User userDetail = service.showUserByUserNoForDetail(new User(userNo));
			
			RentalPage frame = new RentalPage();
			frame.setVisible(true);
			frame.getpUserDetail().setUser(userDetail);
//			frame.getpUserList().table.
			frame.getpUserList().table.setRowSelectionInterval(idx, idx);
//			table.setRowSelectionInterval(idx, idx);			
			// userNo int값이랑 해당 cell value같은지 반복문 돌려서 알아내기>> setRowSelectionInterval(idx, idx); 하기
			
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

	
	
}
