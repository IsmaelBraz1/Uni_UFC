/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Model.Aluno;
import com.mycompany.uniufc.Model.TelefoneAluno;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DialogoContatosAluno extends JDialog {

    private Aluno aluno;
    private JList<TelefoneAluno> listaTelefones;
    private DefaultListModel<TelefoneAluno> modelTelefones;

    public DialogoContatosAluno(Dialog owner, Aluno aluno) {
        super(owner, true);
        this.aluno = aluno;
        setTitle("Telefones de: " + aluno.getNomeAlu());

        // Dados Mockados
        List<TelefoneAluno> telefonesMock = new ArrayList<>(List.of(
            new TelefoneAluno(1, "(48) 98888-5678", "Pessoal", aluno.getMatricula())
        ));

        // Painel de Telefones
        JPanel painelTelefones = new JPanel(new BorderLayout(5, 5));
        painelTelefones.setBorder(BorderFactory.createTitledBorder("Telefones"));
        modelTelefones = new DefaultListModel<>();
        telefonesMock.forEach(modelTelefones::addElement);
        listaTelefones = new JList<>(modelTelefones);
        painelTelefones.add(new JScrollPane(listaTelefones), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addTel = new JButton("Adicionar");
        JButton delTel = new JButton("Remover");
        painelBotoes.add(addTel);
        painelBotoes.add(delTel);
        painelTelefones.add(painelBotoes, BorderLayout.SOUTH);

        // Ações
        addTel.addActionListener(e -> {
            JTextField campoNumero = new JTextField(15);
            JTextField campoDescricao = new JTextField(15);
            JPanel painelForm = new JPanel(new GridLayout(2, 2, 5, 5));
            painelForm.add(new JLabel("Número:"));
            painelForm.add(campoNumero);
            painelForm.add(new JLabel("Descrição:"));
            painelForm.add(campoDescricao);

            int result = JOptionPane.showConfirmDialog(this, painelForm, "Adicionar Telefone", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String novoNumero = campoNumero.getText();
                String novaDescricao = campoDescricao.getText();
                if (!novoNumero.trim().isEmpty() && !novaDescricao.trim().isEmpty()) {
                    System.out.println("ADICIONAR TELEFONE DE ALUNO: " + novoNumero + " (" + novaDescricao + ") para a Matrícula: " + aluno.getMatricula());
                }
            }
        });
        
        // Ação de remover (placeholder)...
        delTel.addActionListener(e -> JOptionPane.showMessageDialog(this, "Ação de remover a ser implementada."));

        // Botão Fechar
        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(e -> dispose());
        JPanel painelFechar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelFechar.add(botaoFechar);

        // Montagem
        setLayout(new BorderLayout(10, 10));
        add(painelTelefones, BorderLayout.CENTER);
        add(painelFechar, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }
}