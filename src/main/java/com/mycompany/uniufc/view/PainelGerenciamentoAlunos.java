/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.view.DialogoAluno;
import com.mycompany.uniufc.Model.Aluno;
import com.mycompany.uniufc.Model.Curso;
import com.mycompany.uniufc.Model.Matricula;
import com.mycompany.uniufc.Model.Turma;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PainelGerenciamentoAlunos extends JPanel {

    private JTable tabelaAlunos;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;

    // Dados Mockados para simular o banco
    private List<Curso> listaDeCursos = Arrays.asList(
        new Curso(1, "Sistemas de Informação", 180, 101),
        new Curso(2, "Engenharia de Software", 200, 101)
    );
    private List<Aluno> listaDeAlunos = Arrays.asList(
        new Aluno(202510, "João da Silva", "Rua A, 123", Aluno.TipoAluno.GRADUACAO, 1),
        new Aluno(202511, "Maria Oliveira", "Rua B, 456", Aluno.TipoAluno.GRADUACAO, 2),
        new Aluno(90901, "Carlos Pereira", "Rua C, 789", Aluno.TipoAluno.POS_GRADUACAO, 1)
    );
    
    private List<Turma> listaDeTurmas = Arrays.asList(
        new Turma(1, 2025, 1, 102, 9001, null),
        new Turma(2, 2025, 1, 101, 9002, null)
    );
    private List<Matricula> listaDeMatriculas = Arrays.asList(
        new Matricula(202510, 1, Matricula.Situacao.ATIVA, null, null) // Matrícula do João na turma 1
    );

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

        // --- AÇÕES ---
        botaoAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
           DialogoAluno dialogo = new DialogoAluno(owner, null, listaDeCursos, listaDeTurmas, listaDeMatriculas);
            dialogo.setVisible(true);

            if (dialogo.foiSalvo()) {
                System.out.println("Salvar novo aluno: " + dialogo.getAluno().getNomeAlu());
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
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um aluno para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        botaoExcluir.addActionListener(e -> {
             if (tabelaAlunos.getSelectedRow() != -1) {
                 JOptionPane.showMessageDialog(this, "Ação de excluir a ser implementada.");
             } else {
                 JOptionPane.showMessageDialog(this, "Selecione um curso para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
             }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        for (Aluno aluno : listaDeAlunos) {
            String nomeCurso = listaDeCursos.stream()
                .filter(c -> c.getCodCurso() == aluno.getCodCurso())
                .map(Curso::getNomeCurso)
                .findFirst().orElse("N/D");
            
            modelTabela.addRow(new Object[]{
                aluno.getMatricula(),
                aluno.getNomeAlu(),
                aluno.getTipoAlu(), // O toString() do Enum faz a mágica aqui
                nomeCurso
            });
        }
    }
}
