package org.poo.entidades;

import org.poo.excecoes.ProntuarioInvalidoException;
import org.poo.interfaces.ItemHistorico;

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

    public void adicionarConsulta(Consulta consulta) throws ProntuarioInvalidoException {
        if (consulta == null) {
            throw new ProntuarioInvalidoException("Consulta não pode ser nula");
        }

        if (!consulta.getPaciente().equals(this.paciente)) {
            throw new ProntuarioInvalidoException("Consulta deve ser do mesmo paciente do prontuário");
        }

        // Estabelece referência bidirecional
        try {
            consulta.setProntuario(this);
        } catch (Exception e) {
            throw new ProntuarioInvalidoException("Erro ao associar consulta ao prontuário: " + e.getMessage());
        }

        this.consultas.add(consulta);
    }

    public void adicionarExame(Exame exame) throws ProntuarioInvalidoException {
        if (exame == null) {
            throw new ProntuarioInvalidoException("Exame não pode ser nulo");
        }

        if (!exame.getPaciente().equals(this.paciente)) {
            throw new ProntuarioInvalidoException("Exame deve ser do mesmo paciente do prontuário");
        }

        // Estabelece referência bidirecional
        exame.setProntuario(this);
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