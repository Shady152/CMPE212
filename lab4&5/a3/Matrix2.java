package a3;

import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

/**
 * A 2x2 matrix. This class provides basic mathematical operations such
 * as matrix-vector and matrix-matrix products, determinant, and inverse.
 *
 */
public class Matrix2 {
	
	/**
	 * The matrix elements, upper left is a, upper right is b, lower left is c, and lower right is d:
	 * 
	 * <pre>
	 * a b
	 * c d
	 * </pre>
	 * 
	 *The matrix itself is also defied here as mat
	 */
	private double a;
	private double b;
	private double c;
	private double d;
	private double[][] mat;

	/**
	 * Initializes this matrix so that its entries are equal to the specified
	 * values. The matrix entries are defined to be:
	 * 
	 * <pre>
	 * a b
	 * c d
	 * </pre>
	 * 
	 * @param a the value of the upper left element
	 * @param b the value of the upper right element
	 * @param c the value of the lower left element
	 * @param d the value of the lower right element
	 */
	public Matrix2(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		double[][] mc = { { a, b }, { c, d } };
		this.mat = mc;
	}
	
	/**
	 * Initializes this matrix to the identity matrix.
	 */
	public Matrix2() {
		this(1.0, 0.0, 0.0, 1.0);
	}
	
	/**
	 * Initializes this matrix so that it is equal to the specified matrix.
	 * 
	 * @param other a matrix to copy
	 */
	public Matrix2(Matrix2 other) {
		this(other.a, other.b, other.c, other.d);
	}
	
	/**
	 * Returns the entry of this matrix located at the given one-based row and column indexes.
	 * 
	 * @param row the row index of the element
	 * @param col the column index of the element
	 * @return the element at the specified row and column indexes
	 * @throws IndexOutOfBoundsException if row is not 1 and not 2
	 * @throws IndexOutOfBoundsException if col is not 1 and not 2
	 */
	public double get(int row, int col) {
		if (row < 1 || row > 2)
			throw new IndexOutOfBoundsException("Input row index out of bounds, must be 1 or 2");
		if (col < 1 || col > 2)
			throw new IndexOutOfBoundsException("Input column index out of bounds, must be 1 or 2");
		return mat[row - 1][col - 1];
	}

	/**
	 * Sets the entry of this matrix located at the given zero-based row and column indexes to the 
	 * specified value.
	 * 
	 * @param row the row index of the element
	 * @param col the column index of the element
	 * @param val the element to store in this matrix
	 * @return a reference to this matrix
	 * @throws IndexOutOfBoundsException if row is not 1 and not 2
	 * @throws IndexOutOfBoundsException if col is not 1 and not 2
	 */
	public Matrix2 set(int row, int col, double val) {
		if (row < 1 || row > 2)
			throw new IndexOutOfBoundsException("Input row index out of bounds, must be 1 or 2");
		if (col < 1 || col > 2)
			throw new IndexOutOfBoundsException("Input column index out of bounds, must be 1 or 2");
		
		//Update the matrix
		this.mat[row - 1][col - 1] = val;
		
		//Update the elements
		this.a = this.mat[0][0];
		this.b = this.mat[0][1];
		this.c = this.mat[1][0];
		this.d = this.mat[1][1];

		return this;
	}
	
	/**
	 * Postmultiply this matrix with the specified column vector returning a new vector.
	 * <p>
	 * {@code w = A.mult(v)} is equivalent to the mathematical equation {@code w = Av} 
	 * where {@code v}  and {@code w}  are 2x1 column vectors.
	 * 
	 * @param v a 2x1 vector
	 * @return a 2x1 vector equal to this matrix times v
	 */
	public Vector2 mult(Vector2 v) {
		return new Vector2(this.a*v.x() + this.b*v.y(), this.c*v.x() + this.d*v.y());
	}
	
	/**
	 * Postmultiply this matrix with the specified matrix returning a new matrix.
	 * <p>
	 * {@code C = A.mult(B)} is equivalent to the mathematical equation {@code C = AB}.
	 * 
	 * @param m a 2x2 matrix
	 * @return a 2x2 matrix equal to this matrix times m
	 */
	public Matrix2 mult(Matrix2 m) {
		Matrix2 multm = new Matrix2(this.a*m.a + this.b*m.c, this.a*m.b + this.b*m.d, this.c*m.a + this.d*m.c, this.c*m.b + this.d*m.d);
		return multm;
	}
	
	/**
	 * Returns the determinant of this matrix.
	 * 
	 * @return the determinant of this matrix
	 */
	public double det() {
		return this.a*this.d - this.b*this.c;
	}
	
	/**
	 * Returns the inverse of this matrix if the determinant of this matrix is not exactly zero.
	 * 
	 * @return the inverse of this matrix
	 * @throws ArithmeticException if the determinant of this matrix is exactly zero
	 */
	public Matrix2 inv() {
		if(det() == 0)
			throw new ArithmeticException("The Determinent of the matrix is zero");
		double ivd = 1/det();
		Matrix2 invm = new Matrix2(ivd*this.d,-ivd*this.b,-ivd*this.c,ivd*this.a);
		return invm;
	}
	
	/**
	 * Returns a string representation of this matrix. The returned string has the
	 * following form:
	 * 
	 * <pre>
	 * [a, b]
	 * [c, d]
	 * </pre>
	 * 
	 * <p>
	 * where {@code a}, {@code b}, {@code c}, and {@code d} are the {@code double}
	 * values of the entries of this matrix.
	 * 
	 * @return a string representation of this matrix
	 */
	@Override
	public String toString() {
		return "[" + this.a + "," + this.b + "]" + "\n" + "[" + this.c + "," + this.d + "]";
	}

	
	//####################################################################################
	/**
	 * Initializes this matrix so that its entries are equal to the specified
	 * values. The matrix entries are defined to be:
	 * 
	 * <pre>
	 * a b
	 * c d
	 * </pre>
	 * 
	 * @param a the value of the upper left element
	 * @param b the value of the upper right element
	 * @param c the value of the lower left element
	 * @param d the value of the lower right element
	 */
	
	/*
	public Matrix2(double a, double b, double c, double d) {
		
	}
	*/
	
	/**
	 * Returns a string representation of this matrix. The returned string
	 * has the following form:
	 * 
	 * <pre>
	 * [a, b]
	 * [c, d]
	 * </pre>
	 * 
	 * <p>
	 * where {@code a}, {@code b}, {@code c}, and {@code d} are the {@code double}
	 * values of the entries of this matrix.
	 * 
	 * @return a string representation of this matrix
	 */
	
	/*
	@Override
	public String toString() {

	}
	*/
	//####################################################################################
	
} 
