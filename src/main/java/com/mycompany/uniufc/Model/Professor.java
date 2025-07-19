/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uniufc.Model;

/**
 *
 * @author IsmaelBrz
 */
import java.time.LocalDate; // Import necessário para datas

public class Professor {
    private int siape;             // SQL: siape
    private String nomeProf;         // SQL: nome_prof
    private LocalDate dataNasProf;   // SQL: data_nas_prof
    private LocalDate dataIngresso;  // SQL: data_ingresso
    private int codDepart;         // SQL: cod_depart

    public Professor(int siape, String nomeProf, LocalDate dataNasProf, LocalDate dataIngresso, int codDepart) {
        this.siape = siape;
        this.nomeProf = nomeProf;
        this.dataNasProf = dataNasProf;
        this.dataIngresso = dataIngresso;
        this.codDepart = codDepart;
    }

    // Getters
    public int getSiape() {
        return siape;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public LocalDate getDataNasProf() {
        return dataNasProf;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public int getCodDepart() {
        return codDepart;
    }
   
    @Override
    public String toString() {
        return this.nomeProf;
    }
}
