import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame {
    public static void playGame(Scanner sc, Random random) {

        // Generate a random number between 0 and 99
        int limit = 100;
        int maxAttempts = 5; // Default max attempts, can be modified as needed
        int numberToGuess = random.nextInt(limit) ;

        // Welcome message and instructions
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have to guess a number between 0 and " + (limit - 1) + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess the number. Good luck!");
        int userGuess = getUserInput(sc);
        int attemptsTaken = 1;

        // Loop until the user guesses the correct number or exhausts the maximum attempts.
        while (userGuess != numberToGuess && attemptsTaken < maxAttempts) {
            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
            userGuess = getUserInput(sc);
            attemptsTaken++;
        }

        // If the user fails to guess the number within the maximum attempts, end the game.
        if (userGuess != numberToGuess) {
            System.out.println("Sorry!\nYou lost! \nYou've used all your attempts. \nThe correct number was: " + numberToGuess);
            return; // End the game if the user fails to guess the number
        }

        //When User guessed the correct number then end the game.
        System.out.println("Congratulations! You've guessed the number!");
        System.out.println("It took you " + attemptsTaken + " attempts.");
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

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        do {playGame(sc, random);}
        while (playAgain(sc));
        sc.close();
    }
}

        