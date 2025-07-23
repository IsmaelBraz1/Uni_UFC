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
import com.mycompany.uniufc.Model.Curso;
import com.mycompany.uniufc.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelConsultaAluno extends JPanel {

    private JTextField campoMatricula;
    private JButton botaoConsultar;
    private JTabbedPane abasResultados;

    // Componentes para as abas de resultado
    private DefaultListModel<String> modelMatriculasAtuais;
    private DefaultListModel<String> modelDisciplinasConcluidas;
    private JTextField campoNomeCurso;
    private DefaultTableModel modelDadosPessoais;
    
    private int matriculaint;

    public PainelConsultaAluno() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL DE ENTRADA (NORTE) ---
        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEntrada.add(new JLabel("Matrícula do Aluno:"));
        campoMatricula = new JTextField(15);
        painelEntrada.add(campoMatricula);
        botaoConsultar = new JButton("Consultar");
        painelEntrada.add(botaoConsultar);
        add(painelEntrada, BorderLayout.NORTH);

        // --- PAINEL DE RESULTADOS COM ABAS (CENTRO) ---
        abasResultados = new JTabbedPane();

        // Aba 1: Matrículas Atuais (agora com JList)
        modelMatriculasAtuais = new DefaultListModel<>();
        JList<String> listaAtuais = new JList<>(modelMatriculasAtuais);
        abasResultados.addTab("Matrículas Atuais", new JScrollPane(listaAtuais));

        // Aba 2: Disciplinas Concluídas (agora com JList)
        modelDisciplinasConcluidas = new DefaultListModel<>();
        JList<String> listaConcluidas = new JList<>(modelDisciplinasConcluidas);
        abasResultados.addTab("Disciplinas Concluídas", new JScrollPane(listaConcluidas));

        // Aba 3: Dados do Curso (agora com campo de texto)
        JPanel painelCurso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCurso.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        painelCurso.add(new JLabel("Nome do Curso:"));
        campoNomeCurso = new JTextField(30);
        campoNomeCurso.setEditable(false); // Apenas visualização
        painelCurso.add(campoNomeCurso);
        abasResultados.addTab("Dados do Curso", painelCurso);

        // Aba 4: Dados Pessoais (com tabela não-editável)
        String[] colunas = {"Matrícula", "Nome", "Tipo", "Curso"};
        // Sobrescrevemos o método isCellEditable para travar a edição
        modelDadosPessoais = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        JTable tabelaDadosPessoais = new JTable(modelDadosPessoais);
        abasResultados.addTab("Dados Pessoais", new JScrollPane(tabelaDadosPessoais));

        add(abasResultados, BorderLayout.CENTER);

        // --- AÇÃO DO BOTÃO ---
        botaoConsultar.addActionListener(e -> {
            String matricula = campoMatricula.getText();
            
            matriculaint = Integer.parseInt(matricula);
            if (!matricula.trim().isEmpty()) {
                // Simula a busca no banco de dados e preenche os componentes
                popularComDadosMockados();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira uma matrícula.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void popularComDadosMockados() {
        // Limpa os dados de consultas anteriores
        modelMatriculasAtuais.clear();
        modelDisciplinasConcluidas.clear();
        campoNomeCurso.setText("");
        modelDadosPessoais.setRowCount(0);

        
    
        // --- DADOS MOCKADOS DE EXEMPLO ---
        // 1.1: Disciplinas atualmente matriculado
        List<String> disciplinasAtuais = Organizador.listaDiscAtuais(matriculaint);
        disciplinasAtuais.forEach(modelMatriculasAtuais::addElement); 
        
        // 1.2: Disciplinas já concluídas
        List<String> disciplinasConcluidas = Organizador.listaDisConc(matriculaint);
        disciplinasConcluidas.forEach(modelDisciplinasConcluidas::addElement);

        // 1.3: Curso do aluno
        Curso cursoDoAluno = new Curso(1, "Sistemas de Informação", 200, 101);
        String curso = Organizador.cursoAluno(matriculaint);
        campoNomeCurso.setText(curso);

        // 1.4: Dados pessoais do aluno
        Aluno alunoConsultado = Organizador.dadosAluno(matriculaint);
        modelDadosPessoais.addRow(new Object[]{
            alunoConsultado.getMatricula(),
            alunoConsultado.getNomeAlu(),
            alunoConsultado.getTipoAlu().toString(),
            cursoDoAluno.getNomeCurso()
        });
    }
}
