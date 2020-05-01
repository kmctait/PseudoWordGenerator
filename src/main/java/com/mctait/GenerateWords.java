package com.mctait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GenerateWords {
	// General settings
	public final static String inputFilename = "/wordlist.txt";
	public final static int mostFrequentPatterns = 10;
	public final static int wordsPerPattern = 1000;
	
	// Bigram settings
	public final static String csvFile = new String("/bigrams.json");
	public final static String delimiter = new String(",");
	public final static long threshold = 6000000000l;
	
	// input: a word list and bigram language model (EN)
	// forms CVC (Consonant / Vowel) word patterns/templates from wordlist
	// inserts random consonants or vowels into patterns and validates new generated word against bigram model
	// output: list of possible new words conforming to EN language model
	
	// References:
	// http://norvig.com/mayzner.html
	// https://www.math.cornell.edu/~mec/2003-2004/cryptography/subs/digraphs.html
	// https://gist.github.com/lydell/c439049abac2c9226e53
	
	// TODO: generate/find bigram model for different languages
	// TODO: order by most probable pseudo-words according to bigram model
	// TODO: output sorted by pseudo-word length
	// TODO: remove duplicate CVC patterns
	
	public static void main(String[] args) throws Exception {
		
		Bigram bigram = new Bigram(csvFile, delimiter);
		bigram.loadBigramFile();
		CVCPatterns patterns = new CVCPatterns();
		String[] tokens;
     
		BufferedReader in = null;
		try {
			InputStream inputStream = GenerateWords.class.getResourceAsStream(inputFilename);
			in = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while((line = in.readLine()) != null) {
				tokens = line.split("\\s+");
				for(String token : tokens) {
					patterns.addToCVCPatterns(token);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
				
		patterns.sortCVCPatternsDesc();

		String[] topPatterns = patterns.getTopCVCPatterns(mostFrequentPatterns);
		for(int i=0; i < topPatterns.length; i++) {
			for(int j=0; j < wordsPerPattern; j++) {
				String filteredWord = patterns.patternToWord(topPatterns[i], bigram, threshold);
				
				if(!filteredWord.isEmpty())
					System.out.println(filteredWord);
			}
		}
		
	}
}

