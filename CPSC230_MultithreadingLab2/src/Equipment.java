
public class Equipment {

	public String equipmentName;
	private int equipmentAmount;
	
	public Equipment (String equipmentName, int equipmentAmount) {
		this.equipmentName = equipmentName;
		this.equipmentAmount = equipmentAmount;
		System.out.println("Starting number of " + equipmentName + ": " + equipmentAmount);
	}
	
	public int equipmentAmount() {
		return this.equipmentAmount;
	}
	
	public void take() {
		this.equipmentAmount--;
		System.out.println("Take: There are now "+ equipmentAmount + " " + equipmentName);
	}
	public void add() {
		this.equipmentAmount++;
		System.out.println("Add: There are now "+ equipmentAmount + " " + equipmentName);
	}
	
}
