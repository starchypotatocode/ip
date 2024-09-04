package kai.commands;

import kai.TaskList;
import kai.ui.Ui;

/**
 * This Command lists out the Tasks present when invoked
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs a ListCommand with the TaskList to list out
     *
     * @param taskList the TaskList to be listed out
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void invoke(Ui ui) {
        ui.showListCommandResults(taskList);
    }
}
