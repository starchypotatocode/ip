package kai.commands;

import kai.TaskList;
import kai.tasks.ToDo;
import kai.ui.Ui;


/**
 * This Command creates an ToDo and adds it to the TaskList
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class ToDoCreatorCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final String desc;

    /**
     * Constructs an CreateToDoCommand which creates a ToDo when invoked
     * and adds it to the taskList afterward
     *
     * @param taskList the TaskList the Task will be added to
     * @param desc the description of the ToDo
     */
    public ToDoCreatorCommand(TaskList taskList, String desc) {
        this.taskList = taskList;
        this.desc = desc;
    }

    @Override
    public void invoke(Ui ui) {
        if (!isInvoked) {
            ToDo task = new ToDo(desc);
            boolean success = taskList.add(task);
            if (success) {
                ui.showCreateTaskCommandResults(taskList);
            } else {
                ui.showDuplicateTaskMessage();
            }
        } else {
            // Ui error or debugging log maybe?
        }
        isInvoked = true;
    }
}
