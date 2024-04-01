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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SlaveList extends JFrame implements ActionListener{
	private Voc voc;
	private Main main;
	private PunishFrame punishframe;
	private String name;
	private String rarity;
	int codeInt;
	int hp;
	
	private JTable table_slave;
	private JScrollPane scrollPane_tableSlave;
	private DefaultTableModel dtm_tableSlave;
	
	private JLabel lbl_title = new JLabel("CHOOSE SLAVE TO BE PUNISHED !");
	
	private JPanel pnl_north = new JPanel();
	private JPanel pnl_south = new JPanel();
	private JPanel pnl_exit = new JPanel();
	
	private JTextField txt_name = new JTextField();
	private JTextField txt_hp = new JTextField();
	private JTextField txt_rarity = new JTextField();
	private JTextField txt_job = new JTextField();
	
	private JButton btn_clear = new JButton("Clear");
	private JButton btn_punish = new JButton("Punish");
	
	public SlaveList() {
		// TODO Auto-generated constructor stub
	}

	public SlaveList(Voc voc, Main main) throws HeadlessException {
		super();
		this.voc = voc;
		this.main = main;
		initFrame();
	}

	public void init_component() {
		table_slave  = new JTable() {
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		scrollPane_tableSlave = new JScrollPane(table_slave);
		
		setLayout(new BorderLayout());
		
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
        
        pnl_exit.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnl_exit.setBackground(new Color(40, 38, 52));
        pnl_exit.add(exit);
        
        pnl_north.setLayout(new GridLayout(2, 1));
        pnl_north.add(pnl_exit);
        JPanel forTitle = new JPanel(new FlowLayout());
        forTitle.add(lbl_title);
        pnl_north.add(forTitle);
        add(pnl_north, "North");
        
        add(scrollPane_tableSlave, "Center");
        
        pnl_south.setLayout(new GridLayout(5, 2));
        pnl_south.add(new JLabel("Name"));
        pnl_south.add(txt_name);
        pnl_south.add(new JLabel("HP"));
        pnl_south.add(txt_hp);
        pnl_south.add(new JLabel("Rarity"));
        pnl_south.add(txt_rarity);
        pnl_south.add(new JLabel("Job"));
        pnl_south.add(txt_job);
        pnl_south.add(btn_clear);
        btn_clear.addActionListener(this);
        pnl_south.add(btn_punish);
        btn_punish.addActionListener(this);
        
        add(pnl_south, "South");
	}
	
	public void load_table() {
		String[] column = {"Name", "HP", "Rarity", "Job"};
		dtm_tableSlave = new DefaultTableModel(column, 0);
		
		for (Slave slave : voc.getSlaves()) {
			String name = slave.getName();
			int hp = slave.getHp();
			String rarity = null;
			String job = null;
			
			if(slave.getLevelCode() == 1) {
				rarity = "Common";
			}
			else if(slave.getLevelCode() == 2) {
				rarity = "Rare";
			}
			else if(slave.getLevelCode() == 3) {
				rarity = "Epic";
			}
			else if(slave.getLevelCode() == 4) {
				rarity = "Legendary";
			}
			
			if(slave.getJobCode() == 1) {
				job = "Cultuurstelsel";
			}
			else {
				job = "Build A Bridge";
			}
			
			Object[] row = {name, hp, rarity, job};
			dtm_tableSlave.addRow(row);
		}
		table_slave.setModel(dtm_tableSlave);
	}
	
	public void initFrame() {
		init_component();
		load_table();
		
		table_slave.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = table_slave.getSelectedRow();
				String name = table_slave.getValueAt(row, 0).toString();
				txt_name.setText(name);
				String hp = table_slave.getValueAt(row, 1).toString();
				txt_hp.setText(hp);
				String rarity = table_slave.getValueAt(row, 2).toString();
				txt_rarity.setText(rarity);
				String job = table_slave.getValueAt(row, 3).toString();
				txt_job.setText(job);
			}
		});
		
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_clear)) {
			System.out.println("MASUK CLEAR");
			reset_txt();
		}
		if(e.getSource().equals(btn_punish)) {
			setVisible(false);
			String code = txt_job.getText();
			if(code.equals("Cultuurstelsel")) {
				codeInt = 0;
			}
			else if(code.equals("Build A Bridge")) {
				codeInt = 1;
			}
//			codeInt = Integer.parseInt(txt_job.getText());
			name = txt_name.getText();
			System.out.println("masuk assign");
			hp = Integer.parseInt(txt_hp.getText());
			rarity = txt_rarity.getText();
			punishframe = new PunishFrame(voc, this);
		}
	}
	
	public void reset_txt() {
		txt_name.setText("");
		txt_hp.setText("");
		txt_rarity.setText("");
		txt_job.setText("");
	}
	
	public void punishResult(int result) {
		if(result == 1) {
			System.out.println("BERHASIL");
			int iter = 0;
//			for (Slave slave : main.getVoc().getSlaves()) {
//				if(slave.getName().equals(name)) {
//					main.getVoc().getSlaves().get(iter).setSlaveLevel(main.getVoc().getSlaves().get(iter).getSlaveLevel()+1);
//					main.getVoc().getSlaves().get(iter).setHp(main.getVoc().getSlaves().get(iter).getHp()-5);
//				}
//				iter++;
//			}
//			iter = 0;
			for (Slave slave : main.getJobs().get(codeInt).getSlaves()) {
				if(slave.getName().equals(name)) {
					main.getJobs().get(codeInt).getSlaves().get(iter).setSlaveLevel(main.getJobs().get(codeInt).getSlaves().get(iter).getSlaveLevel()+1);
					main.getJobs().get(codeInt).getSlaves().get(iter).setHp(main.getJobs().get(codeInt).getSlaves().get(iter).getHp()-5);
					main.getJobs().get(codeInt).setValueSpace();
				}
				iter++;
			}
		}
		else {
			System.out.println("GAGAL TETOT");
		}
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	
}
