package org.poo.entidades;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, String telefone) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setTelefone(telefone);
    }

    public abstract int obterIdade();

    public abstract void atualizarContato(String novoTelefone);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (nome.trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }

        // Remove pontos, hífens e espaços
        this.cpf = cpf.replaceAll("[^0-9]", "");
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }
        if (dataNascimento.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException("Data de nascimento deve ser posterior a 1900");
        }
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }

        // Remove caracteres especiais, mantendo apenas números
        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");

        if (telefoneLimpo.length() < 10 || telefoneLimpo.length() > 11) {
            throw new IllegalArgumentException("Telefone deve ter 10 ou 11 dígitos");
        }

        this.telefone = telefoneLimpo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
