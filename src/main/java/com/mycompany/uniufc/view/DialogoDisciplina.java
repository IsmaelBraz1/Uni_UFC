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
import com.mycompany.uniufc.Model.Disciplina;
import javax.swing.*;
import java.awt.*;
import java.util.List;



public class DialogoDisciplina extends JDialog {

    private JTextField campoCod, campoNome, campoCreditos;
    private JComboBox<Disciplina.TipoDisciplina> comboTipo;
    private JComboBox<Curso> comboCursos;
    private JTextArea areaEmenta;
    private JButton botaoSalvar, botaoCancelar;
    private JButton botaoGerenciarPreRequisitos;
    
    private Disciplina disciplinaResultante;
    private boolean salvo = false;

    public DialogoDisciplina(Frame owner, Disciplina disciplinaParaEditar, List<Curso> cursos, List<Disciplina> todasAsDisciplinas) {
        super(owner, true);
        setTitle(disciplinaParaEditar == null ? "Adicionar Disciplina" : "Editar Disciplina");

        // --- PAINEL DO FORMULÁRIO (COMPONENTES DE TEXTO E LISTAS) ---
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Criação dos campos de texto, combos, etc.
        campoCod = new JTextField(10);
        campoNome = new JTextField();
        areaEmenta = new JTextArea(5, 20);
        areaEmenta.setLineWrap(true);
        areaEmenta.setWrapStyleWord(true);
        JScrollPane scrollEmenta = new JScrollPane(areaEmenta);
        campoCreditos = new JTextField();
        comboTipo = new JComboBox<>(Disciplina.TipoDisciplina.values());
        comboCursos = new JComboBox<>();
        cursos.forEach(comboCursos::addItem);

 
        botaoGerenciarPreRequisitos = new JButton("Gerenciar Pré-requisitos");
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");

        if (disciplinaParaEditar != null) {
            campoCod.setText(String.valueOf(disciplinaParaEditar.getCodDisc()));
            campoCod.setEditable(false);
            campoNome.setText(disciplinaParaEditar.getNomeDisc());
            areaEmenta.setText(disciplinaParaEditar.getEmenta());
            campoCreditos.setText(String.valueOf(disciplinaParaEditar.getNumCreditos()));
            comboTipo.setSelectedItem(disciplinaParaEditar.getTipo());
            botaoGerenciarPreRequisitos.setEnabled(true); // Habilitado para editar

            for (int i = 0; i < cursos.size(); i++) {
                if (cursos.get(i).getCodCurso() == disciplinaParaEditar.getCodCurso()) {
                    comboCursos.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            botaoGerenciarPreRequisitos.setEnabled(false); // Desabilitado para adicionar
        }

        // --- ADIÇÃO DOS COMPONENTES AO PAINEL DO FORMULÁRIO ---
        // Linha 0: Cód. Disciplina
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("Cód. Disciplina:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(campoCod, gbc);

        // Linha 1: Nome
        gbc.gridy = 1; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(campoNome, gbc);
        
        gbc.gridy = 2; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.NORTHWEST;
        painelFormulario.add(new JLabel("Ementa:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 1.0;
        painelFormulario.add(scrollEmenta, gbc);

        gbc.gridy = 3; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; gbc.weighty = 0; gbc.anchor = GridBagConstraints.WEST;
        painelFormulario.add(new JLabel("Nº de Créditos:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(campoCreditos, gbc);

        gbc.gridy = 4; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(comboTipo, gbc);

        gbc.gridy = 5; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE;
        painelFormulario.add(new JLabel("Curso:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        painelFormulario.add(comboCursos, gbc);

        // --- PAINEL DOS BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(botaoGerenciarPreRequisitos);
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);

        // --- AÇÕES DOS BOTÕES ---
        botaoGerenciarPreRequisitos.addActionListener(e -> {
            DialogoPreRequisitos dialogoPre = new DialogoPreRequisitos(this, disciplinaParaEditar, todasAsDisciplinas);
            dialogoPre.setVisible(true);
        });
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose());

        // --- MONTAGEM FINAL ---
        setLayout(new BorderLayout(10, 10));
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(450, 400));
        setLocationRelativeTo(owner);
    }
    
    // --- MÉTODO onSalvar  ---
    private void onSalvar() {
        if (campoCod.getText().trim().isEmpty() || campoNome.getText().trim().isEmpty() || campoCreditos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Código, Nome e Créditos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cod = Integer.parseInt(campoCod.getText());
            String nome = campoNome.getText();
            String ementa = areaEmenta.getText();
            int creditos = Integer.parseInt(campoCreditos.getText());
            Disciplina.TipoDisciplina tipo = (Disciplina.TipoDisciplina) comboTipo.getSelectedItem();
            Curso curso = (Curso) comboCursos.getSelectedItem();

            this.disciplinaResultante = new Disciplina(cod, nome, ementa, creditos, tipo, curso.getCodCurso());
            this.salvo = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código e Créditos devem ser números válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- MÉTODOS DE RESULTADO ---
    public Disciplina getDisciplina() {
        return disciplinaResultante;
    }

    public boolean foiSalvo() {
        return salvo;
    }
}
