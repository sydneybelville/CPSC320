import java.util.ArrayList;
import java.util.Scanner;

public class HeapModel {

	private ArrayList<Integer> tree;
	
	// This method reads input and creates the tree ArrayList
	public void makeTree (Scanner input) {
		ArrayList<Integer> tree = new ArrayList<>();
		// Reads integers from the input and adds them to the tree
		while (input.hasNextInt()) {
			tree.add(input.nextInt());
		}
		
		this.tree = tree;
	}
	
	
	// This method is a getter to retrieve the tree ArrayList
	public ArrayList<Integer> getTree () {
		return tree;
	}

	
	// This method converts a complete binary tree into a heap
	public void makeHeap (ArrayList<Integer> tree, int number) {
		// Start from the last non-leaf node and move up
		for (int i = number / 2 - 1; i >= 0; i--) {
			// Assume the current node is the largest
			int largestNum = i;
			int leftChild = 2 * i + 1;
			int rightChild = 2 * i + 2;
			
			// Check if the left child is larger than the current largest
			if (leftChild < number && tree.get(leftChild) > tree.get(largestNum)) {
				largestNum = leftChild;
			}
			
			// Check if the right child is larger than the current largest
			else if (rightChild < number && tree.get(rightChild) > tree.get(largestNum)) {
				largestNum = rightChild;
			}
			
			// If the largest value is not the current node, swap
			if (largestNum != i) {
				swapElements(tree, i, largestNum);
			}
		}
	}
	
	
	// This method swaps the elements in the tree ArrayList
	private void swapElements(ArrayList<Integer> tree, int indexSpot1, int indexSpot2) {
		// Performs the swap
		int temporarySpot = tree.get(indexSpot1);
		tree.set(indexSpot1, tree.get(indexSpot2));
		tree.set(indexSpot2, temporarySpot);
	}
	
	
	// This method performs the actual heap sort on the tree ArrayList
	public ArrayList<Integer> heapSort (ArrayList<Integer> tree) {
		// A new ArrayList to store the sorted elements
		ArrayList<Integer> sortedTree = new ArrayList<>();
		for (int i = tree.size(); i >= 0; i--) {
			makeHeap(tree, i);
			// This swaps the root with the last element
			swapElements(tree, 0, i);
			sortedTree.add(tree.get(tree.size() - 1));
			// This removes the last element from the tree ArrayList
			tree.remove(tree.size() - 1);
		}
		
		return sortedTree;
	}
}
