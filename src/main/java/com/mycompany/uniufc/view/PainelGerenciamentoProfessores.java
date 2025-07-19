/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.view.DialogoProfessor;
import com.mycompany.uniufc.Model.Departamento;
import com.mycompany.uniufc.Model.Professor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class PainelGerenciamentoProfessores extends JPanel {

    private JTable tabelaProfessores;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;

    // Dados ficticios 
    private List<Departamento> listaDeDepartamentos = Arrays.asList(
        new Departamento(101, "Ciência da Computação"),
        new Departamento(102, "Engenharia Elétrica")
    );
    private List<Professor> listaDeProfessores = Arrays.asList(
        new Professor(9001, "Dr. Alan Turing", LocalDate.of(1912, 6, 23), LocalDate.of(1945, 10, 1), 101),
        new Professor(9002, "Dra. Ada Lovelace", LocalDate.of(1815, 12, 10), LocalDate.of(1842, 7, 27), 101),
        new Professor(9003, "Dr. Nikola Tesla", LocalDate.of(1856, 7, 10), LocalDate.of(1884, 6, 6), 102)
    );

    public PainelGerenciamentoProfessores() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botaoAdicionar = new JButton("Adicionar");
        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        String[] colunas = {"SIAPE", "Nome", "Data de Ingresso", "Departamento"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaProfessores = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaProfessores), BorderLayout.CENTER);

        // Ações
        botaoAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoProfessor dialogo = new DialogoProfessor(owner, null, listaDeDepartamentos);
            dialogo.setVisible(true);
            if (dialogo.foiSalvo()) {
                System.out.println("Salvar novo professor: " + dialogo.getProfessor().getNomeProf());
            }
        });

        botaoEditar.addActionListener(e -> {
            int linha = tabelaProfessores.getSelectedRow();
            if (linha != -1) {
                int siape = (int) modelTabela.getValueAt(linha, 0);
                Professor prof = listaDeProfessores.stream().filter(p -> p.getSiape() == siape).findFirst().orElse(null);
                
                if (prof != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                    DialogoProfessor dialogo = new DialogoProfessor(owner, prof, listaDeDepartamentos);
                    dialogo.setVisible(true);
                    if (dialogo.foiSalvo()) {
                        System.out.println("Editar professor: " + dialogo.getProfessor().getNomeProf());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um professor para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Professor prof : listaDeProfessores) {
            String nomeDepto = listaDeDepartamentos.stream()
                .filter(d -> d.getCodDepart() == prof.getCodDepart())
                .map(Departamento::getNomeDepart)
                .findFirst().orElse("N/D");
            
            modelTabela.addRow(new Object[]{
                prof.getSiape(),
                prof.getNomeProf(),
                prof.getDataIngresso().format(formatter), // Formatando a data
                nomeDepto
            });
        }
    }
}