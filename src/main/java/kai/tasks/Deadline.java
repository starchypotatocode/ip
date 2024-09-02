package kai.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline implements Task with additional deadline functionality
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate deadline;

    /**
     * Constructs a new Deadline with the specified description and deadline
     *
     * @param desc the description of the Task
     * @param deadline the deadline of the Task, represented as a LocalDate
     */
    public Deadline(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String serialize() {
        return "D | " + super.serialize() + " | " + deadline.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString()
                + " (by: " + deadline.format(OUTPUT_FORMATTER) + ")";
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

        Deadline event = (Deadline) obj;
        return this.deadline.equals(event.deadline);
    }
}
