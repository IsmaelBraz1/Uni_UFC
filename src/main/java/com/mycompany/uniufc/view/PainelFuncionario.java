/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.view;

/**
 *
 * @author IsmaelBrz
 */

import com.mycompany.uniufc.Model.Departamento;
import com.mycompany.uniufc.Model.Funcionario;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PainelFuncionario extends JPanel {

    private Funcionario funcionarioLogado;

    // Dados mockados de todos os departamentos para a consulta
    private List<Departamento> todosDepartamentos = List.of(
            new Departamento(101, "Ciência da Computação"),
            new Departamento(102, "Engenharia Elétrica")
    );

    public PainelFuncionario(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- PAINEL SUPERIOR COM BOAS-VINDAS ---
        JLabel labelBoasVindas = new JLabel("Bem-vindo(a), " + funcionarioLogado.getNomeFuncionario() + "!");
        labelBoasVindas.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(labelBoasVindas, BorderLayout.NORTH);

        // --- PAINEL CENTRAL COM AS INFORMAÇÕES ---
        JPanel painelInfo = new JPanel(new GridBagLayout());
        painelInfo.setBorder(BorderFactory.createTitledBorder(null, "Informações do Seu Departamento",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        
        
        // Adiciona um "painel de enchimento" para empurrar o conteúdo para cima
        gbc.gridy++; gbc.weighty = 1.0;
        painelInfo.add(new JLabel(), gbc);

        add(painelInfo, BorderLayout.CENTER);
    }
}
