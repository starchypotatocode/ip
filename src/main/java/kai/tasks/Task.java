package kai.tasks;

/**
 * Framework for a Task for the rest of the chatbot to work with
 * Marked as abstract for easy inheritance for other subclasses
 */
public abstract class Task {

    protected String desc;
    private boolean isComplete;

    /**
     * Constructs a Task with the relevant description,
     * marking it as incomplete at the start
     *
     * @param desc the description of the Task
     */
    public Task(String desc) {
        this.desc = desc;
        this.isComplete = false;
    }

    /**
     * Marks the task as complete (even if the task is already complete)
     */
    public final void markComplete() {
        this.isComplete = true;
    }

    /**
     * Marks the task as incomplete (even if the task is currently incomplete)
     */
    public final void markIncomplete() {
        this.isComplete = false;
    }

    /**
     * Ensures that classes that inherit can have its state preserved by Storage for later recreation
     *
     * @return String that can be parsed by Storage to recreate the Task
     */
    public String serialize() {
        return (this.isComplete ? 1 : 0) + " | " + this.desc;
    }

    @Override
    public String toString() {
        return "[" + (this.isComplete ? "X" : " ") + "] " + this.desc;
    }
}
