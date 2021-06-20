package car;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import mysql.MySQLConnection;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Main extends JFrame {
	private JPanel panel = new JPanel();
	private JPanel ContentPane;
	private JButton cars = new JButton("Car");
	@SuppressWarnings("rawtypes")
	private JComboBox avaibility = new JComboBox();
	private CardLayout c;
	private JTable table;
	private JButton rent = new JButton("Rent");
	private JButton customer = new JButton("Customer");
	private JButton retour = new JButton("Return");
	private JTextField num;
	private JTextField brand;
	private JTextField modele;
	private JTextField feeperday;
	private JTextField id;
	private JTextField name;
	private JTextField mobile;
	private JTable table_1;
	private JTextArea add = new JTextArea();
	private JTextField custid;
	private JTextField custname;
	private JTextField fee;
	private JComboBox<String> carid = new JComboBox<String>();
	private JDateChooser txtdate;
	private JDateChooser txtdue;
	private JTextField adv;
	private JTextField id_res;
	private JTable table_2;
	private JTextField bill_custid;
	private JTextField bill_custname;
	private JTextField bill_rest;
	private JTextField bill_extra;
	private JTextField bill_num;
	private JTable table_3;
	private JTextField bill_car;
	private JTextField bill_res;
	private JTextField bill_issue;
	private JDateChooser txtdue_1;
	private JTextField bill_total;
	private JTextField bill_adv;
	private JTextField bill_fee;
	private JButton signout;

	private void retour() {
		String insert = "DELETE FROM `rent` WHERE `cust_id`= " + Integer.parseInt(bill_custid.getText());

		executeSQlQuery(insert, "Deleted");
		delete(bill_car.getText());
	}

	private void clear() {
		autoid();
		name.setText("");
		mobile.setText("");
		add.setText("");
	}

	private void clear2() {
		num.setText("");
		brand.setText("");
		modele.setText("");
		feeperday.setText("");

	}

	private void clear4() {
		autoid3();
		bill_res.setText("");
		bill_custname.setText("");
		bill_rest.setText("");
		bill_custid.setText("");
		bill_extra.setText("");
		bill_issue.setText("");
		bill_car.setText("");
		bill_adv.setText("");
		bill_total.setText("");
		bill_fee.setText("");

	}

	private void clear3() {
		autoid2();
		custid.setText("");
		custname.setText("");
		adv.setText("");
		fee.setText("");

	}

	private void delete(String av) {
		try {
			Connection con = MySQLConnection.getConnection();

			PreparedStatement pst1 = con
					.prepareStatement("UPDATE `cars` SET `Availability` = 'YES' where `Car_n°`= ? ");
			pst1.setInt(1, Integer.parseInt(av));
			pst1.executeUpdate();

		} catch (Exception e2) {
		}

	}

	private void avail() {
		try {
			Connection con = MySQLConnection.getConnection();
			String av;
			av = carid.getSelectedItem().toString();
			PreparedStatement pst1 = con.prepareStatement("UPDATE `cars` SET `Availability` = 'NO' where `Car_n°`= ? ");
			pst1.setInt(1, Integer.parseInt(av));
			pst1.executeUpdate();

		} catch (Exception e2) {
		}
	}

	public void LoadCarId() {
		try {
			Connection conn = MySQLConnection.getConnection();

			PreparedStatement pst = conn.prepareStatement("select *from cars where Availability ='YES'");
			ResultSet rs = pst.executeQuery();

			carid.removeAllItems();
			while (rs.next()) {
				carid.addItem(rs.getString(1));
			}
		} catch (Exception ex) {

		}
	}

	private void autoid3() {
		try {
			Connection conn = MySQLConnection.getConnection();

			String query = "Select max(`id`) as max from facture ";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("max");
				int inc = num + 1;

				bill_num.setText(String.valueOf(inc));

			}
		} catch (Exception a) {
			a.printStackTrace();
		}

	}

	private void autoid2() {
		try {
			Connection conn = MySQLConnection.getConnection();

			String query = "Select max(`id`) as max from rent ";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("max");
				int inc = num + 1;

				id_res.setText(String.valueOf(inc));

			}

		}

		catch (Exception a) {
			a.printStackTrace();
		}

	}

	private void autoid() {
		try {
			Connection conn = MySQLConnection.getConnection();

			String query = "Select max(`id`) as max from customers ";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("max");
				int inc = num + 1;

				id.setText(String.valueOf(inc));

			}

		}

		catch (Exception a) {
			a.printStackTrace();
		}

	}

	private void update4() {

		try {
			String mySqlQuery = "SELECT * FROM facture";
			Connection myConn = MySQLConnection.getConnection();
			PreparedStatement ps = myConn.prepareStatement(mySqlQuery);
			ResultSet rs = ps.executeQuery();
			table_3.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
		}

	}

	private void update3() {

		try {
			String mySqlQuery = "SELECT * FROM rent";
			Connection myConn = MySQLConnection.getConnection();
			PreparedStatement ps = myConn.prepareStatement(mySqlQuery);
			ResultSet rs = ps.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
		}

	}

	private void update() {

		try {
			String mySqlQuery = "SELECT * FROM cars";
			Connection myConn = MySQLConnection.getConnection();
			PreparedStatement ps = myConn.prepareStatement(mySqlQuery);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
		}

	}

	private void update2() {

		try {
			String mySqlQuery = "SELECT * FROM customers";
			Connection myConn = MySQLConnection.getConnection();
			PreparedStatement ps = myConn.prepareStatement(mySqlQuery);
			ResultSet rs = ps.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception ex) {
		}

	}

	public void executeSQlQuery(String query, String message) {

		Statement st;
		try {
			Connection con = MySQLConnection.getConnection();
			st = con.createStatement();
			if ((st.executeUpdate(query)) == 1) {

				JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
			} else {
				JOptionPane.showMessageDialog(null, "Data Not " + message);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private long dif() {
		long diff = 0;
		try {

			@SuppressWarnings("unused")
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			Date firstDate = txtdate.getDate();
			Date secondDate = txtdue.getDate();

			long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return diff;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Main() {
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/car/car.png")));

		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 635);
		ContentPane = new JPanel();
		ContentPane.setBackground(new Color(51, 51, 51));
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);

		JPanel PaneMenu = new JPanel();
		PaneMenu.setBorder(null);
		PaneMenu.setBackground(new Color(51, 51, 51));
		PaneMenu.setBounds(0, 10, 208, 579);
		ContentPane.add(PaneMenu);
		PaneMenu.setLayout(null);

		JLabel title = new JLabel("Speed rent car");
		title.setBounds(0, 0, 208, 74);
		PaneMenu.add(title);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(255, 255, 204));
		title.setFont(new Font("Segoe Print", Font.BOLD, 20));
		title.setFocusable(false);
		title.setBackground(new Color(255, 255, 204));
		cars.setIcon(new ImageIcon(Main.class.getResource("/car/car (3).png")));

		cars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				cars.setBackground(new Color(102, 102, 102));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cars.setBackground(new Color(51, 204, 153));
			}

		});

		cars.setBackground(new Color(102, 102, 102));
		cars.setForeground(new Color(255, 255, 204));

		cars.setBorder(null);
		cars.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cars.setBounds(0, 219, 208, 43);
		PaneMenu.add(cars);
		customer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(panel, "2");
				update2();
				autoid();
			}
		});
		customer.setIcon(new ImageIcon(Main.class.getResource("/car/infected-people.png")));

		customer.setForeground(new Color(255, 255, 204));
		customer.setBackground(new Color(102, 102, 102));
		customer.setBorder(null);

		customer.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		customer.setBounds(0, 272, 208, 43);
		PaneMenu.add(customer);
		customer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				customer.setBackground(new Color(102, 102, 102));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				customer.setBackground(new Color(51, 204, 153));
			}
		});
		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				c.show(panel, "3");
				LoadCarId();
				autoid2();
				update3();
				clear3();

			}
		});
		rent.setIcon(new ImageIcon(Main.class.getResource("/car/real.png")));

		rent.setForeground(new Color(255, 255, 204));
		rent.setBackground(new Color(102, 102, 102));
		rent.setBorder(null);
		rent.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		rent.setBounds(0, 325, 208, 43);
		PaneMenu.add(rent);
		rent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				rent.setBackground(new Color(102, 102, 102));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				rent.setBackground(new Color(51, 204, 153));
			}
		});
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(panel, "4");
				update4();
				autoid3();
				clear4();
			}
		});
		retour.setIcon(new ImageIcon(Main.class.getResource("/car/left-arrow.png")));
		retour.setForeground(new Color(255, 255, 204));
		retour.setBackground(new Color(102, 102, 102));
		retour.setBorder(null);
		retour.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		retour.setBounds(0, 378, 208, 43);
		PaneMenu.add(retour);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/car/car (4).png")));
		lblNewLabel_1.setBounds(0, 84, 208, 113);
		PaneMenu.add(lblNewLabel_1);

		signout = new JButton("Sign out");
		signout.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					hide();
					@SuppressWarnings("unused")
					Login lo = new Login();
				}

			}

		});
		signout.setIcon(new ImageIcon(Main.class.getResource("/car/emergency-exit (2).png")));
		signout.setForeground(new Color(255, 255, 204));
		signout.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		signout.setBorder(null);
		signout.setBackground(new Color(102, 102, 102));
		signout.setBounds(0, 431, 208, 43);
		PaneMenu.add(signout);
		signout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				signout.setBackground(new Color(102, 102, 102));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				signout.setBackground(new Color(51, 204, 153));
			}
		});
		retour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				retour.setBackground(new Color(102, 102, 102));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				retour.setBackground(new Color(51, 204, 153));
			}
		});

		c = new CardLayout();

		panel.setBackground(new Color(51, 204, 153));

		panel.setBounds(218, 10, 767, 579);
		ContentPane.add(panel);
		panel.setLayout(c);

		JPanel main_menu = new JPanel();
		main_menu.setBorder(null);

		main_menu.setBackground(new Color(51, 204, 153));
		panel.add(main_menu, "0");
		main_menu.setLayout(null);

		JLabel lblNewLabel_3_5_1 = new JLabel("Cars number");
		lblNewLabel_3_5_1.setForeground(Color.BLACK);
		lblNewLabel_3_5_1.setBackground(Color.BLACK);
		lblNewLabel_3_5_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_5_1.setBounds(10, 43, 133, 28);
		main_menu.add(lblNewLabel_3_5_1);

		JLabel lblNewLabel_3_5_2 = new JLabel("Reservations number");
		lblNewLabel_3_5_2.setForeground(Color.BLACK);
		lblNewLabel_3_5_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_5_2.setBounds(10, 116, 206, 28);
		main_menu.add(lblNewLabel_3_5_2);

		final JLabel cars_num = new JLabel("");
		cars_num.setForeground(Color.BLACK);
		cars_num.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cars_num.setBounds(198, 43, 126, 28);
		main_menu.add(cars_num);

		JLabel res_num = new JLabel("");
		res_num.setForeground(Color.BLACK);
		res_num.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		res_num.setBounds(264, 116, 126, 28);
		main_menu.add(res_num);

		JLabel cars_num_1 = new JLabel("");
		cars_num_1.setIcon(new ImageIcon(Main.class.getResource("/car/pexels-artem-beliaikin-2691478 (1).jpg")));
		cars_num_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		cars_num_1.setBounds(0, 0, 767, 579);
		main_menu.add(cars_num_1);
		try {
			Connection con = MySQLConnection.getConnection();
			PreparedStatement st = con.prepareStatement("select count(*) from cars as nb");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cars_num.setText(String.valueOf(rs.getInt(1)));

			}
		} catch (Exception e) {
		}
		try {
			Connection con = MySQLConnection.getConnection();
			PreparedStatement st = con.prepareStatement("select count(*) from rent as nb");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				res_num.setText(String.valueOf(rs.getInt(1)));

			}
		} catch (Exception e) {
		}
		JPanel car_panel = new JPanel();
		car_panel.setAutoscrolls(true);
		car_panel.setBackground(new Color(51, 204, 153));
		panel.add(car_panel, "1");
		car_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(0, 339, 767, 240);
		car_panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				num.setText(model.getValueAt(i, 0).toString());
				brand.setText(model.getValueAt(i, 1).toString());
				modele.setText(model.getValueAt(i, 2).toString());
				feeperday.setText(model.getValueAt(i, 3).toString());
				String av = model.getValueAt(i, 4).toString();
				switch (av) {
				case "YES":
					avaibility.setSelectedIndex(0);
					break;

				default:
					break;
				}
			}
		});
		scrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(model);

		num = new JTextField();
		num.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		num.setBounds(193, 70, 126, 28);
		car_panel.add(num);
		num.setColumns(10);

		brand = new JTextField();
		brand.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		brand.setBounds(193, 120, 126, 28);
		car_panel.add(brand);
		brand.setColumns(10);

		modele = new JTextField();
		modele.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		modele.setBounds(193, 170, 126, 28);
		car_panel.add(modele);
		modele.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("N\u00B0car");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(42, 67, 96, 16);
		car_panel.add(lblNewLabel_3);

		feeperday = new JTextField();
		feeperday.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		feeperday.setBounds(193, 220, 126, 28);
		car_panel.add(feeperday);
		feeperday.setColumns(10);
		avaibility.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		avaibility.setBorder(null);

		avaibility.setName("");
		avaibility.setModel(new DefaultComboBoxModel(new String[] { "YES" }));
		avaibility.setToolTipText("");
		avaibility.setBounds(193, 270, 96, 21);
		car_panel.add(avaibility);

		JLabel lblNewLabel_3_1 = new JLabel("Brand");
		lblNewLabel_3_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(42, 117, 96, 16);
		car_panel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Model\r\n\r\n");
		lblNewLabel_3_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_2.setBounds(42, 167, 96, 16);
		car_panel.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Fee per day");
		lblNewLabel_3_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_3.setBounds(42, 218, 115, 28);
		car_panel.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("Avaibility ");
		lblNewLabel_3_4.setBounds(42, 262, 96, 28);
		car_panel.add(lblNewLabel_3_4);
		lblNewLabel_3_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Main.class.getResource("/car/edit (2).png")));
		btnNewButton_1.setBackground(new Color(51, 204, 153));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					String query = "UPDATE `cars` SET `Car_n°`=" + Integer.parseInt(num.getText()) + ",`Brand`='"
							+ brand.getText() + "',`Model`='" + modele.getText() + "',`fee_per_day`="
							+ Integer.parseInt(feeperday.getText()) + " WHERE `car_n°` = "
							+ Integer.parseInt(num.getText());
					executeSQlQuery(query, "Updated");
					update();
					clear2();
				} catch (Exception e1) {

				}

			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(609, 117, 88, 60);
		car_panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Main.class.getResource("/car/delete-file.png")));
		btnNewButton_2.setBackground(new Color(51, 204, 153));
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the record",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						String insert = "DELETE FROM `cars` WHERE `Car_n°`= " + Integer.parseInt(num.getText());

						executeSQlQuery(insert, "Deleted");
						update();
						clear2();
					}
				} catch (Exception e1) {
				}
			}
		});
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBounds(486, 220, 80, 49);
		car_panel.add(btnNewButton_2);
		setLocationRelativeTo(null);

		cars.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(panel, "1");
				update();
			}
		});

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/car/done.png")));
		btnNewButton.setBackground(new Color(51, 204, 153));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = MySQLConnection.getConnection();
					PreparedStatement st = con.prepareStatement("insert into cars values(?,?,?,?,?)");
					st.setInt(1, Integer.parseInt(num.getText()));
					st.setString(2, brand.getText());
					st.setString(3, modele.getText());
					st.setInt(4, Integer.parseInt(feeperday.getText()));
					String av;
					av = avaibility.getSelectedItem().toString();
					st.setString(5, av);
					st.executeUpdate();
					update();
					clear2();
				} catch (Exception ex) {
				}

			}

		});

		btnNewButton.setBorder(null);
		btnNewButton.setBounds(483, 102, 96, 81);
		car_panel.add(btnNewButton);

		JButton btnNewButton_5_2 = new JButton("");
		btnNewButton_5_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				clear2();
			}
		});
		btnNewButton_5_2.setIcon(new ImageIcon(Main.class.getResource("/car/reload (3).png")));
		btnNewButton_5_2.setBorder(null);
		btnNewButton_5_2.setBackground(new Color(51, 204, 153));
		btnNewButton_5_2.setBounds(626, 209, 65, 60);
		car_panel.add(btnNewButton_5_2);

		JPanel cust_panel = new JPanel();
		cust_panel.setLayout(null);
		cust_panel.setBackground(new Color(51, 204, 153));
		cust_panel.setAutoscrolls(true);
		panel.add(cust_panel, "2");

		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(0, 339, 767, 240);
		cust_panel.add(scrollPane_1);

		table_1 = new JTable();

		scrollPane_1.setViewportView(table_1);
		DefaultTableModel mod = (DefaultTableModel) table_1.getModel();
		table_1.setModel(mod);

		id = new JTextField();
		id.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		id.setColumns(10);
		id.setBounds(193, 63, 176, 40);
		cust_panel.add(id);

		name = new JTextField();
		name.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		name.setColumns(10);
		name.setBounds(193, 113, 176, 40);
		cust_panel.add(name);

		mobile = new JTextField();
		mobile.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		mobile.setColumns(10);
		mobile.setBounds(193, 163, 176, 43);
		cust_panel.add(mobile);

		JLabel lblNewLabel_3_5 = new JLabel("Customer ID");
		lblNewLabel_3_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_5.setBounds(42, 67, 126, 16);
		cust_panel.add(lblNewLabel_3_5);

		JLabel lblNewLabel_3_1_1 = new JLabel("Name");
		lblNewLabel_3_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_1_1.setBounds(42, 117, 96, 16);
		cust_panel.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_2_1 = new JLabel("Mobile");
		lblNewLabel_3_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_2_1.setBounds(42, 167, 96, 16);
		cust_panel.add(lblNewLabel_3_2_1);

		JLabel lblNewLabel_3_3_1 = new JLabel("Address");
		lblNewLabel_3_3_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_3_1.setBounds(42, 216, 115, 19);
		cust_panel.add(lblNewLabel_3_3_1);

		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table_1.getModel();
				table_1.getSelectedRow();
				try {

					String query = "UPDATE `customers` SET `id`=" + Integer.parseInt(id.getText()) + ",`name`='"
							+ name.getText() + "',`mobile`=" + Integer.parseInt(mobile.getText()) + ",`address`='"
							+ add.getText() + "' WHERE `id` = " + Integer.parseInt(id.getText());
					executeSQlQuery(query, "Updated");
					clear();
					autoid();
					update2();

				} catch (Exception e1) {

				}
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(Main.class.getResource("/car/edit (2).png")));
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(51, 204, 153));
		btnNewButton_1_1.setBounds(628, 85, 81, 58);
		cust_panel.add(btnNewButton_1_1);

		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the record",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						String insert = "DELETE FROM `customers` WHERE `id`= " + Integer.parseInt(id.getText());

						executeSQlQuery(insert, "Deleted");
						clear();
						autoid();
						update2();

					}
				} catch (Exception e1) {
				}

			}
		});
		btnNewButton_2_1.setIcon(new ImageIcon(Main.class.getResource("/car/user (5).png")));
		btnNewButton_2_1.setBorder(null);
		btnNewButton_2_1.setBackground(new Color(51, 204, 153));
		btnNewButton_2_1.setBounds(496, 197, 70, 58);
		cust_panel.add(btnNewButton_2_1);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = MySQLConnection.getConnection();
					PreparedStatement st = con.prepareStatement("insert into customers values(?,?,?,?)");
					st.setString(1, id.getText());
					st.setString(2, name.getText());
					st.setInt(3, Integer.parseInt(mobile.getText()));
					st.setString(4, add.getText());
					st.executeUpdate();
					clear();
					autoid();
					update2();
				} catch (Exception ex) {
				}

			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Main.class.getResource("/car/user (6).png")));
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(new Color(51, 204, 153));
		btnNewButton_3.setBounds(496, 85, 70, 58);
		cust_panel.add(btnNewButton_3);

		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(193, 216, 175, 73);
		cust_panel.add(jScrollPane1);
		add.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

		add.setRows(5);
		add.setColumns(20);
		jScrollPane1.setViewportView(add);

		JPanel rental = new JPanel();
		rental.setLayout(null);
		rental.setBackground(new Color(51, 204, 153));
		rental.setAutoscrolls(true);
		panel.add(rental, "3");

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(null);
		scrollPane_2.setBounds(0, 366, 767, 213);
		rental.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table_1.getSelectedRow();
				LoadCarId();
				DefaultTableModel modd = (DefaultTableModel) table_2.getModel();
				i = table_2.getSelectedRow();
				id_res.setText(modd.getValueAt(i, 0).toString());
				custid.setText(modd.getValueAt(i, 2).toString());
				custname.setText(modd.getValueAt(i, 3).toString());
				fee.setText(modd.getValueAt(i, 6).toString());
				adv.setText(modd.getValueAt(i, 7).toString());

				String av = modd.getValueAt(i, 1).toString();
				carid.addItem(av);
				for (int j = 0; j < carid.getItemCount(); j++) {
					if (av == carid.getItemAt(j).toString()) {
						carid.setSelectedIndex(j);
					}
				}

			}
		});

		custid = new JTextField();
		custid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						String cust_id = custid.getText();

						Connection con = MySQLConnection.getConnection();

						PreparedStatement pst = con.prepareStatement("select * from customers where id = ? ");
						pst.setString(1, cust_id);
						ResultSet rs = pst.executeQuery();

						if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "Customer No not Found");
						} else {
							String productname = rs.getString("name");

							custname.setText(productname.trim());

						}

					} catch (Exception ex) {
					}
				}
			}
		});
		custid.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		custid.setColumns(10);
		custid.setBounds(221, 76, 169, 28);
		rental.add(custid);

		custname = new JTextField();
		custname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		custname.setColumns(10);
		custname.setBounds(221, 114, 169, 26);
		rental.add(custname);

		JLabel lblNewLabel_3_6 = new JLabel("N\u00B0car");
		lblNewLabel_3_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_6.setBounds(42, 29, 131, 26);
		rental.add(lblNewLabel_3_6);

		JLabel lblNewLabel_3_1_2 = new JLabel("Customer ID");
		lblNewLabel_3_1_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_1_2.setBounds(42, 74, 131, 28);
		rental.add(lblNewLabel_3_1_2);

		JLabel lblNewLabel_3_2_2 = new JLabel("Customer Name");
		lblNewLabel_3_2_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_2_2.setBounds(42, 112, 169, 28);
		rental.add(lblNewLabel_3_2_2);

		JLabel lblNewLabel_3_3_2 = new JLabel("Issue Date");
		lblNewLabel_3_3_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_3_2.setBounds(42, 160, 131, 28);
		rental.add(lblNewLabel_3_3_2);

		JLabel lblNewLabel_3_4_1 = new JLabel("Due Date");
		lblNewLabel_3_4_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1.setBounds(42, 198, 146, 28);
		rental.add(lblNewLabel_3_4_1);
		txtdate = new JDateChooser();

		carid.setBounds(221, 32, 169, 28);
		rental.add(carid);

		JLabel lblNewLabel_3_4_1_1 = new JLabel("Rental Fee");
		lblNewLabel_3_4_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1.setBounds(42, 247, 146, 28);
		rental.add(lblNewLabel_3_4_1_1);

		fee = new JTextField();
		fee.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		fee.setColumns(10);
		fee.setBounds(221, 249, 169, 28);
		rental.add(fee);

		txtdate.setBounds(221, 160, 169, 28);
		rental.add(txtdate);

		txtdue = new JDateChooser();
		txtdue.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					if (carid.getItemCount() != 0) {
						Connection con = MySQLConnection.getConnection();

						PreparedStatement pst = con
								.prepareStatement("select `fee_per_day` from `cars` where `Car_n°` = ? ");
						String av;
						av = carid.getSelectedItem().toString();
						pst.setInt(1, Integer.parseInt(av));
						ResultSet rs = pst.executeQuery();
						while (rs.next()) {
							long nb = dif() * rs.getInt(1);
							fee.setText(String.valueOf(nb));
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		txtdue.setBounds(221, 198, 169, 28);
		rental.add(txtdue);

		JLabel lblNewLabel_3_4_1_1_1 = new JLabel("Advance");
		lblNewLabel_3_4_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1_1.setBounds(42, 288, 146, 28);
		rental.add(lblNewLabel_3_4_1_1_1);

		adv = new JTextField();
		adv.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		adv.setColumns(10);
		adv.setBounds(221, 287, 169, 28);
		rental.add(adv);

		JLabel lblNewLabel_3_6_1 = new JLabel("Reservation number");
		lblNewLabel_3_6_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_6_1.setBounds(458, 47, 185, 26);
		rental.add(lblNewLabel_3_6_1);

		id_res = new JTextField();
		id_res.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		id_res.setColumns(10);
		id_res.setBounds(653, 48, 77, 28);
		rental.add(id_res);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(Main.class.getResource("/car/done.png")));
		btnNewButton_4.setBackground(new Color(51, 204, 153));
		btnNewButton_4.setBorder(null);
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = MySQLConnection.getConnection();
					PreparedStatement st = con.prepareStatement("insert into rent values(?,?,?,?,?,?,?,?)");
					st.setInt(1, Integer.parseInt(id_res.getText()));
					String av;
					av = carid.getSelectedItem().toString();
					st.setInt(2, Integer.parseInt(av));
					st.setInt(3, Integer.parseInt(custid.getText()));
					st.setString(4, custname.getText());
					st.setString(5, ((JTextField) txtdate.getDateEditor().getUiComponent()).getText());
					st.setString(6, ((JTextField) txtdue.getDateEditor().getUiComponent()).getText());
					st.setInt(7, Integer.parseInt(fee.getText()));
					if (Integer.parseInt(adv.getText()) >= Integer.parseInt(fee.getText()) * 0.4
							&& Integer.parseInt(adv.getText()) <= Integer.parseInt(fee.getText())) {
						st.setInt(8, Integer.parseInt(adv.getText()));
					} else {
						JOptionPane.showMessageDialog(null, "Advance must be +40%");
					}

					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Sucsessfully Saved");
					autoid2();
					avail();
					update3();
					clear3();
					LoadCarId();

				} catch (Exception ex) {
				}

			}
		});
		btnNewButton_4.setBounds(484, 139, 89, 50);
		rental.add(btnNewButton_4);

		JButton btnNewButton_4_1 = new JButton("");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				table_2.getModel();
				table_2.getSelectedRow();
				try {
					String av;
					av = carid.getSelectedItem().toString();

					String query = "UPDATE `rent` SET `car_id`=" + Integer.parseInt(av) + ",`cust_id`="
							+ Integer.parseInt(custid.getText()) + ",`cust_name`='" + custname.getText()
							+ "',`issue_date`='" + ((JTextField) txtdate.getDateEditor().getUiComponent()).getText()
							+ "',`return_date`='" + ((JTextField) txtdue.getDateEditor().getUiComponent()).getText()
							+ "',`fee`=" + Integer.parseInt(fee.getText()) + ",`advance`="
							+ Integer.parseInt(adv.getText()) + " where id=" + Integer.parseInt(id_res.getText());
					if (Integer.parseInt(adv.getText()) >= Integer.parseInt(fee.getText()) * 0.4
							&& Integer.parseInt(adv.getText()) <= Integer.parseInt(fee.getText())) {
						executeSQlQuery(query, "Updated");
					} else {
						JOptionPane.showMessageDialog(null, "Advance must be +40%");
					}
					LoadCarId();
					update3();
					clear3();
					autoid2();
				}

				catch (Exception e1) {

				}
			}
		});
		btnNewButton_4_1.setIcon(new ImageIcon(Main.class.getResource("/car/edit (2).png")));
		btnNewButton_4_1.setBackground(new Color(51, 204, 153));
		btnNewButton_4_1.setBorder(null);
		btnNewButton_4_1.setBounds(616, 139, 89, 50);
		rental.add(btnNewButton_4_1);

		JButton btnNewButton_4_2 = new JButton("");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the record",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						String insert = "DELETE FROM `rent` WHERE `id`= " + Integer.parseInt(id_res.getText());

						executeSQlQuery(insert, "Deleted");
						String av;
						av = carid.getSelectedItem().toString();
						delete(av);
						LoadCarId();
						update3();
						clear3();
						autoid2();
					}
				} catch (Exception e1) {
				}
			}
		});
		btnNewButton_4_2.setBorder(null);
		btnNewButton_4_2.setForeground(new Color(0, 0, 0));
		btnNewButton_4_2.setBackground(new Color(51, 204, 153));
		btnNewButton_4_2.setIcon(new ImageIcon(Main.class.getResource("/car/delete-file.png")));
		btnNewButton_4_2.setBounds(484, 255, 89, 61);
		rental.add(btnNewButton_4_2);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update3();
				clear3();
				LoadCarId();
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(Main.class.getResource("/car/reload (3).png")));
		btnNewButton_5.setBackground(new Color(51, 204, 153));
		btnNewButton_5.setBorder(null);
		btnNewButton_5.setBounds(616, 255, 89, 61);
		rental.add(btnNewButton_5);

		JPanel ret = new JPanel();
		ret.setLayout(null);
		ret.setBackground(new Color(51, 204, 153));
		ret.setAutoscrolls(true);
		panel.add(ret, "4");

		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setViewportBorder(null);
		scrollPane_2_1.setBounds(0, 428, 767, 151);
		ret.add(scrollPane_2_1);

		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel mod = (DefaultTableModel) table_3.getModel();
				int i = table_3.getSelectedRow();
				bill_num.setText(mod.getValueAt(i, 0).toString());
				bill_car.setText(mod.getValueAt(i, 1).toString());
				bill_custid.setText(mod.getValueAt(i, 2).toString());
				bill_custname.setText(mod.getValueAt(i, 3).toString());
				bill_issue.setText(mod.getValueAt(i, 4).toString());
				bill_fee.setText(mod.getValueAt(i, 6).toString());
				bill_adv.setText(mod.getValueAt(i, 7).toString());
				bill_rest.setText(mod.getValueAt(i, 8).toString());
				bill_extra.setText(mod.getValueAt(i, 9).toString());
				bill_total.setText(mod.getValueAt(i, 10).toString());

			}
		});
		scrollPane_2_1.setViewportView(table_3);

		bill_custid = new JTextField();
		bill_custid.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_custid.setColumns(10);
		bill_custid.setBounds(221, 110, 169, 28);
		ret.add(bill_custid);

		bill_custname = new JTextField();
		bill_custname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_custname.setColumns(10);
		bill_custname.setBounds(221, 150, 169, 26);
		ret.add(bill_custname);

		JLabel lblNewLabel_3_6_2 = new JLabel("N\u00B0car");
		lblNewLabel_3_6_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_6_2.setBounds(42, 78, 131, 26);
		ret.add(lblNewLabel_3_6_2);

		JLabel lblNewLabel_3_1_2_1 = new JLabel("Customer ID");
		lblNewLabel_3_1_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_1_2_1.setBounds(42, 114, 131, 28);
		ret.add(lblNewLabel_3_1_2_1);

		JLabel lblNewLabel_3_2_2_1 = new JLabel("Customer Name");
		lblNewLabel_3_2_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_2_2_1.setBounds(42, 151, 169, 28);
		ret.add(lblNewLabel_3_2_2_1);

		JLabel lblNewLabel_3_3_2_1 = new JLabel("Issue Date");
		lblNewLabel_3_3_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_3_2_1.setBounds(42, 190, 131, 28);
		ret.add(lblNewLabel_3_3_2_1);

		JLabel lblNewLabel_3_4_1_2 = new JLabel("Return Date");
		lblNewLabel_3_4_1_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_2.setBounds(42, 228, 146, 28);
		ret.add(lblNewLabel_3_4_1_2);

		JLabel lblNewLabel_3_4_1_1_2 = new JLabel("Rest to pay");
		lblNewLabel_3_4_1_1_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1_2.setBounds(42, 348, 146, 28);
		ret.add(lblNewLabel_3_4_1_1_2);

		bill_rest = new JTextField();
		bill_rest.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_rest.setColumns(10);
		bill_rest.setBounds(221, 350, 169, 28);
		ret.add(bill_rest);

		txtdue_1 = new JDateChooser();
		txtdue_1.setBounds(221, 230, 169, 28);
		ret.add(txtdue_1);

		JLabel lblNewLabel_3_4_1_1_1_1 = new JLabel("Extra fees");
		lblNewLabel_3_4_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1_1_1.setBounds(42, 388, 146, 28);
		ret.add(lblNewLabel_3_4_1_1_1_1);

		bill_extra = new JTextField();
		bill_extra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int b = Integer.parseInt(bill_fee.getText()) + Integer.parseInt(bill_extra.getText());
					bill_total.setText(String.valueOf(b));

				}
			}
		});
		bill_extra.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_extra.setColumns(10);
		bill_extra.setBounds(221, 390, 169, 28);
		ret.add(bill_extra);

		JLabel lblNewLabel_3_6_1_1 = new JLabel("Bill number");
		lblNewLabel_3_6_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_6_1_1.setBounds(544, 30, 112, 30);
		ret.add(lblNewLabel_3_6_1_1);

		bill_num = new JTextField();
		bill_num.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_num.setColumns(10);
		bill_num.setBounds(655, 33, 77, 28);
		ret.add(bill_num);

		JLabel lblNewLabel_3_6_2_1 = new JLabel("Reservation ID");
		lblNewLabel_3_6_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_6_2_1.setBounds(42, 32, 146, 26);
		ret.add(lblNewLabel_3_6_2_1);

		bill_car = new JTextField();
		bill_car.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_car.setColumns(10);
		bill_car.setBounds(221, 70, 169, 28);
		ret.add(bill_car);

		bill_res = new JTextField();
		bill_res.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						String res_id = bill_res.getText();
						Connection con = MySQLConnection.getConnection();
						PreparedStatement pst = con.prepareStatement("select * from rent where id = ? ");
						pst.setString(1, res_id);
						ResultSet rs = pst.executeQuery();

						if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "Reservation No not Found");
						} else {
							bill_car.setText(rs.getString("car_id"));
							bill_custid.setText(rs.getString("cust_id"));
							bill_custname.setText(rs.getString("cust_name"));
							bill_issue.setText(rs.getString("issue_date"));
							bill_adv.setText(rs.getString("advance"));
							bill_fee.setText(rs.getString("fee"));

							int nb = Integer.parseInt(rs.getString("fee")) - Integer.parseInt(rs.getString("advance"));
							bill_rest.setText(String.valueOf(nb));

						}

					} catch (Exception ex) {
					}
				}
			}
		});
		bill_res.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_res.setColumns(10);
		bill_res.setBounds(221, 30, 169, 28);
		ret.add(bill_res);

		bill_issue = new JTextField();
		bill_issue.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_issue.setColumns(10);
		bill_issue.setBounds(221, 190, 169, 26);
		ret.add(bill_issue);

		JButton btnNewButton_4_3 = new JButton("");
		btnNewButton_4_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = MySQLConnection.getConnection();
					PreparedStatement st = con.prepareStatement("insert into facture values(?,?,?,?,?,?,?,?,?,?,?)");
					st.setInt(1, Integer.parseInt(bill_num.getText()));
					st.setInt(2, Integer.parseInt(bill_car.getText()));
					st.setInt(3, Integer.parseInt(bill_custid.getText()));
					st.setString(4, bill_custname.getText());
					st.setString(5, bill_issue.getText());
					st.setString(6, ((JTextField) txtdue_1.getDateEditor().getUiComponent()).getText());
					st.setInt(7, Integer.parseInt(bill_fee.getText()));
					st.setInt(8, Integer.parseInt(bill_adv.getText()));
					st.setInt(9, Integer.parseInt(bill_rest.getText()));
					st.setInt(10, Integer.parseInt(bill_extra.getText()));
					st.setInt(11, Integer.parseInt(bill_total.getText()));
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Sucsessfully Saved");
					delete(bill_car.getText());
					autoid3();
					update4();
					clear4();
					retour();

				} catch (Exception ex) {
				}
			}
		});
		btnNewButton_4_3.setIcon(new ImageIcon(Main.class.getResource("/car/done.png")));
		btnNewButton_4_3.setBorder(null);
		btnNewButton_4_3.setBackground(new Color(51, 204, 153));
		btnNewButton_4_3.setBounds(475, 122, 89, 50);
		ret.add(btnNewButton_4_3);

		JButton btnNewButton_4_1_1 = new JButton("");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE `facture` SET `id`=" + Integer.parseInt(bill_num.getText()) + ",`car_id`="
							+ Integer.parseInt(bill_car.getText()) + ",`cust_id`="
							+ Integer.parseInt(bill_custid.getText()) + ",`cust_name`='" + bill_custname.getText()
							+ "',`issue`='" + bill_issue.getText() + "',`return_date`='"
							+ ((JTextField) txtdue_1.getDateEditor().getUiComponent()).getText() + "',`rental_fee`="
							+ Integer.parseInt(bill_fee.getText()) + ",`advance`="
							+ Integer.parseInt(bill_adv.getText()) + ",`rest_to_pay`="
							+ Integer.parseInt(bill_rest.getText()) + ",`extra_fees`= "
							+ Integer.parseInt(bill_extra.getText()) + ",`total`="
							+ Integer.parseInt(bill_total.getText()) + " WHERE `id` ="
							+ Integer.parseInt(bill_num.getText());
					executeSQlQuery(query, "Updated");
					update4();
					clear4();
				} catch (Exception e2) {

				}
			}
		});
		btnNewButton_4_1_1.setIcon(new ImageIcon(Main.class.getResource("/car/edit (2).png")));
		btnNewButton_4_1_1.setBorder(null);
		btnNewButton_4_1_1.setBackground(new Color(51, 204, 153));
		btnNewButton_4_1_1.setBounds(632, 122, 89, 50);
		ret.add(btnNewButton_4_1_1);

		JButton btnNewButton_4_2_1 = new JButton("");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the record",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						String insert = "DELETE FROM `facture` WHERE `id`= " + Integer.parseInt(bill_num.getText());

						executeSQlQuery(insert, "Deleted");
						update4();
						clear4();
					}
				}

				catch (Exception ex) {
				}
			}
		});
		btnNewButton_4_2_1.setIcon(new ImageIcon(Main.class.getResource("/car/delete-file.png")));
		btnNewButton_4_2_1.setForeground(Color.BLACK);
		btnNewButton_4_2_1.setBorder(null);
		btnNewButton_4_2_1.setBackground(new Color(51, 204, 153));
		btnNewButton_4_2_1.setBounds(475, 228, 89, 61);
		ret.add(btnNewButton_4_2_1);

		bill_total = new JTextField();
		bill_total.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_total.setColumns(10);
		bill_total.setBounds(699, 390, 68, 28);
		ret.add(bill_total);

		JLabel lblNewLabel_3_4_1_1_1_1_1 = new JLabel("Total");
		lblNewLabel_3_4_1_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1_1_1_1.setBounds(632, 390, 50, 28);
		ret.add(lblNewLabel_3_4_1_1_1_1_1);

		bill_adv = new JTextField();
		bill_adv.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_adv.setColumns(10);
		bill_adv.setBounds(221, 310, 169, 26);
		ret.add(bill_adv);

		JLabel lblNewLabel_3_4_1_1_2_1 = new JLabel("Advance");
		lblNewLabel_3_4_1_1_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1_2_1.setBounds(42, 310, 146, 28);
		ret.add(lblNewLabel_3_4_1_1_2_1);

		JLabel lblNewLabel_3_4_1_1_2_1_1 = new JLabel("Rental fee");
		lblNewLabel_3_4_1_1_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_3_4_1_1_2_1_1.setBounds(42, 272, 146, 28);
		ret.add(lblNewLabel_3_4_1_1_2_1_1);

		bill_fee = new JTextField();
		bill_fee.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bill_fee.setColumns(10);
		bill_fee.setBounds(221, 270, 169, 26);
		ret.add(bill_fee);

		JButton btnNewButton_5_1 = new JButton("");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update4();
				clear4();
			}
		});
		btnNewButton_5_1.setIcon(new ImageIcon(Main.class.getResource("/car/reload (3).png")));
		btnNewButton_5_1.setBorder(null);
		btnNewButton_5_1.setBackground(new Color(51, 204, 153));
		btnNewButton_5_1.setBounds(650, 228, 71, 61);
		ret.add(btnNewButton_5_1);

		JButton btnNewButton_5_3 = new JButton("");
		btnNewButton_5_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update2();
				clear();
			}
		});
		btnNewButton_5_3.setIcon(new ImageIcon(Main.class.getResource("/car/reload (3).png")));
		btnNewButton_5_3.setBorder(null);
		btnNewButton_5_3.setBackground(new Color(51, 204, 153));
		btnNewButton_5_3.setBounds(626, 197, 71, 61);
		cust_panel.add(btnNewButton_5_3);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel mod = (DefaultTableModel) table_1.getModel();
				int i = table_1.getSelectedRow();
				id.setText(mod.getValueAt(i, 0).toString());
				name.setText(mod.getValueAt(i, 1).toString());
				mobile.setText(mod.getValueAt(i, 2).toString());
				add.setText(mod.getValueAt(i, 3).toString());

			}
		});

	}
}
