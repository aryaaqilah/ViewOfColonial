package voc;

import java.util.ArrayList;

public class Voc {
	private String username;
	private String password;
	private double money;
	private ArrayList<Slave> slaves = new ArrayList<>();
	
	public Voc() {
		// TODO Auto-generated constructor stub
	}

	public Voc(String username, String password, double money, ArrayList<Slave> slaves) {
		super();
		this.slaves = slaves;
		this.username = username;
		this.password = password;
		this.money = money;
	}
	
	public Voc(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public ArrayList<Slave> getSlaves() {
		return slaves;
	}

	public void setSlaves(ArrayList<Slave> slaves) {
		this.slaves = slaves;
	}
	
	public void countMoney(int level) {
		if(level == 1) {
			setMoney(this.money - 1000);
		}
		else if(level == 2) {
			setMoney(this.money - 2000);
		}
		else if(level == 3) {
			setMoney(this.money - 3000);
		}
		else if(level == 4) {
			setMoney(this.money - 5000);
		}
	}
	
	
}
