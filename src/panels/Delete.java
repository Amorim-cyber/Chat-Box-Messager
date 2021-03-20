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
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import back_end.Login;
import mainFrame.MainFrame;

public class Delete extends JPanel {

	private static final long serialVersionUID = 1L;
	private String pathImage= "/images/delete/Delete.png";
	private JLabel mainImage;
	private boolean deleted = false;
	private boolean notFound = false;
	
	public Delete(MainFrame frame, JButton delete,JMenuBar menu,boolean enable) {

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
		txtLogin.setBounds(1293, 422, 456, 27);
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
		txtPassword.setBounds(1293, 468, 456, 27);
		add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JButton btnDelete = new JButton("");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(deleted)
					pathImage= "/images/delete/Delete-deleted.png";
				else if(notFound)
					pathImage= "/images/delete/Delete-notFound.png";
				else
					pathImage= "/images/delete/Delete.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnDelete.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(deleted)
					pathImage= "/images/delete/Delete-deleted-delete-activate.png";
				else if(notFound)
					pathImage= "/images/delete/Delete-notFound-delete-activate.png";
				else
					pathImage= "/images/delete/Delete-delete-activate.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] chares = txtPassword.getPassword();
				String password = "";
				
				for(int i=0; i<chares.length;i++)
					password+=chares[i];
				
				Login login = new Login(txtLogin.getText(),password);
				
				if(login.exist(false)) {
					login.deleted();
					pathImage = "/images/delete/Delete-deleted.png";
					mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
					txtLogin.setText("");
					txtPassword.setText("");
					notFound=false;
					deleted=true;
				}else {
					pathImage = "/images/delete/Delete-notFound.png";
					mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
					txtLogin.setText("");
					txtPassword.setText("");
					notFound=true;
					deleted=false;
				}
			}
		});
		btnDelete.setFocusPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(1463, 547, 128, 90);
		add(btnDelete);
		
		JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(deleted)
					pathImage= "/images/delete/Delete-deleted.png";
				else if(notFound)
					pathImage= "/images/delete/Delete-notFound.png";
				else
					pathImage= "/images/delete/Delete.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(deleted)
					pathImage= "/images/delete/Delete-deleted-back-activate.png";
				else if(notFound)
					pathImage= "/images/delete/Delete-notFound-back-activate.png";
				else
					pathImage= "/images/delete/Delete-back-activate.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(frame.getCurrentPane());
				menu.setVisible(true);
				delete.setVisible(true);
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(1640, 546, 128, 91);
		add(btnBack);
		
		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1920, 1080);
		add(mainImage);
		
	}

}
