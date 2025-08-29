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
     * @param input User input string.
     * @return index of task in TaskList.
     */
    private static Integer getInt(String input) {

        String[] words = input.split(" ");
        return Integer.parseInt(words[1]);
    }

    /**
     * Determines if input signals end of program.
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
     * @param ui the user interface for showing the responses.
     * @param store to store the tasks.
     * @throws BotChatException if the command is invalid.
     */
    public static void command(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        input = input.trim();
        if (input.equals("bye")) {
            ui.displayBye();
            return;
        }

        if (input.equals("list")) {
            ui.displayList(tasks);
            return;
        }

        if (input.startsWith("mark")) {
            Integer index = getInt(input);
            if (index != null && index <= tasks.size() && index > 0) {
                tasks.get(index - 1).markAsDone();
                ui.displayMark(tasks.get(index - 1));
            }
            store.saveTasks(tasks.getTasks());
            return;
        }

        if (input.startsWith("unmark")) {
            Integer index = getInt(input);
            if (index != null && index <= tasks.size() && index > 0) {
                tasks.get(index - 1).markAsNotDone();
                ui.displayUnmark(tasks.get(index - 1));
            }
            store.saveTasks(tasks.getTasks());
            return;
        }

        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new BotChatException("OH NO! todo is missing a description");
            }
            String description = input.substring(5).trim();
            Task t = new Todo(description);
            tasks.addTask(t);
            ui.displayAdd(t, tasks);
            store.saveTasks(tasks.getTasks());
            return;
        }

        if (input.startsWith("deadline")) {
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
            return;
        }

        if (input.startsWith("event")) {
            if (input.length() <= 6) {
                throw new BotChatException("OH NO! event is missing a description");
            }
            String removeEvent = input.substring(6).trim();
            String[] remaining = removeEvent.split("/from");

            if (remaining.length < 2) {
                throw new BotChatException("OH NO! event must include a /from and /to");
            }

            String description = remaining[0].trim();
            String[] remaining2 = remaining[1].split("/to");

            if (remaining2.length < 2) {
                throw new BotChatException("OH NO! event must include a /from and /to");
            }

            String from = remaining2[0].trim();
            String to = remaining2[1].trim();

            if (to.isEmpty() || from.isEmpty()) {
                throw new BotChatException("OH NO! event is missing a from or to date/time");
            }
            Task t = new Event(description, from, to);
            tasks.addTask(t);
            ui.displayAdd(t, tasks);
            store.saveTasks(tasks.getTasks());
            return;
        }

        if (input.startsWith("delete")) {
            if (input.length() <= 7) {
                throw new BotChatException("OH NO! delete is missing a value");
            }
            Integer index = getInt(input);
            if (index != null && index > 0 && index <= tasks.size()) {
                Task remove = tasks.deleteTask(index - 1);
                ui.displayDelete(remove, tasks);
            }
            store.saveTasks(tasks.getTasks());
            return;

        }

        if (input.startsWith("find")) {
            if (input.length() <= 5) {
                throw new BotChatException("OH NO! find is missing a value");
            }

            String find = input.substring(5).trim();
            TaskList findResults = tasks.find(find);
            ui.displayFind(findResults);
            return;
        }

        else {
            // Throw exception when encountered with unknown command.
            throw new BotChatException("OH NO! I DON'T KNOW WHAT COMMAND IT IS");
        }

    }
}
