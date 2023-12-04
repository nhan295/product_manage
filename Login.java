package stored_management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

//====================================================================//
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class Login {

	JFrame frmLogin;
	private JTextField txtuserid;
	private JPasswordField txtpass;
	JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		Connect();
	}
	
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
			
		}catch(ClassNotFoundException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,ex);
		}catch(SQLException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,ex);
		}
	}

	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("LOGIN");
		frmLogin.setBounds(100, 100, 681, 465);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128), new Color(64, 128, 128), new Color(64, 128, 128)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 657, 421);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(64, 128, 128));
		panel_1.setBounds(0, 0, 314, 411);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome Back!");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setBounds(48, 99, 210, 50);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("To keep connected with us ");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5.setBounds(58, 159, 210, 77);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("please login with your personal info");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_6.setBounds(26, 210, 278, 63);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(442, 31, 146, 37);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USER ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(339, 96, 70, 28);
		panel.add(lblNewLabel_1);
		
		txtuserid = new JTextField();
		txtuserid.setBounds(349, 134, 286, 28);
		panel.add(txtuserid);
		txtuserid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(339, 186, 114, 28);
		panel.add(lblNewLabel_2);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(349, 226, 286, 28);
		panel.add(txtpass);
		
		
		//==================================================LOGIN=======================//
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user = txtuserid.getText();
				String pass = txtpass.getText();
				
				try {
					   String sql = "Select * from USERS where USER_ID=? AND USER_PASS=?";
						       pst = con.prepareCall(sql);
						     
						       pst.setString(1, user);
						       pst.setString(2, pass);
						       rs = pst.executeQuery();
						       
						       if(rs.next()){
						    	 
						    	   Product_CRUD window = new Product_CRUD();
									window.frame.setVisible(true);
							}else {
								 JOptionPane.showMessageDialog(btnlogin, "Your id or password incorrect");
							}

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,e1);
				}
				
			}
			
			
		});
		btnlogin.setBackground(new Color(64, 128, 128));
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnlogin.setBounds(339, 303, 85, 28);
		panel.add(btnlogin);
		
		JLabel lblNewLabel_3 = new JLabel(" You don't have an account?");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(334, 350, 189, 17);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Sign in");//==================================SHOW SIGNUP WINDOWN========================
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignUp window = new SignUp();
				window.frmSignUp.setVisible(true);

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(533, 344, 85, 28);
		panel.add(btnNewButton_1);
	}
}
