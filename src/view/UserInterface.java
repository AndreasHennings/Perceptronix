package view;

import javax.swing.*;

/**
 * Created by aend on 02.12.16.
 */


public class UserInterface
{
    private JPanel jPanel;
    private JTextField PERCEPTRONIXTextField;
    private JTextField trainAITextField;

    public static void main()
    {
        JFrame frame = new JFrame("Perceptronix");
        frame.setContentPane(new UserInterface().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
