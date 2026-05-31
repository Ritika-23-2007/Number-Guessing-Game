import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class NumberGuessingGame {
    public static void playGame(Scanner sc, Random random) {

        // Welcome message and instructions.
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Choose your difficulty level:");

        // Set the difficulty level and the parametres for the game.
        int[] difficultySettings = setDifficulty(sc);
        int limit = difficultySettings[0];
        int maxAttempts = difficultySettings[1];
        ArrayList<Integer> previousGuesses = new ArrayList<>(maxAttempts);

        //generating random number and start the game.
        int numberToGuess = random.nextInt(limit) ;
        System.out.println("You have to guess a number between 0 and " + (limit) + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess the number. Good luck!");

        //loop runs until the user guesses the number or exhausts all attempts.
        int attemptsTaken = 0;
        int userGuess = -1; // Initialize userGuess to an invalid value
        while (attemptsTaken < maxAttempts) {
            userGuess = getUserInput(sc);
            previousGuesses.add(userGuess);
            attemptsTaken++;

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                break; // User guessed the correct number, exit the loop.
            }
        }
        

        // If the user fails to guess the number within the maximum attempts, end the game.
        if (userGuess != numberToGuess) {
            System.out.println("Sorry!\nYou lost! \nYou've used all your attempts. \nThe correct number was: " + numberToGuess);
            System.out.println("Your previous guesses were: " + previousGuesses);
            return; // End the game if the user fails to guess the number
        }

        //When User guessed the correct number then end the game.
        System.out.println("Congratulations! You've guessed the number!");
        System.out.println("It took you " + attemptsTaken + " attempts.");
        System.out.println("Your previous guesses were: " + previousGuesses);
        getScore(attemptsTaken, maxAttempts);
    }

    public static int getScore(int attemptsTaken, int maxAttempts) {
        maxAttempts = (maxAttempts <= 0) ? 10 : maxAttempts; 
        int score = Math.max(0, maxAttempts + 1 - attemptsTaken) * 10;
        System.out.println("Your score is: " + score + " out of " + (maxAttempts * 10));
        return score;
    }

    public static boolean playAgain(Scanner sc) {
        System.out.println("Do you want to play again? (yes/no)");
        String response = sc.nextLine();
        while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            response = sc.nextLine();
        }
        if (response.equalsIgnoreCase("yes")) {return true;}
        System.out.println("Thank you for playing! Goodbye!");
        return false;
    } 
    
    public static int getUserInput(Scanner sc) {
        System.out.println("Enter your guess: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            sc.nextLine(); // Consume the invalid input
        }
        int userInput = sc.nextInt();
        sc.nextLine(); // Consume the newline left by nextInt()
        return userInput;
    }

    public static int[] setDifficulty(Scanner sc) {
        int defaultLimit = 100;
        int defaultMaxAttempts = 5;
        System.out.println("Write number to set difficulty: (1) Easy (2) Medium (3) Hard");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Defaulting to Medium difficulty.");
            sc.nextLine(); // Consume the invalid input
            return new int[]{defaultLimit, defaultMaxAttempts}; // Default to Medium difficulty
        }
        
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline left by nextInt()
        int limit, maxAttempts;
        switch (choice) {
            case 1:
                limit = 50;
                maxAttempts = 10;
                break;
            case 2:
                limit = 100;
                maxAttempts = 5;
                break;
            case 3:
                limit = 200;
                maxAttempts = 3;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Medium difficulty.");
                limit = defaultLimit;
                maxAttempts = defaultMaxAttempts;
        }
        return new int[]{limit, maxAttempts};
    }
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        do {playGame(sc, random);}
        while (playAgain(sc));
        sc.close();
    }
}

        