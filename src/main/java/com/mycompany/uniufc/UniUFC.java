/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.uniufc;
import com.mycompany.uniufc.view.TelaLogin;
import javax.swing.SwingUtilities;
/**
 *
 * @author IsmaelBrz
 */
public class UniUFC {

    public static void main(String[] args) {
        
       SwingUtilities.invokeLater(() -> {
            TelaLogin telaDeLogin = new TelaLogin();
            telaDeLogin.setVisible(true);
        });
    }
}
