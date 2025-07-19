/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.uniufc.view;
import com.mycompany.uniufc.view.TelaPrincipal;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author IsmaelBrz
 */
public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    
    public TelaLogin() {
        //Configurações básicas da janela (JFrame)
        super("Login - Sistema Universitário"); // Define o título da janela
        this.setSize(400, 200); // Define o tamanho da janela (largura, altura)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Faz o programa fechar ao clicar no 'X'
        this.setLocationRelativeTo(null); // Centraliza a janela na tela

        //Painel principal com BorderLayout para organizar os outros painéis
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        this.add(painelPrincipal);

        // Painel para os campos de usuário e senha (usando GridLayout)
        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        
        // Adiciona uma borda vazia para dar espaçamento interno
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Criação dos componentes (Rótulos, Campos de Texto e Botão)
        JLabel labelUsuario = new JLabel("Usuário:");
        campoUsuario = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();
        botaoEntrar = new JButton("Entrar");

        // ação do botão
        botaoEntrar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());
            System.out.println("Usuário: " + usuario);
            System.out.println("Senha: " + senha);
            
             // --- LÓGICA DE TRANSIÇÃO DE TELA ---
            // instância da tela principal
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            TelaLogin.this.dispose();//Fecha a tela de login atual
                 
            // aqui deve ser chamada a lógica de autenticação
            
        });

        // Adiciona os componentes ao painel de campos
        painelCampos.add(labelUsuario);
        painelCampos.add(campoUsuario);
        painelCampos.add(labelSenha);
        painelCampos.add(campoSenha);

        // Painel para o botão
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        painelBotao.add(botaoEntrar);

        //Adiciona os painéis de campos e do botão ao painel principal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);
    }
}
