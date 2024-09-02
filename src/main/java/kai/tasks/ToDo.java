package kai.tasks;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else if (!super.equals(obj)) {
            return false;
        }

        return true;
    }
}
