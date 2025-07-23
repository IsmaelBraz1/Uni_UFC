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
                listaDeDeparts = Conexao.listarDepartamentos(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (listaDeDeparts != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return listaDeDeparts;
    }
    
    
    public static List<Curso> listaCurso(){
        List<Curso> listaDeCursos = new ArrayList<>();
        try {
                listaDeCursos = Conexao.listarCursos(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado" + e.getMessage());
            }

            if (listaDeCursos != null) {
              
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!!");
            }
            
    return listaDeCursos;
    }
    
    
    public static List<Disciplina> listaDisc(){
        List<Disciplina> listaDeDisc = new ArrayList<>();
        try {
                listaDeDisc = Conexao.listarDisciplinas(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (listaDeDisc != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return listaDeDisc;
    }
    
    
        public static List<Turma> listaTurms(){
        List<Turma> listaDeTurm = new ArrayList<>();
        try {
                listaDeTurm = Conexao.listarTurmas(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!" + e.getMessage());
            }

            if (listaDeTurm != null) {
             
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return listaDeTurm;
    }
        
        
    public static List<Usuario> listaUsers(){
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        try {
                listaDeUsuarios = Conexao.listarUsuarios(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (listaDeUsuarios != null) {
               
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return listaDeUsuarios;
    }
    
    
     public static List<Funcionario> listaFuncio(){
        List<Funcionario> listaDeFuncionarios = new ArrayList<>();
        try {
                listaDeFuncionarios = Conexao.listarFuncionarios(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (listaDeFuncionarios != null) {
               
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
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


    public static List<String> listaDisConc(int matricula) {
        List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.disciplinasPorSituacao(matricula, "Cursada");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (lista != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista;
    }

    public static List<String> listaDiscAtuais(int matricula) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.disciplinasPorSituacao(matricula, "Ativa");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (lista != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    

    public static String cursoAluno(int codigo) {
       String curso = null;
        try {
                curso = Conexao.cursoDoAluno(codigo); // ou carregarAlunosDoBanco()
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (curso != null) {
               
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return curso; 
    }

    public static Aluno dadosAluno(int matricula) {
           Aluno aluno = null;
        try {
                aluno = Conexao.dadosPessoaisDoAluno(matricula); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (aluno != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return aluno;    
    }

    public static List<Curso> listCursoDep(int codint) {
        List<Curso> listaDeCursos = new ArrayList<>();
        try {
                listaDeCursos = Conexao.cursosDoDepartamento(codint);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (listaDeCursos != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return listaDeCursos;
    }

    public static Departamento DeptCod(int codint) {
        Departamento dept = null;
        try {
                dept = Conexao.DetalhesDoDepartamento(codint); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (dept != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return dept;
    }
    
    public static List<String> listaDiscPorTipo(String nome, String tipo) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.disciplinasPorTipo(nome, tipo); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (lista != null) {
               
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!!");
            }
            
    return lista; 
    }
    
    public static List<String> listaAlunosCurso(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.alunosDoCurso(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!" + e.getMessage());
            }

            if (lista != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
        public static List<String> listaAlunosConclui(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.alunosComTodasObrigatorias(nome);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!" + e.getMessage());
            }

            if (lista != null) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
        
    public static List<String> listaAlunosSemOp(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.alunosSemOptativas(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (lista != null) {
               
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
    public static List<String> listaAlunosPorDisc(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.alunosDaDisciplina(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!" + e.getMessage());
            }

            if (lista != null) {
            
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
    public static List<String> listaPrerequisitos(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.prerequisitosDaDisciplina(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (lista != null) {
              
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
    public static List<String> listaDeDepende(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.disciplinasQueDependem(nome);  
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado! " + e.getMessage());
            }

            if (lista != null) {
            
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
    public static List<String> listaProfOrient(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.alunosOrientadosPor(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!: " + e.getMessage());
            }

            if (lista != null) {
         
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
    public static List<String> listaDiscProfOri(String nome) {
       List<String> lista = new ArrayList<>();
        try {
                lista = Conexao.disciplinasDoOrientador(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!: " + e.getMessage());
            }

            if (lista != null) {
  
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!");
            }
            
    return lista; 
    }
    
    public static int numCreditos(String nome) {
       List<Integer> lista = new ArrayList<>();
        try {
                lista = Conexao.creditosDoOrientador(nome); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nada carregado!: " + e.getMessage());
            }

            if (lista != null) {
              
            } else {
                JOptionPane.showMessageDialog(null, "Nada carregado!!");
            }
            
            int soma = 0;
            for (int num : lista) {
                soma += num;
               }
            
    return soma; 
    }
}