package kai.tasks;

/**
 * Event implements Task with start and end date information
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Construct a new ToDo with the specified description, start and end date
     *
     * @param desc the description of the Task
     * @param from the start date of the Task (does not need to be a Date)
     * @param to the end date of the Task (does not need to be a Date)
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String serialize() {
        return "E | " + super.serialize() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                " (from: " + this.from + " to: " + this.to + ")";
    }
}
