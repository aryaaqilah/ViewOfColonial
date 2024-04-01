package voc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class profileFrame extends JFrame implements ActionListener{
	private Voc voc;
	private Main main;
	
//	private String username;
	private ArrayList<Job> jobs = new ArrayList<>();
	private String goldLabel;
	
	private JLabel lbl_username;
	private JLabel lbl_username_isi;
	private JLabel lbl_money;
	private JLabel lbl_money_isi;
	private JLabel lbl_cont = new JLabel("Contingenten");
	private JLabel lbl_bridge = new JLabel("Build a Bridge");
	private JLabel lbl_cult = new JLabel("Cultuur Stelsel");
	private JLabel lbl_img;
	
	private JButton btn_project;
	private JButton btn_slaveList;
	private JButton btn_slaveMarket = new JButton("Slave Market");
	private JButton btn_punish = new JButton("Punish Slave");
	private JButton btn_heal = new JButton("Heal");
	
	private JPanel bio_panel = new JPanel();
	private JPanel btn_panel = new JPanel();
	private JPanel namaGold_panel = new JPanel();
	
	public profileFrame(Voc voc, Main main) {
		// TODO Auto-generated constructor stub
		System.out.println("masuk profile");
		this.voc = voc;
		this.main = main;
		initFrame();
		setVisible(true);
	}
	
	public profileFrame() {
		// TODO Auto-generated constructor stub
	}

	public profileFrame(String username, double money, ArrayList<Job> jobs) throws HeadlessException {
		super();
//		this.username = username;
//		this.money = money;
		this.jobs = jobs;
		
		this.lbl_username_isi = new JLabel(username);
		this.lbl_money_isi = new JLabel(Double.toString(money));
		
		initFrame();
	}
	
	public void initFrame() {
		setLayout(new GridLayout(2, 1));
		
		System.out.println("masuk INIT PROFILE11");
		bio_panel.setLayout(new GridLayout(1, 2));
//		bio_panel.set
		
		//add icon
		ImageIcon image = new ImageIcon(getClass().getResource("database/voc-logo.png"));
		Image image22 = image.getImage();
		Image newImage = image22.getScaledInstance(160, 120, Image.SCALE_DEFAULT);
		image = new ImageIcon(newImage);
		JLabel logo = new JLabel(image);
		logo.setBackground(new Color(40, 38, 52));
		bio_panel.setBackground(new Color(40, 38, 52));
		
		bio_panel.add(logo);
		
		namaGold_panel.setLayout(new GridLayout(2,1));
		//add nama
		String usernameLabel=  "Name : ";
		usernameLabel = usernameLabel.concat(voc.getUsername());
//		System.out.println(usernameLabel);
		lbl_username = new JLabel(usernameLabel);
//		JPanel flowing = new JPanel(new FlowLayout());
//		flowing.setBackground(new Color(40, 38, 52));
//		flowing.add(lbl_username);
//		namaGold_panel.add(flowing);
		JPanel uname = new JPanel(new BorderLayout());
//		lbl_username.setPreferredSize(new Dimension(50, 50));
		lbl_username.setFont(new Font("Segoe UI", 20, 20));
		lbl_username.setForeground(new Color(255, 64, 87));
		uname.setBackground(new Color(40, 38, 52));
		uname.add(lbl_username, "Center");
		namaGold_panel.add(uname);
		
		//add gold
		goldLabel = "Gold : ";
		goldLabel = goldLabel.concat(Double.toString(voc.getMoney()));
		lbl_money = new JLabel(goldLabel);
//		JPanel flowing2 = new JPanel(new FlowLayout());
//		flowing2.setBackground(new Color(10, 38, 52));
//		flowing2.add(lbl_money);
//		namaGold_panel.add(flowing2);
		JPanel goldSec = new JPanel(new BorderLayout());
		lbl_money.setFont(new Font("Segoe UI", 20, 20));
		lbl_money.setForeground(new Color(255, 64, 87));
		goldSec.setBackground(new Color(40, 38, 52));
		goldSec.add(lbl_money, "Center");
//		goldSec.add(lbl_money);
		namaGold_panel.add(goldSec);
		bio_panel.add(namaGold_panel);
		
		add(bio_panel);
		
		//set btn panel
		btn_panel.setPreferredSize(new Dimension(100, 50));
		btn_panel.setLayout(new GridLayout(1, 2));
		
		// set project button
//		flowing3.setBackground(new Color(99, 38, 52));
//		btn_panel.add(flowing3);
		btn_project = new JButton("Project");
		btn_project.setBackground(new Color(255, 64, 87));
		btn_project.setForeground(new Color(40, 38, 52));
		JPanel proj_pnl = new JPanel(new BorderLayout());
		JPanel flowing3 = new JPanel(new FlowLayout());
		flowing3.add(btn_project);
//		flowing3.add(proj_pnl);
		JPanel gridProj = new JPanel(new GridLayout(3, 1));
		flowing3.setBackground(new Color(40, 38, 52));
		JPanel temp = new JPanel();
		temp.setBackground(new Color(40, 38, 52));
		gridProj.add(temp);
		gridProj.setBackground(new Color(40, 38, 52));
		gridProj.add(flowing3);
		proj_pnl.add(gridProj, "Center");
		
		JPanel pnl_temp = new JPanel(new GridLayout(1, 2));
		//temp slave market nav
		pnl_temp.add(btn_heal);
		btn_slaveMarket.addActionListener(this);
		btn_heal.addActionListener(this);
		
		//temp punish nav
		pnl_temp.add(btn_punish);
		btn_punish.addActionListener(this);
		
		proj_pnl.add(pnl_temp, "South");
//		
		gridProj.setBackground(new Color(40, 38, 52));
		btn_panel.add(proj_pnl);
		
		//set slave list button
		JPanel flowing4 =  new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelKosong = new JPanel(new BorderLayout());
		JPanel gridList = new JPanel(new GridLayout(3,1));
		JPanel temp2 = new JPanel();
		temp2.setBackground(new Color(40, 38, 52));
		temp2.setForeground(new Color(40, 38, 52));
		gridList.add(temp2);
		btn_slaveList = new JButton("Slave List");
		btn_slaveList.setBackground(new Color(255, 64, 87));
		btn_slaveList.setForeground(new Color(40, 38, 52));
		flowing4.setBackground(new Color(40, 38, 52));
		flowing4.add(btn_slaveList);
		gridList.add(flowing4);
		gridList.setBackground(new Color(40, 38, 52));
		gridList.setForeground(new Color(40, 38, 52));
		btn_panel.add(gridList);
		
		btn_slaveList.addActionListener(this);
		btn_project.addActionListener(this);
		
		add(btn_panel);
		
		System.out.println("masuk INIT PROFILE");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closingAction();
//				closing();
//				setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
//		pack();
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void closingAction() {
		main.writeFile();
//		main.printdoang();
		System.out.println("keluarrr");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_project)) {
			setVisible(false);
			main.openProject();
		}
		else if(e.getSource().equals(btn_slaveList)) {
			setVisible(false);
			main.openJustSlaveList();;
		}
		else if(e.getSource().equals(btn_slaveMarket)) {
			setVisible(false);
			main.openMarket();
		}
		else if(e.getSource().equals(btn_punish)){
			setVisible(false);
			main.openSlaveList();
		}
		else if(e.getSource().equals(btn_heal)) {
			setVisible(false);
			main.openHeal();
		}
	}

	public Voc getVoc() {
		return voc;
	}

	public void setVoc(Voc voc) {
		this.voc = voc;
//		dispose();
//		initFrame();
	}

	public String getGoldLabel() {
		return goldLabel;
	}

	public void setGoldLabel(Voc voc) {
		String temp = "Gold : ";
		temp = temp.concat(Double.toString(voc.getMoney()));
//		this.goldLabel = temp;
		lbl_money.setText(temp);
	}
}
