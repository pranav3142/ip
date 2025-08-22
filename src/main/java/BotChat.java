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
            try {
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
                    if(input.length() <= 5) {
                        throw new BotChatException("OH NO! todo is missing a description");
                    }
                    String description = input.substring(5).trim();

                    tasks[count] = new Todo(description);
                    count++;
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + tasks[count - 1]);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println(line);


                } else if (input.startsWith("deadline")) {

                    if(input.length() <= 9) {
                        throw new BotChatException("OH NO! deadline is missing a description");
                    }

                    String removeDeadline = input.substring(9).trim();
                    String[] remaining = removeDeadline.split("/by");

                    if(remaining.length < 2) {
                        throw new BotChatException("OH NO! deadline is missing a deadline");
                    }
                    if(remaining[1].trim().isEmpty()) {
                        throw new BotChatException("OH NO! deadline is missing a deadline");
                    }

                    tasks[count] = new Deadline(remaining[0].trim(), remaining[1].trim());
                    count++;
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + tasks[count - 1]);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println(line);


                } else if (input.startsWith("event")) {

                    if(input.length() <= 6) {
                        throw new BotChatException("OH NO! event is missing a description");
                    }
                    String removeEvent = input.substring(6).trim();
                    String[] remaining = removeEvent.split("/from");

                    if(remaining.length < 2) {
                        throw new BotChatException("OH NO! event must include a /from and /to");
                    }

                    String description = remaining[0].trim();
                    String[] remaining2 = remaining[1].split("/to");

                    if(remaining2.length < 2) {
                        throw new BotChatException("OH NO! event must include a /from and /to");
                    }

                    String from = remaining2[0].trim();
                    String to = remaining2[1].trim();

                    if(to.isEmpty() || from.isEmpty()) {
                        throw new BotChatException("OH NO! event is missing a from or to date/time");
                    }

                    tasks[count] = new Event(description, from, to);
                    count++;
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("    " + tasks[count - 1]);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println(line);


                }else{
                    throw new BotChatException("OH NO! I DON'T KNOW WHAT COMMAND IT IS");
                }
            } catch (BotChatException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
