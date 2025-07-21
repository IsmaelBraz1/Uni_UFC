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

public class PainelConsultas extends JPanel {

    public PainelConsultas() {
        setLayout(new BorderLayout());

        // --- PAINEL DA ESQUERDA (NAVEGAÇÃO) ---
        String[] tiposConsulta = {"Por Aluno", "Por Departamento", "Por Curso", "Por Disciplina", "Por Professor/Orientador"};
        JList<String> listaNavegacao = new JList<>(tiposConsulta);
        listaNavegacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.add(new JLabel("Tipos de Consulta:"), BorderLayout.NORTH);
        painelEsquerdo.add(new JScrollPane(listaNavegacao), BorderLayout.CENTER);
        painelEsquerdo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // --- PAINEL DA DIREITA (ÁREA DE TRABALHO COM CARDLAYOUT) ---
        JPanel painelDireito = new JPanel();
        CardLayout cardLayout = new CardLayout();
        painelDireito.setLayout(cardLayout);

        // Adiciona o painel de consulta de aluno que criamos
        PainelConsultaAluno painelAluno = new PainelConsultaAluno();
        painelDireito.add(painelAluno, "aluno");

        // Adiciona painéis provisórios para os outros
        PainelConsultaDepartamento painelDepartamento = new PainelConsultaDepartamento();
        painelDireito.add(painelDepartamento, "departamento");
        
         PainelConsultaCurso painelCurso = new PainelConsultaCurso();
        painelDireito.add(painelCurso, "curso");
        
        PainelConsultaDisciplina painelDisciplina = new PainelConsultaDisciplina();
        painelDireito.add(painelDisciplina, "disciplina");

       PainelConsultaProfessor painelProfessor = new PainelConsultaProfessor();
        painelDireito.add(painelProfessor, "professor");
        // --- Listener para trocar o card da direita conforme a seleção da esquerda ---
        listaNavegacao.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selecionado = listaNavegacao.getSelectedValue();
                switch (selecionado) {
                    case "Por Aluno": cardLayout.show(painelDireito, "aluno"); break;
                    case "Por Departamento": cardLayout.show(painelDireito, "departamento"); break;
                    case "Por Curso": cardLayout.show(painelDireito, "curso"); break;
                    case "Por Disciplina": cardLayout.show(painelDireito, "disciplina"); break;
                    case "Por Professor/Orientador": cardLayout.show(painelDireito, "professor"); break;
                }
            }
        });

        // --- JSplitPane para juntar tudo ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setDividerLocation(200); // Posição inicial do divisor
        add(splitPane, BorderLayout.CENTER);
        
        listaNavegacao.setSelectedIndex(0); // Inicia com "Por Aluno" selecionado
    }
}
