package junit5Test.gerenciador_de_tarefa;

import main.gerenciador_de_tarefa.TaskManeger.Priority;
import main.gerenciador_de_tarefa.TaskManeger.Task;
import main.gerenciador_de_tarefa.TaskManeger.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParticaoEquivalenciaTests {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        this.taskManager = new TaskManager();
    }

    @Test
    @DisplayName("Criação de Tarefa com Prioridade Válida")
    public void testCreateTaskWithValidPriority() {
        Task newTask = taskManager.createTask("Tarefa simples", "Descrição", "2023-08-31", Priority.MEDIUM);

        assertEquals("Tarefa simples", newTask.getTitle());
        assertEquals("Descrição", newTask.getDescription());
        assertEquals("2023-08-31", newTask.getDueDate());
        assertEquals(Priority.MEDIUM, newTask.getPriority());
    }

    @Test
    @DisplayName("Criação de Tarefa com Prioridade Inválida")
    public void testCreateTaskWithInvalidPriority() {
        Task newTask = taskManager.createTask("Tarefa simples", "Descrição", "2023-08-31", Priority.valueOf("INVALID"));

        assertNull(newTask);
    }

    @Test
    @DisplayName("Atualização de Descrição com Descrição Válida")
    public void testUpdateDescriptionWithValidDescription() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateDescrition("Tarefa Original", "Nova Descrição");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("Nova Descrição", updatedTask.getDescription());
    }

    @Test
    @DisplayName("Atualização de Descrição com Descrição Inválida")
    public void testUpdateDescriptionWithInvalidDescription() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateDescrition("Tarefa Original", "");
        Task updatedTask = taskManager.getTask("Tarefa Original");

        assertEquals("Descrição", updatedTask.getDescription());
    }

}
