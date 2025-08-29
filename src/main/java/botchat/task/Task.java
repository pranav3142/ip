package botchat.task;

/**
 * Represents a general task that cannot be instantiated.
 */
public abstract class Task {
    /** Description of the task.*/
    protected String description;

    /** Completion status of task*/
    protected boolean isDone;

    /**
     * Constructs a Task with a description.
     *
     * @param description the String description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts completion of task into user interface X.
     *
     * @return string to be displayed in user interface.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of a task for storing.
     * Each subclass of Task implements their own format.
     *
     * @return string representation of a task for storing.
     */
    public abstract String toStorage();

    /**
     * Constructs a Task from a string in storage.
     * @param line the storage string.
     * @return the task as an object.
     */
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
