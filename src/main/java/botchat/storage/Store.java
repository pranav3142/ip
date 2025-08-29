package botchat.storage;

import botchat.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and storing of the tasks to hard drive.
 */
public class Store {
    private final String filePath;

    /**
     * Constructs a store that manages the reading
     * and writing of information.
     * @param filePath path to the storage file.
     */
    public Store(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the storage file.
     * <p>
     * Creates the file and its parent directory if it is missing.
     * @return a list of tasks read from the file.
     */
    public ArrayList<Task> loadTasks() {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }

        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch (IOException e){
                System.out.println(e.getMessage());

            }
            return tasks;
        }

        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                tasks.add(Task.convFromStorage(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        }
        return tasks;

    }

    /**
     * Saves list of tasks into the storage file.
     * @param tasks the list of tasks to be stored.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(filePath);

        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toStorage() + "\n");

            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

}
