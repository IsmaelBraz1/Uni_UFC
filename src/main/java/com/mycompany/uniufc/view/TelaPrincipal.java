/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */
import com.mycompany.uniufc.Model.Funcionario;
import com.mycompany.uniufc.Model.Usuario;
import javax.swing.*;
import java.awt.*;



public class TelaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelConteudo;
    private Usuario usuarioLogado;

    public TelaPrincipal(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;

        // Configurações básicas da janela
        super("Sistema Universitário - Bem-vindo(a) " + usuarioLogado.getNome());
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Cria o painel principal com CardLayout
        cardLayout = new CardLayout();
        painelConteudo = new JPanel(cardLayout);

        // --- LÓGICA DE CONTROLE DE ACESSO ---
        // Constrói a interface baseada no nível de acesso do usuário
        switch (usuarioLogado.getNivelAcesso()) {
            case DBA:
                montarInterfaceDBA();
                break;
            case ALUNO:
            case PROFESSOR:
                montarInterfaceUsuarioComum();
                break;
            case FUNCIONARIO:
                montarInterfaceFuncionario();
                break;
        }

        // Montagem final
        this.add(painelConteudo, BorderLayout.CENTER);
    }

    private void montarInterfaceDBA() {
        // Cria a barra de menu completa para o DBA
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair/Logout");
        itemSair.addActionListener(e -> { System.exit(0); });
        menuArquivo.add(itemSair);

        JMenu menuNavegar = new JMenu("Navegar (DBA)");
        JMenuItem itemEntidades = new JMenuItem("Gerenciar Entidades");
        JMenuItem itemConsultas = new JMenuItem("Realizar Consultas");
        itemEntidades.addActionListener(e -> cardLayout.show(painelConteudo, "painelDBA"));
        itemConsultas.addActionListener(e -> cardLayout.show(painelConteudo, "painelConsultas"));
        menuNavegar.add(itemEntidades);
        menuNavegar.add(itemConsultas);

        menuBar.add(menuArquivo);
        menuBar.add(menuNavegar);
        this.setJMenuBar(menuBar);

        // Adiciona os painéis do DBA ao CardLayout
        PainelDBA painelDBA = new PainelDBA();
        painelConteudo.add(painelDBA, "painelDBA");
        PainelConsultas painelConsultas = new PainelConsultas();
        painelConteudo.add(painelConsultas, "painelConsultas");
        
        // Mostra o painel inicial do DBA
        cardLayout.show(painelConteudo, "painelDBA");
    }

    private void montarInterfaceUsuarioComum() {
        // Menu simples para Aluno e Professor
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair/Logout");
        itemSair.addActionListener(e -> { System.exit(0); });
        menuArquivo.add(itemSair);
        menuBar.add(menuArquivo);
        this.setJMenuBar(menuBar);

        // Adiciona e mostra o PainelUsuarioComum
        PainelUsuarioComum painel = new PainelUsuarioComum(this.usuarioLogado);
        painelConteudo.add(painel, "painelPrincipalUsuario");
        cardLayout.show(painelConteudo, "painelPrincipalUsuario");
    }

  private void montarInterfaceFuncionario() {
        // Menu simples para Funcionário
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair/Logout");
        itemSair.addActionListener(e -> { System.exit(0); });
        menuArquivo.add(itemSair);
        this.setJMenuBar(menuBar);

        // Para que o painel funcione, precisamos de um objeto Funcionario.
        // Futuramente, buscaremos este objeto no banco de dados usando o ID do usuarioLogado.
        // Por agora, criamos um mock que corresponde ao usuário "func.ana".
        Funcionario funcionarioMock = new Funcionario(
                usuarioLogado.getIdUsuario(),
                usuarioLogado.getNome(),
                "Secretaria CC",
                Funcionario.TipoFuncionario.COORDENACAO
        );

        // Adiciona e mostra o PainelFuncionario
        PainelFuncionario painel = new PainelFuncionario(funcionarioMock);
        painelConteudo.add(painel, "painelFuncionario");
        cardLayout.show(painelConteudo, "painelFuncionario");
    }
}
