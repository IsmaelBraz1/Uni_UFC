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
        // O layout do próprio PainelDBA, que vai conter o painel de abas
        setLayout(new BorderLayout());

        // Cria o componente principal que conterá as abas
        abas = new JTabbedPane();

        // --- ABA DE DEPARTAMENTOS ---
        // Cria uma instância do painel de gerenciamento de departamentos
        PainelGerenciamentoDepartamentos painelDepartamentos = new PainelGerenciamentoDepartamentos();
        // Adiciona o painel como uma aba, com o título "Departamentos"
        abas.addTab("Departamentos", painelDepartamentos);

        // --- ABA DE CURSOS ---
        // Cria uma instância do painel de gerenciamento de cursos
        PainelGerenciamentoCursos painelCursos = new PainelGerenciamentoCursos();
        // Adiciona o painel como uma aba, com o título "Cursos"
        abas.addTab("Cursos", painelCursos);

        // --- ABA DE ALUNOS ---
        // Cria uma instância do painel de gerenciamento de alunos
        PainelGerenciamentoAlunos painelAlunos = new PainelGerenciamentoAlunos();
        // Adiciona o painel como uma aba, com o título "Alunos"
        abas.addTab("Alunos", painelAlunos);

        // --- ABA DE PROFESSORES ---
        // Cria uma instância do painel de gerenciamento de professores
        PainelGerenciamentoProfessores painelProfessores = new PainelGerenciamentoProfessores();
        // Adiciona o painel como uma aba, com o título "Professores"
        abas.addTab("Professores", painelProfessores);

        // --- ABA DE DISCIPLINAS ---
        // Cria uma instância do painel de gerenciamento de disciplinas
        PainelGerenciamentoDisciplinas painelDisciplinas = new PainelGerenciamentoDisciplinas();
        // Adiciona o painel como uma aba, com o título "Disciplinas"
        abas.addTab("Disciplinas", painelDisciplinas);
        
        PainelGerenciamentoTurmas painelTurmas = new PainelGerenciamentoTurmas();
        abas.addTab("Turmas", painelTurmas);
        
         PainelGerenciamentoUsuarios painelUsuarios = new PainelGerenciamentoUsuarios();
        abas.addTab("Usuários", painelUsuarios);
        
        PainelGerenciamentoFuncionarios painelFuncionarios = new PainelGerenciamentoFuncionarios();
        abas.addTab("Funcionários", painelFuncionarios);
        // Adiciona o painel de abas (com todas as abas dentro dele) ao centro do PainelDBA
        add(abas, BorderLayout.CENTER);
    }
}