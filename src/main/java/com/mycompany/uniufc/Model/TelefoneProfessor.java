/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
public class TelefoneProfessor {
    private int idTelefone;
    private String numero;
    private String descricao;
    private int siapeProfessor;

    public TelefoneProfessor(int idTelefone, String numero, String descricao, int siapeProfessor) {
        this.idTelefone = idTelefone;
        this.numero = numero;
        this.descricao = descricao;
        this.siapeProfessor = siapeProfessor;
    }

    public int getIdTelefone() { return idTelefone; }
    public String getNumero() { return numero; }
    public String getDescricao() { return descricao; }
    public int getSiapeProfessor() { return siapeProfessor; }

    // Essencial para o JList exibir o texto correto
    @Override
    public String toString() {
        return this.numero + " (" + this.descricao + ")";
    }
}
