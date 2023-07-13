/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.formdev.flatlaf.FlatIntelliJLaf;
import frames.Login;
import frames.PrincipalFrame;
import javax.swing.UIManager;

/**
 *
 * @author Panana
 */
public class PrincipalClass {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Login login = new Login();
    }
}
