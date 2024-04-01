package voc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener{
	private JPanel pnl_north = new JPanel();
	private JPanel pnl_center = new JPanel();
	private JPanel pnl_south = new JPanel();
	private JPanel pnl_right = new JPanel();
	private JLabel lbl_title = new JLabel("View of Collonial");
	private JLabel lbl_login = new JLabel("REGISTER");
	private JLabel lbl_username = new JLabel("Username");
	private JLabel lbl_password = new JLabel("Password");
	private JLabel lbl_confirm = new JLabel("Confirm Password");
	private JTextField txt_username = new JTextField();
//	private JTextField txt_password = new JTextField();
	private JPasswordField txt_password = new JPasswordField();
	private JPasswordField txt_confirm = new JPasswordField();
	
	private JButton btn_register = new JButton("Register");
	
	Main main;

	public Register() {
		// TODO Auto-generated constructor stub
	}
	
	public Register(Main main) throws HeadlessException {
		super();
		this.main = main;
		initFrame();
	}

	public void initFrame() {
		setLayout(new GridLayout(1, 2));
		
		//North
//		pnl_north.setLayout(new FlowLayout());
		pnl_north.setLayout(new BorderLayout());
		pnl_north.setBackground(new Color(40,38,52));
//		
//		pnl_north.add(lbl_title);
//		add(pnl_north);
		try {
			ImageIcon image = new ImageIcon(getClass().getResource("database/voc-logo.png"));
			Image image22 = image.getImage();
			Image newImage = image22.getScaledInstance(320, 240, Image.SCALE_DEFAULT);
			image = new ImageIcon(newImage);
			
			int newWidth = 100;
			int newHeight = 100;
			
//			BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
//			Graphics2D g = newImage.createGraphics();
//	        g.drawImage(image22, 0, 0, newWidth, newHeight, null);
//	        g.dispose();
			
			

	        // buat objek ImageIcon baru dari gambar yang baru
//	        ImageIcon newImageIcon = new ImageIcon(newImage);
			
			JLabel logo = new JLabel(image);
			logo.setText("View Of Collonial");
	        logo.setHorizontalTextPosition(JLabel.CENTER);
	        logo.setVerticalTextPosition(JLabel.BOTTOM);
	        logo.setForeground(new Color(255, 64, 87));
	        logo.setFont(new Font("Segoe UI", 0, 20));
	        logo.setPreferredSize(new Dimension(10, 10));
//	        left_pan.add(logo);
			pnl_north.add(logo);
			add(pnl_north);
			
		} catch (Exception e) {
			System.out.println("GAGAL");
		}
		//center
//		pnl_center.setLayout(new GridLayout(2, 2));
		pnl_right.setLayout(new BorderLayout());
		lbl_login.setPreferredSize(new Dimension(this.getWidth(), 70));
		lbl_login.setHorizontalAlignment(JLabel.CENTER);
		lbl_login.setFont(new Font("Segoe UI", 20, 40));
		lbl_login.setForeground(new Color(255, 64, 87));
		lbl_login.setPreferredSize(new Dimension(200, 90));
		pnl_right.add(lbl_login, "North");
		
		pnl_center.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 25));
//		pnl_center.setLayout(new FlowLayout);
		lbl_username.setPreferredSize(new Dimension(200, 30));
		lbl_username.setVerticalAlignment(JLabel.CENTER);
		lbl_username.setForeground(new Color(255, 64, 87));
		pnl_center.add(lbl_username);
		txt_username.setPreferredSize(new Dimension(200, 40));
		pnl_center.add(txt_username);
		lbl_password.setPreferredSize(new Dimension(200, 30));
		lbl_password.setForeground(new Color(255, 64, 87));
		pnl_center.add(lbl_password);
		txt_password.setPreferredSize(new Dimension(200, 40));
		pnl_center.add(txt_password);
		
		lbl_confirm.setPreferredSize(new Dimension(200, 30));
		lbl_confirm.setForeground(new Color(255, 64, 87));
		pnl_center.add(lbl_confirm);
		txt_confirm.setPreferredSize(new Dimension(200, 40));
		pnl_center.add(txt_confirm);
		
		pnl_center.setBackground(new Color(40, 38, 52));
		pnl_right.add(pnl_center, "Center");
		pnl_right.setBackground(new Color(40, 38, 52));
//		pnl_south.setLayout(new GridLayout(1,2));
//		pnl_south.add(btn_register);
//		pnl_center.add(new JPanel());
		btn_register.setBackground(new Color(255, 64, 87));
//		pnl_center.add(btn_login);
		pnl_south.setBackground(new Color(40, 38, 52));
//		pnl_south
		btn_register.setPreferredSize(new Dimension(120, 30));
		pnl_center.add(btn_register);
		pnl_right.add(pnl_south, "South");
		add(pnl_right);
		
		btn_register.addActionListener(this);
		
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closingAction();
//				closing();
//				setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void closingAction() {
		main.writeFile();
//		main.printdoang();
//		System.out.println("keluarrr");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int checker = 0;
		if(e.getSource().equals(btn_register)) {
			String username = txt_username.getText();
			System.out.println(username);
			String password = new String(txt_password.getPassword());
			System.out.println(password);
			String confirmPass = new String(txt_confirm.getPassword());
			System.out.println(confirmPass);
			
			if(password.equals(confirmPass)) {
				checker = 1;
			}
			if(checker == 1) {
				setVisible(false);
				main.createUser(username, password);
			}
			else {
				txt_username.setText("");
		        txt_password.setText("");
		        txt_confirm.setText("");
			}
		}
	}
}
