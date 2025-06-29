package org.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prontuario {
    private LocalDate dataCriacao;
    private Paciente paciente;
    private List<Consulta> consultas;
    private List<Exame> exames;

    public Prontuario() {
        this.dataCriacao = LocalDate.now();
        this.consultas = new ArrayList<>();
        this.exames = new ArrayList<>();
    }

    public Prontuario(Paciente paciente) {
        this();
        this.paciente = paciente;
    }

    public void adicionarConsulta(Consulta consulta) {
        if (consulta == null) {
            throw new IllegalArgumentException("Consulta não pode ser nula");
        }

        if (!consulta.getPaciente().equals(this.paciente)) {
            throw new IllegalArgumentException("Consulta deve ser do mesmo paciente do prontuário");
        }

        this.consultas.add(consulta);
    }

    public void adicionarExame(Exame exame) {
        if (exame == null) {
            throw new IllegalArgumentException("Exame não pode ser nulo");
        }

        if (!exame.getPaciente().equals(this.paciente)) {
            throw new IllegalArgumentException("Exame deve ser do mesmo paciente do prontuário");
        }

        this.exames.add(exame);
    }

    public List<ItemHistorico> buscarHistorico(LocalDate dataInicio, LocalDate dataFim) {
        List<ItemHistorico> historico = new ArrayList<>();

        // Adiciona consultas no período
        for (Consulta consulta : consultas) {
            LocalDate dataConsulta = consulta.getDataEvento();
            if (dataConsulta != null && isDataNoPeriodo(dataConsulta, dataInicio, dataFim)) {
                historico.add(consulta);
            }
        }

        // Adiciona exames no período
        for (Exame exame : exames) {
            LocalDate dataExame = exame.getDataEvento();
            if (dataExame != null && isDataNoPeriodo(dataExame, dataInicio, dataFim)) {
                historico.add(exame);
            }
        }

        // Ordena por data
        historico.sort((a, b) -> a.getDataEvento().compareTo(b.getDataEvento()));

        return historico;
    }

    private boolean isDataNoPeriodo(LocalDate data, LocalDate dataInicio, LocalDate dataFim) {
        return (dataInicio == null || !data.isBefore(dataInicio)) &&
                (dataFim == null || !data.isAfter(dataFim));
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("=== PRONTUÁRIO MÉDICO ===\n");
        relatorio.append("Paciente: ").append(paciente.getNome()).append("\n");
        relatorio.append("CPF: ").append(paciente.getCpf()).append("\n");
        relatorio.append("Data de Criação: ").append(dataCriacao).append("\n\n");

        relatorio.append("=== CONSULTAS ===\n");
        if (consultas.isEmpty()) {
            relatorio.append("Nenhuma consulta registrada.\n");
        } else {
            for (Consulta consulta : consultas) {
                relatorio.append("Data: ").append(consulta.getDataHora()).append("\n");
                relatorio.append("Médico: ").append(consulta.getMedico().getNome()).append("\n");
                relatorio.append("Diagnóstico: ").append(consulta.getDiagnostico()).append("\n");
                relatorio.append("Tratamento: ").append(consulta.getTratamento()).append("\n");
                relatorio.append("Status: ").append(consulta.isRealizada() ? "Realizada" : "Agendada").append("\n\n");
            }
        }

        relatorio.append("=== EXAMES ===\n");
        if (exames.isEmpty()) {
            relatorio.append("Nenhum exame registrado.\n");
        } else {
            for (Exame exame : exames) {
                relatorio.append("Tipo: ").append(exame.getTipo().getNome()).append("\n");
                relatorio.append("Data Realização: ").append(exame.getDataRealizacao()).append("\n");
                relatorio.append("Resultado: ").append(exame.getResultado() != null ? exame.getResultado() : "Pendente")
                        .append("\n");
                relatorio.append("Status: ").append(exame.isRealizado() ? "Realizado" : "Agendado").append("\n\n");
            }
        }

        return relatorio.toString();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(consultas);
    }

    public List<Exame> getExames() {
        return new ArrayList<>(exames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Prontuario that = (Prontuario) o;
        return Objects.equals(paciente, that.paciente) &&
                Objects.equals(dataCriacao, that.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paciente, dataCriacao);
    }

    @Override
    public String toString() {
        return "Prontuario{" +
                "dataCriacao=" + dataCriacao +
                ", paciente=" + paciente.getNome() +
                ", consultas=" + consultas.size() +
                ", exames=" + exames.size() +
                '}';
    }
}