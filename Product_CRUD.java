package stored_management;

import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
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

public class Product_CRUD {

	JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				 Product_CRUD window = new Product_CRUD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	int q,i,id,deleteItem;
	
//============================================FUNCTION========================================
public void upDateDB() {
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
	pst = con.prepareStatement("Select * from PRODUCT");
	
	rs = pst.executeQuery();
	ResultSetMetaData stData = rs.getMetaData();
	q = stData.getColumnCount();
	DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
	RecordTable.setRowCount(0);
	
	while(rs.next()) {
		Vector columnData = new Vector();
		
		for(i=1;i<=q;i++)
		{
			columnData.add(rs.getString("PRODUCT_ID"));
			columnData.add(rs.getString("PRODUCT_NAME"));
			columnData.add(rs.getString("PROVIDER_NAME"));
			columnData.add(rs.getString("PRICE"));
			columnData.add(rs.getString("AMOUNT"));
			columnData.add(rs.getString("USER_ID"));
			columnData.add(rs.getString("INPUT_DAY"));
		}
		RecordTable.addRow(columnData);
	  }
	}
	catch(Exception ex){
		JOptionPane.showMessageDialog(null,ex);
	}
	
}
//==========================================END FUNCTION=========================================
	
	public Product_CRUD() {
		initialize();
		Connect();
		Table_load();
	}
	private JTextField txtpname;
	private JTextField txtprovidername;
	private JTextField txtprice;
	private JTextField txtamount;
	private JTextField txtuser;
	private JTextField txtdate;
	private JTable table;
	private JTextField txtpid;
	private JTextField txtcount;
	public void Connect() {//================================================CONNECT=======================================
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
			
		}catch(Exception e) {
			Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null, e);
		}
	}
	
	//==========================LOAD PRODUCT--UPDATE AND SEARCH
	public void Table_load() {
		try {
			pst = con.prepareStatement("select * from PRODUCT");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(SQLException ex){
				ex.printStackTrace();
			}
	}
	
	//=============================END LOAD===========================
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 869, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new LineBorder(new Color(64, 128, 128), 8));
		panel.setBounds(10, 10, 806, 583);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(64, 128, 128), 8));
		panel_1.setBounds(616, 100, 180, 473);
		panel.add(panel_1);
		
		JButton btnupdate = new JButton("UPDATE");//=========================UPDATE===============
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
					CallableStatement cstmt  = con.prepareCall("Call UPDATE_PRODUCT(?,?,?,?,?,?,?)");
					
					 cstmt.setString(1,txtpid.getText());
					 cstmt.setString(2,txtpname.getText());
					 cstmt.setString(3,txtprovidername.getText());
					 cstmt.setString(4,txtprice.getText());
					 cstmt.setString(5,txtamount.getText());
					 cstmt.setString(6,txtuser.getText());
					 cstmt.setString(7,txtdate.getText());
					 //cstmt.setString(7,txtpid.getText());
					 
					
					 cstmt.executeUpdate();
					JOptionPane.showMessageDialog(btnupdate,"Record updated!");
					upDateDB();
					Table_load();
					
					txtpname.setText("");
					txtprovidername.setText("");
					txtamount.setText("");
					txtuser.setText("");
					txtdate.setText("");
					txtpname.requestFocus();
			
				}catch(ClassNotFoundException ex) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,ex);
				}catch(SQLException ex) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,ex);
				}
				
				
			}
		});
		btnupdate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnupdate.setBounds(22, 90, 131, 39);
		panel_1.add(btnupdate);
		
		JButton btnrest = new JButton("RESET");
		btnrest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpid.setText("");
				txtpname.setText("");
				txtprovidername.setText("");
				txtprice.setText("");
				txtamount.setText("");
				txtuser.setText("");
				txtdate.setText("");
				
			}
		});
		btnrest.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnrest.setBounds(22, 152, 131, 39);
		panel_1.add(btnrest);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//============================DELETE==========================//
				DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
				int selectRows = table.getSelectedRow();
				
				try {
					id = Integer.parseInt(RecordTable.getValueAt(selectRows, 0).toString());
					deleteItem = JOptionPane.showConfirmDialog(null,"Confirm if you want to delete item","Warning",JOptionPane.YES_NO_OPTION);
				  if(deleteItem==JOptionPane.YES_OPTION) {
					  Class.forName("com.mysql.cj.jdbc.Driver");
					  con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
					  CallableStatement cstmt  = con.prepareCall("Call DEL_PRODUCT(?)");
					  
					  cstmt.setInt(1, id);
					  cstmt.executeUpdate();
					  JOptionPane.showMessageDialog(btndelete, "Product record deleted!");
					  upDateDB();
					  
					  txtpid.setText("");
					  txtpid.requestFocus();
					  txtpname.setText("");
					  txtprovidername.setText("");
					  txtprice.setText("");
					  txtamount.setText("");
					  txtuser.setText("");
					  txtdate.setText("");
					   
				  }
				}
				
				catch(ClassNotFoundException ex) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,ex);
				}catch(SQLException ex) {
					System.err.println(ex);
				}
				
			}
		});
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btndelete.setBounds(22, 214, 131, 39);
		panel_1.add(btndelete);
		
		JButton btnadd = new JButton("ADD");//================================ADD=======================
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
					CallableStatement cstmt  = con.prepareCall("Call INSERT_PRODUCT(?,?,?,?,?,?)");
					
					//pst.setString(1,txtpid.getText());
					cstmt.setString(1,txtpname.getText());
					cstmt.setString(2,txtprovidername.getText());
					cstmt.setString(3,txtprice.getText());
					cstmt.setString(4,txtamount.getText());
					cstmt.setString(5,txtuser.getText());
					cstmt.setString(6,txtdate.getText());
					
					cstmt.executeUpdate();
					JOptionPane.showMessageDialog(btnadd, "Record added!");
					Table_load();
					upDateDB();
			
				}catch(ClassNotFoundException ex) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,ex);
				}catch(SQLException ex) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
		});
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnadd.setBounds(22, 28, 131, 39);
		panel_1.add(btnadd);
		
		JButton btnsearch = new JButton("SEARCH");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //=========================SEARCH===========================
				String id = txtpid.getText();
				try {
					 CallableStatement cstmt  = con.prepareCall("Call SEARCH_PRODUCT(?)");
					 cstmt.setString(1, id);
					  rs = cstmt.executeQuery();
					
					if(rs.next()==true) {
						//txtpid.setText(rs.getString(1));
						txtpname.setText(rs.getString(1));
						txtprovidername.setText(rs.getString(2));
						txtprice.setText(rs.getString(3));
						txtamount.setText(rs.getString(4));
						txtuser.setText(rs.getString(5));
						txtdate.setText(rs.getString(6));
						
					}else {
						JOptionPane.showMessageDialog(btnsearch, "No record found!");
					}
				} catch (SQLException e1) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,e1);
					
				}
			}
		});
		btnsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnsearch.setBounds(22, 278, 131, 45);
		panel_1.add(btnsearch);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(22, 347, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		txtpid = new JTextField();
		txtpid.setBounds(57, 345, 96, 23);
		panel_1.add(txtpid);
		txtpid.setColumns(10);
		
		JButton btnNewButton = new JButton("TOTAL PRODUCT");//==================================SUM================================//
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/qlkho","root","");
				CallableStatement cstmt  = con.prepareCall("{? = Call SUM_PRODUCT()}");
				cstmt.registerOutParameter(1, Types.DOUBLE);
				rs = cstmt.executeQuery();
				
				if(rs.next()) {
				txtcount.setText(rs.getString(1));
				}
			}
				catch(Exception e1) {
					Logger.getLogger(Product_CRUD.class.getName()).log(Level.SEVERE,null,e1);
				}
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(22, 378, 131, 39);
		panel_1.add(btnNewButton);
		
		txtcount = new JTextField();
		txtcount.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtcount.setBounds(57, 427, 96, 23);
		panel_1.add(txtcount);
		txtcount.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(20, 21, 695, 69);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STORED MANAGEMENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(118, 10, 567, 61);
		panel_2.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(Color.BLACK);
		panel_1_1.setBorder(new LineBorder(new Color(64, 128, 128), 8));
		panel_1_1.setBounds(10, 100, 603, 473);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(23, 20, 133, 25);
		panel_1_1.add(lblNewLabel_1_1);
		
		txtpname = new JTextField();
		txtpname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpname.setColumns(10);
		txtpname.setBounds(166, 23, 320, 25);
		panel_1_1.add(txtpname);
		
		JLabel lblNewLabel_1_2 = new JLabel("Provider Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(23, 55, 143, 25);
		panel_1_1.add(lblNewLabel_1_2);
		
		txtprovidername = new JTextField();
		txtprovidername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtprovidername.setColumns(10);
		txtprovidername.setBounds(166, 58, 320, 25);
		panel_1_1.add(txtprovidername);
		
		JLabel lblNewLabel_1_3 = new JLabel("Price");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(23, 90, 116, 25);
		panel_1_1.add(lblNewLabel_1_3);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtprice.setColumns(10);
		txtprice.setBounds(166, 93, 320, 25);
		panel_1_1.add(txtprice);
		
		JLabel lblNewLabel_1_4 = new JLabel("Amount");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(23, 125, 116, 25);
		panel_1_1.add(lblNewLabel_1_4);
		
		txtamount = new JTextField();
		txtamount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtamount.setColumns(10);
		txtamount.setBounds(166, 128, 320, 25);
		panel_1_1.add(txtamount);
		
		JLabel lblNewLabel_1_5 = new JLabel("User ID");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_5.setBounds(23, 160, 116, 25);
		panel_1_1.add(lblNewLabel_1_5);
		
		txtuser = new JTextField();
		txtuser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtuser.setColumns(10);
		txtuser.setBounds(166, 163, 320, 25);
		panel_1_1.add(txtuser);
		
		JLabel lblNewLabel_1_6 = new JLabel("Receipt");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_6.setBounds(23, 195, 116, 25);
		panel_1_1.add(lblNewLabel_1_6);
		
		txtdate = new JTextField();
		txtdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtdate.setColumns(10);
		txtdate.setBounds(166, 198, 320, 25);
		panel_1_1.add(txtdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
				int selectRows = table.getSelectedRow();
				
				txtpid.setText(RecordTable.getValueAt(selectRows, 1).toString());
				txtpname.setText(RecordTable.getValueAt(selectRows, 2).toString());
				txtprovidername.setText(RecordTable.getValueAt(selectRows, 3).toString());
				txtprice.setText(RecordTable.getValueAt(selectRows, 4).toString());
				txtamount.setText(RecordTable.getValueAt(selectRows, 5).toString());
				txtuser.setText(RecordTable.getValueAt(selectRows, 6).toString());
				txtdate.setText(RecordTable.getValueAt(selectRows, 7).toString());
					
			}
		});
			
			
		scrollPane.setBounds(10, 249, 584, 214);
		panel_1_1.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product ID", "Product Name", "Provider Name", "Price", "Amount", "User ID", "Input Day"
			}
		));
	}
}
