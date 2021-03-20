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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back_end.Login;
import mainFrame.MainFrame;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel mainImage;
	private String pathImage;
	private boolean invalid = false;
	public Menu(MainFrame frame,boolean enable) {
		
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		final JPanel thisPanel = this;
		
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
		txtLogin.setBounds(375, 737, 456, 27);
		add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char[] chares = txtPassword.getPassword();
				String password = "";
				
				for(int i=0; i<chares.length;i++)
					password+=chares[i];
				
				int length = password.length();
				if(length > 12) {
					txtPassword.setText(password.substring(0,length-1));
				}
							
			}
		});
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtPassword.setBounds(375, 783, 453, 27);
		add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JButton btnGo = new JButton("");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid.png";
					else
						pathImage = "/images/menu/Menu.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable.png";
					else
						pathImage = "/images/menu/Menu-disable.png";
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnGo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-go-activate.png";
					else
						pathImage = "/images/menu/Menu-go-activate.png";
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable-go-activate.png";
					else
						pathImage = "/images/menu/Menu-disable-go-activate.png";
					
				}	
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] chares = txtPassword.getPassword();
				String password = "";
				
				for(int i=0; i<chares.length;i++)
					password+=chares[i];
				
				Login login = new Login(txtLogin.getText(),password);
				
				if(login.exist(false)) {
					setVisible(false);
					frame.returnJMenuBar().setVisible(false);
					frame.getContentPane().remove(thisPanel);
					frame.setCurrentPane(new Chat(frame,login,enable));
					frame.getContentPane().add(frame.getCurrentPane());
				}else {
					if(enable) 
						pathImage = "/images/menu/Menu-invalid.png";
					else
						pathImage = "/images/menu/Menu-invalid-disable.png";
					mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
					txtLogin.setText("");
					txtPassword.setText("");
					invalid=true;
				}
				
			}
		});
		btnGo.setBounds(550, 834, 93, 92);
		btnGo.setFocusPainted(false);
		btnGo.setContentAreaFilled(false);
		btnGo.setBorderPainted(false);
		add(btnGo);
		
		JButton btnClose = new JButton("");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid.png";
					else
						pathImage = "/images/menu/Menu.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable.png";
					else
						pathImage = "/images/menu/Menu-disable.png";
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnClose.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-close-activate.png";
					else
						pathImage = "/images/menu/Menu-close-activate.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable-close-activate.png";
					else
						pathImage = "/images/menu/Menu-disable-close-activate.png";
					
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setFocusPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setBounds(680, 834, 155, 92);
		add(btnClose);
		
		JButton btnCreate = new JButton("");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid.png";
					else
						pathImage = "/images/menu/Menu.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable.png";
					else
						pathImage = "/images/menu/Menu-disable.png";
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnCreate.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-create-activate.png";
					else
						pathImage = "/images/menu/Menu-create-activate.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable-create-activate.png";
					else
						pathImage = "/images/menu/Menu-disable-create-activate.png";
					
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				frame.getContentPane().remove(thisPanel);
				frame.setCurrentPane(new NewAccount(frame,enable));
				frame.getContentPane().add(frame.getCurrentPane());
			}
		});
		btnCreate.setFocusPainted(false);
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(1467, 83, 343, 27);
		add(btnCreate);
		
		JButton btnDisable = new JButton("");
		btnDisable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid.png";
					else
						pathImage = "/images/menu/Menu.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable.png";
					else
						pathImage = "/images/menu/Menu-disable.png";
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnDisable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(enable) {
					if(invalid)
						pathImage = "/images/menu/Menu-invalid-disable-activate.png";
					else
						pathImage = "/images/menu/Menu-disable-activate.png";
					
				}else {
					if(invalid)
						pathImage = "/images/menu/Menu-disable-invalid-enable-activate.png";
					else
						pathImage = "/images/menu/Menu-disable-enable-activate.png";
					
				}
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				if(enable)
					new MainFrame(false);
				else 
					new MainFrame(true);
			}
		});
		btnDisable.setFocusPainted(false);
		btnDisable.setContentAreaFilled(false);
		btnDisable.setBorderPainted(false);
		btnDisable.setBounds(1447, 980, 363, 24);
		add(btnDisable);
		
		if(enable)
			pathImage = "/images/menu/Menu.png";
		else
			pathImage = "/images/menu/Menu-disable.png";
		
		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1920, 1080);
		add(mainImage);
		
	}
}
