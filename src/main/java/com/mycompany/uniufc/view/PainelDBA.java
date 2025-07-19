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
import com.mycompany.uniufc.Model.Professor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate; // Import para usar LocalDate nos dados falsos
import java.util.Arrays;
import java.util.List;


public class PainelDBA extends JPanel {

    private JTabbedPane abas;

    public PainelDBA() {
        // O layout do próprio PainelDBA
        setLayout(new BorderLayout());

        // Cria o painel com abas
        abas = new JTabbedPane();

        // --- ABA DE DEPARTAMENTOS ---
        // Cria uma instância do nosso novo painel de gerenciamento
        PainelGerenciamentoDepartamentos painelDepartamentos = new PainelGerenciamentoDepartamentos();
        // Adiciona o painel como uma aba
        abas.addTab("Departamentos", painelDepartamentos);

        // --- ABA DE CURSOS (Exemplo de como adicionar outras abas) ---
        PainelGerenciamentoCursos painelCursos = new PainelGerenciamentoCursos();
        abas.addTab("Cursos", painelCursos);

        // --- ABA DE ALUNOS (Exemplo) ---
         PainelGerenciamentoAlunos painelAlunos = new PainelGerenciamentoAlunos();
         abas.addTab("Alunos", painelAlunos);
         
         PainelGerenciamentoProfessores painelProfessores = new PainelGerenciamentoProfessores();
        abas.addTab("Professores", painelProfessores);
        
        // Adiciona mais abas para outras entidades conforme necessário...
        // abas.addTab("Professores", new PainelGerenciamentoProfessores());
        // abas.addTab("Disciplinas", new PainelGerenciamentoDisciplinas());

        // Adiciona o painel de abas ao PainelDBA
        add(abas, BorderLayout.CENTER);
    }
}