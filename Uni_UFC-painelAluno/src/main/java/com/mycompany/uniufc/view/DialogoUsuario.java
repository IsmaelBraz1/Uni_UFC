package com.mycompany.uniufc.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class DialogoUsuario extends JDialog {

    private JTextField campoId, campoNome, campoLogin;
    private JPasswordField campoSenha, campoConfirmaSenha;
    private JComboBox<Usuario.NivelAcesso> comboNivelAcesso;
    private JButton botaoSalvar, botaoCancelar;

    private Usuario usuarioResultante;
    private boolean salvo = false;

    // --- CONSTRUTOR ---
    public DialogoUsuario(Frame owner, Usuario usuarioParaEditar) {
        super(owner, true);
        setTitle(usuarioParaEditar == null ? "Adicionar Usuário" : "Editar Usuário");

        // --- COMPONENTES ---
        campoId = new JTextField(5);
        campoId.setEditable(false);
        campoNome = new JTextField(20);
        campoLogin = new JTextField(15);
        campoSenha = new JPasswordField();
        campoConfirmaSenha = new JPasswordField();
        comboNivelAcesso = new JComboBox<>(Usuario.NivelAcesso.values());

        // --- LAYOUT ---
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Adiciona os componentes ao painel...
        gbc.gridx = 0; gbc.gridy = 0; painelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(campoId, gbc);
        gbc.gridy++; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(campoNome, gbc);
        gbc.gridy++; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Login:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(campoLogin, gbc);
        gbc.gridy++; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Nível de Acesso:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(comboNivelAcesso, gbc);
        gbc.gridy++; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(campoSenha, gbc);
        gbc.gridy++; gbc.gridx = 0; gbc.fill = GridBagConstraints.NONE; painelFormulario.add(new JLabel("Confirmar Senha:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; painelFormulario.add(campoConfirmaSenha, gbc);

        // --- LÓGICA DE EDIÇÃO ---
        if (usuarioParaEditar != null) {
            campoId.setText(String.valueOf(usuarioParaEditar.getIdUsuario()));
            campoNome.setText(usuarioParaEditar.getNome());
            campoLogin.setText(usuarioParaEditar.getLogin());
            campoLogin.setEditable(false);
            comboNivelAcesso.setSelectedItem(usuarioParaEditar.getNivelAcesso());
            campoSenha.setText(usuarioParaEditar.getSenha());
            campoConfirmaSenha.setText(usuarioParaEditar.getSenha());
        }

        // --- BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);

        // --- AÇÕES ---
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose());
        
        // --- MONTAGEM FINAL---
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    } // FIM DO CONSTRUTOR

    // --- MÉTODOS DA CLASSE (FORA DO CONSTRUTOR) ---

    private void onSalvar() {
        String senha = new String(campoSenha.getPassword());
        String confirmaSenha = new String(campoConfirmaSenha.getPassword());

        if (campoNome.getText().trim().isEmpty() || campoLogin.getText().trim().isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome, Login e Senha são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int id = getTitle().contains("Editar") ? Integer.parseInt(campoId.getText()) : 0;
        
        this.usuarioResultante = new Usuario(
                id,
                campoNome.getText(),
                campoLogin.getText(),
                senha,
                (Usuario.NivelAcesso) comboNivelAcesso.getSelectedItem()
        );
        this.salvo = true;
        dispose();
    }

    public Usuario getUsuario() {
        return usuarioResultante;
    }

    public boolean foiSalvo() {
        return salvo;
    }
}