package botchat.storage;

import botchat.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;

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
        assert filePath != null : "filePath cannot be null";
        this.filePath = filePath;
    }

    /**
     * Load tasks from the storage file.
     * <p>
     * Creates the file and its parent directory if it is missing.
     * @return a list of tasks read from the file.
     */
    public ArrayList<Task> loadTasks() {
        Path path = Path.of(filePath);
        ensureFileReady(path);
        return readTasks(path);
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

        assert file.exists() : "File does not exist: " + filePath;
        assert file.canWrite() : "File is not writable: " + filePath;

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toStorage() + "\n");

            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    public void ensureFileReady(Path path) {
        try{
            if (path.getParent() != null){
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)){
                Files.createFile(path);
            }
        }catch (IOException e){
            throw new UncheckedIOException("Failed to prepare file '" + filePath + "'", e);
        }
    }

    public ArrayList<Task> readTasks(Path path) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try{
            scanner = new Scanner(path.toFile());
            while (scanner.hasNextLine()){
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                tasks.add(Task.convFromStorage(line));
            }
        } catch (FileNotFoundException e){
            throw new UncheckedIOException("Failed to open file '" + filePath + "'", e);
        } finally {
            if (scanner != null) scanner.close();
        }
        return tasks;
    }


}
