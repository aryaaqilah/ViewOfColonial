package voc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SlaveMarket extends JFrame implements ActionListener{
	private ArrayList<Slave> slaves;
	private ArrayList<Job> jobs;
	private Voc voc;
	private Main main;
	
	private JTable table_slave;
	private JScrollPane scrollPane_tableSlave;
	private DefaultTableModel dtm_tableSlave;
	
	private JLabel lbl_title = new JLabel("Slaves Market");
	
	private JPanel pnl_north = new JPanel();
	private JPanel pnl_south = new JPanel();
	private JPanel pnl_exit = new JPanel();
	
	private JTextField txt_name = new JTextField();
	private JTextField txt_hp = new JTextField();
	private JTextField txt_rarity = new JTextField();
	private JTextField txt_price = new JTextField();
	private JTextField txt_job = new JTextField();
	
	private JButton btn_clear = new JButton("Clear");
	private JButton btn_assign = new JButton("Assign");

	public SlaveMarket(ArrayList<Slave> slaves, Main main, ArrayList<Job> jobs, Voc voc) throws HeadlessException {
		super();
		this.slaves = slaves;
		this.main = main;
		this.jobs = jobs;
		this.voc = voc;
		initFrame();
	}
	
	public SlaveMarket() {
		// TODO Auto-generated constructor stub
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
        
        pnl_south.setLayout(new GridLayout(6, 2));
        pnl_south.add(new JLabel("Name"));
        pnl_south.add(txt_name);
        pnl_south.add(new JLabel("HP"));
        pnl_south.add(txt_hp);
        pnl_south.add(new JLabel("Rarity"));
        pnl_south.add(txt_rarity);
        pnl_south.add(new JLabel("Price"));
        pnl_south.add(txt_price);
        pnl_south.add(new JLabel("Assign to [1. Cultuurstelsel / 2. Build A Bridge]"));
        pnl_south.add(txt_job);
        pnl_south.add(btn_clear);
        btn_clear.addActionListener(this);
        pnl_south.add(btn_assign);
        btn_assign.addActionListener(this);
        
        add(pnl_south, "South");
	}
	
	public void load_table() {
		String[] column = {"Name", "HP", "Rarity", "Price"};
		dtm_tableSlave = new DefaultTableModel(column, 0);
		
		for (Slave slave : slaves) {
			String name = slave.getName();
			int hp = slave.getHp();
			String rarity = null;
			double price = 0;
			
			if(slave.getLevelCode() == 1) {
				rarity = "Common";
				price = 1000;
			}
			else if(slave.getLevelCode() == 2) {
				rarity = "Rare";
				price = 2000;
			}
			else if(slave.getLevelCode() == 3) {
				rarity = "Epic";
				price = 3000;
			}
			else if(slave.getLevelCode() == 4) {
				rarity = "Legendary";
				price = 5000;
			}
			
			Object[] row = {name, hp, rarity, price};
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
				String price = table_slave.getValueAt(row, 3).toString();
				txt_price.setText(price);
			}
		});
		
		setSize(16*48, 12*48);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btn_clear)) {
			System.out.println("MASUK CLEAR");
			reset_txt();
		}
		if(e.getSource().equals(btn_assign)) {
			String code = txt_job.getText();
			int codeInt = Integer.parseInt(code);
			String name = txt_name.getText();
			System.out.println("masuk assign");
			int hp = Integer.parseInt(txt_hp.getText());
			int level = 0;
			String rarity = txt_rarity.getText();
			
			if(rarity.equals("Common")) {
				level = 1;
			}
			else if(rarity.equals("Rare")) {
				level = 2;
			}
			else if(rarity.equals("Epic")) {
				level = 3;
			}
			else if(rarity.equals("Legendary")) {
				level = 4;
			}
			
			Slave slaveTemp = new Slave(name, hp, level, codeInt);
			
			if(codeInt == 1) {
				main.getJobs().get(0).getSlaves().add(slaveTemp);
				main.getJobs().get(0).setValueSpace();
				main.getVoc().getSlaves().add(slaveTemp);
				main.getVoc().countMoney(level);
			}
			else if(codeInt == 2) {
				main.getJobs().get(1).getSlaves().add(slaveTemp);
				main.getJobs().get(1).setValueSpace();
				main.getVoc().getSlaves().add(slaveTemp);
				main.getVoc().countMoney(level);
			}
			else {
				System.out.println("masuk else");
				JOptionPane.showMessageDialog(null, "Pilih 1 atau 2");
				
			}
			reset_txt();
		}
	}
	
	public void reset_txt() {
		txt_name.setText("");
		txt_hp.setText("");
		txt_rarity.setText("");
		txt_price.setText("");
		txt_job.setText("");
	}
}
