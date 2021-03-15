package library_proj.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SearchUserComboBox extends JPanel {
	private JTextField tfSearchUser;

	/**
	 * Create the panel.
	 */
	public SearchUserComboBox() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 3, 10, 0));
		
		JLabel lblSearchUser = new JLabel("빠른회원검색 : ");
		lblSearchUser.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblSearchUser);
		
		JComboBox cmbSearchUser = new JComboBox();
		cmbSearchUser.setModel(new DefaultComboBoxModel(new String[] {"회원번호", "회원이름", "계정", "휴대전화"}));
		add(cmbSearchUser);
		
		tfSearchUser = new JTextField();
		add(tfSearchUser);
		tfSearchUser.setColumns(10);
	}

}
