package org.poo;

import java.util.Objects;

public class Medicamento {
    private String nome;
    private String principioAtivo;
    private String concentracao;
    private String formaFarmaceutica;

    public Medicamento() {
    }

    public Medicamento(String nome, String principioAtivo, String concentracao, String formaFarmaceutica) {
        this.nome = nome;
        this.principioAtivo = principioAtivo;
        this.concentracao = concentracao;
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String obterInformacoesPosologia() {
        StringBuilder info = new StringBuilder();
        info.append("Medicamento: ").append(nome).append("\n");
        info.append("Princípio Ativo: ").append(principioAtivo).append("\n");
        info.append("Concentração: ").append(concentracao).append("\n");
        info.append("Forma Farmacêutica: ").append(formaFarmaceutica).append("\n");
        info.append("\nConsulte sempre um médico ou farmacêutico para informações sobre posologia adequada.");

        return info.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Medicamento that = (Medicamento) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(principioAtivo, that.principioAtivo) &&
                Objects.equals(concentracao, that.concentracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, principioAtivo, concentracao);
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nome='" + nome + '\'' +
                ", principioAtivo='" + principioAtivo + '\'' +
                ", concentracao='" + concentracao + '\'' +
                ", formaFarmaceutica='" + formaFarmaceutica + '\'' +
                '}';
    }
}