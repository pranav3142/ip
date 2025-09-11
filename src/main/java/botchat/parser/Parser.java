package botchat.parser;

import botchat.app.BotChatException;
import botchat.storage.Store;
import botchat.task.Deadline;
import botchat.task.Event;
import botchat.task.Task;
import botchat.task.TaskList;
import botchat.task.Todo;
import botchat.ui.Ui;

/**
 * Parses user inputs from BotChat into commands.
 */
public class Parser {

    /**
     * Returns the index mentioned in the user input.
     *
     * @param input User input string.
     * @return index of task in TaskList.
     */
    private static Integer getInt(String input) {

        String[] words = input.split(" ");
        return Integer.parseInt(words[1]);
    }

    /**
     * Determines if input signals end of program.
     *
     * @param input the string entered by the user.
     * @return true if command is bye, false otherwise.
     */
    public static boolean exit(String input) {
        input = input.trim();
        return input.equals("bye");
    }

    /**
     * Interprets and executes user's command.
     *
     * @param input string entered by user.
     * @param tasks the list of tasks to be changed.
     * @param ui    the user interface for showing the responses.
     * @param store to store the tasks.
     * @throws BotChatException if the command is invalid.
     */
    public static void command(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        input = input.trim();

        if (commandBye(input, ui)) return;
        if (commandList(input, tasks, ui)) return;
        if (commandMark(input, tasks, ui, store)) return;
        if (commandUnmark(input, tasks, ui, store)) return;
        if (commandTodo(input, tasks, ui, store)) return;
        if (commandDeadline(input, tasks, ui, store)) return;
        if (commandEvent(input, tasks, ui, store)) return;
        if (commandDelete(input, tasks, ui, store)) return;
        if (commandFind(input, tasks, ui)) return;

        throw new BotChatException("Unknown command: " + input);
    }

    private static boolean commandBye(String input, Ui ui) {
        if (!input.equals("bye")) {
            return false;
        }
        ui.displayBye();
        return true;
    }

    private static boolean commandList(String input, TaskList tasks, Ui ui) {
        if (!input.equals("list")) {
            return false;
        }
        ui.displayList(tasks);
        return true;
    }

    private static boolean commandMark(String input, TaskList tasks, Ui ui, Store store) {
        if (!input.startsWith("mark")) {
            return false;
        }
        Integer index = getInt(input);
        if (index != null && index <= tasks.size() && index > 0) {
            tasks.get(index - 1).markAsDone();
            ui.displayMark(tasks.get(index - 1));
        }
        store.saveTasks(tasks.getTasks());
        return true;
    }

    private static boolean commandUnmark(String input, TaskList tasks, Ui ui, Store store) {
        if (!input.startsWith("unmark")) {
            return false;
        }
        Integer index = getInt(input);
        if (index != null && index <= tasks.size() && index > 0) {
            tasks.get(index - 1).markAsNotDone();
            ui.displayUnmark(tasks.get(index - 1));
        }
        store.saveTasks(tasks.getTasks());
        return true;

    }

    private static boolean commandTodo(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("todo")) {
            return false;
        }
        if (input.length() <= 5) {
            throw new BotChatException("OH NO! todo is missing a description");
        }
        String description = input.substring(5).trim();
        Task t = new Todo(description);
        tasks.addTask(t);
        ui.displayAdd(t, tasks);
        store.saveTasks(tasks.getTasks());
        return true;

    }

    private static boolean commandDeadline(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("deadline")) {
            return false;
        }
        if (input.length() <= 9) {
            throw new BotChatException("OH NO! deadline is missing a description");
        }

        String removeDeadline = input.substring(9).trim();
        String[] remaining = removeDeadline.split("/by");

        if (remaining.length < 2) {
            throw new BotChatException("OH NO! deadline is missing a deadline");
        }
        if (remaining[1].trim().isEmpty()) {
            throw new BotChatException("OH NO! deadline is missing a deadline");
        }

        Task t = new Deadline(remaining[0].trim(), remaining[1].trim());
        tasks.addTask(t);
        ui.displayAdd(t, tasks);
        store.saveTasks(tasks.getTasks());
        return true;
    }

    private static boolean commandEvent(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("event")) {
            return false;
        }
        if (input.length() <= 6) {
            throw new BotChatException("OH NO! event is missing a description");
        }

        String removeEvent = input.substring(6).trim();
        String[] remaining = removeEvent.split("/from");

        if (remaining.length < 2) {
            throw new BotChatException("OH NO! event must include a /from and /to");
        }

        String description = remaining[0].trim();
        String[] remainingDate = remaining[1].split("/to");

        if (remainingDate.length < 2) {
            throw new BotChatException("OH NO! event must include a /from and /to");
        }

        String from = remainingDate[0].trim();
        String to = remainingDate[1].trim();

        if (to.isEmpty() || from.isEmpty()) {
            throw new BotChatException("OH NO! event is missing a from or to date/time");
        }

        Task t = new Event(description, from, to);
        tasks.addTask(t);
        ui.displayAdd(t, tasks);
        store.saveTasks(tasks.getTasks());
        return true;

    }

    private static boolean commandDelete(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("delete")) {
            return false;
        }

        if (input.length() <= 7) {
            throw new BotChatException("OH NO! delete is missing a value");
        }

        Integer index = getInt(input);

        if (index != null && index > 0 && index <= tasks.size()) {
            Task remove = tasks.deleteTask(index - 1);
            ui.displayDelete(remove, tasks);
        }

        store.saveTasks(tasks.getTasks());
        return true;

    }

    private static boolean commandFind(String input, TaskList tasks, Ui ui) throws BotChatException {
        if (!input.startsWith("find")) {
            return false;
        }
        if (input.length() <= 5) {
            throw new BotChatException("OH NO! find is missing a value");
        }

        String find = input.substring(5).trim();
        TaskList findResults = tasks.find(find);
        ui.displayFind(findResults);
        return true;
    }
}

