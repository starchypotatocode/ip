package kai.commands;

import kai.TaskList;
import kai.tasks.Deadline;

/**
 * This Command creates a Deadline and adds it to the TaskList
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class CreateDeadlineCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final String desc;
    private final String deadline;

    /**
     * Constructs a CreateDeadlineCommand which creates a Deadline when invoked
     * and adds it to the taskList afterward
     *
     * @param taskList the TaskList the Task will be added to
     * @param desc the description of the Deadline
     * @param deadline the deadline in the Deadline
     */
    public CreateDeadlineCommand(TaskList taskList, String desc, String deadline) {
        this.taskList = taskList;
        this.desc = desc;
        this.deadline = deadline;
    }

    @Override
    public void invoke() {
        if (!this.isInvoked) {
            Deadline task = new Deadline(this.desc, this.deadline);
            this.taskList.add(task);
        } else {
            // Ui error or debugging log maybe?
        }
        this.isInvoked = true;
    }
}
