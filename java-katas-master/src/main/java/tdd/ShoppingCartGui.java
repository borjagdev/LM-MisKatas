package tdd;

import javax.swing.*;

public class ShoppingCartGui {
    private JButton addArticle1ToButton;
    private JButton addArticle3ToButton;
    private JButton addArticle2ToButton;
    private JTextPane textPane1;
    private JPanel mainPanel;
    private JButton checkOutButton;

    public static void main(String[] args){
         JFrame frame = new JFrame("Shopping cart app");
         frame.setContentPane(new ShoppingCartGui().mainPanel);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);
    }
}
