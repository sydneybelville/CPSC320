import java.util.ArrayList;

public class LineChromosome extends BinaryChromosome{

	private ArrayList<int[]> points;
	
	public LineChromosome(int numbits, ArrayList<int[]> points) {
		super(numbits);
		this.points = points;
	}
	
	
	@Override
    public double getFitness() {
        // This should be overridden by a subclass to be useful
		double A = getA();
		double B = getB();
		double C = getC();
		// Calculates the distance between the line and all points
		double distance = 0;
		for (int i = 0; i < points.size(); i++) {
			int X = points.get(i)[0];
			int Y = points.get(i)[1];
			// Distance between a point and a line (for every single point)
			distance += (Math.abs((A * X) + (B * Y) + C)) / Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2));
		}
		// Calculate the average distance of all the points to the line
		double averageDistance = (double) distance/points.size();
		// Flip the fitness to 0 being the worst and 1 being the best
		double fitness = 1 / (1 + averageDistance);
        return fitness;
    }
	
	
	@Override
    public Chromosome crossover(Chromosome other) {
        // This should be overridden by a subclass (getFitness will always return 0)
        return crossover ((LineChromosome) other, new LineChromosome (bits.length, points));
    }
	
	
	protected double getA() {
		// First number has 17 bits total
		int numberSign = super.getNumber(0, 0);
		int numerator = super.getNumber(1, 8);
		int denominator = super.getNumber(9, 16);
		if (numberSign == 0) {
			numberSign = -1;
		}
		double A = 0.0;
		if (denominator != 0 && numerator != 0) {
			A = (double) numerator/denominator;
			A = (double) A * numberSign;
		}
		return A;
	}
	
	
	protected double getB() {
		// First number has 17 bits total
		int numberSign = super.getNumber(17, 17);
		int numerator = super.getNumber(18, 25);
		int denominator = super.getNumber(26, 33);
		if (numberSign == 0) {
			numberSign = -1;
		}
		double B = 0.0;
		if (denominator != 0 && numerator != 0) {
			B = (double) numerator/denominator;
			B = (double) B * numberSign;
		}
		return B;
	}
	
	
	protected double getC() {
		// First number has 17 bits total
		int numberSign = super.getNumber(34, 34);
		int numerator = super.getNumber(35, 42);
		int denominator = super.getNumber(43, 50);
		if (numberSign == 0) {
			numberSign = -1;
		}
		double C = 0.0;
		if (denominator != 0 && numerator != 0) {
			C = (double) numerator/denominator;
			C = (double) C * numberSign;
		}
		return C;
	}
	
}
