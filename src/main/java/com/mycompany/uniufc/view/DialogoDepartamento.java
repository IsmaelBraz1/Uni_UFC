package com.mycompany.uniufc.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Model.Departamento;
import javax.swing.*;
import java.awt.*;

public class DialogoDepartamento extends JDialog {

    private JTextField campoCod, campoNome;
    private JButton botaoSalvar, botaoCancelar;

    private Departamento departamentoResultante;
    private boolean salvo = false;

    // Construtor que serve tanto para ADICIONAR (deptoParaEditar = null)
    // quanto para EDITAR (deptoParaEditar = um objeto Departamento)
    public DialogoDepartamento(Frame owner, Departamento deptoParaEditar) {
        // 'super(owner, true)' cria um diálogo MODAL, que bloqueia a janela principal
        super(owner, true);

        setTitle(deptoParaEditar == null ? "Adicionar Departamento" : "Editar Departamento");
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL DO FORMULÁRIO ---
        JPanel painelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("Código:"));
        campoCod = new JTextField();
        painelFormulario.add(campoCod);

        painelFormulario.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelFormulario.add(campoNome);

        add(painelFormulario, BorderLayout.CENTER);

        // --- PAINEL DOS BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);

        add(painelBotoes, BorderLayout.SOUTH);

        // --- LÓGICA DE EDITAR ---
        if (deptoParaEditar != null) {
            campoCod.setText(String.valueOf(deptoParaEditar.getCodDepart()));
            campoNome.setText(deptoParaEditar.getNomeDepart());
            // Não permite editar a chave primária
            campoCod.setEditable(false);
        }

        // --- AÇÕES DOS BOTÕES ---
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose()); // 'dispose' fecha a janela

        pack(); // Ajusta o tamanho da janela ao conteúdo
        setLocationRelativeTo(owner); // Centraliza em relação à janela principal
    }

    private void onSalvar() {
        // Validação simples
        if (campoCod.getText().trim().isEmpty() || campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ambos os campos devem ser preenchidos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cod = Integer.parseInt(campoCod.getText());
            String nome = campoNome.getText();

            // Cria o objeto Departamento com os dados do formulário
            this.departamentoResultante = new Departamento(cod, nome);
            this.salvo = true;
            dispose(); // Fecha a janela

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O código do departamento deve ser um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Métodos públicos para que o painel principal possa obter o resultado
    public Departamento getDepartamento() {
        return departamentoResultante;
    }

    public boolean foiSalvo() {
        return salvo;
    }
}
