import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ParallelMergesort extends RecursiveTask<Integer>{

	private final int[] array;
	private final int lowerNumber;
	private final int higherNumber;
	private static final int arraySize = 200000000;
	private static final int threshold = 10000;
	
	
	public ParallelMergesort (int[] array, int lowerNumber, int higherNumber) {
		this.array = array;
		this.lowerNumber = lowerNumber;
		this.higherNumber = higherNumber;
	}
	
	public static void main(String [] args) {
		int[] array = generateRandomArray(arraySize);
		ForkJoinPool pool = new ForkJoinPool();
		ParallelMergesort mergeSort = new ParallelMergesort(array, 0, arraySize);
		int numbersOutOfOrder = pool.invoke(mergeSort);
		System.out.println("Numbers out of order: " + numbersOutOfOrder);
		mergeSort.verifySort(array);
	}
	
	private static int[] generateRandomArray (int arraySize) {
		int[] array = new int[arraySize];
		Random random = new Random();
		for (int i = 0; i < arraySize; i++) {
			array[i] = random.nextInt();
		}
		return array;
	}

	@Override
	protected Integer compute() {
		if (higherNumber - lowerNumber <= threshold) {
			Arrays.sort(array, lowerNumber, higherNumber);
			return 0;
		}
		else {
			int middle = lowerNumber + (higherNumber - lowerNumber) / 2;
			ParallelMergesort left = new ParallelMergesort (array, lowerNumber, middle);
			ParallelMergesort right = new ParallelMergesort (array, middle, higherNumber);
			invokeAll(left, right);
			return merge(array, lowerNumber, middle, higherNumber);
		}
	}
	
	private void verifySort (int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				System.err.println("Array is not sorted at: " + i);
			}
		}
		System.out.println("Array is sorted!");
	}
	
	private int merge(int[] array, int lowerNumber, int middle, int higherNumber) {
		int[] temporaryArray = Arrays.copyOfRange(array, lowerNumber, middle);
		int i = 0;
		int j = middle;
		int k = lowerNumber;
		int numbersOutOfOrder = 0;
		
		while (i < temporaryArray.length && j < higherNumber) {
			if (temporaryArray[i] <= array[j]) {
				array[k++] = temporaryArray[i++];
			}
			else {
				array[k++] = array[j++];
				numbersOutOfOrder = (numbersOutOfOrder + middle - (lowerNumber + i));
			}
		}
		while (i < temporaryArray.length) {
			array[k++] = temporaryArray[i++];
		}
		return numbersOutOfOrder;
	}
}
