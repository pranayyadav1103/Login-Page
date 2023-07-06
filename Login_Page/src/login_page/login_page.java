package login_page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class login_page {

	private JFrame frame;
	private JTextField t1;
	private JPasswordField p1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page window = new login_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(214, 243, 234));
		frame.setBounds(100, 100, 685, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN PAGE");
		lblNewLabel.setFont(new Font("Rockwell", Font.BOLD, 22));
		lblNewLabel.setBounds(232, 38, 256, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Roll Number");
		lblNewLabel_1.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		lblNewLabel_1.setBounds(136, 113, 102, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(136, 166, 102, 39);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		t1 = new JTextField();
		t1.setBounds(278, 114, 166, 31);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		p1 = new JPasswordField();
		p1.setBounds(278, 166, 166, 31);
		frame.getContentPane().add(p1);
		
		JButton btn = new JButton("SUBMIT");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n1=t1.getText();
				int nm=Integer.parseInt(n1);
				String pswd=p1.getText();
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","123456");
					String q="Select count(*) from reg where rollno=? and password=?";
					PreparedStatement pd=con.prepareStatement(q);
					pd.setInt(1, nm);
					pd.setString(2, pswd);
					ResultSet rs=pd.executeQuery();
					rs.next();
					int count=rs.getInt(1);
					if(count==1) {
						JOptionPane.showMessageDialog(btn,"Valid Credentials");
					}
					else {
						JOptionPane.showMessageDialog(btn,"Invalid Credentials");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setFont(new Font("Rockwell", Font.BOLD, 17));
		btn.setBounds(198, 238, 128, 31);
		frame.getContentPane().add(btn);
	}

}
