import java.util.Scanner;

public class Kai {
    public static void main(String[] args) {
        String logo = "\t __ ___   _  _____ \n" +
                "\t | |/ /  /_\\ |_ _|\n" +
                "\t |   <  / _ \\ | |\n" +
                "\t |_|\\_\\/_/ \\_\\___|";
        System.out.println("\t Hello! I'm \n" + logo);
        System.out.tprintln("\t What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int ptr = 0;
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                if (ptr == 0) System.out.println("\t No tasks to list, please add some and try again?");
                for (int i = 0; i < ptr; i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks[i]);
                }
            }
            else if (!input.isEmpty()){
                if (ptr >= 100) {
                    System.out.println("\t Sorry, I'm out of space for tasks");
                    continue;
                }
                tasks[ptr] = input;
                ptr++;
                System.out.println("\t Task Added: " + input);
            } else {
                System.out.println("\t I would love to help you, but could you please give me more to work with?");
            }
        }

        System.out.println("\t Bye. Hope to see you again soon!");
    }
}
