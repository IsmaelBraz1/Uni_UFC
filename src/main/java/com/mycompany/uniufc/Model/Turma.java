/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */

public class Turma {
    private int idTurma;
    private int ano;
    private int semestre;
    private int siapeProf1;
    private int codDisc;
    private Integer siapeProf2; // Ponto Importante: Usamos a classe Integer

    public Turma(int idTurma, int ano, int semestre, int codDisc, int siapeProf1, Integer siapeProf2) {
        this.idTurma = idTurma;
        this.ano = ano;
        this.semestre = semestre;
        this.codDisc = codDisc;
        this.siapeProf1 = siapeProf1;
        this.siapeProf2 = siapeProf2;
    }

    // Getters
    public int getIdTurma() { return idTurma; }
    public int getAno() { return ano; }
    public int getSemestre() { return semestre; }
    public int getSiapeProf1() { return siapeProf1; }
    public int getCodDisc() { return codDisc; }
    public Integer getSiapeProf2() { return siapeProf2; }
}
