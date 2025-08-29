import java.time.LocalDateTime;


public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTime.INPUT_DATETIME);
        this.to = LocalDateTime.parse(to, DateTime.INPUT_DATETIME);
    }

    @Override
    public String toStorage(){
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTime.OUTPUT_DATETIME)
                + " to: " + to.format(DateTime.OUTPUT_DATETIME) + ")";
    }
}
