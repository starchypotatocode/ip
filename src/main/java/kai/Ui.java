package kai;

import java.util.Scanner;

/**
 * Ui handles output to the screen
 */
public class Ui {

    /**
     * Constructor for Ui
     */
    public Ui() {

    }

    /**
     * Prints out the appropriate message for the type of loading error that occurred
     *
     * @param message The error message or warning in question
     */
    public static void showLoadingError(String message) {
        System.out.println(message);
    }


    /**
     * Prints out the welcome message to the chatbot
     */
    public static void showWelcomeMessage() {
        String logo = "\t __ ___   _  _____" + System.lineSeparator() +
                "\t | |/ /  /_\\ |_ _|" + System.lineSeparator() +
                "\t |   <  / _ \\ | |" + System.lineSeparator() +
                "\t |_|\\_\\/_/ \\_\\___|";
        System.out.println("\t Hello! I'm" + System.lineSeparator() + logo);
        System.out.println("\t What can I do for you?");
    }

    /**
     * Prints out the exit message of the chatbot and then force exits.
     */
    public static void exit(Scanner sc) {
        System.out.println("\t Bye. Hope to see you again soon!");
        sc.close();
        System.exit(0);
    }
}
