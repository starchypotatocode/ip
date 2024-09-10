package kai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kai.commands.Command;
import kai.commands.DeadlineCreatorCommand;
import kai.commands.DeleteCommand;
import kai.commands.EventCreatorCommand;
import kai.commands.ExitCommand;
import kai.commands.FindCommand;
import kai.commands.InvalidCommand;
import kai.commands.ListCommand;
import kai.commands.MarkCommand;
import kai.commands.ToDoCreatorCommand;
import kai.commands.UnmarkCommand;
import kai.tasks.Deadline;
import kai.tasks.Event;
import kai.tasks.Task;
import kai.tasks.ToDo;

/**
 * Parser handles the logic of parsing input in one place
 */
public class KaiParser {
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses a string representing a Task and transforms it into an actual Task
     * (Note: Input validation is NOT proofed against save file editing)
     *
     * @param string the string representation of the Task
     * @return the Task corresponding to that representation
     * @throws IllegalArgumentException if the stored Task state is invalid
     * @throws StringIndexOutOfBoundsException if the stored Task state is invalid
     * @throws DateTimeParseException if the stored Task state is invalid
     */
    public Task parseStoredTask(String string)
            throws IllegalArgumentException,
            StringIndexOutOfBoundsException, DateTimeParseException {
        String state = string;
        String type = state.substring(0, state.indexOf(" | ") + 3);
        state = state.substring(state.indexOf(" | ") + 3);

        boolean isDone = (state.substring(0, state.indexOf(" | ")).equals("1"));
        state = state.substring(state.indexOf(" | ") + 3);

        String desc;
        Task res;
        switch (type) {
        case "T | ":
            desc = state;
            res = new ToDo(desc);
            break;
        case "D | ":
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            LocalDate deadline = LocalDate.parse(state, STORAGE_FORMATTER);
            res = new Deadline(desc, deadline);
            break;
        case "E | ":
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            LocalDate from = LocalDate.parse(
                    state.substring(0, state.indexOf(" | ")), STORAGE_FORMATTER);
            state = state.substring(state.indexOf(" | ") + 3);

            LocalDate to = LocalDate.parse(state, STORAGE_FORMATTER);
            res = new Event(desc, from, to);
            break;
        default:
            throw new IllegalArgumentException();
        }

        if (isDone) {
            res.setComplete();
        } else {
            res.setIncomplete();
        }
        return res;
    }

    /**
     * Parses the command given and takes the appropriate response thereof
     *
     * @param input the string corresponding to the command in question
     * @param taskList the TaskList some of these commands work on
     * @return the Command to be invoked later on (might be InvalidCommand at times)
     */
    public Command parseCommand(String input, TaskList taskList) {
        if (input.equals("bye")) {
            return createExitCommand();
        } else if (input.equals("list")) {
            return createListCommand(taskList);
        } else if (input.startsWith("find ")) {
            return createFindCommand(input.substring(5), taskList);
        } else if (input.startsWith("mark ")) {
            return createMarkCommand(input.substring(5), taskList);
        } else if (input.startsWith("unmark ")) {
            return createUnmarkCommand(input.substring(7), taskList);
        } else if (input.startsWith("delete ")) {
            return createDeleteCommand(input.substring(7), taskList);
        } else if (input.startsWith("todo ")) {
            return createToDoCommand(input.substring(5), taskList);
        } else if (input.startsWith("deadline ")) {
            return createDeadlineCommand(input.substring(9), taskList);
        } else if (input.startsWith("event ")) {
            return createEventCommand(input.substring(6), taskList);
        } else if (!input.isEmpty()) {
            return new InvalidCommand("\t I'm sorry, I don't recognise your command, "
                    + "the currently supported (case-sensitive, without the quotation marks) commands are:"
                    + System.lineSeparator()
                    + "\t 'bye', 'list', 'find', 'mark', 'unmark', 'delete', 'todo', 'deadline', and 'event'."
                    + System.lineSeparator()
                    + "\t Did you forget to add a space at the end of the commands"
                    + " (to input arguments if needed)?");
        } else {
            return new InvalidCommand("\t I would love to help you,"
                    + " but could you please give me more to work with?");
        }
    }

    /**
     * Creates a new ExitCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @return the Command that is created
     */
    private Command createExitCommand() {
        return new ExitCommand();
    }

    /**
     * Creates a new ListCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createListCommand(TaskList taskList) {
        return new ListCommand(taskList);
    }

    /**
     * Creates a new FindCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param searchTerm the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createFindCommand(String searchTerm, TaskList taskList) {
        if (searchTerm.isEmpty()) {
            return new InvalidCommand("\t The find command requires a non-blank search term to work properly.");
        }
        return new FindCommand(taskList, searchTerm);
    }

    /**
     * Creates a new MarkCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param trimmedIndex the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createMarkCommand(String trimmedIndex, TaskList taskList) {
        try {
            int index = Integer.parseInt(trimmedIndex) - 1;
            if (index >= taskList.size() || index < 0) {
                return new InvalidCommand("\t There is no task corresponding to"
                        + " the number you entered, please try again.");
            } else {
                return new MarkCommand(taskList.getTask(index));
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("\t The task number specified is invalid, please retry.");
        }
    }

    /**
     * Creates a new UnmarkCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param trimmedInput the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createUnmarkCommand(String trimmedInput, TaskList taskList) {
        try {
            int index = Integer.parseInt(trimmedInput) - 1;
            if (index >= taskList.size() || index < 0) {
                return new InvalidCommand("\t There is no task corresponding to"
                        + " the number you entered, please try again.");
            }
            return new UnmarkCommand(taskList.getTask(index));
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("\t The task number specified is invalid, please retry.");
        }
    }

    /**
     * Creates a new DeleteCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param trimmedIndex the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createDeleteCommand(String trimmedIndex, TaskList taskList) {
        try {
            int index = Integer.parseInt(trimmedIndex) - 1;
            if (index >= taskList.size() || index < 0) {
                return new InvalidCommand("\t There is no task corresponding to"
                        + " the number you entered, please try again.");
            }
            return new DeleteCommand(taskList, index);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("\t The task number specified is invalid, please retry.");
        }
    }

    /**
     * Creates a new ToDoCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param trimmedInput the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createToDoCommand(String trimmedInput, TaskList taskList) {
        if (trimmedInput.isEmpty()) {
            return new InvalidCommand("\t The todo command must have a name for the task.");
        }
        return new ToDoCreatorCommand(taskList, trimmedInput);
    }

    /**
     * Creates a new DeadlineCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param trimmedInput the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createDeadlineCommand(String trimmedInput, TaskList taskList) {
        if (!trimmedInput.contains(" /by ")) {
            return new InvalidCommand("\t The deadline command requires ' /by '"
                    + " without the quotation marks and then the deadline to work properly.");
        }
        String desc = trimmedInput.substring(0, trimmedInput.indexOf(" /by "));
        if (desc.isEmpty()) {
            return new InvalidCommand("\t The deadline command must have a name for the task.");
        }

        try {
            LocalDate deadline = LocalDate.parse(
                    trimmedInput.substring(trimmedInput.indexOf(" /by ") + 5), INPUT_FORMATTER);
            return new DeadlineCreatorCommand(taskList, desc, deadline);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("\t The input dates could not be parsed:"
                    + System.lineSeparator()
                    + "\t Did you follow the format of yyyy-MM-dd and pad 0s in front (eg: 2019-09-15)?");
        }
    }

    /**
     * Creates a new EventCommand if possible,
     * and returns InvalidCommand otherwise
     *
     * @param trimmedInput the relevant parts of the input
     * @param taskList the TaskList this command works on
     * @return the Command that is created
     */
    private Command createEventCommand(String trimmedInput, TaskList taskList) {
        if (!(trimmedInput.contains(" /from ") && trimmedInput.contains(" /to "))
                || trimmedInput.indexOf(" /from ") >= trimmedInput.indexOf(" /to ")) {
            return new InvalidCommand("\t The event command requires ' /from '"
                    + " without the quotation marks, the first date,"
                    + System.lineSeparator()
                    + "\t and then ' /to ' without the quotation marks"
                    + " followed by the second date to work properly.");
        }
        String desc = trimmedInput.substring(0, trimmedInput.indexOf(" /from "));
        if (desc.isEmpty()) {
            return new InvalidCommand("\t The event command must have a name for the task.");
        }

        try {
            LocalDate from = LocalDate.parse(
                    trimmedInput.substring(trimmedInput.indexOf(" /from ") + 7, trimmedInput.indexOf(" /to ")),
                    INPUT_FORMATTER);
            LocalDate to = LocalDate.parse(
                    trimmedInput.substring(trimmedInput.indexOf(" /to ") + 5), INPUT_FORMATTER);
            if (from.isAfter(to)) {
                return new InvalidCommand("\t The start date of the event"
                        + " needs to be before or equal to the end date!");
            }
            return new EventCreatorCommand(taskList, desc, from, to);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("\t The input dates could not be parsed:" + System.lineSeparator()
                    + "\t Did you follow the format of yyyy-MM-dd and pad 0s in front (eg: 2019-09-15)?");
        }
    }
}
