import java.util.ArrayList;
import java.util.Scanner;

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
     * Parses the command given and takes the appropriate response thereof.
     *
     * @param input the command in question
     */
    public static void parseCommand(String input, ArrayList<Task> tasks, Scanner sc) {
        if (input.equals("bye")) Ui.exit(sc);
        else if (input.equals("list")) {
            try {
                if (tasks.isEmpty()) {
                    throw new KaiException("\t There are no tasks to list. Congratulations!");
                } else {
                    System.out.println("\t Here are the tasks in your list:");
                }
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks.get(i).toString());
                }
            }
            catch (KaiException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("mark ")) {
            try {
                int id = Integer.parseInt(input.substring(5)) - 1;
                if (id >= tasks.size() || id < 0) {
                    throw new KaiException("\t There is no task corresponding to the number you entered, " +
                            "please try again.");
                }
                tasks.get(id).markComplete();
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t \t " + tasks.get(id).toString());
            } catch (IllegalArgumentException e) {
                System.out.println("\t The task number specified is invalid, please retry.");
            } catch (KaiException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int id = Integer.parseInt(input.substring(7)) - 1;
                if (id >= tasks.size() || id < 0) {
                    throw new KaiException("\t There is no task corresponding to the number you entered, " +
                            "please try again.");
                }
                tasks.get(id).markIncomplete();
                System.out.println("\t Ok, I've marked this task as not done yet:");
                System.out.println("\t \t " + tasks.get(id).toString());
            } catch (IllegalArgumentException e) {
                System.out.println("\t The task number specified is invalid, please retry.");
            } catch (KaiException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.startsWith("delete ")) {
            try {
                int id = Integer.parseInt(input.substring(7)) - 1;
                if (id >= tasks.size() || id < 0) {
                    throw new KaiException("\t There is no task corresponding to the number you entered, " +
                            "please try again.");
                }
                System.out.println("\t Noted. This task has been removed:");
                System.out.println("\t \t " + tasks.get(id).toString());
                tasks.remove(id);
                System.out.println("\t " + "There is now " + tasks.size() + " task(s) in the list.");
            } catch (IllegalArgumentException e) {
                System.out.println("\t The task number specified is invalid, please retry.");
            } catch (KaiException e) {
                System.out.println(e.getMessage());
            }
        } else if (!input.isEmpty()) {
            try {
                if (input.startsWith("todo ")) {
                    if (input.length() < 6) throw new KaiException("\t The todo command must have a name for the task.");
                    tasks.add(new ToDo(input.substring(5)));
                } else if (input.startsWith("deadline ")) {
                    if (!input.contains(" /by ")) throw new KaiException("\t The deadline command requires ' /by ' without the quotation marks " +
                            "and then the deadline to work properly.");
                    String desc = input.substring(9, input.indexOf(" /by "));
                    if (desc.isEmpty()) throw new KaiException("\t The deadline command must have a name for the task.");
                    String deadline = input.substring(input.indexOf(" /by ") + 5);
                    tasks.add(new Deadline(desc, deadline));
                } else if (input.startsWith("event ")) {
                    if (!(input.contains(" /from ") && input.contains(" /to ")) ||
                            input.indexOf(" /from ") >= input.indexOf(" /to ")) {
                        throw new KaiException("\t The event command requires ' /from ' without the quotation marks, " +
                                "the first date," + System.lineSeparator() +
                                "\t and then ' /to ' without the quotation marks " +
                                "followed by the second date to work properly.");
                    }
                    String desc = input.substring(6, input.indexOf(" /from "));
                    if (desc.isEmpty()) throw new KaiException("\t The event command must have a name for the task.");
                    String from = input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
                    String to = input.substring(input.indexOf(" /to ") + 5);
                    tasks.add(new Event(desc, from, to));
                } else throw new KaiException("\t I'm sorry, I don't recognise your command, " +
                        "the currently supported (case-sensitive, without the quotation marks) commands are:" +
                        System.lineSeparator() + "\t " +
                        "'mark', 'unmark', 'delete', 'list', 'todo', 'deadline', and 'event'." +
                        System.lineSeparator() + "\t " +
                        "Did you forget to add a space at the end of the commands to input arguments if applicable?");
                System.out.println("\t Task Added:");
                System.out.println("\t \t " + tasks.get(tasks.size() - 1).toString());
                System.out.println("\t " + "There is now " + tasks.size() + " task(s) in the list.");
            } catch (KaiException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("\t  would love to help you, but could you please give me more to work with?");
        }

    }

}
