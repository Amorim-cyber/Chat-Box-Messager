package panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import back_end.Login;
import mainFrame.MainFrame;

public class NewAccount extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel mainImage;
	private String pathImage = "/images/newAccount/NewAccount.png";
	private boolean created = false;
	private boolean invalid = false;
	private boolean exist = false;
	
	public NewAccount(MainFrame frame, boolean enable) {

		final JPanel thisPanel = this;
		
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JTextField txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = txtUser.getText().length();
				if(length > 12)
					txtUser.setText(txtUser.getText().substring(0,length-1));		
			}
		});
		txtUser.setFont(new Font("Cambria", Font.PLAIN, 25));
		txtUser.setBounds(382, 672, 456, 27);
		add(txtUser);
		txtUser.setColumns(10);
		txtUser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JTextField txtLogin = new JTextField();
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = txtLogin.getText().length();
				if(length > 12)
					txtLogin.setText(txtLogin.getText().substring(0,length-1));		
			}
		});
		txtLogin.setFont(new Font("Cambria", Font.PLAIN, 25));
		txtLogin.setBounds(382, 706, 456, 27);
		add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JTextField txtPassword = new JTextField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int length = txtPassword.getText().length();
				if(length > 12)
					txtPassword.setText(txtPassword.getText().substring(0,length-1));		
			}
		});
		txtPassword.setFont(new Font("Cambria", Font.PLAIN, 25));
		txtPassword.setBounds(382, 740, 456, 27);
		add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(created)
					pathImage = "/images/newAccount/NewAccount-created.png";
				else if(invalid)
					pathImage = "/images/newAccount/NewAccount-invalid.png";
				else if(exist)
					pathImage = "/images/newAccount/NewAccount-exist.png";
				else
					pathImage = "/images/newAccount/NewAccount.png";
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(created) 
					pathImage = "/images/newAccount/NewAccount-created-back-activate.png";
				else if(invalid)
					pathImage = "/images/newAccount/NewAccount-invalid-back-activate.png";
				else if(exist)
					pathImage = "/images/newAccount/NewAccount-exist-back-activate.png";
				else
					pathImage = "/images/newAccount/NewAccount-back-activate.png";
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame,enable));
				
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(666, 810, 150, 93);
		add(btnBack);
		
		JButton btnCreate = new JButton("");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(created) 
					pathImage = "/images/newAccount/NewAccount-created.png";
				else if(invalid)
					pathImage = "/images/newAccount/NewAccount-invalid.png";
				else if(exist)
					pathImage = "/images/newAccount/NewAccount-exist.png";
				else 
					pathImage = "/images/newAccount/NewAccount.png";
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnCreate.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(created) 
					pathImage = "/images/newAccount/NewAccount-created-create-activate.png";
				else if(invalid)
					pathImage = "/images/newAccount/NewAccount-invalid-create-activate.png";
				else if(exist)
					pathImage = "/images/newAccount/NewAccount-exist-create-activate.png";
				else 
					pathImage = "/images/newAccount/NewAccount-create-activate.png";
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login = new Login(txtUser.getText(),txtLogin.getText(),txtPassword.getText());
				if(!login.isValid() || login.exist(true)) {
					if(!login.isValid()) {
						pathImage = "/images/newAccount/NewAccount-invalid.png";
						invalid = true;
						exist = false;
					}else {
						pathImage = "/images/newAccount/NewAccount-exist.png";
						invalid = false;
						exist = true;
					}
					created = false;
				}else {
					login.created(true);
					pathImage = "/images/newAccount/NewAccount-created.png";
					created = true;
					invalid = false;
					exist = false;
				}
				
				txtUser.setText("");
				txtLogin.setText("");
				txtPassword.setText("");
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnCreate.setFocusPainted(false);
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(398, 810, 231, 93);
		add(btnCreate);
		
		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1920, 1080);
		add(mainImage);
		
	}
}
