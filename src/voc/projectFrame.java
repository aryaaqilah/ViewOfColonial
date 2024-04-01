package voc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

public class projectFrame extends JFrame{
	private Voc voc;
	private Main main;
	private ArrayList<Job> jobs;
	private JProgressBar jpb_bridge = new JProgressBar();
	private JProgressBar jpb_cont = new JProgressBar();
	
	private JPanel pnl_bridge = new JPanel();
	private JPanel pnl_cont = new JPanel();
	private JPanel pnl_exit = new JPanel();
	private JPanel pnl_center = new JPanel();
	
	private JLabel logo;
	private JLabel logoCont;
	
	public projectFrame() {
		// TODO Auto-generated constructor stub
	}

	public projectFrame(Voc voc, Main main, ArrayList<Job> jobs) throws HeadlessException {
		super();
		this.voc = voc;
		this.main = main;
		this.jobs = jobs;
		initFrame();
	}

	public Voc getVoc() {
		return voc;
	}

	public void setVoc(Voc voc) {
		this.voc = voc;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}

	public void initFrame() {
		setLayout(new BorderLayout());
		pnl_center.setLayout(new GridLayout(1, 2));
		
		JLabel exit = new JLabel("<");
		exit.setPreferredSize(new Dimension(30, 30));
        exit.setHorizontalAlignment(JLabel.CENTER);
        exit.setVerticalAlignment(JLabel.CENTER);
        exit.setFont(new Font("Segoe UI", Font.BOLD, 25));
        exit.setBackground(new Color(40, 38, 52));
        exit.setForeground(new Color(255, 64, 87));
        exit.setOpaque(true);
        
        exit.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
        		main.openProfile();
        	}
        });
        
//        exit.setBorder(new LineBorder(new Color(255, 64, 87), 1));
        pnl_exit.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_exit.setBackground(new Color(40, 38, 52));
        pnl_exit.add(exit);
		
		try {
			ImageIcon imageBridge = new ImageIcon(getClass().getResource("database/bridge-icon.png"));
			Image image22 = imageBridge.getImage();
			Image newImage = image22.getScaledInstance(200, 120, Image.SCALE_DEFAULT);
			imageBridge = new ImageIcon(newImage);
			
			int newWidth = 100;
			int newHeight = 100;
			
			logo = new JLabel(imageBridge);
			logo.setText(jobs.get(1).getName());
	        logo.setHorizontalTextPosition(JLabel.CENTER);
	        logo.setVerticalTextPosition(JLabel.BOTTOM);
	        logo.setForeground(new Color(255, 64, 87));
	        logo.setBackground(new Color(40, 38, 52));
	        logo.setFont(new Font("Segoe UI", 0, 20));
	        logo.setPreferredSize(new Dimension(10, 10));
//			pnl_north.add(logo);
//			add(pnl_north);
	        
	        ImageIcon imageCont = new ImageIcon(getClass().getResource("database/cultuur-icon.png"));
			Image image2 = imageCont.getImage();
			Image newImageCont = image2.getScaledInstance(160, 120, Image.SCALE_DEFAULT);
			imageCont = new ImageIcon(newImageCont);
			
//			int newWidth = 100;
//			int newHeight = 100;
			
			logoCont = new JLabel(imageCont);
			logoCont.setText(jobs.get(0).getName());
	        logoCont.setHorizontalTextPosition(JLabel.CENTER);
	        logoCont.setVerticalTextPosition(JLabel.BOTTOM);
	        logoCont.setForeground(new Color(255, 64, 87));
	        logoCont.setBackground(new Color(40, 38, 52));
	        logoCont.setFont(new Font("Segoe UI", 0, 20));
	        logoCont.setPreferredSize(new Dimension(10, 10));
			
		} catch (Exception e) {
			System.out.println("GAGAL");
		}
		
		pnl_bridge.setLayout(new GridLayout(2,1));
		pnl_bridge.add(logo);
		pnl_bridge.setBackground(new Color(40, 38, 52));
		pnl_cont.setBackground(new Color(40, 38, 52));
		jpb_bridge.setStringPainted(true);
		int temp = jobs.get(1).getCapacity();
		int temp2 = jobs.get(1).getValue();
		jpb_bridge.setMaximum(temp);
		jpb_bridge.setValue(temp2);
		System.out.println(temp2);
		System.out.println(temp);
//		JPanel jpan = new JPanel();
//		jpan.add(jpb_bridge);
		jpb_bridge.setBackground(new Color(40, 38, 52));
		jpb_bridge.setForeground(new Color(255, 64, 87));
		String levelBridge = " - Level : ";
		levelBridge = levelBridge.concat(Integer.toString(jobs.get(1).getLevel()));
		String persen = jpb_bridge.getString();
		persen = persen.concat(levelBridge);
		jpb_bridge.setString(persen);
		pnl_bridge.add(jpb_bridge);
		
		pnl_cont.setLayout(new GridLayout(2,1));
		pnl_cont.add(logoCont);
		jpb_cont.setStringPainted(true);
		jpb_cont.setMaximum(jobs.get(0).getCapacity());
		jpb_cont.setValue(jobs.get(0).getValue());
		jpb_cont.setBackground(new Color(40, 38, 52));
		jpb_cont.setForeground(new Color(255, 64, 87));
		String levelCont = " - Level : ";
		levelCont = levelCont.concat(Integer.toString(jobs.get(0).getLevel()));
		String persenCont = jpb_cont.getString();
		persenCont = persenCont.concat(levelCont);
		jpb_cont.setString(persenCont);
		pnl_cont.add(jpb_cont);
		
		
		System.out.println(temp);
		System.out.println(temp2);
		
		pnl_center.add(pnl_cont);
		pnl_center.add(pnl_bridge);
		
		add(pnl_exit, "North");
		add(pnl_center, "Center");
		
		System.out.println("masuk projectFrame");
		
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
}
