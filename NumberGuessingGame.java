import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame {
    public static void playGame() {

        // Generate a random number between 0 and 99
        Random random = new Random();
        int limit = 100;
        int numberToGuess = random.nextInt(limit) ;

        // Welcome message and instructions
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have to guess a number between 0 and " + limit);
        System.out.println("Enter your guess: ");
        Scanner sc = new Scanner(System.in);
        int userGuess = sc.nextInt();
        int attemptsTaken = 1;

        // Loop until the user guesses the correct number
        while (userGuess != numberToGuess) {
            attemptsTaken++;
            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
            System.out.println("Enter your guess: ");
            userGuess = sc.nextInt();
        }

        // User guessed the correct number then end the game.
        if (userGuess == numberToGuess) {
            System.out.println("Congratulations! You've guessed the number!");
            System.out.println("It took you " + attemptsTaken + " attempts.");
            int score = getScore(attemptsTaken, -1); //-1 means default max attempts of 10
            sc.close();
            return;
        }
        sc.close();
    }

    public static int getScore(int attemptsTaken, int maxAttempts) {
        maxAttempts = (maxAttempts <= 0) ? 10 : maxAttempts;
        int score = (maxAttempts + 1 - attemptsTaken) * 10;
        System.out.println("Your score is: " + score + " out of " + (maxAttempts * 10));
        return score;
    }

    

    public static void main(String[]args) {
        playGame();
        
        //playAgain();
    }
  
}
