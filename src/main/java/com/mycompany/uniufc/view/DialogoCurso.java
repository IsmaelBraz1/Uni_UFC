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
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogoCurso extends JDialog {

    private JTextField campoCod, campoNome, campoCreditos;
    private JComboBox<Departamento> comboDepartamentos; // O novo componente!
    private JButton botaoSalvar, botaoCancelar;

    private Curso cursoResultante;
    private boolean salvo = false;

    public DialogoCurso(Frame owner, Curso cursoParaEditar, List<Departamento> departamentos) {
        super(owner, true);
        setTitle(cursoParaEditar == null ? "Adicionar Curso" : "Editar Curso");

        // --- PAINEL DO FORMULÁRIO ---
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("Cód. Curso:"));
        campoCod = new JTextField();
        painelFormulario.add(campoCod);

        painelFormulario.add(new JLabel("Nome do Curso:"));
        campoNome = new JTextField();
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("Créditos Mínimos:"));
        campoCreditos = new JTextField();
        painelFormulario.add(campoCreditos);
        
        painelFormulario.add(new JLabel("Departamento:"));
        comboDepartamentos = new JComboBox<>();
        // Popula o JComboBox com a lista de departamentos
        departamentos.forEach(comboDepartamentos::addItem);
        painelFormulario.add(comboDepartamentos);

        // --- LÓGICA DE EDITAR ---
        if (cursoParaEditar != null) {
            campoCod.setText(String.valueOf(cursoParaEditar.getCodCurso()));
            campoNome.setText(cursoParaEditar.getNomeCurso());
            campoCreditos.setText(String.valueOf(cursoParaEditar.getCreditosMinimos()));
            campoCod.setEditable(false);

            // Seleciona o departamento correto no JComboBox
            for (Departamento d : departamentos) {
                if (d.getCodDepart() == cursoParaEditar.getCodDepart()) {
                    comboDepartamentos.setSelectedItem(d);
                    break;
                }
            }
        }

        // --- PAINEL DOS BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);

        // --- AÇÕES ---
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose());
        
        // --- MONTAGEM FINAL ---
        setLayout(new BorderLayout(10, 10));
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void onSalvar() {
        // Validação
        if (campoCod.getText().trim().isEmpty() || campoNome.getText().trim().isEmpty() || campoCreditos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int codCurso = Integer.parseInt(campoCod.getText());
            String nome = campoNome.getText();
            int creditos = Integer.parseInt(campoCreditos.getText());
            
            // Pega o objeto Departamento selecionado no JComboBox
            Departamento deptoSelecionado = (Departamento) comboDepartamentos.getSelectedItem();
            if (deptoSelecionado == null) {
                 JOptionPane.showMessageDialog(this, "Selecione um departamento.", "Erro", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            this.cursoResultante = new Curso(codCurso, nome, creditos, deptoSelecionado.getCodDepart());
            this.salvo = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código e Créditos devem ser números.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Curso getCurso() { return cursoResultante; }
    public boolean foiSalvo() { return salvo; }
}
