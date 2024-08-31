package kai.commands;

/**
 * Abstract base for other concrete Commands to inherit from
 * using the Command design pattern to make things easier?
 */
public abstract class Command {

    /**
     * Allows the delay of command invoking to better timings
     */
    public abstract void invoke();
}
