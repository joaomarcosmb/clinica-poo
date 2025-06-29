package org.poo.validators;

import org.poo.entidades.EspecialidadeMedica;
import org.poo.entidades.Medico;
import org.poo.excecoes.MedicoSemEspecialidadeException;

import java.util.List;

public class ValidadorMedico {
    
    public static void validarEspecialidadeParaConsulta(Medico medico, String tipoConsulta) throws MedicoSemEspecialidadeException {
        if (medico == null) {
            throw new IllegalArgumentException("Médico não pode ser nulo");
        }
        
        List<EspecialidadeMedica> especialidades = medico.getEspecialidades();
        
        if (especialidades.isEmpty()) {
            throw new MedicoSemEspecialidadeException("Médico deve ter pelo menos uma especialidade");
        }
        
        // Validação específica por tipo de consulta
        if (tipoConsulta != null && !tipoConsulta.isEmpty()) {
            boolean temEspecialidadeAdequada = especialidades.stream()
                .anyMatch(esp -> esp.getNome().toLowerCase().contains(tipoConsulta.toLowerCase()));
            
            if (!temEspecialidadeAdequada) {
                throw new MedicoSemEspecialidadeException(
                    "Médico não possui especialidade adequada para: " + tipoConsulta);
            }
        }
    }
    
    public static void validarEspecialidadeParaExame(Medico medico, String tipoExame) throws MedicoSemEspecialidadeException {
        if (medico == null) {
            throw new IllegalArgumentException("Médico não pode ser nulo");
        }
        
        if (medico.getEspecialidades().isEmpty()) {
            throw new MedicoSemEspecialidadeException("Médico deve ter especialidades para solicitar exames");
        }
    }
    
    public static boolean podeRealizarProcedimento(Medico medico, String procedimento) {
        if (medico == null || procedimento == null) {
            return false;
        }
        
        return medico.getEspecialidades().stream()
            .anyMatch(esp -> esp.obterProcedimentos().contains(procedimento));
    }
} 