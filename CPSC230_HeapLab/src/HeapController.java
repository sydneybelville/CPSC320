import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class HeapController {

	public static void main (String [] args) {
		
		 HeapModel heapModel = new HeapModel();
		 
		 Scanner scanner = null;
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
			 
			 heapModel.makeTree(scanner);
			 
			 System.out.println("Original Tree: " + heapModel.getTree());
			 
			 heapModel.heapSort(heapModel.getTree());
			 
			 System.out.println("Sorted Tree: " + heapModel.getTree());
		 }
	}
}
