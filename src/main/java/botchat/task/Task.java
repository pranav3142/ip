package botchat.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }

    public abstract String toStorage();

    public static Task convFromStorage(String line) {
        String[] parts = line.split("|");

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3].trim();
            task = new Deadline(description, by);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            task = new Event(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type");
        }

        if (isDone){
            task.markAsDone();
        }
        return task;

    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
