/**
 * Deadline implements Task with additional deadline functionality
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a new Deadline with the specified description and deadline
     *
     * @param desc the description of the Task
     * @param deadline the deadline of the Task
     */
    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String serialize() {
        String state = super.toString();
        return "D | " + super.serialize() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                " (by: " + this.deadline + ")";
    }
}
