/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
public class TelefoneAluno {
    private int idTelefone;
    private String numero;
    private String descricao;
    private int matriculaAluno; // Chave estrangeira

    public TelefoneAluno(int idTelefone, String numero, String descricao, int matriculaAluno) {
        this.idTelefone = idTelefone;
        this.numero = numero;
        this.descricao = descricao;
        this.matriculaAluno = matriculaAluno;
    }

    public int getIdTelefone() { return idTelefone; }
    public String getNumero() { return numero; }
    public String getDescricao() { return descricao; }
    public int getMatriculaAluno() { return matriculaAluno; }

    @Override
    public String toString() {
        return this.numero + " (" + this.descricao + ")";
    }
}