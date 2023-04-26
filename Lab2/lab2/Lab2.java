package lab2;

public class Lab2 {

	/**
	 * Returns the maximum of two values using an unusual algorithm.
	 * 
	 * @param a a value
	 * @param b a value
	 * @return the maximum of the two argument values
	 */
	public static int max2(int a, int b) {
		long twiceMax = (long) a + b;
		twiceMax += Math.abs(a - b);
		return (int) (twiceMax / 2);
	}

	/**
	 * Swaps the values of the arguments {@code x} and {@code y}?
	 * 
	 * @param x a value to swap
	 * @param y the other value to swap
	 */
	public static void swap(int x, int y) {
		int tmp = x;
		x = y;
		y = tmp;
	}

	/**
	 * Swaps the values of the arguments {@code s} and {@code t}?
	 * 
	 * @param s a string to swap
	 * @param t the other string to swap
	 */
	public static void swap(String s, String t) {
		String tmp = s;
		s = t;
		t = tmp;
	}

	/**
	 * Swaps the values of the elements of the array {@code arr} at indexes
	 * {@code i} and {@code j}?
	 * 
	 * @param arr an array in which to swap elements
	 * @param i   the index of one element to be swapped
	 * @param j   the index of other element to be swapped
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	/**
	 * Returns the number of iterations for the Collatz
	 * sequence to reach the value 1 when starting from the
	 * specified positive value {@code n}.
	 * 
	 * @param n the starting value of the sequence
	 * @return the number of iterations required to reach a value of 1
	 */
	public static long collatz(long n) {
		if (n < 1) {
			throw new IllegalArgumentException("n < 1");
		}
		int count = 0;
		while (n != 1) {
			if (n % 2 == 0) {
				n = n / 2;
			}
			else {
				n = 3 * n + 1;
			}
			count++;
			if (count == 3)
				return n;
		}
		return count;
	}
	
	/**
	 * Complete this method for Exercise 1. 
	 */
	public static void ex1() {
		//We start at 1 b/c max would return 0 if we start at zero
		//Also due to the given hint we could start at 1073741820 so that it's faster
		int y = 1; 
		//If y is too big it will loop around to be negative thats why we have this condition
		while (Lab2.max2(0, y) > 0) 
		{
			if (Lab2.max2(0, y) > 0) //Allows us to keep iterating through the loop
				y += 1;
		}
		//When is breaks out of the loop the max y value will be known
		
		//It returns a negative for the new max() but this commented out stuff fixes it
		/*
		if (y < 0) //This fixes it for the new max()
			System.out.println(y-1);
		else
		*/
		//Also idk if they want us to output to screen but the print statement is here in case
			//System.out.println(y); //Prints the y where max() loops back around
		
	}
	
	
	/**
	 * Complete this method for Exercise 2.
	 * 
	 * <p>
	 * Swaps the coordinates of the two input points.
	 * 
	 * @param p a point to swap coordinates with
	 * @param q the other point to swap coordinates with
	 */
	public static void swap(Point2 p, Point2 q) {
		Point2 temp = new Point2(p.x(), p.y());
		p.set(q.x(), q.y());
		q.set(temp.x(), temp.y());
	}
	
	
	/**
	 * Complete this method for Exercise 3.
	 */
	public static void ex3() {
		final int N = (int) Lab2.collatz(20213140);
		//idk if they want us to output to screen but the print statement is here in case
		//System.out.println(N);
	}
	
	
	
	public static void main(String[] args) {
		//I'm not sure if they want us to output to screen or not
		int x = 5;
		int y = 7;
		int max = Lab2.max2(x, y);
		System.out.println(max);
		Lab2.ex1(); //Here we run ex1() to get the max value
		
		Point2 p = new Point2(1, 1);
		Point2 q = new Point2(0, 0);
		Lab2.swap(p, q);
		
		ex3();
		
	}
}
