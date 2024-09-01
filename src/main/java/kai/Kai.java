package kai;

import java.util.Scanner;

import kai.commands.Command;

/**
 * Kai is the main control logic for chatbot functionality
 */
public class Kai {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final KaiParser parser;

    /**
     * Constructor for the main chatbot class
     *
     * @param filePath where the Tasks are stored on disk
     */
    public Kai(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new KaiParser(ui);
        tasks = new TaskList(storage.load(parser, ui));

    }


    /**
     * Main logic loop for the chatbot
     *
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Command command = parser.parseCommand(input, tasks);
            sc.close();

            command.invoke(ui);
            storage.save(tasks);

            // Hack to avoid having to pass a Scanner around or forget to close
            sc = new Scanner(System.in);
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
