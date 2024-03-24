
public class Threads extends Thread{

	private int[] array;
	private int startingPoint;
	private int endingPoint;
	private int number = 0;
	private long runTime = 0;
	
	public Threads(int[] array, int startingPoint, int endingPoint) {
		this.array = array;
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
	}
	
	@Override
	public void run () {
		long startTime = System.nanoTime();
		for (int i = startingPoint; i < endingPoint; i++) {
			number += array[i];
		}
		long endTime = System.nanoTime();
		runTime = endTime - startTime;
	}
	
	public int sum() {
		return number;
	}
	
	public long time() {
		return runTime;
	}
}
