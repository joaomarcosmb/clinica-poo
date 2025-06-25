package org.poo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EspecialidadeMedica {
    private String nome;
    private String descricao;

    public EspecialidadeMedica() {}

    public EspecialidadeMedica(String nome, String descricao) {
        setNome(nome);
        setDescricao(descricao);
    }

    public List<String> obterProcedimentos() {
        List<String> procedimentos = new ArrayList<>();
        
        switch (nome.toLowerCase()) {
            case "cardiologia":
                procedimentos.add("Eletrocardiograma");
                procedimentos.add("Ecocardiograma");
                procedimentos.add("Teste Ergométrico");
                break;
            case "ortopedia":
                procedimentos.add("Raio-X");
                procedimentos.add("Ressonância Magnética");
                procedimentos.add("Ultrassom");
                break;
            case "dermatologia":
                procedimentos.add("Biópsia");
                procedimentos.add("Dermatoscopia");
                procedimentos.add("Crioterapia");
                break;
            default:
                procedimentos.add("Consulta Geral");
                procedimentos.add("Exame Físico");
        }
        
        return procedimentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da especialidade não pode ser nulo ou vazio");
        }
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da especialidade não pode ser nula ou vazia");
        }
        this.descricao = descricao.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecialidadeMedica that = (EspecialidadeMedica) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }
}
