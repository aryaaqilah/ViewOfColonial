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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PunishFrame extends JFrame implements ActionListener{
	private Voc voc;
	private Main main;
	private SlaveList slavelist;
	private int flag = 1;
	private int total = 0;
	private int timeLeft;
	int totalPunched = 0;
	
	private JPanel pnl_exit = new JPanel();
	private JPanel pnl_center = new JPanel();
	private JPanel pnl_south = new JPanel();
	
	private JLabel logo;
	private JLabel logo2;
	private JLabel tempLogo1 = new JLabel();
	private JLabel counter;
	private JLabel countdown = new JLabel("Timer 5 seconds");
	
	private ImageIcon image2;
	private ImageIcon image;
	
	private Thread thread;
	PunishFrame pf;
	JLabel exit;
	
	private Clip clip;
	
	private javax.swing.Timer timer;
	
//	public PunishFrame() {
//		// TODO Auto-generated constructor stub
//	}

	public PunishFrame(Voc voc, Main main) throws HeadlessException {
//		super();
		this.voc = voc;
		this.main = main;
//		pf = new PunishFrame();
//		pf.thread = new Thread(pf);
//		pf.thread.start();
		initFrame();
		
	}

	public PunishFrame(Voc voc, SlaveList slavelist) throws HeadlessException {
		super();
		this.voc = voc;
		this.slavelist = slavelist;
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
	
	public void initFrame() {
//		startThread();
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
        		chanceMethod();
        		slavelist.getMain().openProfile();
        	}
        });
        
        add(pnl_exit, "North");
        
        try {
			image = new ImageIcon(getClass().getResource("database/this-pukul.png"));
			Image image22 = image.getImage();
			Image newImage = image22.getScaledInstance(320, 240, Image.SCALE_DEFAULT);
			image = new ImageIcon(newImage);

			logo = new JLabel(image);
//			logo.setText("View Of Collonial");
	        logo.setHorizontalTextPosition(JLabel.CENTER);
	        logo.setVerticalTextPosition(JLabel.BOTTOM);
	        logo.setForeground(new Color(255, 64, 87));
	        logo.setFont(new Font("Segoe UI", 0, 20));
	        logo.setPreferredSize(new Dimension(500, 500));
//	        left_pan.add(logo);
			pnl_center.add(logo);
			pnl_center.setBackground(new Color(40, 38, 52));
			add(pnl_center, "Center");
			
			tempLogo1 = logo;
			
			image2 = new ImageIcon(getClass().getResource("database/this-pukul-mirror.png"));
			Image image222 = image2.getImage();
			Image newImage2 = image222.getScaledInstance(320, 240, Image.SCALE_DEFAULT);
			image2 = new ImageIcon(newImage2);

			logo2 = new JLabel(image2);
//			logo.setText("View Of Collonial");
	        logo2.setHorizontalTextPosition(JLabel.CENTER);
	        logo2.setVerticalTextPosition(JLabel.BOTTOM);
	        logo2.setForeground(new Color(255, 64, 87));
	        logo2.setFont(new Font("Segoe UI", 0, 20));
	        logo2.setPreferredSize(new Dimension(500, 500));
			
		} catch (Exception e) {
			System.out.println("GAGAL");
		}
        
        logo.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		flag*=-1;
        		try {
					File audioFile = new File("punch-sound.wav");
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
					
					clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					
					clip.addLineListener(new LineListener() {
						
						@Override
						public void update(LineEvent event) {
							if(event.getType() == LineEvent.Type.STOP) {
								clip.stop();
								clip.close();
							}
						}
					});
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
        		clickedMethod(flag);
        	}
        });
        
        
        
       counter = new JLabel("Punched : 0x");
       counter.setForeground(new Color(255, 64, 87));
       countdown.setForeground(new Color(255, 64, 87));
       pnl_south.setLayout(new GridLayout(1, 2));
       pnl_south.add(countdown);
       pnl_south.add(counter);
       pnl_south.setBackground(new Color(40, 38, 52));
       add(pnl_south, "South");
       
       timeLeft = 5;
       timer = new javax.swing.Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLeft--;
				countdown.setText("Countdown : " + timeLeft + " seconds");
				totalPunched = 0;
				if(timeLeft == 0) {
					timer.stop();
					logo.setEnabled(false);
					counter.setText("Punched : " + total + "x");
					totalPunched = total;
					counter.setText("Punched : " + totalPunched + "x --- [STOP IT BRO]");
				}
			}
		});
       timer.start();
		
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void clickedMethod(int flag) {
		if(flag == 1) {
//			this.logo = new JLabel(image);
			logo.setIcon(image);
		}
		else {
//			this.logo = new JLabel(image)
//			logo.set
			logo.setIcon(image2);
//			logo.set
		}
		this.total+=1;
		if(timeLeft != 0) {
			counter.setText("Punched in 5s : " + total + "x");			
		}
	}
	
	public void bukaProfile() {
		System.out.println("MASUK BUKA PROFILE");
		main.openProfile();
	}
	
	public void counter5s() {
		
	}
	
	public void startThread() {
//		counter5s counting = new 
	}
	
	public void chanceMethod() {
		Random rand = new Random();
		int randNum = rand.nextInt(50)+1;
		if(randNum > 0 && randNum <= totalPunched) {
			slavelist.punishResult(1);
		}
		else {
			slavelist.punishResult(0);
		}
	}
	
//	@Override
//	public void run() {
//		try {
//			Thread.sleep(5000);
//			logo.setEnabled(false);
//			counter.setText("Punched : " + total + "x");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		exit.addMouseListener(new MouseAdapter() {
//        	public void mouseClicked(MouseEvent e) {
//        		setVisible(false);
//        		pf.thread.stop();
//        		System.out.println("masuk listener exit");
////        		main.openProfile();
//        		bukaProfile();
//        	}
//        });
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
