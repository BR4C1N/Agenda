package com.software.agenda;

public class Contato {
    String nome;
    String telefone;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return (nome);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return (telefone);
    }

    @Override
    public String toString() {
        return (String.format("Nome: %s | Telefone: %s", nome, telefone));
    }
}
