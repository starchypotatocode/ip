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
        parser = new KaiParser();
        tasks = new TaskList(storage.load(parser, ui));

    }

    /**
     * Runs the appropriate Command and then gets the response to output
     */
    public String getResponse(String input) {
        ui.showWelcomeMessage();
        Scanner sc = new Scanner(System.in); //hack to clear later
        Command command = parser.parseCommand(input, tasks, sc);
        command.invoke(ui);
        storage.save(tasks);
        sc.close();
        return ui.displayPendingMessage();
    }
}
