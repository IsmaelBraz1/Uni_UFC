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
import com.mycompany.uniufc.view.DialogoAluno;
import com.mycompany.uniufc.Model.Aluno;
import com.mycompany.uniufc.Model.Curso;
import com.mycompany.uniufc.Model.Matricula;
import com.mycompany.uniufc.Model.Organizador;
import com.mycompany.uniufc.Model.Turma;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mycompany.uniufc.Model.Organizador;
import java.sql.SQLException;

public class PainelGerenciamentoAlunos extends JPanel {

    private JTable tabelaAlunos;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;

    // Dados Mockados para simular o banco
    private List<Curso> listaDeCursos;// = Arrays.asList(Conexao.listarCursos());
    private List<Aluno> listaDeAlunos;

    private List<Turma> listaDeTurmas = Arrays.asList(
            new Turma(1, 2025, 1, 102, 9001, null),
            new Turma(2, 2025, 1, 101, 9002, null)
    );
    private List<Matricula> listaDeMatriculas = Arrays.asList(
            new Matricula(202510, 1, Matricula.Situacao.ATIVA, null, null) // Matrícula do João na turma 1
    );
    private Aluno aluno;

    public PainelGerenciamentoAlunos() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botaoAdicionar = new JButton("Adicionar");
        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        String[] colunas = {"Matrícula", "Nome", "Tipo", "Curso"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaAlunos = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaAlunos), BorderLayout.CENTER);

        try {
            listaDeCursos = Conexao.listarCursos();
        } catch (SQLException ex) {
            System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        // --- AÇÕES ---
        botaoAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoAluno dialogo = new DialogoAluno(owner, null, listaDeCursos, listaDeTurmas, listaDeMatriculas);
            dialogo.setVisible(true);

            if (dialogo.foiSalvo()) {
                System.out.println("Salvar novo aluno: " + dialogo.getAluno().getNomeAlu());
                Aluno newAluno = new Aluno(dialogo.getAluno().getMatricula(), dialogo.getAluno().getNomeAlu(), dialogo.getAluno().getEndereco(), dialogo.getAluno().getTipoAlu(), dialogo.getAluno().getCodCurso());
                try {
                    Conexao.inserirAluno(newAluno);
                    preencherTabela();
                } catch (SQLException ex) {
                    System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (ClassNotFoundException ex) {
                    System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });

        botaoEditar.addActionListener(e -> {
            int linha = tabelaAlunos.getSelectedRow();
            if (linha != -1) {
                int matricula = (int) modelTabela.getValueAt(linha, 0);
                Aluno aluno = listaDeAlunos.stream().filter(a -> a.getMatricula() == matricula).findFirst().orElse(null);

                if (aluno != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                    DialogoAluno dialogo = new DialogoAluno(owner, aluno, listaDeCursos, listaDeTurmas, listaDeMatriculas);
                    dialogo.setVisible(true);

                    if (dialogo.foiSalvo()) {
                        System.out.println("Editar aluno: " + dialogo.getAluno().getNomeAlu());
                        Aluno newAluno = new Aluno(dialogo.getAluno().getMatricula(), dialogo.getAluno().getNomeAlu(), dialogo.getAluno().getEndereco(), dialogo.getAluno().getTipoAlu(), dialogo.getAluno().getCodCurso());

                        try {
                            Conexao.atualizarAluno(newAluno);
                            preencherTabela();
                        } catch (SQLException ex) {
                            System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        } catch (ClassNotFoundException ex) {
                            System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um aluno para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        botaoExcluir.addActionListener(e -> {
            
             // Futuramente, pedirá confirmação e excluirá o registro
            int linhaSelecionada = tabelaAlunos.getSelectedRow();
            if (linhaSelecionada != -1) {
                System.out.println("Botão EXCLUIR Departamento clicado para a linha: " + linhaSelecionada);
                // Exemplo de como obter o valor da célula
                
                Object codAluno = tabelaAlunos.getValueAt(linhaSelecionada, 0);
                try {
                    Conexao.deletarAluno((int) codAluno);
                    preencherTabela();
                } catch (SQLException ex) {
                    System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (ClassNotFoundException ex) {
                    System.getLogger(PainelGerenciamentoAlunos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                JOptionPane.showMessageDialog(this, "Excluir Aluno com código: " + codAluno);
                
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);

        listaDeAlunos = Organizador.listaAlu();

        for (Aluno aluno : listaDeAlunos) {

            String nomeCurso = Organizador.tradutor("Aluno", aluno.getMatricula());
            modelTabela.addRow(new Object[]{
                aluno.getMatricula(),
                aluno.getNomeAlu(),
                aluno.getTipoAlu(), // O toString() do Enum faz a mágica aqui
                nomeCurso
            });
        }
    }
}
