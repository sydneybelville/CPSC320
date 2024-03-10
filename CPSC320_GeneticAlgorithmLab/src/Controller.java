import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Controller {

	// Create population instance, then create the initial population inside of that instance
	private Population population = new Population();
	private int chromosomeNumber = 100;
	private ArrayList<int[]> points = new ArrayList <int[]>();
	
	public static void main (String [] args) {
		Controller controller = new Controller();
		controller.go();
	}
	
	
	public void go() {
		getPoints();
		generateChromosome();
		LineChromosome bestChromosome = null;
		
		// evaluates the current fitness of the population and breeds a new population
		for (int i = 0; i < 100000; i++) {
			// must cast LineChromosome to make sure it returns to LineChromosome instead of BinaryChromosome
			bestChromosome = (LineChromosome)population.evaluate();
			double bestFitness = bestChromosome.getFitness();
			System.out.println("Generation number " + i + "'s fitness is: " + bestFitness);
			if (bestFitness >= 0.999) {
				System.out.println("The best fitness for all generations: " + bestFitness);
				break;
			}
			population.breed();
		}
	}
	
	
	public void getPoints() {
		File file = null;
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextInt()) {
				int point[] = new int[2];
				point[0] = scan.nextInt();
				point[1] = scan.nextInt();
				points.add(point);
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
	}
	
	
	public void generateChromosome() {
		for (int i = 0; i < chromosomeNumber; i++) {
			LineChromosome chromosome = new LineChromosome(51, points);
			chromosome.randomize();
			while (chromosome.getFitness() == 0) {
				chromosome.randomize();
			}
			population.addChromosome(chromosome);
		}
	}
}
