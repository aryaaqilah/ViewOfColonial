package voc;

import java.util.ArrayList;

public class Job {
	private String name;
	private ArrayList<Slave> slaves = new ArrayList<>();
	private int capacity;
	private int level;
	private int value;
	private double progress;
	
	public Job() {
		// TODO Auto-generated constructor stub
	}

	public Job(String name, ArrayList<Slave> slaves, int capacity, double progress) {
		super();
		this.name = name;
		this.slaves = slaves;
		this.capacity = capacity;
		this.progress = progress;
		setValueSpace();
	}
	
	public Job(String name, int value) {
		this.name = name;
		this.value = value;
		setCapacity(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Slave> getSlaves() {
		return slaves;
	}

	public void setSlaves(ArrayList<Slave> slaves) {
		this.slaves = slaves;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
		countProgress();
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	public void countProgress() {
		this.progress = slaves.size() / (double)this.capacity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		setCapacity(level * this.capacity);
	}

	public int getValue() {
		return value;
	}

	public void setValueSpace() {
//		this.value = value;
		int temp = 0;
		for (Slave slave : slaves) {
			temp += slave.getSpace();
		}
		this.value = temp;
//		if(this.value >= capacity) {
//			setLevel(this.level+1);
//		}
		checkLevel();
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void checkLevel() {
		while(this.value >= capacity) {
			setLevel(this.level+1);
		}
	}
}
