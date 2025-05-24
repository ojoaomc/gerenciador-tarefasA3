package model;

public class Tarefa {
    private int id;
    private String nome;

    public Tarefa(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da tarefa não pode ser vazio.");
        }
        this.nome = nome;
    }

    public Tarefa(int id, String nome) {
        this(nome);
        this.id = id;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da tarefa não pode ser vazio.");
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}