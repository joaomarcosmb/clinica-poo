package org.poo;

import java.time.LocalDate;
import java.util.Objects;

public class Exame implements ItemHistorico {
    private TipoExame tipo;
    private LocalDate dataRealizacao;
    private String resultado;
    private boolean realizado;
    private Medico solicitante;
    private Paciente paciente;
    private Prontuario prontuario;

    public Exame() {
    }

    public Exame(TipoExame tipo, Medico solicitante, Paciente paciente) {
        this.tipo = tipo;
        this.solicitante = solicitante;
        this.paciente = paciente;
        this.realizado = false;
    }

    public Exame(Paciente paciente, String tipoExame, Medico solicitante) {
        this.paciente = paciente;
        this.solicitante = solicitante;
        this.tipo = new TipoExame(tipoExame, "Exame solicitado", false, 3);
        this.realizado = false;
    }

    public void registrarResultado(String resultado) {
        if (resultado == null || resultado.trim().isEmpty()) {
            throw new IllegalArgumentException("Resultado não pode ser nulo ou vazio");
        }

        if (!realizado) {
            throw new IllegalStateException("Exame deve ser realizado antes de registrar o resultado");
        }

        this.resultado = resultado;
    }

    public boolean remarcarExame(LocalDate novaData) {
        if (novaData == null) {
            return false;
        }

        if (novaData.isBefore(LocalDate.now())) {
            return false; // Não pode remarcar para uma data passada
        }

        if (realizado) {
            return false; // Não pode remarcar exame já realizado
        }

        this.dataRealizacao = novaData;
        return true;
    }

    public String obterStatus() {
        if (realizado && resultado != null) {
            return "Realizado - Resultado disponível";
        } else if (realizado) {
            return "Realizado - Aguardando resultado";
        } else if (dataRealizacao != null) {
            return "Agendado para " + dataRealizacao;
        } else {
            return "Solicitado - Aguardando agendamento";
        }
    }

    public void realizarExame() {
        if (dataRealizacao == null) {
            this.dataRealizacao = LocalDate.now();
        }
        this.realizado = true;
    }

    public TipoExame getTipo() {
        return tipo;
    }

    public void setTipo(TipoExame tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Medico getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Medico solicitante) {
        this.solicitante = solicitante;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    // Implementação da interface ItemHistorico
    @Override
    public LocalDate getDataEvento() {
        return dataRealizacao;
    }

    @Override
    public String getTipoEvento() {
        return "Exame";
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder descricao = new StringBuilder();
        descricao.append("Tipo: ").append(tipo != null ? tipo.getNome() : "N/A");
        descricao.append(" - Solicitante: ").append(solicitante != null ? solicitante.getNome() : "N/A");
        descricao.append(" - Status: ").append(obterStatus());
        if (resultado != null) {
            descricao.append(" - Resultado: ").append(resultado);
        }
        return descricao.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Exame exame = (Exame) o;
        return Objects.equals(tipo, exame.tipo) &&
                Objects.equals(dataRealizacao, exame.dataRealizacao) &&
                Objects.equals(paciente, exame.paciente) &&
                Objects.equals(solicitante, exame.solicitante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, dataRealizacao, paciente, solicitante);
    }

    @Override
    public String toString() {
        return "Exame{" +
                "tipo=" + (tipo != null ? tipo.getNome() : "N/A") +
                ", dataRealizacao=" + dataRealizacao +
                ", realizado=" + realizado +
                ", paciente=" + (paciente != null ? paciente.getNome() : "N/A") +
                ", solicitante=" + (solicitante != null ? solicitante.getNome() : "N/A") +
                '}';
    }
}
