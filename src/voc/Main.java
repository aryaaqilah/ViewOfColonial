package voc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	private ArrayList<Job> jobs = new ArrayList<>();
	private ArrayList<Slave> slaves = new ArrayList<>();
	private ArrayList<Slave> userSlaves = new ArrayList<>();
	private ArrayList<Voc> vocs = new ArrayList<>();
	private LoginFrame loginform;
	private Voc voc;
	private profileFrame profile;
	private SlaveMarket market;
	private PunishFrame punishment;
	private SlaveList slavelist;
	private Navigation nav;
	private Register reg;
	private slaveListFrame slavelistframe;
	private HealFrame healframe;
	private SlaveListForHeal slfh;
	
//	private String username;
//	private String password;
	
	public void readFile() {
		File fileUser = new File("src/voc/database/dummyUser.txt");
		
		try {
			Scanner scanUser = new Scanner(fileUser);
			String username;
			String password;
			String[] raw;
			while(scanUser.hasNextLine()) {
				raw = scanUser.nextLine().split("#");
				username = raw[0];
				password = raw[1];
				vocs.add(new Voc(username, password));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No user");
		}
	}
	
	public void writeFile() {
		System.out.println(voc.getUsername());
		String filePath = "src/voc/database/";
		filePath = filePath.concat(voc.getUsername());
		System.out.println("ppppppppp");
		filePath = filePath.concat(".txt");
		String toWrite;
		
		 try {
	            FileWriter fileWriter = new FileWriter(filePath);
	            toWrite = voc.getUsername();
	            toWrite = toWrite.concat("#");
	            fileWriter.write(toWrite);
	            
	            toWrite = voc.getPassword();
	            toWrite = toWrite.concat("#");
	            fileWriter.write(toWrite);
	            
	            toWrite = Double.toString(voc.getMoney());
	            fileWriter.write(toWrite);
	            fileWriter.write("\n");
	            
	            for (Job job : jobs) {
					toWrite = Double.toString(job.getProgress());
					toWrite = toWrite.concat("#");
					toWrite = toWrite.concat(Integer.toString(job.getLevel()));
//					if(!job.getName().equals(jobs.get(2).getName())) {						
//						toWrite = toWrite.concat("#");
//					}
		            fileWriter.write(toWrite);
		            fileWriter.write("\n");
				}
//	            fileWriter.write("\n");
	            
	            for (Slave slave : userSlaves) {
					toWrite = slave.getName();
					toWrite = toWrite.concat("#");
					toWrite = toWrite.concat(Integer.toString(slave.getHp()));
					toWrite = toWrite.concat("#");
					toWrite = toWrite.concat(Integer.toString(slave.getSlaveLevel()));
					toWrite = toWrite.concat("#");
					toWrite = toWrite.concat(Integer.toString(slave.getJobCode()));
					toWrite = toWrite.concat("\n");
					fileWriter.write(toWrite);
				}
	            
	            fileWriter.close();
	            
	            System.out.println("done");
	        } catch (IOException e) {
	        	System.out.println("hahhh");
	            System.out.println(e.getMessage());
	        }
//		loginform.closing();
		System.out.println("jalan ni bos");
		return;
	}
	
	public void initJob() {
		jobs.add(new Job("Cultuurstelsel", 10));
		jobs.add(new Job("Build A Bridge", 2));
//		jobs.add(new Job("Cultuur Stelsel", 500));
	}
	
	public void printdoang() {
		System.out.println("ngeprint");
	}
	
	public int checkUser(String username, String password) {
		for (Voc voc : vocs) {
			String name;
			if(voc.getUsername().equals(username) && voc.getPassword().equals(password)) {
				return 1;
			}
		}
		return 0;
	}
	
	public void readData(String username) {
		String path = "src/voc/database/";
		path = path.concat(username);
		path = path.concat(".txt");
		System.out.println(path);
		File file = new File(path);
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String name;
			int hp;
//			double money;
			int jobCode;
			int level;
			
			raw = scan.nextLine().split("#");
			String username2;
			String password;
			double moneyVoc;
			username2 = raw[0];
			password = raw[1];
			moneyVoc = Double.parseDouble(raw[2]);
//			vocs.add(new Voc(username, password, moneyVoc));
			
			raw = scan.nextLine().split("#");
			double progress1 = Double.parseDouble(raw[0]);
			int level1 = Integer.parseInt(raw[1]);
			raw = scan.nextLine().split("#");
			double progress2 = Double.parseDouble(raw[0]);
			int level2 = Integer.parseInt(raw[1]);
			
			jobs.get(0).setProgress(progress1);
			jobs.get(0).setLevel(level1);
			jobs.get(1).setProgress(progress2);
			jobs.get(1).setLevel(level2);
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				name = raw[0];
				hp = Integer.parseInt(raw[1]);
//				jobC = Double.parseDouble(raw[2]);
				level = Integer.parseInt(raw[2]);
				jobCode = Integer.parseInt(raw[3]);
				Slave slave = new Slave(name, hp, level, jobCode);
				
				System.out.println(slave.getJobCode());
				
				if(slave.getJobCode() == 1){
					jobs.get(0).getSlaves().add(slave);
					jobs.get(0).countProgress();
					jobs.get(0).setValueSpace();
//					jobs.get(0).setLevel(level2);
				}
				else if(slave.getJobCode() == 2){
					jobs.get(1).getSlaves().add(slave);
					jobs.get(1).countProgress();
					jobs.get(1).setValueSpace();
				}
//				else if(slave.getJobCode() == 3){
//					jobs.get(2).getSlaves().add(slave);
//					jobs.get(2).countProgress();
//				}
				
				userSlaves.add(slave);
			}
			voc = new Voc(username2, password, moneyVoc, userSlaves);
			System.out.println("DONE2");

		} catch (FileNotFoundException e) {
			System.out.println("NO DATA");
		}
//		loginform.dispose();
		System.out.println("ada filenya");
		profile = new profileFrame(voc, this);
		profile.setVisible(true);
	}
	
	public void readSlaves() {
		File fileUser = new File("src/voc/database/dummySlave.txt");
		
		try {
			Scanner scanUser = new Scanner(fileUser);
			String name;
			int hp;
			int level;
			int jobcode;
			String[] raw;
			while(scanUser.hasNextLine()) {
				raw = scanUser.nextLine().split("#");
				name = raw[0];
				hp = Integer.parseInt(raw[1]);
				level = Integer.parseInt(raw[2]);
				jobcode = Integer.parseInt(raw[3]);
				
				Slave slave = new Slave(name, hp, level, jobcode);
//				slave.setLevelNSpace(level)
				System.out.println(slave.getName());
				slaves.add(slave);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No user");
		}
	}
	
	public void createUser(String username, String password) {
//		System.out.println(voc.getUsername());
		String filePath = "src/voc/database/";
		filePath = filePath.concat(username);
//		System.out.println("ppppppppp");
		filePath = filePath.concat(".txt");
		String toWrite;
		
		 try {
	            FileWriter fileWriter = new FileWriter(filePath);
	            toWrite = username;
	            toWrite = toWrite.concat("#");
	            fileWriter.write(toWrite);
	            
	            toWrite = password;
	            toWrite = toWrite.concat("#");
	            fileWriter.write(toWrite);
	            
	            toWrite = Double.toString(100000);
	            fileWriter.write(toWrite);
	            fileWriter.write("\n");
	            
//	            for (Job job : jobs) {
//					toWrite = Double.toString(job.getProgress());
//					toWrite = toWrite.concat("#");
//					toWrite = toWrite.concat(Integer.toString(job.getLevel()));
////					if(!job.getName().equals(jobs.get(2).getName())) {						
////						toWrite = toWrite.concat("#");
////					}
//		            fileWriter.write(toWrite);
//		            fileWriter.write("\n");
//				}
//	            fileWriter.write("\n");
	            
	            toWrite = Double.toString(0);
	            toWrite = toWrite.concat("#");
	            fileWriter.write(toWrite);
	            toWrite = Integer.toString(1);
	            fileWriter.write(toWrite);
	            fileWriter.write("\n");
	            
	            toWrite = Double.toString(0);
	            toWrite = toWrite.concat("#");
	            fileWriter.write(toWrite);
	            toWrite = Integer.toString(1);
	            fileWriter.write(toWrite);
//	            fileWriter.write("\n");
	            
	            fileWriter.close();
	            
	            System.out.println("done");
	        } catch (IOException e) {
	        	System.out.println("hahhh");
	            System.out.println(e.getMessage());
	        }
//		loginform.closing();
		System.out.println("jalan ni bos");
		readData(username);
	}
	
	public void openProfile() {
//		profile.setVoc(voc);
		profile.setGoldLabel(voc);
		System.out.println("MASUK OPEN PROFILE");
		profile.setVisible(true);
	}
	
	public void openProject() {
		projectFrame projFrame = new projectFrame(voc, this, jobs);
	}
	
	public void openSlaveList() {
		slavelist = new SlaveList(voc, this);
	}
	
	public void openJustSlaveList() {
		slavelistframe = new slaveListFrame(voc, this);
	}
	
	public void openMarket() {
		market = new SlaveMarket(slaves, this, jobs, voc);
	}
	
	public void openPunish() {
		punishment = new PunishFrame(voc, this);
	}
	
	public void openNav() {
		nav = new Navigation(this);
	}
	
	public void openLogin() {
		loginform = new LoginFrame(slaves, this);
	}
	
	public void openRegister() {
		reg = new Register(this);
	}
	
	public void openHeal() {
		slfh = new SlaveListForHeal(voc, this);
	}
	
	public Main() {
		initJob();
		readSlaves();
		readFile();
		openNav();
//		loginform = new LoginFrame(slaves, this);
//		System.out.println(voc.getUsername());
//		writeFile();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}

	public Voc getVoc() {
		return voc;
	}

	public void setVoc(Voc voc) {
		this.voc = voc;
	}
	
	

}
