/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

import com.mycompany.uniufc.Control.Conexao;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.lang.ClassNotFoundException;
/**
 *
 * @author Lucas
 */
public class Organizador {
    
    
    
    
    public static List<Aluno> listaAlu(){
        List<Aluno> listaDeAlunos = new ArrayList<>();
        try {
                listaDeAlunos = Conexao.listarAlunos(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeAlunos != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum aluno carregado!");
            }
            
    return listaDeAlunos;
    }
    
    
    
    public static List<Professor> listaProf(){
        List<Professor> listaDeProfessores = new ArrayList<>();
        try {
                listaDeProfessores = Conexao.listarProfessores(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar professores: " + e.getMessage());
            }

            if (listaDeProfessores != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum professor carregado!");
            }
            
    return listaDeProfessores;
    }

    public static List<Departamento> listaDepart(){
        List<Departamento> listaDeDeparts = new ArrayList<>();
        try {
                listaDeDeparts = Conexao.listarDepartamentos(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeDeparts != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Departamento carregado!");
            }
            
    return listaDeDeparts;
    }
    
    
    public static List<Curso> listaCurso(){
        List<Curso> listaDeCursos = new ArrayList<>();
        try {
                listaDeCursos = Conexao.listarCursos(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeCursos != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Departamento carregado!");
            }
            
    return listaDeCursos;
    }
    
    
    public static List<Disciplina> listaDisc(){
        List<Disciplina> listaDeDisc = new ArrayList<>();
        try {
                listaDeDisc = Conexao.listarDisciplinas(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeDisc != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Departamento carregado!");
            }
            
    return listaDeDisc;
    }
    
    
        public static List<Turma> listaTurms(){
        List<Turma> listaDeTurm = new ArrayList<>();
        try {
                listaDeTurm = Conexao.listarTurmas(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeTurm != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Departamento carregado!");
            }
            
    return listaDeTurm;
    }
        
        
    public static List<Usuario> listaUsers(){
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        try {
                listaDeUsuarios = Conexao.listarUsuarios(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeUsuarios != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Departamento carregado!");
            }
            
    return listaDeUsuarios;
    }
    
    
        public static List<Funcionario> listaFuncio(){
        List<Funcionario> listaDeFuncionarios = new ArrayList<>();
        try {
                listaDeFuncionarios = Conexao.listarFuncionarios(); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar alunos: " + e.getMessage());
            }

            if (listaDeFuncionarios != null) {
                // monta tabela
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Departamento carregado!");
            }
            
    return listaDeFuncionarios;
    }
    
    
    public static String tradutor(String cod, int codigo){
        String traducao = "N/D";
        
        if(cod == "Aluno"){
            try {
            traducao = Conexao.cursoDoAluno(codigo);
                } catch (SQLException e) {
                e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                e.printStackTrace();
                }
        }
        if(cod == "Departamento"){
            try {
            traducao = Conexao.departPorCod(codigo);
                } catch (SQLException e) {
                e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                e.printStackTrace();
                }
        }
        if(cod == "Curso"){
            try {
            traducao = Conexao.CursoPorCod(codigo);
                } catch (SQLException e) {
                e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                e.printStackTrace();
                }
        }
        if(cod == "Disciplina"){
            try {
            traducao = Conexao.DisciplinaPorCod(codigo);
                } catch (SQLException e) {
                e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                e.printStackTrace();
                }
        }
        if(cod == "Professor"){
            try {
            traducao = Conexao.ProfessorPorCod(codigo);
                } catch (SQLException e) {
                e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                e.printStackTrace();
                }
        }
        return traducao; 
    }
    
}
