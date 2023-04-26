package a2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Methods useful for implementing the game Wordle. None of the methods
 * assume that the guess or answer words have exactly 5 letters.
 */
public class WordleUtils {

	/**
	 * Returns a new string formed by replacing one character in the string
	 * {@code s} with the replacement character {@code c}. The position of the
	 * replaced character is given by {@code index}.
	 * 
	 * @param s     a string to replace one character in
	 * @param index the index of the replaced character
	 * @param c     the character to use as the replacement character
	 * @return a new string formed by replacing one character in s with the
	 *         replacement character c
	 * @throws IndexOutOfBoundsException if index is not a valid index for the
	 *                                   string s
	 */
	public static String replace(String s, int index, char c) {
		if (index < 0 || index >= s.length()) {
			throw new IndexOutOfBoundsException();
		}
		return s.substring(0, index) + c + s.substring(index + 1);
	}

	/**
	 * Returns a list indicating which letters in the {@code guess} string match
	 * letters in the {@code answer} string. Letters match if they are equal and
	 * have the same location in both strings.
	 * 
	 * <p>
	 * The returned list contains the value {@code true} at index {@code i} if the
	 * characters at index {@code i} of the {@code guess} and {@code answer} strings
	 * are equal, otherwise the the value at index {@code i} is {@code false}.
	 * 
	 * @param guess  a string to compare to the answer
	 * @param answer the answer string
	 * @return a list of boolean values indicating whether characters in the guess
	 *         and answer strings match
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static ArrayList<Boolean> isGreen(String guess, String answer) {
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("s.length() != target.length()");
		}
		ArrayList<Boolean> res = new ArrayList<>();
		
		// compare each letter of the guess and answer 
		for (int i = 0; i < guess.length(); i++) {
			if (guess.charAt(i) != answer.charAt(i)) {
				res.add(false);
			} else {
				res.add(true);
			}
		}
		return res;
	}

	/**
	 * Returns a list indicating which letters in the {@code guess} string also
	 * appear in the {@code answer} string taking into account how many times a
	 * letter appears; see the assignment document for some examples.
	 * 
	 * <p>
	 * The returned list contains the value {@code true} at index {@code i} if the
	 * character at index {@code i} of the {@code guess} appears in the
	 * {@code answer} string (again, accounting for the number of times the letter
	 * appears), otherwise the the value at index {@code i} is {@code false}.
	 * 
	 * @param guess  a string to compare to the answer
	 * @param answer the answer string
	 * @return a list of boolean values indicating whether characters in the guess
	 *         string appear in the answer string accounting for the number of times
	 *         that the character appears
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static ArrayList<Boolean> isYellow(String guess, String answer) {
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("s.length() != target.length()");
		}
		/*
		 * Processes the guess twice.
		 * 
		 * First finds all of the green letters and replaces the matching letter
		 * in the answer so that the matching letter is not considered in the
		 * second pass. This produces a modified answer string.
		 * 
		 * Then finds all of the yellow letters by testing if each guess letter
		 * is contained in the modified answer. Each time a yellow letter is
		 * identified, the matching letter in the answer is replaced to prevent
		 * further matches.
		 */
		
		// find green letters and replace them in the answer
		ArrayList<Boolean> res = isGreen(guess, answer);
		for (int i = 0; i < guess.length(); i++) {
			if (res.get(i)) {
				answer = replace(answer, i, '*');
				// a green letter cannot be yellow, set result to false
				res.set(i,  false);
			}
		}
		
		// find yellow letters
		for (int i = 0; i < guess.length(); i++) {
			char c = guess.charAt(i);
			int index = answer.indexOf(c);
			if (index < 0) {
				// c is not in the answer so letter is not yellow
				res.set(i, false);
			} else {
				// c is in the answer so letter is yellow
				// replace matching letter in answer to prevent further matches
				answer = replace(answer, index, '*');
				res.set(i, true);
			}
		}
		return res;
	}
	
	// solution with map
	public static ArrayList<Boolean> isYellow2(String guess, String answer) {
		if (guess.length() != answer.length()) {
			throw new IllegalArgumentException("s.length() != target.length()");
		}
		ArrayList<Boolean> res = isGreen(guess, answer);
		Map<Character, Integer> count = new HashMap<>();
		for (int i = 0; i < answer.length(); i++) {
			if (res.get(i)) {
				continue;
			}
			char c = answer.charAt(i);
			if (count.containsKey(c)) {
				count.put(c, count.get(c) + 1);
			}
			else {
				count.put(c, 1);
			}
		}
		for (int i = 0; i < guess.length(); i++) {
			if (res.get(i)) {
				res.set(i, false);
				continue;
			}
			char c = guess.charAt(i);
			if (count.containsKey(c)) {
				res.set(i, true);
				int n = count.get(c);
				if (n == 1) {
					count.remove(c);
				}
				else {
					count.put(c, n - 1);
				}
			}
		}
		return res;
	}

	/**
	 * Returns a list of the colorings for the given guess and answer words. See the
	 * assignment document for details on how letters of the guess word are colored
	 * in the game Wordle.
	 * 
	 * @param guess  a guess word
	 * @param answer the answer word
	 * @return a list of the colorings for the given guess and answer words
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static ArrayList<WordleColor> getColors(String guess, String answer) {
		ArrayList<Boolean> matches = WordleUtils.isGreen(guess, answer);
		ArrayList<Boolean> contains = WordleUtils.isYellow(guess, answer);
		ArrayList<WordleColor> res = new ArrayList<>();
		for (int i = 0; i < guess.length(); i++) {
			if (matches.get(i)) {
				res.add(WordleColor.GREEN);
			} else if (contains.get(i)) {
				res.add(WordleColor.YELLOW);
			} else {
				res.add(WordleColor.GRAY);
			}
		}
		return res;
	}

	/**
	 * Update the sets of different letter categories in a game of Wordle given the
	 * guess and answer words. This method is meant to be called after the player
	 * makes a new guess. The first time that the method is called for a new game of
	 * Wordle, the sets {@code included} and {@code excluded} should be empty, and
	 * the set {@code possible} should contain the letters {@code 'A'} through
	 * {@code 'Z'}.
	 * 
	 * <p>
	 * The set {@code included} is the set of letters that the player has determined
	 * must be in the answer word.
	 * 
	 * <p>
	 * The set {@code excluded} is the set of letters that the player has determined
	 * must not be in the answer word.
	 * 
	 * <p>
	 * The set {@possible} is the set of letters that have not been used yet by the
	 * player. If {@code guess.equals(answer)} is {@code true} then the set
	 * {@possible} will be empty.
	 * 
	 * @param guess    the most recent guess word
	 * @param answer   the answer word
	 * @param included the set of letters that the player has determined are in the
	 *                 answer word
	 * @param excluded the set of letters that the player has determined are not in
	 *                 the answer word
	 * @param possible the set of letters not in included or excluded
	 * @throws IllegalArgumentException if guess and answer have different lengths
	 */
	public static void updateLetters(String guess, String answer, 
			TreeSet<Character> included,
			TreeSet<Character> excluded, 
			TreeSet<Character> possible) {
		ArrayList<WordleColor> colors = getColors(guess, answer);
		for (int i = 0; i < colors.size(); i++) {
			Character c = guess.charAt(i);
			possible.remove(c);
			if (colors.get(i) != WordleColor.GRAY) {
				// add letter to included and remove from excluded
				included.add(c);
				// letter might be in excluded if the guess contains duplicated letters
				excluded.remove(c);
			}
			else {
				// add to excluded only if not already in included
				if (!included.contains(c)) {
					excluded.add(c);
				}
			}
		}
		if (guess.equals(answer)) {
			// all remaining possible letters are not in the answer word
			for (Character c : possible) {
				excluded.add(c);
			}
			possible.clear();
		}
	}

	public static void main(String[] args) {
		// simulates one game of Wordle
		
		// set up the sets of letters
		TreeSet<Character> in = new TreeSet<>();
		TreeSet<Character> notIn = new TreeSet<>();
		TreeSet<Character> mightBeIn = new TreeSet<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			mightBeIn.add(c);
		}
		
		// the answer
		String ans = "CORAL";

		// the guesses
		final String[] GUESSES = { "VEGAN", "HULAS", "BLOAT", "LOYAL",  "FOCAL", "CORAL"};
		int guessNum = 0;
		for (String g : GUESSES) {
			guessNum++;
			System.out.println("answer  : " + ans);
			System.out.println("guess " + guessNum + " : " + g);
			System.out.println("isGreen : " + WordleUtils.isGreen(g, ans));
			System.out.println("isYellow: " + WordleUtils.isYellow(g, ans));
			System.out.println("colors  : " + WordleUtils.getColors(g, ans));
			WordleUtils.updateLetters(g, ans, in, notIn, mightBeIn);
			System.out.println("in      : " + in);
			System.out.println("not in  : " + notIn);
			System.out.println("maybe?  : " + mightBeIn);
			System.out.println();
		}
	}

}
