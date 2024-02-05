import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testMakeArray() {
		 DynamicController controller = new DynamicController();
		 String test_numbers = "2 5 1 2 1 3 2 3 2 0 3 0";
		 Scanner scan = new Scanner(test_numbers);
		 int[][] array = controller.makeArray(scan);
		 
		 int[][] test_array = {{0, 0, 0, 0}, {0, 0, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 0}};
		 
		 for (int i = 0; i < array.length; i++) {
			 for (int j = 0; j <array.length; j++) {
				 verify (array[i][j] == test_array[i][j], "Expected: array should equal test_array,  Result: " + i + ", " + j);
			 }
		 }
	}	
	
	@Test
	public void testGetPaths() {
		 DynamicController controller = new DynamicController();
		 int[][] test_array = {{0, 0, 0, 0, 0}, {0, 0, 2, 1, 0}, {0, 0, 0, 1, 2}, {2, 0, 0, 0, 0}, {2, 0, 0, 0, 0}};
		 int result = controller.getPaths(test_array);
		 
		 verify (result == 14, "Expected: result should be equal to 14 runs,  Result: " + result);
		 
		 
		 int[][] test_array2 = {{0, 0}, {2, 0}};
		 int result2 = controller.getPaths(test_array2);
		 
		 verify (result2 == 2, "Expected: result should be equal to 2 runs,  Result: " + result2);
		 
		 
		 int[][] test_array3 = {{0, 0, 0}, {1, 0, 2}, {2, 0, 0}};
		 int result3 = controller.getPaths(test_array3);
		 
		 verify (result3 == 5, "Expected: result should be equal to 5 runs,  Result: " + result3);
		 
		 
		 int[][] test_array4 = {{0, 0, 0, 0}, {0, 0, 1, 1}, {2, 0, 0, 1}, {1, 0, 0, 0}};
		 int result4 = controller.getPaths(test_array4);
		 
		 verify (result4 == 4, "Expected: result should be equal to 4 runs,  Result: " + result4);
		 
	}

	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}
