package be.howest.ti.secure.development.g3.g09;

import javax.swing.*;

public class ExampleG3G07 {
    public static void main(String[] args)
    {
        new ExampleG3G07().run();
    }

    public void run() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createHTMLSwing("<html><h1>HTML rendering<h1></html>");
                createNoHTMLSwing("<html><h1>HTML rendering<h1></html>");
            }
        });
    }

    private static void createHTMLSwing(String input) {
        JFrame frmGUI = new JFrame("Example HTML rendering");
        frmGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(input);
        frmGUI.getContentPane().add(label);

        frmGUI.pack();
        frmGUI.setVisible(true);
    }

    private static void createNoHTMLSwing(String input) {
        JFrame frmGUI = new JFrame("Example HTML rendering");
        frmGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel();
        label.putClientProperty("html.disable", true);
        label.setText(input);
        frmGUI.getContentPane().add(label);

        frmGUI.pack();
        frmGUI.setVisible(true);
    }




}
