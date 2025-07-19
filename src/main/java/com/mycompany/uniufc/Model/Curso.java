/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
public class Curso {
    private int codCurso;          // SQL: cod_curso
    private String nomeCurso;        // SQL: nome_curso
    private int creditosMinimos;   // SQL: creditos_minimos
    private int codDepart;         // SQL: cod_depart

    public Curso(int codCurso, String nomeCurso, int creditosMinimos, int codDepart) {
        this.codCurso = codCurso;
        this.nomeCurso = nomeCurso;
        this.creditosMinimos = creditosMinimos;
        this.codDepart = codDepart;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public int getCreditosMinimos() {
        return creditosMinimos;
    }

    public int getCodDepart() {
        return codDepart;
    }
    
    @Override
    public String toString() {
        return this.nomeCurso;
    }
}