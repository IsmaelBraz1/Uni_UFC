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
import com.mycompany.uniufc.Model.Professor;
import com.mycompany.uniufc.Model.Turma;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DialogoTurma extends JDialog {

    private JTextField campoAno, campoSemestre;
    private JComboBox<Disciplina> comboDisciplinas;
    private JComboBox<Professor> comboProf1, comboProf2;
    private JButton botaoSalvar, botaoCancelar;

    private Turma turmaResultante;
    private boolean salvo = false;

    public DialogoTurma(Frame owner, Turma turmaParaEditar, List<Disciplina> disciplinas, List<Professor> professores) {
        super(owner, true);
        setTitle(turmaParaEditar == null ? "Adicionar Turma" : "Editar Turma");

        // Componentes
        campoAno = new JTextField(String.valueOf(LocalDate.now().getYear()));
        campoSemestre = new JTextField(String.valueOf(LocalDate.now().getMonthValue() < 7 ? 1 : 2));
        comboDisciplinas = new JComboBox<>();
        disciplinas.forEach(comboDisciplinas::addItem);
        comboProf1 = new JComboBox<>();
        professores.forEach(comboProf1::addItem);
        comboProf2 = new JComboBox<>();

        // Lógica do Professor Opcional
        Professor professorNenhum = new Professor(0, "-- Nenhum --", null, null, 0);
        List<Professor> professoresOpcionais = new ArrayList<>();
        professoresOpcionais.add(professorNenhum);
        professoresOpcionais.addAll(professores);
        professoresOpcionais.forEach(comboProf2::addItem);

        // Layout
        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelFormulario.add(new JLabel("Ano:"));
        painelFormulario.add(campoAno);
        painelFormulario.add(new JLabel("Semestre:"));
        painelFormulario.add(campoSemestre);
        painelFormulario.add(new JLabel("Disciplina:"));
        painelFormulario.add(comboDisciplinas);
        painelFormulario.add(new JLabel("Professor Principal:"));
        painelFormulario.add(comboProf1);
        painelFormulario.add(new JLabel("Professor Auxiliar:"));
        painelFormulario.add(comboProf2);

        // Lógica de Edição...
        if (turmaParaEditar != null) {
            campoAno.setText(String.valueOf(turmaParaEditar.getAno()));
            campoSemestre.setText(String.valueOf(turmaParaEditar.getSemestre()));

            // Seleciona a disciplina correta
            for(Disciplina d : disciplinas) {
                if(d.getCodDisc() == turmaParaEditar.getCodDisc()) {
                    comboDisciplinas.setSelectedItem(d);
                    break;
                }
            }

            // Seleciona o professor 1
            for(Professor p : professores) {
                if(p.getSiape() == turmaParaEditar.getSiapeProf1()){
                    comboProf1.setSelectedItem(p);
                    break;
                }
            }

            // Seleciona o professor 2 (ou "Nenhum")
            if (turmaParaEditar.getSiapeProf2() != null) {
                for(int i = 1; i < comboProf2.getItemCount(); i++){ // Começa em 1 para pular o "Nenhum"
                    Professor p = comboProf2.getItemAt(i);
                    if(p.getSiape() == turmaParaEditar.getSiapeProf2()){
                        comboProf2.setSelectedItem(p);
                        break;
                    }
                }
            } else {
                comboProf2.setSelectedIndex(0); // Seleciona "-- Nenhum --"
            }
        }

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);

        // Ações
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose());
        
        // Montagem
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void onSalvar() {
        try {
            int ano = Integer.parseInt(campoAno.getText());
            int semestre = Integer.parseInt(campoSemestre.getText());
            Disciplina disc = (Disciplina) comboDisciplinas.getSelectedItem();
            Professor prof1 = (Professor) comboProf1.getSelectedItem();
            Professor prof2 = (Professor) comboProf2.getSelectedItem();

            Integer siapeProf2 = (prof2.getSiape() == 0) ? null : prof2.getSiape();
            
            // ID da turma é 0, pois o banco de dados irá gerar o valor real com AUTO_INCREMENT
            this.turmaResultante = new Turma(0, ano, semestre, disc.getCodDisc(), prof1.getSiape(), siapeProf2);
            this.salvo = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e Semestre devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Turma getTurma() { return turmaResultante; }
    public boolean foiSalvo() { return salvo; }
}