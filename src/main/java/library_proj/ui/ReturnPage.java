package library_proj.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class ReturnPage extends JFrame {

	private JPanel contentPane;

	public ReturnPage() {
		initialize();
	}
	private void initialize() {
		setTitle("반납화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pCenter = new JPanel();
		contentPane.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel pSearch1 = new JPanel();
		pCenter.add(pSearch1);
		pSearch1.setLayout(new BorderLayout(0, 0));
		
		JPanel pCmbUser = new JPanel();
		pSearch1.add(pCmbUser, BorderLayout.NORTH);
		
		JPanel pUserList = new JPanel();
		pSearch1.add(pUserList, BorderLayout.CENTER);
		
		JPanel pUserDetail = new JPanel();
		pCenter.add(pUserDetail);
		
		JPanel pBookRentalList = new JPanel();
		pCenter.add(pBookRentalList);
		pBookRentalList.setLayout(new BorderLayout(0, 0));
		
		JPanel pBookRentalDetail = new JPanel();
		pCenter.add(pBookRentalDetail);
		
		JPanel pBtn = new JPanel();
		FlowLayout fl_pBtn = (FlowLayout) pBtn.getLayout();
		fl_pBtn.setAlignment(FlowLayout.TRAILING);
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		JButton btnReturn = new JButton("반납하기");
		pBtn.add(btnReturn);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
	}

}
