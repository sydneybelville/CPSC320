import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testMakeArray() {
		 DynamicController controller = new DynamicController();
		 String test_numbers = "2 4\n1 2 1 3 2 3 2 0 3 0";
		 Scanner scan = new Scanner(test_numbers);
		 int[][] array = controller.makeArray(scan);
		 
		 int[][] test_array = {{0, 0, 0, 0}, {0, 0, 1, 1}, {0, 0, 0, 2}, {0, 0, 0, 1}};
				 
		 verify (test_numbers == test_array.toString(), "Expected: test_numbers should equal test_array  Result: " + test_numbers);
	}	
	
	@Test
	public void testGetPaths() {
		 DynamicController controller = new DynamicController();
		 
		 
	}

	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}
