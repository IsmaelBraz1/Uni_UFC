/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Model.Departamento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import com.mycompany.uniufc.Model.Organizador;

public class PainelGerenciamentoDepartamentos extends JPanel {

    private JTable tabelaDepartamentos;
    private DefaultTableModel modelTabela;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;

    // Usaremos os mesmos dados mockados por enquanto
    private List<Departamento> listaDeDepartamentos;

    public PainelGerenciamentoDepartamentos() {
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DOS BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botaoAdicionar = new JButton("Adicionar");
        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);

        add(painelBotoes, BorderLayout.NORTH);

        // --- TABELA DE DEPARTAMENTOS ---
        String[] colunas = {"Cód. Departamento", "Nome do Departamento"};
        modelTabela = new DefaultTableModel(colunas, 0);
        tabelaDepartamentos = new JTable(modelTabela);

        // Preenche a tabela com os dados mockados
        preencherTabela();

        add(new JScrollPane(tabelaDepartamentos), BorderLayout.CENTER);

        
        // --- AÇÕES DOS BOTÕES ---
        botaoAdicionar.addActionListener(e -> {
            // Pega a janela principal como "pai" do diálogo
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            DialogoDepartamento dialogo = new DialogoDepartamento(owner, null); // null = modo Adicionar
            dialogo.setVisible(true);

            // O código abaixo só executa DEPOIS que o diálogo for fechado
            if (dialogo.foiSalvo()) {
                Departamento novoDepto = dialogo.getDepartamento();
                // Por enquanto, apenas imprimimos no console. Futuramente, vamos adicionar na tabela e no BD.
                System.out.println("Departamento a ser ADICIONADO: " + novoDepto.getCodDepart() + " - " + novoDepto.getNomeDepart());
                // TODO: Adicionar o novoDepto na 'listaDeDepartamentos' e chamar preencherTabela()
            }
        });

        botaoEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaDepartamentos.getSelectedRow();
            if (linhaSelecionada != -1) {
                // Pega os dados da linha selecionada na tabela
                int codDepto = (int) tabelaDepartamentos.getValueAt(linhaSelecionada, 0);
                String nomeDepto = (String) tabelaDepartamentos.getValueAt(linhaSelecionada, 1);
                Departamento deptoParaEditar = new Departamento(codDepto, nomeDepto);

                Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
                DialogoDepartamento dialogo = new DialogoDepartamento(owner, deptoParaEditar); // Passa o objeto = modo Editar
                dialogo.setVisible(true);

                // O código abaixo só executa DEPOIS que o diálogo for fechado
                if (dialogo.foiSalvo()) {
                    Departamento deptoEditado = dialogo.getDepartamento();
                    // Por enquanto, apenas imprimimos no console. Futuramente, vamos atualizar na tabela e no BD.
                    System.out.println("Departamento a ser EDITADO: " + deptoEditado.getCodDepart() + " - " + deptoEditado.getNomeDepart());
                     // TODO: Atualizar o deptoEditado na 'listaDeDepartamentos' e chamar preencherTabela()
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um departamento para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });


        botaoExcluir.addActionListener(e -> {
            // Futuramente, pedirá confirmação e excluirá o registro
            int linhaSelecionada = tabelaDepartamentos.getSelectedRow();
            if (linhaSelecionada != -1) {
                System.out.println("Botão EXCLUIR Departamento clicado para a linha: " + linhaSelecionada);
                // Exemplo de como obter o valor da célula
                Object codDepto = tabelaDepartamentos.getValueAt(linhaSelecionada, 0);
                JOptionPane.showMessageDialog(this, "Excluir departamento com código: " + codDepto);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um departamento para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void preencherTabela() {
        // Limpa a tabela antes de preencher
        modelTabela.setRowCount(0);
        
        listaDeDepartamentos = Organizador.listaDepart();
        
        for (Departamento depto : listaDeDepartamentos) {
            modelTabela.addRow(new Object[]{depto.getCodDepart(), depto.getNomeDepart()});
        }
    }
}
