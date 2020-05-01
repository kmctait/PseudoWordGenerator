package com.mctait;

import java.util.Random;

public final class Letters {

	private static String strCons = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
	private static String strVows = "aeiouAEIOU";
	private static String strConsUpper = "BCDFGHJKLMNPQRSTVWXYZ";
	private static String strVowsUpper = "AEIOU";
	
	public Letters() {}
		
	// is letter a consonant
	static public boolean isConsonant(char c) {
		if(strCons.indexOf(c) >= 0)
			return true;
		else
			return false;
	}
	
	// is letter a vowel
	static public boolean isVowel(char c) {
		if(strVows.indexOf(c) >= 0)
			return true;
		else
			return false;
	}
	
	// return a random upper case vowel
	static public char randUpperCaseVowel() {
		return strVowsUpper.charAt(new Random().nextInt(strVowsUpper.length()));
	}
	
	// return a random upper case consonant
	static public char randUpperCaseConsonant() {
		return strConsUpper.charAt(new Random().nextInt(strConsUpper.length()));
	}
}
