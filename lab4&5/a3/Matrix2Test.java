package a3;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

public class Matrix2Test {

	@Test
	public void test01_ctor() {
		// expected matrix values
		double expA = 1.5;
		double expB = -1.5;
		double expC = 2.5;
		double expD = 3.0;

		// call constructor
		Matrix2 mat = new Matrix2(expA, expB, expC, expD);

		// test for equality
		double tol = 1e-9;
		assertEquals(expA, mat.get(1, 1), tol);
		assertEquals(expB, mat.get(1, 2), tol);
		assertEquals(expC, mat.get(2, 1), tol);
		assertEquals(expD, mat.get(2, 2), tol);
	}

	@Test
	public void test02_copyCtor() {
		// expected matrix values
		double expA = 1.5;
		double expB = -1.5;
		double expC = 2.5;
		double expD = 3.0;

		// matrix to copy
		Matrix2 mat = new Matrix2(expA, expB, expC, expD);

		// call copy constructor to make a copy of mat
		Matrix2 copy = new Matrix2(mat);

		// test for equality
		double tol = 1e-9;
		assertEquals(copy.get(1, 1), mat.get(1, 1), tol);
		assertEquals(copy.get(1, 2), mat.get(1, 2), tol);
		assertEquals(copy.get(2, 1), mat.get(2, 1), tol);
		assertEquals(copy.get(2, 2), mat.get(2, 2), tol);
	}

	@Test
	public void test03a_set() {
		// expected matrix values
		double expA = 1.5;
		double expB = -1.5;
		double expC = 2.5;
		double expD = 3.0;

		// matrix on which to call set
		Matrix2 mat = new Matrix2(expA, expB, expC, 100.0);

		// call set to set bottom-right matrix entry to expD
		mat.set(2, 2, expD);

		// test for equality
		double tol = 1e-9;
		assertEquals(expA, mat.get(1, 1), tol);
		assertEquals(expB, mat.get(1, 2), tol);
		assertEquals(expC, mat.get(2, 1), tol);
		assertEquals(expD, mat.get(2, 2), tol);
	}

	@Test
	public void test03b_set() {
		// matrix on which to call set
		Matrix2 mat = new Matrix2();

		// call set setting any entry or entries of mat
		Matrix2 ref = mat.set(1, 1, 1);

		// test that reference returned by set points to the same object as mat
		assertSame(ref, mat);
	}

	@Test
	public void test04_multVector2() {
		// Define Vectors and Matrices
		Vector2 vect = new Vector2(3.3, 1.0);
		Matrix2 mat = new Matrix2(1.5, -1.5, 2.5, 3.0);

		// expected values after multiplication
		double expA = 3.45;
		double expB = 11.25;

		// call mult for vector matrix multiplication
		Vector2 mult = mat.mult(vect);

		// test for equality
		double tol = 1e-9;
		assertEquals(expA, mult.x(), tol);
		assertEquals(expB, mult.y(), tol);
	}
	
}
