import java.util.ArrayList;
import java.util.Scanner;

public class Kai {
    public static void main(String[] args) {
        String logo = "\t __ ___   _  _____ \n" +
                "\t | |/ /  /_\\ |_ _|\n" +
                "\t |   <  / _ \\ | |\n" +
                "\t |_|\\_\\/_/ \\_\\___|";
        System.out.println("\t Hello! I'm \n" + logo);
        System.out.println("\t What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                try {
                    if (tasks.isEmpty()) {
                        throw new KaiException("\t Ther are no tasks to list. Congratulations!");
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
                                    "the first date,\n\t and then ' /to ' without the quotation marks " +
                                    "followed by the second date to work properly.");
                        }
                        String desc = input.substring(6, input.indexOf(" /from "));
                        if (desc.isEmpty()) throw new KaiException("\t The event command must have a name for the task.");
                        String from = input.substring(input.indexOf(" /from ") + 7, input.indexOf(" /to "));
                        String to = input.substring(input.indexOf(" /to ") + 5);
                        tasks.add(new Event(desc, from, to));
                    } else throw new KaiException("\t I'm sorry, I don't recognise your command, " +
                            "the currently supported (case-sensitive) commands are:\n\t " +
                            "'mark', 'unmark', 'list', 'todo', 'deadline' and 'event'.\n\t " +
                            "Did you forget to add a space at the end of the commands to input arguments if applicable?");
                    System.out.println("\t Task Added:");
                    System.out.println("\t \t " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("\t " + "There is now " + tasks.size() + " task(s) in the list.");
                } catch (KaiException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("\t I would love to help you, " +
                        "but could you please give me more to work with?");
            }
        }
        System.out.println("\t Bye. Hope to see you again soon!");
        sc.close();
    }
}
