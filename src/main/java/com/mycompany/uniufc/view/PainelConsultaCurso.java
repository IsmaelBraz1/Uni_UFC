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

import com.mycompany.uniufc.Model.*;

public class PainelConsultaCurso extends JPanel {

    private JTextField campoNomeCurso;
    private JButton botaoConsultar;
    private JTabbedPane abasResultados;

    // Modelos para cada uma das 5 listas de resultados
    private DefaultListModel<String> modelObrigatorias;
    private DefaultListModel<String> modelOptativas;
    private DefaultListModel<String> modelAlunosCurso;
    private DefaultListModel<String> modelAlunosConcluintes;
    private DefaultListModel<String> modelAlunosSemOptativas;
    
    private String nomeCurso;

    public PainelConsultaCurso() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL DE ENTRADA (NORTE) ---
        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEntrada.add(new JLabel("Nome do Curso:"));
        campoNomeCurso = new JTextField(30);
        painelEntrada.add(campoNomeCurso);
        botaoConsultar = new JButton("Consultar");
        painelEntrada.add(botaoConsultar);
        add(painelEntrada, BorderLayout.NORTH);

        // --- PAINEL DE RESULTADOS COM ABAS (CENTRO) ---
        abasResultados = new JTabbedPane();

        // Aba 3.1: Disciplinas Obrigatórias
        modelObrigatorias = new DefaultListModel<>();
        abasResultados.addTab("Obrigatórias", new JScrollPane(new JList<>(modelObrigatorias)));

        // Aba 3.2: Disciplinas Optativas
        modelOptativas = new DefaultListModel<>();
        abasResultados.addTab("Optativas", new JScrollPane(new JList<>(modelOptativas)));

        // Aba 3.3: Alunos do Curso
        modelAlunosCurso = new DefaultListModel<>();
        abasResultados.addTab("Todos os Alunos", new JScrollPane(new JList<>(modelAlunosCurso)));

        // Aba 3.4: Alunos Concluintes (das obrigatórias)
        modelAlunosConcluintes = new DefaultListModel<>();
        abasResultados.addTab("Concluintes (Obrigatórias)", new JScrollPane(new JList<>(modelAlunosConcluintes)));

        // Aba 3.5: Alunos sem Optativas
        modelAlunosSemOptativas = new DefaultListModel<>();
        abasResultados.addTab("Sem Optativas Cursadas", new JScrollPane(new JList<>(modelAlunosSemOptativas)));

        add(abasResultados, BorderLayout.CENTER);

        // --- AÇÃO DO BOTÃO ---
        botaoConsultar.addActionListener(e -> {
            nomeCurso = campoNomeCurso.getText();
            if (!nomeCurso.trim().isEmpty()) {
                popularComDadosMockados(nomeCurso);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira um nome de curso.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void popularComDadosMockados(String nomeCurso) {
        // Limpa os resultados de consultas anteriores
        modelObrigatorias.clear();
        modelOptativas.clear();
        modelAlunosCurso.clear();
        modelAlunosConcluintes.clear();
        modelAlunosSemOptativas.clear();
        
        System.out.println("Consultando dados para o curso: " + nomeCurso);

        // --- DADOS MOCKADOS DE EXEMPLO ---
        // 3.1: Disciplinas Obrigatórias
        List<String> disciplinasObrig = Organizador.listaDiscPorTipo(nomeCurso, "Obrigatoria");
        disciplinasObrig.forEach(modelObrigatorias::addElement);

        // 3.2: Disciplinas Optativas
        List<String> disciplinasOpta = Organizador.listaDiscPorTipo(nomeCurso, "optativa");
        disciplinasOpta.forEach(modelOptativas::addElement);

        // 3.3: Alunos do Curso
        List<String> listAlunCurso = Organizador.listaAlunosCurso(nomeCurso);
            listAlunCurso.forEach(modelAlunosCurso::addElement);
        
        // 3.4: Alunos que concluíram as obrigatórias
        List<String> listAlunCon = Organizador.listaAlunosConclui(nomeCurso);
            listAlunCon.forEach(modelAlunosConcluintes::addElement);
        
        // 3.5: Alunos que não fizeram optativas
        List<String> listAlunSem = Organizador.listaAlunosSemOp(nomeCurso);
            listAlunSem.forEach(modelAlunosSemOptativas::addElement);
    }
}