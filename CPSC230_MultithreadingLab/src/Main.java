import java.util.Random;

public class Main {
	
	// Number of threads include 1, 2, 4, 10
	private static int numThreads = 1;
	
	// Values of N include 10, 100, 1000, 10000
	private static int arraySize = 10000;
	
	public static void main (String [] args) {
		// Creates the random array 
		int[] array = new int[arraySize];
		Random random = new Random();
		for (int i = 0; i < arraySize; i++) {
			array[i] = random.nextInt(1, 101);
		}
		
		Threads[] threadArray = new Threads[numThreads];
		
		// Calculating how many numbers out of the array a thread can take
		int numbersPerThread = arraySize / numThreads;
		int remainingNumbers = arraySize % numThreads;
		int startingPoint = 0;
		int sum = 0;
		
		// for loop for creating threads
		for (int i = 0; i < numThreads; i++) {
			// Calculate startingPoint and endingPoint using N and num_threads
			int endingPoint = startingPoint + numbersPerThread;
			if (i < remainingNumbers) {
				endingPoint++;
			}
			
			Threads thread = new Threads(array, startingPoint, endingPoint);
			threadArray[i] = thread;
			startingPoint = endingPoint;
		}
		
		// for loop for starting threads
		for (Threads thread : threadArray) {
			thread.start();
		}
		
		// for loop for joining them (time)
		for (Threads thread : threadArray) {
			try {
				thread.join();
				sum += thread.sum();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// for loop to find the longest time and calculate the efficiency factor
		long longestTime = 0;
		for (Threads thread : threadArray) {
			if (thread.time() > longestTime) {
				longestTime = thread.time();
			}
		}
		
		// for loop to add all the sums of the threads
		for (Threads thread : threadArray) {
			System.out.println("Thread runtime: " + thread.time());
			System.out.println("Thread efficiency factor: " + (long)(longestTime / thread.time()));
		}
		
		System.out.println("Total sum: " + sum);
	}
}
