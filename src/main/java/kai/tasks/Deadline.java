package kai.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline implements Task with additional deadline functionality
 */
public class Deadline extends Task {
    private final LocalDate deadline;
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

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
        return "D | " + super.serialize() + " | " + deadline.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                " (by: " + deadline.format(outputFormatter) + ")";
    }
}
