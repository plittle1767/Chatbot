package a6;

import java.util.Scanner;

/**
 * A class that manages a single chat session between a user and a chatbot.
 * The chat session can be started by running this class. See the main method
 * for more details.
 * 
 * THIS FILE IS COMPLETE AND WORKING. DO NOT MODIFY IT.
 * 
 * 
 * @author David Johnson
 */
public class ChatWithUser {
    
    /**
     * Normalizes an input phrase by making it all lowercase and removing all punctuation.
     * This makes it easier for a chatbot to match words with words it knows.
     * 
     * @param phrase a String sentence in English
     * @return a normalized version of the input phrase
     */
    public static String makeLowercaseWithoutPunctuation(String phrase) {
        String lowercase = phrase.toLowerCase();
        String noPunctuation = lowercase.replaceAll("[^a-zA-Z ]", "");
        return noPunctuation;
    }

    /**
     * Runs a single chatbot session by creating a Chatter object
     * and then passing it inputs from the user until the user
     * types "stop".
     * 
     * This method also takes care of printing the chatbot responses.
     */
    public static void main(String[] args) {
    	// Make a new Chatter bot with a greeting, a goodbye phrase, and a file of words and responses.
    	Chatter bot = new Chatter("Hi, my name is K.I.T. (knowledgeable interactive technology). What would you like to talk about?",
    			"It was nice chatting with you",
    			"src/a6/responses.txt");
 
    	// Start the session with a greeting
    	System.out.println("Chatbot: " + bot.sayHello());
    	
    	// Prepare to read user input from the console
    	Scanner userInput = new Scanner(System.in);
    	
    	// Loop until "stop" is typed - the loop stops from the break command
    	while (true) {
    		// Get the user text
    		System.out.print("User: ");
    		String userResponse = userInput.nextLine();
    		// Make the user response all lowercase and remove punctuation
    		userResponse = makeLowercaseWithoutPunctuation(userResponse);

    		if (userResponse.equals("bye"))
    			break;
    		
    		// Compute and print the chatbot response
    		String botResponse = bot.respondToPhrase(userResponse);
    		System.out.println("Chatbot: " + botResponse);

    	}
    	// End the session with a goodbye phrase
		System.out.println("Chatbot: " + bot.sayGoodbye());

    }
}
