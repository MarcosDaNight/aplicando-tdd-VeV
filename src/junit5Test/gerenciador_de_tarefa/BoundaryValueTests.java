package junit5Test.gerenciador_de_tarefa;

import main.gerenciador_de_tarefa.TaskManeger.Priority;
import main.gerenciador_de_tarefa.TaskManeger.Task;
import main.gerenciador_de_tarefa.TaskManeger.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundaryValueTests {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        this.taskManager = new TaskManager();
    }

    @Test
    @DisplayName("Criação de Tarefa com Prioridade Baixa")
    public void testCreateTaskWithLowPriority() {
        Task newTask = taskManager.createTask("Tarefa simples", "Descrição", "2023-08-31", Priority.LOW);

        assertEquals("Tarefa simples", newTask.getTitle());
        assertEquals("Descrição", newTask.getDescription());
        assertEquals("2023-08-31", newTask.getDueDate());
        assertEquals(Priority.LOW, newTask.getPriority());
    }

    @Test
    @DisplayName("Criação de Tarefa com Prioridade Alta")
    public void testCreateTaskWithHighPriority() {
        Task newTask = taskManager.createTask("Tarefa importante", "Descrição", "2023-08-31", Priority.HIGH);

        assertEquals("Tarefa importante", newTask.getTitle());
        assertEquals("Descrição", newTask.getDescription());
        assertEquals("2023-08-31", newTask.getDueDate());
        assertEquals(Priority.HIGH, newTask.getPriority());
    }

    @Test
    @DisplayName("Atualização de Título com Título Válido")
    public void testUpdateTitleWithValidTitle() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateTitle("Tarefa Original", "Novo Título");
        Task updatedTask = taskManager.getTask("Novo Título");

        assertEquals("Novo Título", updatedTask.getTitle());
    }

    @Test
    @DisplayName("Atualização de Título com Título Inválido")
    public void testUpdateTitleWithInvalidTitle() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateTitle("Tarefa Original", "");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("Tarefa Original", updatedTask.getTitle());
    }

    @Test
    @DisplayName("Atualização de Data com Data Válida")
    public void testUpdateDateWithValidDate() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateDate("Tarefa Original", "2023-08-30");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("2023-08-30", updatedTask.getDueDate());
    }

    @Test
    @DisplayName("Atualização de Data com Data Inválida")
    public void testUpdateDateWithInvalidDate() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateDate("Tarefa Original", "2023-08-32");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("2023-08-31", updatedTask.getDueDate());
    }
}
