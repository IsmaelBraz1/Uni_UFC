/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
public class Funcionario {

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getEndereco() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public enum TipoFuncionario {
        PORTARIA, COORDENACAO;

        @Override
        public String toString() {
            // Deixa o nome mais amigável para o JComboBox
            return this == PORTARIA ? "Portaria" : "Coordenacao";
        }
    }

    private int idFuncionario;
    private String nomeFuncionario;
    private String enderecoFuncionario;
    private TipoFuncionario tipoFuncionario;
   

    public Funcionario(int idFuncionario, String nomeFuncionario, String enderecoFuncionario, TipoFuncionario tipoFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.enderecoFuncionario = enderecoFuncionario;
        this.tipoFuncionario = tipoFuncionario;
           }

    // Getters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getEnderecoFuncionario() {
        return enderecoFuncionario;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }
    
}
