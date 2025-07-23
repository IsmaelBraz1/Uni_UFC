/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
public class Departamento {
    private int codDepart;    // SQL: cod_depart
    private String nomeDepart; // SQL: nome_depart

    public Departamento(int codDepart, String nomeDepart) {
        this.codDepart = codDepart;
        this.nomeDepart = nomeDepart;
    }

    public int getCodDepart() {
        return codDepart;
    }

    public String getNomeDepart() {
        return nomeDepart;
    }
 
    @Override
    public String toString() {
        return this.nomeDepart;
    }
}