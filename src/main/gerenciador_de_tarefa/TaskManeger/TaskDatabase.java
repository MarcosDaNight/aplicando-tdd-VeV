package main.gerenciador_de_tarefa.TaskManeger;

import main.gerenciador_de_tarefa.TaskManeger.Task;

import java.util.*;

public class TaskDatabase {
    private final Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getTitle(), task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteTask(String taskTitle) {
        tasks.remove(taskTitle);
    }

}
