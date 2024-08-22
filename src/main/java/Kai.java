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
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            System.out.println("\t" + input);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
