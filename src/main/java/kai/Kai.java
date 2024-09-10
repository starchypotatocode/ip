package kai;

import kai.commands.Command;
import kai.commands.ExitCommand;
import kai.ui.Ui;

/**
 * Kai is the main control logic for chatbot functionality
 */
public class Kai {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final KaiParser parser;
    private boolean shouldRun = true;

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
     * Gets the startup message (including warnings or errors)
     * for the application to be displayed through the GUI
     */
    public String getStartupMessage() {
        ui.showWelcomeMessage();
        return ui.displayPendingMessage();
    }

    /**
     * Runs the appropriate Command and then gets the response to output
     */
    public String getResponse(String input) {
        Command command = parser.parseCommand(input, tasks);
        assert command != null;
        command.invoke(ui);
        if (command instanceof ExitCommand) { // couldn't think of a better solution w/o breaking abstraction
            shouldRun = false;
        }
        storage.save(tasks);
        return ui.displayPendingMessage();
    }

    /**
     * A method to check if the program should continue running
     *
     * @return whether the entire program should terminate
     */
    public boolean continueRunning() {
        return shouldRun;
    }
}
