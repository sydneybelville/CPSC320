import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UnitTest {
	
	@Test
	public void testMakeHeap() {
		HeapModel model = new HeapModel();
		ArrayList<Integer> testTree = new ArrayList<>();
		testTree.add(1);
		testTree.add(5);
		testTree.add(3);
		testTree.add(7);
		
		model.makeHeap(testTree, 4);
		
		ArrayList<Integer> sortedTestTree = new ArrayList<>();
		sortedTestTree.add(7);
		sortedTestTree.add(5);
		sortedTestTree.add(3);
		sortedTestTree.add(1);
		
		for (int i = 0; i < 4; i++) {
			verify(testTree.get(i) == sortedTestTree.get(i), "Expected: testTree should be equal to sortedTestTree,  Result: " + testTree);
		}
	}
	
	
	@Test
	public void testHeapSort() {
		HeapModel model = new HeapModel();
		ArrayList<Integer> testTree = new ArrayList<>();
		testTree.add(1);
		testTree.add(2);
		
		ArrayList<Integer> sortedTestTree = new ArrayList<>();
		sortedTestTree.add(2);
		sortedTestTree.add(1);
		
		ArrayList<Integer> tree = model.heapSort(testTree);
		
		for (int i = 0; i < 2; i++) {
			verify(tree.get(i) == sortedTestTree.get(i), "Expected: tree should be equal to sortedTestTree,  Result: " + tree);
		}
	}
	
	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}
