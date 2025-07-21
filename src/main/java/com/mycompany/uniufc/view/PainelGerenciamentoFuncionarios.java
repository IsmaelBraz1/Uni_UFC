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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PainelGerenciamentoFuncionarios extends JPanel {

    private JTable tabelaFuncionarios;
    private DefaultTableModel modelTabela;
    private List<Funcionario> listaDeFuncionarios = Arrays.asList(
            new Funcionario(501, "Carlos Souza", "Portaria Bloco A", Funcionario.TipoFuncionario.PORTARIA),
            new Funcionario(101, "Ana Costa", "Secretaria CC", Funcionario.TipoFuncionario.COORDENACAO)
    );

    public PainelGerenciamentoFuncionarios() {
        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "Endereço", "Tipo"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaFuncionarios = new JTable(modelTabela);
        preencherTabela();
        add(new JScrollPane(tabelaFuncionarios), BorderLayout.CENTER);

        // Ações
        btnAdicionar.addActionListener(e -> {
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoFuncionario dialogo = new DialogoFuncionario(owner, null);
            dialogo.setVisible(true);
            if (dialogo.foiSalvo()) {
                System.out.println("Salvar novo funcionário: " + dialogo.getFuncionario().getNomeFuncionario());
            }
        });

        btnEditar.addActionListener(e -> {
            int linha = tabelaFuncionarios.getSelectedRow();
            if (linha != -1) {
                int id = (int) modelTabela.getValueAt(linha, 0);
                Funcionario func = listaDeFuncionarios.stream().filter(f -> f.getIdFuncionario() == id).findFirst().orElse(null);
                if (func != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                    DialogoFuncionario dialogo = new DialogoFuncionario(owner, func);
                    dialogo.setVisible(true);
                    if (dialogo.foiSalvo()) {
                        System.out.println("Editar funcionário: " + dialogo.getFuncionario().getNomeFuncionario());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um funcionário para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        btnExcluir.addActionListener(e -> {
            // Lógica de exclusão com confirmação...
        });
    }
    
    private void preencherTabela() {
        modelTabela.setRowCount(0);
        for (Funcionario f : listaDeFuncionarios) {
            modelTabela.addRow(new Object[]{f.getIdFuncionario(), f.getNomeFuncionario(), f.getEnderecoFuncionario(), f.getTipoFuncionario()});
        }
    }
}
