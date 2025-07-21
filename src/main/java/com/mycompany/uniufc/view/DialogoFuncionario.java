/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Funcionario;
import javax.swing.*;
import java.awt.*;

public class DialogoFuncionario extends JDialog {

    private JTextField campoId, campoNome, campoEndereco;
    private JComboBox<Funcionario.TipoFuncionario> comboTipo;
    private JButton botaoSalvar, botaoCancelar;

    private Funcionario funcionarioResultante;
    private boolean salvo = false;

    public DialogoFuncionario(Frame owner, Funcionario funcionarioParaEditar) {
        super(owner, true);
        setTitle(funcionarioParaEditar == null ? "Adicionar Funcionário" : "Editar Funcionário");

        // Componentes
        campoId = new JTextField(10);
        campoNome = new JTextField(20);
        campoEndereco = new JTextField(30);
        comboTipo = new JComboBox<>(Funcionario.TipoFuncionario.values());

        // Layout
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelFormulario.add(new JLabel("ID Funcionário:"));
        painelFormulario.add(campoId);
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("Endereço:"));
        painelFormulario.add(campoEndereco);
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(comboTipo);

        // Lógica de Edição
        if (funcionarioParaEditar != null) {
            campoId.setText(String.valueOf(funcionarioParaEditar.getIdFuncionario()));
            campoId.setEditable(false);
            campoNome.setText(funcionarioParaEditar.getNomeFuncionario());
            campoEndereco.setText(funcionarioParaEditar.getEnderecoFuncionario());
            comboTipo.setSelectedItem(funcionarioParaEditar.getTipoFuncionario());
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
        if (campoId.getText().trim().isEmpty() || campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID e Nome são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
            String endereco = campoEndereco.getText();
            Funcionario.TipoFuncionario tipo = (Funcionario.TipoFuncionario) comboTipo.getSelectedItem();

            this.funcionarioResultante = new Funcionario(id, nome, endereco, tipo);
            this.salvo = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Funcionario getFuncionario() { return funcionarioResultante; }
    public boolean foiSalvo() { return salvo; }
}
