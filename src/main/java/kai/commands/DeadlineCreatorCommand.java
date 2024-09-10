package kai.commands;

import java.time.LocalDate;

import kai.TaskList;
import kai.tasks.Deadline;
import kai.ui.Ui;


/**
 * This Command creates a Deadline and adds it to the TaskList
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class DeadlineCreatorCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final String desc;
    private final LocalDate deadline;

    /**
     * Constructs a CreateDeadlineCommand which creates a Deadline when invoked
     * and adds it to the taskList afterward
     *
     * @param taskList the TaskList the Task will be added to
     * @param desc the description of the Deadline
     * @param deadline the deadline in the Deadline, in LocalDate format
     */
    public DeadlineCreatorCommand(TaskList taskList, String desc, LocalDate deadline) {
        this.taskList = taskList;
        this.desc = desc;
        this.deadline = deadline;
    }

    @Override
    public void invoke(Ui ui) {
        if (!isInvoked) {
            Deadline task = new Deadline(desc, deadline);
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
