package com.mechanics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JProgressBar;

import com.gui.Display;

public class Processor {
	private static char[] letters;
	public static ArrayList<String> possibleWords = new ArrayList<String>();
	private static ArrayList<String> importedWords = new ArrayList<String>();
	private static String[] wordReference;
	private static double totalWordCount = 0;
	private static double currentWord = 0;
	
	public static void process() throws IOException{
		letters = Display.getLetters();
		findWords();
		sortAndDisplay(possibleWords.toArray(new String[possibleWords.size()]), "HIGH_LOW");
		System.out.println(totalWordCount);
	}

	
	public static void setWordReference() throws IOException{
		// Open the file
		FileInputStream fstream = new FileInputStream("D:/Programing/Java/Eclipse/projects/Wordly Cheater/resources/ref/wordReference.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  importedWords.add(strLine);
		  totalWordCount++;
		}

		//Close the input stream
		br.close();
		wordReference = new String[importedWords.size()];
		importedWords.toArray(wordReference);
		//System.out.println(Arrays.toString(wordReference));       //Prints out entire word reference list to the console
	}
	
	public static void findWords(){
		for(int i = 0; i < wordReference.length; i++){
			if(letters.length > 0){
				if(checkWord(wordReference[i]))
					possibleWords.add(wordReference[i]);
				currentWord++;
				//Display.setProgressBar((int)((currentWord/totalWordCount)*100));
			}
		}
	}
	
	private static boolean checkWord(String word){
		letters = Display.getLetters();
		char[] availableLetters = letters;
		char[] characters = word.toCharArray();
		boolean[] hasCharacter = new boolean[characters.length];
		boolean allTrue = false;
		Arrays.fill(hasCharacter, false);
		
		for(int c = 0; c < characters.length; c++){
			for(int a = 0; a < availableLetters.length; a++){
				if (characters[c] == availableLetters[a]){
					availableLetters[a] = '!';
					hasCharacter[c] = true;
					break;
				}
			}
		}
		
		if(countOccurances(hasCharacter, true) == hasCharacter.length)
			allTrue = true;
		
		System.out.println(word + ": " + allTrue);
		
		return allTrue;
	}
	
	private static int countOccurances(boolean[] array, boolean bool){
		int x = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i] == bool)
				x++;
		}
		return x;
	}
	
	public static void resetLetters(){
		letters = Display.getLetters();
	}
	
	public static void sortAndDisplay(String[] words, String direction){
		words = sortArray(words, direction);
		Display.displayWords(words);
	}
	
	private static String[] sortArray(String[] strings, String direction){
		 return sortArrayByChar(sortArrayByLength(strings, direction));
	}
	
	private static String[] sortArrayByLength(String[] strings, String direction){
		boolean lastSwapped = true;
		String temp = null;
		
		if(direction == "HIGH_LOW"){
		while(lastSwapped){
			lastSwapped = false;
			
			for(int i = 0; i < strings.length-1; i++){
				if(strings[i].length() < strings[i+1].length()){
					temp = strings[i];
					strings[i] = strings[i+1];
					strings[i+1] = temp;
					lastSwapped = true;
				}
			}
		}
		}
		
		if(direction == "LOW_HIGH"){
			while(lastSwapped){
				lastSwapped = false;
				
				for(int i = 0; i < strings.length-1; i++){
					if(strings[i].length() > strings[i+1].length()){
						temp = strings[i];
						strings[i] = strings[i+1];
						strings[i+1] = temp;
						lastSwapped = true;
					}
				}
			}
			}
		return strings;
	}
	
	private static String[] sortArrayByChar(String[] strings){
		String temp = null;
		
		for(int i = 0; i < strings.length-1; i++){
			if(strings[i].length() == strings[i+1].length()){
				char[] word1 = strings[i].toCharArray();
				char[] word2 = strings[i+1].toCharArray();
				for(int j = 0; j < word1.length; j++){
					if (!(word1[j] == word2[j])){
						if(word1[j] > word2[j]){
							temp = strings[i];
							strings[i] = strings[i+1];
							strings[i+1] = temp;
						}
					}
				}
			}		
		}
		return strings;
	}
}
