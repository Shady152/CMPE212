package a5;

/**
 * Root finding via Newton's method.
 *
 */
public class Newton implements RootFinder1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION

	private Function1 dfun; // derivative of the function
	public static final double TOL = 1.0E-6; // tolerance

	// constructor that Specifies the derivative function df for the main function
	public Newton(Function1 df) {
		this.dfun = df;
	}

	@Override
	public Root root(Function1 f, double a, double b) {
		// exception handling
		if (a >= b)
			throw new IllegalArgumentException("a is larger than b; a should be smaller than b; [a,b]");

		// checks that the function doesn't have the same sign at a and b else there
		// would be not root
		if (f.eval(a) * f.eval(b) > 0)
			return new Root(); // returns Double.NaN

		double x0 = (a + b) / 2; // initial guess
		while (Math.abs(f.eval(x0)) > Newton.TOL) {
			x0 = x0 - (f.eval(x0) / this.dfun.eval(x0)); // update x0 (x_i+1)

			// this is to prevent cases such as 1/x so that the loop doesn't go forever
			if (x0 < a || x0 > b)
				return new Root(); // returns Double.NaN
		}

		if (f.eval(x0) > Newton.TOL)
			return new Root(); // returns Double.NaN
		else {
			Root rootval = new Root(x0); // make the found root at mid a root type object
			return rootval;
		}
	}

	/**
	 * Small example program that finds a root of the sine and cosine function.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		// find the root of sin(x) on the interval [3, 4]
		Function1 sin = new Sine();
		Function1 cos = new Cosine(); // derivative of sin(x)
		Newton n = new Newton(cos);
		Root x0 = n.root(sin, 3.0, 4.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi
		}

		// find the root of cos(x) on the interval [1, 2]
		Function1 negsin = new NegSine();
		Newton n2 = new Newton(negsin); // derivative of cos(x)
		x0 = n2.root(cos, 1.0, 2.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi / 2
		}
	}

}
