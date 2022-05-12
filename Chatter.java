package a6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Chatter {

	public String welcome;
	public String farewell;
	public HashMap<String, String> file;

	/**
	 * The constructor builds a Chatter object, with set greeting, goodbye, and
	 * potential response phrases.
	 * 
	 * @param greeting      a fixed String greeting
	 * @param goodbye       a fixed String goodbye
	 * @param responsesPath the filename for a file with possible chatbot responses
	 */
	public Chatter(String greeting, String goodbye, String responsesPath) {
		this.welcome = greeting;
		this.farewell = goodbye;
		this.file = loadResponsesFromFile(responsesPath);
	}

	/**
	 * Loads lines from a file into a chatbot response map. The lines have the
	 * format of a word followed by a response for the word. The word should be all
	 * lowercase and the response can be any String.
	 * 
	 * If the file is not found, prints an error and returns an empty HashMap. Do
	 * not use a throws to send the error somewhere else. Deal with it in this
	 * method.
	 * 
	 * @param filePath the String path to the file
	 * @return a HashMap with each word as a key and response as a value
	 */
	private static HashMap<String, String> loadResponsesFromFile(String filePath) {
		// Create a new HashMap
		HashMap<String, String> responses = new HashMap<String, String>();

		// Create a new file
		File responsesFile = new File(filePath);

		// Create a scanner variable named fileScanner
		Scanner fileScanner;

		try {
			// Assign fileScanner to responseFile
			fileScanner = new Scanner(responsesFile);
			
			// while the fileScanner is going, make the first word the key
			// and the rest of the line the value
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				int space = line.indexOf(" ");
				String key = line.substring(0, space);
				String value = line.substring(space + 1);
				responses.put(key, value);
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
			return responses;

		}
		return responses;
	}

	/**
	 * Gives the single, set greeting phrase.
	 * 
	 * @return the greeting String.
	 */
	public String sayHello() {
		return welcome;
	}

	/**
	 * Gives the single, set goodbye phrase.
	 * 
	 * @return the goodbye String.
	 */
	public String sayGoodbye() {
		return farewell;
	}

	/**
	 * Picks a response phrase based on userPhrase. This method compares every word
	 * in userPhrase with the keys in the map of words and responses. If the map has
	 * the key, than add that response to a list of possible responses. After
	 * examining all the words in the userPhrase, choose one of responses randomly.
	 * You can use the Random class nextInt(upperbound) here, but the form
	 * nextInt(lower, upper) is not supported by the autograder.
	 * 
	 * Provides a default response if no match is found. The default response should
	 * be "Please tell me more about " followed by the last word in the userPhrase.
	 * Assume there is at least one word in the userPhrase.
	 * 
	 * @param userPhrase a sentence of lowercase words and no punctuation. Assume
	 *                   there is at least one word.
	 * @return a response that matches one of the words in userPhrase or a default
	 *         response.
	 */
	public String respondToPhrase(String userPhrase) {
		// Get the words from the userPhrase
		String[] words = userPhrase.split(" ");
		ArrayList<String> fileArray = new ArrayList<>();
		Random rand = new Random();

		// Build up a list of possible responses
		for (String word : words) {
			if (file.containsKey(word)) {
				fileArray.add(word);
			}
		}

		// If there is a match, pick one randomly
		if (!fileArray.isEmpty()) {
			String key = fileArray.get(rand.nextInt(fileArray.size()));
			return file.get(key);
		}

		// Otherwise use a generic response
		else {
			String genericResponse = "Please tell me more about " + words[words.length - 1];
			return genericResponse;
		}
		
	}

}