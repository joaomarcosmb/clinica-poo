package org.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta implements ItemHistorico {
    private LocalDateTime dataHora;
    private String diagnostico;
    private String tratamento;
    private boolean realizada;
    private Medico medico;
    private Paciente paciente;
    private Prontuario prontuario;

    public Consulta() {
    }

    public Consulta(LocalDateTime dataHora, String diagnostico, String tratamento, boolean realizada, Medico medico,
            Paciente paciente) {
        this.dataHora = dataHora;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.realizada = realizada;
        this.medico = medico;
        this.paciente = paciente;
    }

    public boolean alterarData(LocalDateTime novaData) {
        if (novaData == null || novaData.isBefore(LocalDateTime.now())) {
            return false; // Não pode alterar para uma data nula ou passada
        }
        this.dataHora = novaData;
        return true;
    }

    public void registrarDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.isEmpty()) {
            throw new IllegalArgumentException("Diagnóstico não pode ser nulo ou vazio");
        }
        this.diagnostico = diagnostico;
    }

    public void registrarTratamento(String tratamento) {
        if (tratamento == null || tratamento.isEmpty()) {
            throw new IllegalArgumentException("Tratamento não pode ser nulo ou vazio");
        }
        this.tratamento = tratamento;
    }

    public void finalizarConsulta() {
        if (this.realizada) {
            throw new IllegalStateException("Consulta já foi finalizada");
        }
        this.realizada = true;
    }

    public boolean cancelarConsulta() {
        if (this.realizada) {
            return false;
        }
        this.dataHora = null;
        this.diagnostico = null;
        this.tratamento = null;
        return true;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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
        return dataHora != null ? dataHora.toLocalDate() : null;
    }

    @Override
    public String getTipoEvento() {
        return "Consulta";
    }

    @Override
    public String getDescricaoDetalhada() {
        StringBuilder descricao = new StringBuilder();
        descricao.append("Médico: ").append(medico != null ? medico.getNome() : "N/A");
        if (diagnostico != null && !diagnostico.isEmpty()) {
            descricao.append(" - Diagnóstico: ").append(diagnostico);
        }
        if (tratamento != null && !tratamento.isEmpty()) {
            descricao.append(" - Tratamento: ").append(tratamento);
        }
        descricao.append(" - Status: ").append(realizada ? "Realizada" : "Agendada");
        return descricao.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(dataHora, consulta.dataHora) &&
                Objects.equals(medico, consulta.medico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataHora, medico);
    }

    @Override
    public String toString() {
        return "Consulta realizada em " + dataHora + " com o médico " + medico.getNome() + " para o paciente "
                + paciente.getNome() + " com o diagnóstico " + diagnostico + " e o tratamento " + tratamento
                + (realizada ? " e finalizada" : " e não finalizada");
    }
}
