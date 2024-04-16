import static org.junit.Assert.*;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testParalleMergesort() {
		Random random = new Random();
		int[] array = new int[100000];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(1000);
		}
		ParallelMergesort mergeSort = new ParallelMergesort(array, 0, array.length);
		ForkJoinPool.commonPool().invoke(mergeSort);
		
		
		for (int i = 0; i < array.length - 1; i++) {
			verify (array[i] <= array [i + 1], "Array is not sorted");
		}
	}
	
	
	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}
