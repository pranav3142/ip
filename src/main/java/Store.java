import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private final String filepath;

    public Store(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> loadTasks(){
        File file = new File(filepath);
        ArrayList<Task> tasks = new ArrayList<>();

        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }

        if(!file.exists()){
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

    public void saveTasks(ArrayList<Task> tasks){
        File file = new File(filepath);

        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toStorage() + "\n");
                //writer.write(System.lineSeparator());
            }
        } catch (IOException e){
            System.out.println("Error while saving tasks" + e.getMessage());
        }
    }

}
