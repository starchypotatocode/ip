package kai.commands;

import java.time.LocalDate;

import kai.TaskList;
import kai.tasks.Event;
import kai.ui.Ui;


/**
 * This Command creates an Event and adds it to the TaskList
 * It is immediately implemented as one-use
 * to avoid potential re-use issues down the line
 */
public class EventCreatorCommand extends Command {
    private boolean isInvoked = false;
    private final TaskList taskList;
    private final String desc;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a CreateEventCommand which creates an Event when invoked
     * and adds it to the taskList afterward
     *
     * @param taskList the TaskList the Task will be added to
     * @param desc the description of the Event
     * @param from the start date of the Event, in LocalDate format
     * @param to the end date of the Event, in LocalDate format
     */
    public EventCreatorCommand(TaskList taskList, String desc, LocalDate from, LocalDate to) {
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
