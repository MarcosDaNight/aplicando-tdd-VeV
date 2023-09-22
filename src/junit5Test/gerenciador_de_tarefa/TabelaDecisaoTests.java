package junit5Test.gerenciador_de_tarefa;

import main.gerenciador_de_tarefa.TaskManeger.Priority;
import main.gerenciador_de_tarefa.TaskManeger.Task;
import main.gerenciador_de_tarefa.TaskManeger.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabelaDecisaoTests {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        this.taskManager = new TaskManager();
    }

    @Test
    @DisplayName("Atualização de Título e Descrição")
    public void testUpdateTitleAndDescription() {
        Task task = taskManager.createTask("Tarefa Original", "Descrição", "2023-08-31", Priority.MEDIUM);
        taskManager.updateTask("Tarefa Original", "Novo Título", "Nova Descrição", "2023-09-30", Priority.HIGH);

        Task updatedTask = taskManager.getTask("Novo Título");
        assertEquals("Novo Título", updatedTask.getTitle());
        assertEquals("Nova Descrição", updatedTask.getDescription());
        assertEquals("2023-09-30", updatedTask.getDueDate());
        assertEquals(Priority.HIGH, updatedTask.getPriority());
    }

}
