/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Model.EmailProfessor;
import com.mycompany.uniufc.Model.Professor;
import com.mycompany.uniufc.Model.TelefoneProfessor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DialogoContatosProfessor extends JDialog {

    private Professor professor;
    private JList<EmailProfessor> listaEmails;
    private JList<TelefoneProfessor> listaTelefones;
    private DefaultListModel<EmailProfessor> modelEmails;
    private DefaultListModel<TelefoneProfessor> modelTelefones;

    public DialogoContatosProfessor(Dialog owner, Professor professor) {
        super(owner, true);
        this.professor = professor;
        setTitle("Contatos de: " + professor.getNomeProf());

        // Dados Mockados para este diálogo
        List<EmailProfessor> emailsMock = new ArrayList<>(List.of(
            new EmailProfessor(1, professor.getNomeProf().toLowerCase().replace(" ", ".") + "@uni.com", professor.getSiape())
        ));
        List<TelefoneProfessor> telefonesMock = new ArrayList<>(List.of(
            new TelefoneProfessor(1, "(48) 99999-1234", "Celular", professor.getSiape())
        ));

        // --- PAINEL DE EMAILS ---
        JPanel painelEmails = new JPanel(new BorderLayout(5, 5));
        painelEmails.setBorder(BorderFactory.createTitledBorder("E-mails"));
        modelEmails = new DefaultListModel<>();
        emailsMock.forEach(modelEmails::addElement);
        listaEmails = new JList<>(modelEmails);
        painelEmails.add(new JScrollPane(listaEmails), BorderLayout.CENTER);
        
        JPanel painelBotoesEmail = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addEmail = new JButton("Adicionar");
        JButton delEmail = new JButton("Remover");
        painelBotoesEmail.add(addEmail);
        painelBotoesEmail.add(delEmail);
        painelEmails.add(painelBotoesEmail, BorderLayout.SOUTH);

        // --- PAINEL DE TELEFONES ---
        JPanel painelTelefones = new JPanel(new BorderLayout(5, 5));
        painelTelefones.setBorder(BorderFactory.createTitledBorder("Telefones"));
        modelTelefones = new DefaultListModel<>();
        telefonesMock.forEach(modelTelefones::addElement);
        listaTelefones = new JList<>(modelTelefones);
        painelTelefones.add(new JScrollPane(listaTelefones), BorderLayout.CENTER);

        JPanel painelBotoesTel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addTel = new JButton("Adicionar");
        JButton delTel = new JButton("Remover");
        painelBotoesTel.add(addTel);
        painelBotoesTel.add(delTel);
        painelTelefones.add(painelBotoesTel, BorderLayout.SOUTH);

        // --- BOTÃO FECHAR ---
        JButton botaoFechar = new JButton("Fechar");
        JPanel painelFechar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelFechar.add(botaoFechar);

        // --- LAYOUT PRINCIPAL ---
        setLayout(new BorderLayout(10, 10));
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.add(painelEmails);
        painelPrincipal.add(painelTelefones);
        add(painelPrincipal, BorderLayout.CENTER);
        add(painelFechar, BorderLayout.SOUTH);

        // --- AÇÕES ---
        addEmail.addActionListener(e -> {
            String novoEmail = JOptionPane.showInputDialog(this, "Digite o novo e-mail:", "Adicionar E-mail", JOptionPane.PLAIN_MESSAGE);
            if (novoEmail != null && !novoEmail.trim().isEmpty()) {
                System.out.println("ADICIONAR EMAIL: " + novoEmail + " para o SIAPE: " + professor.getSiape());
            }
        });

      

        addTel.addActionListener(e -> {
            // 1. Cria os componentes para o nosso mini-formulário
            JTextField campoNumero = new JTextField(15);
            JTextField campoDescricao = new JTextField(15);

            // 2. Coloca os componentes em um painel
            JPanel painelForm = new JPanel(new GridLayout(2, 2, 5, 5));
            painelForm.add(new JLabel("Número:"));
            painelForm.add(campoNumero);
            painelForm.add(new JLabel("Descrição (Ex: Celular):"));
            painelForm.add(campoDescricao);

            // 3. Mostra o JOptionPane com o nosso painel customizado
            int result = JOptionPane.showConfirmDialog(this, painelForm, "Adicionar Telefone", 
                                                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // 4. Se o usuário clicou em "OK", pega os dados dos campos
            if (result == JOptionPane.OK_OPTION) {
                String novoNumero = campoNumero.getText();
                String novaDescricao = campoDescricao.getText();
                
                if (!novoNumero.trim().isEmpty() && !novaDescricao.trim().isEmpty()) {
                    System.out.println("ADICIONAR TELEFONE: " + novoNumero + " (" + novaDescricao + ") para o SIAPE: " + professor.getSiape());
                    // TODO: Lógica para adicionar no banco e atualizar a lista
                } else {
                    JOptionPane.showMessageDialog(this, "Ambos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
// ...

        delEmail.addActionListener(e -> {
            EmailProfessor emailSelecionado = listaEmails.getSelectedValue();
            if (emailSelecionado != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o e-mail: " + emailSelecionado.getEmail() + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.out.println("REMOVER EMAIL ID: " + emailSelecionado.getIdEmail());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um e-mail para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        delTel.addActionListener(e -> {
             TelefoneProfessor telSelecionado = listaTelefones.getSelectedValue();
            if (telSelecionado != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o telefone: " + telSelecionado.getNumero() + "?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.out.println("REMOVER TELEFONE ID: " + telSelecionado.getIdTelefone());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um telefone para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        botaoFechar.addActionListener(e -> dispose());
        
        setSize(500, 400);
        setLocationRelativeTo(owner);
    }
}