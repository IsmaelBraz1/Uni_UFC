/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.view.DialogoContatosProfessor;
import com.mycompany.uniufc.Model.Departamento;
import com.mycompany.uniufc.Model.Professor;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.IntStream;

public class DialogoProfessor extends JDialog {

    private JTextField campoSiape, campoNome;
    private JComboBox<Departamento> comboDepartamentos;
    private JComboBox<Integer> comboDiaNasc, comboAnoNasc;
    private JComboBox<String> comboMesNasc;
    private JComboBox<Integer> comboDiaIng, comboAnoIng;
    private JComboBox<String> comboMesIng;

    private JButton botaoSalvar, botaoCancelar;
    private JButton botaoGerenciarContatos;
    private Professor professorResultante;
    private boolean salvo = false;

    public DialogoProfessor (Frame owner, Professor profParaEditar, List<Departamento> departamentos) {
        super(owner, true);
        setTitle(profParaEditar == null ? "Adicionar Professor" : "Editar Professor");

        // --- COMPONENTES ---
        campoSiape = new JTextField(10);
        campoNome = new JTextField(30);
        comboDepartamentos = new JComboBox<>();
        departamentos.forEach(comboDepartamentos::addItem);

        // Date Pickers
        comboDiaNasc = new JComboBox<>();
        comboMesNasc = new JComboBox<>();
        comboAnoNasc = new JComboBox<>();
        popularCombosDeData(comboDiaNasc, comboMesNasc, comboAnoNasc);
        
        comboDiaIng = new JComboBox<>();
        comboMesIng = new JComboBox<>();
        comboAnoIng = new JComboBox<>();
        popularCombosDeData(comboDiaIng, comboMesIng, comboAnoIng);

        // --- LAYOUT ---
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 0: SIAPE
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("SIAPE:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoSiape, gbc);

        // Linha 1: Nome
        gbc.gridx = 0; gbc.gridy = 1;
        painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoNome, gbc);

        // Linha 2: Data de Nascimento
        gbc.gridx = 0; gbc.gridy = 2;
        painelFormulario.add(new JLabel("Data de Nascimento:"), gbc);
        JPanel painelDataNasc = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelDataNasc.add(comboDiaNasc); painelDataNasc.add(comboMesNasc); painelDataNasc.add(comboAnoNasc);
        gbc.gridx = 1;
        painelFormulario.add(painelDataNasc, gbc);

        // Linha 3: Data de Ingresso
        gbc.gridx = 0; gbc.gridy = 3;
        painelFormulario.add(new JLabel("Data de Ingresso:"), gbc);
        JPanel painelDataIng = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelDataIng.add(comboDiaIng); painelDataIng.add(comboMesIng); painelDataIng.add(comboAnoIng);
        gbc.gridx = 1;
        painelFormulario.add(painelDataIng, gbc);

        // Linha 4: Departamento
        gbc.gridx = 0; gbc.gridy = 4;
        painelFormulario.add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(comboDepartamentos, gbc);

        // --- LÓGICA DE EDITAR ---
        if (profParaEditar != null) {
            campoSiape.setText(String.valueOf(profParaEditar.getSiape()));
            campoSiape.setEditable(false);
            campoNome.setText(profParaEditar.getNomeProf());
            
            LocalDate dataNasc = profParaEditar.getDataNasProf();
            comboDiaNasc.setSelectedItem(dataNasc.getDayOfMonth());
            comboMesNasc.setSelectedIndex(dataNasc.getMonthValue() - 1);
            comboAnoNasc.setSelectedItem(dataNasc.getYear());

            LocalDate dataIng = profParaEditar.getDataIngresso();
            comboDiaIng.setSelectedItem(dataIng.getDayOfMonth());
            comboMesIng.setSelectedIndex(dataIng.getMonthValue() - 1);
            comboAnoIng.setSelectedItem(dataIng.getYear());

            for (int i = 0; i < departamentos.size(); i++) {
                if (departamentos.get(i).getCodDepart() == profParaEditar.getCodDepart()) {
                    comboDepartamentos.setSelectedIndex(i);
                    break;
                }
            }
        }
        
        // --- BOTÕES ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoGerenciarContatos = new JButton("Gerenciar Contatos");
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoGerenciarContatos);
        
        
        if (profParaEditar != null) {
            // ... (código existente que preenche os campos)
            botaoGerenciarContatos.setEnabled(true); // Habilitado para editar
        } else {
            botaoGerenciarContatos.setEnabled(false); // Desabilitado para adicionar
        }

        // 4. ADICIONE O ACTION LISTENER PARA O NOVO BOTÃO
        botaoGerenciarContatos.addActionListener(e -> {
            // Cria e exibe o diálogo de contatos, passando este diálogo como "pai"
            // e o professor que está sendo editado.
            DialogoContatosProfessor dialogoContatos = new DialogoContatosProfessor(this, profParaEditar);
            dialogoContatos.setVisible(true);
        });

        
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose());

        // --- MONTAGEM FINAL ---
        setLayout(new BorderLayout(10, 10));
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }
    
    private void popularCombosDeData(JComboBox<Integer> comboDia, JComboBox<String> comboMes, JComboBox<Integer> comboAno) {
        // Dias
        IntStream.rangeClosed(1, 31).forEach(comboDia::addItem);
        // Meses
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        for(String mes : meses) { comboMes.addItem(mes); }
        // Anos (de 75 anos atrás até o ano atual)
        int anoAtual = LocalDate.now().getYear();
        IntStream.rangeClosed(anoAtual - 75, anoAtual).forEach(comboAno::addItem);
    }

    private void onSalvar() {
        try {
            int siape = Integer.parseInt(campoSiape.getText());
            String nome = campoNome.getText();
            Departamento depto = (Departamento) comboDepartamentos.getSelectedItem();
            
            int diaNasc = (int) comboDiaNasc.getSelectedItem();
            int mesNasc = comboMesNasc.getSelectedIndex() + 1;
            int anoNasc = (int) comboAnoNasc.getSelectedItem();
            LocalDate dataNasc = LocalDate.of(anoNasc, mesNasc, diaNasc);

            int diaIng = (int) comboDiaIng.getSelectedItem();
            int mesIng = comboMesIng.getSelectedIndex() + 1;
            int anoIng = (int) comboAnoIng.getSelectedItem();
            LocalDate dataIng = LocalDate.of(anoIng, mesIng, diaIng);

            this.professorResultante = new Professor(siape, nome, dataNasc, dataIng, depto.getCodDepart());
            this.salvo = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "SIAPE deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao criar professor. Verifique os dados (especialmente as datas).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Professor getProfessor() { return professorResultante; }
    public boolean foiSalvo() { return salvo; }
}