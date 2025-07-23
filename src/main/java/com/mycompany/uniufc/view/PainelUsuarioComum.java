/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Model.Disciplina;
import com.mycompany.uniufc.Model.Matricula;
import com.mycompany.uniufc.Model.Usuario;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class PainelUsuarioComum extends JPanel {

    private Usuario usuarioLogado;
    private JList<Disciplina> listaDisciplinas;
    private DefaultListModel<Disciplina> modelDisciplinas;

    // Componentes do painel de detalhes
    private CardLayout cardLayoutDetalhes;
    private JPanel painelDetalhes;

    // --- Componentes específicos do Aluno ---
    private JTextField campoNota, campoFrequencia;

    // --- Componentes específicos do Professor ---
    private DefaultTableModel modelAlunosDaTurma;
    private JTable tabelaAlunosDaTurma;

    public PainelUsuarioComum(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        setLayout(new BorderLayout());

        // --- PAINEL ESQUERDO (MESTRE - LISTA DE DISCIPLINAS) ---
        modelDisciplinas = new DefaultListModel<>();
        listaDisciplinas = new JList<>(modelDisciplinas);
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setBorder(BorderFactory.createTitledBorder("Minhas Disciplinas"));
        painelEsquerdo.add(new JScrollPane(listaDisciplinas), BorderLayout.CENTER);

        // --- PAINEL DIREITO (DETALHES) ---
        cardLayoutDetalhes = new CardLayout();
        painelDetalhes = new JPanel(cardLayoutDetalhes);

        // --- Construção do Painel de Detalhes do Aluno ---
        JPanel detalheAluno = new JPanel(new GridBagLayout());
        detalheAluno.setBorder(BorderFactory.createTitledBorder("Meu Desempenho"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        detalheAluno.add(new JLabel("Média Final:"), gbc);
        gbc.gridy = 1;
        detalheAluno.add(new JLabel("Frequência (%):"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        campoNota = new JTextField(10);
        campoNota.setEditable(false);
        detalheAluno.add(campoNota, gbc);
        gbc.gridy = 1;
        campoFrequencia = new JTextField(10);
        campoFrequencia.setEditable(false);
        detalheAluno.add(campoFrequencia, gbc);
        painelDetalhes.add(detalheAluno, "aluno");

        // --- Construção do Painel de Detalhes do Professor ---
        JPanel detalheProfessor = new JPanel(new BorderLayout());
        detalheProfessor.setBorder(BorderFactory.createTitledBorder("Alunos da Turma"));
        modelAlunosDaTurma = new DefaultTableModel(new String[]{"Matrícula", "Nome do Aluno", "Média Final", "Frequência"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaAlunosDaTurma = new JTable(modelAlunosDaTurma);
        detalheProfessor.add(new JScrollPane(tabelaAlunosDaTurma), BorderLayout.CENTER);
        painelDetalhes.add(detalheProfessor, "professor");

        // --- JSplitPane para juntar tudo ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDetalhes);
        splitPane.setDividerLocation(250);
        add(splitPane, BorderLayout.CENTER);

        // --- LÓGICA PARA ADAPTAR A TELA AO USUÁRIO ---
        if (usuarioLogado.getNivelAcesso() == Usuario.NivelAcesso.ALUNO) {
            carregarDadosAluno();
            cardLayoutDetalhes.show(painelDetalhes, "aluno");
        } else if (usuarioLogado.getNivelAcesso() == Usuario.NivelAcesso.PROFESSOR) {
            carregarDadosProfessor();
            cardLayoutDetalhes.show(painelDetalhes, "professor");
        }

        // --- Listener para atualizar os detalhes ---
        listaDisciplinas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Disciplina disciplinaSelecionada = listaDisciplinas.getSelectedValue();
                if (disciplinaSelecionada != null) {
                    atualizarDetalhes(disciplinaSelecionada);
                }
            }
        });
    }

    private void carregarDadosAluno() {
        // Mock: Disciplinas do Aluno
        List<Disciplina> disciplinasDoAluno = List.of(
                new Disciplina(102, "Banco de Dados", null, 4, null, 0),
                new Disciplina(201, "Engenharia de Requisitos", null, 4, null, 0)
        );
        disciplinasDoAluno.forEach(modelDisciplinas::addElement);
    }

    private void carregarDadosProfessor() {
        // Mock: Disciplinas do Professor
        List<Disciplina> disciplinasDoProfessor = List.of(
                new Disciplina(102, "Banco de Dados", null, 4, null, 0)
        );
        disciplinasDoProfessor.forEach(modelDisciplinas::addElement);
    }

    private void atualizarDetalhes(Disciplina disciplina) {
        if (usuarioLogado.getNivelAcesso() == Usuario.NivelAcesso.ALUNO) {
            // Mock: Busca a nota e frequência do aluno para a disciplina selecionada
            Matricula matriculaMock = new Matricula(usuarioLogado.getIdUsuario(), 0, Matricula.Situacao.ATIVA, new BigDecimal("8.5"), new BigDecimal("92.5"));
            campoNota.setText(matriculaMock.getMediaFinal().toString());
            campoFrequencia.setText(matriculaMock.getFrequencia().toString());
        } else if (usuarioLogado.getNivelAcesso() == Usuario.NivelAcesso.PROFESSOR) {
            // Mock: Busca todos os alunos da turma daquela disciplina
            modelAlunosDaTurma.setRowCount(0); // Limpa a tabela
            List<Matricula> matriculasDaTurma = List.of(
                    new Matricula(202510, 0, Matricula.Situacao.ATIVA, new BigDecimal("8.5"), new BigDecimal("92.5")),
                    new Matricula(202511, 0, Matricula.Situacao.ATIVA, new BigDecimal("7.0"), new BigDecimal("88.0"))
            );
            // Simula um JOIN com a tabela de Alunos
            matriculasDaTurma.forEach(m -> {
                modelAlunosDaTurma.addRow(new Object[]{
                    m.getAlunoMatricula(),
                    "Aluno " + m.getAlunoMatricula(), // Nome mockado
                    m.getMediaFinal(),
                    m.getFrequencia()
                });
            });
        }
    }
}
