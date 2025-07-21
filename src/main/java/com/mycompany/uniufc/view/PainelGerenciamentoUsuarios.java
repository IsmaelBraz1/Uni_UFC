/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */



import com.mycompany.uniufc.Model.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PainelGerenciamentoUsuarios extends JPanel {

    private JTable tabelaUsuarios;
    private DefaultTableModel modelTabela;

    private List<Usuario> listaDeUsuarios = Arrays.asList(
        new Usuario(1, "Administrador", "admin", "123", Usuario.NivelAcesso.DBA),
        new Usuario(2, "Fulano de Tal", "fulano", "456", Usuario.NivelAcesso.FUNCIONARIO)
    );

    public PainelGerenciamentoUsuarios() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "Login", "Nível de Acesso", "Senha"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaUsuarios = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaUsuarios), BorderLayout.CENTER);

        btnAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoUsuario dialogo = new DialogoUsuario(owner, null);
            dialogo.setVisible(true);
            if(dialogo.foiSalvo()) {
                System.out.println("Salvar novo usuário: " + dialogo.getUsuario().getNome());
            }
        });

        btnEditar.addActionListener(e -> {
            int linha = tabelaUsuarios.getSelectedRow();
            if (linha != -1) {
                int id = (int) modelTabela.getValueAt(linha, 0);
                Usuario usuario = listaDeUsuarios.stream().filter(u -> u.getIdUsuario() == id).findFirst().orElse(null);
                
                if(usuario != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                    DialogoUsuario dialogo = new DialogoUsuario(owner, usuario);
                    dialogo.setVisible(true);
                    if(dialogo.foiSalvo()){
                        System.out.println("Editar usuário: " + dialogo.getUsuario().getNome());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        modelTabela.setRowCount(0);
        for (Usuario u : listaDeUsuarios) {
            modelTabela.addRow(new Object[]{u.getIdUsuario(), u.getNome(), u.getLogin(), u.getNivelAcesso(), u.getSenha()});
        }
    }
}
