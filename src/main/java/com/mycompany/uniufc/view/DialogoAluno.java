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
import com.mycompany.uniufc.Model.Curso;
import com.mycompany.uniufc.Model.Matricula;
import com.mycompany.uniufc.Model.Turma;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DialogoAluno extends JDialog {

    private JTextField campoMatricula, campoNome, campoEndereco;
    private JComboBox<Aluno.TipoAluno> comboTipo;
    private JComboBox<Curso> comboCursos;
    private JButton botaoSalvar, botaoCancelar;
    private JButton botaoGerenciarContatos;
    private JButton botaoGerenciarMatriculas;
    private Aluno alunoResultante;
    private boolean salvo = false;

    public DialogoAluno(Frame owner, Aluno alunoParaEditar, List<Curso> cursos, List<Turma> todasAsTurmas, List<Matricula> todasMatriculas) {
        super(owner, true);
        setTitle(alunoParaEditar == null ? "Adicionar Aluno" : "Editar Aluno");

        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos de texto
        painelFormulario.add(new JLabel("Matrícula:"));
        campoMatricula = new JTextField();
        painelFormulario.add(campoMatricula);

        painelFormulario.add(new JLabel("Nome Completo:"));
        campoNome = new JTextField();
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("Endereço:"));
        campoEndereco = new JTextField();
        painelFormulario.add(campoEndereco);

        // ComboBox para Tipo de Aluno
        painelFormulario.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(Aluno.TipoAluno.values());
        painelFormulario.add(comboTipo);

        // ComboBox para Cursos
        painelFormulario.add(new JLabel("Curso:"));
        comboCursos = new JComboBox<>();
        cursos.forEach(comboCursos::addItem);
        painelFormulario.add(comboCursos);

        // Lógica de Edição
        if (alunoParaEditar != null) {
            campoMatricula.setText(String.valueOf(alunoParaEditar.getMatricula()));
            campoNome.setText(alunoParaEditar.getNomeAlu());
            campoEndereco.setText(alunoParaEditar.getEndereco());
            comboTipo.setSelectedItem(alunoParaEditar.getTipoAlu());
            campoMatricula.setEditable(false);

            for (int i = 0; i < cursos.size(); i++) {
                if (cursos.get(i).getCodCurso() == alunoParaEditar.getCodCurso()) {
                    comboCursos.setSelectedIndex(i);
                    break;
                }
            }
        }

        // Painel de Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        botaoSalvar = new JButton("Salvar");
        botaoCancelar = new JButton("Cancelar");
        botaoGerenciarContatos = new JButton("Gerenciar Contatos");
        botaoGerenciarMatriculas = new JButton("Gerenciar Matrículas");
         
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoGerenciarContatos);
         painelBotoes.add(botaoGerenciarMatriculas);
// 3. Habilite/Desabilite na lógica de edição
        if (alunoParaEditar != null) {
            // ... (código que preenche os campos para edição)
            botaoGerenciarContatos.setEnabled(true);
           
            botaoGerenciarMatriculas.setEnabled(true);
        } else {
            botaoGerenciarContatos.setEnabled(false);
        }

        // 4. Adicione a ação para o novo botão
        botaoGerenciarContatos.addActionListener(e -> {
            DialogoContatosAluno dialogoContatos = new DialogoContatosAluno(this, alunoParaEditar);
            dialogoContatos.setVisible(true);
        });

         botaoGerenciarMatriculas.addActionListener(e -> {
        // Filtra as matrículas apenas para o aluno que está sendo editado
        List<Matricula> matriculasDoAluno = todasMatriculas.stream()
            .filter(m -> m.getAlunoMatricula() == alunoParaEditar.getMatricula())
            .collect(Collectors.toList());

        DialogoGerenciarMatriculas dialogo = new DialogoGerenciarMatriculas(this, alunoParaEditar, todasAsTurmas, matriculasDoAluno);
        dialogo.setVisible(true);
    });
        // Ações
        botaoSalvar.addActionListener(e -> onSalvar());
        botaoCancelar.addActionListener(e -> dispose());
        
        // Montagem
        setLayout(new BorderLayout(10, 10));
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void onSalvar() {
        // Validação básica
        if (campoMatricula.getText().trim().isEmpty() || campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Matrícula e Nome são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int matricula = Integer.parseInt(campoMatricula.getText());
            String nome = campoNome.getText();
            String endereco = campoEndereco.getText();
            Aluno.TipoAluno tipo = (Aluno.TipoAluno) comboTipo.getSelectedItem();
            Curso cursoSelecionado = (Curso) comboCursos.getSelectedItem();
            
            this.alunoResultante = new Aluno(matricula, nome, endereco, tipo, cursoSelecionado.getCodCurso());
            this.salvo = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Matrícula deve ser um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Aluno getAluno() { return alunoResultante; }
    public boolean foiSalvo() { return salvo; }
}