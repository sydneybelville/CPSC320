import java.util.ArrayList;

public class Doctors extends Thread{

	private AllEquipment allEquipment;
	ArrayList<Equipment> toolsInUse;
	private ArrayList<Patients> patient;
	private int doctorNum;
	
	public Doctors(AllEquipment allEquipment, ArrayList<Patients> patient, int doctorNum) {
		this.allEquipment = allEquipment;
		this.patient = patient;
		this.doctorNum = doctorNum;
	}
	
	public void run() {
		while(patient.size() > 0) {
			Patients patients = patient.get(0);
			patient.remove(0);
			System.out.println("Doctor "+ doctorNum + " is helping patient.");
			long startTime = System.currentTimeMillis();
			
			getPatientEquipment(patients);
			healPatient(patients);
			returnEquipment(patients);
			
			
			
			long endTime = System.currentTimeMillis();
			System.out.println(getTime(startTime, endTime));
			}
	}
	
	private void returnEquipment (Patients patient) {
		synchronized (allEquipment) {
			allEquipment.returnAll(patient);
		}
	}
	
	private void healPatient (Patients patient) {
		long startTime = System.currentTimeMillis();
	    long remainingTime = patient.getTimeTreated();

	    while (remainingTime > 0) {
	        try {
	            Thread.sleep(remainingTime);
	        } 
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        long currentTime = System.currentTimeMillis();
	        long elapsedTime = currentTime - startTime;
	        remainingTime = patient.getTimeTreated() - elapsedTime;
	    }
	}
	
	private void getPatientEquipment (Patients patient) {
		synchronized (allEquipment) {
			allEquipment.getAllEquipment(patient);
		}
	}
	
	public long getTime (long startTime, long endTime) {
		return endTime - startTime;
	}
}