/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

import com.mycompany.uniufc.Control.Conexao;
import com.mycompany.uniufc.Model.Usuario;
import com.mycompany.uniufc.view.TelaPrincipal;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author IsmaelBrz
 */


public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;

    // --- DADOS MOCKADOS DE USUÁRIOS PARA TESTE ---
    // Em um sistema real, a verificação seria feita no banco de dados.
    private List<Usuario> usuariosCadastrados = null;/* = List.of(
            new Usuario(1, "Administrador DBA", "admin", "admin123", Usuario.NivelAcesso.DBA),
            new Usuario(202510, "Estudante Teste", "aluno.teste", "aluno123", Usuario.NivelAcesso.ALUNO),
            new Usuario(9001, "Professor Turing", "prof.turing", "prof123", Usuario.NivelAcesso.PROFESSOR),
            new Usuario(101, "Funcionário Ana", "func.ana", "func123", Usuario.NivelAcesso.FUNCIONARIO)
    );*/

    public TelaLogin() {
        // 1. Configurações básicas da janela (JFrame)
        super("Login - Sistema Universitário");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centraliza a janela na tela
        
        try {
            usuariosCadastrados = Conexao.listarUsuarios();
        } catch (SQLException ex) {
            System.getLogger(TelaLogin.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(TelaLogin.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        // 2. Painel principal com BorderLayout para organizar os outros painéis
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        // Adiciona uma borda vazia para dar espaçamento interno geral
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(painelPrincipal);

        // 3. Painel para os campos de usuário e senha (usando GridLayout)
        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        
        // 4. Criação dos componentes (Rótulos, Campos de Texto e Botão)
        JLabel labelUsuario = new JLabel("Usuário (Login):");
        campoUsuario = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        botaoEntrar = new JButton("Entrar");

        // 5. Adiciona os componentes ao painel de campos
        painelCampos.add(labelUsuario);
        painelCampos.add(campoUsuario);
        painelCampos.add(labelSenha);
        painelCampos.add(campoSenha);

        // 6. Painel para o botão, para que ele não ocupe toda a largura
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.add(botaoEntrar);

        // 7. Adiciona os painéis de campos e do botão ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);
        
        // 8. Lógica de Autenticação do Botão "Entrar"
        botaoEntrar.addActionListener(e -> {
            String login = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            // Procura o usuário na nossa lista de usuários cadastrados
            Usuario usuarioAutenticado = null;
            for (Usuario u : usuariosCadastrados) {
                if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                    usuarioAutenticado = u;
                    break; // Encontrou o usuário, pode parar o loop
                }
            }

            // Verifica se o usuário foi encontrado
            if (usuarioAutenticado != null) {
                // Se encontrou, abre a TelaPrincipal, passando o objeto do usuário logado
                System.out.println("Login bem-sucedido para: " + usuarioAutenticado.getNome());
                TelaPrincipal telaPrincipal = new TelaPrincipal(usuarioAutenticado);
                telaPrincipal.setVisible(true);
                
                // Fecha a tela de login
                this.dispose();
            } else {
                // Se não encontrou, mostra uma mensagem de erro para o usuário
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}