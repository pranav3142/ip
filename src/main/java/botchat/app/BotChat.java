package botchat.app;

import botchat.parser.Parser;
import botchat.storage.Store;
import botchat.task.TaskList;
import botchat.ui.Ui;

/**
 * Represents the main entry class for the BotChat Chatbot.
 * <p>
 * Responsible for initialising the components and starting
 * the application.
 */

public class BotChat {
    private final Store store;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new BotChat instance.
     *
     * @param filePath path to where tasks will be loaded and stored.
     */
    public BotChat(String filePath) {
        ui = new Ui();
        store = new Store(filePath);
        tasks = new TaskList(store.loadTasks());

    }
    /**
     * Runs the BotChat Chatbot.
     * <p>
     * Displays welcome message and reads user's commands,
     * exits on bye command.
     */
    public void run() {
        ui.displayWelcome();

        while (true) {
            String input = ui.nextLine();
            if (Parser.exit(input)) {
                ui.displayBye();
                break;
            }
            try {
                Parser.command(input, tasks, ui, store);

            } catch (BotChatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Start point of program.
     * @param args not in use.
     */
    public static void main(String[] args) {
        new BotChat("./data/botchat.app.BotChat.txt").run();
    }

}
