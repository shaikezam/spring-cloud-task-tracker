package tasktracker.tasktrackerapplication.model;

public class Task {

    private int id = 3;
    private String title;
    private String description;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " [id=" + id + ", title=" + title + ", description=" + description + "] ";
    }
}
