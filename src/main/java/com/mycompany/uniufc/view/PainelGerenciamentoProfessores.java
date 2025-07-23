/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Control.Conexao;
import com.mycompany.uniufc.view.DialogoProfessor;
import com.mycompany.uniufc.Model.Departamento;
import com.mycompany.uniufc.Model.Organizador;
import com.mycompany.uniufc.Model.Professor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import com.mycompany.uniufc.Model.*;
import java.sql.SQLException;

public class PainelGerenciamentoProfessores extends JPanel {

    private JTable tabelaProfessores;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;

    // Dados ficticios 
    private List<Departamento> listaDeDepartamentos;/* = Arrays.asList(
            new Departamento(101, "Ciência da Computação"),
            new Departamento(102, "Engenharia Elétrica")
    );*/
    private List<Professor> listaDeProfessores;

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

        try {
            listaDeDepartamentos = Conexao.listarDepartamentos();
        } catch (SQLException ex) {
            System.getLogger(PainelGerenciamentoProfessores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(PainelGerenciamentoProfessores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
                
        // Ações
        botaoAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoProfessor dialogo = new DialogoProfessor(owner, null, listaDeDepartamentos);
            dialogo.setVisible(true);
            if (dialogo.foiSalvo()) {
                Professor newProfessor = new Professor(dialogo.getProfessor().getSiape(), dialogo.getProfessor().getNomeProf(), dialogo.getProfessor().getDataNasProf(), dialogo.getProfessor().getDataIngresso(), dialogo.getProfessor().getCodDepart());
                try {
                    Conexao.inserirProfessor(newProfessor);
                    preencherTabela();
                } catch (SQLException ex) {
                    System.getLogger(PainelGerenciamentoProfessores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (ClassNotFoundException ex) {
                    System.getLogger(PainelGerenciamentoProfessores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
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
                        Professor newProfessor = new Professor(dialogo.getProfessor().getSiape(), dialogo.getProfessor().getNomeProf(), dialogo.getProfessor().getDataNasProf(), dialogo.getProfessor().getDataIngresso(), dialogo.getProfessor().getCodDepart());
                        try {
                            Conexao.atualizarProfessor(newProfessor);
                            preencherTabela();
                        } catch (SQLException ex) {
                            System.getLogger(PainelGerenciamentoProfessores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        } catch (ClassNotFoundException ex) {
                            System.getLogger(PainelGerenciamentoProfessores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                        System.out.println("Editar professor: " + dialogo.getProfessor().getNomeProf());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um professor para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // No arquivo PainelGerenciamentoProfessores.java
        botaoExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaProfessores.getSelectedRow();
            if (linhaSelecionada != -1) {

                int siape = (int) modelTabela.getValueAt(linhaSelecionada, 0);
                String nomeProf = (String) modelTabela.getValueAt(linhaSelecionada, 1);

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Tem certeza que deseja excluir o professor: '" + nomeProf + "' (SIAPE: " + siape + ")?", // Mensagem
                        "Confirmar Remoção",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {

                        Professor profParaExcluir = listaDeProfessores.stream()
                                .filter(p -> p.getSiape() == siape)
                                .findFirst()
                                .orElse(null);

                        if (profParaExcluir != null) {

                            Conexao.deletarProfessor(profParaExcluir.getSiape(), profParaExcluir.getCodDepart());
                            preencherTabela();
                            JOptionPane.showMessageDialog(this, "Professor excluído com sucesso!");

                            preencherTabela();
                        }

                    } catch (SQLException | ClassNotFoundException ex) {

                        JOptionPane.showMessageDialog(this, "Erro ao excluir professor do banco de dados.", "Erro de Exclusão", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            } else {

                JOptionPane.showMessageDialog(this, "Por favor, selecione um Professor para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        listaDeProfessores = Organizador.listaProf();

        for (Professor prof : listaDeProfessores) {
            String nomeDepto = Organizador.tradutor("Departamento", prof.getCodDepart());

            modelTabela.addRow(new Object[]{
                prof.getSiape(),
                prof.getNomeProf(),
                prof.getDataIngresso().format(formatter), // Formatando a data
                nomeDepto
            });
        }
    }
}
