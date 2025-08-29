import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate by;

    public Deadline(String description, String by){
        super(description);
        this.by = LocalDate.parse(by, DateTime.INPUT_DATE);
    }

    @Override
    public String toStorage(){
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by.format(DateTime.OUTPUT_DATE) + ")";
    }
}
