package main.gerenciador_de_tarefa.TaskManeger;

public class Task {

    private String title;

    private String description;

    private String dueDate;

    private Priority priority;

    public Task() {

    }

    public Task(String title, String description, String dueDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!title.equals(task.title)) return false;
        if (!description.equals(task.description)) return false;
        if (!dueDate.equals(task.dueDate)) return false;
        return priority == task.priority;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dueDate.hashCode();
        result = 31 * result + priority.hashCode();
        return result;
    }

}
