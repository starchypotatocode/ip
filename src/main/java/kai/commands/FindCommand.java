package kai.commands;

import java.util.ArrayList;

import kai.TaskList;
import kai.tasks.Task;
import kai.ui.Ui;


/**
 * This Command finds and displays the Tasks in the TaskList
 * whose descriptions match the String that is given
 */
public class FindCommand extends Command {
    private final TaskList taskList;
    private final String searchTerm;

    /**
     * Constructs a FindCommand with the TaskList to search
     * and the search term in question
     *
     * @param taskList the TaskList to be searched
     * @param searchTerm the term to be searched for in the Task's descriptions
     */
    public FindCommand(TaskList taskList, String searchTerm) {
        this.taskList = taskList;
        this.searchTerm = searchTerm;
    }

    @Override
    public void invoke(Ui ui) {
        TaskList result = new TaskList(new ArrayList<>());
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(searchTerm)) {
                result.add(task);
            }
        }
        ui.showFindCommandResults(result);
    }
}
