import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
	
	protected ArrayList<Chromosome> chromosomes;
	private double mutationRate;
	private Random random = new Random();

	
	public Population () {
		// Holds a collection of Chromosomes, each chromosome represents an individual in the population 
		chromosomes = new ArrayList<>();
		mutationRate = 0.05;
	}
	
	
	public void addChromosome (Chromosome chromosome) {
		// Used to add one chromosome to the population (must be called once per individual in the initial population)
		chromosomes.add(chromosome);
	}
	
	
	public Chromosome evaluate () {
		// Called to calculate the fitness of every individual and returns the chromosome with the highest fitness
		Chromosome highFitness = chromosomes.get(0);
		// Get the fitness of the first chromosome
		double maxFitness = highFitness.getFitness();
		for (Chromosome chromosome : chromosomes) {
			double fitness = chromosome.getFitness();
			// Check if the fitness of the current chromosome is greater than the max fitness so far
			if (fitness > maxFitness) {
				maxFitness = fitness;
				highFitness = chromosome;
			}
		}
		// Return the fittest chromosome
		return highFitness;
	}
	
	
	public void breed () {
		// Creates a new population by breeding the most fit individuals (generate the number of tickets for each current individual)
		double leastFitness = leastFitness().getFitness();
		ArrayList<Chromosome> tickets = new ArrayList<>();
		for (int i = 0; i < chromosomes.size(); i++) {
			double currentChromosomeFitness = chromosomes.get(i).getFitness();
			int ticketNumber = (int) (currentChromosomeFitness/leastFitness);
			for (int k = 0; k < ticketNumber; k++) {
				tickets.add(chromosomes.get(i));
			}
		}
		ArrayList<Chromosome> nextGeneration = new ArrayList<>();
		int nextGenerationSize = 0;
		// while new population size != old population size
		while (nextGenerationSize != chromosomes.size()) {
			Chromosome childChromosome;
			do {
				// draw two tickets (parent1, parent2)
				Chromosome parentOne = tickets.get(random.nextInt(0, tickets.size() - 1));
				Chromosome parentTwo = tickets.get(random.nextInt(0, tickets.size() - 1));
				// child = parent1.crossover(parent2)
				childChromosome = parentOne.crossover(parentTwo);
				//child.mutate(mutationRate)
				childChromosome.mutate(mutationRate);
				
			}
			// Goes through the do, then if childChromosome is equal to 0, it goes back up to do
			while (childChromosome.getFitness() == 0);
			//add child to new population
			nextGeneration.add(childChromosome);
			nextGenerationSize++;
		}
		chromosomes = nextGeneration;
	}
	
	
	public Chromosome leastFitness() {
		// Called to calculate the fitness of every individual and returns the chromosome with the worst fitness
		Chromosome leastFitness = chromosomes.get(0);
		// Get the fitness of the first chromosome
		double minFitness = leastFitness.getFitness();
		for (Chromosome chromosome : chromosomes) {
			double fitness = chromosome.getFitness();
			// Check if the fitness of the current chromosome is less than the min fitness so far
			if (fitness < minFitness) {
				minFitness = fitness;
				leastFitness = chromosome;
			}
		}
		// Returns the least fit chromosome
		return leastFitness;
	}
	
	
	public void setMutationRate (double rate) {
		// Sets the mutation rate (checked during breeding after an offspring has been created to see if that offspring needs mutated)
		mutationRate = rate;
	}
}
