package kai.commands;

import kai.Ui;
import kai.tasks.Task;

/**
 * This Command marks the Task given as complete
 * Can be modified as one-use later on if needed?
 */
public class MarkCommand extends Command {
    private final Task task;

    /**
     * Constructs MarkCommand with the relevant Task
     * it needs to be applied to
     *
     * @param task the Task that needs to be marked complete
     */
    public MarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public void invoke(Ui ui) {
        task.markComplete();
        ui.showMarkCommandResults(task);
    }
}
