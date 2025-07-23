/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Aluno;
import com.mycompany.uniufc.Model.Matricula;
import com.mycompany.uniufc.Model.Turma;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DialogoGerenciarMatriculas extends JDialog {

    private DefaultListModel<Turma> modelDisponiveis;
    private DefaultListModel<Turma> modelMatriculadas;

    public DialogoGerenciarMatriculas(Dialog owner, Aluno aluno, List<Turma> todasAsTurmas, List<Matricula> matriculasDoAluno) {
        super(owner, true);
        setTitle("Gerenciar Matrículas de: " + aluno.getNomeAlu());

        // --- Modelos das Listas ---
        modelDisponiveis = new DefaultListModel<>();
        modelMatriculadas = new DefaultListModel<>();

  
        List<Integer> idsTurmasMatriculadas = matriculasDoAluno.stream()
                .map(Matricula::getTurmaId)
                .collect(Collectors.toList());

      
        for (Turma t : todasAsTurmas) {
            if (idsTurmasMatriculadas.contains(t.getIdTurma())) {
                modelMatriculadas.addElement(t);
            } else {
                modelDisponiveis.addElement(t);
            }
        }

        // --- Componentes da UI (Lista Dupla) ---
        JList<Turma> listaDisponiveis = new JList<>(modelDisponiveis);
        JList<Turma> listaMatriculadas = new JList<>(modelMatriculadas);
        JButton btnAdicionar = new JButton(">>");
        JButton btnRemover = new JButton("<<");
      
        
        JPanel painelBotoesMeio = new JPanel(new GridLayout(2, 1, 5, 5));
        painelBotoesMeio.add(btnAdicionar);
        painelBotoesMeio.add(btnRemover);

        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setBorder(BorderFactory.createTitledBorder("Turmas Disponíveis"));
        painelEsquerdo.add(new JScrollPane(listaDisponiveis));
        
        JPanel painelDireito = new JPanel(new BorderLayout());
        painelDireito.setBorder(BorderFactory.createTitledBorder("Turmas Matriculadas"));
        painelDireito.add(new JScrollPane(listaMatriculadas));

        JButton btnSalvar = new JButton("Salvar Alterações");
        JPanel painelSalvar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelSalvar.add(btnSalvar);
        


        // --- Ações ---
        btnAdicionar.addActionListener(e -> {
            listaDisponiveis.getSelectedValuesList().forEach(turma -> {
                modelMatriculadas.addElement(turma);
                modelDisponiveis.removeElement(turma);
            });
        });

        btnRemover.addActionListener(e -> {
            listaMatriculadas.getSelectedValuesList().forEach(turma -> {
                modelDisponiveis.addElement(turma);
                modelMatriculadas.removeElement(turma);
            });
        });

        btnSalvar.addActionListener(e -> {
            System.out.println("Salvando matrículas para o aluno " + aluno.getMatricula());
            
            dispose();
        });

        // Montagem do layout (similar ao DialogoPreRequisitos)
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.45; gbc.weighty = 1.0;
        
        gbc.gridx = 0; gbc.gridy = 0;
        add(painelEsquerdo, gbc);
        
        gbc.gridx = 2;
        add(painelDireito, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.1;
        add(painelBotoesMeio, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        add(painelSalvar, gbc);
        
        setSize(700, 400);
        setLocationRelativeTo(owner);
    }
}