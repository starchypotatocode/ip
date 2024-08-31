/**
 * ToDo is a concrete version of Task with no special functionality.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo with the specified description
     *
     * @param desc the description of the Task
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String serialize() {
        return "T | " + super.serialize();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
