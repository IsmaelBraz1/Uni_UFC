package com.mycompany.uniufc.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author IsmaelBrz
 */
public class EmailProfessor {
    private int idEmail;
    private String email;
    private int siapeProfessor; // Chave estrangeira

    public EmailProfessor(int idEmail, String email, int siapeProfessor) {
        this.idEmail = idEmail;
        this.email = email;
        this.siapeProfessor = siapeProfessor;
    }

    public int getIdEmail() { return idEmail; }
    public String getEmail() { return email; }
    public int getSiapeProfessor() { return siapeProfessor; }
    
    @Override
    public String toString() {
        return this.email;
    }
}
