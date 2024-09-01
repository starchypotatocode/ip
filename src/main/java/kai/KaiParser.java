package kai;

import kai.commands.Command;
import kai.commands.CreateDeadlineCommand;
import kai.commands.CreateEventCommand;
import kai.commands.CreateToDoCommand;
import kai.commands.DeleteCommand;
import kai.commands.ExitCommand;
import kai.commands.InvalidCommand;
import kai.commands.ListCommand;
import kai.commands.MarkCommand;
import kai.commands.UnmarkCommand;
import kai.tasks.Deadline;
import kai.tasks.Event;
import kai.tasks.Task;
import kai.tasks.ToDo;

/**
 * Class to keep the logic of parsing input in one place.
 */
public class KaiParser {
    private final Ui ui;

    /**
     * Constructs a Parser to parse input
     *
     * @param ui The Ui used to display (error) messages as needed
     */
    public KaiParser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses a string representing a Task and transforms it into an actual Task
     *
     * @param state the string representation of the Task
     * @return the Task corresponding to that representation
     * @throws IllegalArgumentException if the stored Task state is invalid
     */
    public Task parseStoredTask(String state)
            throws IllegalArgumentException, StringIndexOutOfBoundsException {
        String type = state.substring(0, state.indexOf(" | ") + 3);
        state = state.substring(state.indexOf(" | ") + 3);

        boolean isDone = (state.substring(0, state.indexOf(" | ")).equals("1"));
        state = state.substring(state.indexOf(" | ") + 3);

        String desc;
        Task res;
        switch (type) {
        case "T | " -> {
            desc = state;
            res = new ToDo(desc);
        }
        case "D | " -> {
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            String deadline = state;
            res = new Deadline(desc, deadline);
        }
        case "E | " -> {
            desc = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            String from = state.substring(0, state.indexOf(" | "));
            state = state.substring(state.indexOf(" | ") + 3);

            String to = state;
            res = new Event(desc, from, to);
        }
        default -> throw new IllegalArgumentException();
        }

        if (isDone) {
            res.markComplete();
        } else {
            res.markIncomplete();
        }

        return res;
    }

    /**
     * Parses the command given and takes the appropriate response thereof.
     *
     * @param input the command in question
     */
    public Command parseCommand(String input, TaskList taskList) {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        else if (input.equals("list")) {
            return new ListCommand(taskList);
        } else if (input.startsWith("mark ")) {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= taskList.size() || index < 0) {
                    ui.showMarkCommandError("\t There is no task corresponding to" +
                            " the number you entered, please try again.");
                }
                return new MarkCommand(taskList.getTask(index));
            } catch (IllegalArgumentException e) {
                ui.showMarkCommandError("\t The task number specified is invalid, please retry.");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= taskList.size() || index < 0) {
                    ui.showUnmarkCommandError("\t There is no task corresponding to" +
                            " the number you entered, please try again.");
                }
                return new UnmarkCommand(taskList.getTask(index));
            } catch (IllegalArgumentException e) {
                ui.showUnmarkCommandError("\t The task number specified is invalid, please retry.");
            }
        } else if (input.startsWith("delete ")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= taskList.size() || index < 0) {
                    ui.showDeleteCommandError("\t There is no task corresponding to" +
                            " the number you entered, please try again.");
                }
                return new DeleteCommand(taskList, index);
            } catch (IllegalArgumentException e) {
                ui.showDeleteCommandError("\t The task number specified is invalid, please retry.");
            }
        } else if (!input.isEmpty()) {
            if (input.startsWith("todo ")) {
                    if (input.length() < 6) {
                        ui.showCreateTaskCommandError("\t The todo command must have a name for the task.");
                    }
                    return new CreateToDoCommand(taskList, input.substring(5));
                } else if (input.startsWith("deadline ")) {
                    if (!input.contains(" /by ")) {
                        ui.showCreateTaskCommandError("\t The deadline command requires ' /by '" +
                                " without the quotation marks " + "and then the deadline to work properly.");
                    }
                    String desc = input.substring(9, input.indexOf(" /by "));
                    if (desc.isEmpty()) {
                        ui.showCreateTaskCommandError("\t The deadline command must have a name for the task.");
                    }
                    String deadline = input.substring(input.indexOf(" /by ") + 5);
                    return new CreateDeadlineCommand(taskList, desc, deadline);
                } else if (input.startsWith("event ")) {
                if (!(input.contains(" /from ") && input.contains(" /to ")) ||
                        input.indexOf(" /from ") >= input.indexOf(" /to ")) {
                    ui.showCreateTaskCommandError("\t The event command requires ' /from '" +
                            " without the quotation marks, the first date," + System.lineSeparator() +
                            "\t and then ' /to ' without the quotation marks " +
                            "followed by the second date to work properly.");
                }
                String desc = input.substring(6, input.indexOf(" /from "));
                if (desc.isEmpty()) {
                    ui.showCreateTaskCommandError("\t The event command must have a name for the task.");
                }
                String from = input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
                String to = input.substring(input.indexOf(" /to ") + 5);
                return new CreateEventCommand(taskList, desc, from, to);
            } else {
                return new InvalidCommand(false);

            }
        }
        return new InvalidCommand(true);
    }
}
