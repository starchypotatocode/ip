package kai.commands;

import kai.TaskList;
import kai.ui.Ui;

/**
 * This Command deletes the Task given by index
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class DeleteCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final int index;


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
    public void invoke(Ui ui) {
        if (!isInvoked) {
            String taskDesc = taskList.getTask(index).toString();
            taskList.remove(index);

            int tasksLeft = taskList.size();
            ui.showDeleteCommandResults(taskDesc, tasksLeft);
        } else {
            // Ui error or debugging log maybe?
        }
        isInvoked = true;
    }
}
