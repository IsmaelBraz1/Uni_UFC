/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

import com.mycompany.uniufc.Model.Aluno;
import com.mycompany.uniufc.Model.Departamento;
import com.mycompany.uniufc.Model.Professor;
import com.mycompany.uniufc.Model.*;
import com.mycompany.uniufc.Model.Disciplina.TipoDisciplina;
import java.sql.Date;

/**
 *
 * @author kaiqd
 */
public class Conexao {

    static Connection conectar() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            System.out.println("oie estou funcionando rsrs");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipe<542062>", "root", "K1234");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver do banco de dados nao encontrados");
        } catch (SQLException ex) {
            System.out.println("ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return conexao;
    }
// 1. Disciplinas por situação (Cursando/Cursada) de um aluno

    public static void disciplinasPorSituacao(int matricula, String situacao) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT * FROM disciplina WHERE cod_disc IN (
                SELECT cod_disc FROM turma WHERE id_turma in (
                    SELECT Turma_id_turma FROM matricula_na_turma 
                    WHERE Situacao = ? AND Aluno_matricula = ?
                )
            )
        """;
        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, situacao);
            stmt.setInt(2, matricula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo da disciplina:" + rs.getString("cod_disc") + " Nome da disciplina:" + rs.getString("nome_disc") + " Ementa:" + rs.getString("ementa")
                        + " Numero de Creditos:" + rs.getString("num_creditos") + " Tipo da disciplina:" + rs.getString("Tipo_disciplina") + " Cidigo do Curso" + rs.getString("cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 2. Curso do aluno
    public static String cursoDoAluno(int matricula) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_curso FROM curso 
            WHERE cod_curso = (SELECT Curso_cod_curso FROM aluno WHERE matricula = ?)
        """;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                System.out.println("Curso: " + rs.getString("nome_curso"));
                return rs.getString("nome_curso");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
        return null;
    }

    // 3. Dados pessoais do aluno
    public static void dadosPessoaisDoAluno(int matricula) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT a.matricula, a.nome_alu, a.endereco, a.tipo_alu, c.nome_curso , a.Curso_cod_curso
            FROM aluno a JOIN curso c ON a.Curso_cod_curso = c.cod_curso
            WHERE a.matricula = ?
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Matricula do Aluno: " + rs.getString("matricula") + " Nome: " + rs.getString("nome_alu") + " Endereço: " + rs.getString("endereco")
                        + " Tipo do Aluno: " + rs.getString("tipo_alu") + " Curso: " + rs.getString("nome_curso") + " Codigo do Curso:" + rs.getInt("Curso_cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 4. Cursos de um departamento
    public static void cursosDoDepartamento(int codDepart) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM curso WHERE cod_depart = ?";

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codDepart);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo do curso: " + rs.getString("cod_curso") + " Curso: " + rs.getString("nome_curso") + "Creditos minimos: " + rs.getString("creditos_minimos")
                        + "Codigo do departamento: " + rs.getString("cod_depart"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 5. Detalhes de um departamento
    public static void DetalhesDoDepartamento(int codDepart) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM departamento WHERE cod_depart = ?";

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codDepart);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo do departamento: " + rs.getInt("cod_depart") + " Disciplina: " + rs.getString("nome_depart"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 6. Disciplinas obrigatórias/optativas de um curso
    public static void disciplinasPorTipo(String nomeCurso, String tipo) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT * FROM disciplina WHERE Tipo_disciplina = ? AND cod_curso = (
                SELECT cod_curso FROM curso WHERE nome_curso = ?
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            stmt.setString(2, nomeCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo da disciplina: " + rs.getString("cod_disc") + " Disciplina: " + rs.getString("nome_disc") + " Ementa:" + rs.getString("ementa")
                        + " Numero de creditos: " + rs.getString("num_creditos") + " Tipo de Disciplina" + rs.getString("Tipo_disciplina") + " Codigo do curso:" + rs.getString("cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 7. Alunos de um curso
    public static void alunosDoCurso(String nomeCurso) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_alu FROM aluno WHERE Curso_cod_curso = (
                SELECT cod_curso FROM curso WHERE nome_curso = ?
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Aluno: " + rs.getString("nome_alu"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 8. Alunos que cursaram todas obrigatórias
    public static void alunosComTodasObrigatorias(String nomeCurso) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT aluno.matricula, aluno.nome_alu
            FROM aluno
            INNER JOIN matricula_na_turma ON aluno.matricula = matricula_na_turma.Aluno_matricula
            INNER JOIN turma ON matricula_na_turma.Turma_id_turma = turma.id_turma
            INNER JOIN disciplina ON turma.cod_disc = disciplina.cod_disc
            INNER JOIN curso ON disciplina.cod_curso = curso.cod_curso
            WHERE curso.nome_curso = ?
              AND disciplina.Tipo_disciplina = 'obrigatoria'
              AND matricula_na_turma.Situacao = 'cursada'
            GROUP BY aluno.matricula, aluno.nome_alu
            HAVING COUNT(DISTINCT disciplina.cod_disc) = (
                SELECT COUNT(cod_disc) FROM disciplina
                WHERE cod_curso = (SELECT cod_curso FROM curso WHERE nome_curso = ?)
                  AND Tipo_disciplina = 'obrigatoria'
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeCurso);
            stmt.setString(2, nomeCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Matricula: " + rs.getString("matricula") + " Aluno completo: " + rs.getString("nome_alu"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // 9. Alunos que não fizeram nenhuma optativa
    public static void alunosSemOptativas(String nomeCurso) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT a.matricula, a.nome_alu
            FROM aluno a
            WHERE NOT EXISTS (
              SELECT 1
              FROM matricula_na_turma m
              INNER JOIN turma t ON m.Turma_id_turma = t.id_turma
              INNER JOIN disciplina d ON t.cod_disc = d.cod_disc
              WHERE m.Aluno_matricula = a.matricula
                AND d.cod_curso = (SELECT cod_curso FROM curso WHERE nome_curso = ?)
                AND d.Tipo_disciplina = 'optativa'
                AND m.Situacao = 'cursada'
            )
        """;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Matricula: " + rs.getString("matricula") + " Sem optativas: " + rs.getString("nome_alu"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //10. Alunos em uma certa Disciplina
    public static void alunosDaDisciplina(String nomeDisciplina) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT * FROM aluno WHERE matricula IN (
                SELECT Aluno_matricula FROM matricula_na_turma 
                WHERE Turma_id_turma IN (
                    SELECT id_turma FROM turma 
                    WHERE cod_disc = (SELECT cod_disc FROM disciplina WHERE nome_disc = ?)
                )
            )
        """;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeDisciplina);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Matricula: " + rs.getString("matricula") + " Alunos: " + rs.getString("nome_alu") + " Endereco:" + rs.getString("endereco")
                        + " Tipo do aluno: " + rs.getString("tipo_alu") + " Codigo do curso:" + rs.getInt("Curso_cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //11. Dado uma disciplina, os pré-requisitos dela
    public static void prerequisitosDaDisciplina(String nomeDisciplina) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT * FROM disciplina WHERE cod_disc IN (
                SELECT cod_disc_pre_requisito FROM prerequisito 
                WHERE cod_disc = (SELECT cod_disc FROM disciplina WHERE nome_disc = ?)
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeDisciplina);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo da disciplina: " + rs.getInt("cod_disc") + " Pré-requisito: " + rs.getString("nome_disc") + " Ementa:" + rs.getString("ementa")
                        + " Numero de numeros: " + rs.getString("num_creditos") + " Tipo de disciplina: " + rs.getString("Tipo_disciplina") + " Codigo do curso:" + rs.getInt("cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //12. Dado uma disciplina, as disciplinas que ela é pré-requisito
    public static void disciplinasQueDependem(String nomeDisciplina) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT * FROM disciplina WHERE cod_disc IN (
                SELECT cod_disc FROM prerequisito 
                WHERE cod_disc_pre_requisito = (
                    SELECT cod_disc FROM disciplina WHERE nome_disc = ?
                )
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeDisciplina);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo da disciplina: " + rs.getInt("cod_disc") + " Pré-requisito: " + rs.getString("nome_disc") + " Ementa:" + rs.getString("ementa")
                        + " Numero de numeros: " + rs.getString("num_creditos") + " Tipo de disciplina: " + rs.getString("Tipo_disciplina") + " Codigo do curso:" + rs.getInt("cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //13. professor orientador
    public static void alunosOrientadosPor(String nomeProfessor) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT * FROM aluno WHERE matricula IN (
                SELECT matricula FROM alunoposgraduacao 
                WHERE siape_orient = (SELECT siape FROM professor WHERE nome_prof = ?)
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProfessor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Matricula do Aluno: " + rs.getString("matricula") + " Nome: " + rs.getString("nome_alu") + " Endereço: " + rs.getString("endereco")
                        + " Tipo do Aluno: " + rs.getString("tipo_alu") + " Codigo do Curso:" + rs.getInt("Curso_cod_curso"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //14. Dado um Orientador, as disciplinas que ele da
    public static void disciplinasDoOrientador(String nomeProfessor) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_disc FROM disciplina, professor WHERE cod_disc IN (
                SELECT cod_disc FROM turma WHERE siape_prof_1 = (
                    SELECT siape FROM professor WHERE nome_prof = ?
                ) OR siape_prof_2 = (
                    SELECT siape FROM professor WHERE nome_prof = ?
                )
            ) AND professor.siape = (
                SELECT siape_orient FROM alunoposgraduacao 
                WHERE siape_orient = (SELECT siape FROM professor WHERE nome_prof = ?)
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProfessor);
            stmt.setString(2, nomeProfessor);
            stmt.setString(3, nomeProfessor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Disciplina ministrada: " + rs.getString("nome_disc"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //15. Dado um Orientador, o total de creditos das disciplinas que ele da
    public static void creditosDoOrientador(String nomeProfessor) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_disc, num_creditos FROM disciplina, professor WHERE cod_disc IN (
                SELECT cod_disc FROM turma WHERE siape_prof_1 = (
                    SELECT siape FROM professor WHERE nome_prof = ?
                ) OR siape_prof_2 = (
                    SELECT siape FROM professor WHERE nome_prof = ?
                )
            ) AND professor.siape = (
                SELECT siape_orient FROM alunoposgraduacao 
                WHERE siape_orient = (SELECT siape FROM professor WHERE nome_prof = ?)
            )
        """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProfessor);
            stmt.setString(2, nomeProfessor);
            stmt.setString(3, nomeProfessor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Disciplina: " + rs.getString("nome_disc")
                        + ", Créditos: " + rs.getInt("num_creditos"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // ... você pode continuar com os métodos de disciplina, orientador, etc.
    public static List<Aluno> listarAlunos() throws SQLException, ClassNotFoundException {
        List<Aluno> lista = new ArrayList<>();

        String sql = """
        SELECT a.matricula, a.nome_alu, a.endereco, a.tipo_alu, a.Curso_cod_curso
        FROM aluno a
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nome = rs.getString("nome_alu");
                String endereco = rs.getString("endereco");
                String tipoStr = rs.getString("tipo_alu").toUpperCase();
                int periodo = rs.getInt("Curso_cod_curso");

                Aluno.TipoAluno tipo = Aluno.TipoAluno.valueOf(tipoStr);
                Aluno aluno = new Aluno(matricula, nome, endereco, tipo, periodo);
                lista.add(aluno);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static List<Professor> listarProfessores() throws SQLException, ClassNotFoundException {
        List<Professor> lista = new ArrayList<>();

        String sql = """
        SELECT p.siape, p.nome_prof, p.data_nas_prof, p.data_ingresso, p.cod_depart
        FROM professor p
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int siape = rs.getInt("siape");
                String nome = rs.getString("nome_prof");
                LocalDate dataNasProf = rs.getDate("data_nas_prof").toLocalDate();
                LocalDate dataIngresso = rs.getDate("data_ingresso").toLocalDate();
                int codDepart = rs.getInt("cod_depart");

                Professor professor = new Professor(siape, nome, dataNasProf, dataIngresso, codDepart);

                lista.add(professor);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar profs: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static String departPorCod(int codigo) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_depart FROM departamento
            WHERE cod_depart = ?
        """;
        String traducao = null;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                System.out.println("Departamento: " + rs.getString("nome_depart"));
                traducao = rs.getString("nome_depart");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
        return traducao;
    }

    public static List<Departamento> listarDepartamentos() throws SQLException, ClassNotFoundException {
        List<Departamento> lista = new ArrayList<>();

        String sql = """
        SELECT *
        FROM departamento
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cod_depart = rs.getInt("cod_depart");
                String nome = rs.getString("nome_depart");

                Departamento departamento = new Departamento(cod_depart, nome);

                lista.add(departamento);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar departs: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static List<Curso> listarCursos() throws SQLException, ClassNotFoundException {
        List<Curso> lista = new ArrayList<>();

        String sql = """
        SELECT *
        FROM curso
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cod_curso = rs.getInt("cod_curso");
                String nome = rs.getString("nome_curso");
                int creditos = rs.getInt("creditos_minimos");
                int cod_depart = rs.getInt("cod_depart");

                Curso curso = new Curso(cod_curso, nome, creditos, cod_depart);

                lista.add(curso);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar profs: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static List<Disciplina> listarDisciplinas() throws SQLException, ClassNotFoundException {
        List<Disciplina> lista = new ArrayList<>();

        String sql = """
        SELECT *
        FROM disciplina
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cod_disc = rs.getInt("cod_disc");
                String nome = rs.getString("nome_disc");
                String ementa = rs.getString("ementa");
                int creditos = rs.getInt("num_creditos");
                String tipoStr = rs.getString("Tipo_disciplina").toUpperCase();
                int cod_curso = rs.getInt("cod_curso");

                Disciplina.TipoDisciplina tipo = Disciplina.TipoDisciplina.valueOf(tipoStr);
                Disciplina disc = new Disciplina(cod_disc, nome, ementa, creditos, tipo, cod_curso);

                lista.add(disc);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static String CursoPorCod(int codigo) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_curso FROM curso
            WHERE cod_curso = ?
        """;
        String traducao = null;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                System.out.println("Curso: " + rs.getString("nome_curso"));
                traducao = rs.getString("nome_curso");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
        return traducao;
    }

    public static List<Turma> listarTurmas() throws SQLException, ClassNotFoundException {
        List<Turma> lista = new ArrayList<>();

        String sql = """
        SELECT *
        FROM turma
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cod_turma = rs.getInt("id_turma");
                int ano = rs.getInt("ano");
                int semestre = rs.getInt("semestre");
                int cod_disc = rs.getInt("cod_disc");
                int siape_p1 = rs.getInt("siape_prof_1");
                int siape_p2 = rs.getInt("siape_prof_2");

                Turma turm = new Turma(cod_turma, ano, semestre, cod_disc, siape_p1, siape_p2);

                lista.add(turm);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static String DisciplinaPorCod(int codigo) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_disc FROM disciplina
            WHERE cod_disc = ?
        """;
        String traducao = null;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                System.out.println("Disciplina: " + rs.getString("nome_disc"));
                traducao = rs.getString("nome_disc");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
        return traducao;
    }

    public static String ProfessorPorCod(int codigo) throws SQLException, ClassNotFoundException {
        String sql = """
            SELECT nome_prof FROM professor
            WHERE siape = ?
        """;
        String traducao = null;

        Connection conn = conectar();
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                System.out.println("Professor: " + rs.getString("nome_prof"));
                traducao = rs.getString("nome_prof");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
        return traducao;
    }

    public static List<Usuario> listarUsuarios() throws SQLException, ClassNotFoundException {
        List<Usuario> lista = new ArrayList<>();

        String sql = """
        SELECT *
        FROM usuario
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_user = rs.getInt("idUsuario");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String nv_acs = rs.getString("nivel_acesso").toUpperCase();

                Usuario.NivelAcesso nivel = Usuario.NivelAcesso.valueOf(nv_acs);
                Usuario user = new Usuario(id_user, nome, login, senha, nivel);

                lista.add(user);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    public static List<Funcionario> listarFuncionarios() throws SQLException, ClassNotFoundException {
        List<Funcionario> lista = new ArrayList<>();

        String sql = """
        SELECT *
        FROM funcionario
    """;

        Connection conn = conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_funcio = rs.getInt("id_funcionario");
                String nome = rs.getString("nome_funcionario");
                String end = rs.getString("endereco_funcionario");
                String tip_f = rs.getString("tipo_funcionario").toUpperCase();

                Funcionario.TipoFuncionario tipo = Funcionario.TipoFuncionario.valueOf(tip_f);
                Funcionario funcionario = new Funcionario(id_funcio, nome, end, tipo);

                lista.add(funcionario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    System.out.println("conexao finalizada ");
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

        return lista;
    }

    //PARTE DO ALUNO!!!!
    // Método para inserir um aluno
    public static void inserirAluno(Aluno aluno) throws SQLException, ClassNotFoundException {
        String sql = """
            INSERT INTO aluno (matricula, nome_alu, endereco, tipo_alu, Curso_cod_curso)
            VALUES (?, ?, ?, ?, ?)
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNomeAlu());
            stmt.setString(3, aluno.getEndereco());
            stmt.setString(4, aluno.getTipoAlu().name());
            stmt.setInt(5, aluno.getCodCurso());

            stmt.executeUpdate();
            System.out.println("Funcionou a iserção do aluno");
        } catch (Exception e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }

    }

    // Método para deletar um aluno
    public static void deletarAluno(int matricula) throws SQLException, ClassNotFoundException {
        String sql = """
            DELETE FROM aluno
            WHERE matricula = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno deletado com sucesso.");
            } else {
                System.out.println("Nenhum aluno encontrado com a matrícula informada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // Método para atualizar os dados de um aluno
    public static void atualizarAluno(Aluno aluno) throws SQLException, ClassNotFoundException {
        String sql = """
            UPDATE aluno
            SET nome_alu = ?, endereco = ?, tipo_alu = ?, Curso_cod_curso = ?
            WHERE matricula = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNomeAlu());
            stmt.setString(2, aluno.getEndereco());
            stmt.setString(3, aluno.getTipoAlu().name());
            stmt.setInt(4, aluno.getCodCurso());
            stmt.setInt(5, aluno.getMatricula());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno atualizado com sucesso.");
            } else {
                System.out.println("Nenhum aluno encontrado com a matrícula informada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    //PARTE DO PROFESSOR!!!
    //Método para inserir um professor
    public static void inserirProfessor(Professor prof) throws SQLException, ClassNotFoundException {
        String sql = """
            INSERT INTO Professor (siape, nome_prof, data_nas_prof, data_ingresso, cod_depart)
            VALUES (?, ?, ?, ?, ?)
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prof.getSiape());
            stmt.setString(2, prof.getNomeProf());
            stmt.setDate(3, Date.valueOf(prof.getDataNasProf()));
            stmt.setDate(4, Date.valueOf(prof.getDataIngresso()));
            stmt.setInt(5, prof.getCodDepart());

            stmt.executeUpdate();
            System.out.println("Professor inserido com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    //Método para deletar um professor
    public static void deletarProfessor(int siape, int codDepart) throws SQLException, ClassNotFoundException {
        String sql = """
            DELETE FROM Professor
            WHERE siape = ? AND cod_depart = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, siape);
            stmt.setInt(2, codDepart);

            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Professor deletado com sucesso." : "Professor não encontrado.");

        } catch (Exception e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }
    
    //Método para atualizar um professor
    public static void atualizarProfessor(Professor prof) throws SQLException, ClassNotFoundException {
        String sql = """
            UPDATE Professor
            SET nome_prof = ?, data_nas_prof = ?, data_ingresso = ?
            WHERE siape = ? AND cod_depart = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prof.getNomeProf());
            stmt.setDate(2, Date.valueOf(prof.getDataNasProf()));
            stmt.setDate(3, Date.valueOf(prof.getDataIngresso()));
            stmt.setInt(4, prof.getSiape());
            stmt.setInt(5, prof.getCodDepart());

            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Professor atualizado com sucesso." : "Professor não encontrado.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    
    //PARTE DO FUNCIONARIO!!!
    //Método para inserir um funcionario
    public static void inserirFuncionario(Funcionario func) throws SQLException, ClassNotFoundException {
        String sql = """
            INSERT INTO Funcionario (id_funcionario, nome_funcionario, endereco_funcionario, tipo_funcionario)
            VALUES (?, ?, ?, ?)
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, func.getIdFuncionario());
            stmt.setString(2, func.getNomeFuncionario());
            stmt.setString(3, func.getEnderecoFuncionario());
            stmt.setString(4, func.getTipoFuncionario().name());

            stmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    //Método para deletar um funcionario
    public static void deletarFuncionario(int id) throws SQLException, ClassNotFoundException {
        String sql = """
            DELETE FROM Funcionario
            WHERE id_funcionario = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Funcionário deletado com sucesso." : "Funcionário não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    //Método para atualizar um funcionario
    public static void atualizarFuncionario(Funcionario func) throws SQLException, ClassNotFoundException {
        String sql = """
            UPDATE Funcionario
            SET nome_funcionario = ?, endereco_funcionario = ?, tipo_funcionario = ?
            WHERE id_funcionario = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, func.getNomeFuncionario());
            stmt.setString(2, func.getEnderecoFuncionario());
            stmt.setString(3, func.getTipoFuncionario().name());
            stmt.setInt(4, func.getIdFuncionario());
            
       

            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Funcionário atualizado com sucesso." : "Funcionário não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }
    
    // PARTE DO USUARIO!!!
    // Método para inserir um usuario
    public static void inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        String sql = """
            INSERT INTO Usuario (idUsuario, nome, login, senha, nivel_acesso)
            VALUES (?, ?, ?, ?, ?)
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getNivelAcesso().name());

            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    // Método para deletar um usuario
    public static void deletarUsuario(int id) throws SQLException, ClassNotFoundException {
        String sql = """
            DELETE FROM Usuario
            WHERE idUsuario = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Usuário deletado com sucesso." : "Usuário não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    // Método para atualizar um usuario
    public static void atualizarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        String sql = """
            UPDATE Usuario
            SET nome = ?, login = ?, senha = ?, nivel_acesso = ?
            WHERE idUsuario = ?
        """;

        Connection conn = Conexao.conectar();

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getNivelAcesso().name());
            stmt.setInt(5, usuario.getIdUsuario());

            int linhas = stmt.executeUpdate();
            System.out.println(linhas > 0 ? "Usuário atualizado com sucesso." : "Usuário não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    

    
    // Método de teste (opcional)
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Aluno kinakas = new Aluno(3000, "kink", "aculaaaa", Aluno.TipoAluno.GRADUACAO, 100);
       
        LocalDate dataNascimento = LocalDate.of(1980, 5, 15); // 15 de maio de 1980
        LocalDate dataAdmissao = LocalDate.of(2020, 2, 10);   // 10 de fevereiro de 2020
        Professor Jarbas = new Professor(12222, "Jarbinhas", dataNascimento, dataAdmissao, 1);
        
        Funcionario gabi = new Funcionario(3003, "Gabi", "puta que pariu", Funcionario.TipoFuncionario.PORTARIA);
        
        Usuario usuario = new Usuario(5, "Fernando", "admin", "root", Usuario.NivelAcesso.DBA);
                
        //aluno
//        atualizarAluno(kinakas);
//        deletarAluno(3000);
//        inserirAluno(kinakas);
       
        //professor
//        inserirProfessor(Jarbas);
//        deletarProfessor(12222, 1);
//        atualizarProfessor(Jarbas);
        
        //funcionario
//        inserirFuncionario(gabi);
//        deletarFuncionario(3003);
//        atualizarFuncionario(gabi);

        //usuarios 
//        inserirUsuario(usuario);
//        deletarUsuario(5);
        atualizarUsuario(usuario);
        
        disciplinasPorSituacao(10003, "cursada");
        cursoDoAluno(10003);
        dadosPessoaisDoAluno(10003);
        cursosDoDepartamento(1);
        DetalhesDoDepartamento(1);
        disciplinasPorTipo("psicologia", "optativa");
        alunosComTodasObrigatorias("psicologia");
        alunosDoCurso("psicologia");
        alunosSemOptativas("psicologia");
        alunosDaDisciplina("Banco de Dados");
        prerequisitosDaDisciplina("Sistemas operacionais");
        disciplinasQueDependem("Banco de Dados");
        alunosOrientadosPor("carlos silva");
        disciplinasDoOrientador("carlos silva");
        creditosDoOrientador("carlos silva");

    }
}
