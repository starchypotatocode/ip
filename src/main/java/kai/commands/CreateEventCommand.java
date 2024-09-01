package kai.commands;

import kai.TaskList;
import kai.Ui;
import kai.tasks.Event;

/**
 * This Command creates a Event and adds it to the TaskList
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class CreateEventCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final String desc;
    private final String from;
    private final String to;

    /**
     * Constructs a CreateEventCommand which creates a Event when invoked
     * and adds it to the taskList afterward
     *
     * @param taskList the TaskList the Task will be added to
     * @param desc the description of the Event
     * @param from the start date of the Event
     * @param to the end date of the Event
     */
    public CreateEventCommand(TaskList taskList, String desc, String from, String to) {
        this.taskList = taskList;
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void invoke(Ui ui) {
        if (!isInvoked) {
            Event task = new Event(desc, from, to);
            taskList.add(task);
            ui.showCreateTaskCommandResults(taskList);
        } else {
            // Ui error or debugging log maybe?
        }
        isInvoked = true;
    }
}
