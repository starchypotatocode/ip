package kai.commands;

import kai.TaskList;
import kai.Ui;
import kai.tasks.ToDo;

/**
 * This Command creates a ToDo and adds it to the TaskList
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class CreateToDoCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final String desc;

    /**
     * Constructs a CreateToDoCommand which creates a ToDo when invoked
     * and adds it to the taskList afterward
     *
     * @param taskList the TaskList the Task will be added to
     * @param desc the description of the ToDo
     */
    public CreateToDoCommand(TaskList taskList, String desc) {
        this.taskList = taskList;
        this.desc = desc;
    }

    @Override
    public void invoke(Ui ui) {
        if (!isInvoked) {
            ToDo task = new ToDo(desc);
            taskList.add(task);
            ui.showCreateTaskCommandResults(taskList);
        } else {
            // Ui error or debugging log maybe?
        }
        isInvoked = true;
    }
}
