package com.mctait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

// Class to load a bigram model

public class Bigram {
	
	private String modelFile = "";
	private String delimiter = "";
	private BufferedReader br;
	private HashMap<String, Long> bigramModel = new HashMap<String, Long>();

	public Bigram(String modelFile, String delimiter) {
		this.modelFile = modelFile;
		this.delimiter = delimiter;
	}
	
	public void loadBigramFile() {
		
		String line = "";
		
		try{
			InputStream inputStream = GenerateWords.class.getResourceAsStream(modelFile);
			br = new BufferedReader(new InputStreamReader(inputStream));
			while((line = br.readLine()) != null ) {
				String[] array = line.split(delimiter);
				String bigram = array[0];
				long probability= Long.parseLong(array[1]);
				bigramModel.put(bigram, probability);
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally{
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public long getBigramFreq(String bigramToCheck) {

		String bigram = bigramToCheck.toLowerCase();
		long freq = 0;
		
		if(bigramModel != null) {
			if(bigramModel.get(bigram) != null) {
				freq = bigramModel.get(bigram);
			}
		}
		return freq;
	}
	
	public String toString() {
		return bigramModel.toString();
	}	

	public String getModelFile() {
		return modelFile;
	}

	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public HashMap<String, Long> getBigramModel() {
		return bigramModel;
	}

	public void setBigramModel(HashMap<String, Long> bigramModel) {
		this.bigramModel = bigramModel;
	}
	
}
