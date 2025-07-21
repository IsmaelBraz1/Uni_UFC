/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Curso;
import com.mycompany.uniufc.Model.Disciplina;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PainelGerenciamentoDisciplinas extends JPanel {
    private JTable tabelaDisciplinas;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir; // Botões declarados

    // Dados Mockados
    private List<Curso> listaDeCursos = Arrays.asList(
        new Curso(1, "Sistemas de Informação", 180, 101),
        new Curso(2, "Engenharia de Software", 200, 101)
    );
    private List<Disciplina> listaDeDisciplinas = Arrays.asList(
        new Disciplina(101, "Algoritmos I", "Conceitos básicos de lógica e programação.", 4, Disciplina.TipoDisciplina.OBRIGATORIA, 1),
        new Disciplina(102, "Banco de Dados", "Modelagem relacional, SQL e normalização.", 4, Disciplina.TipoDisciplina.OBRIGATORIA, 1),
        new Disciplina(201, "Engenharia de Requisitos", "Técnicas de levantamento e análise de requisitos.", 4, Disciplina.TipoDisciplina.OBRIGATORIA, 2)
    );

    public PainelGerenciamentoDisciplinas() {
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DOS BOTÕES (Agora completo) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botaoAdicionar = new JButton("Adicionar");
        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");
        
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        // --- TABELA ---
        String[] colunas = {"Cód. Disc.", "Nome", "Créditos", "Tipo", "Curso"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaDisciplinas = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaDisciplinas), BorderLayout.CENTER);

        // --- AÇÕES DOS BOTÕES (Agora completas) ---
        botaoAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
           DialogoDisciplina dialogo = new DialogoDisciplina(owner, null, listaDeCursos, listaDeDisciplinas);
            dialogo.setVisible(true);

            if (dialogo.foiSalvo()) {
                Disciplina novaDisciplina = dialogo.getDisciplina();
                System.out.println("Salvar nova disciplina: " + novaDisciplina.getNomeDisc());
                // TODO: Adicionar na listaDeDisciplinas e chamar preencherTabela()
            }
        });

        botaoEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaDisciplinas.getSelectedRow();
            if (linhaSelecionada != -1) {
                int codDisc = (int) modelTabela.getValueAt(linhaSelecionada, 0);
                
                // Encontra o objeto Disciplina para editar na nossa lista
                Disciplina disciplinaParaEditar = listaDeDisciplinas.stream()
                        .filter(d -> d.getCodDisc() == codDisc)
                        .findFirst()
                        .orElse(null);

                if (disciplinaParaEditar != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                     DialogoDisciplina dialogo = new DialogoDisciplina(owner, disciplinaParaEditar, listaDeCursos, listaDeDisciplinas);
                    dialogo.setVisible(true);

                    if (dialogo.foiSalvo()) {
                        Disciplina disciplinaEditada = dialogo.getDisciplina();
                        System.out.println("Editar disciplina: " + disciplinaEditada.getNomeDisc());
                        // TODO: Atualizar na listaDeDisciplinas e chamar preencherTabela()
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma disciplina para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        botaoExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaDisciplinas.getSelectedRow();
            if (linhaSelecionada != -1) {
                String nomeDisciplina = (String) modelTabela.getValueAt(linhaSelecionada, 1);
                int confirm = JOptionPane.showConfirmDialog(this, 
                        "Tem certeza que deseja excluir a disciplina: '" + nomeDisciplina + "'?", 
                        "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    int codDisc = (int) modelTabela.getValueAt(linhaSelecionada, 0);
                    System.out.println("Excluir disciplina com código: " + codDisc);
                    // TODO: Remover da listaDeDisciplinas e chamar preencherTabela()
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione uma disciplina para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        for (Disciplina disc : listaDeDisciplinas) {
            String nomeCurso = listaDeCursos.stream()
                .filter(c -> c.getCodCurso() == disc.getCodCurso())
                .map(Curso::getNomeCurso)
                .findFirst().orElse("N/D");
            
            modelTabela.addRow(new Object[]{
                disc.getCodDisc(),
                disc.getNomeDisc(),
                disc.getNumCreditos(),
                disc.getTipo(),
                nomeCurso
            });
        }
    }
}