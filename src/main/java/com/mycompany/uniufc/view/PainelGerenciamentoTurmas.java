/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Disciplina;
import com.mycompany.uniufc.Model.Professor;
import com.mycompany.uniufc.Model.Turma;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PainelGerenciamentoTurmas extends JPanel {
    private JTable tabelaTurmas;
    private DefaultTableModel modelTabela;
    private JButton btnAdicionar, btnEditar, btnExcluir; // Declarando todos os botões

    // Dados Mockados
    private List<Professor> listaProfs = Arrays.asList(
        new Professor(9001, "Dr. Alan Turing", null, null, 101),
        new Professor(9002, "Dra. Ada Lovelace", null, null, 101)
    );
    private List<Disciplina> listaDiscs = Arrays.asList(
        new Disciplina(102, "Banco de Dados", "", 4, null, 1),
        new Disciplina(101, "Algoritmos I", "", 4, null, 1)
    );
    private List<Turma> listaTurmas = Arrays.asList(
        new Turma(1, 2025, 1, 102, 9001, 9002),
        new Turma(2, 2025, 1, 101, 9002, null)
    );

    public PainelGerenciamentoTurmas() {
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DOS BOTÕES (Agora completo) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        // --- TABELA ---
        String[] colunas = {"ID Turma", "Período", "Disciplina", "Professor Principal"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaTurmas = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaTurmas), BorderLayout.CENTER);

        // --- AÇÕES DOS BOTÕES (Agora completas) ---
        btnAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoTurma dialogo = new DialogoTurma(owner, null, listaDiscs, listaProfs);
            dialogo.setVisible(true);
            if (dialogo.foiSalvo()) {
                System.out.println("Salvar nova turma para o período: " + dialogo.getTurma().getAno() + "/" + dialogo.getTurma().getSemestre());
                // TODO: Adicionar à lista e à tabela
            }
        });

        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaTurmas.getSelectedRow();
            if (linhaSelecionada != -1) {
                int idTurma = (int) modelTabela.getValueAt(linhaSelecionada, 0);
                Turma turmaParaEditar = listaTurmas.stream()
                        .filter(t -> t.getIdTurma() == idTurma)
                        .findFirst().orElse(null);

                if (turmaParaEditar != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                    DialogoTurma dialogo = new DialogoTurma(owner, turmaParaEditar, listaDiscs, listaProfs);
                    dialogo.setVisible(true);
                    if (dialogo.foiSalvo()) {
                        System.out.println("Editar turma ID: " + dialogo.getTurma().getIdTurma());
                        // TODO: Atualizar na lista e na tabela
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma turma para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaTurmas.getSelectedRow();
            if (linhaSelecionada != -1) {
                String descTurma = modelTabela.getValueAt(linhaSelecionada, 1) + " - " + modelTabela.getValueAt(linhaSelecionada, 2);
                int confirm = JOptionPane.showConfirmDialog(this, 
                        "Tem certeza que deseja excluir a turma: " + descTurma + "?", 
                        "Confirmar Remoção", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    int idTurma = (int) modelTabela.getValueAt(linhaSelecionada, 0);
                    System.out.println("Excluir turma ID: " + idTurma);
                    // TODO: Remover da lista e da tabela
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma turma para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        for (Turma turma : listaTurmas) {
            String nomeDisc = listaDiscs.stream().filter(d -> d.getCodDisc() == turma.getCodDisc()).findFirst().map(Disciplina::getNomeDisc).orElse("N/D");
            String nomeProf = listaProfs.stream().filter(p -> p.getSiape() == turma.getSiapeProf1()).findFirst().map(Professor::getNomeProf).orElse("N/D");

            modelTabela.addRow(new Object[]{
                turma.getIdTurma(),
                turma.getAno() + "/" + turma.getSemestre(),
                nomeDisc,
                nomeProf
            });
        }
    }
}
