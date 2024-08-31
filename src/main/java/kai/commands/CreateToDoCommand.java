package kai.commands;

import kai.TaskList;
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
     * @param desc the description of the Deadline
     */
    public CreateToDoCommand(TaskList taskList, String desc) {
        this.taskList = taskList;
        this.desc = desc;
    }

    @Override
    public void invoke() {
        if (!this.isInvoked) {
            ToDo task = new ToDo(this.desc);
            this.taskList.add(task);
        } else {
            // Ui error or debugging log maybe?
        }
        this.isInvoked = true;
    }
}
