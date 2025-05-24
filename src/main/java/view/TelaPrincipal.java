package view;

import javax.swing.*;
import dao.TarefaDAO;
import model.Tarefa;
import java.awt.*;


public class TelaPrincipal extends JFrame {
	
    
	 private TarefaDAO tarefaDAO;

	    public TelaPrincipal(TarefaDAO tarefaDAO) {
	        this.tarefaDAO = tarefaDAO;
	        init();
	    }

	    public TelaPrincipal() {
	        this(new TarefaDAO());
	    }

	    private void init() {
	        setTitle("Sistema de Tarefas");
	        setSize(400, 300);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

	        JButton btnNovaTarefa = new JButton("Nova Tarefa");
	        JButton btnListarTarefas = new JButton("Listar Tarefas");
	        JButton btnEditarExcluirTarefa = new JButton("Editar/Excluir Tarefa");

	        btnNovaTarefa.addActionListener(e -> novaTarefa());
	        btnListarTarefas.addActionListener(e -> listarTarefas());
	        btnEditarExcluirTarefa.addActionListener(e -> editarOuExcluirTarefa());

	        panel.add(btnNovaTarefa);
	        panel.add(btnListarTarefas);
	        panel.add(btnEditarExcluirTarefa);

	        add(panel);
	        setVisible(true);
	    }

    
    public void novaTarefa() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome da tarefa:");
        try {
            tarefaDAO.inserir(new Tarefa(nome));
            JOptionPane.showMessageDialog(this, "Tarefa cadastrada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    public void listarTarefas() {
        var lista = tarefaDAO.listarTodas();
        StringBuilder sb = new StringBuilder();
        for (Tarefa t : lista) {
            sb.append(t.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhuma tarefa cadastrada.");
    }

    private void editarOuExcluirTarefa() {
        var lista = tarefaDAO.listarTodas();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma tarefa cadastrada.");
            return;
        }

        String[] opcoes = lista.stream().map(Tarefa::toString).toArray(String[]::new);
        String selecao = (String) JOptionPane.showInputDialog(this, "Escolha uma tarefa:",
            "Editar/Excluir", JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (selecao == null) return;

        int idSelecionado = Integer.parseInt(selecao.split(" - ")[0]);

        String[] acoes = {"Editar nome", "Excluir", "Cancelar"};
        int escolha = JOptionPane.showOptionDialog(this, "O que deseja fazer?", "Ação",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, acoes, acoes[0]);

        if (escolha == 0) {
            String novoNome = JOptionPane.showInputDialog(this, "Novo nome:");
            if (novoNome != null && !novoNome.isBlank()) {
                Tarefa nova = new Tarefa(idSelecionado, novoNome);
                tarefaDAO.atualizar(nova);
                JOptionPane.showMessageDialog(this, "Tarefa atualizada!");
            }
        } else if (escolha == 1) {
            tarefaDAO.excluir(idSelecionado);
            JOptionPane.showMessageDialog(this, "Tarefa excluída!");
        }
    }
    
    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
