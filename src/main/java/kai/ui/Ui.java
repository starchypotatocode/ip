package kai.ui;

import kai.TaskList;
import kai.tasks.Task;

/**
 * Ui handles output to the screen by accumulating messages
 * that will be displayed later in a batch.
 */
public class Ui {
    private StringBuilder pendingDisplay = new StringBuilder();

    /**
     * Stores the message corresponding to a loading error or warning for later printing.
     *
     * @param message The error message or warning in question.
     */
    public void showLoadingError(String message) {
        pendingDisplay.append(message);
    }

    /**
     * Stores the welcome message to the chatbot for later printing.
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
     * Stores the exit message of the chatbot for later printing.
     */
    public void exit() {
        pendingDisplay.append("\t Bye. Hope to see you again soon!");
    }

    /**
     * Stores the formatted list of tasks from the TaskList for later printing.
     * If the list is empty, a congratulatory message is stored instead.
     *
     * @param taskList The TaskList to be displayed.
     */
    public void showListCommandResults(TaskList taskList) {
        if (taskList.isEmpty()) {
            pendingDisplay.append("\t There are no tasks to list. Congratulations!" + System.lineSeparator());
        } else {
            pendingDisplay.append("\t Here are the tasks in your list:" + System.lineSeparator());
        }

        for (int i = 0; i < taskList.size(); i++) {
            pendingDisplay.append("\t " + (i + 1) + ". " + taskList.getTask(i).toString() + System.lineSeparator());
        }
    }

    /**
     * Stores the message indicating that a task has been marked as done for later printing.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkCommandResults(Task task) {
        pendingDisplay.append("\t Nice! I've marked this task as done:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + task.toString() + System.lineSeparator());
    }

    /**
     * Stores the message indicating that a task has been marked as incomplete for later printing.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkCommandResults(Task task) {
        pendingDisplay.append("\t Ok, I've marked this task as not done yet:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + task.toString() + System.lineSeparator());
    }

    /**
     * Stores the message for a deleted task and the remaining number of tasks for later printing.
     *
     * @param taskDesc The description of the task that was deleted.
     * @param tasksLeft The number of tasks left in the list.
     */
    public void showDeleteCommandResults(String taskDesc, int tasksLeft) {
        pendingDisplay.append("\t Noted. This task has been removed:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + taskDesc + System.lineSeparator());
        pendingDisplay.append("\t There is now " + tasksLeft + " task(s) in the list." + System.lineSeparator());
    }

    /**
     * Stores the results of a search command that lists matching tasks in the TaskList for later printing.
     * If no tasks match, a message is stored indicating no results.
     *
     * @param resultList The TaskList containing the matching tasks.
     */
    public void showFindCommandResults(TaskList resultList) {
        if (resultList.isEmpty()) {
            pendingDisplay.append("\t No task's description matched the search keyword you input."
                    + System.lineSeparator());
        } else {
            pendingDisplay.append("\t Here are the matching tasks in your list:" + System.lineSeparator());
            for (int i = 0; i < resultList.size(); i++) {
                pendingDisplay.append("\t " + (i + 1) + ". "
                        + resultList.getTask(i).toString() + System.lineSeparator());
            }
        }
    }

    /**
     * Stores the result of a create task command, including the task description and current task count.
     *
     * @param taskList The TaskList containing the newly added task.
     */
    public void showCreateTaskCommandResults(TaskList taskList) {
        pendingDisplay.append("\t Task Added:" + System.lineSeparator());
        pendingDisplay.append("\t \t " + taskList.getTask(taskList.size() - 1).toString() + System.lineSeparator());
        pendingDisplay.append("\t There is now " + taskList.size() + " task(s) in the list." + System.lineSeparator());
    }

    /**
     * Stores a message indicating that an identical task already exists and cannot be added.
     */
    public void showDuplicateTaskMessage() {
        pendingDisplay.append("\t The (valid) Task could not be added, as an identical Task is already present."
                + System.lineSeparator());
    }

    /**
     * Stores the result of an invalid command, including the error message for later printing.
     *
     * @param message The error message to be displayed.
     */
    public void showInvalidCommandResults(String message) {
        pendingDisplay.append(message + System.lineSeparator());
    }

    /**
     * Displays the accumulated messages that were stored and resets the pending display.
     *
     * @return The formatted string containing all pending messages for display.
     */
    public String displayPendingMessage() {
        String message = pendingDisplay.toString();
        pendingDisplay = new StringBuilder(); // Reset after displaying
        assert !message.isEmpty(); // Ensure there's something to display
        return message;
    }
}
