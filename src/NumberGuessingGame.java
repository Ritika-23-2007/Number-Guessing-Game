import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class NumberGuessingGame {

    //the following methods are called inside playGame() method and the rest are called inside main() method.
    //setDifficulty() method
    //getUserInput() method
    //hintSystem() method
    //getScore() method

    public static int[] setDifficulty(Scanner sc) {
        int defaultLimit = 100;
        int defaultMaxAttempts = 5;
        int limit, maxAttempts;
        System.out.println("Write number to set difficulty: (1) Easy (2) Medium (3) Hard");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Defaulting to Medium difficulty.");
            sc.nextLine(); // Consume the invalid input
            return new int[]{defaultLimit, defaultMaxAttempts}; // Default to Medium difficulty
        }
        
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline left by nextInt()
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

    public static int playGame(Scanner sc, Random random) {

        // Welcome message and instructions.
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Choose your difficulty level:");

        // Set the difficulty level and the parametres for the game.
        int[] difficultySettings = setDifficulty(sc);
        //in setDifficulty(), difficultySettings[0] is the limit for the random number and difficultySettings[1] is the maximum attempts allowed.
        int limit = difficultySettings[0];
        int maxAttempts = difficultySettings[1];
        ArrayList<Integer> previousGuesses = new ArrayList<>(maxAttempts);

        //generating random number and start the game.
        int numberToGuess = random.nextInt(limit) ;
        System.out.println("You have to guess a number between 0 and " + (limit) + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess the number. Good luck!");

        //loop runs until the user guesses the number or exhausts all attempts.
        int attemptsTaken = 0;
        int attemptsLeft = maxAttempts;
        int userGuess = -1; // Initialize userGuess to an invalid value
        while (attemptsTaken < maxAttempts) {
            userGuess = getUserInput(sc);
            previousGuesses.add(userGuess);
            
            if (userGuess == numberToGuess) {
                break; // User guessed the correct number, exit the loop.
            }
            
            attemptsTaken++;
            attemptsLeft--;
            
            // Provide feedback to the user after each guess except the last.
            //Since attemptsTaken is incremented before the feedback, we check if it's less than maxAttempts to ensure feedback is given only when there are attempts left.

            if (userGuess < numberToGuess && attemptsTaken < maxAttempts) {
                System.out.println("Too low! Try again.");
                hintSystem(numberToGuess , attemptsLeft);
            } else if (userGuess > numberToGuess && attemptsTaken < maxAttempts) {
                System.out.println("Too high! Try again.");
                hintSystem(numberToGuess , attemptsLeft);
            }
        }
        
        // If the user fails to guess the number within the maximum attempts, end the game.
        if (userGuess != numberToGuess) {
            System.out.println("Sorry!\nYou lost! \nYou've used all your attempts. \nThe correct number was: " + numberToGuess);
            System.out.println("Your previous guesses were: " + previousGuesses);
            }

        //When User guessed the correct number then end the game.
        else {
            System.out.println("Congratulations! You've guessed the number!");
            System.out.println("It took you " + (attemptsTaken + 1) + " attempts."); //for score calculation, we considered only wrong attempts, so we added 1 to attemptsTaken to include the correct guess.
            System.out.println("Your previous guesses were: " + previousGuesses);
        }
        //Calculate and display the score based on the number of attempts taken and the maximum attempts allowed.
        return getScore(attemptsTaken, maxAttempts);
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

    public static void hintSystem(int numberToGuess, int attemptsLeft) {
        switch (attemptsLeft) {
            case 1:
                System.out.println("Hint: The number is between " + ((numberToGuess / 10) * 10) + " and " + (((numberToGuess / 10) * 10) + 10) + ".");
                break;
            case 2:
                System.out.println("Hint: The number is " + (numberToGuess % 2 == 0 ? "even." : "odd."));
                break;
            default:
                break;
        }
    }

    public static int getScore(int attemptsTaken, int maxAttempts) {
        maxAttempts = (maxAttempts <= 0) ? 10 : maxAttempts; //-ve max attempts represent default value.
        int attemptsLeft = maxAttempts - attemptsTaken;
        int score = (attemptsLeft * 100) / maxAttempts;
        System.out.println("Your score is: " + score + " out of " + (100));
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
    
    public static void displayScores(ArrayList<Integer> scores) {

        //print the scores of all games played in the current session.
        System.out.print("Scores of all games played:");
        System.out.println(scores);
        

        //Calculate and display the highest score across all games played.
        int highestScore = Collections.max(scores);
        System.out.println("Highest score across all games: " + highestScore);

        //Calculate and display the total points across all games played.
        int totalPoints = 0;
        for (int score : scores) {
            totalPoints += score;
        }
        System.out.println("Total points across all games: " + totalPoints);
    }

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Integer> scores = new ArrayList<>();
        int currentScore ;
        do {
            currentScore = playGame(sc, random);
            scores.add(currentScore);
        }
        while (playAgain(sc));
        displayScores(scores);
        sc.close();
    }
}

        