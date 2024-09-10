package kai.tasks;

/**
 * Framework for a Task for the rest of the chatbot to work with
 * Marked as abstract for easy inheritance for other subclasses
 */
public abstract class Task {
    private final String desc;
    private boolean isComplete;

    /**
     * Constructs a Task with the relevant description,
     * marking it as incomplete at the start
     *
     * @param desc the description of the Task
     */
    public Task(String desc) {
        assert !desc.isEmpty();
        this.desc = desc;
        this.isComplete = false;
    }

    /**
     * Marks the task as complete (even if the task is already complete)
     */
    public final void setComplete() {
        isComplete = true;
    }

    /**
     * Marks the task as incomplete (even if the task is currently incomplete)
     */
    public final void setIncomplete() {
        isComplete = false;
    }

    public String getDescription() {
        return desc;
    }

    /**
     * Ensures that classes that inherit can have its state preserved by Storage for later recreation
     *
     * @return String that can be parsed by Storage to recreate the Task
     */
    public String serialize() {
        return (isComplete ? 1 : 0) + " | " + desc;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + desc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        }

        final Task task = (Task) obj;
        return this.isComplete == task.isComplete
                && this.desc.equals(task.desc);
    }
}
