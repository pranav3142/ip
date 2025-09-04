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


    /**
     * Constructs a new BotChat instance.
     *
     * @param filePath path to where tasks will be loaded and stored.
     */
    public BotChat(String filePath) {

        store = new Store(filePath);
        tasks = new TaskList(store.loadTasks());

    }

    /**
     * Generates a reply from the BotChat for the user input.
     * @param input the command entered by the user.
     * @return BotChat's reply to the user for the command.
     */
    public String getResponse(String input) {
        try{
            Ui ui = new Ui();

            if (Parser.exit(input)) {
                Parser.command(input, this.tasks, ui, this.store);
                return ui.out();
            }

            Parser.command(input, this.tasks, ui, this.store);
            return ui.out();
        } catch ( BotChatException e){
            return e.getMessage();
        }
    }

}
