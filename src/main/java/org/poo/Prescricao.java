package org.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prescricao {
    private LocalDate data;
    private Medico medico;
    private Paciente paciente;
    private boolean ativa;
    private List<ItemPrescricao> itens;

    public Prescricao() {
        this.data = LocalDate.now();
        this.ativa = true;
        this.itens = new ArrayList<>();
    }

    public Prescricao(Medico medico, Paciente paciente) {
        this();
        this.medico = medico;
        this.paciente = paciente;
    }

    public void adicionarMedicamento(Medicamento medicamento, String posologia, int duracao) {
        if (medicamento == null) {
            throw new IllegalArgumentException("Medicamento não pode ser nulo");
        }

        if (posologia == null || posologia.trim().isEmpty()) {
            throw new IllegalArgumentException("Posologia não pode ser nula ou vazia");
        }

        if (duracao <= 0) {
            throw new IllegalArgumentException("Duração do tratamento deve ser maior que zero");
        }

        ItemPrescricao item = new ItemPrescricao(medicamento, posologia, duracao, "");
        this.itens.add(item);
    }

    public void removerMedicamento(Medicamento medicamento) {
        if (medicamento == null) {
            return;
        }

        itens.removeIf(item -> item.getMedicamento().equals(medicamento));
    }

    public String imprimir() {
        StringBuilder prescricao = new StringBuilder();

        prescricao.append("=== PRESCRIÇÃO MÉDICA ===\n");
        prescricao.append("Data: ").append(data).append("\n");
        prescricao.append("Médico: ").append(medico != null ? medico.getNome() : "N/A").append("\n");
        prescricao.append("CRM: ").append(medico != null ? medico.getCrm() : "N/A").append("\n");
        prescricao.append("Paciente: ").append(paciente != null ? paciente.getNome() : "N/A").append("\n");
        prescricao.append("CPF: ").append(paciente != null ? paciente.getCpf() : "N/A").append("\n\n");

        prescricao.append("=== MEDICAMENTOS PRESCRITOS ===\n");

        if (itens.isEmpty()) {
            prescricao.append("Nenhum medicamento prescrito.\n");
        } else {
            int contador = 1;
            for (ItemPrescricao item : itens) {
                prescricao.append(contador++).append(". ");
                prescricao.append("Medicamento: ").append(item.getMedicamento().getNome()).append("\n");
                prescricao.append("   Princípio Ativo: ").append(item.getMedicamento().getPrincipioAtivo())
                        .append("\n");
                prescricao.append("   Concentração: ").append(item.getMedicamento().getConcentracao()).append("\n");
                prescricao.append("   Forma Farmacêutica: ").append(item.getMedicamento().getFormaFarmaceutica())
                        .append("\n");
                prescricao.append("   Posologia: ").append(item.getPosologia()).append("\n");
                prescricao.append("   Duração: ").append(item.getDuracaoTratamento()).append(" dias\n");

                if (item.getObservacoes() != null && !item.getObservacoes().trim().isEmpty()) {
                    prescricao.append("   Observações: ").append(item.getObservacoes()).append("\n");
                }
                prescricao.append("\n");
            }
        }

        prescricao.append("Status: ").append(ativa ? "Ativa" : "Inativa").append("\n");
        prescricao.append("\n_______________________________\n");
        prescricao.append("Assinatura do Médico\n");

        return prescricao.toString();
    }

    public void desativarPrescricao() {
        this.ativa = false;
    }

    public void ativarPrescricao() {
        this.ativa = true;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public List<ItemPrescricao> getItens() {
        return new ArrayList<>(itens);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Prescricao that = (Prescricao) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(medico, that.medico) &&
                Objects.equals(paciente, that.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, medico, paciente);
    }

    @Override
    public String toString() {
        return "Prescricao{" +
                "data=" + data +
                ", medico=" + (medico != null ? medico.getNome() : "N/A") +
                ", paciente=" + (paciente != null ? paciente.getNome() : "N/A") +
                ", ativa=" + ativa +
                ", itens=" + itens.size() +
                '}';
    }
}