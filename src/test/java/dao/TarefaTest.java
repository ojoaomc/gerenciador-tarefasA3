package dao;

import org.junit.jupiter.api.*;
import model.Tarefa;

import static org.junit.jupiter.api.Assertions.*;

public class TarefaTest {

    @Test
    public void testConstrutorComNomeValido() {
        Tarefa tarefa = new Tarefa("Estudar");
        assertEquals("Estudar", tarefa.getNome());
    }

    @Test
    public void testConstrutorComNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Tarefa(""));
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("   "));
        assertThrows(IllegalArgumentException.class, () -> new Tarefa(null));
    }

    @Test
    public void testSetNomeValido() {
        Tarefa tarefa = new Tarefa("Comer");
        tarefa.setNome("Dormir");
        assertEquals("Dormir", tarefa.getNome());
    }

    @Test
    public void testSetNomeInvalido() {
        Tarefa tarefa = new Tarefa("Limpar");
        assertThrows(IllegalArgumentException.class, () -> tarefa.setNome(""));
        assertThrows(IllegalArgumentException.class, () -> tarefa.setNome("  "));
        assertThrows(IllegalArgumentException.class, () -> tarefa.setNome(null));
    }

    @Test
    public void testToString() {
        Tarefa tarefa = new Tarefa(1, "Treinar");
        assertEquals("1 - Treinar", tarefa.toString());
    }
}
