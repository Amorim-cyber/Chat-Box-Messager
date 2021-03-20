package mainFrame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import panels.Delete;
import panels.Menu;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean focus;
	private JPanel currentPane;
	private JMenuBar menuBar;
	
	public MainFrame(boolean undercorated) {
		final MainFrame frame = this;
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				focus = true;
			}
			public void windowLostFocus(WindowEvent e) {
				focus = false;
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/logo/Logo.png")));
		
		setTitle("ChatBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1090);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(undercorated);
		setResizable(false);
		
		menuBar = new JMenuBar();
		
		if(undercorated)
			menuBar.setVisible(false);
		else
			menuBar.setVisible(true);
		setJMenuBar(menuBar);
		
		JButton btnDelete = new JButton("Delete an account");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDelete.setVisible(false);
				menuBar.setVisible(false);
				contentPane.setVisible(false);
				contentPane.remove(currentPane);
				contentPane.add(new Delete(frame,btnDelete,menuBar,undercorated));
				contentPane.setVisible(true);
			}
		});
		btnDelete.setFocusPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		menuBar.add(btnDelete);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setVisible(true);
		
		currentPane = new Menu(frame,undercorated);
		
		contentPane.add(currentPane);
	}
	
	public boolean isFocus() {
		return focus;
	}

	public JPanel getCurrentPane() {
		return currentPane;
	}

	public void setCurrentPane(JPanel currentPane) {
		this.currentPane = currentPane;
	}
	
	public JMenuBar returnJMenuBar() {
		return menuBar;
	}

}
