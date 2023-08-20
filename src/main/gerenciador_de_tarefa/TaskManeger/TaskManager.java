package main.gerenciador_de_tarefa.TaskManeger;

import java.util.List;
import java.util.Comparator;

public class TaskManager {
    private final TaskDatabase taskDatabase = new TaskDatabase();
    public TaskManager() {
    }

    public Task createTask(String title, String description, String dueDate, Priority priority) {
        Task newTask = new Task(title, description, dueDate, priority);
        this.taskDatabase.addTask(newTask);
        return newTask;
    }

    public void updateTitle(String olderTitle, String newTitle) {
        Task taskToUpdate = getTask(olderTitle);
        if(taskIsNotNull(taskToUpdate))
            taskToUpdate.setTitle(newTitle);
    }

    public void updateDescrition(String taskTitle, String newDescription) {
        Task taskToUpdate = getTask(taskTitle);
        if(taskIsNotNull(taskToUpdate))
            taskToUpdate.setDescription(newDescription);

    }

    public void updatePriority(String taskTitle, Priority priority) {
        Task taskToUpdate = getTask(taskTitle);
        if(taskIsNotNull(taskToUpdate))
            taskToUpdate.setPriority(priority);

    }


    public void updateDate(String taskTile, String newDate) {
        Task taskToUpdate = getTask(taskTile);
        if(taskIsNotNull(taskToUpdate))
            taskToUpdate.setDueDate(newDate);
    }


    public void updateTask(String taskTitle, String newTitle, String newDescription, String newDueDate, Priority newPriority) {
        Task taskToUpdate = getTask(taskTitle);

        if (taskIsNotNull(taskToUpdate)) {
            taskToUpdate.setTitle(newTitle);
            taskToUpdate.setDescription(newDescription);
            taskToUpdate.setDueDate(newDueDate);
            taskToUpdate.setPriority(newPriority);
        }
    }

    public List<Task> getAllTasksSortedByDueDateAndPriority() {
        List<Task> tasks = taskDatabase.getAllTasks();

        // Ordena as tarefas por data de vencimento e prioridade
        tasks.sort(Comparator
                .comparing(Task::getDueDate)
                .thenComparing(Task::getPriority));

        return tasks;
    }

    public Task getTask(String taskTitle) {
        List<Task> allTasks = taskDatabase.getAllTasks();
        for (Task task : allTasks) {
            if (task.getTitle().equals(taskTitle)) {
                return task;
            }
        }
        return null;
    }

    private boolean taskIsNotNull(Task task) {
        return task != null;
    }

    public List<Task> getAllTasks() {
        return taskDatabase.getAllTasks();
    }

}
