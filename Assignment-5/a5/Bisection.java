package a5;

/**
 * Root finding via the bisection method.
 *
 */
public class Bisection implements RootFinder1 {

	// YOUR CLASS SHOULD PROVIDE ALL OF THE FEATURES
	// DESCRIBED BY ITS DOCUMENTATION

	// The tolerance used by this root finder
	public static final double TOL = 1.0E-6; // tolerance

	public Bisection() {
	}

	@Override
	public Root root(Function1 f, double a, double b) {
		// exception handling
		if (a >= b)
			throw new IllegalArgumentException("a is larger than b; a should be smaller than b; [a,b]");

		// checks that the function doesn't have the same sign at a and b else there
		// would be not root
		if (f.eval(a) * f.eval(b) > 0)
			throw new IllegalArgumentException(
					"f(a) and f(b) have the same sign; there is no root on the given interval");

		double mid = (a + b) / 2; // starting mid point for loop
		while (Math.abs(f.eval(mid)) > Bisection.TOL) // condition check f at the mid point
		{
			// if-else statement changes the range
			if ((f.eval(mid) * f.eval(a)) < 0)
				b = mid;
			else
				a = mid;
			mid = (a + b) / 2; // new value of mid

			// this is to prevent asymptotic cases such as 1/x so that the loop doesn't go
			// forever
			if (f.eval(mid) == Double.POSITIVE_INFINITY || f.eval(mid) == Double.NEGATIVE_INFINITY)
				return new Root();
		}
		if (f.eval(mid) > Bisection.TOL)
			return new Root(); // returns Double.NaN
		else {
			Root rootval = new Root(mid); // make the found root at mid a root type object
			return rootval;
		}

	}

	/**
	 * Small example program that finds a root of the sine and cosine functions.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// find the root of sin(x) on the interval [3, 4]
		Function1 sin = new Sine();
		Bisection bisect = new Bisection();
		Root x0 = bisect.root(sin, 3.0, 4.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi
		}

		// find the root of cos(x) on the interval [1, 2]
		Function1 cos = new Cosine();
		x0 = bisect.root(cos, 1.0, 2.0);
		if (x0 != Root.NONE) {
			System.out.println(x0.value()); // should be approx. pi / 2
		}
	}

}
