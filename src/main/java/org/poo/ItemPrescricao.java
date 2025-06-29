package org.poo;

import java.util.Objects;

public class ItemPrescricao {
    private Medicamento medicamento;
    private String posologia;
    private int duracaoTratamento;
    private String observacoes;

    public ItemPrescricao() {
    }

    public ItemPrescricao(Medicamento medicamento, String posologia, int duracaoTratamento, String observacoes) {
        this.medicamento = medicamento;
        this.posologia = posologia;
        this.duracaoTratamento = duracaoTratamento;
        this.observacoes = observacoes;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public int getDuracaoTratamento() {
        return duracaoTratamento;
    }

    public void setDuracaoTratamento(int duracaoTratamento) {
        this.duracaoTratamento = duracaoTratamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ItemPrescricao that = (ItemPrescricao) o;
        return duracaoTratamento == that.duracaoTratamento &&
                Objects.equals(medicamento, that.medicamento) &&
                Objects.equals(posologia, that.posologia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicamento, posologia, duracaoTratamento);
    }

    @Override
    public String toString() {
        return "ItemPrescricao{" +
                "medicamento=" + (medicamento != null ? medicamento.getNome() : "N/A") +
                ", posologia='" + posologia + '\'' +
                ", duracaoTratamento=" + duracaoTratamento + " dias" +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}