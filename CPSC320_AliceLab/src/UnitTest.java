import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	@Test
	public void test() {
		
	}
	
	
	
	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}
