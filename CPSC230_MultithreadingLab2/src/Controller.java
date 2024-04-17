import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Controller<T> {

	private AllEquipment allEquipment = new AllEquipment();
	private ArrayList<Patients> patient;
	
	// Number of doctors can be 1, 2, 4, or 8
	public int numDoctors = 2;
	
	private Doctors[] doctor = new Doctors[numDoctors];
	
	public static void main(String [] args) {
		Controller controller = new Controller();
		controller.go();
	}
	
	public void go() {
		readInFile();
		long startTime = System.currentTimeMillis();
		loadDoctors();
		helpPatients();
		end();
		System.out.println("All patients have been helped. Total time: " + (System.currentTimeMillis() - startTime));
	}
	
	private void end() {
		for (int i = 0; i < numDoctors; i++) {
			try {
				doctor[i].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void helpPatients() {
		for (int i = 0; i < numDoctors; i++) {
			doctor[i].start();
		}
	}
	
	private void loadDoctors() {
		for (int i = 0; i < numDoctors; i++) {
			doctor[i] = new Doctors(this.allEquipment, patient, i);
		}
	}
	
	public void readInFile() {
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			try {
				Scanner scanner = new Scanner(file);
				int different_equipments = scanner.nextInt();
				scanner.nextLine();
				for(int i=0; i < different_equipments; i++) {
					String equipmentInfo = scanner.nextLine();
					int equipmentAmount = Integer.parseInt(equipmentInfo.split(" ")[1]);
					String equipmentName = equipmentInfo.split(" ")[0];
					System.out.println("Name: "+equipmentName + ", Number: "+equipmentAmount);
					
					allEquipment.addEquipmentItem(equipmentName, equipmentAmount);
				}
				int numberOfPatients = Integer.parseInt(scanner.nextLine());
				patient = new ArrayList<>();
				for(int i=0; i < numberOfPatients; i++) {
					long treatingTime = Long.parseLong(scanner.nextLine());
					String[] equipmentItems = (scanner.nextLine()).split(" ");
					patient.add(new Patients(treatingTime, equipmentItems));
				}
			} 
			catch (FileNotFoundException e) {
				System.err.println("File not found: " + e.getMessage());
			}
		}
	}
}