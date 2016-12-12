package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aend on 02.12.16.
 */


public class UserInterface extends JPanel

{
    private JPanel jPanel;
    private JButton trainAIUsingListButton;
    private JButton runButton;
    private JTextField statusReadyTextField;
    private JProgressBar progressBar1;

    // this main() is called from Perceptronix.main
    public static void main()
    {
        JFrame frame = new JFrame("Perceptronix");
        frame.setContentPane(new UserInterface().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public UserInterface()
    {
        init();
    }

    public UserInterface (String[] categories)
    {
        init();
    }

    public void init()
    {
        /*
        trainAIUsingListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fc;
                fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(UserInterface.this);
            }
        });
        */

    }


}
