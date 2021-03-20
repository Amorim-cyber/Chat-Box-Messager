package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import back_end.Login;
import mainFrame.MainFrame;

public class Records extends JPanel {

	private static final long serialVersionUID = 1L;
	private String pathImage= "/images/records/Record.png";
	private JLabel mainImage;

	public Records(String m, String d, String y,MainFrame frame,Login login,back_end.Chat chat,boolean enable) {

		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		final JPanel thisPanel = this;
		
		JButton btnTitle = new JButton("Chat date      "+m+"-"+d+"-"+y);
		btnTitle.setForeground(Color.BLACK);
		btnTitle.setHorizontalAlignment(SwingConstants.LEFT);
		btnTitle.setFont(new Font("Cambria", Font.BOLD, 50));
		btnTitle.setFocusPainted(false);
		btnTitle.setContentAreaFilled(false);
		btnTitle.setBorderPainted(false);
		btnTitle.setBounds(145, 147, 699, 64);
		add(btnTitle);
		
		JScrollPane scrollPaneChat = new JScrollPane();
		scrollPaneChat.setBorder(null);
		scrollPaneChat.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPaneChat.setBounds(135, 270, 1653, 495);
		add(scrollPaneChat);
		
		JTextArea textAreaChat = new JTextArea();
		textAreaChat.setEditable(false);
		textAreaChat.setFont(new Font("Cambria", Font.PLAIN, 25));
		textAreaChat.setLineWrap(true);
		textAreaChat.setText(chat.getTxt());
		textAreaChat.setWrapStyleWord(true);
		scrollPaneChat.setViewportView(textAreaChat);
	    
		JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				pathImage= "/images/records/Record.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pathImage= "/images/records/Record-back-activate.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				frame.getContentPane().remove(thisPanel);
				frame.setCurrentPane(new Chat(frame,login,enable));
				frame.getContentPane().add(frame.getCurrentPane());
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(1632, 824, 141, 101);
		add(btnBack);
		
		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1920, 1080);
		add(mainImage);
		
	}

}
