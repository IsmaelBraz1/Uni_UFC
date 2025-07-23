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
import com.mycompany.uniufc.Model.Departamento;
import com.mycompany.uniufc.Model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelConsultaDepartamento extends JPanel {

    private JTextField campoCodDepartamento;
    private JButton botaoConsultar;
    private JTextField campoNomeResultado;
    private DefaultTableModel modelCursos;
    
    private int codint;

    public PainelConsultaDepartamento() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL DE ENTRADA (NORTE) ---
        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEntrada.add(new JLabel("Código do Departamento:"));
        campoCodDepartamento = new JTextField(10);
        painelEntrada.add(campoCodDepartamento);
        botaoConsultar = new JButton("Consultar");
        painelEntrada.add(botaoConsultar);
        add(painelEntrada, BorderLayout.NORTH);

        // --- PAINEL DE RESULTADOS (CENTRO) ---
        JPanel painelResultados = new JPanel(new BorderLayout(10, 10));
        add(painelResultados, BorderLayout.CENTER);

        // Sub-painel para o nome do departamento
        JPanel painelNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelNome.setBorder(BorderFactory.createTitledBorder(null, "Detalhes do Departamento", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12)));
        painelNome.add(new JLabel("Nome:"));
        campoNomeResultado = new JTextField(30);
        campoNomeResultado.setEditable(false);
        painelNome.add(campoNomeResultado);
        painelResultados.add(painelNome, BorderLayout.NORTH);

        // Tabela para os cursos do departamento
        String[] colunas = {"Cód. Curso", "Nome do Curso"};
        modelCursos = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela apenas para visualização
            }
        };
        JTable tabelaCursos = new JTable(modelCursos);
        JScrollPane scrollTabela = new JScrollPane(tabelaCursos);
        scrollTabela.setBorder(BorderFactory.createTitledBorder(null, "Cursos Sob Responsabilidade", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12)));
        painelResultados.add(scrollTabela, BorderLayout.CENTER);

        // --- AÇÃO DO BOTÃO ---
        botaoConsultar.addActionListener(e -> {
            String codDepto = campoCodDepartamento.getText();
            codint = Integer.parseInt(codDepto);
            
            if (!codDepto.trim().isEmpty()) {
                popularComDadosMockados(codDepto);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira um código de departamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void popularComDadosMockados(String codDepto) {
        // Limpa os resultados anteriores
        campoNomeResultado.setText("");
        modelCursos.setRowCount(0);

        // --- DADOS MOCKADOS DE EXEMPLO ---
        // Simula a busca no banco pelo departamento com o código informado
        Departamento deptoEncontrado = Organizador.DeptCod(codint);
        List<Curso> cursosDoDepto = Organizador.listCursoDep(codint);

        // Preenche os componentes com os dados encontrados
        // 2.2: Nome do departamento
        campoNomeResultado.setText(deptoEncontrado.getNomeDepart());

        // 2.1: Cursos do departamento
        for (Curso curso : cursosDoDepto) {
            modelCursos.addRow(new Object[]{curso.getCodCurso(), curso.getNomeCurso()});
        }
    }
}
