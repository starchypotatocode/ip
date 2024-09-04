package kai.commands;

import kai.ui.Ui;

/**
 * Generic default for when the command cannot be parsed at all
 * Can be modified as one-use later on if needed?
 */
public class InvalidCommand extends Command {
    private final String message;

    /**
     * Constructor for InvalidCommand
     *
     * @param message the message to be conveyed to the user through Ui
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void invoke(Ui ui) {
        ui.showInvalidCommandResults(message);
    }

}
