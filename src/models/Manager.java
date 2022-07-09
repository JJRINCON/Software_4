package models;

import exceptions.RepeatedNameException;

import java.util.ArrayList;


public class Manager {

	private ArrayList<Partition> partitions;
	
	public Manager() {
		partitions = new ArrayList<>();
	}
	
	public void addPartition(String name, int size) {
		partitions.add(new Partition(name, size));
	}
	
	public boolean editPartition(String name, String newName, int size) {
		Partition partition = searchPartition(name);
		if (partition!=null) {
			partition.setName(newName);
			partition.setSize(size);
			return true;
		}else {
			return false;
		}
	}
	
	public Partition searchPartition(String name) {
		for (Partition partition : partitions) {
			if (partition.getName().equalsIgnoreCase(name)) {
				return partition;
			}
		}
		return null;
	}

	public void verifyPartitionName(String partitionName) throws RepeatedNameException {
		for(Partition partition : partitions){
			if (partition.getName().equalsIgnoreCase(partitionName)){
				throw new RepeatedNameException(partitionName);
			}
		}
	}

	public void verifyProcessName(String processName) throws RepeatedNameException {
		for(Partition partition : partitions){
			partition.verifyProcessName(processName);
		}
	}

	public boolean deletePartition(String name) {
		Partition partition = searchPartition(name);
		if(partition != null) {
			partitions.remove(partition);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean addProcess(String partitionName, String processName,int time,int size, boolean isLocked) {
		Partition partition = searchPartition(partitionName);
		if (partition!=null) {
			partition.addProcess(new MyProcess(processName, time, size, isLocked));
			return true;
		}else {
			return false;
		}
	}
	
	public boolean editProcess(String partitionName, String processName,String processNewName,int time,int size, boolean isLocked) {
		Partition partition = searchPartition(partitionName);
		if (partition!=null) {
			partition.editProcess(processName, processNewName, time,size, isLocked);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteProcess(String partitionName, String processName) {
		Partition partition = searchPartition(partitionName);
		if(partition != null) {
			partition.deleteProccess(processName);
			return true;
		}else {
			return false;
		}
	}

	public void initSimulation(){
		for(Partition partition : partitions){
			partition.startSimulation();
		}
	}

	public MyProcess searchProcess(String partitionName, String processName){
		for(Partition partition : partitions){
			if(partition.getName().equals(partitionName)){
				MyProcess process = partition.search(processName);
				if(process != null){
					return process;
				}
			}
		}
		return null;
	}

	public ArrayList<Partition> getPartitions() {
		return partitions;
	}
}
