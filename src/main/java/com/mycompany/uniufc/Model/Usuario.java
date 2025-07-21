/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */


public class Usuario {

    public enum NivelAcesso {
        DBA, FUNCIONARIO, ALUNO, PROFESSOR;

        @Override
        public String toString() {
            // Garante que o nome seja exibido corretamente (ex: "FUNCIONARIO" -> "Funcionario")
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }
    }

    private int idUsuario;
    private String nome;
    private String login;
    private String senha; // Em um sistema real, isso guardaria o HASH da senha, não a senha em texto plano.
    private NivelAcesso nivelAcesso;

    public Usuario(int idUsuario, String nome, String login, String senha, NivelAcesso nivelAcesso) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    // Getters
    public int getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public NivelAcesso getNivelAcesso() { return nivelAcesso; }
}
