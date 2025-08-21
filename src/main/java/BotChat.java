import java.util.Scanner;

public class BotChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        String[] tasks = new String[100];


        String logo = "BotChat";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            //exit feature
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;

            } else if (input.equals("list")) {
                // list feature
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.println( (i+1) +". "+ tasks[i]);

                }
                System.out.println(line);

            } else{
                // add tasks feature
                tasks[count] = input;
                count++;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }

        }

    }
}
