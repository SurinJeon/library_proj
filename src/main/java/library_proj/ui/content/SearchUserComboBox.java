package library_proj.ui.content;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library_proj.dto.SubUserAccount;
import library_proj.dto.SubUserPhone;
import library_proj.dto.User;
import library_proj.service.UserService;
import library_proj.ui.content.list.UserTablePanel;
import library_proj.ui.exception.NotAvailableException;

@SuppressWarnings("serial")
public class SearchUserComboBox extends JPanel implements ActionListener {
	private JTextField tfSearchUser;
	private JButton btnSearch;
	private UserService service;
	private JComboBox<String> cmbSearchUser;
	private List<User> list;
	private UserTablePanel pUserList;

	public SearchUserComboBox() {
		pUserList = new UserTablePanel();
		initialize();
		cmbSearchUser.setSelectedIndex(-1);
	}
	private void initialize() {
		setLayout(new GridLayout(0, 4, 10, 0));
		
		JLabel lblSearchUser = new JLabel("빠른회원검색 : ");
		lblSearchUser.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblSearchUser);
		
		cmbSearchUser = new JComboBox<String>();
		cmbSearchUser.setModel(new DefaultComboBoxModel<String>(new String[] {"회원번호", "회원이름", "계정", "휴대전화"}));
		add(cmbSearchUser);
		
		tfSearchUser = new JTextField();
		add(tfSearchUser);
		tfSearchUser.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		add(btnSearch);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		list = new ArrayList<User>();
		List blankList = new ArrayList();
		try {
			String searchItem = (String) cmbSearchUser.getSelectedItem();
			System.out.println(searchItem);
			list = switchList(searchItem);
			
			if(list != null) {
				pUserList.setList(list);
				pUserList.setList(); /*loadData() 전체 쓰지말고 setList만! (initList쓰면 showall로 초기화됨...)*/
			} else {
				throw new NotAvailableException("해당 회원이 존재하지 않습니다.");
			}
		} catch (NotAvailableException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			pUserList.setList(blankList);
			pUserList.setList();
			clearTf();
		}
	}
	
	public List<User> switchList(String searchItem) {
		List<User> list = new ArrayList<User>();
		
		try {
			switch(searchItem){
			case "회원번호":
				list = service.showUsersByUserNo(new User(Integer.parseInt(tfSearchUser.getText())));		
				break;
			case "회원이름":
				list = service.showUsersByUserName(new User(tfSearchUser.getText()));
				break;
			case "계정":
				list = service.showUsersByUserAccount(new SubUserAccount(tfSearchUser.getText()));
				break;
			case "휴대전화":
				list = service.showUsersByUserPhone(new SubUserPhone(tfSearchUser.getText()));
				break;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "형식에 알맞게 입력해주세요.");
		}
		return list;
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	public List<User> getList() {
		return list;
	}
	public UserTablePanel getpUserList() {
		return pUserList;
	}
	
	public void clearTf() {
		tfSearchUser.setText("");
	}
	
	
}
