package Codigo;

import java.util.*;

import javax.swing.text.AbstractDocument.Content;

public class Cliente {
    private String nome;
    private String cpf;
    private String senha;
    private List<Content> contas;

    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }

     public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setContas(List<Content> contas) {
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public List<Content> getContas() {
        return contas;
    }

    public void adicionarConta(Content conta) {
        contas.add(conta);
    }
}
