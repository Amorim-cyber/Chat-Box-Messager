package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import back_end.Login;
import mainFrame.MainFrame;

public class Chat extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel mainImage;
	private String pathImage= "/images/chat/Chat.png";
	private JTextArea textAreaChat;
	private JButton btnTitle;
	private JButton btnGo;
	private JButton btnBack;
	private JFormattedTextField txtDate;
	
	private boolean date = false;
	private boolean invalidDate = false;
	private boolean flash = false;
	
	private String month, day, year;
	
	public Chat(MainFrame frame,Login login,boolean enable) {
		setToday();
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		final JPanel thisPanel = this;
		back_end.Chat chat = new back_end.Chat(month,day,year,false);
		
		btnTitle = new JButton("Chat date      "+month+"-"+day+"-"+year);
		btnTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnTitle.setForeground(Color.BLACK);
			}
		});
		btnTitle.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnTitle.setForeground(Color.red);
			}
		});
		btnTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTitle.setVisible(false);
				pathImage = "/images/chat/Chat-date.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
				btnGo.setVisible(true);
				btnBack.setVisible(true);
				txtDate.setVisible(true);
				date = true;
			}
		});
		btnTitle.setForeground(Color.BLACK);
		btnTitle.setHorizontalAlignment(SwingConstants.LEFT);
		btnTitle.setFont(new Font("Cambria", Font.BOLD, 50));
		btnTitle.setFocusPainted(false);
		btnTitle.setContentAreaFilled(false);
		btnTitle.setBorderPainted(false);
		btnTitle.setBounds(145, 148, 699, 64);
		add(btnTitle);
		
		
		JScrollPane scrollPaneMgs = new JScrollPane();
		scrollPaneMgs.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPaneMgs.setBorder(null);
		scrollPaneMgs.setBounds(145, 438, 703, 365);
		add(scrollPaneMgs);
		JTextArea textAreaMgs = new JTextArea();
		textAreaMgs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textAreaMgs.getText().equals("\n"))
					textAreaMgs.setText("");
			}
		});
		
		InputMap input = textAreaMgs.getInputMap();
	    KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
	    KeyStroke shiftEnter = KeyStroke.getKeyStroke("shift ENTER");
	    input.put(shiftEnter, "insert-break");  
	    input.put(enter, "text-submit");
		
	    ActionMap actions = textAreaMgs.getActionMap();
	    actions.put("text-submit", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(ActionEvent e) {
				
				if(textAreaMgs.getText().equals(""))
					return;
				
	        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				
				textAreaChat.setText(textAreaChat.getText()+login.getUserName()+"\t"+dtf.format(now)+"\n\n>> "+textAreaMgs.getText()+
						"\n\n");
				textAreaMgs.setText("");
				chat.save(textAreaChat.getText());
				
	        }
	    });
	    
	    textAreaMgs.setFont(new Font("Cambria", Font.PLAIN, 25));
		textAreaMgs.setLineWrap(true);
		textAreaMgs.setWrapStyleWord(true);
		scrollPaneMgs.setViewportView(textAreaMgs);
	    
	    
	    JScrollPane scrollPaneChat = new JScrollPane();
	    scrollPaneChat.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
	    scrollPaneChat.setBorder(null);
		scrollPaneChat.setBounds(1070, 140, 715, 800);
		add(scrollPaneChat);
		
		textAreaChat = new JTextArea();
		textAreaChat.setEditable(false);
		textAreaChat.setFont(new Font("Cambria", Font.PLAIN, 25));
		textAreaChat.setLineWrap(true);
		textAreaChat.setText(chat.getTxt());
		textAreaChat.setWrapStyleWord(true);
		scrollPaneChat.setViewportView(textAreaChat);
	    
		JButton btnSend = new JButton("");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(date) {
					if(invalidDate)
						pathImage = "/images/chat/Chat-invalidDate.png";
					else
						pathImage = "/images/chat/Chat-date.png";
				}
					
				else
					pathImage = "/images/chat/Chat.png";
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnSend.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(date) {
					if(invalidDate)
						pathImage = "/images/chat/Chat-invalidDate-send-activate.png";
					else
						pathImage = "/images/chat/Chat-date-send-activate.png";
				}
					
				else
					pathImage = "/images/chat/Chat-send-activate.png";
				
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));	
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textAreaMgs.getText().equals(""))
					return;
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				
				textAreaChat.setText(textAreaChat.getText()+login.getUserName()+"\t"+dtf.format(now)+"\n\n>> "+textAreaMgs.getText()+
						"\n\n");
				textAreaMgs.setText("");
				chat.save(textAreaChat.getText());
				
			}
		});
		btnSend.setBounds(527, 834, 126, 92);
		btnSend.setFocusPainted(false);
		btnSend.setContentAreaFilled(false);
		btnSend.setBorderPainted(false);
		add(btnSend);
		
		
		MaskFormatter maskDate;
		try {
			maskDate = new MaskFormatter("##/##/####");
			txtDate = new JFormattedTextField(maskDate);
			txtDate.setHorizontalAlignment(SwingConstants.CENTER);
			txtDate.setFont(new Font("Cambria", Font.PLAIN, 40));
			txtDate.setBounds(321, 159, 225, 45);
			txtDate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			add(txtDate);
			txtDate.setVisible(false);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		

		btnGo = new JButton("");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(invalidDate)
					pathImage = "/images/chat/Chat-invalidDate.png";
				else
					pathImage = "/images/chat/Chat-date.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnGo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(invalidDate)
					pathImage = "/images/chat/Chat-invalidDate-go-activate.png";
				else
					pathImage = "/images/chat/Chat-date-go-activate.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));	
			}
		});
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String m = txtDate.getText().substring(0,2);
				String d = txtDate.getText().substring(3,5);
				String y = txtDate.getText().substring(6,10);
				
				back_end.Chat chat = new back_end.Chat(m, d, y, true);
				
				if(chat.isInvalid()) {
					pathImage = "/images/chat/Chat-invalidDate.png";
					mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
					invalidDate = true;
					txtDate.setText("");
				}else {
					setVisible(false);
					frame.getContentPane().remove(thisPanel);
					frame.setCurrentPane(new Records(m,d,y,frame,login,chat,enable));
					frame.getContentPane().add(frame.getCurrentPane());
				}
				
			}
		});
		btnGo.setFocusPainted(false);
		btnGo.setContentAreaFilled(false);
		btnGo.setBorderPainted(false);
		btnGo.setBounds(591, 138, 106, 91);
		add(btnGo);
		btnGo.setVisible(false);
		
		btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(invalidDate)
					pathImage = "/images/chat/Chat-invalidDate.png";
				else if(date)
					pathImage = "/images/chat/Chat-date.png";
				else
					pathImage = "/images/chat/Chat.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnBack.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(invalidDate)
					pathImage = "/images/chat/Chat-invalidDate-back-activate.png";
				else 
					pathImage = "/images/chat/Chat-date-back-activate.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));	
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = false;
				btnTitle.setVisible(true);
				btnGo.setVisible(false);
				btnBack.setVisible(false);
				txtDate.setVisible(false);
				
				invalidDate = false;
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(719, 138, 128, 91);
		add(btnBack);
		btnBack.setVisible(false);
		
		
		JButton btnLogOut = new JButton("");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(date) {
					if(invalidDate) 
						pathImage = "/images/chat/Chat-invalidDate.png";
					else
						pathImage = "/images/chat/Chat-date.png";
				}	
				else
					pathImage = "/images/chat/Chat.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnLogOut.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(date) {
					if(invalidDate) 
						pathImage = "/images/chat/Chat-date-logout-activate.png";
					else
						pathImage = "/images/chat/Chat-date-logout-activate.png";
				}
				else
					pathImage = "/images/chat/Chat-logout-activate.png";
				mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				frame.getContentPane().remove(thisPanel);
				frame.setCurrentPane(new Menu(frame,enable));
				frame.getContentPane().add(frame.getCurrentPane());
			}
		});
		btnLogOut.setFocusPainted(false);
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBounds(682, 834, 164, 92);
		add(btnLogOut);
		
		Timer t = new Timer();
	    t.schedule(new TimerTask() {
	        @Override public void run() {
	        	textAreaChat.setText(chat.getTxt());
	        	
	        	if(!frame.isFocus() &&!textAreaChat.getText().equals(chat.getTxt())) {
	        		notification(frame);
	        	}
	        	else {
	        		chat.save(textAreaChat.getText());
	        		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") +"\\imagens\\logo\\Logo.png"));
	        		flash = true;
	        	}	
	        }
	    }, 0L, 1000L);
		
		
		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(Menu.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1920, 1080);
		add(mainImage);
		
		

	}
	
	private void setToday() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		
		year = Integer.toString(calendar.get(Calendar.YEAR));
		
		if(calendar.get(Calendar.MONTH)+1 < 10) {
			month = "0"+(calendar.get(Calendar.MONTH) + 1);
		}else {
			month = Integer.toString(calendar.get(Calendar.MONTH)+1);
		}
		
		if(calendar.get(Calendar.DAY_OF_MONTH) < 10) {
			day = "0"+(calendar.get(Calendar.DAY_OF_MONTH));
		}else {
			day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		}
	}
	
	public void notification(MainFrame frame) {
		if(flash) {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") +"\\imagens\\logo\\Logo.png"));
			flash = false;
		}else {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") +"\\imagens\\logo\\Logo2.png"));
			flash = true;
		}
		
	}
}
