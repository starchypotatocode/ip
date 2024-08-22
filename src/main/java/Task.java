public class Task {
    protected String desc;
    private boolean isComplete;

    public Task(String desc) {
        this.desc = desc;
        this.isComplete = false;
    }

    public final void markComplete() {
        this.isComplete = true;
    }

    public final void markIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return "[" + (this.isComplete ? "X" : " ") + "] " + this.desc;
    }
}
