import java.util.Scanner;

public class BotChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = "BotChat";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            System.out.println(line);
            System.out.println(input);
            System.out.println(line);

        }


    }
}
