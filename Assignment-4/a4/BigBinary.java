package a4;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that represents a signed immutable binary number having no specified
 * limits on the number of bits. The binary number is represented using a ones
 * complement representation.
 *
 */
public class BigBinary implements Comparable<BigBinary> {

	/**
	 * The bits of this binary number.
	 */
	private List<Bit> bits;

	/**
	 * Initializes this binary number to have the specified number of bits and a
	 * decimal value of (positive) zero.
	 * 
	 * @param nbits the number of bits
	 * @throws IllegalArgumentException if {@code nbits} is less than 1
	 */
	public BigBinary(int nbits) {
		if (nbits < 1) {
			throw new IllegalArgumentException("binary number must have at least 1 bit");
		}
		this.bits = new ArrayList<>();
		for (int i = 0; i < nbits; i++) {
			this.bits.add(Bit.ZERO);
		}
	}

	/**
	 * Translates the decimal string representation of a {@code BigBinary} into a
	 * {@code BigBinary}. The string representation consists of a sequence of one or
	 * more binary digits (0s and 1s). The string may not contain any extraneous
	 * characters (whitespace, for example) or characters not equal to {@code '0'}
	 * or {@code '1'}.
	 * 
	 * <p>
	 * If {@code bits} is empty then this binary number is initialized to the 8-bit
	 * binary number equal to zero.
	 * 
	 * @param bits the bits of the binary number
	 * @throws IllegalArgumentException if bits is not a valid representation of a
	 *                                  {@code BigBinary}
	 */
	public BigBinary(String bits) {
		int n = bits.length();
		if (n == 0) {
			bits = "00000000";
		}
		this.bits = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			char c = bits.charAt(i);
			if (c == '0') {
				this.bits.add(Bit.ZERO);
			} else if (c == '1') {
				this.bits.add(Bit.ONE);
			}
			else {
				throw new IllegalArgumentException("invalid bit : " + c);
			}
		}
	}


	/**
	 * Returns the number of bits in this binary number.
	 * 
	 * @return the number of bits in this binary number
	 */
	public int numberOfBits() {
		return this.bits.size();
	}

	/**
	 * Returns {@code true} if this binary number is equal to positive or negative
	 * zero, {@code false} otherwise. In ones complement representation, a binary
	 * number is equal to zero if all of the bits are equal to zero or all of the
	 * bits are equal to one.
	 * 
	 * @return {@code true} if this binary number is equal to positive or negative
	 *         zero, {@code false} otherwise
	 */
	public boolean isZero() {
		Bit first = this.bits.get(0);
		for (int i = 1; i < this.bits.size(); i++) {
			if (this.bits.get(i) != first) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns {@code true} if this binary number is negative, and {@code false}
	 * otherwise. This method considers {@code -0} to be negative.
	 * 
	 * @return {@code true} if this binary number is negative, and {@code false}
	 *         otherwise
	 */
	public boolean isNegative() {
		return this.bits.get(0) == Bit.ONE;
	}

	/**
	 * Returns a new binary number equal to the bitwise NOT of this binary number. The
	 * returned number has a decimal value equal to the negative decimal value of
	 * this binary number (it is the ones complement of this number).
	 * 
	 * @return a new binary number equal to the bitwise NOT of this binary number
	 */
	public BigBinary not() {
		BigBinary result = new BigBinary(this.toString());
		for (int i = 0; i < result.bits.size(); i++) {
			Bit negate = result.bits.get(i).not();
			result.bits.set(i, negate);
		}
		return result;
	}
	

	/**
	 * Returns the bits of this number as a list. The returned list cannot be used
	 * to modify this binary number.
	 * 
	 * <p>
	 * If you use a list to store the bits of the number then return a new copy
	 * of the list.
	 * 
	 * <p>
	 * If you use an array to store the bits of the number then use {@code Arrays.toList}
	 * to get a list representation of your array, and then return a new copy of
	 * that list.
	 * 
	 * @return a the bits of this number as a list
	 */
	public List<Bit> getBits() {
		return new ArrayList<Bit>(this.bits);
	}
	

	/**
	 * Compares this binary number to an object for equality. Returns true if and
	 * only if {@code obj} is a {@code BigBinary} number having the same number of
	 * bits and the same decimal value as this binary number.
	 * 
	 * <p>
	 * Note that this method considers positive zero to be equal to negative zero if
	 * both numbers have the same number of bits. For all other binary numbers,
	 * two binary numbers are equal if and only if they have exact same bit pattern.
	 * 
	 * <p>
	 * Two binary numbers having the same decimal value but different number of bits
	 * are considered to be not equal by this method.
	 * 
	 * @param obj the object to compare
	 * @return true if this binary number is equal to the other binary number, false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		BigBinary other = (BigBinary) obj;
		if (this.numberOfBits() != other.numberOfBits()) {
			return false;
		}
		if (this.isZero() && other.isZero()) {
			return true;
		}
		return this.bits.equals(other.bits);
	}

	/**
	 * Returns a hash code for this binary number.
	 * 
	 * @return a hash code for this binary number
	 */
	@Override
	public int hashCode() {
		// zero is a special case because -0 is considered to be equal to 0
		if (this.isZero()) {
			return 0;
		}
		return Objects.hash(this.bits);
	}

	/**
	 * Compares this binary number to another binary number for order. The result is
	 * {@code -1} if this number is less than the other number, {@code 0} is this
	 * number is equal to the other number, and {@code 1} if this number is greater
	 * than the other number.
	 * 
	 * <p>
	 * Unlike {@code equals}, this method will correctly compare binary numbers
	 * having different numbers of bits. Also, this method considers {@code -0} to
	 * be less than {@code 0}.
	 * 
	 * <p>
	 * Conceptually, the decimal value of the numbers are used in the comparison,
	 * but it is not practical to compute the decimal value of an arbitrary length
	 * binary number. Instead a bit-wise comparison is performed.
	 * 
	 * 
	 * @param other the binary number to compare
	 * @return -1, 0 or 1 as this number is numerically less than, equal to, or
	 *         greater than the other number
	 */
	public int compareTo(BigBinary other) {
		// Case 1: both numbers are zero
		if (this.isZero() && other.isZero()) {
			if (this.isNegative() && !other.isNegative()) {
				return -1;
			} else if (!this.isNegative() && other.isNegative()) {
				return 1;
			} else {
				return 0;
			}
		}
		// Case 2: numbers have different signs
		if (this.isNegative() && !other.isNegative()) {
			return -1;
		} else if (!this.isNegative() && other.isNegative()) {
			return 1;
		}
		// Case 3: numbers have the same sign
		// lengthen the shorter number so that the number of bits are the same
		BigBinary x = this;
		BigBinary y = other;
		if (this.numberOfBits() < other.numberOfBits()) {
			x = this.lengthen(other.numberOfBits());
		} else if (this.numberOfBits() > other.numberOfBits()) {
			y = other.lengthen(this.numberOfBits());
		}
		// compare bit-by-bit looking for the first Bit.ONE
		for (int i = 0; i < this.numberOfBits(); i++) {
			Bit xBit = x.bits.get(i);
			Bit yBit = y.bits.get(i);
			if (xBit != yBit) {
				if (xBit == Bit.ONE) {
					return 1;
				}
				return -1;
			}
		}
		return 0;
	}

	/**
	 * Returns a new binary number having {@code num} bits whose decimal value is
	 * equal to this binary number. {@code num} must be greater than or equal to the
	 * number of bits of this binary number.
	 * 
	 * <p>
	 * A positive binary number can be lengthened by adding any number of
	 * {@code Bit.ZERO} bits to the front of the binary number.
	 * 
	 * <p>
	 * A negative binary number can be lengthened by adding any number of
	 * {@code Bit.ONE} bits to the front of the binary number.
	 * 
	 * @param num the number of bits in the returned binary number
	 * @return a new binary number having {@code num} bits whose decimal value is
	 *         equal to this binary number
	 * @throws IllegalArgumentException if {@code num < this.numberOfBits()}
	 */
	public BigBinary lengthen(int num) {
		int n = num - this.numberOfBits();
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		BigBinary b = new BigBinary(this.toString());
		Bit toAdd = Bit.ZERO;
		if (this.isNegative()) {
			toAdd = Bit.ONE;
		}
		// add n toAdd bits to the front of b.bits
		for (int i = 0; i < n; i++) {
			b.bits.add(0, toAdd);
		}
		return b;
	}

	/**
	 * Returns a string representation of this binary number. The returned string is
	 * made up of the bit values (0 or 1) of the number from the left-most bit to
	 * the right-most bit.
	 * 
	 * @return a string representation of this binary number
	 */
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (Bit bit : this.getBits()) {
			b.append(bit.toString());
		}
		return b.toString();
	}

	/**
	 * Returns the decimal (base 10) value of this binary number as an {@code int}
	 * value throwing an exception if the result overflows an {@code int}.
	 * 
	 * @return the decimal (base 10) value of this binary number
	 * @throws ArithmeticException if the result overflows an {@code int}
	 */
	public int toDecimal() {
		long val = 0;
		List<Bit> bits = this.getBits();
		for (int i = 0; i < bits.size(); i++) {
			int y = bits.size() - 1 - i;
			int bitVal = bits.get(i).value();
			if (this.isNegative()) {
				bitVal = -bits.get(i).not().value();
			}
			val += bitVal * Math.pow(2, y);
			if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) {
				throw new ArithmeticException();
			}
		}
		return (int) val;
	}


	/**
	 * Prints the output of of calling {@code toDecimal}, {@code isZero}, {@code isNegative},
	 * and {@code not} on a binary number.
	 * 
	 * @param b a binary number
	 * @param header a message to print at the start of the test
	 */
	static void test(BigBinary b, String header) {
		String s = b.toString();
		String str;
		try {
			str = "" + b.toDecimal();
		} catch (ArithmeticException ex) {
			str = "overflows int";
		}
		System.out.println(header);
		System.out.println(s + ".toDecimal()  : " + str);
		System.out.println(s + ".isZero()     : " + b.isZero());
		System.out.println(s + ".isNegative() : " + b.isNegative());
		System.out.println(s + ".not()        : " + b.not());
		System.out.println();
	}

	/**
	 * Prints the output of of calling {@code equals} and {@code compareTo} on a 
	 * pair of binary numbers.
	 * 
	 * @param x a binary number
	 * @param y a binary number
	 * @param header a message to print at the start of the test
	 */
	static void testEqualsAndCompareTo(BigBinary x, BigBinary y, String header) {
		String sx = x.toString();
		String sy = y.toString();
		System.out.println(header);
		System.out.println(sx + ".equals(" + sy + ")    : " + x.equals(y));
		System.out.println(sx + ".compareTo(" + sy + ") : " + x.compareTo(y));
		System.out.println(sy + ".compareTo(" + sx + ") : " + y.compareTo(x));
		System.out.println();
	}

	/**
	 * A simple test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// 8-bit binary number equal to +0
		BigBinary b = new BigBinary(8);
		test(b, "8-bit binary number equal to +0");
		
		// 8-bit binary number equal to -0
		b = new BigBinary("11111111");
		test(b, "8-bit binary number equal to -0");

		// 8-bit binary number equal to 127
		b = new BigBinary("01111111");
		test(b, "8-bit binary number equal to 127");

		// 8-bit binary number equal to -127
		b = new BigBinary("10000000");
		test(b, "8-bit binary number equal to -127");

		// 32-bit binary number equal to Integer.MAX_VALUE
		b = new BigBinary("01111111111111111111111111111111");
		test(b, "32-bit binary number equal to Integer.MAX_VALUE");

		// 33-bit binary number equal to Integer.MAX_VALUE + 1
		b = new BigBinary("010000000000000000000000000000000");
		test(b, "33-bit binary number equal to Integer.MAX_VALUE + 1");

		// 33-bit binary number equal to Integer.MIN_VALUE
		b = new BigBinary("101111111111111111111111111111111");
		test(b, "33-bit binary number equal to Integer.MIN_VALUE");
		
		// 33-bit binary number equal to Integer.MIN_VALUE - 1
		b = new BigBinary("101111111111111111111111111111110");
		test(b, "33-bit binary number equal to Integer.MIN_VALUE - 1");
		

		// compare 4-bit +0 to 4-bit +0
		BigBinary x = new BigBinary(4);
		BigBinary y = new BigBinary(4);
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 4-bit +0");

		// compare 4-bit +0 to 4-bit -0
		x = new BigBinary(4);
		y = new BigBinary("1111");
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 4-bit -0");

		// compare 4-bit +5 to 4-bit +1
		x = new BigBinary("0101");
		y = new BigBinary("0001");
		testEqualsAndCompareTo(x, y, "compare 4-bit +5 to 4-bit +1");

		// compare 4-bit -5 to 4-bit -1
		x = new BigBinary("1010");
		y = new BigBinary("1110");
		testEqualsAndCompareTo(x, y, "compare 4-bit -5 to 4-bit -1");

		// compare 4-bit +0 to 2-bit +0
		x = new BigBinary(4);
		y = new BigBinary("00");
		testEqualsAndCompareTo(x, y, "compare 4-bit +0 to 2-bit +0");

		// compare 4-bit +5 to 6-bit +5
		x = new BigBinary("0101");
		y = new BigBinary("000101");
		testEqualsAndCompareTo(x, y, "compare 4-bit +5 to 6-bit +5");
		
		// compare large number to a larger number
		x = new BigBinary("01000011000000101000101010100001111101");
		y = new BigBinary("010000110000001010001010101000011111011");
		testEqualsAndCompareTo(x, y, "compare large number to a larger number");

		// compare Integer.MIN_VALUE to (Integer.MIN_VALUE - 1)
		x = new BigBinary("101111111111111111111111111111111");
		y = new BigBinary("101111111111111111111111111111110");
		testEqualsAndCompareTo(x, y, "compare Integer.MIN_VALUE to (Integer.MIN_VALUE - 1)");		
	}
}
