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

            } else if (input.startsWith("todo")) {
                String description = input.substring(5).trim();
                tasks[count] = new Todo(description);
                count++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("    "+ tasks[count-1]);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);


            } else if (input.startsWith("deadline")) {
                String removeDeadline = input.substring(9).trim();
                String[] remaining = removeDeadline.split("/by");
                tasks[count] = new Deadline(remaining[0].trim(), remaining[1].trim());
                count++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("    "+ tasks[count-1]);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);


            } else if (input.startsWith("event")) {
                String removeEvent = input.substring(6).trim();
                String[] remaining = removeEvent.split("/from");
                String description = remaining[0].trim();
                String [] remaining2 = remaining[1].split("/to");
                String from = remaining2[0].trim();
                String to = remaining2[1].trim();

                tasks[count] = new Event(description, from, to);
                count++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("    "+ tasks[count-1]);
                System.out.println("Now you have " + count + " tasks in the list.");
                System.out.println(line);


            }

        }
    }
}
