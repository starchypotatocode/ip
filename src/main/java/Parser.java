/**
 * Class to keep the logic of parsing input in one place.
 */
public class Parser {

    /**
     * Parses a string representing a Task and transforms it into an actual Task
     *
     * @param state the string representation of the Task
     * @return the Task corresponding to that representation
     * @throws IllegalArgumentException
     */
    public static Task parseStoredTask(String state) throws IllegalArgumentException {
        String type = state.substring(0, state.indexOf(" | ") + 3);
        state = state.substring(state.indexOf(" | ") + 3);

        boolean isDone = (state.substring(0, state.indexOf(" | ")).equals("1"));
        state = state.substring(state.indexOf(" | ") + 3);

        String desc;
        Task res;

        if (type.equals("T | ")) {
            desc = state;
            res = new ToDo(desc);
        } else if (type.equals("D | ")) {
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            String deadline = state;
            res = new Deadline(desc, deadline);

        } else if (type.equals("E | ")) {
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            String from = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            String to = state;
            res = new Event(desc, from, to);
        } else {
            throw new IllegalArgumentException();
        }

        if (isDone) {
            res.markComplete();
        } else {
            res.markIncomplete();
        }

        return res;
    }

    /**
     * Parses the command given and takes the appopriate response thereof.
     *
     * @param command the command in question
     */
    public static void parseCommand(String command) {

    }

}
