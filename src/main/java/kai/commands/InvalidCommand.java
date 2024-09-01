package kai.commands;

import kai.Ui;

/**
 * Generic default for when the command cannot be parsed at all
 * which performs differently based on whether it is completely empty or not
 * Can be modified as one-use later on if needed?
 */
public class InvalidCommand extends Command {
    private final boolean isEmpty;

    /**
     * Constructor for isEmpty
     *
     * @param isEmpty true if the input is completely blank
     */
    public InvalidCommand(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    @Override
    public void invoke(Ui ui) {
        ui.showInvalidCommandResults(isEmpty);
    }

}
