package org.poo;

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
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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