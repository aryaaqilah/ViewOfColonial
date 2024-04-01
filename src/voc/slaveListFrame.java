package voc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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

public class slaveListFrame extends JFrame {
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
	
	private JLabel lbl_title = new JLabel("SLAVE LIST");
	
	private JPanel pnl_north = new JPanel();
	private JPanel pnl_south = new JPanel();
	private JPanel pnl_exit = new JPanel();
	
	private JTextField txt_name = new JTextField();
	private JTextField txt_hp = new JTextField();
	private JTextField txt_rarity = new JTextField();
	private JTextField txt_job = new JTextField();
	
//	private JButton btn_clear = new JButton("Clear");
//	private JButton btn_punish = new JButton("Punish");
	
	public slaveListFrame() {
		// TODO Auto-generated constructor stub
	}

	public slaveListFrame(Voc voc, Main main) throws HeadlessException {
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
	
	
}
