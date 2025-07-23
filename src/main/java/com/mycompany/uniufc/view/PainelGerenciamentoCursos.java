/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.view.DialogoCurso;
import com.mycompany.uniufc.Model.Curso;
import com.mycompany.uniufc.Model.Departamento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mycompany.uniufc.Model.Organizador;

public class PainelGerenciamentoCursos extends JPanel {

    private JTable tabelaCursos;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;

    // Dados Mockados para simular o banco
    private List<Departamento> listaDeDepartamentos = Arrays.asList(
        new Departamento(101, "Ciência da Computação"),
        new Departamento(102, "Engenharia Elétrica")
    );
    private List<Curso> listaDeCursos;

    public PainelGerenciamentoCursos() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botaoAdicionar = new JButton("Adicionar");
        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        String[] colunas = {"Cód. Curso", "Nome do Curso", "Créditos", "Departamento"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaCursos = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaCursos), BorderLayout.CENTER);

        // --- AÇÕES ---
        botaoAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            // Passamos a lista de departamentos para o diálogo poder popular o JComboBox
            DialogoCurso dialogo = new DialogoCurso(owner, null, listaDeDepartamentos);
            dialogo.setVisible(true);

            if (dialogo.foiSalvo()) {
                Curso novoCurso = dialogo.getCurso();
                System.out.println("Salvar novo curso: " + novoCurso.getNomeCurso());
                // TODO: Adicionar na lista e atualizar a tabela
            }
        });

        botaoEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaCursos.getSelectedRow();
            if (linhaSelecionada != -1) {
                int codCurso = (int) modelTabela.getValueAt(linhaSelecionada, 0);
                // Encontra o objeto Curso correspondente na nossa lista de dados
                Optional<Curso> cursoParaEditarOpt = listaDeCursos.stream()
                        .filter(c -> c.getCodCurso() == codCurso).findFirst();

                if (cursoParaEditarOpt.isPresent()) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                    DialogoCurso dialogo = new DialogoCurso(owner, cursoParaEditarOpt.get(), listaDeDepartamentos);
                    dialogo.setVisible(true);

                    if (dialogo.foiSalvo()) {
                        Curso cursoEditado = dialogo.getCurso();
                        System.out.println("Editar curso: " + cursoEditado.getNomeCurso());
                        // TODO: Atualizar na lista e na tabela
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um curso para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // Ação de excluir (ainda apenas um placeholder)
        botaoExcluir.addActionListener(e -> {
             if (tabelaCursos.getSelectedRow() != -1) {
                 JOptionPane.showMessageDialog(this, "Ação de excluir a ser implementada.");
             } else {
                 JOptionPane.showMessageDialog(this, "Selecione um curso para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
             }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        
        listaDeCursos = Organizador.listaCurso();
        
        for (Curso curso : listaDeCursos) {
            // Simula um JOIN para buscar o nome do departamento
            String nomeDepto = Organizador.tradutor("Departamento", curso.getCodDepart());
            
            modelTabela.addRow(new Object[]{
                curso.getCodCurso(),
                curso.getNomeCurso(),
                curso.getCreditosMinimos(),
                nomeDepto
            });
        }
    }
}
