import java.util.ArrayList;

public class AllEquipment {

	private ArrayList<Equipment> equipment = new ArrayList<>();
	
	public synchronized void getAllEquipment (Patients patient) {
		ArrayList<Equipment> takenEquipment = new ArrayList<>();
        boolean completed = false;
        while(!completed) {
        	for (String itemName : patient.pieceOfEquipment) {
        		Equipment equipment = getEquipment(itemName);
        		if (equipment != null && equipment.equipmentAmount() > 0) {
        			equipment.take();
        			takenEquipment.add(equipment);
        			completed = true;
        		}
        		else {
        			try {
        				for (Equipment take : takenEquipment) {
        					take.add();
        				}
        				takenEquipment.clear();
        				wait();
        				completed = false;
        				break;
        			} 
        			catch (InterruptedException e) {
        				e.printStackTrace();
        			}
        		}
        	}
        }
	}
	
	public synchronized void returnAll (Patients patient) {
		for (String itemName : patient.pieceOfEquipment) {
            Equipment equipment = getEquipment(itemName);
            if (equipment != null) {
                equipment.add();
                notifyAll();
            }
        }
	}
	
	public synchronized void addEquipmentItem (String equipmentName, int equipmentAmount) {
		equipment.add(new Equipment(equipmentName, equipmentAmount));
        notifyAll();
	}
	
	private Equipment getEquipment (String equipmentName) {
		for (Equipment equipment : equipment) {
            if (equipment.equipmentName.equals(equipmentName)) {
                return equipment;
            }
        }
        return null;
	}
}
