package kai.commands;

import kai.TaskList;

/**
 * This Command deletes the Task given by index
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class DeleteCommand extends Command {
    private boolean isInvoked = false;
    private final int index;
    private final TaskList taskList;

    /**
     * Constructs DeleteCommand with the relevant TaskList
     * and index of the Task to be removed
     *
     * @param taskList the TaskList in question
     * @param index the index of the Task that needs to be deleted
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void invoke() {
        if (!this.isInvoked) {
            this.taskList.remove(this.index);
        } else {
            // Ui error or debugging log maybe?
        }
        this.isInvoked = true;
    }
}
