package dao;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockStatic;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TelaPrincipal;

public class TelaPrincipalTest {

    private TelaPrincipal tela;
    private TarefaDAO mockDAO;

    @BeforeEach
    public void setup() {
        mockDAO = mock(TarefaDAO.class);
        tela = new TelaPrincipal(mockDAO);
    }

    @Test
    public void testNovaTarefaComEntradaValida() {
        try (var mocked = mockStatic(JOptionPane.class)) {
            mocked.when(() -> JOptionPane.showInputDialog(any(), any()))
                  .thenReturn("Nova tarefa");

            tela.novaTarefa();

            verify(mockDAO).inserir(any(Tarefa.class));
        }
    }

    @Test
    public void testListarTarefas() {
        List<Tarefa> tarefas = Arrays.asList(
            new Tarefa(1, "Estudar"),
            new Tarefa(2, "Trabalhar")
        );

        when(mockDAO.listarTodas()).thenReturn(tarefas);

        tela.listarTarefas();

        verify(mockDAO).listarTodas();
    }
}
