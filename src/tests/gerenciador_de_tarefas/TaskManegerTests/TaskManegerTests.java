package tests.gerenciador_de_tarefas.TaskManegerTests;

import main.gerenciador_de_tarefa.TaskManeger.Priority;
import main.gerenciador_de_tarefa.TaskManeger.Task;
import main.gerenciador_de_tarefa.TaskManeger.TaskManeger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManegerTests {
    private TaskManeger taskManager;

    @BeforeEach
    public void setUp() {
        this.taskManager = new TaskManeger();
    }
    @Test
    public void testCreateTask() {
        this.taskManager = new TaskManeger();
        Task newTask = taskManager.createTask("Tarefa simples", "Isso é uma tarefa simples", "2023-08-31", Priority.MEDIUM);

        assertEquals("Tarefa simples", newTask.getTitle());
        assertEquals("Isso é uma tarefa simples", newTask.getDescription());
        assertEquals("2023-08-31", newTask.getDueDate());
        assertEquals(Priority.MEDIUM, newTask.getPriority());
    }

    @Test
    public void testUpdateTaskTitle() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateTitle("Tarefa Original", "Novo Título");
        Task updatedTask = taskManager.getTask("Novo Título");

        assertEquals("Novo Título", updatedTask.getTitle());
    }

    @Test
    public void testUpdateDescription() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateDescrition("Tarefa Original", "Descrição atualizada");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("Descrição atualizada", updatedTask.getDescription());
    }

    @Test
    public void testUpdateDate() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateDate("Tarefa Original", "2023-08-32");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("2023-08-32", updatedTask.getDueDate());
    }

    @Test
    public void testUpdatePriority() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updatePriority("Tarefa Original", Priority.HIGH);
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals(Priority.HIGH, updatedTask.getPriority());
    }

    @Test
    public void testUpdateTask() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);

        taskManager.updateTask("Tarefa Original", "Novo Título", "Nova Descrição", "2023-09-30", Priority.HIGH);

        Task updatedTask = taskManager.getTask("Novo Título");
        assertEquals("Novo Título", updatedTask.getTitle());
        assertEquals("Nova Descrição", updatedTask.getDescription());
        assertEquals("2023-09-30", updatedTask.getDueDate());
        assertEquals(Priority.HIGH, updatedTask.getPriority());
    }

    @Test
    public void testUpdateNonExistentTask() {
        taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);

        taskManager.updateTask("Tarefa Inexistente", "Novo Título", "Nova Descrição", "2023-09-30", Priority.HIGH);

        Task updatedTask = taskManager.getTask("Tarefa Original");
        assertEquals("Tarefa Original", updatedTask.getTitle());
        assertEquals("Descrição", updatedTask.getDescription());
        assertEquals("2023-08-31", updatedTask.getDueDate());
        assertEquals(Priority.MEDIUM, updatedTask.getPriority());
    }

    @Test
    public void testGetAllTasksSortedByDueDateAndPriority() {
        // Criação de tarefas
        Task task1 = taskManager.createTask("Tarefa 1", "Descrição 1", "2023-08-31", Priority.HIGH);
        Task task2 = taskManager.createTask("Tarefa 2", "Descrição 2", "2023-09-30", Priority.MEDIUM);
        Task task3 = taskManager.createTask("Tarefa 3", "Descrição 3", "2023-08-31", Priority.LOW);
        Task task4 = taskManager.createTask("Tarefa 4", "Descrição 4", "2023-08-30", Priority.HIGH);
        Task task5 = taskManager.createTask("Tarefa 5", "Descrição 5", "2023-08-30", Priority.LOW);

        // Obtenção das tarefas ordenadas
        List<Task> sortedTasks = taskManager.getAllTasksSortedByDueDateAndPriority();

        // Verificação da ordenação
        assertEquals("Tarefa 4", sortedTasks.get(0).getTitle());
        assertEquals("Tarefa 5", sortedTasks.get(1).getTitle());
        assertEquals("Tarefa 1", sortedTasks.get(2).getTitle());
        assertEquals("Tarefa 3", sortedTasks.get(3).getTitle());
        assertEquals("Tarefa 2", sortedTasks.get(4).getTitle());
    }



}
