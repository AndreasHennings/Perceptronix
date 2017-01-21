package IO;

import BUS.ButtonListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aend on 02.12.16.
 */


public class UserInterface extends JPanel

{
    public ButtonListener bl;
    private JPanel jPanel;
    private JButton trainAIUsingListButton;
    private JTextArea textArea1;
    private JPanel c1;
    private JButton b1;
    private JPanel pm;
    private JButton runAI;
    private JTextField trainFilename;
    private JTextField brainStatus;

    // this main() is called from Perceptronix.main
    public static UserInterface main()
    {
        UserInterface ui = new UserInterface();

        JFrame frame = new JFrame("Perceptronix");
        frame.setContentPane(ui.jPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        return ui;

    }


    public UserInterface ()
    {
    }

    public void init(ButtonListener b)
    {
        bl=b;


       // runAI.addActionListener(this);

        /*
        for (int i =0; i<40;i++)
        {
            textArea1.append("\nblabla");
        }
        */




        trainAIUsingListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bl.onButtonPressed();
            }
                /*
                JFileChooser fc;
                fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(UserInterface.this);
                */

        });


    }
/*

    @Override
    public void actionPerformed(ActionEvent e)
    {
        bl.onButtonPressed();

    }
    */
}
