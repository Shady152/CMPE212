package a3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;
import princeton.introcs.StdDraw;

public class LineFit {

	/**
	 * Finds the line of best fit to a sequence of points using ordinary least
	 * squares. The returned array consists of the y-intercept followed by
	 * the slope of the best fit line. If {@code a} is the returned array,
	 * then the equation of the line is given by:
	 * 
	 * <pre>
	 * y = a[0] + a[1] * x;
	 * </pre>
	 * 
	 * @param p a list of points to fit a line to
	 * @return an array containing the y-intercept and slope of the best fit line
	 * @throws IllegalArgumentException if {@code p} has fewer than two points
	 * @throws ArithmeticException if the line of best fit is exactly vertical
	 */
	public static double[] lsfit(List<Vector2> p) {
		//Exception handling
		if(p.size() < 1)
			throw new IllegalArgumentException("p has fewer than two points");
		
		//Initializing the 2x2 matrix elements
		double a = p.size();
		double b = 0;
		double c = 0;
		
		//Initializing the y vectors elements
		double y = 0;
		double yx = 0;
		
		//Getting the 2x2 matrix elements and the y vector elements
		for(int i = 0; i < p.size(); i++)
		{
			b += (p.get(i)).x();
			c += Math.pow((p.get(i)).x(),2);
			y += (p.get(i)).y();
			yx += ((p.get(i)).x())*((p.get(i)).y());
		}
		
		//Generating the 2x2 matrix
		Matrix2 matA = new Matrix2(a,b,b,c);
		//Generating the y vector
		Vector2 vectb = new Vector2(y,yx);
		//Solving the system
		Vector2 temp = (matA.inv()).mult(vectb);
		
		//Putting the solution vector into a double array
		double[] ans = {temp.x(),temp.y()};
		
		//Exception handling
		if(ans[1] == Double.POSITIVE_INFINITY)
			throw new ArithmeticException("The line of best fit is vertical");
		
		return ans;
	}

	/**
	 * Plots the specified sequence of points and line.
	 * 
	 * @param p a list of points to plot
	 * @param line the y-intercept and slope of the line to plot
	 */
	public static void plot(List<Vector2> p, double[] line) {
		StdDraw.clear();
		for (Vector2 v : p) {
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.filledCircle(v.x(), v.y(), 0.25);
		}
		double x1 = -10.0;
		double y1 = line[1] * x1 + line[0];
		double x2 = 10.0;
		double y2 = line[1] * x2 + line[0];
		StdDraw.setPenColor(Color.RED);
		StdDraw.line(x1, y1, x2, y2);
	}

	/**
	 * Plots 10 points randomly displaced vertically from the line
	 * {@code y = 0.0 + x} and the line of the best fit. Prints the
	 * slope and intercept of the best fit line to standard output. 
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		StdDraw.setScale(-11, 11);
		List<Vector2> p = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			double x = i * 2 - 10;
			double y = x + (Math.random() - 0.5) * 4;
			p.add(new Vector2(x, y));
		}
		double[] params = lsfit(p);
		System.out.println("slope : " + params[1] + ", intercept : " + params[0]);
		plot(p, params);
	}
}
