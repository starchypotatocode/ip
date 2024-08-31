import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kai is the main control logic for chatbot functionality
 */
public class Kai {
    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Constructor for the main chatbot class
     *
     * @param filePath where the Tasks are stored on disk
     */
    public Kai(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }


    /**
     * Main logic loop for the chatbot
     *
     */
    public void run() {
        Ui.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            Parser.parseCommand(input, tasks, sc);
            storage.save(tasks);
        }
    }

    /**
     * Initial entry point of the entire application,
     * creates a Kai class to handle further details
     *
     * @param args
     */
    public static void main(String[] args) {
        new Kai("data/tasks.txt").run();
    }
}
