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
        Task[] tasks = new Task[10];
        int ptr = 0;
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                if (ptr == 0) {
                    System.out.println("\t No tasks to list, please add some and try again?");
                } else {
                    System.out.println("\t Here are the tasks in your list:");
                }
                for (int i = 0; i < ptr; i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks[i].toString());
                }
            } else if (input.startsWith("mark ")) {
                try {
                    int id = Integer.parseInt(input.substring(5)) - 1;
                    if (id >= ptr || id < 0) {
                        throw new IllegalArgumentException();
                    }
                    tasks[id].markComplete();
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.println("\t " + tasks[id].toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("\t The task number specified is invalid, please retry.");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int id = Integer.parseInt(input.substring(7)) - 1;
                    if (id >= ptr || id < 0) {
                        throw new IllegalArgumentException();
                    }
                    tasks[id].markIncomplete();
                    System.out.println("\t Ok, I've marked this task as not done yet:");
                    System.out.println("\t" + tasks[id].toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("\t The task number specified is invalid, please retry.");
                }
            } else if (!input.isEmpty()) {
                try {
                    tasks[ptr] = new Task(input);
                    ptr++;
                    System.out.println("\t Task Added: " + input);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\t Sorry, I'm out of space for tasks");
                }
            } else {
                System.out.println("\t I would love to help you, but could you please give me more to work with?");
            }
        }

        System.out.println("\t Bye. Hope to see you again soon!");
    }
}
