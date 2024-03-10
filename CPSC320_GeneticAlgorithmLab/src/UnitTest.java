import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UnitTest {

	@Test
	public void testGenerateChromosome() {
        Population population = new Population();
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{2,4});
        Chromosome chromosome = new LineChromosome(51, points);
        population.addChromosome(chromosome);
        
        verify(population.chromosomes.size() == 1, "Expected: Chromosome size should only have one chromosome,  Result: " + population.chromosomes.size());
	}
	
	@Test
	public void testGetFitness() {
		Population population = new Population();
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{1,1});
        points.add(new int[]{5,5});
        points.add(new int[]{9,9});
        LineChromosome bestChromosome = new LineChromosome(51, points);
        LineChromosome testChromosome1 = new LineChromosome(51, points);
        testChromosome1.randomize();
        LineChromosome testChromosome2 = new LineChromosome(51, points);
        testChromosome2.randomize();
        bestChromosome.bits = new int[]{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        verify(bestChromosome.getFitness() == 1.0, "Expected: BestChromosome fitness should be 1.0,  Result: " + bestChromosome.getFitness());
        
        population = new Population();
        points = new ArrayList<>();
        points.add(new int[]{1,1});
        points.add(new int[]{5,5});
        points.add(new int[]{9,9});
        bestChromosome = new LineChromosome(51, points);
        testChromosome1 = new LineChromosome(51, points);
        testChromosome1.randomize();
        testChromosome2 = new LineChromosome(51, points);
        testChromosome2.randomize();
        bestChromosome.bits = new int[]{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        verify(bestChromosome.getFitness() != 1.0, "Expected: BestChromosome fitness should not be 1.0,  Result: " + bestChromosome.getFitness());
	}
	
	@Test
	public void testEvaluate() {
        Population population = new Population();
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{1,1});
        points.add(new int[]{5,5});
        points.add(new int[]{9,9});
        LineChromosome bestChromosome = new LineChromosome(51, points);
        LineChromosome testChromosome1 = new LineChromosome(51, points);
        testChromosome1.randomize();
        LineChromosome testChromosome2 = new LineChromosome(51, points);
        testChromosome2.randomize();
		bestChromosome.bits = new int[] {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        population.addChromosome(testChromosome1);
        population.addChromosome(testChromosome2);
        population.addChromosome(bestChromosome);
        
        verify (population.evaluate() == bestChromosome, "Expected: evaluation should return the bestChromosome,  Result: " + population.evaluate());
        
        population = new Population();
        points = new ArrayList<>();
        points.add(new int[]{1,1});
        points.add(new int[]{5,5});
        points.add(new int[]{9,9});
        LineChromosome worstChromosome = new LineChromosome(51, points);
        testChromosome1 = new LineChromosome(51, points);
        testChromosome1.randomize();
        testChromosome2 = new LineChromosome(51, points);
        testChromosome2.randomize();
        worstChromosome.bits = new int[]{1,1,1,1,1,1,1,1,0,0,1,1,0,1,0,0,1,1,0,0,1,0,0,0,1,0,0,1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        population.addChromosome(testChromosome1);
        population.addChromosome(testChromosome2);
        population.addChromosome(worstChromosome);
        
        verify (population.evaluate() != worstChromosome, "Expected: evaluation should not return the worstChromosome,  Result: " + population.evaluate());
	}
	
	@Test
	public void testBreed() {
        Population population = new Population();
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{1,1});
        points.add(new int[]{5,5});
        points.add(new int[]{9,9});
        LineChromosome c1 = new LineChromosome(51, points);
        c1.randomize();
        LineChromosome c2 = new LineChromosome(51, points);
        c2.randomize();
        LineChromosome c3 = new LineChromosome(51, points);
        c3.randomize();
        LineChromosome c4 = new LineChromosome(51, points);
        c4.randomize();
        LineChromosome c5 = new LineChromosome(51, points);
        c5.randomize();
        LineChromosome c6 = new LineChromosome(51, points);
        c6.randomize();
        LineChromosome c7 = new LineChromosome(51, points);
        c7.randomize();
        LineChromosome c8 = new LineChromosome(51, points);
        c8.randomize();
        population.addChromosome(c1);
        population.addChromosome(c2);
        population.addChromosome(c3);
        population.addChromosome(c4);
        population.addChromosome(c5);
        population.addChromosome(c6);
        population.addChromosome(c7);
        population.addChromosome(c8);
        Population oldPopulation = new Population();
        oldPopulation.addChromosome(c1);
        oldPopulation.addChromosome(c2);
        oldPopulation.addChromosome(c3);
        oldPopulation.addChromosome(c4);
        oldPopulation.addChromosome(c5);
        oldPopulation.addChromosome(c6);
        oldPopulation.addChromosome(c7);
        oldPopulation.addChromosome(c8);
        population.breed();
        boolean oneDifferent = false;
        for (int i = 0; i < population.chromosomes.size(); i++) {
            if (!population.chromosomes.get(i).equals(oldPopulation.chromosomes.get(i))) {
                oneDifferent = true;
                break;
            }
        }
        
        verify (oneDifferent == false, "Expected: chromosomes in the list should be the same,  Result: " + oneDifferent);
	}
	
	@Test
	public void testGetABC() {
        ArrayList<int[]> points = new ArrayList<>();
        LineChromosome chromosome = new LineChromosome(51, points);
        chromosome.bits = new int[]{1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,1};
        
        verify (chromosome.getA() == 5, "Expected: A should equal 5,  Result: " + chromosome.getA());
        verify (chromosome.getB() == -7, "Expected: B should equal -7,  Result: " + chromosome.getB());
        verify (chromosome.getC() == 22, "Expected: C should equal 22,  Result: " + chromosome.getC());
	}

	
	private void verify (boolean correct, String error) {
		if (!correct) fail(error);
	}
}
