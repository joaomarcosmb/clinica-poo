package org.poo.servicos;

import org.poo.entidades.Consulta;
import org.poo.entidades.Exame;
import org.poo.entidades.Paciente;
import org.poo.entidades.Prontuario;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {
    
    public String gerarRelatorioProntuario(Prontuario prontuario) {
        if (prontuario == null) {
            throw new IllegalArgumentException("Prontuário não pode ser nulo");
        }
        
        StringBuilder relatorio = new StringBuilder();
        Paciente paciente = prontuario.getPaciente();
        
        relatorio.append("=== PRONTUÁRIO MÉDICO ===\n");
        relatorio.append("Paciente: ").append(paciente.getNome()).append("\n");
        relatorio.append("CPF: ").append(paciente.getCpf()).append("\n");
        relatorio.append("Data de Criação: ").append(prontuario.getDataCriacao()).append("\n\n");

        relatorio.append("=== CONSULTAS ===\n");
        List<Consulta> consultas = prontuario.getConsultas();
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
        List<Exame> exames = prontuario.getExames();
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
    
    public String gerarRelatorioConsultas(List<Consulta> consultas, LocalDate dataInicio, LocalDate dataFim) {
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append("=== RELATÓRIO DE CONSULTAS ===\n");
        relatorio.append("Período: ").append(dataInicio).append(" a ").append(dataFim).append("\n\n");
        
        if (consultas.isEmpty()) {
            relatorio.append("Nenhuma consulta encontrada no período.\n");
        } else {
            for (Consulta consulta : consultas) {
                relatorio.append("Data: ").append(consulta.getDataHora()).append("\n");
                relatorio.append("Paciente: ").append(consulta.getPaciente().getNome()).append("\n");
                relatorio.append("Médico: ").append(consulta.getMedico().getNome()).append("\n");
                relatorio.append("Status: ").append(consulta.isRealizada() ? "Realizada" : "Agendada").append("\n\n");
            }
        }
        
        return relatorio.toString();
    }
} 