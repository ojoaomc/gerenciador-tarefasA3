package dao;

import model.Tarefa;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TarefaDAOTest {

    private static TarefaDAO dao;

    @BeforeAll
    public static void setup() {
        dao = new TarefaDAO();
    }

    @Test
    @Order(1)
    public void testInserirTarefa() {
        Tarefa tarefa = new Tarefa("Lavar a louça");
        assertDoesNotThrow(() -> dao.inserir(tarefa));
    }

    @Test
    @Order(2)
    public void testListarTarefas() {
        assertFalse(dao.listarTodas().isEmpty());
    }

    @Test
    @Order(3)
    public void testAtualizarTarefa() {
        var lista = dao.listarTodas();
        if (!lista.isEmpty()) {
            Tarefa t = lista.get(0);
            t.setNome("Nome Atualizado");
            assertDoesNotThrow(() -> dao.atualizar(t));
        }
    }

    @Test
    @Order(4)
    public void testExcluirTarefa() {
        var lista = dao.listarTodas();
        if (!lista.isEmpty()) {
            int id = lista.get(0).getId();
            assertDoesNotThrow(() -> dao.excluir(id));
        }
    }
}
