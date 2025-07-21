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
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DialogoPreRequisitos extends JDialog {

    private DefaultListModel<Disciplina> modelDisponiveis;
    private DefaultListModel<Disciplina> modelAtuais;
    private JList<Disciplina> listaDisponiveis;
    private JList<Disciplina> listaAtuais;

    public DialogoPreRequisitos(Dialog owner, Disciplina disciplinaAlvo, List<Disciplina> todasAsDisciplinas) {
        super(owner, true);
        setTitle("Gerenciar Pré-requisitos para: " + disciplinaAlvo.getNomeDisc());

        // --- Modelos das Listas ---
        modelDisponiveis = new DefaultListModel<>();
        modelAtuais = new DefaultListModel<>();

        // --- Mock de pré-requisitos existentes (futuramente virá do banco) ---
        // Exemplo: a disciplina "Banco de Dados" tem "Algoritmos I" como pré-requisito.
        List<Disciplina> preRequisitosAtuais;
        if (disciplinaAlvo.getCodDisc() == 102) { // Simula que estamos editando "Banco de Dados"
            preRequisitosAtuais = todasAsDisciplinas.stream()
                .filter(d -> d.getCodDisc() == 101) // Filtra por "Algoritmos I"
                .collect(Collectors.toList());
        } else {
            preRequisitosAtuais = List.of(); // Lista vazia para os outros
        }

        // Popula a lista da direita (Pré-requisitos Atuais)
        preRequisitosAtuais.forEach(modelAtuais::addElement);

        // Popula a lista da esquerda (Disciplinas Disponíveis)
        // Uma disciplina não pode ser pré-requisito dela mesma.
        // E não pode estar disponível se já for um pré-requisito.
        List<Integer> codigosAtuais = preRequisitosAtuais.stream().map(Disciplina::getCodDisc).collect(Collectors.toList());
        todasAsDisciplinas.stream()
            .filter(d -> d.getCodDisc() != disciplinaAlvo.getCodDisc() && !codigosAtuais.contains(d.getCodDisc()))
            .forEach(modelDisponiveis::addElement);

        // --- Componentes da UI ---
        listaDisponiveis = new JList<>(modelDisponiveis);
        listaAtuais = new JList<>(modelAtuais);

        JButton btnAdicionar = new JButton(">>");
        JButton btnRemover = new JButton("<<");
        JPanel painelBotoesMeio = new JPanel(new GridLayout(2, 1, 5, 5));
        painelBotoesMeio.add(btnAdicionar);
        painelBotoesMeio.add(btnRemover);

        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.setBorder(BorderFactory.createTitledBorder("Disciplinas Disponíveis"));
        painelEsquerdo.add(new JScrollPane(listaDisponiveis));
        
        JPanel painelDireito = new JPanel(new BorderLayout());
        painelDireito.setBorder(BorderFactory.createTitledBorder("Pré-requisitos Atuais"));
        painelDireito.add(new JScrollPane(listaAtuais));

        JButton btnSalvar = new JButton("Salvar");
        JPanel painelSalvar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelSalvar.add(btnSalvar);

        // --- Layout Principal ---
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.45; gbc.weighty = 1.0;
        
        gbc.gridx = 0; gbc.gridy = 0;
        add(painelEsquerdo, gbc);
        
        gbc.gridx = 2;
        add(painelDireito, gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0.1;
        add(painelBotoesMeio, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        add(painelSalvar, gbc);

        // --- Ações dos Botões ---
        btnAdicionar.addActionListener(e -> {
            List<Disciplina> selecionadas = listaDisponiveis.getSelectedValuesList();
            for (Disciplina d : selecionadas) {
                modelAtuais.addElement(d);
                modelDisponiveis.removeElement(d);
            }
        });

        btnRemover.addActionListener(e -> {
            List<Disciplina> selecionadas = listaAtuais.getSelectedValuesList();
            for (Disciplina d : selecionadas) {
                modelDisponiveis.addElement(d);
                modelAtuais.removeElement(d);
            }
        });

        btnSalvar.addActionListener(e -> {
            // Futuramente, aqui salvaremos a lista de pré-requisitos no banco
            System.out.println("Salvando " + modelAtuais.getSize() + " pré-requisitos para a disciplina " + disciplinaAlvo.getNomeDisc());
            dispose();
        });

        setSize(700, 400);
        setLocationRelativeTo(owner);
    }
}
