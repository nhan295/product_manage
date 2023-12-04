package stored_management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
//===================================//
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
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
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;



public class SignUp {
	
	JFrame frmSignUp;
	private JTextField txtuid;
	private JTextField txtuname;
	private JPasswordField txtupass;
	private JTextField txtumail;
	
	JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frmSignUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
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
			
		}catch(Exception e) {
			Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null, e);
		}
	}
	
	
	private void initialize() {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("SIGN UP\r\n");
		frmSignUp.setBounds(100, 100, 693, 490);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 669, 433);
		frmSignUp.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 10, 299, 429);
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\Admin\\Pictures\\images.png"));
		lblNewLabel_8.setBounds(29, 65, 235, 276);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel = new JLabel("SIGN UP");
		lblNewLabel.setForeground(new Color(64, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(414, 10, 164, 63);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USER ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(309, 86, 73, 17);
		panel.add(lblNewLabel_1);
		
		txtuid = new JTextField();
		txtuid.setBounds(414, 83, 185, 27);
		panel.add(txtuid);
		txtuid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("USER NAME");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(306, 134, 98, 17);
		panel.add(lblNewLabel_2);
		
		txtuname = new JTextField();
		txtuname.setBounds(414, 131, 185, 27);
		panel.add(txtuname);
		txtuname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PASSWORD");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(309, 186, 78, 17);
		panel.add(lblNewLabel_3);
		
		txtupass = new JPasswordField();
		txtupass.setBounds(414, 183, 185, 27);
		panel.add(txtupass);
		
		JLabel lblNewLabel_4 = new JLabel("EMAIL");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(309, 235, 73, 19);
		panel.add(lblNewLabel_4);
		
		txtumail = new JTextField();
		txtumail.setBounds(414, 233, 185, 27);
		panel.add(txtumail);
		txtumail.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("GENDER");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(309, 289, 73, 13);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("ROLE ID");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(309, 343, 63, 17);
		panel.add(lblNewLabel_6);
		
		JComboBox cmbrole = new JComboBox();
		cmbrole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbrole.setModel(new DefaultComboBoxModel(new String[] {"001", "002", "003"}));
		cmbrole.setBounds(414, 336, 78, 27);
		panel.add(cmbrole);
		
		JComboBox cmbsex = new JComboBox();
		cmbsex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbsex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cmbsex.setBounds(414, 286, 78, 27);
		panel.add(cmbsex);
		
		JButton btnsignup = new JButton("Sign up");//=====================================SIGNUP============================
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
					CallableStatement cstmt  = con.prepareCall("Call INSERT_USER(?,?,?,?,?,?)");
					
					//pst.setString(1,txtpid.getText());
					cstmt.setString(1,txtuid.getText());
					cstmt.setString(2,txtuname.getText());
					cstmt.setString(3,txtupass.getText());
					cstmt.setString(4,txtumail.getText());
					cstmt.setString(5,cmbsex.getSelectedItem().toString());
					cstmt.setString(6,cmbrole.getSelectedItem().toString());
					
					 cstmt.executeUpdate();
					 
					 JOptionPane.showMessageDialog(btnsignup, "Register success!");
					
	
			
				}catch(ClassNotFoundException ex) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,ex);
				}catch(SQLException ex) {
					
					JOptionPane.showMessageDialog(btnsignup, "ID or email existed");
				}
				
				
				
				
				
			}
		});
		btnsignup.setForeground(new Color(255, 255, 255));
		btnsignup.setBackground(new Color(64, 128, 128));
		btnsignup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnsignup.setBounds(309, 385, 85, 32);
		panel.add(btnsignup);
		
		JLabel lblNewLabel_7 = new JLabel("I've an account");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(456, 392, 122, 19);
		panel.add(lblNewLabel_7);
		
		JButton btnloginn = new JButton("Login");
		btnloginn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window = new Login();
				window.frmLogin.setVisible(true);
				
			}
		});
		
		btnloginn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnloginn.setBounds(574, 385, 85, 32);
		panel.add(btnloginn);
		
		
	}
}
