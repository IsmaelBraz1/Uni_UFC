/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelConsultaDisciplina extends JPanel {

    private JTextField campoDisciplina;
    private JButton botaoConsultar;
    private JTabbedPane abasResultados;

    // Modelos para cada uma das 3 listas de resultados
    private DefaultListModel<String> modelAlunosMatriculados;
    private DefaultListModel<String> modelPreRequisitos;
    private DefaultListModel<String> modelRequisitoPara;

    public PainelConsultaDisciplina() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL DE ENTRADA (NORTE) ---
        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEntrada.add(new JLabel("Nome ou Código da Disciplina:"));
        campoDisciplina = new JTextField(30);
        painelEntrada.add(campoDisciplina);
        botaoConsultar = new JButton("Consultar");
        painelEntrada.add(botaoConsultar);
        add(painelEntrada, BorderLayout.NORTH);

        // --- PAINEL DE RESULTADOS COM ABAS (CENTRO) ---
        abasResultados = new JTabbedPane();

        // Aba 4.1: Alunos Matriculados
        modelAlunosMatriculados = new DefaultListModel<>();
        abasResultados.addTab("Alunos Matriculados", new JScrollPane(new JList<>(modelAlunosMatriculados)));

        // Aba 4.2: Pré-requisitos da Disciplina
        modelPreRequisitos = new DefaultListModel<>();
        abasResultados.addTab("Pré-requisitos", new JScrollPane(new JList<>(modelPreRequisitos)));

        // Aba 4.3: É Pré-requisito Para
        modelRequisitoPara = new DefaultListModel<>();
        abasResultados.addTab("É Pré-requisito Para", new JScrollPane(new JList<>(modelRequisitoPara)));

        add(abasResultados, BorderLayout.CENTER);

        // --- AÇÃO DO BOTÃO ---
        botaoConsultar.addActionListener(e -> {
            String disciplina = campoDisciplina.getText();
            if (!disciplina.trim().isEmpty()) {
                popularComDadosMockados(disciplina);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira o nome ou código da disciplina.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void popularComDadosMockados(String disciplina) {
        // Limpa os resultados de consultas anteriores
        modelAlunosMatriculados.clear();
        modelPreRequisitos.clear();
        modelRequisitoPara.clear();

        System.out.println("Consultando dados para a disciplina: " + disciplina);

        // --- DADOS MOCKADOS DE EXEMPLO (simulando a consulta por "Banco de Dados") ---

        // 4.1: Alunos Matriculados
        List.of("João da Silva", "Maria Oliveira", "Carlos Pereira")
             .forEach(modelAlunosMatriculados::addElement);

        // 4.2: Pré-requisitos da Disciplina
        List.of("Algoritmos I", "Estrutura de Dados")
             .forEach(modelPreRequisitos::addElement);

        // 4.3: Disciplinas para as quais a mesma é pré-requisito
        List.of("Sistemas de Gerenciamento de Banco de Dados", "Tópicos Especiais em Banco de Dados")
             .forEach(modelRequisitoPara::addElement);
    }
}
