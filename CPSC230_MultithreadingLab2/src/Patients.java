
public class Patients {

	private boolean beingTreated = false;
	private long timeTreated;
	public String[] pieceOfEquipment;
	
	public Patients (long timeTreated, String[] pieceOfEquipment) {
		this.timeTreated = timeTreated;
		this.pieceOfEquipment = pieceOfEquipment;
	}
	
	public void setBeingTreated (boolean beingTreated) {
		this.beingTreated = beingTreated;
	}
	
	public long getTimeTreated() {
		return timeTreated;
	}
}