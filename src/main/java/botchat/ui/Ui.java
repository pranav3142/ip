package botchat.ui;

import botchat.task.Task;
import botchat.task.TaskList;

import java.util.Scanner;

/**
 * Handle user interaction.
 */
public class Ui {
    /**
     * Scanner to read user input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Divider between commands and outputs
     */
    public static final String LINE = "____________________________________________________________";
    /**
     * Name of chatbot
     */
    public static final String NAME = "BotChat";

    /**
     * Displays the welcome message on app start.
     */
    public void displayWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Moves the scanner to read the next line.
     * @return the string in the file being read.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Displays the tasks in the list.
     * @param tasks list of tasks.
     */
    public void displayList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());

        }
        System.out.println(LINE);
    }

    /**
     * Displays message indicating task that is marked complete.
     * @param t the task that is marked as done
     */
    public void displayMark(Task t) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println(LINE);
    }

    /**
     * Displays message indicating task is marked as not completed.
     * @param t the task that is marked as not completed.
     */
    public void displayUnmark(Task t) {
        System.out.println(LINE);
        System.out.println("OK, I've unmarked this task as not done yet:");
        System.out.println(t);
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating which task is added.
     * @param t the task that is added.
     * @param tasks the list of tasks to add the task to.
     */
    public void displayAdd(Task t, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message indicating which task is deleted.
     * @param t the task that is deleted.
     * @param tasks the list of tasks to delete from.
     */
    public void displayDelete(Task t, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Bye message when program ends.
     */
    public void displayBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
