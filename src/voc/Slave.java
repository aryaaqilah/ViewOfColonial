package voc;

public class Slave {
	private String name;
	private int hp;
//	private double money;
	private int jobCode;
	private int levelCode;
	private int slaveLevel;
	private int space;
	
	public Slave() {
		// TODO Auto-generated constructor stub
	}

	public Slave(String name, int hp, int slaveLevel, int jobCode) {
		super();
		this.name = name;
		this.hp = hp;
		this.jobCode = jobCode;
		this.slaveLevel = slaveLevel;
//		setLevelNSpace(levelCode);
		setSlaveLevel(slaveLevel);
	}
	
	public void setLevelNSpace(int levelCode) {
		this.levelCode = levelCode;
		if(levelCode == 1) {
			this.slaveLevel = 1;
			this.space = 1;
		}
		else if(levelCode == 2) {
			this.slaveLevel = 5;
			this.space = 5;
		}
		else if(levelCode == 3) {
			this.slaveLevel = 20;
			this.space = 20;
		}
		else {
			this.slaveLevel = 100;
			this.space = 100;
		}
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getJobCode() {
		return jobCode;
	}

	public void setJobCode(int jobCode) {
		this.jobCode = jobCode;
	}

	public int getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(int levelCode) {
		this.levelCode = levelCode;
	}

	public int getSlaveLevel() {
		return slaveLevel;
	}

	public void setSlaveLevel(int slaveLevel) {
		this.slaveLevel = slaveLevel;
		if(this.slaveLevel < 5) {
			setLevelCode(1);
			setSpace(1);
		}
		else if(this.slaveLevel < 20) {
			setLevelCode(2);
			setSpace(5);
		}
		else if(this.slaveLevel < 100) {
			setLevelCode(3);
			setSpace(20);
		}
		else if(this.slaveLevel >= 100) {
			setLevelCode(4);
			setSpace(100);
		}
	}
	
	
	
}
