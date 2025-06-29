package org.poo.entidades;

import org.poo.excecoes.MedicoSemEspecialidadeException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medico extends Pessoa {
    private String crm;
    private List<EspecialidadeMedica> especialidades;

    public Medico() {
        super();
        this.especialidades = new ArrayList<>();
    }

    public Medico(String nome, String cpf, LocalDate dataNascimento, String telefone, String crm) {
        super(nome, cpf, dataNascimento, telefone);
        setCrm(crm);
        this.especialidades = new ArrayList<>();
    }

    public void realizarConsulta(Consulta consulta) throws MedicoSemEspecialidadeException {
        if (consulta == null) {
            throw new IllegalArgumentException("Consulta não pode ser nula");
        }

        // Um médico deve ter pelo menos uma especialidade para realizar consultas
        if (especialidades.isEmpty()) {
            throw new MedicoSemEspecialidadeException(
                    "Médico deve ter pelo menos uma especialidade para realizar consultas");
        }

        System.out.println("Dr(a). " + getNome() + " está realizando consulta para o paciente: " +
                consulta.getPaciente().getNome());
    }

    public Exame solicitarExame(Paciente paciente, String tipoExame) throws MedicoSemEspecialidadeException {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não pode ser nulo");
        }

        if (tipoExame == null || tipoExame.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de exame não pode ser nulo ou vazio");
        }

        if (especialidades.isEmpty()) {
            throw new MedicoSemEspecialidadeException("Médico deve ter especialidades para solicitar exames");
        }

        // Cria e retorna um novo exame
        return new Exame(paciente, tipoExame, this);
    }

    public List<EspecialidadeMedica> getEspecialidades() {
        return new ArrayList<>(especialidades); // Retorna cópia para proteger encapsulamento
    }

    public void adicionarEspecialidade(EspecialidadeMedica especialidade) {
        if (especialidade == null) {
            throw new IllegalArgumentException("Especialidade não pode ser nula");
        }

        if (!especialidades.contains(especialidade)) {
            especialidades.add(especialidade);
        }
    }

    public void removerEspecialidade(EspecialidadeMedica especialidade) {
        especialidades.remove(especialidade);
    }

    public boolean possuiEspecialidade(EspecialidadeMedica especialidade) {
        return especialidades.contains(especialidade);
    }

    @Override
    public int obterIdade() {
        if (getDataNascimento() == null) {
            throw new IllegalStateException("Data de nascimento não foi definida");
        }

        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }

    @Override
    public void atualizarContato(String novoTelefone) {
        setTelefone(novoTelefone);
        System.out.println("Contato do Dr(a). " + getNome() + " atualizado para: " + novoTelefone);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        if (crm == null || crm.trim().isEmpty()) {
            throw new IllegalArgumentException("CRM não pode ser nulo ou vazio");
        }

        // Remove espaços e converte para maiúsculo
        String crmLimpo = crm.trim().toUpperCase();

        // Validação básica de formato CRM
        if (!crmLimpo.matches("CRM/[A-Z]{2}\\s*\\d{4,6}")) {
            throw new IllegalArgumentException("CRM deve estar no formato: CRM/UF NÚMERO (ex: CRM/SP 123456)");
        }

        this.crm = crmLimpo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Medico medico = (Medico) o;
        return Objects.equals(crm, medico.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crm);
    }

    @Override
    public String toString() {
        return "Dr(a). " + getNome() + " (CRM: " + crm + ")";
    }
}
