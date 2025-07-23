/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Organizador;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelConsultaProfessor extends JPanel {

    private JTextField campoProfessor;
    private JButton botaoConsultar;
    private JTabbedPane abasResultados;

    // Modelos para os resultados
    private DefaultListModel<String> modelOrientandos;
    private DefaultListModel<String> modelDisciplinas;
    private JTextField campoTotalCreditos;

    public PainelConsultaProfessor() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL DE ENTRADA (NORTE) ---
        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEntrada.add(new JLabel("Nome ou SIAPE do Professor:"));
        campoProfessor = new JTextField(30);
        painelEntrada.add(campoProfessor);
        botaoConsultar = new JButton("Consultar");
        painelEntrada.add(botaoConsultar);
        add(painelEntrada, BorderLayout.NORTH);

        // --- PAINEL DE RESULTADOS COM ABAS (CENTRO) ---
        abasResultados = new JTabbedPane();

        // Aba 5.1: Alunos Orientandos
        modelOrientandos = new DefaultListModel<>();
        abasResultados.addTab("Alunos Orientandos", new JScrollPane(new JList<>(modelOrientandos)));

        // Aba 5.2: Disciplinas Lecionadas
        modelDisciplinas = new DefaultListModel<>();
        abasResultados.addTab("Disciplinas Lecionadas", new JScrollPane(new JList<>(modelDisciplinas)));

        // Aba 5.3: Total de Créditos
        JPanel painelCreditos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCreditos.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        painelCreditos.add(new JLabel("Total de créditos das disciplinas lecionadas:"));
        campoTotalCreditos = new JTextField(10);
        campoTotalCreditos.setEditable(false);
        campoTotalCreditos.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelCreditos.add(campoTotalCreditos);
        abasResultados.addTab("Créditos", painelCreditos);

        add(abasResultados, BorderLayout.CENTER);

        // --- AÇÃO DO BOTÃO ---
        botaoConsultar.addActionListener(e -> {
            String professor = campoProfessor.getText();
            if (!professor.trim().isEmpty()) {
                popularComDadosMockados(professor);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira o nome ou SIAPE do professor.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void popularComDadosMockados(String professor) {
        // Limpa os resultados de consultas anteriores
        modelOrientandos.clear();
        modelDisciplinas.clear();
        campoTotalCreditos.setText("");

        System.out.println("Consultando dados para o professor: " + professor);

      

        // 5.1: Alunos Orientandos
        List<String> listOrient = Organizador.listaProfOrient(professor);
             listOrient.forEach(modelOrientandos::addElement);

        // 5.2: Disciplinas Lecionadas
        List<String> listDisc = Organizador.listaDiscProfOri(professor);
             listDisc.forEach(modelDisciplinas::addElement);

        // 5.3: Total de Créditos
        int totalCreditos = Organizador.numCreditos(professor);
        campoTotalCreditos.setText(String.valueOf(totalCreditos));
    }
}
