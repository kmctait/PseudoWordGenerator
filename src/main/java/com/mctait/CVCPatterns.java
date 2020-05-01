package com.mctait;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CVCPatterns {
	
	private Map<String, Integer> hmap;
	private Map<String, Integer> sortedMap;
	
	public CVCPatterns () {
		this.hmap = new HashMap<String, Integer>();
	}
	
	public void addToCVCPatterns(String token) {
		String cvcPattern = computeCVCPattern(token);

		if(this.hmap.containsKey(cvcPattern)) {
			int freq = this.hmap.get(cvcPattern);
			this.hmap.put(cvcPattern, ++freq);
		} else {
			this.hmap.put(cvcPattern, 1);			
		}
	}
	
	@Override
	public String toString() {
		return this.hmap.toString();
	}

	public int getFreq(String pattern) {
		return this.hmap.get(pattern);
	}
	
	// compute CVC pattern from input string
	private String computeCVCPattern(String token) {
		StringBuffer buf = new StringBuffer();
		
		for(char c: token.toCharArray()) {
			
			if(Letters.isConsonant(c))
				buf.append("C");
			else if(Letters.isVowel(c))
				buf.append("V");
			else
				;
		}
		return buf.toString();
	}
	
	// getTopNPatternsByFrequency
	public String[] getTopCVCPatterns(int limit) {
		String[] patterns = new String[limit];
		
		Collection<String> sortedCVCPatterns = this.sortedMap.keySet();
		Iterator<String> iter = sortedCVCPatterns.iterator();
		
		for(int i=0; i < limit; i++) {
			patterns[i] = (String)iter.next();
		}
		
		return patterns;
	}
	
	// CVCPattern to Word
	public String patternToWord(String pattern) {
		String word = new String();
		
		char[] charArray = pattern.toCharArray();
		for(char c: charArray) {
			if(c == 'C')
				word += Letters.randUpperCaseConsonant();
			else if(c == 'V')
				word += Letters.randUpperCaseVowel();
		}
		return word;
	}

	// CVCPattern to Word (validated against bigram model and bigram frequency threshold)
	public String patternToWord(String pattern, Bigram bigramModel, long threshold) {
		String word = new String();
		String filteredWord = new String();
		
		char[] charArray = pattern.toCharArray();
		for(char c: charArray) {
			if(c == 'C')
				word += Letters.randUpperCaseConsonant();
			else if(c == 'V')
				word += Letters.randUpperCaseVowel();
		}
		filteredWord = filterWord(word, bigramModel, threshold);
		
		return filteredWord;
	}
	
	private String filterWord(String word, Bigram bigramModel, long threshold) {
		// split word into bigrams
		// check each bigram is over threshold
		// if OK, proceed
		// else reject entire word and exit
		int length = word.length() -1;
		for(int i = 0; i < length; i++) {
			String bigram = word.substring(i, i+2);
			if(bigramModel.getBigramFreq(bigram) < threshold)
				return "";
		}
		return word;
	}
	
	// sort CVC patterns into descending frequency order
	public void sortCVCPatternsDesc() {
		
		Set<Entry<String,Integer>> mapEntries = this.hmap.entrySet();
		
        // used linked list to sort, because insertion of elements in linked list is faster than an array list. 
        List<Entry<String,Integer>> aList = new LinkedList<Entry<String,Integer>>(mapEntries);

        // sorting the List
        Collections.sort(aList, new Comparator<Entry<String,Integer>>() {

            public int compare(Entry<String, Integer> ele1, Entry<String, Integer> ele2) {
                return ele2.getValue().compareTo(ele1.getValue());
            }
        });
        
        // Storing the list into Linked HashMap to preserve the order of insertion. 
        this.sortedMap = new LinkedHashMap<String, Integer>();
        for(Entry<String,Integer> entry: aList) {
        	this.sortedMap.put(entry.getKey(), entry.getValue());
        }
		
	}
	
	public String printSortedCVCPatterns() {
		
		String output = new String();
		
        for(Entry<String,Integer> entry : this.sortedMap.entrySet()) {
        	output += entry.getValue();
        	output += " - ";
        	output += entry.getKey();
        	output += "\n";
        }
        
        return output;
	}
}

