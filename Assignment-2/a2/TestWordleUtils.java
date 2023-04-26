package a2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestWordleUtils {

	@Test
	public void test01a_replace() {
		// method args
		String s = "A";
		int index = 0;
		char c = 'B';
		
		// expected answer
		String exp = "B";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}

	@Test
	public void test01b_replace() {
		// method args
		String s = "GUMBO";
		int index = 0;
		char c = 'J';
		
		// expected answer
		String exp = "JUMBO";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}
	
	@Test
	public void test01c_replace() {
		// method args
		String s = "JIMBO";
		int index = 1;
		char c = 'U';
		
		// expected answer
		String exp = "JUMBO";
		
		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}
	
	// ---------------------------------------- The prof wrote this in the videos
	@Test
	public void test01d_replace_throws() {
		// method args
		String s = "JIMBO";
		int index = -1;
		char c = 'U';
		
		try {
			WordleUtils.replace(s, index, c);
			fail("IndexOutOfBoundsException expected for index = " + index);
		}
		catch(IndexOutOfBoundsException x) {
			//ok, expected result
		}
	}
	// ----------------------------------------
	
	// ---------------------------------------- The prof wrote this in the videos
	@Test
	public void test02a_isGreen() {
		String guess = "GREEN";
		String ans = "COLOR";
		
		ArrayList<Boolean> exp = new ArrayList<>();
		for (int i = 0; i < guess.length(); i++)
			exp.add(false);
		
		assertEquals(exp, WordleUtils.isGreen(guess, ans));
	}
	// ----------------------------------------
	
	@Test
	//Exercise 1
	public void test02b_isGreen_throws() 
	{
		String guess = "GREEN"; //5 chars
		String ans = "COLORS"; //6 chars
		//guess and ans are not the same length so an exception must be thrown
		
		try 
		{
			WordleUtils.isGreen(guess, ans);
			//If the exception is not thrown then the unit test fails
			fail("IllegalArgumentException"); 
		}
		catch(IllegalArgumentException x) 
		{
			//If the exception is thrown then we end up here which is what we want so the unit test passes
		}
	}
	
	@Test
	//Exercise 2
	public void test03a_isYellow() 
	{
		String guess = "KAYAK";
		String ans = "WHACK";
		
		//define the expected arraylist
		ArrayList<Boolean> exp = new ArrayList<>();
		//populate the expected arraylist
		exp.add(false);
		exp.add(true);
		exp.add(false);
		exp.add(false);
		exp.add(false);
		
		assertEquals(exp, WordleUtils.isYellow(guess, ans));
	}
	
	@Test
	//Exercise 3
	public void test04a_getColors() 
	{
		String guess = "KAIAK";
		String ans = "KAYAK";
		//The guess (KAIAK) and ans (KAYAK) are [GREEN, GREEN, GRAY, GREEN, GREEN] so here we expect it to pass
		
		//define the expected arraylist
		ArrayList<WordleColor> exp = new ArrayList<>();
		//populate the expected arraylist
		exp.add(WordleColor.GREEN);
		exp.add(WordleColor.GREEN);
		exp.add(WordleColor.GRAY);
		exp.add(WordleColor.GREEN);
		exp.add(WordleColor.GREEN);
		
		assertEquals(exp, WordleUtils.getColors(guess, ans));
	}
	
}
