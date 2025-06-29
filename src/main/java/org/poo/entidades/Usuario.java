package org.poo.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

public class Usuario extends Pessoa {
    private String login;
    private String senha;
    private Set<Permissao> permissoes;

    public Usuario() {
        super();
        this.permissoes = new HashSet<>();
    }

    public Usuario(String nome, String cpf, LocalDate dataNascimento, String telefone, String login, String senha) {
        super(nome, cpf, dataNascimento, telefone);
        setLogin(login);
        setSenha(senha);
        this.permissoes = new HashSet<>();
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public boolean possuiPermissao(String codigoPermissao) {
        return permissoes.stream().anyMatch(p -> p.getCodigo().equals(codigoPermissao));
    }

    @Override
    public int obterIdade() {
        return Period.between(super.getDataNascimento(), LocalDate.now()).getYears();
    }

    @Override
    public void atualizarContato(String novoTelefone) {
        super.setTelefone(novoTelefone);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login não pode ser nulo ou vazio");
        }
        if (login.trim().length() < 3) {
            throw new IllegalArgumentException("Login deve ter pelo menos 3 caracteres");
        }
        this.login = login.trim();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }
        this.senha = senha;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public void adicionarPermissao(Permissao permissao) {
        permissoes.add(permissao);
    }
}
