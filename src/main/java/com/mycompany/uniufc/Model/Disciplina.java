package com.mycompany.uniufc.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author IsmaelBrz
 */

public class Disciplina {

    public enum TipoDisciplina {
        OBRIGATORIA("obrigatoria"),
        OPTATIVA("Optativa");

        private final String nome;

        TipoDisciplina(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            // Texto que aparecerá no JComboBox e na tabela
            return this.name().equals("OBRIGATORIA") ? "Obrigatória" : "Optativa";
        }
    }

    private int codDisc;
    private String nomeDisc;
    private String ementa;
    private int numCreditos;
    private TipoDisciplina tipo;
    private int codCurso;

    public Disciplina(int codDisc, String nomeDisc, String ementa, int numCreditos, TipoDisciplina tipo, int codCurso) {
        this.codDisc = codDisc;
        this.nomeDisc = nomeDisc;
        this.ementa = ementa;
        this.numCreditos = numCreditos;
        this.tipo = tipo;
        this.codCurso = codCurso;
    }

    // Getters
    public int getCodDisc() { return codDisc; }
    public String getNomeDisc() { return nomeDisc; }
    public String getEmenta() { return ementa; }
    public int getNumCreditos() { return numCreditos; }
    public TipoDisciplina getTipo() { return tipo; }
    public int getCodCurso() { return codCurso; }

    @Override
    public String toString() {
        return this.nomeDisc;
    }
}