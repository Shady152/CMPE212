package ca.queensu.cs.cisc124.notes.basics.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class Point2Test {
	
	@Test
	public void test01_noArgCtor() {
		// call the constructor that we want to test
		Point2 p = new Point2();

		// expected point
		double expX = 0.0;
		double expY = 0.0;

		// test
		double tol = 1e-9;
		assertEquals(expX, p.x(), tol);
		assertEquals(expY, p.y(), tol);
	}

	@Test
	public void test02a_multiplyScalar() {
		// choose a point and a scalar
		Point2 p = new Point2(1.1, -1.3);
		double s = 3.0;

		// expected point
		double expX = 3.3;
		double expY = -3.9;

		// call multiply and record return
		Point2 got = p.multiply(s);

		// test
		double tol = 1e-9;
		assertEquals(expX, got.x(), tol);
		assertEquals(expY, got.y(), tol);
	}

	@Test
	public void test02b_multiplyScalar() {
		// choose a point and a scalar
		Point2 p = new Point2(1.1, -1.3);
		double s = 3.0;

		// call multiply and record return
		Point2 got = p.multiply(s);

		// test for identity
		assertSame(p, got); // tests if two references refer to the same object (assertSame())
	}
	
	@Test
	public void test03a_toString() {
		// choose a point and a scalar
		Point2 p = new Point2(1.1, -1.3);

		// expected string
		String exp = "(1.1, -1.3)";

		// call method
		String got = p.toString();

		// test for equality
		assertEquals(exp, got);
	}

	@Test
	public void test03b_toString() {
		// choose a point and a scalar
		Point2 p = new Point2(1.1, -1.3);

		// expected coordinates
		double expX = 1.1;
		double expY = -1.3;

		// call method
		String got = p.toString();
		
		//test coordinates
		double tol = 1e-9;
		assertEquals (expX, p.x(), tol);
		assertEquals (expY, p.y(), tol);
	}
}
