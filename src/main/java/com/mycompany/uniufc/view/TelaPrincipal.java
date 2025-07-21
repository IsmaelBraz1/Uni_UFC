/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelConteudo;

    public TelaPrincipal() {
        //Configurações básicas da janela principal
        super("Sistema Universitário - Painel Principal");
        this.setSize(800, 600); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Garante que a aplicação feche
        this.setLocationRelativeTo(null); // Centraliza na tela

        // Criação da Barra de Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair/Logout");
        
        // Ação para o item "Sair" fecha a aplicaçao
        itemSair.addActionListener(e -> {
            System.exit(0); 
        });
        
        menuArquivo.add(itemSair);
        
        JMenu menuEntidades = new JMenu("Entidades");
        
        JMenu menuNavegar = new JMenu("Consultas");
        
        
        
        
        //ouvinte de clique de mouse para fazer o menu agir como um botão
        menuEntidades.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Comando para mostrar o painel do DBA
                cardLayout.show(painelConteudo, "painelDBA");
            }
        });
         menuNavegar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Comando para mostrar o painel do DBA
                cardLayout.show(painelConteudo, "painelConsultas");
            }
        });

        
        JMenu menuConsultas = new JMenu("Arquivo");
        menuBar.add(menuArquivo);
        menuBar.add(menuEntidades); 
         menuBar.add(menuNavegar);
        this.setJMenuBar(menuBar);
        
        
        // Define a barra de menu para esta janela
        this.setJMenuBar(menuBar);

        // --- PAINEL DE CONTEÚDO ---
        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);
        
        JPanel painelBoasVindas = new JPanel(new GridBagLayout());
        painelBoasVindas.add(new JLabel("Bem-vindo ao Sistema! Use o menu para navegar."));
        painelConteudo.add(painelBoasVindas, "boasVindas");

        //Criar e adicionar o painel do DBA
        PainelDBA painelDBA = new PainelDBA();
        painelConteudo.add(painelDBA, "painelDBA");
        
         PainelConsultas painelConsultas = new PainelConsultas();
        painelConteudo.add(painelConsultas, "painelConsultas");

        this.add(painelConteudo, BorderLayout.CENTER);
        
        cardLayout.show(painelConteudo, "boasVindas");
    }
}