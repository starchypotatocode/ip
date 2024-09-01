package kai;

import kai.tasks.Task;

/**
 * Ui handles output to the screen
 */
public class Ui {

    /**
     * Constructor for Ui
     */
    public Ui() {

    }

    /**
     * Prints out the appropriate message for the type of loading error that occurred
     *
     * @param message The error message or warning in question
     */
    public void showLoadingError(String message) {
        System.out.println(message);
    }


    /**
     * Prints out the welcome message to the chatbot
     */
    public void showWelcomeMessage() {
        String logo = "\t __ ___   _  _____" + System.lineSeparator() +
                "\t | |/ /  /_\\ |_ _|" + System.lineSeparator() +
                "\t |   <  / _ \\ | |" + System.lineSeparator() +
                "\t |_|\\_\\/_/ \\_\\___|";
        System.out.println("\t Hello! I'm" + System.lineSeparator() + logo);
        System.out.println("\t What can I do for you?");
    }

    /**
     * Prints out the message of a Task being marked complete
     *
     * @param task the Task in question
     */
    public void showMarkCommandErrors(Task task) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t \t " + task.toString());
    }

    /**
     * Prints out the message of a Task being marked incomplete
     *
     * @param task the Task in question
     */
    public void showUnmarkCommandResults(Task task) {
        System.out.println("\t Ok, I've marked this task as not done yet:");
        System.out.println("\t \t " + task.toString());
    }

    /**
     * Prints out the description of the created Task
     * and the current number of Tasks in the TaskList
     * (Applies to any command that creates a Task)
     *
     * @param taskList the TaskList in question
     */
    public void showCreateTaskCommandResults(TaskList taskList) {
        System.out.println("\t Task Added:");
        System.out.println("\t \t " + taskList.getTask(taskList.size() - 1).toString());
        System.out.println("\t " + "There is now " + taskList.size() + " task(s) in the list.");
    }

    /**
     * Prints out the description of the Task that was deleted
     * and then the number of Tasks that are left
     *
     * @param taskDesc the description of the Task that was deleted
     * @param tasksLeft the number of Tasks that are left
     */
    public void showDeleteCommandResults(String taskDesc, int tasksLeft) {
        System.out.println("\t Noted. This task has been removed:");
        System.out.println("\t \t " + taskDesc);
        System.out.println("\t " + "There is now " + tasksLeft +
                " task(s) in the list.");
    }

    /**
     * Prints out the exit message of the chatbot and then force exits
     */
    public void exit() {
        System.out.println("\t Bye. Hope to see you again soon!");
        // Kai.closeResources() ?
        System.exit(0);
    }
}
