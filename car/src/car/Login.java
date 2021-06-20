package car;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mysql.Operation;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JFrame frmLogin;
	private JTextField user_txt;
	private JPasswordField pass_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setVisible(true);
		frmLogin.getContentPane().setBackground(new Color(51, 51, 51));
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);
		JLabel title = new JLabel("Speed rent car");
		title.setFocusable(false);
		title.setBackground(new Color(0, 206, 209));
		title.setBounds(0, 0, 772, 63);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(51, 204, 153));
		title.setFont(new Font("Segoe Print", Font.BOLD, 35));
		frmLogin.getContentPane().add(title);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(154, 105, 434, 63);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);

		user_txt = new JTextField();
		user_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (user_txt.getText().equals("Username")) {
					user_txt.setText("");
				} else {
					user_txt.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (user_txt.getText().equals("")) {
					user_txt.setText("Username");
				}
			}
		});
		user_txt.setBorder(null);
		user_txt.setForeground(new Color(102, 102, 102));
		user_txt.setOpaque(false);
		user_txt.setHorizontalAlignment(SwingConstants.CENTER);
		user_txt.setText("Username");
		user_txt.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		user_txt.setBackground(new Color(204, 204, 204));
		user_txt.setBounds(0, 0, 349, 63);
		panel.add(user_txt);
		user_txt.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/car/user_name.png")));
		lblNewLabel.setBounds(371, 0, 35, 63);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 204, 204));
		panel_1.setBounds(154, 208, 434, 63);
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		pass_txt = new JPasswordField();
		pass_txt.setFocusTraversalKeysEnabled(false);
		pass_txt.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (pass_txt.getText().equals("Password")) {
					pass_txt.setEchoChar('●');
					pass_txt.setText("");
				} else {
					pass_txt.selectAll();
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (pass_txt.getText().equals("")) {
					pass_txt.setText("Password");
					pass_txt.setEchoChar((char) 0);
				}
			}
		});
		pass_txt.setEchoChar((char) 0);
		pass_txt.setBorder(null);
		pass_txt.setHorizontalAlignment(SwingConstants.CENTER);
		pass_txt.setText("Password");
		pass_txt.setForeground(new Color(102, 102, 102));
		pass_txt.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		pass_txt.setBackground(new Color(204, 204, 204));
		pass_txt.setBounds(0, 0, 348, 63);
		panel_1.add(pass_txt);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/car/user_password.png")));
		lblNewLabel_1.setBounds(377, 0, 30, 63);
		panel_1.add(lblNewLabel_1);

		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {

				@SuppressWarnings("unused")
				Operation operation = new Operation();
				try {
					String user = user_txt.getText();
					String pass = String.valueOf(pass_txt.getPassword());
					if (Operation.isLogin(user, pass)) {
						Main main = new Main();
						frmLogin.dispose();
						main.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
					}
				} catch (Exception ex) {

				}
			}
		});

		login.setForeground(new Color(255, 255, 204));
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		login.setIcon(new ImageIcon(Login.class.getResource("/car/login_btn.png")));
		login.setBorder(null);
		login.setBackground(new Color(102, 102, 102));
		login.setBounds(232, 300, 248, 56);
		frmLogin.getContentPane().add(login);
		frmLogin.setBackground(new Color(51, 51, 51));
		frmLogin.setForeground(new Color(0, 153, 153));
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/car/lock.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 786, 403);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
