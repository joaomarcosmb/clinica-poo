package org.poo.entidades;

import org.poo.excecoes.AgendaConflitanteException;
import org.poo.excecoes.ConsultaInvalidaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Paciente extends Pessoa {
    private String convenio;
    private String endereco;
    private Prontuario prontuario;

    public Paciente() {
        super();
    }

    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone, String convenio,
            String endereco) {
        super(nome, cpf, dataNascimento, telefone);
        setConvenio(convenio);
        setEndereco(endereco);
    }

    public boolean agendarConsulta(Medico medico, LocalDateTime dataHora)
            throws ConsultaInvalidaException, AgendaConflitanteException {
        if (medico == null) {
            throw new ConsultaInvalidaException("Médico não pode ser nulo");
        }

        if (dataHora == null) {
            throw new ConsultaInvalidaException("Data e hora não podem ser nulas");
        }

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new ConsultaInvalidaException("Não é possível agendar consulta no passado");
        }

        // Cria uma nova consulta
        Consulta consulta = new Consulta(dataHora, null, null, false, medico, this);

        // Se o paciente tem prontuário, adiciona a consulta
        if (this.prontuario != null) {
            try {
                this.prontuario.adicionarConsulta(consulta);
            } catch (Exception e) {
                throw new ConsultaInvalidaException("Erro ao adicionar consulta ao prontuário: " + e.getMessage());
            }
        }

        System.out.println("Consulta agendada para " + this.getNome() + " com Dr(a). "
                + medico.getNome() + " em " + dataHora);

        return true;
    }

    public boolean cancelarAgendamento(Consulta consulta) {
        // TODO: Implementar
        return true;
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
        System.out.println("Contato do paciente " + getNome() + " atualizado para: " + novoTelefone);
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
        
        // Mantém a consistência bidirecional
        if (prontuario != null && !this.equals(prontuario.getPaciente())) {
            prontuario.setPaciente(this);
        }
    }

    public Prontuario criarProntuario() {
        if (this.prontuario == null) {
            this.prontuario = new Prontuario(this);
        }
        return this.prontuario;
    }

    public void removerProntuario() {
        if (this.prontuario != null) {
            this.prontuario = null;
        }
    }

    @Override
    public String toString() {
        return "Paciente: " + getNome() + " (CPF: " + getCpf() + ")";
    }
}
