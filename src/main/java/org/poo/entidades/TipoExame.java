package org.poo.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class TipoExame {
    private String nome;
    private String descricao;
    private boolean requerJejum;
    private int prazoResultado;

    public TipoExame() {
    }

    public TipoExame(String nome, String descricao, boolean requerJejum, int prazoResultado) {
        this.nome = nome;
        this.descricao = descricao;
        this.requerJejum = requerJejum;
        this.prazoResultado = prazoResultado;
    }

    public String obterInstrucoes() {
        StringBuilder instrucoes = new StringBuilder();
        instrucoes.append("Instruções para ").append(nome).append(":\n");
        instrucoes.append("Descrição: ").append(descricao).append("\n");

        if (requerJejum) {
            instrucoes.append("IMPORTANTE: É necessário jejum antes do exame.\n");
        }

        instrucoes.append("Prazo para resultado: ").append(prazoResultado).append(" dias úteis.");

        return instrucoes.toString();
    }

    public LocalDate calcularDataResultado(LocalDate dataRealizacao) {
        if (dataRealizacao == null) {
            throw new IllegalArgumentException("Data de realização não pode ser nula");
        }

        return dataRealizacao.plusDays(prazoResultado);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do tipo de exame não pode ser nulo ou vazio");
        }
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do tipo de exame não pode ser nula ou vazia");
        }
        this.descricao = descricao.trim();
    }

    public boolean isRequerJejum() {
        return requerJejum;
    }

    public void setRequerJejum(boolean requerJejum) {
        this.requerJejum = requerJejum;
    }

    public int getPrazoResultado() {
        return prazoResultado;
    }

    public void setPrazoResultado(int prazoResultado) {
        if (prazoResultado <= 0) {
            throw new IllegalArgumentException("Prazo para resultado deve ser maior que zero");
        }
        if (prazoResultado > 365) {
            throw new IllegalArgumentException("Prazo para resultado não pode ser maior que 365 dias");
        }
        this.prazoResultado = prazoResultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TipoExame tipoExame = (TipoExame) o;
        return Objects.equals(nome, tipoExame.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "TipoExame{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", requerJejum=" + requerJejum +
                ", prazoResultado=" + prazoResultado +
                '}';
    }
}