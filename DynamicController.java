import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class DynamicController {

	public static void main (String [] args) {
		 
		 Scanner scanner = null;
		 DynamicController controller = new DynamicController();
		 
		 // JFileChooser gets the file containing the integers
		 JFileChooser chooser = new JFileChooser();
		 
		 int returnVal = chooser.showOpenDialog(null);
		 
		 if(returnVal == JFileChooser.APPROVE_OPTION) {
			 File selectedFile = chooser.getSelectedFile();
			 
			 try { 
				 scanner = new Scanner(selectedFile);
			 }
			 
			 catch (FileNotFoundException e) {
				 System.out.println("File not found.");
			 }
		 }
		 
		 int index = 1;
		 
		 // Looks at each text from the file
		 while (scanner.hasNext()) {
			 int number_paths = controller.getPaths(controller.makeArray(scanner));
			 System.out.println("Slope " + index + " has " + number_paths + " runs.");
			 index++;
		 }
	}
	
	
	// Converts input to an adjacency matrix
	public int[][] makeArray (Scanner input) {
		int nodes = input.nextInt();
		int legs = input.nextInt();
		
		int[][] array = new int[nodes + 2][nodes + 2];
		
		for (int i = 0; i < legs; i++) {
			int from = input.nextInt();
			int to = input.nextInt();
			
			array[from][to]++;
		}
		
		return array;
	}
	
	
	// Determines the number of paths from 1 to 0
	public int getPaths (int[][] array) {
		// row, column
		int[][] trackingArray = new int[array.length + 1][array.length];
		
		// iterating through rows (from)
		for (int i = 1; i < array.length; i++) {
			// iterating through columns (to)
			for (int j = 1; j < array.length; j++) {
				int multiplier = trackingArray[array.length][i];
				
				if (multiplier == 0) {
					multiplier = 1;
				}
				
				// Computes paths and updates trackingArray
				trackingArray[i][j] = array[i][j] * multiplier;
				trackingArray[array.length][j] += trackingArray[i][j];
			}
		}
		
		// iterating through rows, but with zeros
		for (int i = 1; i < array.length; i++) {
			int multiplier = trackingArray[array.length][i];
			
			if (multiplier == 0) {
				multiplier = 1;
			}
			
			trackingArray[i][0] = array[i][0] * multiplier;
			trackingArray[array.length][0] += trackingArray[i][0];
		}
		
		return trackingArray[array.length][0];
	}
}
