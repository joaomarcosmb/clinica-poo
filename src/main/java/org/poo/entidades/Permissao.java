package org.poo.entidades;

import java.util.UUID;

public class Permissao {
    private String codigo;
    private String descricao;
    private boolean ativa;

    public Permissao(String descricao, boolean ativa) {
        this.codigo = UUID.randomUUID().toString();
        setDescricao(descricao);
        setAtiva(ativa);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da permissão não pode ser nula ou vazia");
        }
        this.descricao = descricao.trim();
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
