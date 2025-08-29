import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public static final String LINE = "____________________________________________________________";
    public static final String NAME = "BotChat";

    public void displayWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void displayList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());

        }
        System.out.println(LINE);
    }

    public void displayMark(Task t) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println(LINE);
    }

    public void displayUnmark(Task t) {
        System.out.println(LINE);
        System.out.println("OK, I've unmarked this task as not done yet:");
        System.out.println(t);
        System.out.println(LINE);
    }

    public void displayAdd(Task t,TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void displayDelete(Task t,TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void displayBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
