package kai.commands;

import kai.ui.Ui;

/**
 * Abstract base for other concrete Commands to inherit from
 * using the Command design pattern to make things easier?
 */
public abstract class Command {

    /**
     * Allows the delay of command invoking to better timings
     *
     * @param ui The Ui given for output of messages to the screen if needed
     */
    public abstract void invoke(Ui ui);
}
