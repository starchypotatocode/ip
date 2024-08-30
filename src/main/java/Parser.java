/**
 * Class to keep the logic of parsing input in one place.
 */
public class Parser {

    /**
     * Parses a string representing a Task and transforms it into an actual Task
     *
     * @param task the string representation of the Task
     * @return the Task corresponding to that representation
     * @throws IllegalArgumentException
     */
    public static Task parseStoredTask(String task) throws IllegalArgumentException {
        String type = task.substring(0, task.indexOf(" | ") + 3);
        task = task.substring(type.length());
        boolean isDone = (task.substring(0, task.indexOf(" | ")).equals("1"));
        task = task.substring(task.indexOf(" | ") + 3);

        if (type.equals("T | ")) {
            task.substring(task.indexOf(" "))

        } else if (type.equals("D | ")) {

        } else if (type.equals("E | ")) {

        } else {
            throw new IllegalArgumentException();
        }

        throw new IllegalArgumentException();
    }

    /**
     * Parses the command given and takes the appopriate response thereof.
     *
     * @param command the command in question
     */
    public static void parseCommand(String command) {

    }

}
