package voc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HealFrame extends JFrame implements ActionListener{
	private Main main;
	private Voc voc;
	private SlaveListForHeal slaveHeal;
	
	private JPanel pnl_exit = new JPanel();
	private JPanel pnl_center = new JPanel();
//	private JPanel pnl_center_grid = new JPanel();
	private JPanel pnl_center_center = new JPanel();
	private JPanel pnl_inputan = new JPanel();
	private JPanel pnl_button = new JPanel();
	
	private JLabel lbl_title = new JLabel("Heal Slave Page");
	private JLabel lbl_command = new JLabel("Enter a number between 1 - 10.\nThere will be a random number that becomes the limit.\nEach number entered will be added up.\nIf the result of the sum exceeds the limit then it fails.\nIf you choose to stop, the slave can be healed with the possibility: the sum / limit");
	private JLabel lbl_input = new JLabel("Input Number");
	private JLabel lbl_limit = new JLabel("Limit = ???");
	private JLabel exit;
	
	private JTextField txt_input = new JTextField();
	private JButton btn_stop = new JButton("Stop");
	
	private JButton btn_add = new JButton("Add");
	
	private int limit = 0;
	private int total = 0;
	
	public HealFrame() {
		// TODO Auto-generated constructor stub
	}

	public HealFrame(Voc voc, SlaveListForHeal slfh) throws HeadlessException {
		super();
		this.main = main;
		this.slaveHeal = slfh;
		Random rand = new Random();
		limit = rand.nextInt(10)+1;
		initFrame();
	}
	
	public void initFrame() {
		setLayout(new BorderLayout());
		
		exit = new JLabel("<");
		exit.setPreferredSize(new Dimension(30, 30));
        exit.setHorizontalAlignment(JLabel.CENTER);
        exit.setVerticalAlignment(JLabel.CENTER);
        exit.setFont(new Font("Segoe UI", Font.BOLD, 25));
        exit.setBackground(new Color(40, 38, 52));
        exit.setForeground(new Color(255, 64, 87));
        exit.setOpaque(true);
        
        pnl_exit.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_exit.setBackground(new Color(40, 38, 52));
        pnl_exit.add(exit);
        
        exit.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
//        		pf.thread.stop();
        		System.out.println("masuk listener exit");
//        		main.openProfile();
//        		bukaProfile();
//        		chanceMethod();
        		slaveHeal.getMain().openProfile();
        	}
        });
        
        add(pnl_exit, "North");
        
        pnl_center.setLayout(new GridLayout(1, 3));
        
        pnl_center_center.setLayout(new GridLayout(5, 1));
        
        pnl_center_center.add(lbl_title);
        pnl_center_center.add(lbl_command);
        pnl_inputan.setLayout(new GridLayout(1, 2));
        pnl_inputan.add(lbl_input);
        pnl_inputan.add(txt_input);
        pnl_center_center.add(pnl_inputan);
        pnl_center_center.add(lbl_limit);
        pnl_button.setLayout(new GridLayout(1, 2));
        pnl_button.add(btn_stop);
        pnl_button.add(btn_add);
        pnl_center_center.add(pnl_button);
        
        pnl_center.add(new JPanel());
        pnl_center.add(pnl_center_center);
        pnl_center.add(new JPanel());
        
        btn_stop.addActionListener(this);
        btn_add.addActionListener(this);
        
        add(pnl_center, "Center");
		
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_add)) {
			int angka = Integer.parseInt(txt_input.getText());
			total += angka;
			if(total > limit) {
				System.out.println("heal gagal");
				slaveHeal.setVisible(true);
				setVisible(false);
			}
			txt_input.setText("");
		}
		else if(e.getSource().equals(btn_stop)) {
			txt_input.setText("");
			chance(total, limit);
		}
	}
	
	public void chance(int total, int limit) {
		Random rand = new Random();
		int randNum = rand.nextInt(limit)+1;
		if(randNum > 0 && randNum <= total) {
			slaveHeal.healResult(1);
		}
		else {
			slaveHeal.healResult(0);
		}
	}
}
