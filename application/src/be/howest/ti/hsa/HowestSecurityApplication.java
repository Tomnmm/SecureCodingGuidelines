/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.hsa;

import be.howest.ti.hsa.gui.Main;
import java.util.Scanner;

/**
 *
 * @author madewael
 */
public class HowestSecurityApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        // readPassword();
        int code = 1074;

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.print("Starting ...");
                new Main(code).setVisible(true);
            }
        });
    }
    
    private static boolean isValidPassword(String s){
        if (s.length() != 7) return false;
        
        if (!s.contains("!")) return false;
        if (!s.contains("0")) return false;
        
        return true;
    }
    
    private static String readPassword() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your password:");
        String secret = in.nextLine();
        while(!isValidPassword(secret)){
            System.out.print("Invalid password, try again:");
            secret = in.nextLine();
        }
        return secret;
    }
    
    private static int readCode() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the code for using the external service:");
        return in.nextInt();
    }
    
}
