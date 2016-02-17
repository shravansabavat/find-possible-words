package com.problems.iterative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is to find the number of possible words in a grid of letters.
 * Following assumptions are made for the solution.
 * 
 * 1. Words are formed in directions left to right, right to left, down to up, up to down,
 * traverse up-left diagonal, up-right diagonal, down-left diagonal, down-right diagonal.
 * 
 * 2. A list of valid words are supplied through a text document. As of now list of valid words are stored
 * in validWords.txt under the same level of this class.
 * 
 * 3. A list of block of words is supplied at runtime as a system input to this program to search for valid words from
 * the valid-words.txt file. Copy paste this block and click enter 2 times
TAMHAT
AIRQDR
MONCRT
EDRTCR						
 * (Even the empty line above)
 * @author ssabavat
 *
 */
public class Solution {

	private static final String LINE_SEP = System.getProperty("line.separator", "\r\n");

	private static Set<String> validWordsDictionary = new HashSet<>();

	private static Set<String> wordsFoundInBlock = new HashSet<>();

	private static char[][] twoDimCharArray = null;

	public static void main(String[] args) throws IOException {
		StringBuilder wordsBlock = new StringBuilder();

		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)); 
		String line;
		while ((line = bufferReader.readLine()) != null && line.length()!= 0) {//would break when there is an empty input line
			wordsBlock.append(line).append(LINE_SEP);
		}

		Solution solution = new Solution();
		solution.loadValidWords("valid-words.txt");
		solution.convertWordsBlockInto2dArr(wordsBlock);
		solution.findWords();

		if (wordsFoundInBlock.size() > 0) {
			System.out.println(wordsFoundInBlock);
		} else {
			System.out.println(
					"There are not words found following this traversal at each letter, "
					+ "left to right, right to left, down to up, up to down,"
					+ "traverse up-left diagonal, up-right diagonal, down-left diagonal, down-right diagonal"
					);
		}
		
	}

	/*
	 * Loads valid words from a file. Please change it accordingly for testing.
	 */
	private void loadValidWords(String validWordsLocation) {
		try {
			InputStream in = getClass().getResourceAsStream(validWordsLocation);
			BufferedReader bufferReader=new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = bufferReader.readLine()) != null) {
				validWordsDictionary.add(line);
			}
			bufferReader.close();
		} catch (Exception e) {
			throw new RuntimeException("There was an error reading the file which has valid words.");
		}
	}

	/**
	 * Converts blocks of strings into 2d char array.
	 * 
	 * @param wordBlocks
	 */
	private void convertWordsBlockInto2dArr(StringBuilder wordBlocks) {
		String[] lines = wordBlocks.toString().split(LINE_SEP);

		twoDimCharArray = new char[lines.length][lines[0].length()];

		for(int i = 0; i < lines.length; i++)  {
			String line = lines[i];
			twoDimCharArray[i] = line.toCharArray();
		}
	}

	/**
	 * Iterate over 2d array to find the possible words that match the valid words list.
	 * 
	 */
	private void findWords() {
		for (int i = 0; i < twoDimCharArray.length; i++) {
			for (int j = 0; j < twoDimCharArray[0].length; j++) {
				findWordsStartingWith(twoDimCharArray[i][j], i, j);
			}
		}
	}

	/**
	 * Find matching at starting with a character, iterate over all possible paths (as mentioned in assumptions).
	 * 
	 * @param c - starting character.
	 * @param i - row position of the character
	 * @param j - col position of the character
	 */
	private static void findWordsStartingWith(char c, int i, int j) {
		//traverse left
		StringBuilder tempWord = new StringBuilder(String.valueOf(c));
		for (int k = j - 1; k >= 0; k--) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[i][k]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse right
		tempWord = new StringBuilder(String.valueOf(c));
		for (int k = j + 1; k < twoDimCharArray[i].length; k++) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[i][k]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse up
		tempWord = new StringBuilder(String.valueOf(c));
		for (int k = i - 1; k >= 0; k--) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[k][j]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse down
		tempWord = new StringBuilder(String.valueOf(c));
		for (int k = i + 1; k < twoDimCharArray.length; k++) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[k][j]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse up-left diagonal
		tempWord = new StringBuilder(String.valueOf(c));
		int k = i-1;
		int l = j-1;
		while (k >= 0 && l >= 0) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[k--][l--]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse up-right diagonal
		tempWord = new StringBuilder(String.valueOf(c));
		k = i-1;
		l = j+1;
		while (k >= 0 && l < twoDimCharArray[i].length) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[k--][l++]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse down-left diagonal
		tempWord = new StringBuilder(String.valueOf(c));
		k = i+1;
		l = j-1;
		while (k < twoDimCharArray.length && l >= 0) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[k++][l--]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}

		//traverse down-right diagonal
		tempWord = new StringBuilder(String.valueOf(c));
		k = i+1;
		l = j+1;
		while (k < twoDimCharArray.length && l < twoDimCharArray[i].length) {
			if (validWordsDictionary.contains(tempWord.append(twoDimCharArray[k++][l++]).toString())) {
				wordsFoundInBlock.add(tempWord.toString());
			}
		}
	}
}