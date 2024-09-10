package kai;

import java.util.ArrayList;

import kai.tasks.Task;

/**
 * For managing the list of Task in an encapsulated manner
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor of empty TaskList
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor of TaskList with the given tasks
     *
     * @param tasks the list of tasks in the class
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a Task to the TaskList
     *
     * @param task the Task to be added
     */
    public void add(Task task) {
        assert task != null;
        tasks.add(task);
    }

    /**
     * Remove a Task from the TaskList, based on the index given
     * (Assumes the index given is valid, input sanitation is done elsewhere)
     *
     * @param index the index of the Task to be deleted
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Check if the TaskList is empty and return accordingly
     *
     * @return a boolean about whether the TaskList is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * To check the size of the TaskList
     *
     * @return the number of Tasks in the TaskList
     */
    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        assert tasks.get(index) != null;
        return tasks.get(index);
    }
}
