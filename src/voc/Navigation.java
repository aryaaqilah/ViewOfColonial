package voc;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Navigation extends JFrame implements ActionListener{
	private JButton btn_login = new JButton("Login");
	private JButton btn_register = new JButton("Register");
	
	Main main;
	
	public Navigation() {
		// TODO Auto-generated constructor stub
	}
		
	public Navigation(Main main) throws HeadlessException {
		super();
		this.main = main;
		initFrame();
	}

	public void initFrame() {
		setLayout(new GridLayout(1, 2));
		
		add(btn_login);
		add(btn_register);
		
		btn_login.addActionListener(this);
		btn_register.addActionListener(this);
		
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_login)) {
			setVisible(false);
			main.openLogin();
		}
		else if(e.getSource().equals(btn_register)) {
			setVisible(false);
			main.openRegister();
		}
	}
}
