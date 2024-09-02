package kai.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event implements Task with start and end date information
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Construct a new Event with the specified description, start and end date
     * (Likewise, assumes that from <= to and will not check during creation)
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
        return "E | " + super.serialize() + " | "
                + from.format(OUTPUT_FORMATTER) + " | " + to.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER)
                + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else if (!super.equals(obj)) {
            return false;
        }

        Event event = (Event) obj;
        return this.from.equals(event.from) && this.to.equals(event.to);
    }
}
