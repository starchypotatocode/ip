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
                if (tasks.isEmpty()) {
                    System.out.println("\t No tasks to list, please add some and try again?");
                } else {
                    System.out.println("\t Here are the tasks in your list:");
                }
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks.get(i).toString());
                }
            } else if (input.startsWith("mark ")) {
                try {
                    int id = Integer.parseInt(input.substring(5)) - 1;
                    if (id >= tasks.size() || id < 0) {
                        throw new IllegalArgumentException();
                    }
                    tasks.get(id).markComplete();
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.println("\t \t " + tasks.get(id).toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("\t The task number specified is invalid, please retry.");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int id = Integer.parseInt(input.substring(7)) - 1;
                    if (id >= tasks.size() || id < 0) {
                        throw new IllegalArgumentException();
                    }
                    tasks.get(id).markIncomplete();
                    System.out.println("\t Ok, I've marked this task as not done yet:");
                    System.out.println("\t \t " + tasks.get(id).toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("\t The task number specified is invalid, please retry.");
                }
            } else if (!input.isEmpty()) {
                try {
                    if (input.startsWith("todo ") && input.length() >= 6) {
                        tasks.add(new ToDo(input.substring(5)));
                    } else if (input.startsWith("deadline ")) {
                        String deadline = input.substring(input.indexOf("/by ") + 4);
                        tasks.add(new Deadline(input, deadline));
                    } else if (input.startsWith("event ")) {
                        String from = input.substring(input.indexOf("/from ") + 6, input.indexOf(" /to "));
                        String to = input.substring(input.indexOf(" /to ") + 5);
                        tasks.add(new Event(input, from, to));
                    } else throw new IllegalArgumentException();
                    System.out.println("\t Task Added:");
                    System.out.println("\t \t " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("\t " + "There is now " + tasks.size() + " task(s) in the list.");
                } catch (IllegalArgumentException e) {
                    System.out.println("\t Invalid task input, please check how you formatted it and try again.");
                }
            } else {
                System.out.println("\t I would love to help you, " +
                        "but could you please give me more to work with?");
            }
        }

        System.out.println("\t Bye. Hope to see you again soon!");
    }
}
