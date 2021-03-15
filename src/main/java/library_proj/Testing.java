package library_proj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import library_proj.service.UserService;
import library_proj.ui.RentalPage;
import library_proj.ui.ReturnPage;
import library_proj.ui.content.list.UserTablePanel;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class Testing extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnRent;
	private JButton btnReturn;
	private UserService service;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testing frame = new Testing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Testing() {
		service = new UserService();
		initialize();
	}
	
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pBtn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pBtn.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		btnRent = new JButton("대출하기");
		btnRent.addActionListener(this);
		pBtn.add(btnRent);
		
		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);
		
		JPanel pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 5, 0));
		
		UserTablePanel pUser = new UserTablePanel();
		pUser.setService(service);
		pUser.loadData();
		pSearch.add(pUser);
		
		JPanel pBook = new JPanel();
		pSearch.add(pBook);
		
		JPanel pList = new JPanel();
		contentPane.add(pList, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReturn) {
			actionPerformedBtnReturn(e);
		}
		if (e.getSource() == btnRent) {
			actionPerformedBtnRent(e);
		}
	}
	protected void actionPerformedBtnRent(ActionEvent e) {
		RentalPage frame = new RentalPage();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnReturn(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setVisible(true);
	}
}

