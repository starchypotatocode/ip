package kai.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event implements Task with start and end date information
 */
public class Event extends Task {

    private final LocalDate from;
    private final LocalDate to;
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Construct a new ToDo with the specified description, start and end date
     *
     * @param desc the description of the Task
     * @param from the start date of the Task, represented as a LocalDate
     * @param to the end date of the Task, represented as a LocalDate
     */
    public Event(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String serialize() {
        return "E | " + super.serialize() + " | " +
                from.format(outputFormatter) + " | " + to.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from.format(outputFormatter) +
                " to: " + to.format(outputFormatter) + ")";
    }
}
