package org.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Paciente extends Pessoa {
    private String convenio;
    private String endereco;

    public Paciente() {
        super();
    }

    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone, String convenio,
            String endereco) {
        super(nome, cpf, dataNascimento, telefone);
        setConvenio(convenio);
        setEndereco(endereco);
    }

    public boolean agendarConsulta(Medico medico, LocalDateTime dataHora) {
        // TODO: Implementar
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

    @Override
    public String toString() {
        return "Paciente: " + getNome() + " (CPF: " + getCpf() + ")";
    }
}
