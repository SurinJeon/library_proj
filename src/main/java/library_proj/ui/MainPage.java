package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainPage extends JFrame {

	private JPanel contentPane;

	public MainPage() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.NORTH);
		
		JButton btnRent = new JButton("대출하기");
		pBtn.add(btnRent);
		
		JButton btnReturn = new JButton("반납하기");
		pBtn.add(btnReturn);
		
		JPanel pSearch = new JPanel();
		contentPane.add(pSearch, BorderLayout.CENTER);
		pSearch.setLayout(new GridLayout(0, 2, 5, 0));
		
		JPanel pUser = new JPanel();
		pSearch.add(pUser);
		
		JPanel pBook = new JPanel();
		pSearch.add(pBook);
		
		JPanel pList = new JPanel();
		contentPane.add(pList, BorderLayout.SOUTH);
	}

}
