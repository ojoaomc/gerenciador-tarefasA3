package dao;

import model.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private Connection conexao;

    public TarefaDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:tarefas.db");
            criarTabela();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco", e);
        }
    }

    private void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL)";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (nome) VALUES (?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, tarefa.getNome());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tarefa", e);
        }
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET nome = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, tarefa.getNome());
            pstmt.setInt(2, tarefa.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tarefa", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tarefa", e);
        }
    }

    public List<Tarefa> listarTodas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tarefas.add(new Tarefa(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tarefas", e);
        }
        return tarefas;
    }
}
