import java.util.Scanner;

public class BotChat {

    private static Integer getInt(String input) {
        //get integer from input for mark and unmark
        String[] words = input.split(" ");
        return Integer.parseInt(words[1]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        Task[] tasks = new Task[100];

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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);

                }
                System.out.println(line);

            } else if (input.startsWith("mark")) {
                //mark feature
                Integer index = getInt(input);
                if (index != null && index <= count && index > 0) {
                    tasks[index - 1].markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[index - 1]);
                    System.out.println(line);
                }

            } else if (input.startsWith("unmark")) {
                //unmark feature
                Integer index = getInt(input);
                if (index != null && index <= count && index > 0) {
                    tasks[index - 1].markAsNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've unmarked this task as not done yet:");
                    System.out.println(tasks[index - 1]);
                    System.out.println(line);
                }

            } else {
                    // add tasks feature
                    tasks[count] = new Task(input);
                    count++;
                    System.out.println(line);
                    System.out.println("added: " + input);
                    System.out.println(line);
            }

        }
    }
}
