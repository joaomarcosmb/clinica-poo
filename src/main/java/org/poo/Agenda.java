package org.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Agenda {
    private Medico medico;
    private Map<LocalDateTime, Boolean> horarios;

    public Agenda() {
        this.horarios = new HashMap<>();
    }

    public Agenda(Medico medico) {
        this();
        this.medico = medico;
    }

    public boolean verificarDisponibilidade(LocalDateTime data) {
        if (data == null) {
            return false;
        }

        if (data.isBefore(LocalDateTime.now())) {
            return false;
        }

        // Se não há informação sobre o horário, considera disponível
        return !horarios.containsKey(data) || horarios.get(data);
    }

    public boolean reservarHorario(LocalDateTime data, Paciente paciente) {
        if (data == null || paciente == null) {
            return false;
        }

        if (!verificarDisponibilidade(data)) {
            return false;
        }

        // Marca o horário como ocupado
        horarios.put(data, false);
        return true;
    }

    public void liberarHorario(LocalDateTime data) {
        if (data != null) {
            horarios.put(data, true);
        }
    }

    public List<LocalDateTime> obterHorariosDisponiveis(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<LocalDateTime> horariosDisponiveis = new ArrayList<>();

        if (dataInicio == null || dataFim == null) {
            return horariosDisponiveis;
        }

        LocalDateTime dataAtual = dataInicio;
        while (!dataAtual.isAfter(dataFim)) {
            if (verificarDisponibilidade(dataAtual)) {
                horariosDisponiveis.add(dataAtual);
            }
            dataAtual = dataAtual.plusDays(1);
        }

        return horariosDisponiveis;
    }

    public List<String> obterProcedimentos() {
        List<String> procedimentos = new ArrayList<>();

        if (medico != null && medico.getEspecialidades() != null) {
            for (EspecialidadeMedica especialidade : medico.getEspecialidades()) {
                procedimentos.addAll(especialidade.obterProcedimentos());
            }
        }

        if (procedimentos.isEmpty()) {
            procedimentos.add("Consulta Médica Geral");
        }

        return procedimentos;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Map<LocalDateTime, Boolean> getHorarios() {
        return new HashMap<>(horarios);
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "medico=" + (medico != null ? medico.getNome() : "N/A") +
                ", horariosOcupados=" + horarios.entrySet().stream()
                        .filter(entry -> !entry.getValue())
                        .map(entry -> entry.getKey().toString())
                        .collect(Collectors.joining(", "))
                +
                '}';
    }
}