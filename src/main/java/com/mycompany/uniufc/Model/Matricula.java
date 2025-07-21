/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */


import java.math.BigDecimal;

public class Matricula {
    public enum Situacao { ATIVA, CURSADA; /* ... */ }
    private int alunoMatricula;
    private int turmaId;
    private Situacao situacao;
    private BigDecimal mediaFinal;
    private BigDecimal frequencia;
    public Matricula(int alunoMatricula, int turmaId, Situacao situacao, BigDecimal mediaFinal, BigDecimal frequencia) {
        this.alunoMatricula = alunoMatricula;
        this.turmaId = turmaId;
        this.situacao = situacao;
        this.mediaFinal = mediaFinal;
        this.frequencia = frequencia;
    }

    // Getters
    public int getAlunoMatricula() { return alunoMatricula; }
    public int getTurmaId() { return turmaId; }
    public Situacao getSituacao() { return situacao; }
    public BigDecimal getMediaFinal() { return mediaFinal; }
    public BigDecimal getFrequencia() { return frequencia; }
}

