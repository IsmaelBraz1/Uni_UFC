/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
public class Aluno {

    public enum TipoAluno {
        GRADUACAO("Graduacao"),
        POSGRADUACAO("PosGraduacao");

        private final String nomeNoBanco;

        TipoAluno(String nome) {
            this.nomeNoBanco = nome;
        }

        @Override
        public String toString() {
            // Isso controla o texto que aparece no JComboBox
            return this.name().replace("_", " ");
        }
    }

    private int matricula;
    private String nomeAlu;
    private String endereco;
    private TipoAluno tipoAlu;
    private int codCurso;

    public Aluno(int matricula, String nomeAlu, String endereco, TipoAluno tipoAlu, int codCurso) {
        this.matricula = matricula;
        this.nomeAlu = nomeAlu;
        this.endereco = endereco;
        this.tipoAlu = tipoAlu;
        this.codCurso = codCurso;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNomeAlu() {
        return nomeAlu;
    }

    public String getEndereco() {
        return endereco;
    }

    public TipoAluno getTipoAlu() {
        return tipoAlu;
    }

    public int getCodCurso() {
        return codCurso;
    }
}
