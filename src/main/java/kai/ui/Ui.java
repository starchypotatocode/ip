package kai.ui;

import kai.TaskList;
import kai.tasks.Task;

/**
 * Ui handles output to the screen
 */
public class Ui {
    private StringBuilder pendingDisplay = new StringBuilder();

    /**
     * Stores the message corresponding the loading error/warning that occurred
     * for later printing
     *
     * @param message The error message or warning in question
     */
    public void showLoadingError(String message) {
        pendingDisplay.append(message);
    }

    /**
     * Stores the welcome message to the chatbot
     * for later printing
     */
    public void showWelcomeMessage() {
        String logo = "\t __ ___   _  _____" + System.lineSeparator()
                + "\t | |/ /  /_\\ |_ _|" + System.lineSeparator()
                + "\t |   <  / _ \\ | |" + System.lineSeparator()
                + "\t |_|\\_\\/_/ \\_\\___|";
        String res = "\t Hello! I'm" + System.lineSeparator()
                + logo + System.lineSeparator()
                + "\t What can I do for you?" + System.lineSeparator();
        pendingDisplay.append(res);
    }

    /**
     * Stores the exit message of the chatbot
     * for later printing
     */
    public void exit() {
        pendingDisplay.append("\t Bye. Hope to see you again soon!");
    }

    /**
     * Stores the Tasks in the TaskList if any and formats it accordingly
     * for later printing
     *
     * @param taskList the TaskList in question
     */
    public void showListCommandResults(TaskList taskList) {
        String temp;
        if (taskList.isEmpty()) {
            temp = "\t There are no tasks to list. Congratulations!" + System.lineSeparator();
        } else {
            temp = "\t Here are the tasks in your list:" + System.lineSeparator();
        }
        pendingDisplay.append(temp);

        for (int i = 0; i < taskList.size(); i++) {
            pendingDisplay.append("\t " + (i + 1) + ". "
                    + taskList.getTask(i).toString() + System.lineSeparator());
        }
    }

    /**
     * Stores the message of a Task being marked complete
     * for later printing
     *
     * @param task the Task in question
     */
    public void showMarkCommandResults(Task task) {
        pendingDisplay.append("\t Nice! I've marked this task as done:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + task.toString() + System.lineSeparator());
    }

    /**
     * Stores the message of a Task being marked incomplete
     * for later printing
     *
     * @param task the Task in question
     */
    public void showUnmarkCommandResults(Task task) {
        pendingDisplay.append("\t Ok, I've marked this task as not done yet:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + task.toString() + System.lineSeparator());
    }

    /**
     * Stores the description of the Task that was deleted
     * and then the number of Tasks that are left
     * for later printing
     *
     * @param taskDesc the description of the Task that was deleted
     * @param tasksLeft the number of Tasks that are left
     */
    public void showDeleteCommandResults(String taskDesc, int tasksLeft) {
        pendingDisplay.append("\t Noted. This task has been removed:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + taskDesc + System.lineSeparator());
        pendingDisplay.append("\t There is now " + tasksLeft
                + " task(s) in the list." + System.lineSeparator());
    }

    /**
     * Stores the Task(s) if any in the original TaskList
     * whose description matched the original search term
     * for later printing
     *
     * @param resultList the TaskList with the remaining Task(s) that match
     */
    public void showFindCommandResults(TaskList resultList) {
        String temp;
        if (resultList.isEmpty()) {
            temp = "\t No task's description matched the search keyword you input."
                    + System.lineSeparator();
        } else {
            temp = "\t Here are the matching tasks in your list:" + System.lineSeparator();
        }
        pendingDisplay.append(temp);

        for (int i = 0; i < resultList.size(); i++) {
            pendingDisplay.append("\t " + (i + 1) + ". "
                    + resultList.getTask(i).toString() + System.lineSeparator());
        }
    }

    /**
     * Stores the description of the created Task
     * and the current number of Tasks in the TaskList
     * for later printing
     * (Applies to any command that creates a Task)
     *
     * @param taskList the TaskList in question
     */
    public void showCreateTaskCommandResults(TaskList taskList) {
        pendingDisplay.append("\t Task Added:" + System.lineSeparator());
        pendingDisplay.append("\t \t "
                + taskList.getTask(taskList.size() - 1).toString() + System.lineSeparator());
        pendingDisplay.append("\t " + "There is now " + taskList.size()
                + " task(s) in the list." + System.lineSeparator());
    }

    /**
     * Stores the message of a valid but duplicate Task being added via commands
     */
    public void showDuplicateTaskMessage() {
        pendingDisplay.append("\t The (valid) Task could not be added, as an identical Task is already present."
                + System.lineSeparator());
    }

    /**
     * Stores the results of giving an invalid Command
     * for later printing
     *
     * @param message the message to be conveyed to the user
     */
    public void showInvalidCommandResults(String message) {
        pendingDisplay.append(message + System.lineSeparator());
    }

    /**
     * Displays the accumulated message and then deletes it
     *
     * @return the String to be displayed through the GUI
     */
    public String displayPendingMessage() {
        String message = pendingDisplay.toString();
        pendingDisplay = new StringBuilder();
        assert !message.isEmpty();
        return message;
    }
}
