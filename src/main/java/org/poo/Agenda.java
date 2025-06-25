package org.poo;

import java.time.LocalDate;
import java.util.*;

public class Agenda {
    private Medico medico;
    private Map<LocalDate, Boolean> horarios;

    public Agenda() {
        this.horarios = new HashMap<>();
    }

    public Agenda(Medico medico) {
        setMedico(medico);
        this.horarios = new HashMap<>();
    }

    public boolean verificarDisponibilidade(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        
        if (data.isBefore(LocalDate.now())) {
            return false; // Datas passadas não estão disponíveis
        }
        
        // Se não existe entrada para a data, considera como disponível
        return !horarios.getOrDefault(data, false);
    }

    public boolean reservarHorario(LocalDate data, Paciente paciente) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não pode ser nulo");
        }
        
        if (!verificarDisponibilidade(data)) {
            return false; // Horário não disponível
        }
        
        // Marca o horário como ocupado
        horarios.put(data, true);
        return true;
    }

    public void liberarHorario(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        
        // Remove o horário da agenda ou marca como disponível
        horarios.put(data, false);
    }

    public List<LocalDate> obterHorariosDisponiveis(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("Data inicial deve ser anterior à data final");
        }
        
        List<LocalDate> horariosDisponiveis = new ArrayList<>();
        LocalDate dataAtual = dataInicio;
        
        while (!dataAtual.isAfter(dataFim)) {
            if (verificarDisponibilidade(dataAtual)) {
                horariosDisponiveis.add(dataAtual);
            }
            dataAtual = dataAtual.plusDays(1);
        }
        
        return horariosDisponiveis;
    }

    public List<LocalDate> obterHorariosOcupados() {
        return horarios.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("Médico não pode ser nulo");
        }
        
        this.medico = medico;
    }

    public Map<LocalDate, Boolean> getHorarios() {
        return new HashMap<>(horarios); // Retorna cópia para proteger encapsulamento
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return Objects.equals(medico, agenda.medico);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(medico);
    }
} 